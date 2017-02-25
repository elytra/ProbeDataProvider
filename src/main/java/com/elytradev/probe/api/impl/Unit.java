package com.elytradev.probe.api.impl;

import java.text.NumberFormat;

import com.elytradev.probe.api.IUnit;

public class Unit implements IUnit {
	public static final NumberFormat FORMAT_STANDARD = NumberFormat.getNumberInstance();
	static {
		FORMAT_STANDARD.setMinimumFractionDigits(0);
		FORMAT_STANDARD.setMaximumFractionDigits(2);
	}
	
	
	private final String name;
	private final String abbreviation;
	private final int barColor;
	protected final NumberFormat format;
	protected final boolean spaceAfterNumber;
	
	public Unit(String name, String abbreviation) {
		this(name, abbreviation, 0xAAAAAA, FORMAT_STANDARD, true);
	}
	
	public Unit(String name, String abbreviation, int barColor) {
		this(name, abbreviation, barColor, FORMAT_STANDARD, true);
	}
	
	public Unit(String name, String abbreviation, int barColor, NumberFormat nfmt, boolean spaceAfterNumber) {
		this.name = name;
		this.abbreviation = abbreviation;
		this.barColor = barColor;
		this.format = nfmt;
		this.spaceAfterNumber = spaceAfterNumber;
	}
	
	@Override
	public String getFullName() {
		return name;
	}

	@Override
	public String getAbbreviation() {
		return abbreviation;
	}

	@Override
	public int getBarColor() {
		return barColor;
	}

	@Override
	public String format(double d) {
		String space = (spaceAfterNumber) ? " " : "";
		return format.format(d)+space+getAbbreviation();
	}
	
}
