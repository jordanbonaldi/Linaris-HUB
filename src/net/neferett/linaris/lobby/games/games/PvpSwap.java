package net.neferett.linaris.lobby.games.games;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import net.neferett.linaris.BukkitAPI;
import net.neferett.linaris.api.Games;
import net.neferett.linaris.lobby.games.Game;
import net.neferett.linaris.lobby.games.Map;
import net.neferett.linaris.lobby.games.games.pvpswapkits.KitAlchimiste;
import net.neferett.linaris.lobby.games.games.pvpswapkits.KitBourrin;
import net.neferett.linaris.lobby.games.games.pvpswapkits.KitEnchanteur;
import net.neferett.linaris.lobby.games.games.pvpswapkits.KitMineur;
import net.neferett.linaris.lobby.games.games.pvpswapkits.KitRoublard;

public class PvpSwap extends Game {

	public PvpSwap() {
		super("PvpSwap", Games.PVPSWAP, new ItemStack(Material.ENDER_PEARL), getLore());
		registerMap(new Map("Citadelle"));
		registerMap(new Map("Duel"));
		setShopName("Kits PvpSwap");
		addItem(new KitBourrin());
		addItem(new KitEnchanteur());
		addItem(new KitMineur());
		addItem(new KitRoublard());
		addItem(new KitAlchimiste());
	}
	
	public static List<String> getLore() {
		List<String> lorePvpSwap = new ArrayList<String>();
		lorePvpSwap.add("�b�nR�gle�r�f:");
		lorePvpSwap.add("�7Vous �tes t�l�port� al�atoirement");
		lorePvpSwap.add("�7dans des modules aux ressources et");
		lorePvpSwap.add("�7configurations diff�rentes, mais vous");
		lorePvpSwap.add("�7n'y serez pas forc�ment seul... le but ?");
		lorePvpSwap.add("�7�liminier les autres et rester en vie !");
		lorePvpSwap.add("");
		lorePvpSwap.add("�6Nombre de joueurs�f: �b" + BukkitAPI.get().getProxyDataManager().getPlayerInGame(Games.PVPSWAP));
		return lorePvpSwap;
	}

}
