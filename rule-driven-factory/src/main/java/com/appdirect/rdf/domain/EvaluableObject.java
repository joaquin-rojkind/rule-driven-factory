package com.appdirect.rdf.domain;

public class EvaluableObject {

	private int firstLevelMatch;
	private int secondLevelMatch;
	private int thirdLevelMatch;

	public enum BlockNumber {

		BLOCK_1(1),BLOCK_2(2),BLOCK_3(3);

		private int number;

		BlockNumber(int blockNumber) {
			this.number = blockNumber;
		}

		public int number() {
			return number;
		}
	}

	public EvaluableObject firstLevelMatch(BlockNumber blockNumber) {
		this.firstLevelMatch = blockNumber.number();
		return this;
	}

	public EvaluableObject secondLevelMatch(BlockNumber blockNumber) {
		this.secondLevelMatch = blockNumber.number();
		return this;
	}

	public EvaluableObject thirdLevelMatch(BlockNumber blockNumber) {
		this.thirdLevelMatch = blockNumber.number();
		return this;
	}

	public int getFirstLevelMatch() {
		return firstLevelMatch;
	}

	public int getSecondLevelMatch() {
		return secondLevelMatch;
	}

	public int getThirdLevelMatch() {
		return thirdLevelMatch;
	}
}
