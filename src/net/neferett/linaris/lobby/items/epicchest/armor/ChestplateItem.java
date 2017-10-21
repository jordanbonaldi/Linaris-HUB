package net.neferett.linaris.lobby.items.epicchest.armor;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import net.neferett.linaris.lobby.handlers.inventories.epicchest.ArmorsChestplateGui;
import net.neferett.linaris.specialitems.MenuItem;
import net.neferett.linaris.specialitems.SpecialItem;
import net.neferett.linaris.utils.gui.GuiManager;

public class ChestplateItem extends MenuItem {

	public static int			id			= -1;

	public static SpecialItem get() {
		if (id == -1)id = registerItem(new ChestplateItem());
		return get(id);
	}
	
	public ChestplateItem() {
		super("§aPlastrons", new ItemStack(Material.LEATHER_CHESTPLATE),"");
	}

	@Override
	public void inventoryClickEvent(Player player) {

		GuiManager.openGui(new ArmorsChestplateGui(player));
	}
	
}
