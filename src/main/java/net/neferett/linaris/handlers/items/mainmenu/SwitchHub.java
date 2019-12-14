package net.neferett.linaris.handlers.items.mainmenu;

import net.neferett.linaris.handlers.inventories.ChangeHubInventory;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import net.neferett.linaris.specialitems.MenuItem;
import net.neferett.linaris.specialitems.SpecialItem;
import net.neferett.linaris.utils.gui.GuiManager;

public class SwitchHub extends MenuItem {

	public static int			id			= -1;

	public static SpecialItem get() {
		if (id == -1)id = registerItem(new SwitchHub());
		return get(id);
	}
	
	public SwitchHub() {
		super("§6Changer de Hub", new ItemStack(Material.ENDER_PORTAL_FRAME),"§7Rejoindre le Hub désiré");
	}

	@Override
	public void inventoryClickEvent(Player player) {
		GuiManager.openGui(new ChangeHubInventory(player));
	}
	
}
