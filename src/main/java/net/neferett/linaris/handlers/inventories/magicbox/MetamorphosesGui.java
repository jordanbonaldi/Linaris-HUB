package net.neferett.linaris.handlers.inventories.magicbox;

import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import net.neferett.linaris.api.Games;
import net.neferett.linaris.api.SettingsManager;
import net.neferett.linaris.mistery.MetamorphosesUtils;
import net.neferett.linaris.mistery.MysteryItem;
import net.neferett.linaris.mistery.MysteryItemsManager;
import net.neferett.linaris.utils.ItemStackUtils;
import net.neferett.linaris.utils.gui.GuiManager;
import net.neferett.linaris.utils.gui.GuiScreen;
import net.neferett.linaris.handlers.inventories.MagicBoxInventory;
import net.neferett.linaris.utils.ItemBuilder;
import net.neferett.linaris.utils.NBTItem;

public class MetamorphosesGui extends GuiScreen {

	private HashMap<ItemStack, MysteryItem> itemstacks;
	
	public MetamorphosesGui(Player p) {
		super("Metamorphoses", 6, p, false);
		this.itemstacks = new HashMap<>();
		build();
	}
	
	public HashMap<ItemStack, MysteryItem> getItemstacks() {
		return itemstacks;
	}

	@Override
	public void drawScreen() {
		
		
		setItemLine(ItemStackUtils.createRenamedItemStack(Material.BARRIER, 1, (short)0, "§fRetirer", null), 1, 5);
		
		if (SettingsManager.isEnabled(getPlayer().getName(), Games.LOBBY, "metamorphose-viewself",true))
			setItemLine(ItemStackUtils.createRenamedItemStack(Material.EYE_OF_ENDER, 1, (short)0, "§6M§tamorphose §aactiv§e", null), 6, 5);
		else
			setItemLine(ItemStackUtils.createRenamedItemStack(Material.ENDER_PEARL, 1, (short)0, "§6M§tamorphose §cd§sactiv§e", null), 6, 5);
		
		
		setItemShop(MetamorphosesUtils.SHEEP.getItem(), 2, 3);
		setItemShop(MetamorphosesUtils.WOLF.getItem(), 2, 4);
		setItemShop(MetamorphosesUtils.MUSHCOW.getItem(), 2, 5);
		setItemShop(MetamorphosesUtils.POWERCREEPER.getItem(), 2, 6);
		setItemShop(MetamorphosesUtils.GUARDIAN.getItem(), 2, 7);
		
		
		setItemShop(MetamorphosesUtils.SILVERFISH.getItem(), 3, 1);
		setItemShop(MetamorphosesUtils.PIG.getItem(), 3, 2);
		setItemShop(MetamorphosesUtils.COW.getItem(), 3, 3);
		setItemShop(MetamorphosesUtils.SQUID.getItem(), 3, 4);
		setItemShop(MetamorphosesUtils.ENDERMAN.getItem(), 3, 5);
		setItemShop(MetamorphosesUtils.CHICKEN.getItem(), 3, 6);
		setItemShop(MetamorphosesUtils.SPIDER.getItem(), 3, 7);
		setItemShop(MetamorphosesUtils.BLACKSPIDER.getItem(), 3, 8);
		setItemShop(MetamorphosesUtils.CREEPER.getItem(), 3, 9);
		
		setItemShop(MetamorphosesUtils.BLAZE.getItem(), 4, 1);
		setItemShop(MetamorphosesUtils.CAT.getItem(), 4, 2);
		setItemShop(MetamorphosesUtils.HORSE.getItem(), 4, 3);
		setItemShop(MetamorphosesUtils.ZOMBIE.getItem(), 4, 4);
		setItemShop(MetamorphosesUtils.SKELETON.getItem(), 4, 5);
		setItemShop(MetamorphosesUtils.PIGZOMBIE.getItem(), 4, 6);
		setItemShop(MetamorphosesUtils.WITCH.getItem(), 4, 7);
		setItemShop(MetamorphosesUtils.GIANT.getItem(), 4, 8);
		setItemShop(MetamorphosesUtils.BAT.getItem(), 4, 9);
		
		setItemShop(MetamorphosesUtils.WITHER.getItem(), 5, 1);
		setItemShop(MetamorphosesUtils.SLIME.getItem(), 5, 2);
		setItemShop(MetamorphosesUtils.LAVASLIME.getItem(), 5, 3);
		setItemShop(MetamorphosesUtils.SNOWGOLEM.getItem(), 5, 4);
		setItemShop(MetamorphosesUtils.GOLEM.getItem(), 5, 5);
		setItemShop(MetamorphosesUtils.RABBIT.getItem(), 5, 6);
		
		


		setItemLine(15, new ItemBuilder(Material.ARROW).setTitle("§fRevenir en arri§re").build(), 6, 9);
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
		if (item.getType() == Material.EYE_OF_ENDER) {
			String metaID = SettingsManager.getSetting(getPlayer().getName(), Games.LOBBY, "metamorphose");
			SettingsManager.setSetting(getPlayer().getName(), Games.LOBBY,  "metamorphose-viewself",String.valueOf(false));
			if (metaID != null) {
				MysteryItem mitem = MysteryItemsManager.getInstance().getMysteryItem(metaID);
				if (mitem != null) {
					mitem.onUse(getPlayer(),false);
					getPlayer().closeInventory();
				}
			}
		}
		if (item.getType() == Material.ENDER_PEARL) {
			String metaID = SettingsManager.getSetting(getPlayer().getName(), Games.LOBBY, "metamorphose");

			SettingsManager.setSetting(getPlayer().getName(), Games.LOBBY,  "metamorphose-viewself",String.valueOf(true));
			if (metaID != null) {
				MysteryItem mitem = MysteryItemsManager.getInstance().getMysteryItem(metaID);
				if (mitem != null) {
					mitem.onUse(getPlayer(),false);
					getPlayer().closeInventory();
					setItemLine(ItemStackUtils.createRenamedItemStack(Material.EYE_OF_ENDER, 1, (short)0, "§6M§tamorphose §aactiv§e", null), 6, 5);
				}
			}
		}
		if (item.getType() == Material.BARRIER) {
			String metaID = SettingsManager.getSetting(getPlayer().getName(), Games.LOBBY,  "metamorphose");
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
