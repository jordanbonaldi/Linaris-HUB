package net.neferett.linaris.handlers.games.games.TowersKit;

import java.util.Arrays;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import net.neferett.linaris.handlers.shop.LevelInfo;
import net.neferett.linaris.handlers.shop.ShopItem;
import net.neferett.linaris.utils.ItemBuilder;

public class Kit6 extends ShopItem {

	@SuppressWarnings("deprecation")
	public Kit6() {
		super("kit6","Kit 6","",new ItemBuilder(new ItemStack(267,1,(short) 0)).addEnchantment(Enchantment.DAMAGE_ALL, 1).build(),true);
		
		LevelInfo level0 = new LevelInfo(Arrays.asList("§7Obtenez en d§but de partie :", "", "§7- Casque en Fer", "§7- Plastron en Fer", "§7- Pantalon en Fer", "§7- Bottes en Fer","§7- Ep§e en Fer T1","§7- Pomme dor§e","§7- Potion de soin (Jetable)" ),0);
		LevelInfo level1 = new LevelInfo(Arrays.asList("§7Obtenez en d§but de partie :", "", "§7- Casque en Fer", "§7- Plastron en Fer", "§7- Pantalon en Fer", "§7- Bottes en Fer","§7- Ep§e en Fer T1","§7- Pomme dor§e","§7- Potion de soin (Jetable)" ),4000);

		addLevelInfo(level0);
		addLevelInfo(level1);

	}

}
