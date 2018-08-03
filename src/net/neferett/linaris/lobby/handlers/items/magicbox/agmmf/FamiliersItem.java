package net.neferett.linaris.lobby.handlers.items.magicbox.agmmf;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import net.neferett.linaris.lobby.handlers.inventories.magicbox.PetsGui;
import net.neferett.linaris.mistery.GlowUtils;
import net.neferett.linaris.specialitems.MenuItem;
import net.neferett.linaris.specialitems.SpecialItem;
import net.neferett.linaris.utils.gui.GuiManager;

public class FamiliersItem extends MenuItem {

	public static int id = -1;

	public static SpecialItem get() {
		if (id == -1)
			id = registerItem(new FamiliersItem());
		return get(id);
	}

	public FamiliersItem() {
		super("§aFamiliers", GlowUtils.addGlow(new ItemStack(Material.DRAGON_EGG)), "§7Des amis pour la vie !", "");
	}

	@Override
	public void inventoryClickEvent(final Player player) {
		GuiManager.openGui(new PetsGui(player)).open();
	}

}
