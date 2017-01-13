package com.appdirect.rdf.factory;

import java.util.function.Predicate;

public class CompositeRule<T, R> extends Rule<T, R> {

	private final Ruleset<T, R> ruleSubset;
	private T evaluable;

	public CompositeRule(Predicate<T> predicate, Ruleset<T, R> ruleSubset) {
		super(predicate);
		this.ruleSubset = ruleSubset;
	}

	@Override
	public boolean evaluate(T evaluable) {
		boolean evaluationResult = predicate.test(evaluable);
		if (evaluationResult) {
			this.evaluable = evaluable;
		}
		return evaluationResult;
	}

	@Override
	public R provideCompliantResult() {
		for (Rule<T, R> rule : ruleSubset.getRules()) {
			if (rule.evaluate(evaluable)) {
				return rule.provideCompliantResult();
			}
		}
		return null;
	}
}
