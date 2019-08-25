package net.neferett.linaris.handlers.games.games.TowersKit;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import net.neferett.linaris.handlers.shop.LevelInfo;
import net.neferett.linaris.handlers.shop.ShopItem;

public class Kit3 extends ShopItem {

	public Kit3() {
		super("kit3","Kit 3","",new ItemStack(Material.STONE_SWORD),true);
		LevelInfo level0 = new LevelInfo(Arrays.asList("§7Obtenez en d§but de partie :", "", "§7- Casque en Mailles", "§7- Plastron en Mailles",
				"§7- Pantalon en Mailles", "§7- Bottes en Mailles","§7- Ep§e en pierre T1","§7- Potion de soin (Buvable)" ),0);

		LevelInfo level1 = new LevelInfo(Arrays.asList("§7Obtenez en d§but de partie :", "", "§7- Casque en Mailles", "§7- Plastron en Mailles", "§7- Pantalon en Mailles", "§7- Bottes en Mailles","§7- Ep§e en pierre T1","§7- Potion de soin (Buvable)" ),650);
			
		
	
		addLevelInfo(level0);
		addLevelInfo(level1);

	}



}
