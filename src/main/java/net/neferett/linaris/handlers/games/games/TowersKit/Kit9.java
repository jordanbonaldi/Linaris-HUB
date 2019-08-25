package net.neferett.linaris.handlers.games.games.TowersKit;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import net.neferett.linaris.handlers.shop.LevelInfo;
import net.neferett.linaris.handlers.shop.ShopItem;

public class Kit9 extends ShopItem {

	public Kit9() {
		super("kit9","Kit 9","",new ItemStack(Material.GOLDEN_APPLE),true);
		
		LevelInfo level0 = new LevelInfo(Arrays.asList("§7Obtenez en d§but de partie :", "", "§7- Casque en Diamants", "§7- Plastron en Diamants", "§7- Pantalon en Diamants", "§7- Bottes en Diamants","§7- Ep§e en Diamants T1","§7- 2 Pommes dor§es","§7- 2 Potion de soin II (Jetable)" ),0);

		LevelInfo level1 = new LevelInfo(Arrays.asList("§7Obtenez en d§but de partie :", "", "§7- Casque en Diamants", "§7- Plastron en Diamants", "§7- Pantalon en Diamants", "§7- Bottes en Diamants","§7- Ep§e en Diamants T1","§7- 2 Pommes dor§es","§7- 2 Potion de soin II (Jetable)" ),15000);

		addLevelInfo(level0);
		addLevelInfo(level1);

	}

}
