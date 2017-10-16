package net.neferett.linaris.lobby.items.mainmenu;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import net.neferett.linaris.specialitems.MenuItem;
import net.neferett.linaris.specialitems.SpecialItem;
import net.neferett.linaris.utils.gui.GuiManager;
import net.neferett.linaris.lobby.inventories.MainMenuInventory;

public class MainMenuItem extends MenuItem {

	public static int			id			= -1;

	public static SpecialItem get() {
		if (id == -1)id = registerItem(new MainMenuItem());
		return get(id);
	}
	
	public MainMenuItem() {
		super("�6Menu Principal & Jeux", new ItemStack(Material.COMPASS),"�7Acc�der au menu principal","�7et aux jeux");
	}

	@Override
	public void inventoryClickEvent(Player player) {
		GuiManager.openGui(new MainMenuInventory(player));
	}
	
}