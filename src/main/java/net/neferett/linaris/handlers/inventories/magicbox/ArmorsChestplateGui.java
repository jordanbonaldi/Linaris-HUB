package net.neferett.linaris.handlers.inventories.magicbox;

import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import net.neferett.linaris.api.Games;
import net.neferett.linaris.api.SettingsManager;
import net.neferett.linaris.mistery.ColorUtils;
import net.neferett.linaris.mistery.MysteryItem;
import net.neferett.linaris.mistery.MysteryItemsManager;
import net.neferett.linaris.mistery.OresUtils;
import net.neferett.linaris.utils.ItemStackUtils;
import net.neferett.linaris.utils.gui.GuiManager;
import net.neferett.linaris.utils.gui.GuiScreen;
import net.neferett.linaris.handlers.inventories.MagicBoxInventory;
import net.neferett.linaris.utils.ItemBuilder;
import net.neferett.linaris.utils.NBTItem;

public class ArmorsChestplateGui extends GuiScreen {

	private HashMap<ItemStack, MysteryItem> itemstacks;
	
	public ArmorsChestplateGui(Player p) {
		super("Plastrons", 5, p, false);
		this.itemstacks = new HashMap<>();
		build();
	}
	
	public HashMap<ItemStack, MysteryItem> getItemstacks() {
		return itemstacks;
	}

	@Override
	public void drawScreen() {
		
		
		setItemLine(ItemStackUtils.createRenamedItemStack(Material.BARRIER, 1, (short)0, "§fRetirer", null), 1, 5);
		
		
		setItemShop(OresUtils.FER.getChestplate(), 2, 3);
		setItemShop(OresUtils.OR.getChestplate(), 2, 4);
		
		setItemShop(OresUtils.MAILLE.getChestplate(), 2, 6);
		setItemShop(OresUtils.DIAMANT.getChestplate(), 2, 7);
		
		
		setItemShop(ColorUtils.NORMAL.getChestplate(), 3, 1);
		setItemShop(ColorUtils.DARK_AQUA.getChestplate(), 3, 2);
		setItemShop(ColorUtils.BLACK.getChestplate(), 3, 3);
		setItemShop(ColorUtils.BLUE.getChestplate(), 3, 4);
		setItemShop(ColorUtils.LIGHT_PURPLE.getChestplate(), 3, 5);
		setItemShop(ColorUtils.GRAY.getChestplate(), 3, 6);
		setItemShop(ColorUtils.DARK_GREEN.getChestplate(), 3, 7);
		setItemShop(ColorUtils.GREEN.getChestplate(), 3, 8);
		setItemShop(ColorUtils.RED.getChestplate(), 3, 9);
		
		
		setItemShop(ColorUtils.DARK_BLUE.getChestplate(), 4, 1);
		setItemShop(ColorUtils.OLIVE.getChestplate(), 4, 2);
		setItemShop(ColorUtils.GOLD.getChestplate(), 4, 3);
		setItemShop(ColorUtils.DARK_PURPLE.getChestplate(), 4, 4);
		setItemShop(ColorUtils.DARK_RED.getChestplate(), 4, 5);
		setItemShop(ColorUtils.DARK_GRAY.getChestplate(), 4, 6);
		setItemShop(ColorUtils.TEAL.getChestplate(), 4, 7);
		setItemShop(ColorUtils.WHITE.getChestplate(), 4, 8);
		setItemShop(ColorUtils.YELLOW.getChestplate(), 4, 9);
		

		setItemLine(15, new ItemBuilder(Material.ARROW).setTitle("§fRevenir en arri§re").build(), 5, 9);
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
			String bootsID = SettingsManager.getSetting(getPlayer().getName(), Games.LOBBY, "armors-chestplate");
			if (bootsID != null) {
				MysteryItem mitem = MysteryItemsManager.getInstance().getMysteryItem(bootsID);
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
