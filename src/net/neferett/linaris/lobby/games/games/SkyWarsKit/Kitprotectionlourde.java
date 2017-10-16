package net.neferett.linaris.lobby.games.games.SkyWarsKit;

import java.util.Arrays;

import org.bukkit.inventory.ItemStack;

import net.neferett.linaris.lobby.shop.LevelInfo;
import net.neferett.linaris.lobby.shop.ShopItem;

public class Kitprotectionlourde extends ShopItem{

	@SuppressWarnings("deprecation")
	public Kitprotectionlourde() {
		super("kit-PROTLOURDE","Protection Lourde","",new ItemStack(311,1,(short) 0),true);
	

		LevelInfo level0 = new LevelInfo(Arrays.asList("§7Obtenez en début de partie :", "", "§7- 1 Plastron en cuir", "§7- 1 épée en bois"),0); 
			





		LevelInfo level1 = new LevelInfo(Arrays.asList("§7Obtenez en début de partie :", "", "§7- 1 Plastron en cuir P1", "§7- 1 épée en pierre"),600);




		
		LevelInfo level2 = new LevelInfo(Arrays.asList("§7Obtenez en début de partie :", "", "§7- 1 Plastron en cuir P1", "§7- 1 épée en fer"),1200); 




		LevelInfo level3 = new LevelInfo(Arrays.asList("§7Obtenez en début de partie :", "", "§7- 1 Casque en or", "§7- 1 Plastron en fer", "§7- 1 Pantalon en or", "§7-1 épée en fer"),2500); 







		LevelInfo level4 = new LevelInfo(Arrays.asList("§7Obtenez en début de partie :", "", "§7- 1 Casque en or", "§7- 1 Plastron en fer", "§7- 1 Pantalon en fer","§7- Bottes en or" ,"§7-1 épée en fer T1"),3600); 
			
	




		LevelInfo level5 = new LevelInfo(Arrays.asList("§7Obtenez en début de partie :", "", "§7- 1 Casque en or", "§7- 1 Plastron en fer P1", "§7- 1 Pantalon en fer P1","§7- Bottes en or" ,"§7-1 épée en diamant T1"),4500); 

		
		addLevelInfo(level0);
		addLevelInfo(level1);
		addLevelInfo(level2);
		addLevelInfo(level3);
		addLevelInfo(level4);
		addLevelInfo(level5);
	
	}

}
