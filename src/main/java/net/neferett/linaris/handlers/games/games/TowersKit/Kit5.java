package net.neferett.linaris.handlers.games.games.TowersKit;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import net.neferett.linaris.handlers.shop.LevelInfo;
import net.neferett.linaris.handlers.shop.ShopItem;


public class Kit5 extends ShopItem {

	public Kit5() {
		super("kit5","Kit 5","",new ItemStack(Material.IRON_SWORD),true);
		
		LevelInfo level0 = new LevelInfo(Arrays.asList("§7Obtenez en d§but de partie :",
				"", "§7- Casque en Mailles", "§7- Plastron en Fer", "§7- Pantalon en Fer", "§7- Bottes en Fer","§7- Ep§e en Fer","§7- Potion de soin (Jetable)" ),0);
		
		LevelInfo level1 = new LevelInfo(Arrays.asList("§7Obtenez en d§but de partie :",
				"", "§7- Casque en Mailles", "§7- Plastron en Fer", "§7- Pantalon en Fer", "§7- Bottes en Fer","§7- Ep§e en Fer","§7- Potion de soin (Jetable)" ),2000);
	
		addLevelInfo(level0);
		addLevelInfo(level1);

	}


}
