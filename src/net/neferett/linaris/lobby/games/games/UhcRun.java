package net.neferett.linaris.lobby.games.games;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import net.neferett.linaris.BukkitAPI;
import net.neferett.linaris.api.Games;
import net.neferett.linaris.lobby.games.Game;
import net.neferett.linaris.lobby.games.Map;

public class UhcRun extends Game {

	public UhcRun() {
		super("UHCRun", Games.UHCRUN, new ItemStack(Material.GOLDEN_APPLE), getLore());
		registerMap(new Map("2v2 Classic"));
		registerMap(new Map("Solo Classic"));
	}
	
	public static List<String> getLore() {
		List<String> loreUhcRun = new ArrayList<String>();
		loreUhcRun.add("§b§nRègle§r§f:");
		loreUhcRun.add("§7Affrontez les autres joueurs");
		loreUhcRun.add("§7sur un mode de jeux hardcore");
		loreUhcRun.add("§eSeul §7ou en §aéquipe§7.");
		loreUhcRun.add("§cAttention§f:");
		loreUhcRun.add("§e§oRégénération naturelle désactivé !");
		loreUhcRun.add("");
		loreUhcRun.add("§6Nombre de joueurs§f: §b" + BukkitAPI.get().getProxyDataManager().getPlayerInGame(Games.UHCRUN));
		return loreUhcRun;
	}

}
