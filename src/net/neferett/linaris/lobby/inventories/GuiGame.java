package net.neferett.linaris.lobby.inventories;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import net.neferett.linaris.utils.QueueUtils;
import net.neferett.linaris.utils.gui.GuiManager;
import net.neferett.linaris.utils.gui.GuiScreen;
import net.neferett.linaris.lobby.games.Game;
import net.neferett.linaris.lobby.games.Map;
import net.neferett.linaris.lobby.utils.ItemBuilder;
import net.neferett.linaris.lobby.utils.NBTItem;

public class GuiGame extends GuiScreen {

	public GuiGame(Game game, Player p,GuiScreen lastGui) {
		super("Jeux > " + game.getName(), 4, p, true);
		this.game = game;
		this.lastGui = lastGui;
		build();
	}

	GuiScreen lastGui;
	Game game;

	@Override
	public void drawScreen() {
		setItemLine(new ItemBuilder(Material.SIGN).setTitle("§6Lobby du jeu").build(), 1, 4);
		setItemLine(game.getQueueItem(), 1, 5);
		setItemLine(new ItemBuilder(Material.WOOD_DOOR).setTitle("§6Quitter la file d'attente").addLores("§7Vous pouvez quitter la file d'attente",
																										   "§7à tout moment, sauf en groupe où",
																										   "§7seul le chef du groupe a ce pouvoir.").build(), 1, 6);
		setItemLine(new ItemBuilder(Material.EMERALD).setTitle("§6Boutique").addLores("§7La boutique de ce jeu !").build(), 1, 9);
		
		setItemLine(new ItemBuilder(Material.ARROW).setTitle("§fRevenir en arrière").build(), 4, 9);
		
		buildMaps();
	}
	

	public void setMap(Map map, int line, int slot) {
		super.setItemLine(new NBTItem(map.getItemUI()).setString("mapID", map.getName()).getItem(), line, slot);
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
		
		if (item.getType() == Material.SIGN) {
			//getPlayer().teleport(game.getLobby());
			getPlayer().closeInventory();
			return;
		}
		if (item.equals(game.getQueueItem())) {
			QueueUtils.addInQueue(getPlayer(), game.getGame());
			getPlayer().closeInventory();
			return;
		}
		if (item.getType() == Material.WOOD_DOOR) {
			QueueUtils.removeFromQueue(getPlayer());
			getPlayer().closeInventory();
			return;
		}
		if (item.getType() == Material.EMERALD) {
			GuiManager.openGui(new GuiShopGame(game, getPlayer(), this));
			return;
		}
		
		NBTItem nbt = new NBTItem(item);
		if (!nbt.hasKey("mapID")) {
			getPlayer().closeInventory();
			return;
		}
		
		String mapName = nbt.getString("mapID");
		
		Map map = game.getMap(mapName);
		
		if (map == null) {
			getPlayer().closeInventory();
			return;
		}
		
		ClickType action = event.getClick();
		
		if (action == ClickType.LEFT) {
			QueueUtils.addInQueue(getPlayer(), game.getGame(), map.getName());
			getPlayer().closeInventory();
			return;
		}
		
		if (action == ClickType.RIGHT) {
			
			return;
		}

	}

	@Override
	public void onOpen() {
	}

	@Override
	public void onClose() {
	}

	public void buildMaps() {
		List<Map> maps = this.game.getMaps();
		switch (game.getMaps().size()) {
		case 1:

			setMap(maps.get(0), 2, 5);

			break;

		case 2:

			setMap(maps.get(0), 2, 4);
			setMap(maps.get(1), 2, 6);

			break;

		case 3:

			setMap(maps.get(0), 2, 3);
			setMap(maps.get(1), 2, 5);
			setMap(maps.get(2), 2, 7);

			break;

		case 4:

			setMap(maps.get(0), 2, 3);
			setMap(maps.get(1), 2, 4);
			setMap(maps.get(2), 2, 6);
			setMap(maps.get(3), 2, 7);

			break;

		case 5:

			setMap(maps.get(0), 2, 3);
			setMap(maps.get(1), 2, 4);
			setMap(maps.get(2), 2, 5);
			setMap(maps.get(3), 2, 6);
			setMap(maps.get(4), 2, 7);
			break;

		case 6:

			setMap(maps.get(0), 2, 2);
			setMap(maps.get(1), 2, 3);
			setMap(maps.get(2), 2, 4);
			setMap(maps.get(3), 2, 6);
			setMap(maps.get(4), 2, 7);
			setMap(maps.get(5), 2, 8);
			break;

		case 7:

			setMap(maps.get(0), 2, 2);
			setMap(maps.get(1), 2, 3);
			setMap(maps.get(2), 2, 4);
			setMap(maps.get(3), 2, 5);
			setMap(maps.get(4), 2, 6);
			setMap(maps.get(5), 2, 7);
			setMap(maps.get(6), 2, 8);

			break;

		case 8:

			setMap(maps.get(0), 2, 1);
			setMap(maps.get(1), 2, 2);
			setMap(maps.get(2), 2, 3);
			setMap(maps.get(3), 2, 4);
			setMap(maps.get(4), 2, 6);
			setMap(maps.get(5), 2, 7);
			setMap(maps.get(6), 2, 8);
			setMap(maps.get(7), 2, 9);
			break;

		case 9:

			setMap(maps.get(0), 2, 1);
			setMap(maps.get(1), 2, 2);
			setMap(maps.get(2), 2, 3);
			setMap(maps.get(3), 2, 4);
			setMap(maps.get(4), 2, 5);
			setMap(maps.get(5), 2, 6);
			setMap(maps.get(6), 2, 7);
			setMap(maps.get(7), 2, 8);
			setMap(maps.get(8), 2, 9);

			break;

		default:
			break;
		}
	}

}
