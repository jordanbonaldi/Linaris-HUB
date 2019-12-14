package net.neferett.linaris.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class ConfigDatas {

	public static ConfigDatas instance;

	public static ConfigDatas getInstance() {
		return instance == null ? instance = new ConfigDatas() : instance;
	}

	Location spawn = new Location(Bukkit.getWorld("world"), 11.5, 106, -42.5, (float)-89, (float) 1);

	public Location getSpawn() {
		return this.spawn;
	}

}
