package net.neferett.linaris.lobby.games.games.fallenkindoms;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import net.neferett.linaris.lobby.shop.LevelInfo;
import net.neferett.linaris.lobby.shop.ShopItem;

public class KitFarmer extends ShopItem {

	public KitFarmer() {
		super("kit-farmer", "Farmer", "",new ItemStack(Material.BONE), true);

		LevelInfo level0 = new LevelInfo(Arrays.asList("§7Obtenez en début de partie :", "", "§7- 16 Bone Meals"), 0);

		LevelInfo level1 = new LevelInfo(Arrays.asList("§7Obtenez en début de partie :", "", "§7- 16 Bone Meals",
				"§7- Une épée en bois §4très usée §dlooting I"), 100);

		LevelInfo level2 = new LevelInfo(Arrays.asList("§7Obtenez en début de partie :", "", "§7- 24 Bone Meals",
				"§7- Une épée en bois §cusée §dlooting I"), 400);

		LevelInfo level3 = new LevelInfo(Arrays.asList("§7Obtenez en début de partie :", "", "§7- 16 Bone Meals",
				"§7- Une épée en bois §4très usée §dlooting II"), 900);

		LevelInfo level4 = new LevelInfo(Arrays.asList("§7Obtenez en début de partie :", "", "§7- 32 Bone Meals",
				"§7- Une épée en bois §4très usée §dlooting II", "    avec §dFire aspect I"), 1600);

		LevelInfo level5 = new LevelInfo(Arrays.asList("§7Obtenez en début de partie :", "", "§7- 64 Bone Meals",
				"§7- Une épée en bois §4très usée §dlooting III", "    avec §dFire aspect I"), 2500);

		addLevelInfo(level0);
		addLevelInfo(level1);
		addLevelInfo(level2);
		addLevelInfo(level3);
		addLevelInfo(level4);
		addLevelInfo(level5);
	}


}
