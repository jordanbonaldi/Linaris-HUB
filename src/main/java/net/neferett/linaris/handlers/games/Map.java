package net.neferett.linaris.handlers.games;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.neferett.linaris.BukkitAPI;

public class Map {

	String name;
	Game owner;
	
	public Map(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public Game getOwner() {
		return owner;
	}
	
	public void setOwner(Game owner) {
		this.owner = owner;
	}
	
	public int getPlayers() {
		return BukkitAPI.get().getProxyDataManager().getPlayerInGameWithMap(owner.getGame(), this.getName());
	}
	
	public ItemStack getItemUI() {
		ItemStack item = new ItemStack(Material.PAPER);
		ItemMeta meta = item.getItemMeta();
	
		List<String> strings = new ArrayList<String>();
		strings.add("");
		strings.add("§e> §aClic gauche §7Rejoindre la file");
		strings.add("§e> §aClic droit §7Voir les serveurs");
		strings.add("");
		strings.add("§7En jeu §e" + getPlayers() + " §7joueur(s)");
		strings.add("§bAttente : §eAucune");

		meta.setLore(strings);
		meta.setDisplayName("§b" + name);
		
		item.setItemMeta(meta);
		
		
		return item;
	}
	
	
}
