package com.appdirect.rdf.factory;

import java.util.function.Predicate;

public abstract class Rule<T, R> {

	protected final Predicate<T> predicate;

	public Rule() {
		predicate = null;
	}

	public Rule(Predicate<T> predicate) {
		this.predicate = predicate;
	}

	public abstract boolean evaluate(T evaluable);

	public abstract R provideCompliantResult();

}
