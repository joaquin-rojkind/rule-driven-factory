package com.appdirect.rdf.factory.impl;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.appdirect.rdf.domain.CompliantObject;
import com.appdirect.rdf.domain.EvaluableObject;
import com.appdirect.rdf.domain.EvaluableObject.Block;
import com.appdirect.rdf.domain.EvaluableObject.Level;
import com.appdirect.rdf.factory.UnsupportedObjectException;

public class ConcreteFactoryTest {

	@Test
	public void testProvide_successLevel_1() throws UnsupportedObjectException {

		EvaluableObject evaluable = new EvaluableObject()
				.addMatcher(Level.LEVEL_1, Block.BLOCK_2)
				.addMatcher(Level.LEVEL_2, Block.BLOCK_2)
				.addMatcher(Level.LEVEL_3, Block.NO_MATCH);

		CompliantObject compliantObject1 = ConcreteFactory.getInstance().provide(evaluable);
		CompliantObject compliantObject2 = OldStyleFactory.getInstance().provide(evaluable);

		assertThat(compliantObject1.getMessage()).isEqualTo("Second Level - Block 2");
		assertThat(compliantObject2.getMessage()).isEqualTo("Second Level - Block 2");
	}

	@Test
	public void testProvide_successLevel_2() throws UnsupportedObjectException {

		EvaluableObject evaluable = new EvaluableObject()
				.addMatcher(Level.LEVEL_1, Block.BLOCK_3)
				.addMatcher(Level.LEVEL_2, Block.NO_MATCH)
				.addMatcher(Level.LEVEL_3, Block.NO_MATCH);

		CompliantObject compliantObject1 = ConcreteFactory.getInstance().provide(evaluable);
		CompliantObject compliantObject2 = OldStyleFactory.getInstance().provide(evaluable);

		assertThat(compliantObject1.getMessage()).isEqualTo("First Level - Block 3");
		assertThat(compliantObject2.getMessage()).isEqualTo("First Level - Block 3");
	}

	@Test
	public void testProvide_successLevel_3() throws UnsupportedObjectException {

		EvaluableObject evaluable = new EvaluableObject()
				.addMatcher(Level.LEVEL_1, Block.BLOCK_2)
				.addMatcher(Level.LEVEL_2, Block.BLOCK_1)
				.addMatcher(Level.LEVEL_3, Block.BLOCK_3);

		CompliantObject compliantObject1 = ConcreteFactory.getInstance().provide(evaluable);
		CompliantObject compliantObject2 = OldStyleFactory.getInstance().provide(evaluable);

		assertThat(compliantObject1.getMessage()).isEqualTo("Third Level - Block 3");
		assertThat(compliantObject2.getMessage()).isEqualTo("Third Level - Block 3");
	}

	@Test
	public void testProvide_successDefault() throws UnsupportedObjectException {

		EvaluableObject evaluable = new EvaluableObject()
				.addMatcher(Level.LEVEL_1, Block.NO_MATCH)
				.addMatcher(Level.LEVEL_2, Block.NO_MATCH)
				.addMatcher(Level.LEVEL_3, Block.NO_MATCH);

		CompliantObject compliantObject1 = ConcreteFactory.getInstance().provide(evaluable);
		CompliantObject compliantObject2 = OldStyleFactory.getInstance().provide(evaluable);

		assertThat(compliantObject1.getMessage()).isEqualTo("First Level - Block 3");
		assertThat(compliantObject2.getMessage()).isEqualTo("First Level - Block 3");
	}

	@Test(expected = UnsupportedObjectException.class)
	public void testProvide_failureNoMatch() throws UnsupportedObjectException {

		EvaluableObject evaluable = new EvaluableObject()
				.addMatcher(Level.LEVEL_1, Block.BLOCK_2)
				.addMatcher(Level.LEVEL_2, Block.BLOCK_1)
				.addMatcher(Level.LEVEL_3, Block.NO_MATCH);

		CompliantObject compliantObject = OldStyleFactory.getInstance().provide(evaluable);

		// OldStyleFactory returns a null object, no exception thrown.
		assertThat(compliantObject).isNull();
		ConcreteFactory.getInstance().provide(evaluable);
	}
}
