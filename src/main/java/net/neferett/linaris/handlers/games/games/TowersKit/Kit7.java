package net.neferett.linaris.handlers.games.games.TowersKit;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import net.neferett.linaris.handlers.shop.LevelInfo;
import net.neferett.linaris.handlers.shop.ShopItem;

public class Kit7 extends ShopItem {

	public Kit7() {
		super("kit7","Kit 7","",new ItemStack(Material.DIAMOND_CHESTPLATE),true);
		
		LevelInfo level0 = new LevelInfo(Arrays.asList("§7Obtenez en d§but de partie :", "", "§7- Casque en Fer", "§7- Plastron en Diamants", "§7- Pantalon en Fer", "§7- Bottes en Fer","§7- Ep§e en Fer T2","§7- 2 Pommes dor§es","§7- Potion de soin (Jetable)" ),0);

		LevelInfo level1 = new LevelInfo(Arrays.asList("§7Obtenez en d§but de partie :", "", "§7- Casque en Fer", "§7- Plastron en Diamants", "§7- Pantalon en Fer", "§7- Bottes en Fer","§7- Ep§e en Fer T2","§7- 2 Pommes dor§es","§7- Potion de soin (Jetable)" ),8000);

	
		addLevelInfo(level0);
		addLevelInfo(level1);

	}


}
