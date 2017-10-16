package net.neferett.linaris.lobby.games.games.fallenkindoms;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import net.neferett.linaris.lobby.shop.LevelInfo;
import net.neferett.linaris.lobby.shop.ShopItem;

public class KitMineur extends ShopItem {

	public KitMineur() {
		super("kit-mineur", "Mineur", "",new ItemStack(Material.STONE_PICKAXE), true);

		LevelInfo level0 = new LevelInfo(
				Arrays.asList("§7Obtenez en début de partie :", "", "§7- Une pioche en pierre §cusée"), 0);

		LevelInfo level1 = new LevelInfo(
				Arrays.asList("§7Obtenez en début de partie :", "", "§7- Une pioche en pierre"), 100);

		LevelInfo level2 = new LevelInfo(Arrays.asList("§7Obtenez en début de partie :", "", "§7- Une pioche en pierre",
				"§7- Une pioche en fer §4très usée"), 400);

		LevelInfo level3 = new LevelInfo(Arrays.asList("§7Obtenez en début de partie :", "", "§7- Une pioche en pierre",
				"§7- Une pioche en fer §cusée"), 900);

		LevelInfo level4 = new LevelInfo(Arrays.asList("§7Obtenez en début de partie :", "",
				"§7- Une pioche en pierre §defficacité I", "§7- Une pioche en fer §cusée"), 1600);

		LevelInfo level5 = new LevelInfo(Arrays.asList("§7Obtenez en début de partie :", "",
				"§7- Une pioche en pierre §defficacité I", "§7- Une pioche en fer §cusée §defficacité I"), 2500);
		addLevelInfo(level0);
		addLevelInfo(level1);
		addLevelInfo(level2);
		addLevelInfo(level3);
		addLevelInfo(level4);
		addLevelInfo(level5);
	}

}
