package net.neferett.linaris.lobby.games.games.SkyWarsKit;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import net.neferett.linaris.lobby.shop.LevelInfo;
import net.neferett.linaris.lobby.shop.ShopItem;

public class KitArcher extends ShopItem {

	public KitArcher() {
		super("kit-archer","Archer","",new ItemStack(Material.FEATHER),true);
		

		

		
		LevelInfo level0 = new LevelInfo(Arrays.asList("§7Obtenez en début de partie :", "", "§7- 1 Plastron en maille", "§7- 1 Arc", "§7- 10 fleches"),0); 
			



		LevelInfo level1 = new LevelInfo(Arrays.asList("§7Obtenez en début de partie :", "", "§7- 1 Plastron en maille", "§7- 1 Arc ","§7- 24 fleches"),500); 
			




		LevelInfo level2 = new LevelInfo(Arrays.asList("§7Obtenez en début de partie :", "", "§7- 1 Plastron en maille", "§7- 1 Arc INFINITY","§7- 1 fleche", "§7- 1 épée en pierre"),1200); 
			





		LevelInfo level3 = new LevelInfo(Arrays.asList("§7Obtenez en début de partie :", "", "§7- 1 Plastron en or", "§7- 1 Arc INFINITY POWER 1","§7- 1 fleche", "§7- 1 épée en pierre"),2400); 
			




		LevelInfo level4 = new LevelInfo(Arrays.asList("§7Obtenez en début de partie :", "", "§7- 1 Plastron en fer", "§7- 1 Arc INFINITY POWER 1 K1","§7- 1 fleche", "§7- 1 épée en fer"),3000); 
			
	



		LevelInfo level5 = new LevelInfo(Arrays.asList("§7Obtenez en début de partie :", "", "§7- 1 Plastron en fer", "§7- 1 Arc INFINITY POWER 2 K2","§7- 1 fleche", "§7- 1 épée en fer"),4000);

		
		addLevelInfo(level0);
		addLevelInfo(level1);
		addLevelInfo(level2);
		addLevelInfo(level3);
		addLevelInfo(level4);
		addLevelInfo(level5);
	}

}
