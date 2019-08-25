package net.neferett.linaris.handlers.items.magicbox.kits;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import net.neferett.linaris.specialitems.MenuItem;
import net.neferett.linaris.specialitems.SpecialItem;

public class KitsSkyWars extends MenuItem {

	public static int			id			= -1;

	public static SpecialItem get() {
		if (id == -1)id = registerItem(new KitsSkyWars());
		return get(id);
	}
	
	public KitsSkyWars() {
		super("§eKits SkyWars", (new ItemStack(Material.EYE_OF_ENDER)));
	}

	@Override
	public void inventoryClickEvent(Player player) {

	}
	
}
