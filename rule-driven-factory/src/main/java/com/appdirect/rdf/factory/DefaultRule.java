package com.appdirect.rdf.factory;

public class DefaultRule<T, R> extends Rule<T, R> {

	private final R defaultResult;

	public DefaultRule(R defaultResult) {
		this.defaultResult = defaultResult;
	}

	@Override
	public boolean evaluate(T evaluable) {
		return true;
	}

	@Override
	public R provideCompliantResult() {
		return defaultResult;
	}
}
