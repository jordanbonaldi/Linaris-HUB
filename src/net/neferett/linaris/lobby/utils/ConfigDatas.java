package net.neferett.linaris.lobby.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class ConfigDatas {

	public static ConfigDatas instance;

	public static ConfigDatas getInstance() {
		return instance == null ? instance = new ConfigDatas() : instance;
	}

	Location spawn = new Location(Bukkit.getWorld("world"), 0.476, 103, -3.617, (float) -0.4, (float) 0.5);

	public Location getSpawn() {
		return this.spawn;
	}

}
