package net.neferett.linaris.lobby.items.epicchest.kits;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import net.neferett.linaris.specialitems.MenuItem;
import net.neferett.linaris.specialitems.SpecialItem;

public class KitPVPSWAPItem extends MenuItem {

	public static int			id			= -1;

	public static SpecialItem get() {
		if (id == -1)id = registerItem(new KitPVPSWAPItem());
		return get(id);
	}
	
	public KitPVPSWAPItem() {
		super("§eKits PvpSwap", (new ItemStack(Material.ENDER_PEARL)));
	}

	@Override
	public void inventoryClickEvent(Player player) {

	}
	
}
