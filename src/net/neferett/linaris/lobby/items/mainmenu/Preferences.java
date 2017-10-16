package net.neferett.linaris.lobby.items.mainmenu;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import net.neferett.linaris.specialitems.MenuItem;
import net.neferett.linaris.specialitems.SpecialItem;
import net.neferett.linaris.utils.gui.GuiManager;
import net.neferett.linaris.lobby.inventories.MainMenuInventory;
import net.neferett.linaris.lobby.inventories.PrefsGui;

public class Preferences extends MenuItem {

	public static int			id			= -1;

	public static SpecialItem get() {
		if (id == -1)id = registerItem(new Preferences());
		return get(id);
	}
	
	public Preferences() {
		super("§6Préférences", new ItemStack(Material.PAPER),"§7Activer/désactiver les", "§7méssageries et invitations",
				"§7ainsi que les options de groupe");
	}

	@Override
	public void inventoryClickEvent(Player player) {
		GuiManager.openGui(new PrefsGui(player, new MainMenuInventory(player)));
	}
	
}
