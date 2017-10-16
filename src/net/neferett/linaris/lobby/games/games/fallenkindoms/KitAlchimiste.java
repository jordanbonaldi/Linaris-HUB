package net.neferett.linaris.lobby.games.games.fallenkindoms;

import java.util.Arrays;

import org.bukkit.inventory.ItemStack;

import net.neferett.linaris.lobby.shop.LevelInfo;
import net.neferett.linaris.lobby.shop.ShopItem;

public class KitAlchimiste extends ShopItem {

	@SuppressWarnings("deprecation")
	public KitAlchimiste() {
		super("kit-alchimiste","Alchimiste","",new ItemStack(379,1,(short) 0),true);


		LevelInfo level0 = new LevelInfo(Arrays.asList("§7Obtenez en début de partie :", "", "§7- 1 Brewing Stand", "§7- 3 Awkward potion", "§7- 6 Bouteilles vides"),0); 



		LevelInfo level1 = new LevelInfo(Arrays.asList("§7Obtenez en début de partie :", "", "§7- 1 Brewing Stand", "§7- 3 Awkward potion", "§7- 6 Bouteilles vides", "§7- 1 Oeil d'araignée", "§7- 1 Sucre", "§7- 1 Champignon"),100); 

		LevelInfo level2 = new LevelInfo(Arrays.asList("§7Obtenez en début de partie :", "", "§7- 1 Brewing Stand", "§7- 6 Awkward potion", "§7- 3 Bouteilles vides", "§7- 2 Yeux d'araignée", "§7- 2 Sucres", "§7- 2 Champignons", "§7- 1 Redstone", "§7- 1 Glowstone"),400); 




		LevelInfo level3 = new LevelInfo(Arrays.asList("§7Obtenez en début de partie :", "", "§7- 1 Brewing Stand", "§7- 6 Awkward potion", "§7- 3 Bouteilles vides", "§7- 3 Yeux d'araignée", "§7- 3 Sucres", "§7- 3 Champignons", "§7- 1 Redstone", "§7- 1 Glowstone", "§7- 1 Pastèque dorée"),900); 




		LevelInfo level4 = new LevelInfo(Arrays.asList("§7Obtenez en début de partie :", "", "§7- 1 Brewing Stand", "§7- 9 Awkward potion", "§7- 3 Bouteilles vides", "§7- 3 Yeux d'araignée", "§7- 3 Sucres", "§7- 3 Champignons", "§7- 2 Redstones", "§7- 2 Glowstones", "§7- 1 Pastèque dorée", "§7- 1 Larme de Ghast"),1600) ;




		LevelInfo level5 = new LevelInfo(Arrays.asList("§7Obtenez en début de partie :", "", "§7- 1 Brewing Stand", "§7- 12 Awkward potion", "§7- 3 Yeux d'araignée", "§7- 5 Sucres", "§7- 3 Champignons", "§7- 2 Redstones", "§7- 2 Glowstones", "§7- 1 Pastèque dorée", "§7- 1 Larme de Ghast", "§7- 1 Instant Damage II"),2500); 
	
		addLevelInfo(level0);
		addLevelInfo(level1);
		addLevelInfo(level2);
		addLevelInfo(level3);
		addLevelInfo(level4);
		addLevelInfo(level5);
	}

}
