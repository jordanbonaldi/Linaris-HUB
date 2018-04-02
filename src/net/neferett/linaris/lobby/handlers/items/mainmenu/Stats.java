package net.neferett.linaris.lobby.handlers.items.mainmenu;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import net.neferett.linaris.specialitems.MenuItem;
import net.neferett.linaris.specialitems.SpecialItem;

public class Stats extends MenuItem {

	public static int			id			= -1;

	public static SpecialItem get() {
		if (id == -1)id = registerItem(new Stats());
		return get(id);
	}
	
	public Stats() {
		super("§6Stats et Succès", new ItemStack(Material.BOOK_AND_QUILL),"§7Vos stats et succès", "§c§oBientôt");
	}

	@Override
	public void inventoryClickEvent(Player player) {
		setCancelInteractEvent(true);
	}
	
}
