package net.neferett.linaris.handlers.items.magicbox.kits;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import net.neferett.linaris.specialitems.MenuItem;
import net.neferett.linaris.specialitems.SpecialItem;

public class KitRush extends MenuItem {

	public static int			id			= -1;

	public static SpecialItem get() {
		if (id == -1)id = registerItem(new KitRush());
		return get(id);
	}
	
	public KitRush() {
		super("§eKits Rushs", (new ItemStack(Material.BED)));
	}

	@Override
	public void inventoryClickEvent(Player player) {

	}
	
}
