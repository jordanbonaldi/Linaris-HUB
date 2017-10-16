package net.neferett.linaris.lobby.games.games.fallenkindoms;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import net.neferett.linaris.lobby.shop.LevelInfo;
import net.neferett.linaris.lobby.shop.ShopItem;

public class KitRanger extends ShopItem {

	public KitRanger() {
		super("kit-ranger", "Ranger", "", new ItemStack(Material.BOW), true);

		LevelInfo level0 = new LevelInfo(
				Arrays.asList("�7Obtenez en d�but de partie :", "", "�7- Un arc �4tr�s us�e", "�7- 4 fl�ches"), 0);

		LevelInfo level1 = new LevelInfo(
				Arrays.asList("�7Obtenez en d�but de partie :", "", "�7- Un arc �cus�e", "�7- 16 fl�ches"), 100);

		LevelInfo level2 = new LevelInfo(Arrays.asList("�7Obtenez en d�but de partie :", "",
				"�7- Un arc �cus�e �dPunch I", "�7- 24 fl�ches", "�7- Des bottes en cuir �dFeather Falling I"), 400);

		LevelInfo level3 = new LevelInfo(
				Arrays.asList("�7Obtenez en d�but de partie :", "", "�7- Un arc �cpeu us�e �dPunch I", "�7- 32 fl�ches",
						"�7- Des bottes en cuir �dPI�5,", "  �dFeather Falling II"),
				900);

		LevelInfo level4 = new LevelInfo(
				Arrays.asList("�7Obtenez en d�but de partie :", "", "�7- Un arc �cpeu us�e �dPunch I�5, �dPower I",
						"�7- 48 fl�ches", "�7- Des bottes en cuir �dPI�5,", "  �dFeather Falling III"),
				1600);

		LevelInfo level5 = new LevelInfo(
				Arrays.asList("�7Obtenez en d�but de partie :", "", "�7- Un arc �dPunch I�5, �dPower II",
						"�7- 64 fl�ches", "�7- Des bottes en cuir �dPII�5,", "  �dFeather Falling IV"),
				2500);
		addLevelInfo(level0);
		addLevelInfo(level1);
		addLevelInfo(level2);
		addLevelInfo(level3);
		addLevelInfo(level4);
		addLevelInfo(level5);
	}

}
