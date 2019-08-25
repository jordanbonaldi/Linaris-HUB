package net.neferett.linaris.handlers.items.mainmenu;

import net.neferett.linaris.handlers.inventories.MainMenuInventory;
import net.neferett.linaris.handlers.inventories.PrefsGui;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import net.neferett.linaris.specialitems.MenuItem;
import net.neferett.linaris.specialitems.SpecialItem;
import net.neferett.linaris.utils.gui.GuiManager;

public class Preferences extends MenuItem {

	public static int			id			= -1;

	public static SpecialItem get() {
		if (id == -1)id = registerItem(new Preferences());
		return get(id);
	}
	
	public Preferences() {
		super("§6Pr§f§rences", new ItemStack(Material.PAPER),"§7Activer/d§sactiver les", "§7m§ssageries et invitations",
				"§7ainsi que les options de groupe");
	}

	@Override
	public void inventoryClickEvent(Player player) {
		GuiManager.openGui(new PrefsGui(player, new MainMenuInventory(player)));
	}
	
}
