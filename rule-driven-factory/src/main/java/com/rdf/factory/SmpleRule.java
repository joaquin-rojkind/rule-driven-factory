package com.rdf.factory;

import java.util.function.Predicate;

public class SmpleRule<T, R> extends Rule<T, R> {

	public SmpleRule(Predicate<T> predicate, R compliantResult) {
		super(predicate, compliantResult);
	}

	@Override
	public boolean evaluate(T evaluable) {
		return predicate.test(evaluable);
	}
}
