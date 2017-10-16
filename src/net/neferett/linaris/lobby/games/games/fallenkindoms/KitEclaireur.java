package net.neferett.linaris.lobby.games.games.fallenkindoms;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import net.neferett.linaris.lobby.shop.LevelInfo;
import net.neferett.linaris.lobby.shop.ShopItem;

public class KitEclaireur extends ShopItem {

	public KitEclaireur() {
		super("kit-eclaireur","Eclaireur","",new ItemStack(Material.FEATHER),true);
		
		LevelInfo level0 = new LevelInfo(Arrays.asList("�7Obtenez en d�but de partie :", "", "�7- Une pioche en fer �7(�82 utilisations�7)", "�7- Une potion �8splash �5speed"),0);
		LevelInfo level1 = new LevelInfo(Arrays.asList("�7Obtenez en d�but de partie :", "", "�7- Une pioche en fer �7(�84 utilisations�7)", "�7- Deux potions �8splash �5speed"),100);
		LevelInfo level2 = new LevelInfo(Arrays.asList("�7Obtenez en d�but de partie :", "", "�7- Une pioche en fer �7(�84 utilisations�7)", "�7- Une potion �8splash �5speed", "�7- Une potion �8splash �5speed �dII"),400);
		LevelInfo level3 = new LevelInfo(Arrays.asList("�7Obtenez en d�but de partie :", "", "�7- Une pioche en fer �7(�88 utilisations�7)", "�7- Une potion �8splash �5speed", "�7- Une potion �8splash �5speed �dII", "�7- Une potion speed"),900);
		LevelInfo level4 = new LevelInfo(Arrays.asList("�7Obtenez en d�but de partie :", "", "�7- Une pioche en fer �7(�810 utilisations�7)", "�7- Une potion �8splash �5speed", "�7- Une potion �8splash �5speed �dII", "�7- Une potion speed", "�7- Une potion �8splash �5speed �dallong�e"),1600) ;
		LevelInfo level5 = new LevelInfo(Arrays.asList("�7Obtenez en d�but de partie :", "", "�7- Une pioche en fer �7(�810 utilisations�7)", "�7- Une potion �8splash �5speed", "�7- Une potion �8splash �5speed �dII", "�7- Une potion speed", "�7- Une potion �8splash �5speed �dallong�e", "�7- Une potion speed �dallong�e"),2500);
		addLevelInfo(level0);
		addLevelInfo(level1);
		addLevelInfo(level2);
		addLevelInfo(level3);
		addLevelInfo(level4);
		addLevelInfo(level5);
	}



}
