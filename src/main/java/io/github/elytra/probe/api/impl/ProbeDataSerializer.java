package io.github.elytra.probe.api.impl;

import io.github.elytra.probe.api.IProbeDataProvider;
import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class ProbeDataSerializer implements IStorage<IProbeDataProvider> {

	@Override
	public void readNBT(Capability<IProbeDataProvider> capability, IProbeDataProvider probeDataProvider, EnumFacing side, NBTBase nbt) {
	}

	@Override
	public NBTBase writeNBT(Capability<IProbeDataProvider> arg0, IProbeDataProvider arg1, EnumFacing arg2) {
		return null; //Maybe dis works? Otherwise use below:
		//NBTTagCompound tag = new NBTTagCompound();
		//return tag;
	}

}
