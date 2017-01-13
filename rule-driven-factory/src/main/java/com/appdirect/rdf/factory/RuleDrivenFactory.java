package com.appdirect.rdf.factory;

import java.util.Optional;

public abstract class RuleDrivenFactory<T, R> {

	private final Ruleset<T, R> ruleset;

	protected RuleDrivenFactory() {
		this.ruleset = initializeRuleset();
	}
	
	public R provide(T evaluable) throws UnsupportedObjectException {
		R compliantResult = null;
		for (Rule<T, R> rule : ruleset.getRules()) {
			if (rule.evaluate(evaluable)) {
				compliantResult = rule.provideCompliantResult();
				break;
			}
		}
		return Optional.ofNullable(compliantResult)
				.orElseThrow(() -> new UnsupportedObjectException("No compliant result could be found for the given evaluable object."));
	}
	
	protected abstract Ruleset<T, R> initializeRuleset();
}
