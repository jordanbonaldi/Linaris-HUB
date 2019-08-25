package net.neferett.linaris.handlers.games.games.TowersKit;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import net.neferett.linaris.handlers.shop.LevelInfo;
import net.neferett.linaris.handlers.shop.ShopItem;

public class Kit4 extends ShopItem {

	public Kit4() {
		super("kit4","Kit 4","",new ItemStack(Material.IRON_CHESTPLATE),true);
		
		LevelInfo level0 = new LevelInfo(Arrays.asList("§7Obtenez en d§but de partie :", "", "§7- Casque en Mailles",
				"§7- Plastron en Fer", "§7- Pantalon en Mailles", "§7- Bottes en Mailles","§7- Ep§e en pierre T1","§7- Potion de soin (Buvable)" ), 0);
		
		LevelInfo level1 = new LevelInfo(Arrays.asList("§7Obtenez en d§but de partie :", "", "§7- Casque en Mailles",
				"§7- Plastron en Fer", "§7- Pantalon en Mailles", "§7- Bottes en Mailles","§7- Ep§e en pierre T1","§7- Potion de soin (Buvable)" ), 1000);
		addLevelInfo(level0);
		addLevelInfo(level1);

	}


}
