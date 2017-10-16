package net.neferett.linaris.lobby.inventories;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import net.neferett.linaris.api.Games;
import net.neferett.linaris.specialitems.SpecialItem;
import net.neferett.linaris.utils.gui.GuiManager;
import net.neferett.linaris.utils.gui.GuiScreen;
import net.neferett.linaris.lobby.games.GamesManager;
import net.neferett.linaris.lobby.inventories.epicchest.ArmorsBootsGui;
import net.neferett.linaris.lobby.inventories.epicchest.ArmorsChestplateGui;
import net.neferett.linaris.lobby.inventories.epicchest.ArmorsHelmetGui;
import net.neferett.linaris.lobby.inventories.epicchest.ArmorsLeggingsGui;
import net.neferett.linaris.lobby.inventories.epicchest.MetamorphosesGui;
import net.neferett.linaris.lobby.inventories.epicchest.MountsGui;
import net.neferett.linaris.lobby.inventories.epicchest.PetsGui;
import net.neferett.linaris.lobby.items.epicchest.agmmf.FamiliersItem;
import net.neferett.linaris.lobby.items.epicchest.agmmf.MetamorphosesItem;
import net.neferett.linaris.lobby.items.epicchest.agmmf.MonturesItem;
import net.neferett.linaris.lobby.items.epicchest.armor.BootsItem;
import net.neferett.linaris.lobby.items.epicchest.armor.ChestplateItem;
import net.neferett.linaris.lobby.items.epicchest.armor.HelmetItem;
import net.neferett.linaris.lobby.items.epicchest.armor.LeggingsItem;
import net.neferett.linaris.lobby.items.epicchest.kits.KitPVPSWAPItem;
import net.neferett.linaris.lobby.items.epicchest.kits.KitRush;
import net.neferett.linaris.lobby.items.epicchest.kits.KitTowers;
import net.neferett.linaris.lobby.items.epicchest.kits.KitsSkyWars;
import net.neferett.linaris.lobby.items.epicchest.misc.RanksItem;
import net.neferett.linaris.lobby.utils.ItemBuilder;
import net.neferett.linaris.lobby.utils.NBTItem;

public class EpicChestInventory extends GuiScreen {

	GuiScreen lastGui;
	
	public EpicChestInventory(Player p,GuiScreen lastScreen) {
		super("Boite magique", 4, p, false);
		this.lastGui = lastScreen;
		build();
	}

	public void drawScreen() {

		addItem(1, HelmetItem.get(), 1, 2);
		addItem(2, ChestplateItem.get(), 2, 2);
		addItem(3, LeggingsItem.get(), 3, 2);
		addItem(4, BootsItem.get(), 4, 2);
		//addItem(5, AnimationsItem.get(), 1, 3);
		//addItem(6, GadgetsItem.get(), 2, 3);
		addItem(7, MonturesItem.get(), 3, 3);
		addItem(8, MetamorphosesItem.get(), 2, 1);
		addItem(9, FamiliersItem.get(), 3, 1);

		//addItem(10, KitFKItem.get(), 1, 7);
		addItem(11, KitsSkyWars.get(), 1, 7);
		addItem(12, KitPVPSWAPItem.get(), 1, 8);
		addItem(14, KitRush.get(), 1, 9);
		addItem(16, KitTowers.get(), 2, 7);


		addItem(13, RanksItem.get(), 2, 5);
		//addItem(14, LinkItem.get(), 3, 5);

		setItemLine(15, new ItemBuilder(Material.ARROW).setTitle("§fRevenir en arrière").build(), 4, 9);

	}

	public void setItemLine(int id, ItemStack item, int line, int slot) {
		super.setItemLine(new NBTItem(item).setInteger("itemID", id).getItem(), line, slot);
	}

	public void addItem(int id, SpecialItem specialItemId, int row, int slot) {
		setItemLine(new NBTItem(specialItemId.getStaticItem()).setInteger("itemID", id).getItem(), row, slot);
	}

	@Override
	public void onOpen() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onClick(ItemStack item, InventoryClickEvent event) {

		event.setCancelled(true);


		if (item.getType() == Material.ARROW) {
			if (lastGui != null) {
				getPlayer().closeInventory();
				GuiManager.openGui(lastGui);
			} else
				getPlayer().closeInventory();
			
			return;
		}
		
		NBTItem nbt = new NBTItem(item);
		if (!nbt.hasKey("itemID")) {
			return;
		}

		int itemID = nbt.getInteger("itemID");
		
		if (itemID == 0) {
			return;
		}
		if (itemID == 1) {
			GuiManager.openGui(new ArmorsHelmetGui(getPlayer()));
			return;
		}
		if (itemID == 2) {

			GuiManager.openGui(new ArmorsChestplateGui(getPlayer()));
			return;
		}
		if (itemID == 3) {

			GuiManager.openGui(new ArmorsLeggingsGui(getPlayer()));
			return;
		}
		if (itemID == 4) {
			GuiManager.openGui(new ArmorsBootsGui(getPlayer()));
			return;
		}
		if (itemID == 5) {
			return;
		}
		if (itemID == 6) {

			return;
		}
		if (itemID == 7) {
			GuiManager.openGui(new MountsGui(getPlayer()));
			return;
		}
		if (itemID == 8) {
			GuiManager.openGui(new MetamorphosesGui(getPlayer()));
			return;
		}
		if (itemID == 9) {
			GuiManager.openGui(new PetsGui(getPlayer()));
			return;
		}
		if (itemID == 10) {
			GuiManager.openGui(new GuiShopGame(GamesManager.getInstance().getGame(Games.FALLENKINGDOMS), getPlayer(), this));
			return;
		}
		if (itemID == 11) {
			GuiManager.openGui(new GuiShopGame(GamesManager.SKYWARS, getPlayer(), this));
			return;
		}
		if (itemID == 12) {
			GuiManager.openGui(new GuiShopGame(GamesManager.getInstance().getGame(Games.PVPSWAP), getPlayer(), this));
			return;
		}
		if (itemID == 14) {
			GuiManager.openGui(new GuiShopGame(GamesManager.getInstance().getGame(Games.RUSH), getPlayer(), this));
			return;
		}
		if (itemID == 16) {
			GuiManager.openGui(new GuiShopGame(GamesManager.getInstance().getGame(Games.TOWERS), getPlayer(), this));
			return;
		}
		if (itemID == 13) {
			GuiManager.openGui(new RankAndBoosterInventory(getPlayer(), this));
			return;
		}
		
		if (itemID == 17) {
			GuiManager.openGui(new GuiShopGame(GamesManager.getInstance().getGame(Games.SpeedUHC), getPlayer(), this));
			return;
		}
	}

	@Override
	public void onClose() {
		// TODO Auto-generated method stub

	}

}
