package com.rdf.factory;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class RulsetBuilder<T, R> {

	private List<Rule<T, R>> rules = new ArrayList<>();
	private R defaultResult;

	public RulsetBuilder<T, R> addSingleRule(Predicate<T> predicate, R compliantResult) {
		rules.add(new SmpleRule<T, R>(predicate, compliantResult));
		return this;
	}

	public RulsetBuilder<T, R> addCompositeRule(Predicate<T> predicate, Ruleset<T, R> ruleSubset) {
		rules.add(new CompositeRule<T, R>(predicate, ruleSubset));
		return this;
	}

	public RulsetBuilder<T, R> setDefaultResult(R defaultResult) {
		this.defaultResult = defaultResult;
		return this;
	}

	public Ruleset<T, R> build() {
		return new Ruleset<>(rules, defaultResult);
	}
}
