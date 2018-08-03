package net.neferett.linaris.lobby.handlers.items.magicbox.misc;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import net.neferett.linaris.BukkitAPI;
import net.neferett.linaris.logo.gui.color.ColorShop;
import net.neferett.linaris.mistery.GlowUtils;
import net.neferett.linaris.specialitems.MenuItem;
import net.neferett.linaris.specialitems.SpecialItem;
import net.neferett.linaris.utils.gui.GuiManager;

public class LogoItem extends MenuItem {

	public static int id = -1;

	public static SpecialItem get() {
		if (id == -1)
			id = registerItem(new LogoItem());
		return get(id);
	}

	public LogoItem() {
		super("§aLogo", GlowUtils.addGlow(new ItemStack(Material.APPLE)), "§7Envie de changer le look de votre chat ?");
	}

	@Override
	public void inventoryClickEvent(final Player player) {
		GuiManager.openGui(
				new ColorShop(BukkitAPI.get().getPlayerDataManager().getPlayerData(player.getName()), player.getName()))
				.open();
	}

}
