package net.neferett.linaris.lobby.handlers.inventories;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import net.neferett.linaris.utils.gui.GuiManager;
import net.neferett.linaris.utils.gui.GuiScreen;
import net.neferett.linaris.lobby.handlers.games.Game;
import net.neferett.linaris.lobby.handlers.shop.ShopItem;
import net.neferett.linaris.lobby.utils.ItemBuilder;
import net.neferett.linaris.lobby.utils.NBTItem;

public class GuiShopGame extends GuiScreen {

	GuiScreen lastGui;
	
	public GuiShopGame(Game game,Player p,GuiScreen lastScreen) {
		super(game.getShopName(), 2, p, false);
		this.game = game;
		this.lastGui = lastScreen;
		build();
	}
	
	Game game;

	@Override
	public void drawScreen() {
		clearInventory();
		setItemLine(new ItemBuilder(Material.ARROW).setTitle("§fRevenir en arrière").build(), 2, 9);
		
		if (game.getItems().isEmpty()) return;
		for (ShopItem item : game.getItems()) {
			addItem(item);
		}
	}
	
	public void addItem(ShopItem item) {
		addItem(new NBTItem(item.getItemUI(getPlayer())).setString("itemID", item.getUUID()).getItem());
	}

	@Override
	public void onClick(ItemStack item, InventoryClickEvent event) {

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
			getPlayer().closeInventory();
			return;
		}
		
		String itemID = nbt.getString("itemID");
		
		ShopItem shopItem = game.getItem(itemID);
		
		if (shopItem == null) {
			getPlayer().closeInventory();
			return;
		}
		
		ClickType action = event.getClick();
		
		if (action == ClickType.LEFT) {
			shopItem.useOrBuy(getPlayer(), this);
			return;
		}
		
		if (action == ClickType.RIGHT) {
			
			GuiManager.openGui(new GuiItemView(game, shopItem, getPlayer(), this));
			
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

}
