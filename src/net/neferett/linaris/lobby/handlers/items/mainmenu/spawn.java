package net.neferett.linaris.lobby.handlers.items.mainmenu;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import net.neferett.linaris.lobby.utils.ConfigDatas;
import net.neferett.linaris.specialitems.MenuItem;
import net.neferett.linaris.specialitems.SpecialItem;

public class spawn extends MenuItem {

	public static int id = -1;

	public static SpecialItem get() {
		if (id == -1)
			id = registerItem(new spawn());
		return get(id);
	}

	public spawn() {
		super("§6Spawn", new ItemStack(Material.BED), "§7Retourner au spawn");
	}

	@Override
	public void inventoryClickEvent(final Player player) {
		player.teleport(ConfigDatas.getInstance().getSpawn());
	}

}
