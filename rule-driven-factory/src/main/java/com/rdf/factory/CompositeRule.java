package com.rdf.factory;

import java.util.function.Predicate;

public class CompositeRule<T, R> extends Rule<T, R> {

	private final Ruleset<T, R> ruleSubset;

	public CompositeRule(Predicate<T> predicate, Ruleset<T, R> ruleSubset) {
		super(predicate);
		this.ruleSubset = ruleSubset;
	}

	@Override
	public boolean evaluate(T evaluable) throws UnsupportedObjectException {
		boolean evaluation = predicate.test(evaluable);
		if (evaluation) {
			compliantResult = ruleSubset.evaluateAndProvide(evaluable);
		}
		return evaluation;
	}
}
