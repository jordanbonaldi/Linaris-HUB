package net.neferett.linaris.lobby.handlers.items.magicbox.armor;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import net.neferett.linaris.lobby.handlers.inventories.magicbox.ArmorsLeggingsGui;
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
		super("§aJambières", new ItemStack(Material.LEATHER_LEGGINGS),"");
	}

	@Override
	public void inventoryClickEvent(Player player) {

		GuiManager.openGui(new ArmorsLeggingsGui(player));
	}
	
}
