package com.appdirect.rdf.factory;

import java.util.function.Predicate;

public class SingleRule<T, R> extends Rule<T, R> {

	private final R compliantResult;

	public SingleRule(Predicate<T> predicate, R compliantResult) {
		super(predicate);
		this.compliantResult = compliantResult;
	}

	@Override
	public boolean evaluate(T evaluable) {
		return predicate.test(evaluable);
	}

	@Override
	public R provideCompliantResult() {
		return compliantResult;
	}

}
