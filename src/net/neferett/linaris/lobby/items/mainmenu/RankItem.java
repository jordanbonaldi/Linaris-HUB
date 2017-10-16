package net.neferett.linaris.lobby.items.mainmenu;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import net.neferett.linaris.specialitems.MenuItem;
import net.neferett.linaris.specialitems.SpecialItem;
import net.neferett.linaris.utils.gui.GuiManager;
import net.neferett.linaris.lobby.inventories.Ranks;

public class RankItem extends MenuItem {

	public static int			id			= -1;

	public static SpecialItem get() {
		if (id == -1)id = registerItem(new RankItem());
		return get(id);
	}
	
	public RankItem() {
		super("§6Informations Grades", new ItemStack(Material.SIGN),"§7Accéder aux informations","§7des differents gardes du serveur");
	}

	@Override
	public void inventoryClickEvent(Player player) {
		GuiManager.openGui(new Ranks("Grades", 3, player, false));
	}
	
}
