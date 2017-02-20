package com.elytradev.probe;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.elytradev.probe.api.IProbeDataProvider;
import com.elytradev.probe.api.UnitDictionary;
import com.elytradev.probe.api.impl.ProbeDataProviderDefault;
import com.elytradev.probe.api.impl.ProbeDataSerializer;
import com.elytradev.probe.api.impl.Unit;

import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid=ProbeDataProviderPlugin.MODID, name="ProbeDataProvider", version="@VERSION@")
public class ProbeDataProviderPlugin {
	public static final String MODID = "probedataprovider";
	public static Logger LOG;
	public static Configuration CONFIG;
	
	@Instance(MODID)
	public static ProbeDataProviderPlugin INSTANCE;
	
	@EventHandler
	public void onPreInit(FMLPreInitializationEvent e) {
		LOG = LogManager.getLogger(MODID);
		File config = e.getSuggestedConfigurationFile();
		CONFIG = new Configuration(config);
		//TODO: Uncomment if we ever have something to config.
		//CONFIG.save();
		
		CapabilityManager.INSTANCE.register(IProbeDataProvider.class, new ProbeDataSerializer(), ProbeDataProviderDefault::new);
	}
	
	@EventHandler
	public void onPostInit(FMLPostInitializationEvent e) {
		UnitDictionary dict = UnitDictionary.getInstance();
		for(Fluid fluid : FluidRegistry.getRegisteredFluids().values()) {
			Unit fluidUnit = new Unit("buckets_"+fluid.getName(),"B", fluid.getColor());
			dict.register(fluidUnit, fluid);
		}
	}
}
