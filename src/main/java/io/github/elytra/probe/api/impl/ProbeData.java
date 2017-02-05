package io.github.elytra.probe.api.impl;

import io.github.elytra.probe.api.IProbeData;
import net.minecraft.util.ResourceLocation;

public class ProbeData implements IProbeData {
	private ResourceLocation icon = null;
	private String label = null;
	private int barMin = -1;
	private int barCur = -1;
	private int barMax = -1;
	private String barUnit = null;
	
	/**
	 * Creates a blank data line
	 */
	public ProbeData() {}
	
	/**
	 * Creates a data line with text information
	 * @param label the information to be displayed on this line
	 */
	public ProbeData(String label) {
		this.label = label;
	}
	
	/**
	 * Creates a data line with an icon
	 * @param icon the icon to be displayed on this line
	 */
	public ProbeData(ResourceLocation icon) {
		this.icon = icon;
	}
	
	/**
	 * Adds an icon to this ProbeData
	 * @param icon the icon to add to this ProbeData
	 * @return this ProbeData
	 */
	public ProbeData withIcon(ResourceLocation icon) {
		this.icon = icon;
		return this;
	}
	
	/**
	 * Adds an label to this ProbeData
	 * @param label the text to add to this ProbeData
	 * @return this ProbeData
	 */
	public ProbeData withLabel(String label) {
		this.label = label;
		return this;
	}
	
	/**
	 * Adds a bar to this ProbeData
	 * @param minimum The lowest possible value for the bar
	 * @param current The current value of the bar. Must be between minimum and maximum, inclusive
	 * @param maximum The highest possible value for the bar
	 * @param unit The unit that the quantities in the bar are expressed in. Use an empty string to specify no units.
	 * @return this ProbeData
	 */
	public ProbeData withBar(int minimum, int current, int maximum, String unit) {
		this.barMin = minimum;
		this.barCur = current;
		this.barMax = maximum;
		this.barUnit = unit;
		return this;
	}
	
	@Override
	public boolean hasIcon() {
		return icon!=null;
	}

	@Override
	public boolean hasLabel() {
		return label!=null;
	}

	@Override
	public boolean hasBar() {
		return barCur!=-1 && barMax!=-1;
	}

	@Override
	public ResourceLocation getIcon() {
		return icon;
	}

	@Override
	public String getLabel() {
		return label;
	}

	@Override
	public int getBarMinimum() {
		return barMin;
	}
	
	@Override
	public int getBarCurrent() {
		return barCur;
	}

	@Override
	public int getBarMaximum() {
		return barMax;
	}

	@Override
	public String getBarUnit() {
		return barUnit;
	}

}
