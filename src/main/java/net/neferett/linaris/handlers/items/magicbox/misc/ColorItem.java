package net.neferett.linaris.handlers.items.magicbox.misc;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import net.neferett.linaris.BukkitAPI;
import net.neferett.linaris.logo.gui.color.ColorShop;
import net.neferett.linaris.mistery.GlowUtils;
import net.neferett.linaris.specialitems.MenuItem;
import net.neferett.linaris.specialitems.SpecialItem;
import net.neferett.linaris.utils.gui.GuiManager;

public class ColorItem extends MenuItem {

	public static int id = -1;

	public static SpecialItem get() {
		if (id == -1)
			id = registerItem(new ColorItem());
		return get(id);
	}

	public ColorItem() {
		super("§eColor", GlowUtils.addGlow(new ItemStack(Material.ANVIL)),
				"§7Envie de changer vos couleurs dans le chat ?");
	}

	@Override
	public void inventoryClickEvent(final Player player) {
		GuiManager.openGui(
				new ColorShop(BukkitAPI.get().getPlayerDataManager().getPlayerData(player.getName()), player.getName()))
				.open();
	}

}
