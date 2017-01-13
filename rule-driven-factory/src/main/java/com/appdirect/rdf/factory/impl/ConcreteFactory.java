package com.appdirect.rdf.factory.impl;

import com.appdirect.rdf.domain.CompliantObject;
import com.appdirect.rdf.domain.EvaluableObject;
import com.appdirect.rdf.domain.EvaluableObject.Block;
import com.appdirect.rdf.domain.EvaluableObject.Level;
import com.appdirect.rdf.factory.RuleDrivenFactory;
import com.appdirect.rdf.factory.Ruleset;

public class ConcreteFactory extends RuleDrivenFactory<EvaluableObject, CompliantObject> {

	private static final ConcreteFactory instance = new ConcreteFactory();

	private ConcreteFactory() {
	}

	public static ConcreteFactory getInstance() {
		return instance;
	}

	@Override
	protected Ruleset<EvaluableObject, CompliantObject> initializeRuleset() {

		return new Ruleset<EvaluableObject, CompliantObject>()

				/*** FIRST LEVEL ***/

				.addSingleRule(evaluable -> evaluable.getMatcher(Level.LEVEL_1).value() == Block.BLOCK_1.value(),
						new CompliantObject("First Level - Block 1"))
				.addCompositeRule(evaluable -> evaluable.getMatcher(Level.LEVEL_1).value() == Block.BLOCK_2.value(),
						new Ruleset<EvaluableObject, CompliantObject>()

								/*** SECOND LEVEL ***/

								.addCompositeRule(evaluable -> evaluable.getMatcher(Level.LEVEL_2).value() == Block.BLOCK_1.value(),
										new Ruleset<EvaluableObject, CompliantObject>()

												/*** THIRD LEVEL - (NO DEFAULT OUTPUT) ***/

												.addSingleRule(evaluable -> evaluable.getMatcher(Level.LEVEL_3).value() == Block.BLOCK_1.value(),
														new CompliantObject("Third Level - Block 1"))
												.addSingleRule(evaluable -> evaluable.getMatcher(Level.LEVEL_3).value() == Block.BLOCK_2.value(),
														new CompliantObject("Third Level - Block 2"))
												.addSingleRule(evaluable -> evaluable.getMatcher(Level.LEVEL_3).value() == Block.BLOCK_3.value(),
														new CompliantObject("Third Level - Block 3"))) // default rule missing

								.addSingleRule(evaluable -> evaluable.getMatcher(Level.LEVEL_2).value() == Block.BLOCK_2.value(),
										new CompliantObject("Second Level - Block 2"))
								.addDefaultRule(new CompliantObject("Second Level - Block 3")))

				.addDefaultRule(new CompliantObject("First Level - Block 3"));
	}
}
