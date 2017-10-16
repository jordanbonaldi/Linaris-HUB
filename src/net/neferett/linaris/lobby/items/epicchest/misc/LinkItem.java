package net.neferett.linaris.lobby.items.epicchest.misc;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import net.neferett.linaris.specialitems.MenuItem;
import net.neferett.linaris.specialitems.SpecialItem;

public class LinkItem extends MenuItem {

	public static int			id			= -1;

	public static SpecialItem get() {
		if (id == -1)id = registerItem(new LinkItem());
		return get(id);
	}
	
	public LinkItem() {
		super("§aParrainez un joueur !", new ItemStack(Material.EMERALD),"","§fOffre le mini-vip","§fa un autre joueur","§fpendant 7 jours !","§cUsage Unique","","§eRéservé aux §aEpicVIP");
	}

	@Override
	public void inventoryClickEvent(Player player) {
		player.sendMessage("§6Helmet");
	}
	
}
