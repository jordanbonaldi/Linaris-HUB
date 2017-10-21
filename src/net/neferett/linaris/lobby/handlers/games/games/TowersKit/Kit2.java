package net.neferett.linaris.lobby.handlers.games.games.TowersKit;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import net.neferett.linaris.lobby.shop.LevelInfo;
import net.neferett.linaris.lobby.shop.ShopItem;

public class Kit2 extends ShopItem {

	public Kit2() {
		super("kit2","Kit 2","",new ItemStack(Material.GOLD_SWORD),true);
		LevelInfo level0 = new LevelInfo(Arrays.asList("§7Obtenez en début de partie :", "", "§7- Casque en Or", "§7- Plastron en Mailles", "§7- Pantalon en Mailles", "§7- Bottes en Or","§7- Epée en Or T1","§7- Potion de soin (Buvable)" ), 0);

		LevelInfo level1 = new LevelInfo(Arrays.asList("§7Obtenez en début de partie :", "", "§7- Casque en Or", "§7- Plastron en Mailles", "§7- Pantalon en Mailles", "§7- Bottes en Or","§7- Epée en Or T1","§7- Potion de soin (Buvable)" ), 400);
			
		
	
		addLevelInfo(level0);
		addLevelInfo(level1);

	}

}
