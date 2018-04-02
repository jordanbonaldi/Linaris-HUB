package net.neferett.linaris.lobby.handlers.inventories;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import net.neferett.linaris.api.Games;
import net.neferett.linaris.lobby.handlers.games.GamesManager;
import net.neferett.linaris.lobby.handlers.inventories.magicbox.ArmorsBootsGui;
import net.neferett.linaris.lobby.handlers.inventories.magicbox.ArmorsChestplateGui;
import net.neferett.linaris.lobby.handlers.inventories.magicbox.ArmorsHelmetGui;
import net.neferett.linaris.lobby.handlers.inventories.magicbox.ArmorsLeggingsGui;
import net.neferett.linaris.lobby.handlers.inventories.magicbox.MetamorphosesGui;
import net.neferett.linaris.lobby.handlers.inventories.magicbox.MountsGui;
import net.neferett.linaris.lobby.handlers.inventories.magicbox.PetsGui;
import net.neferett.linaris.lobby.handlers.items.magicbox.agmmf.FamiliersItem;
import net.neferett.linaris.lobby.handlers.items.magicbox.agmmf.MetamorphosesItem;
import net.neferett.linaris.lobby.handlers.items.magicbox.agmmf.MonturesItem;
import net.neferett.linaris.lobby.handlers.items.magicbox.armor.BootsItem;
import net.neferett.linaris.lobby.handlers.items.magicbox.armor.ChestplateItem;
import net.neferett.linaris.lobby.handlers.items.magicbox.armor.HelmetItem;
import net.neferett.linaris.lobby.handlers.items.magicbox.armor.LeggingsItem;
import net.neferett.linaris.lobby.handlers.items.magicbox.kits.KitRush;
import net.neferett.linaris.lobby.handlers.items.magicbox.kits.KitTowers;
import net.neferett.linaris.lobby.handlers.items.magicbox.kits.KitsSkyWars;
import net.neferett.linaris.lobby.handlers.items.magicbox.misc.RanksItem;
import net.neferett.linaris.lobby.utils.ItemBuilder;
import net.neferett.linaris.lobby.utils.NBTItem;
import net.neferett.linaris.specialitems.SpecialItem;
import net.neferett.linaris.utils.gui.GuiManager;
import net.neferett.linaris.utils.gui.GuiScreen;

public class MagicBoxInventory extends GuiScreen {

	GuiScreen lastGui;

	public MagicBoxInventory(final Player p, final GuiScreen lastScreen) {
		super("Boite magique", 4, p, false);
		this.lastGui = lastScreen;
		this.build();
	}

	public void addItem(final int id, final SpecialItem specialItemId, final int row, final int slot) {
		this.setItemLine(new NBTItem(specialItemId.getStaticItem()).setInteger("itemID", id).getItem(), row, slot);
	}

	@Override
	public void drawScreen() {

		this.addItem(1, HelmetItem.get(), 1, 2);
		this.addItem(2, ChestplateItem.get(), 2, 2);
		this.addItem(3, LeggingsItem.get(), 3, 2);
		this.addItem(4, BootsItem.get(), 4, 2);
		this.addItem(7, MonturesItem.get(), 3, 3);
		this.addItem(8, MetamorphosesItem.get(), 2, 1);
		this.addItem(9, FamiliersItem.get(), 3, 1);

		this.addItem(11, KitsSkyWars.get(), 1, 7);
		this.addItem(14, KitRush.get(), 1, 9);
		this.addItem(16, KitTowers.get(), 2, 7);

		this.addItem(13, RanksItem.get(), 2, 5);
		// addItem(14, LinkItem.get(), 3, 5);

		this.setItemLine(15, new ItemBuilder(Material.ARROW).setTitle("§fRevenir en arrière").build(), 4, 9);

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

		final NBTItem nbt = new NBTItem(item);
		if (!nbt.hasKey("itemID"))
			return;

		final int itemID = nbt.getInteger("itemID");

		if (itemID == 0)
			return;
		if (itemID == 1) {
			GuiManager.openGui(new ArmorsHelmetGui(this.getPlayer()));
			return;
		}
		if (itemID == 2) {

			GuiManager.openGui(new ArmorsChestplateGui(this.getPlayer()));
			return;
		}
		if (itemID == 3) {

			GuiManager.openGui(new ArmorsLeggingsGui(this.getPlayer()));
			return;
		}
		if (itemID == 4) {
			GuiManager.openGui(new ArmorsBootsGui(this.getPlayer()));
			return;
		}
		if (itemID == 5)
			return;
		if (itemID == 6)
			return;
		if (itemID == 7) {
			GuiManager.openGui(new MountsGui(this.getPlayer()));
			return;
		}
		if (itemID == 8) {
			GuiManager.openGui(new MetamorphosesGui(this.getPlayer()));
			return;
		}
		if (itemID == 9) {
			GuiManager.openGui(new PetsGui(this.getPlayer()));
			return;
		}
		if (itemID == 10) {
			GuiManager.openGui(
					new GuiShopGame(GamesManager.getInstance().getGame(Games.FALLENKINGDOMS), this.getPlayer(), this));
			return;
		}
		if (itemID == 11) {
			GuiManager.openGui(new GuiShopGame(GamesManager.SKYWARS, this.getPlayer(), this));
			return;
		}
		if (itemID == 12) {
			GuiManager.openGui(
					new GuiShopGame(GamesManager.getInstance().getGame(Games.PVPSWAP), this.getPlayer(), this));
			return;
		}
		if (itemID == 14) {
			GuiManager.openGui(new GuiShopGame(GamesManager.getInstance().getGame(Games.RUSH), this.getPlayer(), this));
			return;
		}
		if (itemID == 16) {
			GuiManager
					.openGui(new GuiShopGame(GamesManager.getInstance().getGame(Games.TOWERS), this.getPlayer(), this));
			return;
		}
		if (itemID == 13) {
			GuiManager.openGui(new RankAndBoosterInventory(this.getPlayer(), this));
			return;
		}

		if (itemID == 17) {
			GuiManager.openGui(
					new GuiShopGame(GamesManager.getInstance().getGame(Games.SpeedUHC), this.getPlayer(), this));
			return;
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

}
