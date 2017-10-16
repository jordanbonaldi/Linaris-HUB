package net.neferett.linaris.lobby.items.epicchest.kits;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import net.neferett.linaris.specialitems.MenuItem;
import net.neferett.linaris.specialitems.SpecialItem;

public class KitUHCItem extends MenuItem {

	public static int			id			= -1;

	public static SpecialItem get() {
		if (id == -1)id = registerItem(new KitUHCItem());
		return get(id);
	}
	
	public KitUHCItem() {
		super("§eEffets UHC/UHCRun", (new ItemStack(Material.GOLDEN_APPLE)));
	}

	@Override
	public void inventoryClickEvent(Player player) {

	}
	
}
