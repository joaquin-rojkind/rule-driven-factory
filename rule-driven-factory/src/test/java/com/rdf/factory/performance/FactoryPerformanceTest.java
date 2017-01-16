package com.rdf.factory.performance;

import java.util.concurrent.TimeUnit;

import org.junit.Test;

import com.rdf.domain.EvaluableObject;
import com.rdf.domain.EvaluableObject.Level;
import com.rdf.domain.EvaluableObject.Rule;
import com.rdf.factory.UnsupportedObjectException;
import com.rdf.factory.impl.ConcreteFactory;
import com.rdf.factory.impl.OldStyleFactory;

public class FactoryPerformanceTest {

	@Test
	public void test_Performance() throws UnsupportedObjectException {

		EvaluableObject evaluable = new EvaluableObject()
				.addMatcher(Level.LEVEL_1, Rule.RULE_2)
				.addMatcher(Level.LEVEL_2, Rule.RULE_1)
				.addMatcher(Level.LEVEL_3, Rule.RULE_3);

		ConcreteFactory.getInstance();
		OldStyleFactory.getInstance();

		long startTime1 = System.nanoTime();
		for (int i = 0; i < 10000; i++) {
			ConcreteFactory.getInstance().provide(evaluable);
		}
		long responseTime1 = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startTime1);

		long startTime2 = System.nanoTime();
		for (int i = 0; i < 10000; i++) {
			OldStyleFactory.getInstance().provide(evaluable);
		}
		long responseTime2 = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startTime2);

		System.out.println("RuleDrivenFactory response time: " + responseTime1 + "ms");
		System.out.println("OldStyleFactory response time: " + responseTime2 + "ms");
	}

}
