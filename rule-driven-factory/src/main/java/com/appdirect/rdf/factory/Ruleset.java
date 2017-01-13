package com.appdirect.rdf.factory;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Ruleset<T, R> {

	private final List<Rule<T, R>> rules = new ArrayList<>();
	
	public Ruleset<T, R> addSingleRule(Predicate<T> predicate, R compliantResult) {
		rules.add(new SingleRule<T, R>(predicate, compliantResult));
		return this;
	}

	public Ruleset<T, R> addCompositeRule(Predicate<T> predicate, Ruleset<T, R> ruleSubset) {
		rules.add(new CompositeRule<T, R>(predicate, ruleSubset));
		return this;
	}

	public List<Rule<T, R>> getRules() {
		return rules;
	}

}
