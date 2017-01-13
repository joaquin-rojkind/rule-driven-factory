package com.appdirect.rdf.factory;

public class UnsupportedObjectException extends Exception {

	private static final long serialVersionUID = -5030692430883953274L;

	private final String message;

	public UnsupportedObjectException(String message) {
		super();
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}
}
