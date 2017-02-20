package com.elytradev.probe.api.impl;

import java.text.NumberFormat;

import com.elytradev.probe.api.IUnit;

public class Unit implements IUnit {
	private static final NumberFormat FORMAT_STANDARD = NumberFormat.getNumberInstance();
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

	private static final double HELLA = 1_000_000_000_000_000_000_000_000_000D;
	private static final double YOTTA = 1_000_000_000_000_000_000_000_000D;
	private static final double ZETTA = 1_000_000_000_000_000_000_000D;
	private static final double EXA   = 1_000_000_000_000_000_000D;
	private static final double PETA  = 1_000_000_000_000_000D;
	private static final double TERA  = 1_000_000_000_000D;
	private static final double GIGA  = 1_000_000_000D;
	private static final double MEGA  = 1_000_000D;
	private static final double KILO  = 1_000D;
	private static final double MILLI = 1/1_000D;
	private static final double MICRO = 1/1_000_000D;
	private static final double NANO  = 1/1_000_000_000D;
	private static final double PICO  = 1/1_000_000_000_000D;
	
	@Override
	public String format(double d) {
		String space = (spaceAfterNumber) ? " " : "";
		
		if (d==0) return format.format(d)+space+getAbbreviation();
		
		double magnitude = Math.abs(d);
		
		if (magnitude>HELLA) {
			return format.format(d/HELLA)+space+"X"+getAbbreviation();
		} else if (magnitude>YOTTA) {
			return format.format(d/YOTTA)+space+"Y"+getAbbreviation();
		} else if (magnitude>ZETTA) {
			return format.format(d/ZETTA)+space+"Z"+getAbbreviation();
		} else if (magnitude>EXA) {
			return format.format(d/EXA)+space+"E"+getAbbreviation();
		} else if (magnitude>PETA) {
			return format.format(d/PETA)+space+"P"+getAbbreviation();
		} else if (magnitude>TERA) {
			return format.format(d/TERA)+space+"T"+getAbbreviation();
		} else if (magnitude>GIGA) {
			return format.format(d/GIGA)+space+"G"+getAbbreviation();
		} else if (magnitude>MEGA) {
			return format.format(d/MEGA)+space+"M"+getAbbreviation();
		} else if (magnitude>KILO) {
			return format.format(d/KILO)+space+"k"+getAbbreviation();
			
		//if we ever added femto/atto/zepto/yocto they'd go here
		//dividing by the reciprocal down there should totally work. It's not the most efficient way, but it's consistent.
			
		} else if (magnitude<NANO) {
			return format.format(d/PICO)+space+"p"+getAbbreviation();
		} else if (magnitude<MICRO) {
			return format.format(d/NANO)+space+"n"+getAbbreviation();
		} else if (magnitude<MILLI) {
			return format.format(d/MICRO)+space+"µ"+getAbbreviation();
		} else if (magnitude<1.0) {
			return format.format(d/MILLI)+space+"m"+getAbbreviation();
		} else {
			return format.format(d)+space+getAbbreviation();
		}
	}
}
