package com.appdirect.rdf.factory.impl;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.appdirect.rdf.domain.CompliantObject;
import com.appdirect.rdf.domain.EvaluableObject;
import com.appdirect.rdf.domain.EvaluableObject.BlockNumber;
import com.appdirect.rdf.factory.UnsupportedObjectException;

public class ConcreteRuleDrivenFactoryTest {

	@Test
	public void testProvide_successLevel_1() throws UnsupportedObjectException {

		EvaluableObject evaluable = new EvaluableObject()
				.firstLevelMatch(BlockNumber.BLOCK_2)
				.secondLevelMatch(BlockNumber.BLOCK_2);

		CompliantObject compliantObject1 = ConcreteRuleDrivenFactory.getInstance().provide(evaluable);
		CompliantObject compliantObject2 = OldStyleFactory.getInstance().provide(evaluable);

		assertThat(compliantObject1.getMessage()).isEqualTo("Second Level - Block 2");
		assertThat(compliantObject2.getMessage()).isEqualTo("Second Level - Block 2");
	}

	@Test
	public void testProvide_successLevel_2() throws UnsupportedObjectException {

		EvaluableObject evaluable = new EvaluableObject()
				.firstLevelMatch(BlockNumber.BLOCK_3);

		CompliantObject compliantObject1 = ConcreteRuleDrivenFactory.getInstance().provide(evaluable);
		CompliantObject compliantObject2 = OldStyleFactory.getInstance().provide(evaluable);

		assertThat(compliantObject1.getMessage()).isEqualTo("First Level - Block 3");
		assertThat(compliantObject2.getMessage()).isEqualTo("First Level - Block 3");
	}

	@Test
	public void testProvide_successLevel_3() throws UnsupportedObjectException {

		EvaluableObject evaluable = new EvaluableObject()
				.firstLevelMatch(BlockNumber.BLOCK_2)
				.secondLevelMatch(BlockNumber.BLOCK_1)
				.thirdLevelMatch(BlockNumber.BLOCK_3);

		CompliantObject compliantObject1 = ConcreteRuleDrivenFactory.getInstance().provide(evaluable);
		CompliantObject compliantObject2 = OldStyleFactory.getInstance().provide(evaluable);

		assertThat(compliantObject1.getMessage()).isEqualTo("Third Level - Block 3");
		assertThat(compliantObject2.getMessage()).isEqualTo("Third Level - Block 3");
	}

	@Test
	public void testProvide_successDefault() throws UnsupportedObjectException {

		EvaluableObject evaluable = new EvaluableObject();

		CompliantObject compliantObject1 = ConcreteRuleDrivenFactory.getInstance().provide(evaluable);
		CompliantObject compliantObject2 = OldStyleFactory.getInstance().provide(evaluable);

		assertThat(compliantObject1.getMessage()).isEqualTo("First Level - Block 3");
		assertThat(compliantObject2.getMessage()).isEqualTo("First Level - Block 3");
	}

	@Test(expected = UnsupportedObjectException.class)
	public void testProvide_failureNoMatch() throws UnsupportedObjectException {

		EvaluableObject evaluable = new EvaluableObject()
				.firstLevelMatch(BlockNumber.BLOCK_2)
				.secondLevelMatch(BlockNumber.BLOCK_1);

		CompliantObject compliantObject = OldStyleFactory.getInstance().provide(evaluable);

		// OldStyleFactory returns a null object, no exception thrown.
		assertThat(compliantObject).isNull();
		ConcreteRuleDrivenFactory.getInstance().provide(evaluable);
	}
}
