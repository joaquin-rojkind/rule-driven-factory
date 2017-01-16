package com.rdf.factory.impl;

import com.rdf.domain.CompliantObject;
import com.rdf.domain.EvaluableObject;
import com.rdf.domain.EvaluableObject.Level;
import com.rdf.domain.EvaluableObject.Matcher;
import com.rdf.domain.EvaluableObject.Rule;
import com.rdf.factory.RuleDrivenFactory;
import com.rdf.factory.Ruleset;
import com.rdf.factory.RulsetBuilder;

public class ConcreteFactory extends RuleDrivenFactory<EvaluableObject, CompliantObject> {

	private static final ConcreteFactory instance = new ConcreteFactory();

	private ConcreteFactory() {
	}

	public static ConcreteFactory getInstance() {
		return instance;
	}

	@Override
	protected Ruleset<EvaluableObject, CompliantObject> initializeRuleset() {

		return new RulsetBuilder<EvaluableObject, CompliantObject>()

				/*** FIRST LEVEL ***/

				.addSingleRule(evaluable -> evaluable.getMatcher(Level.LEVEL_1) == Rule.RULE_1,
						new CompliantObject(Matcher.LEVEL_1_RULE_1))
				.addCompositeRule(evaluable -> evaluable.getMatcher(Level.LEVEL_1) == Rule.RULE_2,
						new RulsetBuilder<EvaluableObject, CompliantObject>()

								/*** SECOND LEVEL ***/

								.addCompositeRule(evaluable -> evaluable.getMatcher(Level.LEVEL_2) == Rule.RULE_1,
										new RulsetBuilder<EvaluableObject, CompliantObject>()

												/*** THIRD LEVEL - (NO DEFAULT RESULT) ***/

												.addSingleRule(evaluable -> evaluable.getMatcher(Level.LEVEL_3) == Rule.RULE_1,
														new CompliantObject(Matcher.LEVEL_3_RULE_1))
												.addSingleRule(evaluable -> evaluable.getMatcher(Level.LEVEL_3) == Rule.RULE_2,
														new CompliantObject(Matcher.LEVEL_3_RULE_2))
												.addSingleRule(evaluable -> evaluable.getMatcher(Level.LEVEL_3) == Rule.RULE_3,
														new CompliantObject(Matcher.LEVEL_3_RULE_3))
												.build())
								

								.addSingleRule(evaluable -> evaluable.getMatcher(Level.LEVEL_2) == Rule.RULE_2,
										new CompliantObject(Matcher.LEVEL_2_RULE_2))
								.setDefaultResult(new CompliantObject(Matcher.LEVEL_2_DEFAULT))
								.build())

				.setDefaultResult(new CompliantObject(Matcher.LEVEL_1_DEFAULT))
				.build();
	}
}
