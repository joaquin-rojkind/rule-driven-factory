package com.rdf.factory;

import java.util.function.Predicate;

public abstract class Rule<T, R> {

	protected final Predicate<T> predicate;
	protected R compliantResult;

	public Rule(Predicate<T> predicate) {
		this.predicate = predicate;
	}

	public Rule(Predicate<T> predicate, R compliantResult) {
		this.predicate = predicate;
		this.compliantResult = compliantResult;
	}

	public abstract boolean evaluate(T evaluable) throws UnsupportedObjectException;

	public R provideCompliantResult() {
		return compliantResult;
	}
}
