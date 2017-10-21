package net.neferett.linaris.lobby.items.epicchest.agmmf;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import net.neferett.linaris.lobby.handlers.inventories.epicchest.MetamorphosesGui;
import net.neferett.linaris.specialitems.MenuItem;
import net.neferett.linaris.specialitems.SpecialItem;
import net.neferett.linaris.utils.gui.GuiManager;

public class MetamorphosesItem extends MenuItem {

	public static int			id			= -1;

	public static SpecialItem get() {
		if (id == -1)id = registerItem(new MetamorphosesItem());
		return get(id);
	}
	
	public MetamorphosesItem() {
		super("§aMétamorphoses", new ItemStack(Material.BLAZE_ROD),"§7Changer d'apparence !","");
	}

	@Override
	public void inventoryClickEvent(Player player) {

		GuiManager.openGui(new MetamorphosesGui(player));
	}
	
}
