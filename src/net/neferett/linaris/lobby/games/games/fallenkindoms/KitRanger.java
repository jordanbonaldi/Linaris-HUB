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
				Arrays.asList("§7Obtenez en début de partie :", "", "§7- Un arc §4très usée", "§7- 4 flèches"), 0);

		LevelInfo level1 = new LevelInfo(
				Arrays.asList("§7Obtenez en début de partie :", "", "§7- Un arc §cusée", "§7- 16 flèches"), 100);

		LevelInfo level2 = new LevelInfo(Arrays.asList("§7Obtenez en début de partie :", "",
				"§7- Un arc §cusée §dPunch I", "§7- 24 flèches", "§7- Des bottes en cuir §dFeather Falling I"), 400);

		LevelInfo level3 = new LevelInfo(
				Arrays.asList("§7Obtenez en début de partie :", "", "§7- Un arc §cpeu usée §dPunch I", "§7- 32 flèches",
						"§7- Des bottes en cuir §dPI§5,", "  §dFeather Falling II"),
				900);

		LevelInfo level4 = new LevelInfo(
				Arrays.asList("§7Obtenez en début de partie :", "", "§7- Un arc §cpeu usée §dPunch I§5, §dPower I",
						"§7- 48 flèches", "§7- Des bottes en cuir §dPI§5,", "  §dFeather Falling III"),
				1600);

		LevelInfo level5 = new LevelInfo(
				Arrays.asList("§7Obtenez en début de partie :", "", "§7- Un arc §dPunch I§5, §dPower II",
						"§7- 64 flèches", "§7- Des bottes en cuir §dPII§5,", "  §dFeather Falling IV"),
				2500);
		addLevelInfo(level0);
		addLevelInfo(level1);
		addLevelInfo(level2);
		addLevelInfo(level3);
		addLevelInfo(level4);
		addLevelInfo(level5);
	}

}
