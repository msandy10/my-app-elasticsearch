package my.app.elasticsearch.service;

import io.micronaut.http.HttpStatus;

public class SearchException extends Exception {
	private static final long serialVersionUID = 1L;
	HttpStatus statusCode;
	
	public HttpStatus getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(HttpStatus statusCode) {
		this.statusCode = statusCode;
	}

	public SearchException(HttpStatus stausCode, String message) {
		super(message);
		this.statusCode = stausCode;

	}
}
