package net.neferett.linaris.lobby.items.epicchest.agmmf;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import net.neferett.linaris.specialitems.MenuItem;
import net.neferett.linaris.specialitems.SpecialItem;

public class PaintballItem extends MenuItem {

	public static int			id			= -1;

	public static SpecialItem get() {
		if (id == -1)id = registerItem(new PaintballItem());
		return get(id);
	}
	
	@SuppressWarnings("deprecation")
	public PaintballItem() {
		super("§aP§ba§ci§dn§et§ab§ba§cl§dl §e!", new ItemStack(35,1,(short) 14),"§6Redécorez le hub !","§cRéservé aux §aEpicVIP");
	}

	@Override
	public void inventoryClickEvent(Player player) {
		player.sendMessage("§6Helmet");
	}
	
}
