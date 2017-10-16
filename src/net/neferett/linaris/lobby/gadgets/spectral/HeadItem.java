package net.neferett.linaris.lobby.gadgets.spectral;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import net.neferett.linaris.specialitems.MenuItem;

public class HeadItem extends MenuItem {

	public static int	id;

	public HeadItem() {
		super("Tête", new ItemStack(Material.SKULL_ITEM));
		this.setMovable(false);
	}

	@Override
	public void inventoryClickEvent(Player player) {

	}

}
