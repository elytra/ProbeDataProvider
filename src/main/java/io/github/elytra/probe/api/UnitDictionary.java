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
	public static final Unit BUCKETS_ANY       = new Unit("buckets",          "B", 0x283593); //800 indigo
	public static final Unit BUCKETS_WATER     = new Unit("buckets_water",    "B", 0x1976D2); //700 blue
	public static final Unit BUCKETS_LAVA      = new Unit("buckets_lava",     "B", 0xFF8F00); //800 amber
	public static final Unit BUCKETS_REDSTONE  = new Unit("buckets_redstone", "B", 0xE53935); //600 red
	public static final Unit BUCKETS_OIL       = new Unit("buckets_oil",      "B", 0x212121); //900 grey
	public static final Unit BUCKETS_STEAM     = new Unit("buckets_steam",    "B", 0xB0BEC5); //200 blue grey
	
	//Data
	public static final Unit BYTES        = new Unit("bytes",         "B", 0x76FF03); //A400 light green
	
	//Energy
	public static final Unit FORGE_ENERGY = new Unit("forge_energy", "FU", 0xD50000); //A700 red
	public static final Unit DANKS        = new Unit("danks",        "Dk", 0x512DA8); //700 deep purple (from Tesla capabilities)
	
	//Temperature for ITemperature
	public static final Unit KELVIN       = new Unit("kelvin",       "°K", 0xF70000);
	
	
	public static final UnitDictionary getInstance() {
		return INSTANCE;
	}
	
	
	
	private HashMap<String, IUnit> registry = new HashMap<>();
	
	private UnitDictionary() {
		register(BUCKETS_ANY);
		register(BUCKETS_WATER);
		register(BUCKETS_LAVA);
		register(BUCKETS_REDSTONE);
		register(BUCKETS_OIL);
		register(BUCKETS_STEAM);
		
		register(BYTES);
		
		register(FORGE_ENERGY);
		register(DANKS);
		
		register(KELVIN);
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
