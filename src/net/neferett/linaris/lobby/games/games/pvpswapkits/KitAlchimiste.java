package net.neferett.linaris.lobby.games.games.pvpswapkits;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import net.neferett.linaris.lobby.shop.LevelInfo;
import net.neferett.linaris.lobby.shop.ShopItem;

public class KitAlchimiste extends ShopItem {

	public KitAlchimiste() {
		super("kit-alchimiste","Alchimiste","",new ItemStack(Material.BREWING_STAND_ITEM),true);
	
		LevelInfo level0 = new LevelInfo(Arrays.asList("�b10% �7de chance d'obtenir"," �7- �eDes potions"),0);
		LevelInfo level1 = new LevelInfo(Arrays.asList("�b15% �7de chance d'obtenir"," �7- �eDes potions"),100);
		LevelInfo level2 = new LevelInfo(Arrays.asList("�b20% �7de chance d'obtenir"," �7- �eDes potions"),400);
		LevelInfo level3 = new LevelInfo(Arrays.asList("�b25% �7de chance d'obtenir"," �7- �eDes potions"),900);
		LevelInfo level4 = new LevelInfo(Arrays.asList("�b30% �7de chance d'obtenir"," �7- �eDes potions"),1600);
		LevelInfo level5 = new LevelInfo(Arrays.asList("�b35% �7de chance d'obtenir"," �7- �eDes potions"),2500);
		addLevelInfo(level0);
		addLevelInfo(level1);
		addLevelInfo(level2);
		addLevelInfo(level3);
		addLevelInfo(level4);
		addLevelInfo(level5);
	}

}