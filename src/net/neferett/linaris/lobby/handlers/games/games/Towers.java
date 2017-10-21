package net.neferett.linaris.lobby.handlers.games.games;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import net.neferett.linaris.BukkitAPI;
import net.neferett.linaris.api.Games;
import net.neferett.linaris.lobby.handlers.games.Game;
import net.neferett.linaris.lobby.handlers.games.Map;
import net.neferett.linaris.lobby.handlers.games.games.TowersKit.Kit1;
import net.neferett.linaris.lobby.handlers.games.games.TowersKit.Kit2;
import net.neferett.linaris.lobby.handlers.games.games.TowersKit.Kit3;
import net.neferett.linaris.lobby.handlers.games.games.TowersKit.Kit4;
import net.neferett.linaris.lobby.handlers.games.games.TowersKit.Kit5;
import net.neferett.linaris.lobby.handlers.games.games.TowersKit.Kit6;
import net.neferett.linaris.lobby.handlers.games.games.TowersKit.Kit7;
import net.neferett.linaris.lobby.handlers.games.games.TowersKit.Kit8;
import net.neferett.linaris.lobby.handlers.games.games.TowersKit.Kit9;



public class Towers extends Game {

	public Towers() {
		super("Towers", Games.TOWERS, new ItemStack(Material.NETHER_FENCE), getLore());
		registerMap(new Map("2vs2"));
		registerMap(new Map("4vs4"));
		setShopName("Kits Towers");
		addItem(new Kit1());
		addItem(new Kit2());
		addItem(new Kit3());
		addItem(new Kit4());
		addItem(new Kit5());
		addItem(new Kit6());
		addItem(new Kit7());
		addItem(new Kit8());
		addItem(new Kit9());


	}
	
	public static List<String> getLore() {
		List<String> lorePvpSwap = new ArrayList<String>();
		lorePvpSwap.add("§b§nRègle§r§f:");
		lorePvpSwap.add("§7Dominez le milieu de la map");
		lorePvpSwap.add("§7et marquer des points dans les");
		lorePvpSwap.add("§7piscines de teams adverses !");
		lorePvpSwap.add("§e§o10 points pour gagner");
		lorePvpSwap.add("");
		lorePvpSwap.add("§6Nombre de joueurs§f: §b" + BukkitAPI.get().getProxyDataManager().getPlayerInGame(Games.TOWERS));
		return lorePvpSwap;
	}

}
