package net.neferett.linaris.lobby.handlers.games.games.TowersKit;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import net.neferett.linaris.lobby.shop.LevelInfo;
import net.neferett.linaris.lobby.shop.ShopItem;

public class Kit9 extends ShopItem {

	public Kit9() {
		super("kit9","Kit 9","",new ItemStack(Material.GOLDEN_APPLE),true);
		
		LevelInfo level0 = new LevelInfo(Arrays.asList("§7Obtenez en début de partie :", "", "§7- Casque en Diamants", "§7- Plastron en Diamants", "§7- Pantalon en Diamants", "§7- Bottes en Diamants","§7- Epée en Diamants T1","§7- 2 Pommes dorées","§7- 2 Potion de soin II (Jetable)" ),0);

		LevelInfo level1 = new LevelInfo(Arrays.asList("§7Obtenez en début de partie :", "", "§7- Casque en Diamants", "§7- Plastron en Diamants", "§7- Pantalon en Diamants", "§7- Bottes en Diamants","§7- Epée en Diamants T1","§7- 2 Pommes dorées","§7- 2 Potion de soin II (Jetable)" ),15000);

		addLevelInfo(level0);
		addLevelInfo(level1);

	}

}
