package com.appdirect.rdf.factory.impl;

import com.appdirect.rdf.domain.CompliantObject;
import com.appdirect.rdf.domain.EvaluableObject;
import com.appdirect.rdf.domain.EvaluableObject.Block;
import com.appdirect.rdf.domain.EvaluableObject.Level;

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

		if (evaluable.getMatcher(Level.LEVEL_1).value() == Block.BLOCK_1.value()) {
			compliantObject = new CompliantObject("First Level - Block 1");
		} else if (evaluable.getMatcher(Level.LEVEL_1).value() == Block.BLOCK_2.value()) {

			/*** SECOND LEVEL ***/

			if (evaluable.getMatcher(Level.LEVEL_2).value() == Block.BLOCK_1.value()) {

				/*** THIRD LEVEL - (NO DEFAULT OUTPUT) ***/

				if (evaluable.getMatcher(Level.LEVEL_3).value() == Block.BLOCK_1.value()) {
					compliantObject = new CompliantObject("Third Level - Block 1");
				} else if (evaluable.getMatcher(Level.LEVEL_3).value() == Block.BLOCK_2.value()) {
					compliantObject = new CompliantObject("Third Level - Block 2");
				} else if (evaluable.getMatcher(Level.LEVEL_3).value() == Block.BLOCK_3.value()) {
					compliantObject = new CompliantObject("Third Level - Block 3");
				} // else block missing

			} else if (evaluable.getMatcher(Level.LEVEL_2).value() == Block.BLOCK_2.value()) {
				compliantObject = new CompliantObject("Second Level - Block 2");
			} else {
				compliantObject = new CompliantObject("Second Level - Block 3");
			}

		} else {
			compliantObject = new CompliantObject("First Level - Block 3");
		}
		return compliantObject;
	}
}
