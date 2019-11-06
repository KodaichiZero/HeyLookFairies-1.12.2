package com.kodaichizero.heylookfairies.util;

public enum EnumShoulderSide {
	
	NONE(0, "none"),
	LEFT(1, "left"),
	RIGHT(2, "right"),
	BOTH(3, "both");

	int index;
	String name;
	
	private EnumShoulderSide(int index, String name) {
		this.index = index;
		this.name = name;
	}
}
