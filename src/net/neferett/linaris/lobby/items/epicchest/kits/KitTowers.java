package net.neferett.linaris.lobby.items.epicchest.kits;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import net.neferett.linaris.specialitems.MenuItem;
import net.neferett.linaris.specialitems.SpecialItem;

public class KitTowers extends MenuItem {

	public static int			id			= -1;

	public static SpecialItem get() {
		if (id == -1)id = registerItem(new KitTowers());
		return get(id);
	}
	
	public KitTowers() {
		super("�eKits Towers", (new ItemStack(Material.NETHER_FENCE)));
	}

	@Override
	public void inventoryClickEvent(Player player) {

	}
	
}
