package net.neferett.linaris.handlers.inventories;

import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import net.neferett.linaris.BukkitAPI;
import net.neferett.linaris.inventory.InstantShopItem;
import net.neferett.linaris.handlers.shop.ranks.BoosterItem;
import net.neferett.linaris.utils.ItemBuilder;
import net.neferett.linaris.utils.NBTItem;
import net.neferett.linaris.utils.gui.GuiManager;
import net.neferett.linaris.utils.gui.GuiScreen;

public class RankAndBoosterInventory extends GuiScreen {

	private final HashMap<ItemStack, InstantShopItem>	itemstacks;
	GuiScreen											lastGui;

	public RankAndBoosterInventory(final Player p, final GuiScreen lastScreen) {
		super("Boosters", 3, p, false);
		this.lastGui = lastScreen;
		this.itemstacks = new HashMap<>();
		this.build();
	}

	@Override
	public void drawScreen() {

		this.setItemShop(BoosterItem.BOOSTER7, 2, 4);
		this.setItemShop(BoosterItem.BOOSTER15, 2, 5);
		this.setItemShop(BoosterItem.BOOSTER31, 2, 6);

		this.setItemLine(new ItemBuilder(Material.ARROW).setTitle("§fRevenir en arri§re").build(), 3, 9);

	}

	@Override
	public void onClick(final ItemStack item, final InventoryClickEvent event) {

		event.setCancelled(true);

		if (item.getType() == Material.ARROW) {
			if (this.lastGui != null) {
				this.getPlayer().closeInventory();
				GuiManager.openGui(this.lastGui);
			} else
				this.getPlayer().closeInventory();

			return;
		}

		if (this.itemstacks.containsKey(item)) {
			final InstantShopItem myitem = this.itemstacks.get(item);
			if (myitem == null)
				return;
			this.close();
			myitem.useOrBuy(BukkitAPI.get().getPlayerDataManager().getPlayerData(this.getPlayer().getName()), this);
		}
	}

	@Override
	public void onClose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onOpen() {
		// TODO Auto-generated method stub

	}

	public void setItemLine(final int id, final ItemStack item, final int line, final int slot) {
		super.setItemLine(new NBTItem(item).setInteger("itemID", id).getItem(), line, slot);
	}

	public void setItemShop(final InstantShopItem item, final int line, final int slot) {
		final ItemStack i = item.getItemUI(this.getPlayer());
		this.itemstacks.put(i, item);
		this.setItemLine(i, line, slot);

	}

}
