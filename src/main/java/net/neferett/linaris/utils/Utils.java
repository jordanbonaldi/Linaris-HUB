package net.neferett.linaris.utils;

import net.neferett.linaris.utils.PlayerUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

public abstract class Utils
{
  public static void sendPlayerTo(Player player, String serverName)
  {
    PlayerUtils.goToServer(player.getName(), serverName);
  }

  public static Location toLocation(String string) {
    String[] splitted = string.split("_");
    World world = Bukkit.getWorld(splitted[0]);
    if ((world == null) || (splitted.length < 4)) return null;
    return new Location(world, Integer.parseInt(splitted[1]), Integer.parseInt(splitted[2]), Integer.parseInt(splitted[3]));
  }

  public static String toString(Location location) {
    World world = location.getWorld();
    return world.getName() + "_" + location.getBlockX() + "_" + location.getBlockY() + "_" + location.getBlockZ();
  }

  public static String colorize(Object[] texts) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < texts.length; i++) {
      sb.append(texts[i].toString());
    }
    return ChatColor.translateAlternateColorCodes('&', sb.toString());
  }
}