package net.neferett.linaris.lobby.handlers.inventories.epicchest;

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
import net.neferett.linaris.lobby.handlers.inventories.EpicChestInventory;
import net.neferett.linaris.lobby.utils.ItemBuilder;
import net.neferett.linaris.lobby.utils.NBTItem;

public class ArmorsLeggingsGui extends GuiScreen {

	private HashMap<ItemStack, MysteryItem> itemstacks;
	
	public ArmorsLeggingsGui(Player p) {
		super("Jabmières", 5, p, false);
		this.itemstacks = new HashMap<>();
		build();
	}
	
	public HashMap<ItemStack, MysteryItem> getItemstacks() {
		return itemstacks;
	}

	@Override
	public void drawScreen() {
		
		
		setItemLine(ItemStackUtils.createRenamedItemStack(Material.BARRIER, 1, (short)0, "§fRetirer", null), 1, 5);
		
		
		setItemShop(OresUtils.FER.getLeggings(), 2, 3);
		setItemShop(OresUtils.OR.getLeggings(), 2, 4);
		
		setItemShop(OresUtils.MAILLE.getLeggings(), 2, 6);
		setItemShop(OresUtils.DIAMANT.getLeggings(), 2, 7);
		
		
		setItemShop(ColorUtils.NORMAL.getLeggings(), 3, 1);
		setItemShop(ColorUtils.DARK_AQUA.getLeggings(), 3, 2);
		setItemShop(ColorUtils.BLACK.getLeggings(), 3, 3);
		setItemShop(ColorUtils.BLUE.getLeggings(), 3, 4);
		setItemShop(ColorUtils.LIGHT_PURPLE.getLeggings(), 3, 5);
		setItemShop(ColorUtils.GRAY.getLeggings(), 3, 6);
		setItemShop(ColorUtils.DARK_GREEN.getLeggings(), 3, 7);
		setItemShop(ColorUtils.GREEN.getLeggings(), 3, 8);
		setItemShop(ColorUtils.RED.getLeggings(), 3, 9);
		
		
		setItemShop(ColorUtils.DARK_BLUE.getLeggings(), 4, 1);
		setItemShop(ColorUtils.OLIVE.getLeggings(), 4, 2);
		setItemShop(ColorUtils.GOLD.getLeggings(), 4, 3);
		setItemShop(ColorUtils.DARK_PURPLE.getLeggings(), 4, 4);
		setItemShop(ColorUtils.DARK_RED.getLeggings(), 4, 5);
		setItemShop(ColorUtils.DARK_GRAY.getLeggings(), 4, 6);
		setItemShop(ColorUtils.TEAL.getLeggings(), 4, 7);
		setItemShop(ColorUtils.WHITE.getLeggings(), 4, 8);
		setItemShop(ColorUtils.YELLOW.getLeggings(), 4, 9);
		

		setItemLine(15, new ItemBuilder(Material.ARROW).setTitle("§fRevenir en arrière").build(), 5, 9);
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
			GuiManager.openGui(new EpicChestInventory(getPlayer(), null));
			return;
		}
		if (item.getType() == Material.BARRIER) {
			String bootsID = SettingsManager.getSetting(getPlayer().getName(), Games.LOBBY, "armors-leggings");
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
