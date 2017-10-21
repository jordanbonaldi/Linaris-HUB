package net.neferett.linaris.lobby.items.epicchest.agmmf;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import net.neferett.linaris.specialitems.MenuItem;
import net.neferett.linaris.specialitems.SpecialItem;

public class SpectreItem extends MenuItem {

	public static int			id			= -1;

	public static SpecialItem get() {
		if (id == -1)id = registerItem(new SpectreItem());
		return get(id);
	}
	
	public SpectreItem() {
		super("§fSpectre", new ItemStack(Material.GHAST_TEAR),"§7Transformez vous en Spectre !","§cRéservé aux §aEpicVIP");
	}

	@Override
	public void inventoryClickEvent(Player player) {
		player.sendMessage("§6Helmet");
	}
	
}
