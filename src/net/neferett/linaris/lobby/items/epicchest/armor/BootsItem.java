package net.neferett.linaris.lobby.items.epicchest.armor;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import net.neferett.linaris.specialitems.MenuItem;
import net.neferett.linaris.specialitems.SpecialItem;
import net.neferett.linaris.utils.gui.GuiManager;
import net.neferett.linaris.lobby.inventories.epicchest.ArmorsBootsGui;

public class BootsItem extends MenuItem {

	public static int			id			= -1;

	public static SpecialItem get() {
		if (id == -1)id = registerItem(new BootsItem());
		return get(id);
	}
	
	public BootsItem() {
		super("§aBottes", new ItemStack(Material.LEATHER_BOOTS),"");
	}

	@Override
	public void inventoryClickEvent(Player player) {
		GuiManager.openGui(new ArmorsBootsGui(player));
	}
	
}
