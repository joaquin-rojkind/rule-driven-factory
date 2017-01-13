package com.appdirect.rdf.domain;

import java.util.HashMap;
import java.util.Map;

public class EvaluableObject {

	private final Map<Level, Block> matchers = new HashMap<>();

	public enum Level {
		LEVEL_1(1), LEVEL_2(2), LEVEL_3(3);

		private int value;

		Level(int level) {
			this.value = level;
		}

		public int value() {
			return value;
		}
	}

	public enum Block {
		BLOCK_1(1), BLOCK_2(2), BLOCK_3(3), NO_MATCH(99);

		private int value;

		Block(int block) {
			this.value = block;
		}

		public int value() {
			return value;
		}
	}

	public EvaluableObject addMatcher(Level level, Block block) {
		matchers.put(level, block);
		return this;
	}

	public Block getMatcher(Level level) {
		return matchers.get(level);
	}
}
