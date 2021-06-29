package br.com.paulistense.mystore.api.exceptionhendler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.databind.JsonMappingException.Reference;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;

import br.com.paulistense.mystore.api.exceptionhendler.Problema.ProblemaBuilder;
import br.com.paulistense.mystore.domain.exception.EntidadeEmUsoException;
import br.com.paulistense.mystore.domain.exception.EntidadeNaoEncontradaException;
import br.com.paulistense.mystore.domain.exception.NegocioException;

@ControllerAdvice
public class ApiExceptionHendler extends ResponseEntityExceptionHandler {

	@Autowired
	private MessageSource messageSource;

	public static final String MSG_ERRO_GENERICA_USUARIO_FINAL = "Ocorreu um erro interno inesperado no sistema. Tente novamente e se "
			+ "o problema persistir, entre em contato com o administrador do sistema.";

	private ProblemaBuilder createProblemBuilder(HttpStatus status, ProblemaTipo problemaTipo, String detail) {
		return Problema.builder().dataHora(LocalDateTime.now()).status(status.value()).tipo(problemaTipo.getUri())
				.titulo(problemaTipo.getTitulo()).detalhe(detail);
	}

	@ExceptionHandler(EntidadeEmUsoException.class)
	public ResponseEntity<?> handleEntidadeEmUso(EntidadeEmUsoException ex, WebRequest request) {
		HttpStatus status = HttpStatus.CONFLICT;
		ProblemaTipo problemaTipo = ProblemaTipo.ENTIDADE_EM_USO;
		String detail = ex.getMessage();

		Problema problema = createProblemBuilder(status, problemaTipo, detail).menssagemUsuario(detail).build();

		return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
	}

	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<?> handleEntidadeNaoEncontrada(EntidadeNaoEncontradaException ex, WebRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		ProblemaTipo problemaTipo = ProblemaTipo.RECURSO_NAO_ENCONTRADO;
		String detail = ex.getMessage();

		Problema problema = createProblemBuilder(status, problemaTipo, detail).menssagemUsuario(detail).build();

		return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
	}

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		if (body == null) {
			body = Problema.builder().dataHora(LocalDateTime.now()).titulo(status.getReasonPhrase())
					.status(status.value()).menssagemUsuario(MSG_ERRO_GENERICA_USUARIO_FINAL).build();
		} else if (body instanceof String) {
			body = Problema.builder().dataHora(LocalDateTime.now()).titulo((String) body).status(status.value())
					.menssagemUsuario(MSG_ERRO_GENERICA_USUARIO_FINAL).build();
		}

		return super.handleExceptionInternal(ex, body, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		Throwable rootCause = ExceptionUtils.getRootCause(ex); // unwrapInvocationTargetException(ex);

		if (rootCause instanceof InvalidFormatException) {
			return handleInvalidFormat((InvalidFormatException) rootCause, headers, status, request);
		} else if (rootCause instanceof PropertyBindingException) {
			return handlePropertyBinding((PropertyBindingException) rootCause, headers, status, request);
		}

		ProblemaTipo problemaTipo = ProblemaTipo.MENSAGEM_INCOMPREENSIVEL;
		String detail = "O corpo da requisição está inválido. Verifique erro de sintaxe.";

		Problema problema = createProblemBuilder(status, problemaTipo, detail)
				.menssagemUsuario(MSG_ERRO_GENERICA_USUARIO_FINAL).build();

		return handleExceptionInternal(ex, problema, headers, status, request);
	}

	private ResponseEntity<Object> handleInvalidFormat(InvalidFormatException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		String path = joinPath(ex.getPath());

		ProblemaTipo problemaTipo = ProblemaTipo.MENSAGEM_INCOMPREENSIVEL;
		String detail = String.format(
				"A propriedade '%s' recebeu o valor '%s', "
						+ "que é de um tipo inválido. Corrija e informe um valor compatível com o tipo %s.",
				path, ex.getValue(), ex.getTargetType().getSimpleName());

		Problema problema = createProblemBuilder(status, problemaTipo, detail)
				.menssagemUsuario(MSG_ERRO_GENERICA_USUARIO_FINAL).build();

		return handleExceptionInternal(ex, problema, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			org.springframework.http.HttpHeaders headers, HttpStatus status, WebRequest request) {
		ProblemaTipo problemaTipo = ProblemaTipo.DADOS_INVALIDOS;
		String detail = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.";

		BindingResult bindingResult = ex.getBindingResult();

		List<Problema.Campo> problemFields = bindingResult.getFieldErrors().stream().map(fieldError -> {
			String message = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());

			return Problema.Campo.builder().nome(fieldError.getField()).menssagemUsuario(message).build();
		}).collect(Collectors.toList());

		Problema problema = createProblemBuilder(status, problemaTipo, detail).menssagemUsuario(detail)
				.campos(problemFields).build();

		return handleExceptionInternal(ex, problema, headers, status, request);
	}

	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<?> handleNegocio(NegocioException ex, WebRequest request) {

		HttpStatus status = HttpStatus.BAD_REQUEST;
		ProblemaTipo problemaTipo = ProblemaTipo.ERRO_NEGOCIO;
		String detail = ex.getMessage();

		Problema problema = createProblemBuilder(status, problemaTipo, detail).menssagemUsuario(detail).build();

		return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
	}

	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		ProblemaTipo problemaTipo = ProblemaTipo.RECURSO_NAO_ENCONTRADO;
		String detail = String.format("O recurso %s, que você tentou acessar, é inexistente.", ex.getRequestURL());

		Problema problema = createProblemBuilder(status, problemaTipo, detail)
				.menssagemUsuario(MSG_ERRO_GENERICA_USUARIO_FINAL).build();

		return handleExceptionInternal(ex, problema, headers, status, request);
	}

	private ResponseEntity<Object> handlePropertyBinding(PropertyBindingException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		String path = joinPath(ex.getPath());

		ProblemaTipo problemaTipo = ProblemaTipo.MENSAGEM_INCOMPREENSIVEL;
		String detail = String.format(
				"A propriedade '%s' não existe. " + "Corrija ou remova essa propriedade e tente novamente.", path);

		Problema problema = createProblemBuilder(status, problemaTipo, detail)
				.menssagemUsuario(MSG_ERRO_GENERICA_USUARIO_FINAL).build();

		return handleExceptionInternal(ex, problema, headers, status, request);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleUncaught(Exception ex, WebRequest request) {
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		ProblemaTipo problemaTipo = ProblemaTipo.ERRO_DE_SISTEMA;
		String detail = MSG_ERRO_GENERICA_USUARIO_FINAL;

		ex.printStackTrace();

		Problema problema = createProblemBuilder(status, problemaTipo, detail).menssagemUsuario(detail).build();

		return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
	}

	private String joinPath(List<Reference> references) {
		return references.stream().map(ref -> ref.getFieldName()).collect(Collectors.joining("."));
	}
}