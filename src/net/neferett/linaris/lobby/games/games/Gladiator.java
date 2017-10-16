package net.neferett.linaris.lobby.games.games;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import net.neferett.linaris.BukkitAPI;
import net.neferett.linaris.api.Games;
import net.neferett.linaris.lobby.games.Game;
import net.neferett.linaris.lobby.games.Map;



public class Gladiator extends Game {

	public Gladiator() {
		super("Gladiator", Games.GLADIATOR, new ItemStack(Material.DIAMOND_SWORD), getLore());
		registerMap(new Map("Desert"));

	}
	
	public static List<String> getLore() {
		List<String> lorePvpSwap = new ArrayList<String>();
		lorePvpSwap.add("§b§nRègle§r§f:");
		lorePvpSwap.add("§7Battez vous sur 4 rounds");
		lorePvpSwap.add("§7en duel ( 1vs1 )");
		lorePvpSwap.add("");
		lorePvpSwap.add("§6Nombre de joueurs§f: §b" + BukkitAPI.get().getProxyDataManager().getPlayerInGame(Games.GLADIATOR));
		return lorePvpSwap;
	}

}
