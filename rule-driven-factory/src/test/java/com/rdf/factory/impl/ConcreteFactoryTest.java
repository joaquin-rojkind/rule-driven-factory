package com.rdf.factory.impl;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.rdf.domain.CompliantObject;
import com.rdf.domain.EvaluableObject;
import com.rdf.domain.EvaluableObject.Level;
import com.rdf.domain.EvaluableObject.Matcher;
import com.rdf.domain.EvaluableObject.Rule;
import com.rdf.factory.UnsupportedObjectException;

public class ConcreteFactoryTest {

	@Test
	public void testProvide_successLevel_1() throws UnsupportedObjectException {

		EvaluableObject evaluable = new EvaluableObject()
				.addMatcher(Level.LEVEL_1, Rule.RULE_1)
				.addMatcher(Level.LEVEL_2, Rule.NO_MATCH)
				.addMatcher(Level.LEVEL_3, Rule.NO_MATCH);

		CompliantObject compliantObject1 = ConcreteFactory.getInstance().provide(evaluable);
		CompliantObject compliantObject2 = OldStyleFactory.getInstance().provide(evaluable);

		assertThat(compliantObject1.getMatcher()).isEqualTo(Matcher.LEVEL_1_RULE_1);
		assertThat(compliantObject2.getMatcher()).isEqualTo(Matcher.LEVEL_1_RULE_1);
	}

	@Test
	public void testProvide_successLevel_2() throws UnsupportedObjectException {

		EvaluableObject evaluable = new EvaluableObject()
				.addMatcher(Level.LEVEL_1, Rule.RULE_2)
				.addMatcher(Level.LEVEL_2, Rule.RULE_2)
				.addMatcher(Level.LEVEL_3, Rule.NO_MATCH);

		CompliantObject compliantObject1 = ConcreteFactory.getInstance().provide(evaluable);
		CompliantObject compliantObject2 = OldStyleFactory.getInstance().provide(evaluable);

		assertThat(compliantObject1.getMatcher()).isEqualTo(Matcher.LEVEL_2_RULE_2);
		assertThat(compliantObject2.getMatcher()).isEqualTo(Matcher.LEVEL_2_RULE_2);
	}

	@Test
	public void testProvide_successLevel_3() throws UnsupportedObjectException {

		EvaluableObject evaluable = new EvaluableObject()
				.addMatcher(Level.LEVEL_1, Rule.RULE_2)
				.addMatcher(Level.LEVEL_2, Rule.RULE_1)
				.addMatcher(Level.LEVEL_3, Rule.RULE_3);

		CompliantObject compliantObject1 = ConcreteFactory.getInstance().provide(evaluable);
		CompliantObject compliantObject2 = OldStyleFactory.getInstance().provide(evaluable);

		assertThat(compliantObject1.getMatcher()).isEqualTo(Matcher.LEVEL_3_RULE_3);
		assertThat(compliantObject2.getMatcher()).isEqualTo(Matcher.LEVEL_3_RULE_3);
	}

	@Test
	public void testProvide_successLevel_1_Default() throws UnsupportedObjectException {

		EvaluableObject evaluable = new EvaluableObject()
				.addMatcher(Level.LEVEL_1, Rule.NO_MATCH)
				.addMatcher(Level.LEVEL_2, Rule.NO_MATCH)
				.addMatcher(Level.LEVEL_3, Rule.NO_MATCH);

		CompliantObject compliantObject1 = ConcreteFactory.getInstance().provide(evaluable);
		CompliantObject compliantObject2 = OldStyleFactory.getInstance().provide(evaluable);

		assertThat(compliantObject1.getMatcher()).isEqualTo(Matcher.LEVEL_1_DEFAULT);
		assertThat(compliantObject2.getMatcher()).isEqualTo(Matcher.LEVEL_1_DEFAULT);
	}

	@Test
	public void testProvide_successLevel_2_Default() throws UnsupportedObjectException {

		EvaluableObject evaluable = new EvaluableObject()
				.addMatcher(Level.LEVEL_1, Rule.RULE_2)
				.addMatcher(Level.LEVEL_2, Rule.NO_MATCH)
				.addMatcher(Level.LEVEL_3, Rule.NO_MATCH);

		CompliantObject compliantObject1 = ConcreteFactory.getInstance().provide(evaluable);
		CompliantObject compliantObject2 = OldStyleFactory.getInstance().provide(evaluable);

		assertThat(compliantObject1.getMatcher()).isEqualTo(Matcher.LEVEL_2_DEFAULT);
		assertThat(compliantObject2.getMatcher()).isEqualTo(Matcher.LEVEL_2_DEFAULT);
	}

	@Test(expected = UnsupportedObjectException.class)
	public void testProvide_failureNoMatch() throws UnsupportedObjectException {

		EvaluableObject evaluable = new EvaluableObject()
				.addMatcher(Level.LEVEL_1, Rule.RULE_2)
				.addMatcher(Level.LEVEL_2, Rule.RULE_1)
				.addMatcher(Level.LEVEL_3, Rule.NO_MATCH);

		CompliantObject compliantObject = OldStyleFactory.getInstance().provide(evaluable);

		// OldStyleFactory returns a null object, no exception thrown.
		assertThat(compliantObject).isNull();
		ConcreteFactory.getInstance().provide(evaluable);
	}
}
