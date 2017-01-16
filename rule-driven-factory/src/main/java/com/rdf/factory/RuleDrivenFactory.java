package com.rdf.factory;

public abstract class RuleDrivenFactory<T, R> {

	private final Ruleset<T, R> ruleset;

	protected RuleDrivenFactory() {
		this.ruleset = initializeRuleset();
	}
	
	public R provide(T evaluable) throws UnsupportedObjectException {
		return ruleset.evaluateAndProvide(evaluable);
	}

	protected abstract Ruleset<T, R> initializeRuleset();
}
