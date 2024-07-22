package br.com.omnilink.gestaodefrota.exceptions;

public class VeiculoNotFoundException extends RuntimeException {
	public VeiculoNotFoundException(String message) {
		super(message);
	}
}