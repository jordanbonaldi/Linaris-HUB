package net.neferett.linaris.lobby.handlers.inventories;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import net.neferett.linaris.utils.gui.GuiManager;
import net.neferett.linaris.utils.gui.GuiScreen;
import net.neferett.linaris.lobby.handlers.games.Game;
import net.neferett.linaris.lobby.items.ProfileItem;
import net.neferett.linaris.lobby.shop.ShopItem;
import net.neferett.linaris.lobby.utils.ItemBuilder;

public class GuiItemView extends GuiScreen {

	GuiScreen lastGui;
	
	public GuiItemView(Game game,ShopItem item,Player p,GuiScreen lastScreen) {
		super(game.getShopName(), 3, p, false);
		this.game = game;
		this.item = item;
		this.lastGui = lastScreen;
		build();
	}
	
	Game game;
	ShopItem item;

	@Override
	public void drawScreen() {
		
		setItemLine(new ProfileItem(getPlayer()).getStaticItem(), 3, 1);
		setItemLine(new ItemBuilder(Material.ARROW).setTitle("§fRevenir en arrière").build(), 3, 9);
		
		if (game.getItems().isEmpty()) return;
		
		setItemLine(item.getItemLevelInfo(getPlayer(),0, item.getLevelInfos().get(0)), 2, 1);
		
		for (int i = 1 ; i < item.getLevelInfos().size() ; i++) {
			setItemLine(item.getItemLevelInfo(getPlayer(),i, item.getLevelInfos().get(i)),2, 2+i);
		}
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
