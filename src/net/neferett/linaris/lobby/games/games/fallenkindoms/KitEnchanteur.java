package net.neferett.linaris.lobby.games.games.fallenkindoms;

import java.util.Arrays;

import org.bukkit.inventory.ItemStack;

import net.neferett.linaris.lobby.shop.LevelInfo;
import net.neferett.linaris.lobby.shop.ShopItem;

public class KitEnchanteur extends ShopItem {

	@SuppressWarnings("deprecation")
	public KitEnchanteur() {
		super("kit-enchanteur", "Enchanteur", "", new ItemStack(384, 1, (short) 0), true);

		LevelInfo level0 = new LevelInfo(
				Arrays.asList("§7Obtenez en début de partie :", "", "§7- 6 Bouteilles d'Exp", "§7- 3 Lapis Lazuli"), 0);
		LevelInfo level1 = new LevelInfo(Arrays.asList("§7Obtenez en début de partie :", "", "§7- 12 Bouteilles d'Exp",
				"§7- 6 Lapis Lazuli", "§7- Une enclume §4très usée", "§7- Un livre Sharpness I"), 100);

		LevelInfo level2 = new LevelInfo(
				Arrays.asList("§7Obtenez en début de partie :", "", "§7- 24 Bouteilles d'Exp", "§7- 12 Lapis Lazuli",
						"§7- Une enclume §4très usée", "§7- 2 livres Sharpness I", "§7- Un livre Protection II"),
				400);

		LevelInfo level3 = new LevelInfo(Arrays.asList("§7Obtenez en début de partie :", "", "§7- 32 Bouteilles d'Exp",
				"§7- 16 Lapis Lazuli", "§7- Une enclume §cusée", "§7- 2 livres Sharpness I",
				"§7- 2 livres Protection II", "§7- Un livre Protection I", "§7- Un livre Thorns I"), 900);

		LevelInfo level4 = new LevelInfo(
				Arrays.asList("§7Obtenez en début de partie :", "", "§7- 48 Bouteilles d'Exp", "§7- 24 Lapis Lazuli",
						"§7- Une enclume §cusée", "§7- 2 livres Sharpness I", "§7- 2 livres Protection II",
						"§7- Un livre Protection I", "§7- 2 livres Thorns I", "§7- Un livre KnockBack II"),
				1600);

		LevelInfo level5 = new LevelInfo(Arrays.asList("§7Obtenez en début de partie :", "", "§7- 64 Bouteilles d'Exp",
				"§7- 32 Lapis Lazuli", "§7- Une enclume", "§7- 2 livres Sharpness I", "§7- 2 livres Protection II",
				"§7- Un livre Protection I", "§7- 2 livres Thorns I", "§7- Un livre KnockBack II",
				"§7- 2 livres Sharpness II"), 2500);

		addLevelInfo(level0);
		addLevelInfo(level1);
		addLevelInfo(level2);
		addLevelInfo(level3);
		addLevelInfo(level4);
		addLevelInfo(level5);
	}

}
