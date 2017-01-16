package com.rdf.factory.impl;

import com.rdf.domain.CompliantObject;
import com.rdf.domain.EvaluableObject;
import com.rdf.domain.EvaluableObject.Level;
import com.rdf.domain.EvaluableObject.Matcher;
import com.rdf.domain.EvaluableObject.Rule;

public class OldStyleFactory {

	private static final OldStyleFactory instance = new OldStyleFactory();

	private OldStyleFactory() {
	}

	public static OldStyleFactory getInstance() {
		return instance;
	}

	public CompliantObject provide(EvaluableObject evaluable) {

		CompliantObject compliantObject = null;

		/*** FIRST LEVEL ***/

		if (evaluable.getMatcher(Level.LEVEL_1) == Rule.RULE_1) {
			compliantObject = new CompliantObject(Matcher.LEVEL_1_RULE_1);
		} else if (evaluable.getMatcher(Level.LEVEL_1) == Rule.RULE_2) {

			/*** SECOND LEVEL ***/

			if (evaluable.getMatcher(Level.LEVEL_2) == Rule.RULE_1) {

				/*** THIRD LEVEL - (NO DEFAULT RESULT) ***/

				if (evaluable.getMatcher(Level.LEVEL_3) == Rule.RULE_1) {
					compliantObject = new CompliantObject(Matcher.LEVEL_3_RULE_1);
				} else if (evaluable.getMatcher(Level.LEVEL_3) == Rule.RULE_2) {
					compliantObject = new CompliantObject(Matcher.LEVEL_3_RULE_2);
				} else if (evaluable.getMatcher(Level.LEVEL_3) == Rule.RULE_3) {
					compliantObject = new CompliantObject(Matcher.LEVEL_3_RULE_3);
				}

			} else if (evaluable.getMatcher(Level.LEVEL_2) == Rule.RULE_2) {
				compliantObject = new CompliantObject(Matcher.LEVEL_2_RULE_2);
			} else {
				compliantObject = new CompliantObject(Matcher.LEVEL_2_DEFAULT);
			}

		} else {
			compliantObject = new CompliantObject(Matcher.LEVEL_1_DEFAULT);
		}
		return compliantObject;
	}
}
