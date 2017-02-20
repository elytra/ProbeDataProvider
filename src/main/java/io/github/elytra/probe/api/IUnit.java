package io.github.elytra.probe.api;

/**
 * Represents information about a quantity being expressed. Nominally, the unit of measurement, but also the kind of
 * thing being measured.
 */
public interface IUnit {
	public String getFullName();
	public String getAbbreviation();
	public int getBarColor();
}
