package com.rdf.domain;

import com.rdf.domain.EvaluableObject.Matcher;

public class CompliantObject {

	private Matcher matcher;

	public CompliantObject(Matcher matcher) {
		this.matcher = matcher;
	}

	public Matcher getMatcher() {
		return matcher;
	}
}
