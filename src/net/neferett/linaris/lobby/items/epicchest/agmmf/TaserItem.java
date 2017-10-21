package net.neferett.linaris.lobby.items.epicchest.agmmf;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import net.neferett.linaris.specialitems.MenuItem;
import net.neferett.linaris.specialitems.SpecialItem;

public class TaserItem extends MenuItem {

	public static int			id			= -1;

	public static SpecialItem get() {
		if (id == -1)id = registerItem(new TaserItem());
		return get(id);
	}
	
	public TaserItem() {
		super("§fGuardian Shot", new ItemStack(Material.PRISMARINE_SHARD),"§7Tasez les tous !","§cRéservé aux §aEpicVIP");
	}

	@Override
	public void inventoryClickEvent(Player player) {
		player.sendMessage("§6Helmet");
	}
	
}
