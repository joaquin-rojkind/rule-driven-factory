package com.rdf.domain;

import java.util.HashMap;
import java.util.Map;

public class EvaluableObject {

	private final Map<Level, Rule> matchers = new HashMap<>();

	public enum Level {
		LEVEL_1, LEVEL_2, LEVEL_3;
	}

	public enum Rule {
		RULE_1, RULE_2, RULE_3, NO_MATCH;
	}

	public enum Matcher {
		LEVEL_1_RULE_1,
		LEVEL_1_RULE_2,
		LEVEL_1_RULE_3,
		LEVEL_1_DEFAULT,

		LEVEL_2_RULE_1,
		LEVEL_2_RULE_2,
		LEVEL_2_RULE_3,
		LEVEL_2_DEFAULT,

		LEVEL_3_RULE_1,
		LEVEL_3_RULE_2,
		LEVEL_3_RULE_3,
		LEVEL_3_DEFAULT
	}

	public EvaluableObject addMatcher(Level level, Rule rule) {
		matchers.put(level, rule);
		return this;
	}

	public Rule getMatcher(Level level) {
		return matchers.get(level);
	}
}
