package net.neferett.linaris.lobby.items.epicchest.agmmf;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import net.neferett.linaris.specialitems.MenuItem;
import net.neferett.linaris.specialitems.SpecialItem;

public class ExcaliburItem extends MenuItem {

	public static int			id			= -1;

	public static SpecialItem get() {
		if (id == -1)id = registerItem(new ExcaliburItem());
		return get(id);
	}
	
	public ExcaliburItem() {
		super("§6Excalibur !", new ItemStack(Material.DIAMOND_SWORD),"§7Maniez l'épée légendaire !","§cRéservé aux §aEpicVIP");
	}

	@Override
	public void inventoryClickEvent(Player player) {
		player.sendMessage("§6Helmet");
	}
	
}
