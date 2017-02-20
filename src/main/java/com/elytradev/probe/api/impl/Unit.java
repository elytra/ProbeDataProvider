package com.elytradev.probe.api.impl;

import java.text.NumberFormat;

import com.elytradev.probe.api.IUnit;

public class Unit implements IUnit {
	private final String name;
	private final String abbreviation;
	private final int barColor;
	
	public Unit(String name, String abbreviation) {
		this(name, abbreviation, 0xAAAAAA);
	}
	
	public Unit(String name, String abbreviation, int barColor) {
		this.name = name;
		this.abbreviation = abbreviation;
		this.barColor = barColor;
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

	/* Convenience Methods */
	
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
	private static final NumberFormat FORMAT = NumberFormat.getNumberInstance();
	static {
		FORMAT.setMinimumFractionDigits(0);
		FORMAT.setMaximumFractionDigits(2);
	}
	
	public static String formatSI(double d, IUnit u) {
		if (d==0) return FORMAT.format(d)+" "+u.getAbbreviation();
		
		double magnitude = Math.abs(d);
		
		if (magnitude>HELLA) {
			return FORMAT.format(d/HELLA)+" X"+u.getAbbreviation();
		} else if (magnitude>YOTTA) {
			return FORMAT.format(d/YOTTA)+" Y"+u.getAbbreviation();
		} else if (magnitude>ZETTA) {
			return FORMAT.format(d/ZETTA)+" Z"+u.getAbbreviation();
		} else if (magnitude>EXA) {
			return FORMAT.format(d/EXA)+" E"+u.getAbbreviation();
		} else if (magnitude>PETA) {
			return FORMAT.format(d/PETA)+" P"+u.getAbbreviation();
		} else if (magnitude>TERA) {
			return FORMAT.format(d/TERA)+" T"+u.getAbbreviation();
		} else if (magnitude>GIGA) {
			return FORMAT.format(d/GIGA)+" G"+u.getAbbreviation();
		} else if (magnitude>MEGA) {
			return FORMAT.format(d/MEGA)+" M"+u.getAbbreviation();
		} else if (magnitude>KILO) {
			return FORMAT.format(d/KILO)+" k"+u.getAbbreviation();
			
		//if we ever added femto/atto/zepto/yocto they'd go here
		//dividing by the reciprocal down there should totally work. It's not the most efficient way, but it's consistent.
			
		} else if (magnitude<NANO) {
			return FORMAT.format(d/PICO)+" p"+u.getAbbreviation();
		} else if (magnitude<MICRO) {
			return FORMAT.format(d/NANO)+" n"+u.getAbbreviation();
		} else if (magnitude<MILLI) {
			return FORMAT.format(d/MICRO)+" µ"+u.getAbbreviation();
		} else if (magnitude<1.0) {
			return FORMAT.format(d/MILLI)+" m"+u.getAbbreviation();
		} else {
			return FORMAT.format(d)+" "+u.getAbbreviation();
		}
	}
}
