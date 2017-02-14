package io.github.elytra.probe.api.impl;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.google.common.collect.ImmutableList;

import io.github.elytra.probe.api.IProbeData;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class ProbeData implements IProbeData {
	private String label = null;
	private int barMin = -1;
	private int barCur = -1;
	private int barMax = -1;
	private String barUnit = null;
	private ImmutableList<ItemStack> inventory = null;
	
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
	
	/**
	 * Adds an inventory to this ProbeData
	 * @param inventory the contents of all itemslots, full or empty, in this datum.
	 * @return this ProbeData
	 */
	public ProbeData withInventory(@Nonnull ImmutableList<ItemStack> inventory) {
		this.inventory = inventory;
		return this;
	}

	@Override
	public boolean hasLabel() {
		return label!=null && !label.isEmpty();
	}

	@Override
	public boolean hasBar() {
		return barCur!=-1 && barMax!=-1;
	}

	@Override
	@Nonnull
	public String getLabel() {
		return label!=null ? label : "";
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
	@Nonnull
	public String getBarUnit() {
		return barUnit!=null ? barUnit : "";
	}

	@Override
	public boolean hasInventory() {
		return inventory!=null;
	}

	@Override
	@Nullable
	public ImmutableList<ItemStack> getInventory() {
		return inventory;
	}

}
