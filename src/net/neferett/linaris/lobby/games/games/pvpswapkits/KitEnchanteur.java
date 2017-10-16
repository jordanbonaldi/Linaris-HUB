package net.neferett.linaris.lobby.games.games.pvpswapkits;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import net.neferett.linaris.lobby.shop.LevelInfo;
import net.neferett.linaris.lobby.shop.ShopItem;

public class KitEnchanteur extends ShopItem {

	public KitEnchanteur() {
		super("kit-enchanteur","Enchanteur","",new ItemStack(Material.EXP_BOTTLE),true);
	
		LevelInfo level0 = new LevelInfo(Arrays.asList("§b10% §7de chance d'obtenir"," §7- §eDes bouteilles d'xp"," §7- §eDes livres enchantés"),0);
		LevelInfo level1 = new LevelInfo(Arrays.asList("§b15% §7de chance d'obtenir"," §7- §eDes bouteilles d'xp"," §7- §eDes livres enchantés"),100);
		LevelInfo level2 = new LevelInfo(Arrays.asList("§b20% §7de chance d'obtenir"," §7- §eDes bouteilles d'xp"," §7- §eDes livres enchantés"),400);
		LevelInfo level3 = new LevelInfo(Arrays.asList("§b25% §7de chance d'obtenir"," §7- §eDes bouteilles d'xp"," §7- §eDes livres enchantés"),900);
		LevelInfo level4 = new LevelInfo(Arrays.asList("§b30% §7de chance d'obtenir"," §7- §eDes bouteilles d'xp"," §7- §eDes livres enchantés"),1600);
		LevelInfo level5 = new LevelInfo(Arrays.asList("§b35% §7de chance d'obtenir"," §7- §eDes bouteilles d'xp"," §7- §eDes livres enchantés"),2500);
		addLevelInfo(level0);
		addLevelInfo(level1);
		addLevelInfo(level2);
		addLevelInfo(level3);
		addLevelInfo(level4);
		addLevelInfo(level5);
		

	}

}
