package net.neferett.linaris.lobby.games.games.TowersKit;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import net.neferett.linaris.lobby.shop.LevelInfo;
import net.neferett.linaris.lobby.shop.ShopItem;

public class Kit1 extends ShopItem {

	public Kit1() {
		super("kit1","Kit 1","",new ItemStack(Material.WOOD_SWORD),true);
		
		LevelInfo level0 = new LevelInfo(Arrays.asList("§7Obtenez en début de partie :", "", "§7- Casque en Cuir", "§7- Plastron en Cuir P1", "§7- Pantalon en Cuir P1", "§7- Bottes en Cuir","§7- Epée en bois","§7- Potion de soin (Buvable)"),0 );
		LevelInfo level1 = new LevelInfo(Arrays.asList("§7Obtenez en début de partie :", "", "§7- Casque en Cuir", "§7- Plastron en Cuir P1", "§7- Pantalon en Cuir P1", "§7- Bottes en Cuir","§7- Epée en bois","§7- Potion de soin (Buvable)" ),250 );

	
		addLevelInfo(level0);
		addLevelInfo(level1);

		
	}

}
