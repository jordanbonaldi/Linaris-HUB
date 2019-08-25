package net.neferett.linaris.handlers.items.magicbox.armor;

import net.neferett.linaris.handlers.inventories.magicbox.ArmorsLeggingsGui;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import net.neferett.linaris.specialitems.MenuItem;
import net.neferett.linaris.specialitems.SpecialItem;
import net.neferett.linaris.utils.gui.GuiManager;

public class LeggingsItem extends MenuItem {

	public static int			id			= -1;

	public static SpecialItem get() {
		if (id == -1)id = registerItem(new LeggingsItem());
		return get(id);
	}
	
	public LeggingsItem() {
		super("§aJambi§res", new ItemStack(Material.LEATHER_LEGGINGS),"");
	}

	@Override
	public void inventoryClickEvent(Player player) {

		GuiManager.openGui(new ArmorsLeggingsGui(player));
	}
	
}
