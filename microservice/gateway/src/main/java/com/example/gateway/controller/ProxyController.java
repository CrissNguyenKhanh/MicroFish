package com.example.gateway.controller;

import java.net.URI;
import java.nio.charset.StandardCharsets;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientResponseException;

@RestController
public class ProxyController {

	private final RestClient restClient;
	private final String userServiceBaseUrl;

	public ProxyController(
			RestClient.Builder restClientBuilder,
			@Value("${services.user-service.base-url}") String userServiceBaseUrl) {
//		tao httpclient
		this.restClient = restClientBuilder.build();
		this.userServiceBaseUrl = userServiceBaseUrl;
	}

	@RequestMapping(
			value = {"/hello", "/hello/**", "/users", "/users/**"},
			method = {
					org.springframework.web.bind.annotation.RequestMethod.GET,
					org.springframework.web.bind.annotation.RequestMethod.POST,
					org.springframework.web.bind.annotation.RequestMethod.PUT,
					org.springframework.web.bind.annotation.RequestMethod.DELETE,
					org.springframework.web.bind.annotation.RequestMethod.PATCH,
					org.springframework.web.bind.annotation.RequestMethod.OPTIONS
			})
//hàm xử lí full mọi dịch vụ khi gặp các http reqeust khác nhau Universal HTTP Forwarder
	public ResponseEntity<byte[]> forward(HttpServletRequest request) {
//		lay request tu user gui len ( day al lop tiep can voi request cua users dau tien geateway -> cac servicekhac)
		String targetUrl = buildTargetUrl(request);
		HttpMethod method = HttpMethod.valueOf(request.getMethod());
		byte[] body = readBody(request);

		try {
			RestClient.RequestBodySpec requestSpec = restClient.method(method).uri(URI.create(targetUrl));
			String contentType = request.getContentType();
			if (contentType != null && !contentType.isBlank()) {
				requestSpec.contentType(MediaType.parseMediaType(contentType));
			}

			RestClient.RequestHeadersSpec<?> headersSpec = body.length > 0 ? requestSpec.body(body) : requestSpec;
			ResponseEntity<byte[]> response = headersSpec.retrieve().toEntity(byte[].class);

			return ResponseEntity.status(response.getStatusCode())
					.headers(copyHeaders(response.getHeaders()))
					.body(response.getBody());
		}
		catch (RestClientResponseException ex) {
			return ResponseEntity.status(ex.getStatusCode())
					.headers(copyHeaders(ex.getResponseHeaders()))
					.body(ex.getResponseBodyAsByteArray());
		}
		catch (Exception ex) {
			byte[] responseBody = "{\"message\":\"gateway unavailable\"}".getBytes(StandardCharsets.UTF_8);
			return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
					.contentType(MediaType.APPLICATION_JSON)
					.body(responseBody);
		}
	}

	private String buildTargetUrl(HttpServletRequest request) {
		StringBuilder target = new StringBuilder(userServiceBaseUrl);
		target.append(request.getRequestURI());
		if (request.getQueryString() != null && !request.getQueryString().isBlank()) {
			target.append('?').append(request.getQueryString());
		}
		return target.toString();
	}

	private byte[] readBody(HttpServletRequest request) {
		try {
			return StreamUtils.copyToByteArray(request.getInputStream());
		}
		catch (Exception ex) {
			return new byte[0];
		}
	}

	private HttpHeaders copyHeaders(HttpHeaders source) {
		HttpHeaders headers = new HttpHeaders();
		if (source == null) {
			return headers;
		}

		MediaType contentType = source.getContentType();
		if (contentType != null) {
			headers.setContentType(contentType);
		}
		if (source.getLocation() != null) {
			headers.setLocation(source.getLocation());
		}
		return headers;
	}
}
