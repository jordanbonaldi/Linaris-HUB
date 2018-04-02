package net.neferett.linaris.lobby.handlers.shop;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.neferett.linaris.BukkitAPI;
import net.neferett.linaris.api.ItemInfo;
import net.neferett.linaris.api.PlayerData;
import net.neferett.linaris.api.ShopItemsManager;
import net.neferett.linaris.mistery.GlowUtils;
import net.neferett.linaris.utils.ShopMessage;
import net.neferett.linaris.utils.gui.GuiManager;
import net.neferett.linaris.utils.gui.GuiScreen;
import net.neferett.linaris.lobby.handlers.games.Game;
import net.neferett.linaris.lobby.handlers.games.GamesManager;
import net.neferett.linaris.lobby.handlers.shop.guis.BuyKitGui;

public class ShopItem {

	String uuid;
	Game game;
	List<LevelInfo> levelInfos;
	Boolean firstFree;
	String name;
	String description;
	ItemStack item;

	public ShopItem(String uuid, String name, String description, ItemStack item, Boolean firstFree) {
		this.uuid = uuid;
		this.name = name;
		this.description = description;
		this.item = item;
		this.levelInfos = new ArrayList<LevelInfo>();
		this.firstFree = firstFree;
	}

	public String getUUID() {
		return uuid;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public List<LevelInfo> getLevelInfos() {
		return levelInfos;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public void addLevelInfo(LevelInfo info) {
		levelInfos.add(info);
	}

	public boolean isLevelItem() {
		return (levelInfos.size() == 1) ? false : true;
	}

	public ItemStack getItemUI(Player p) {
		ItemStack item = this.item.clone();
		ItemMeta meta = item.getItemMeta();
		int level = getLevelKit(p);

		meta.setDisplayName("§e" + getName() + ((levelInfos.size() > 1) ? " §6" + getLevelKit(p) : ""));

		List<String> lore = new ArrayList<String>();

		lore.add("");

		if (firstFree) {
			lore.addAll(getLevelInfos().get(level).getLore());
			lore.add("");
			if (level == getLevelInfos().size()-1) {
				lore.add("§aAcheté");
				lore.add("§bClic droit: voir les niveaux");
			} else {
				LevelInfo next = getLevelInfos().get(level + 1);
				lore.add("§aClic gauche: débloquer §e" + next.getPrice() + "Coins");
				lore.add("§bClic droit: voir les niveaux");
			}


		}
		// else {
		// if (level == 0) {
		// lore.add("§cVous ne possédez pas,");
		// lore.add("§c ce kit achetez le en");
		// lore.add("§c boutique");
		// } else {
		// lore.addAll(getLevelInfos().get(level-1).getLore());
		// }
		// }

		meta.setLore(lore);
		item.setItemMeta(meta);
		return item;
	}

	public int getLevelKit(Player p) {
		return ShopItemsManager.getItem(p.getName(),String.valueOf(game.getGame().getID()), uuid).getLevel();
	}

	public ItemStack getItemLevelInfo(Player p,int level, LevelInfo info) {
		ItemStack item = this.item.clone();
		ItemMeta meta = item.getItemMeta();

		List<String> strings = new ArrayList<String>();
		
		strings.add("");
		strings.addAll(info.getLore());
		
		if (info.getPrice() != 0) {
		strings.add("");	
		strings.add("§6Prix §e" + info.getPrice() + "Coins");
		}
		
		meta.setLore(strings);
		meta.setDisplayName("§e" + getName() + ((levelInfos.size() > 1) ? " §6" +  level : ""));
		item.setAmount((level == 0) ? 1 : level);

		item.setItemMeta(meta);

		
		return (getLevelKit(p) >= level && level != 0) ? GlowUtils.addGlow(item) : item;
	}
	
	public ItemStack getBuyItemInfo(int level, LevelInfo info) {
		ItemStack item = this.item.clone();
		ItemMeta meta = item.getItemMeta();

		List<String> strings = new ArrayList<String>();
		
		strings.add("");
		strings.addAll(info.getLore());
		strings.add("");	
		strings.add("§6Prix §e" + info.getPrice() + "Coins");
		meta.setLore(strings);
		meta.setDisplayName("§e" + getName() + ((levelInfos.size() > 1) ? " §6" +  level : ""));

		item.setItemMeta(meta);

		return item;
	}

	public void testBuy(Player p) {

		PlayerData pd = BukkitAPI.get().getPlayerDataManager().getPlayerData(p.getName().toLowerCase());
		String name = p.getName().toLowerCase();
		int level = getLevelKit(p);
		if(!getGame().equals(GamesManager.TOWERS)){
			if (level == getLevelInfos().size()-1) {
			ShopMessage.itemIsAlreadyBought(p);
			return;
			}
		}else{
			if (level == 1) {
			ShopMessage.itemIsAlreadyBought(p);
			return;
			}
		}
		LevelInfo next = getLevelInfos().get(level + 1);
		if (pd.getEC() >= next.getPrice()) {
			ShopMessage.itemBoughtEC(p, "§6" + ChatColor.stripColor(getName() + " " + (level + 1)), next.getPrice());
			pd.withdrawCoins(next.getPrice(), null);
			ShopItemsManager.setItem(name, String.valueOf(game.getGame().getID()), new ItemInfo(getUUID(), level + 1));
		} else {
			ShopMessage.itemNotEnoughGolds(p);
		}

	}

	public void useOrBuy(Player p, GuiScreen last) {

		int level = getLevelKit(p);
		if(!getGame().equals(GamesManager.TOWERS)){
		if (level == getLevelInfos().size()-1) {
			ShopMessage.itemIsAlreadyBought(p);
		}else {
			GuiManager.openGui(new BuyKitGui(p, this, last));
		}
		}else{
			if (level == 1) {
				ShopMessage.itemIsAlreadyBought(p);
			} else {
				GuiManager.openGui(new BuyKitGui(p, this, last));
			}
		}
		


	
	}

	

	public ItemStack getItemUIBuy(Player player) {
		int level = getLevelKit(player);
		if(!getGame().equals(GamesManager.TOWERS)){
		if (level == getLevelInfos().size()-1) {
			ShopMessage.itemIsAlreadyBought(player);
			return null;
		}
		}else{
			if (level == 1) {
				ShopMessage.itemIsAlreadyBought(player);
				return null;
			}
		}
		LevelInfo next = getLevelInfos().get(level + 1);
		
		return getBuyItemInfo(level+1	, next);

		
		}

}
