package net.neferett.linaris.lobby.games.games;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import net.neferett.linaris.BukkitAPI;
import net.neferett.linaris.api.Games;
import net.neferett.linaris.lobby.games.Game;
import net.neferett.linaris.lobby.games.Map;
import net.neferett.linaris.lobby.games.games.rushs.KitAM;
import net.neferett.linaris.lobby.games.games.rushs.KitArcher;
import net.neferett.linaris.lobby.games.games.rushs.Kitprotectionlourde;


public class Rushs extends Game {

	public Rushs() {
		super("Rushs", Games.RUSH, new ItemStack(Material.BED), getLore());
		registerMap(new Map("2vs2"));
		registerMap(new Map("4vs4"));
		setShopName("Kits Rushs");
		addItem(new Kitprotectionlourde());
		addItem(new KitAM());
		addItem(new KitArcher());

	}
	
	public static List<String> getLore() {
		List<String> lorePvpSwap = new ArrayList<String>();
		lorePvpSwap.add("§b§nRègle§r§f:");
		lorePvpSwap.add("§7Construisez un pont, collectez");
		lorePvpSwap.add("§7des ressources et détruisez le");
		lorePvpSwap.add("§7lit de vos adversaires pour les");
		lorePvpSwap.add("§7empecher de revenir à la vie.");
		lorePvpSwap.add("");
		lorePvpSwap.add("§6Nombre de joueurs§f: §b" + BukkitAPI.get().getProxyDataManager().getPlayerInGame(Games.RUSH));
		return lorePvpSwap;
	}

}
