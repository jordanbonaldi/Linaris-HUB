package net.neferett.linaris.lobby.handlers.inventories;

import java.util.List;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import net.neferett.linaris.BukkitAPI;
import net.neferett.linaris.api.GameServer;
import net.neferett.linaris.api.Games;
import net.neferett.linaris.utils.PlayerUtils;
import net.neferett.linaris.utils.gui.GuiManager;
import net.neferett.linaris.utils.gui.GuiScreen;
import net.neferett.linaris.lobby.utils.ItemBuilder;
import net.neferett.linaris.lobby.utils.NBTItem;

public class ChangeHubInventory extends GuiScreen {

	public ChangeHubInventory(Player p) {
		super("Changer de Hub (" + Integer.parseInt(Bukkit.getServerName().replace("hub", "")) + ")", 6, p, false);
		build();
	}

	@Override
	public void drawScreen() {
		List<GameServer> hubs = BukkitAPI.get().getProxyDataManager().getServersByGameName(Games.LOBBY.getDisplayName());
		List<GameServer> hub = hubs.stream().sorted((p1, p2) -> p1.getServName().replace("hub", "").compareTo(p2.getServName().replace("hub", ""))).collect(Collectors.toList());
		for (GameServer server : hub) {
			if (!server.canJoin() || !server.canSee()) continue;
			int number = Integer.parseInt(server.getServName().replace("hub", ""));
			ItemBuilder ib = new ItemBuilder(Material.WOOL);
			if (server.getPlayers() > 50) {
				ib.setDamage((short) 4);
			} 
			if (server.getPlayers() > 80) {
				ib.setDamage((short) 1);
			}
			if (server.getServName().equals(Bukkit.getServerName())) {
				ib.setDamage((short) 5);
			}
			
			ib.setTitle("§6Hub " + number);
			String addon = "§fFaible";
			if (server.getPlayers() > 30) {
				addon = "§eMoyenne";
			} 
			if (server.getPlayers() > 50) {
				addon = "§6Forte";
			}
			ib.addLores("§e☼ §7Présence §f" + server.getPlayers() + " §7(" + addon + "§7) §e☼");
			ib.setAmount(number);
			addItem(number,ib.build());
		}
		setItemLine(-10, new ItemBuilder(Material.ARROW).setTitle("§fRevenir en arrière").build(), 6, 9);
	}

	public void setItemLine(int id, ItemStack item, int line, int slot) {
		super.setItemLine(new NBTItem(item).setInteger("itemID", id).getItem(), line, slot);
	}
	
	public void addItem(int id, ItemStack item) {
		super.addItem(new NBTItem(item).setInteger("itemID", id).getItem());
	}

	@Override
	public void onOpen() {
	}

	@Override
	public void onClick(ItemStack item, InventoryClickEvent event) {

		event.setCancelled(true);

		NBTItem nbt = new NBTItem(item);
		if (!nbt.hasKey("itemID")) {
			return;
		}

		int itemID = nbt.getInteger("itemID");

		if (itemID == -10){
				GuiManager.openGui(new MainMenuInventory(getPlayer()));
				return;
		}
		GameServer server = getHubWithNumber(itemID);
		if (server == null) {
			getPlayer().closeInventory();
			return;
		}
		
		if (server.getServName().equals(Bukkit.getServerName())) {
			return;
		}
		PlayerUtils.goToServer(getPlayer(), server);
		
		
	}

	@Override
	public void onClose() {

	}
	
	public GameServer getHubWithNumber(int nb) {
		List<GameServer> hubs = BukkitAPI.get().getProxyDataManager().getServersByGameName(Games.LOBBY.getDisplayName());
		for (GameServer server : hubs) {
			int number = Integer.parseInt(server.getServName().replace("hub", ""));
			if (nb == number) return server;
		}
		return null;
	}

}
