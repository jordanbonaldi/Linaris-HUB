package net.neferett.linaris.handlers.items.magicbox.agmmf;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import net.neferett.linaris.specialitems.MenuItem;
import net.neferett.linaris.specialitems.SpecialItem;

public class MonturesItem extends MenuItem {

	public static int			id			= -1;

	public static SpecialItem get() {
		if (id == -1)id = registerItem(new MonturesItem());
		return get(id);
	}
	
	public MonturesItem() {
		super("§aMontures", new ItemStack(Material.SADDLE),"§7Envie de faire une ballade ?","");
	}

	@Override
	public void inventoryClickEvent(Player player) {
		player.sendMessage("§6Helmet");
	}
	
}
