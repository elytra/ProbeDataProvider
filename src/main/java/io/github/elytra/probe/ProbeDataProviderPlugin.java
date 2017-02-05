package io.github.elytra.probe;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.github.elytra.probe.api.IProbeDataProvider;
import io.github.elytra.probe.api.impl.ProbeDataProviderDefault;
import io.github.elytra.probe.api.impl.ProbeDataSerializer;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
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
}
