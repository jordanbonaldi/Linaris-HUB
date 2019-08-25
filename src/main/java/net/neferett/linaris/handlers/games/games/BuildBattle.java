package net.neferett.linaris.handlers.games.games;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import net.neferett.linaris.BukkitAPI;
import net.neferett.linaris.api.Games;
import net.neferett.linaris.handlers.games.Game;
import net.neferett.linaris.handlers.games.Map;



public class BuildBattle extends Game {

	public BuildBattle() {
		super("BuildBattle", Games.BuildBattle, new ItemStack(Material.BRICK), getLore());
		registerMap(new Map("Classique"));

	}
	
	public static List<String> getLore() {
		List<String> lorePvpSwap = new ArrayList<String>();
		lorePvpSwap.add("§b§nR§gle§r§f:");
		lorePvpSwap.add("§7Construisez la meilleure repr§sentation");
		lorePvpSwap.add("§7du theme selectionn§ pour gagner");
		lorePvpSwap.add("");
		lorePvpSwap.add("§6Nombre de joueurs§f: §b" + BukkitAPI.get().getProxyDataManager().getPlayerInGame(Games.BuildBattle));
		return lorePvpSwap;
	}

}
