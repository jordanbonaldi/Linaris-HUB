package net.neferett.linaris.handlers.items.magicbox.misc;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import net.neferett.linaris.mistery.GlowUtils;
import net.neferett.linaris.specialitems.MenuItem;
import net.neferett.linaris.specialitems.SpecialItem;

public class RanksItem extends MenuItem {

	public static int			id			= -1;

	public static SpecialItem get() {
		if (id == -1)id = registerItem(new RanksItem());
		return get(id);
	}
	
	public RanksItem() {
		super("§aBoosters", GlowUtils.addGlow(new ItemStack(Material.DIAMOND)),"§7Une envie de vitesse ?");
	}

	@Override
	public void inventoryClickEvent(Player player) {

	}
	
}
