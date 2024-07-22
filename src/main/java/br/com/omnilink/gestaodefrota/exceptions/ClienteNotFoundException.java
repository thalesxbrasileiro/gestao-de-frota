package br.com.omnilink.gestaodefrota.exceptions;

public class ClienteNotFoundException extends RuntimeException {
	public ClienteNotFoundException(String message) {
		super(message);
	}
}