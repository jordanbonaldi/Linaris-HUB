package net.neferett.linaris.lobby.games.games;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import net.neferett.linaris.BukkitAPI;
import net.neferett.linaris.api.Games;
import net.neferett.linaris.lobby.games.Game;
import net.neferett.linaris.lobby.games.Map;
import net.neferett.linaris.lobby.games.games.fallenkindoms.KitAlchimiste;
import net.neferett.linaris.lobby.games.games.fallenkindoms.KitEclaireur;
import net.neferett.linaris.lobby.games.games.fallenkindoms.KitEnchanteur;
import net.neferett.linaris.lobby.games.games.fallenkindoms.KitFarmer;
import net.neferett.linaris.lobby.games.games.fallenkindoms.KitGuerrier;
import net.neferett.linaris.lobby.games.games.fallenkindoms.KitMineur;
import net.neferett.linaris.lobby.games.games.fallenkindoms.KitRanger;

public class FallenKingdoms extends Game {

	public FallenKingdoms() {
		super("FallenKingoms", Games.FALLENKINGDOMS, new ItemStack(Material.TNT), getLore());
		registerMap(new Map("Cactus"));
		setShopName("Kits FallenKingoms");
		addItem(new KitGuerrier());
		addItem(new KitMineur());
		addItem(new KitFarmer());
		addItem(new KitEclaireur());
		addItem(new KitRanger());
		addItem(new KitAlchimiste());
		addItem(new KitEnchanteur());
	}
	
	public static List<String> getLore() {
		List<String> loreFallenKingdom = new ArrayList<String>();
		loreFallenKingdom.add("§b§nRègle§r§f:");
		loreFallenKingdom.add("§7Plusieurs équipes s'affrontent");
		loreFallenKingdom.add("§7sur des maps immenses, vous devrez");
		loreFallenKingdom.add("§7pénétrer les bases ennemies et tuer");
		loreFallenKingdom.add("§7leurs withers pour gagner la partie");
		loreFallenKingdom.add("");
		loreFallenKingdom.add("§6Nombre de joueurs§f: §b" + BukkitAPI.get().getProxyDataManager().getPlayerInGame(Games.FALLENKINGDOMS));
		return loreFallenKingdom;
	}

}
