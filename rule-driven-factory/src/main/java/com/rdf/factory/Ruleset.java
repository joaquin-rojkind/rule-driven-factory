package com.rdf.factory;

import java.util.List;

public class Ruleset<T, R> {

	private final List<Rule<T, R>> rules;
	private final R defaultResult;
	
	public Ruleset(List<Rule<T, R>> rules, R defaultResult) {
		this.rules = rules;
		this.defaultResult = defaultResult;
	}

	public R evaluateAndProvide(T evaluable) throws UnsupportedObjectException {
		R compliantResult = null;
		for (Rule<T, R> rule : rules) {
			if (rule.evaluate(evaluable)) {
				compliantResult = rule.provideCompliantResult();
				break;
			}
		}
		if (compliantResult != null) {
			return compliantResult;
		} else if (defaultResult != null) {
			return defaultResult;
		} else {
			throw new UnsupportedObjectException("No compliant result could be found for the given evaluable object.");
		}
	}
}
