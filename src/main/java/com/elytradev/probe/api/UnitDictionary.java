package com.elytradev.probe.api;

import java.util.HashMap;

import javax.annotation.Nullable;

import com.elytradev.probe.api.impl.Unit;

import net.minecraftforge.fluids.Fluid;

/**
 * A central place for unit registrations. It's highly reccommended to register your unit on both the client *and* the
 * server.
 */
public class UnitDictionary {
	
	private static UnitDictionary INSTANCE;
	
	//Fluids
	public static final Unit BUCKETS_ANY       = new Unit("buckets",          "B", 0x283593); //800 indigo
	//public static final Unit BUCKETS_WATER     = new Unit("buckets_water",    "B", 0x1976D2); //700 blue
	//public static final Unit BUCKETS_LAVA      = new Unit("buckets_lava",     "B", 0xFF8F00); //800 amber
	//public static final Unit BUCKETS_REDSTONE  = new Unit("buckets_redstone", "B", 0xE53935); //600 red
	//public static final Unit BUCKETS_OIL       = new Unit("buckets_oil",      "B", 0x212121); //900 grey
	//public static final Unit BUCKETS_STEAM     = new Unit("buckets_steam",    "B", 0xB0BEC5); //200 blue grey
	
	//Data
	public static final Unit BYTES             = new Unit("bytes",            "B",   0x76FF03); //A400 light green
	
	//Energy
	public static final Unit FORGE_ENERGY      = new Unit("forge_energy",     "FU",  0xD50000); //A700 red
	public static final Unit FU_PER_TICK       = new Unit("fu_per_tick",      "FU/t",0xD50000); //Also A700 red
	public static final Unit DANKS             = new Unit("danks",            "Dk",  0x512DA8); //700 deep purple (from Tesla capabilities)
	
	//Temperature for ITemperature
	public static final Unit KELVIN            = new Unit("kelvin",           "°K",  0xFF0000); //Programmer Red
	
	public static final Unit PERCENT           = new Unit("percent",          "%");
	
	
	public static final UnitDictionary getInstance() {
		if (INSTANCE==null) INSTANCE = new UnitDictionary();
		return INSTANCE;
	}
	
	
	
	private HashMap<String, IUnit> registry = new HashMap<>();
	private HashMap<IUnit, Fluid> fluidUnits = new HashMap<>();
	
	private UnitDictionary() {
		register(BUCKETS_ANY);
		//register(BUCKETS_WATER);
		//register(BUCKETS_LAVA);
		//register(BUCKETS_REDSTONE);
		//register(BUCKETS_OIL);
		//register(BUCKETS_STEAM);
		
		register(BYTES);
		
		register(FORGE_ENERGY);
		register(DANKS);
		
		register(KELVIN);
	}
	
	/**
	 * Register a unit with the dictionary.
	 * @param unit the unit to register.
	 */
	public void register(IUnit unit) {
		registry.put(unit.getFullName(), unit);
	}
	
	/**
	 * Registers this unit as a fluid
	 * @param unit the unit to register
	 * @param fluid the fluid the unit is associated with
	 */
	public void register(IUnit unit, Fluid fluid) {
		registry.put(unit.getFullName(), unit);
		fluidUnits.put(unit, fluid);
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
	
	/**
	 * Returns true if the specified unit corresponds to buckets of a forge Fluid
	 * @param unit the Unit to test
	 * @return true if there is a known association between this Unit and a Fluid
	 */
	public boolean isFluid(IUnit unit) {
		return fluidUnits.containsKey(unit);
	}
	
	/**
	 * Finds the Fluid that the specified Unit is associated with
	 * @param unit the Unit to find a Fluid for
	 * @return the Fluid this Unit is associated with, or null if it isn't known to be a Fluid
	 */
	@Nullable
	public Fluid getFluid(IUnit unit) {
		return fluidUnits.get(unit);
	}
}
