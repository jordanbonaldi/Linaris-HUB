package net.neferett.linaris.handlers.games;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.neferett.linaris.BukkitAPI;
import net.neferett.linaris.api.Games;
import net.neferett.linaris.handlers.shop.ShopItem;

public class Game {

	String name;
	Games game;
	List<String> description;
	List<Map> maps;
	ItemStack item;
	Location lobby;
	
	List<ShopItem> items;
	String shopName;
	
	
	public Game(String name,Games game,ItemStack item,List<String> description) {
		this.name = name;
		this.game = game;
		this.description = description;
		this.item = item;
		this.maps = new ArrayList<Map>();
		this.items = new ArrayList<ShopItem>();
		this.shopName = name;
	}
	
	public Games getGame() {
		return game;
	}
	
	public List<String> getDescription() {
		return description;
	}
	
	public List<Map> getMaps() {
		return maps;
	}
	
	public Map getMap(String name) {
		if (maps.isEmpty()) return null;
		for (Map map : maps) 
			if (map.getName().equals(name)) return map;	
		return null;
	}
	
	public String getName() {
		return name;
	}
	
	public void registerMap(Map map) {
		map.setOwner(this);
		maps.add(map);
	}
	
	public void setLobby(Location lobby) {
		this.lobby = lobby;
	}
	
	public Location getLobby() {
		return lobby;
	}
	
	public int getPlayers() {
		return BukkitAPI.get().getProxyDataManager().getPlayerInGame(game);
	}
	
	public ShopItem getItem(String id) {
		if (items.isEmpty()) return null;
		for (ShopItem item : items) 
			if (item.getUUID().equals(id)) return item;	
		return null;
	}
	
	public void addItem(ShopItem i) {
		i.setGame(this);
		items.add(i);
	}
	
	public List<ShopItem> getItems() {
		return items;
	}
	
	public String getShopName() {
		return shopName;
	}
	
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	
	public ItemStack getItemUI() {
		ItemStack item = this.item.clone();
		ItemMeta meta = item.getItemMeta();
		
		List<String> strings = new ArrayList<String>();
		strings.addAll(description);
		meta.setLore(strings);
		meta.setDisplayName("§6" + name);
		
		item.setItemMeta(meta);
		
		return item;
	}
	
	public ItemStack getQueueItem() {
		ItemStack item = new ItemStack(Material.EYE_OF_ENDER);
		ItemMeta meta = item.getItemMeta();
		
		List<String> strings = new ArrayList<String>();
		strings.add("net.neferett.linaris7Trouve la première partie libre");
		strings.add("§7sur une carte al§atoirement choisie");
		strings.add("");
		strings.add("§7En jeu §e" + getPlayers() + " §7joueur(s)");
		strings.add("§bAttente : §eAucune");
		meta.setLore(strings);
		meta.setDisplayName("§6Jouer maintenant !");
		
		item.setItemMeta(meta);
		
		
		return item;
	}
	
	
}
