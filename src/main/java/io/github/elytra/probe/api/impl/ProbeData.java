package io.github.elytra.probe.api.impl;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.google.common.collect.ImmutableList;

import io.github.elytra.probe.api.IProbeData;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;

public class ProbeData implements IProbeData {
	private ITextComponent label = null;
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
		this.label = new TextComponentString(label);
	}
	
	/**
	 * Creates a data line with text information
	 * @param label the information to be displayed on this line
	 */
	public ProbeData(ITextComponent label) {
		this.label = label;
	}
	
	/**
	 * Adds an label to this ProbeData
	 * @param label the text to add to this ProbeData
	 * @return this ProbeData
	 */
	public ProbeData withLabel(String label) {
		this.label = new TextComponentString(label);
		return this;
	}
	
	/**
	 * Adds an label to this ProbeData
	 * @param label the text to add to this ProbeData
	 * @return this ProbeData
	 */
	public ProbeData withLabel(ITextComponent label) {
		//TODO: Consider making multiple withLabel calls concatenate them. That might just wind up being confusing and irreversible.
		//if (this.label==null) {
			this.label = label;
		//} else {
		//	this.label = this.label.appendSibling(label);
		//}
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
		return label!=null;
	}

	@Override
	public boolean hasBar() {
		return barCur!=-1 && barMax!=-1;
	}

	@Override
	@Nonnull
	public ITextComponent getLabel() {
		return label!=null ? label : new TextComponentString("");
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + barCur;
		result = prime * result + barMax;
		result = prime * result + barMin;
		result = prime * result + ((barUnit == null) ? 0 : barUnit.hashCode());
		result = prime * result + ((inventory == null) ? 0 : stackListHashCode(inventory));
		try {
			result = prime * result + ((label == null) ? 0 : label.hashCode());
		} catch (NullPointerException e) {
			// Some TextComponent implementations have broken hashCode methods
			// Ignore Mojang's quality code
		}
		return result;
	}

	private static int stackListHashCode(List<ItemStack> li) {
		int result = 1;
		for (ItemStack is : li) {
			result = 31 * result + (is == null ? 0 : stackHashCode(is));
		}
		return result;
	}

	private static int stackHashCode(ItemStack is) {
		if (is.isEmpty()) return 0;
		final int prime = 31;
		int result = 1;
		result = prime * result + is.getItem().hashCode();
		result = prime * result + is.getCount();
		result = prime * result + is.getMetadata();
		result = prime * result + (is.hasTagCompound() ? is.getTagCompound().hashCode() : 0);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		ProbeData other = (ProbeData) obj;
		if (barCur != other.barCur) {
			return false;
		}
		if (barMax != other.barMax) {
			return false;
		}
		if (barMin != other.barMin) {
			return false;
		}
		if (barUnit == null) {
			if (other.barUnit != null) {
				return false;
			}
		} else if (!barUnit.equals(other.barUnit)) {
			return false;
		}
		if (inventory == null) {
			if (other.inventory != null) {
				return false;
			}
		} else if (!stackListsEqual(inventory, other.inventory)) {
			return false;
		}
		if (label == null) {
			if (other.label != null) {
				return false;
			}
		} else if (!label.equals(other.label)) {
			return false;
		}
		return true;
	}

	private static boolean stackListsEqual(List<ItemStack> a, List<ItemStack> b) {
		if (a.size() != b.size()) return false;
		for (int i = 0; i < a.size(); i++) {
			ItemStack isa = a.get(i);
			ItemStack isb = b.get(i);
			if (!ItemStack.areItemStacksEqual(isa, isb)) {
				return false;
			}
		}
		return true;
	}
	
	

}
