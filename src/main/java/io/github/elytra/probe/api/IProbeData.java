package io.github.elytra.probe.api;

import net.minecraft.util.ResourceLocation;

public interface IProbeData {
	public boolean hasIcon();
	public boolean hasLabel();
	public boolean hasBar();
	
	public ResourceLocation getIcon();
	public String getLabel();
	public int getBarMinimum();
	public int getBarCurrent();
	public int getBarMaximum();
	public String getBarUnit();
}
