package com.appdirect.rdf.factory.impl;

import com.appdirect.rdf.domain.CompliantObject;
import com.appdirect.rdf.domain.EvaluableObject;

public class OldStyleFactory {

	private static final OldStyleFactory instance = new OldStyleFactory();

	private OldStyleFactory() {
	}

	public static OldStyleFactory getInstance() {
		return instance;
	}

	public CompliantObject provide(EvaluableObject evaluable) {

		CompliantObject compliantObject = null;
		
		/*** FIRST LEVEL - WITH DEFAULT OUTPUT ***/
		
		if (evaluable.getFirstLevelMatch() == 1) {
			compliantObject = new CompliantObject("First Level - Block 1");
		} else if (evaluable.getFirstLevelMatch() == 2) {
			
			/*** SECOND LEVEL - NO DEFAULT OUTPUT ***/
			
			if (evaluable.getSecondLevelMatch() == 1) {
				
				/*** THIRD LEVEL - NO DEFAULT OUTPUT ***/

				if (evaluable.getThirdLevelMatch() == 1) {
					compliantObject = new CompliantObject("Third Level - Block 1");
				} else if (evaluable.getThirdLevelMatch() == 2) {
					compliantObject = new CompliantObject("Third Level - Block 2");
				} else if (evaluable.getThirdLevelMatch() == 3) {
					compliantObject = new CompliantObject("Third Level - Block 3");
				}
				
			} else if (evaluable.getSecondLevelMatch() == 2) {
				compliantObject = new CompliantObject("Second Level - Block 2");
			} else if (evaluable.getSecondLevelMatch() == 3) {
				compliantObject = new CompliantObject("Second Level - Block 3");
			}
			
		} else { // default output
			compliantObject = new CompliantObject("First Level - Block 3");
		}
		return compliantObject;
	}
}
