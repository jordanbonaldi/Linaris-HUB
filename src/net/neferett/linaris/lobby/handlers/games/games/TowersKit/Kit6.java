package net.neferett.linaris.lobby.handlers.games.games.TowersKit;

import java.util.Arrays;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import net.neferett.linaris.lobby.handlers.shop.LevelInfo;
import net.neferett.linaris.lobby.handlers.shop.ShopItem;
import net.neferett.linaris.lobby.utils.ItemBuilder;

public class Kit6 extends ShopItem {

	@SuppressWarnings("deprecation")
	public Kit6() {
		super("kit6","Kit 6","",new ItemBuilder(new ItemStack(267,1,(short) 0)).addEnchantment(Enchantment.DAMAGE_ALL, 1).build(),true);
		
		LevelInfo level0 = new LevelInfo(Arrays.asList("§7Obtenez en début de partie :", "", "§7- Casque en Fer", "§7- Plastron en Fer", "§7- Pantalon en Fer", "§7- Bottes en Fer","§7- Epée en Fer T1","§7- Pomme dorée","§7- Potion de soin (Jetable)" ),0);
		LevelInfo level1 = new LevelInfo(Arrays.asList("§7Obtenez en début de partie :", "", "§7- Casque en Fer", "§7- Plastron en Fer", "§7- Pantalon en Fer", "§7- Bottes en Fer","§7- Epée en Fer T1","§7- Pomme dorée","§7- Potion de soin (Jetable)" ),4000);

		addLevelInfo(level0);
		addLevelInfo(level1);

	}

}
