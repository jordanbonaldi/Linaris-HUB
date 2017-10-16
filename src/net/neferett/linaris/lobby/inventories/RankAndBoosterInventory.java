package net.neferett.linaris.lobby.inventories;

import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import net.neferett.linaris.utils.gui.GuiManager;
import net.neferett.linaris.utils.gui.GuiScreen;
import net.neferett.linaris.lobby.shop.InstantShopItem;
import net.neferett.linaris.lobby.shop.ranks.BoosterItem;
import net.neferett.linaris.lobby.utils.ItemBuilder;
import net.neferett.linaris.lobby.utils.NBTItem;

public class RankAndBoosterInventory extends GuiScreen {

	GuiScreen lastGui;
	private HashMap<ItemStack, InstantShopItem> itemstacks;
	
	public RankAndBoosterInventory(Player p,GuiScreen lastScreen) {
		super("Boosters", 3,p,false);
		this.lastGui = lastScreen;
		this.itemstacks = new HashMap<>();
		build();
	}

	@Override
	public void drawScreen() {

//			addItem(0,MiniVIPBuyItem.get(), 2, 4);
//			addItem(1,VIPBuyItem.get(), 2, 5);
//			addItem(2,VIPPlusBuyItem.get(), 2, 6);
//			addItem(3,EpicVIPBuyItem.get(), 1, 5);
//			addItem(4,Booster7Item.get(), 3, 4);
//			addItem(5,Booster15Item.get(), 3, 5);
//			addItem(6,Booster31Item.get(), 3, 6);
			setItemShop(BoosterItem.BOOSTER7, 2, 4);
			setItemShop(BoosterItem.BOOSTER15, 2, 5);
			setItemShop(BoosterItem.BOOSTER31, 2, 6);
			
			/*setItemShop(RankMiniVip.RANK, 2, 4);
			setItemShop(RankVip.RANK, 2, 5);
			setItemShop(RankVipPlus.RANK, 2, 6);
			
			setItemShop(RankEpicVip.RANK, 1, 5);*/


			setItemLine( new ItemBuilder(Material.ARROW).setTitle("§fRevenir en arrière").build(), 3, 9);


	}

	public void setItemLine(int id, ItemStack item, int line, int slot) {
		super.setItemLine(new NBTItem(item).setInteger("itemID", id).getItem(), line, slot);
	}

	public void setItemShop(InstantShopItem item,int line, int slot) {
		ItemStack i =  item.getItemUI(getPlayer());
		itemstacks.put(i, item);
		setItemLine(i, line, slot);
		
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
		
		if (itemstacks.containsKey(item)) {
			InstantShopItem myitem = itemstacks.get(item);
			if (myitem == null) return;
			close();
			myitem.useOrBuy(getPlayer(),this);
		}
	}

	@Override
	public void onClose() {
		// TODO Auto-generated method stub

	}

}
