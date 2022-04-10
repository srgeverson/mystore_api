package br.com.mystore.api.v1.controller;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mystore.api.v1.openapi.EstatisticasControllerOpenApi;

@RestController
@RequestMapping(path = "/v1/estatisticas")
public class EstatisticasController implements EstatisticasControllerOpenApi {


	public static class EstatisticasModel extends RepresentationModel<EstatisticasModel> {
	}

}
