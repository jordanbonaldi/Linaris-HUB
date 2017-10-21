package net.neferett.linaris.lobby.items.mainmenu;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import net.neferett.linaris.specialitems.MenuItem;
import net.neferett.linaris.specialitems.SpecialItem;
import net.neferett.linaris.lobby.Lobby;

public class spawn extends MenuItem {

	public static int			id			= -1;

	public static SpecialItem get() {
		if (id == -1)id = registerItem(new spawn());
		return get(id);
	}
	
	public spawn() {
		super("§6Spawn", new ItemStack(Material.BED),"§7Retourner au spawn");
	}

	@Override
	public void inventoryClickEvent(Player player) {
		player.teleport(Lobby.loc);
	}
	
}
