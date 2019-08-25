package net.neferett.linaris.handlers.inventories.magicbox;

import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import net.neferett.linaris.api.Games;
import net.neferett.linaris.api.SettingsManager;
import net.neferett.linaris.mistery.MysteryItem;
import net.neferett.linaris.mistery.MysteryItemsManager;
import net.neferett.linaris.mistery.PetsUtils;
import net.neferett.linaris.utils.ItemStackUtils;
import net.neferett.linaris.utils.gui.GuiManager;
import net.neferett.linaris.utils.gui.GuiScreen;
import net.neferett.linaris.handlers.inventories.MagicBoxInventory;
import net.neferett.linaris.utils.ItemBuilder;
import net.neferett.linaris.utils.NBTItem;

public class PetsGui extends GuiScreen {

	private HashMap<ItemStack, MysteryItem> itemstacks;
	
	public PetsGui(Player p) {
		super("Familiers", 4, p, false);
		this.itemstacks = new HashMap<>();
		build();
	}
	
	public HashMap<ItemStack, MysteryItem> getItemstacks() {
		return itemstacks;
	}

	@Override
	public void drawScreen() {
		
		
		setItemLine(ItemStackUtils.createRenamedItemStack(Material.BARRIER, 1, (short)0, "§fRetirer", null), 1, 5);
		
		setItemShop(PetsUtils.Wither.getItem(), 2, 4);
		setItemShop(PetsUtils.Villager.getItem(), 2, 5);
		setItemShop(PetsUtils.Lapin.getItem(), 2, 6);

		setItemShop(PetsUtils.Cow.getItem(), 3, 2);
		setItemShop(PetsUtils.MooshroomCow.getItem(), 3, 3);
		setItemShop(PetsUtils.Wolf.getItem(), 3, 4);
		setItemShop(PetsUtils.Chat.getItem(), 3, 5);
		setItemShop(PetsUtils.Sheep.getItem(), 3, 6);
		setItemShop(PetsUtils.Chicken.getItem(), 3, 7);
		setItemShop(PetsUtils.Bat.getItem(), 3, 8);
		setItemShop(PetsUtils.Pig.getItem(), 4, 2);
		setItemShop(PetsUtils.EnderMite.getItem(), 4, 3);
		setItemShop(PetsUtils.SilverFish.getItem(), 4, 4);


		

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
			String metaID = SettingsManager.getSetting(getPlayer().getName(), Games.LOBBY,  "pet");
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
