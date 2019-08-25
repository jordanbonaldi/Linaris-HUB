package net.neferett.linaris.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class ConfigDatas {

	public static ConfigDatas instance;

	public static ConfigDatas getInstance() {
		return instance == null ? instance = new ConfigDatas() : instance;
	}

	Location spawn = new Location(Bukkit.getWorld("world"), 0.470, 103, -3.5, (float)-1, (float) 2.4);

	public Location getSpawn() {
		return this.spawn;
	}

}
