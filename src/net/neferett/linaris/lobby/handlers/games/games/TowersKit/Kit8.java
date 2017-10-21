package net.neferett.linaris.lobby.handlers.games.games.TowersKit;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import net.neferett.linaris.lobby.shop.LevelInfo;
import net.neferett.linaris.lobby.shop.ShopItem;

public class Kit8 extends ShopItem {

	public Kit8() {
		super("kit8","Kit 8","",new ItemStack(Material.DIAMOND_SWORD),true);
		
		LevelInfo level0 = new LevelInfo(Arrays.asList("§7Obtenez en début de partie :", "", "§7- Casque en Fer", "§7- Plastron en Diamants", "§7- Pantalon en Diamants", "§7- Bottes en Fer","§7- Epée en Diamants","§7- 2 Pommes dorées","§7- Potion de soin II (Jetable)" ),0);
		LevelInfo level1 = new LevelInfo(Arrays.asList("§7Obtenez en début de partie :", "", "§7- Casque en Fer", "§7- Plastron en Diamants", "§7- Pantalon en Diamants", "§7- Bottes en Fer","§7- Epée en Diamants","§7- 2 Pommes dorées","§7- Potion de soin II (Jetable)" ),10000);

		addLevelInfo(level0);
		addLevelInfo(level1);

	}



}
