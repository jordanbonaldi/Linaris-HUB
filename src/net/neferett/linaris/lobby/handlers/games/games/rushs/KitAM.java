package net.neferett.linaris.lobby.handlers.games.games.rushs;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import net.neferett.linaris.lobby.shop.LevelInfo;
import net.neferett.linaris.lobby.shop.ShopItem;

public class KitAM extends ShopItem {

	public KitAM() {
		super("kit-AM","Armement lourd","",new ItemStack(Material.DIAMOND_SWORD),true);


		LevelInfo level0 = new LevelInfo(Arrays.asList("§7Obtenez en début de partie :", "", "§7- Un plastron en maille", "§7- Une épée en pierre"),0);

		
		LevelInfo level1 = new LevelInfo(Arrays.asList("§7Obtenez en début de partie :", "", "§7- Un plastron en maille", "§7- Un pantalon en maille", "§7- Une épée en fer"),500); 
			





		LevelInfo level2 = new LevelInfo(Arrays.asList("§7Obtenez en début de partie :", "", "§7- Un plastron en or", "§7- Un pantalon en maille", "§7- Une épée en fer T1"),1200);




		LevelInfo level3 = new LevelInfo(Arrays.asList("§7Obtenez en début de partie :", "", "§7- Un plastron en or", "§7- Un pantalon en or", "§7- Une épée en fer T1 K1"),2600) ;
			


		LevelInfo level4 = new LevelInfo(Arrays.asList("§7Obtenez en début de partie :", "", "§7- Un plastron en fer", "§7- Un pantalon en or", "§7- Une épée en fer T1 K1"),3500) ;



		LevelInfo level5 = new LevelInfo(Arrays.asList("§7Obtenez en début de partie :", "", "§7- Un plastron en fer", "§7- Un pantalon en fer", "§7- Une épée en diamant T1 K1"),4000); 

		addLevelInfo(level0);
		addLevelInfo(level1);
		addLevelInfo(level2);
		addLevelInfo(level3);
		addLevelInfo(level4);
		addLevelInfo(level5);
}
}