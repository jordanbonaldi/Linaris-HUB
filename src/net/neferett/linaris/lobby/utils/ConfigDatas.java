package net.neferett.linaris.lobby.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class ConfigDatas {

	public static ConfigDatas instance;

	public static ConfigDatas getInstance() {
		return instance == null ? instance = new ConfigDatas() : instance;
	}

	Location spawn = new Location(Bukkit.getWorld("world"), 31, 38, -16, -127, (float) -4.2);

	public Location getSpawn() {
		return this.spawn;
	}

}
