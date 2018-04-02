package net.neferett.linaris.lobby.handlers.items.magicbox.boosters;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import net.neferett.linaris.specialitems.MenuItem;
import net.neferett.linaris.specialitems.SpecialItem;

public class Booster7Item extends MenuItem {

	public static int			id			= -1;

	public static SpecialItem get() {
		if (id == -1)id = registerItem(new Booster7Item());
		return get(id);
	}
	
	@SuppressWarnings("deprecation")
	public Booster7Item() {
		super("§6Booster §7x§a7 Jours", new ItemStack(384,7,(short) 0),"§7Durée: §a7 Jours","","§a> §dAugmente les gains d'EpiCoins §e+25%","§a> §dpour TOUS les joueurs dans votre jeu !","§a> §dSe cumule avec les joueurs dans votre","§a> §djeu qui ont un booster, jusqu'à §e+150%","§a> §dSe cumule avec les bonus VIP et EpicBonus","","§7Chaque achat étend la durée !");
	}

	@Override
	public void inventoryClickEvent(Player player) {

	}
	
}
