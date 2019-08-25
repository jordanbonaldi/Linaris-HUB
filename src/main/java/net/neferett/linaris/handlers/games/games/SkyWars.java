package net.neferett.linaris.handlers.games.games;

import java.util.ArrayList;
import java.util.List;

import net.neferett.linaris.handlers.games.games.SkyWarsKit.KitAM;
import net.neferett.linaris.handlers.games.games.SkyWarsKit.KitArcher;
import net.neferett.linaris.handlers.games.games.SkyWarsKit.Kitprotectionlourde;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import net.neferett.linaris.BukkitAPI;
import net.neferett.linaris.api.Games;
import net.neferett.linaris.handlers.games.Game;
import net.neferett.linaris.handlers.games.Map;

public class SkyWars extends Game {

	public SkyWars() {
		super("SkyWars", Games.SKYWARS, new ItemStack(Material.EYE_OF_ENDER), getLore());
		registerMap(new Map("Aleatoire"));
		setShopName("Kits SkyWars");
		addItem(new KitAM());
		addItem(new Kitprotectionlourde());
		addItem(new KitArcher());


	}
	
	public static List<String> getLore() {
		List<String> loreSkyWars = new ArrayList<String>();
		loreSkyWars.add("§b§nR§gle§r§f:");
		loreSkyWars.add("§7Ramassez le contenu des coffres et");
		loreSkyWars.add("§7sautez d'§le en §le afin de tuer");
		loreSkyWars.add("§7vos adversaires sur le champ de bataille");
		loreSkyWars.add("");
		loreSkyWars.add("§6Nombre de joueurs§f: §b" + BukkitAPI.get().getProxyDataManager().getPlayerInGame(Games.SKYWARS));
		return loreSkyWars;
	}

}
