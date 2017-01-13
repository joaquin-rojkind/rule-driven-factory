package com.appdirect.rdf.factory.impl;

import com.appdirect.rdf.domain.CompliantObject;
import com.appdirect.rdf.domain.EvaluableObject;
import com.appdirect.rdf.factory.AbstractRuleDrivenFactory;
import com.appdirect.rdf.factory.Ruleset;

public class ConcreteRuleDrivenFactory extends AbstractRuleDrivenFactory<EvaluableObject, CompliantObject> {

	private static final ConcreteRuleDrivenFactory instance = new ConcreteRuleDrivenFactory();
	
	private ConcreteRuleDrivenFactory() {
	}

	public static ConcreteRuleDrivenFactory getInstance() {
		return instance; 
	}

	@Override
	protected Ruleset<EvaluableObject, CompliantObject> initializeRuleset() {

		return new Ruleset<EvaluableObject, CompliantObject>()

				/*** FIRST LEVEL - WITH DEFAULT OUTPUT ***/

				.addSingleRule(evaluable -> evaluable.getFirstLevelMatch() == 1,
						new CompliantObject("First Level - Block 1"))
				.addCompositeRule(evaluable -> evaluable.getFirstLevelMatch() == 2,
						new Ruleset<EvaluableObject, CompliantObject>()
						
								/*** SECOND LEVEL - NO DEFAULT OUTPUT ***/

								.addCompositeRule(evaluable -> evaluable.getSecondLevelMatch() == 1,
										new Ruleset<EvaluableObject, CompliantObject>()

												/*** THIRD LEVEL - NO DEFAULT OUTPUT ***/

												.addSingleRule(evaluable -> evaluable.getThirdLevelMatch() == 1,
														new CompliantObject("Third Level - Block 1"))
												.addSingleRule(evaluable -> evaluable.getThirdLevelMatch() == 2,
														new CompliantObject("Third Level - Block 2"))
												.addSingleRule(evaluable -> evaluable.getThirdLevelMatch() == 3,
														new CompliantObject("Third Level - Block 3")))

								.addSingleRule(evaluable -> evaluable.getSecondLevelMatch() == 2,
										new CompliantObject("Second Level - Block 2"))
								.addSingleRule(evaluable -> evaluable.getSecondLevelMatch() == 3,
										new CompliantObject("Second Level - Block 3")))

				.addSingleRule(evaluable -> true, // default output
						new CompliantObject("First Level - Block 3"));
	}
}
