package net.neferett.linaris.lobby.gadgets.spectral;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import net.neferett.linaris.specialitems.MenuItem;

public class ChestplateItem extends MenuItem {

	public static int	id;

	public ChestplateItem() {
		super("Ectoplasme", new ItemStack(Material.CHAINMAIL_CHESTPLATE));
		this.setMovable(false);
	}

	@Override
	public void inventoryClickEvent(Player player) {

	}
}
