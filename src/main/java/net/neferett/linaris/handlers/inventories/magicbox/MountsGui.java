package net.neferett.linaris.handlers.inventories.magicbox;

import java.util.HashMap;

import net.neferett.linaris.handlers.inventories.MagicBoxInventory;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import net.neferett.linaris.api.Games;
import net.neferett.linaris.api.SettingsManager;
import net.neferett.linaris.mistery.MountsUtils;
import net.neferett.linaris.mistery.MysteryItem;
import net.neferett.linaris.mistery.MysteryItemsManager;
import net.neferett.linaris.utils.ItemStackUtils;
import net.neferett.linaris.utils.gui.GuiManager;
import net.neferett.linaris.utils.gui.GuiScreen;
import net.neferett.linaris.utils.ItemBuilder;
import net.neferett.linaris.utils.NBTItem;

public class MountsGui extends GuiScreen {

	private HashMap<ItemStack, MysteryItem> itemstacks;
	
	public MountsGui(Player p) {
		super("Montures", 4, p, false);
		this.itemstacks = new HashMap<>();
		build();
	}
	
	public HashMap<ItemStack, MysteryItem> getItemstacks() {
		return itemstacks;
	}

	@Override
	public void drawScreen() {
		
		
		setItemLine(ItemStackUtils.createRenamedItemStack(Material.BARRIER, 1, (short)0, "§fRetirer", null), 1, 5);
		
		setItemShop(MountsUtils.Wither.getItem(), 2, 3);
		setItemShop(MountsUtils.SKELETONHORSE.getItem(), 2, 4);
		setItemShop(MountsUtils.ZOMBIEHORSE.getItem(), 2, 5);
		setItemShop(MountsUtils.Blaze.getItem(), 2, 6);
		setItemShop(MountsUtils.Sheep.getItem(), 2, 7);
		
		setItemShop(MountsUtils.HORSE.getItem(), 3, 2);
		setItemShop(MountsUtils.Spider.getItem(), 3, 3);
		setItemShop(MountsUtils.CaveSpider.getItem(), 3, 4);
		setItemShop(MountsUtils.Cow.getItem(), 3, 5);
		setItemShop(MountsUtils.Golem.getItem(), 3, 6);
		setItemShop(MountsUtils.Dog.getItem(), 3, 7);
		setItemShop(MountsUtils.Pig.getItem(), 3, 8);
		setItemShop(MountsUtils.Chicken.getItem(), 4, 2);
		setItemShop(MountsUtils.Rabbit.getItem(), 4, 3);
		setItemShop(MountsUtils.Slime.getItem(), 4, 4);







		setItemLine(15, new ItemBuilder(Material.ARROW).setTitle("§fRevenir en arri§re").build(), 4, 9);
	}
	
	public void setItemLine(int id, ItemStack item, int line, int slot) {
		super.setItemLine(new NBTItem(item).setInteger("itemID", id).getItem(), line, slot);
	}
	
	public void setItemShop(MysteryItem item,int line, int slot) {
		ItemStack i =  item.getItemUI(getPlayer());
		itemstacks.put(i, item);
		setItemLine(i, line, slot);
		
	}

	@Override
	public void onOpen() {
		
		
	}

	@Override
	public void onClick(ItemStack item, InventoryClickEvent event) {
		NBTItem nbt = new NBTItem(item);
//		if (!nbt.hasKey("itemID")) {
//			return;
//		}
		int itemID = nbt.getInteger("itemID");
		if (itemID == 15){
			GuiManager.openGui(new MagicBoxInventory(getPlayer(), null));
			return;
		}
		if (item.getType() == Material.BARRIER) {
			String metaID = SettingsManager.getSetting(getPlayer().getName(), Games.LOBBY,  "mount");
			if (metaID != null) {
				MysteryItem mitem = MysteryItemsManager.getInstance().getMysteryItem(metaID);
				if (mitem != null)
					mitem.onRemove(getPlayer());
			}
		}
		
		if (itemstacks.containsKey(item)) {
			MysteryItem myitem = itemstacks.get(item);
			if (myitem == null) return;
			close();
			myitem.useOrBuy(getPlayer(),this);
		}
		
	}

	@Override
	public void onClose() {
		
		
	}

}
