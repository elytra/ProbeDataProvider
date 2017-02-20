package io.github.elytra.probe.api;

import java.util.HashMap;

import javax.annotation.Nullable;

import io.github.elytra.probe.api.impl.Unit;

/**
 * A central place for unit registrations. It's highly reccommended to register your unit on both the client *and* the
 * server.
 */
public class UnitDictionary {
	
	private static final UnitDictionary INSTANCE = new UnitDictionary();
	
	//Fluids
	public static final Unit MILLIBUCKETS = new Unit("millibuckets", "mB", 0x283593); //800 indigo
	public static final Unit MB_WATER     = new Unit("mb_water",     "mB", 0x1976D2); //700 blue
	public static final Unit MB_LAVA      = new Unit("mb_lava",      "mB", 0xFF8F00); //800 amber
	public static final Unit MB_REDSTONE  = new Unit("mb_redstone",  "mB", 0xE53935); //600 red
	public static final Unit MB_OIL       = new Unit("mb_oil",       "mB", 0x212121); //900 grey
	public static final Unit MB_STEAM     = new Unit("mb_steam",     "mB", 0xB0BEC5); //200 blue grey
	
	//Data
	public static final Unit BYTES        = new Unit("bytes",         "B", 0x76FF03); //A400 light green
	
	//Energy
	public static final Unit FORGE_ENERGY = new Unit("forge_energy", "FU", 0xD50000); //A700 red
	
	
	
	public static final UnitDictionary getInstance() {
		return INSTANCE;
	}
	
	
	
	private HashMap<String, IUnit> registry = new HashMap<>();
	
	private UnitDictionary() {
		register(MILLIBUCKETS);
		register(MB_WATER);
		register(MB_LAVA);
		register(MB_REDSTONE);
		register(MB_OIL);
		register(MB_STEAM);
		
		register(BYTES);
		register(FORGE_ENERGY);
	}
	
	/**
	 * Register a unit with the dictionary.
	 * @param unit The unit to register.
	 */
	public void register(IUnit unit) {
		registry.put(unit.getFullName(), unit);
	}
	
	/**
	 * Finds the IUnit with the specified proper name
	 * @param fullName the name the IUnit was registered under
	 * @return the IUnit itself, or null if none was registered under that name.
	 */
	@Nullable
	public IUnit getUnit(String fullName) {
		return registry.get(fullName);
	}
}
