package net.neferett.linaris.lobby.items.epicchest.agmmf;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import net.neferett.linaris.specialitems.MenuItem;
import net.neferett.linaris.specialitems.SpecialItem;

public class FamiliersItem extends MenuItem {

	public static int			id			= -1;

	public static SpecialItem get() {
		if (id == -1)id = registerItem(new FamiliersItem());
		return get(id);
	}
	
	public FamiliersItem() {
		super("§aFamiliers", new ItemStack(Material.DRAGON_EGG),"§7Des amis pour la vie !","");
	}

	@Override
	public void inventoryClickEvent(Player player) {
		player.sendMessage("§6Helmet");
	}
	
}
