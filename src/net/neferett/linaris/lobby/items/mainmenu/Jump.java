package net.neferett.linaris.lobby.items.mainmenu;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import net.neferett.linaris.specialitems.MenuItem;
import net.neferett.linaris.specialitems.SpecialItem;
import net.neferett.linaris.lobby.Lobby;

public class Jump extends MenuItem {

	public static int			id			= -1;

	public static SpecialItem get() {
		if (id == -1)id = registerItem(new Jump());
		return get(id);
	}
	
	public Jump() {
		super("§6Jump", new ItemStack(Material.FEATHER),"§7Arrivez au sommet sans tricher", "§7pour avoir une surprise :)");
	}

	@Override
	public void inventoryClickEvent(Player player) {
		player.teleport(Lobby.getInstance().getJumpsManager().getBaseLocation());
	}
	
}
