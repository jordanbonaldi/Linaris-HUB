package net.neferett.linaris.lobby.items.epicchest.armor;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import net.neferett.linaris.specialitems.MenuItem;
import net.neferett.linaris.specialitems.SpecialItem;
import net.neferett.linaris.utils.gui.GuiManager;
import net.neferett.linaris.lobby.inventories.epicchest.ArmorsHelmetGui;

public class HelmetItem extends MenuItem {

	public static int			id			= -1;

	public static SpecialItem get() {
		if (id == -1)id = registerItem(new HelmetItem());
		return get(id);
	}
	
	public HelmetItem() {
		super("§aChapeaux", new ItemStack(Material.LEATHER_HELMET),"");
	}

	@Override
	public void inventoryClickEvent(Player player) {

		GuiManager.openGui(new ArmorsHelmetGui(player));
	}
	
}
