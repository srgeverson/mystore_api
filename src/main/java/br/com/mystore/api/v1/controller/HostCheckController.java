package br.com.mystore.api.v1.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mystore.domain.exception.NegocioException;

@RestController
@RequestMapping(path = "/v1/hosts", produces = MediaType.APPLICATION_JSON_VALUE)
public class HostCheckController {

	@GetMapping("/check")
	public String checkHost() {
		var host = "";
		try {
			host = String.format("IP: %s, Hostname: %s", InetAddress.getLocalHost().getHostAddress(),
					InetAddress.getLocalHost().getHostName());
		} catch (UnknownHostException e) {
			throw new NegocioException(e.getMessage(), e);
		}
		return host;
	}

}
