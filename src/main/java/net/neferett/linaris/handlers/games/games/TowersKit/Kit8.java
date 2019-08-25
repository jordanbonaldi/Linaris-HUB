package net.neferett.linaris.handlers.games.games.TowersKit;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import net.neferett.linaris.handlers.shop.LevelInfo;
import net.neferett.linaris.handlers.shop.ShopItem;

public class Kit8 extends ShopItem {

	public Kit8() {
		super("kit8","Kit 8","",new ItemStack(Material.DIAMOND_SWORD),true);
		
		LevelInfo level0 = new LevelInfo(Arrays.asList("§7Obtenez en d§but de partie :", "", "§7- Casque en Fer", "§7- Plastron en Diamants", "§7- Pantalon en Diamants", "§7- Bottes en Fer","§7- Ep§e en Diamants","§7- 2 Pommes dor§es","§7- Potion de soin II (Jetable)" ),0);
		LevelInfo level1 = new LevelInfo(Arrays.asList("§7Obtenez en d§but de partie :", "", "§7- Casque en Fer", "§7- Plastron en Diamants", "§7- Pantalon en Diamants", "§7- Bottes en Fer","§7- Ep§e en Diamants","§7- 2 Pommes dor§es","§7- Potion de soin II (Jetable)" ),10000);

		addLevelInfo(level0);
		addLevelInfo(level1);

	}



}
