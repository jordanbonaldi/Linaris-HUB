package net.neferett.linaris.lobby.handlers.games.games;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import net.neferett.linaris.BukkitAPI;
import net.neferett.linaris.api.Games;
import net.neferett.linaris.lobby.handlers.games.Game;
import net.neferett.linaris.lobby.handlers.games.Map;



public class BuildBattle extends Game {

	public BuildBattle() {
		super("BuildBattle", Games.BuildBattle, new ItemStack(Material.BRICK), getLore());
		registerMap(new Map("Classique"));

	}
	
	public static List<String> getLore() {
		List<String> lorePvpSwap = new ArrayList<String>();
		lorePvpSwap.add("§b§nRègle§r§f:");
		lorePvpSwap.add("§7Construisez la meilleure représentation");
		lorePvpSwap.add("§7du theme selectionné pour gagner");
		lorePvpSwap.add("");
		lorePvpSwap.add("§6Nombre de joueurs§f: §b" + BukkitAPI.get().getProxyDataManager().getPlayerInGame(Games.BuildBattle));
		return lorePvpSwap;
	}

}
