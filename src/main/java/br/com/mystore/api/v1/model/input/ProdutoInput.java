package br.com.mystore.api.v1.model.imput;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProdutoInput {

	@ApiModelProperty(example = "Carregador de Celular", required = true)
	@NotBlank
	private String nome;
	
	@ApiModelProperty(example = "Com a fonte e cabo USB", required = true)
	@NotBlank
	private String descricao;
	
	@ApiModelProperty(example = "20.00", required = true)
	@NotNull
	@PositiveOrZero
	private BigDecimal venda;
	
	@ApiModelProperty(example = "7.00", required = true)
	@NotNull
	@PositiveOrZero
	private BigDecimal compra;
	
	@ApiModelProperty(example = "10.00", required = true)
	@NotNull
	@PositiveOrZero
	private BigDecimal custo;
	
	@ApiModelProperty(example = "true", required = true)
	@NotNull
	private Boolean ativo;
	
}
