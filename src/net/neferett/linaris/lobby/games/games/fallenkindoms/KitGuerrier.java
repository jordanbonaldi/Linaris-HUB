package net.neferett.linaris.lobby.games.games.fallenkindoms;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import net.neferett.linaris.lobby.shop.LevelInfo;
import net.neferett.linaris.lobby.shop.ShopItem;

public class KitGuerrier extends ShopItem {

	public KitGuerrier() {
		super("kit-guerrier", "Guerrier", "", new ItemStack(Material.STONE_SWORD), true);

		LevelInfo level0 = new LevelInfo(Arrays.asList("�7Obtenez en d�but de partie :", "", "�7- Un plastron en cuir",
				"�7- Une �p�e en pierre �cus�e"), 0);

		LevelInfo level1 = new LevelInfo(Arrays.asList("�7Obtenez en d�but de partie :", "",
				"�7- Un plastron en cuir �dPI", "�7- Un pantalon en cuir", "�7- Une �p�e en pierre"), 100);

		LevelInfo level2 = new LevelInfo(
				Arrays.asList("�7Obtenez en d�but de partie :", "", "�7- Un plastron en fer",
						"�7- Un pantalon en cuir �dPI", "�7- Une �p�e en pierre", "�7- Une �p�e en fer �4tr�s us�e"),
				400);

		LevelInfo level3 = new LevelInfo(
				Arrays.asList("�7Obtenez en d�but de partie :", "", "�7- Un plastron en fer �dPI",
						"�7- Un pantalon en fer", "�7- Une �p�e en pierre", "�7- Une �p�e en fer �cus�e"),
				900);

		LevelInfo level4 = new LevelInfo(Arrays.asList("�7Obtenez en d�but de partie :", "",
				"�7- Un plastron en fer �dPII", "�7- Un pantalon en fer �dPI", "�7- Une �p�e en pierre �dsharpness I",
				"�7- Une �p�e en fer �cus�e"), 1600);
		LevelInfo level5 = new LevelInfo(Arrays.asList("�7Obtenez en d�but de partie :", "",
				"�7- Un plastron en fer �dPII TI", "�7- Un pantalon en fer �dPII",
				"�7- Une �p�e en pierre �dsharpness I", "�7- Une �p�e en fer �cus�e �dsharpness I"), 2500);
		addLevelInfo(level0);
		addLevelInfo(level1);
		addLevelInfo(level2);
		addLevelInfo(level3);
		addLevelInfo(level4);
		addLevelInfo(level5);
	}

}
