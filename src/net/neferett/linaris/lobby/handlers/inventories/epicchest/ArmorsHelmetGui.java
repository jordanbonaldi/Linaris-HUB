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

public class ArmorsHelmetGui extends GuiScreen {

	private HashMap<ItemStack, MysteryItem> itemstacks;
	
	public ArmorsHelmetGui(Player p) {
		super("Chapeaux", 4, p, false);
		this.itemstacks = new HashMap<>();
		build();
	}
	
	public HashMap<ItemStack, MysteryItem> getItemstacks() {
		return itemstacks;
	}

	@Override
	public void drawScreen() {
		
		
		setItemLine(ItemStackUtils.createRenamedItemStack(Material.BARRIER, 1, (short)0, "§fRetirer", null), 1, 5);
		
		
		setItemShop(OresUtils.FER.getHelmet(), 1, 3);
		setItemShop(OresUtils.OR.getHelmet(), 1, 4);
		
		setItemShop(OresUtils.MAILLE.getHelmet(), 1, 6);
		setItemShop(OresUtils.DIAMANT.getHelmet(), 1, 7);
		
		
		setItemShop(ColorUtils.NORMAL.getHelmet(), 2, 1);
		setItemShop(ColorUtils.DARK_AQUA.getHelmet(), 2, 2);
		setItemShop(ColorUtils.BLACK.getHelmet(), 2, 3);
		setItemShop(ColorUtils.BLUE.getHelmet(), 2, 4);
		setItemShop(ColorUtils.LIGHT_PURPLE.getHelmet(), 2, 5);
		setItemShop(ColorUtils.GRAY.getHelmet(), 2, 6);
		setItemShop(ColorUtils.DARK_GREEN.getHelmet(), 2, 7);
		setItemShop(ColorUtils.GREEN.getHelmet(), 2, 8);
		setItemShop(ColorUtils.RED.getHelmet(), 2, 9);
		
		
		setItemShop(ColorUtils.DARK_BLUE.getHelmet(), 3, 1);
		setItemShop(ColorUtils.OLIVE.getHelmet(), 3, 2);
		setItemShop(ColorUtils.GOLD.getHelmet(), 3, 3);
		setItemShop(ColorUtils.DARK_PURPLE.getHelmet(), 3, 4);
		setItemShop(ColorUtils.DARK_RED.getHelmet(), 3, 5);
		setItemShop(ColorUtils.DARK_GRAY.getHelmet(), 3, 6);
		setItemShop(ColorUtils.TEAL.getHelmet(), 3, 7);
		setItemShop(ColorUtils.WHITE.getHelmet(), 3, 8);
		setItemShop(ColorUtils.YELLOW.getHelmet(), 3, 9);
		


		setItemLine(15, new ItemBuilder(Material.ARROW).setTitle("§fRevenir en arrière").build(), 4, 9);
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
			String bootsID = SettingsManager.getSetting(getPlayer().getName(), Games.LOBBY, "armors-helmet");
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
