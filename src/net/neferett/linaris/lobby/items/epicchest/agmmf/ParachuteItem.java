package net.neferett.linaris.lobby.items.epicchest.agmmf;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import net.neferett.linaris.specialitems.MenuItem;
import net.neferett.linaris.specialitems.SpecialItem;

public class ParachuteItem extends MenuItem {

	public static int			id			= -1;

	public static SpecialItem get() {
		if (id == -1)id = registerItem(new ParachuteItem());
		return get(id);
	}
	
	public ParachuteItem() {
		super("§aParachute de poulets !", new ItemStack(Material.FEATHER),"§7Retombez en toute sécurité !","§cRéservé aux §bVIP§e+");
	}

	@Override
	public void inventoryClickEvent(Player player) {
		player.sendMessage("§6Helmet");
	}
	
}
