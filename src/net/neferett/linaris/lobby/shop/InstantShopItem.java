package net.neferett.linaris.lobby.shop;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.neferett.linaris.BukkitAPI;
import net.neferett.linaris.api.PlayerData;
import net.neferett.linaris.utils.ShopMessage;
import net.neferett.linaris.utils.gui.GuiManager;
import net.neferett.linaris.utils.gui.GuiScreen;
import net.neferett.linaris.lobby.shop.guis.InstantBuyGui;

public abstract class InstantShopItem {

	public enum PriceType {
		EC, LC
	}

	List<String>	description;
	String			id;
	ItemStack		itemUI;
	String			name;
	double			price;

	PriceType		priceType;

	public InstantShopItem(final String id, final String name, final PriceType priceType, final double price,
			final List<String> description, final ItemStack itemUI) {
		this.id = id;
		this.name = name;
		this.priceType = priceType;
		this.price = price;
		this.description = description;
		this.itemUI = itemUI;
	}

	public abstract boolean alreadyHave(Player p);

	public double getBasePrice() {
		return this.price;
	}

	public String getColoredPrice(final Player p) {
		if (this.priceType == PriceType.EC) return "§e" + this.getPrice(p) + "Coins";
		else return "§b" + this.getPrice(p) + "Crédits";
	}

	public List<String> getDescription() {
		return this.description;
	}

	public String getID() {
		return this.id;
	}

	public ItemStack getItem() {
		final ItemStack item = this.itemUI.clone();
		final ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(this.getName());
		item.setItemMeta(meta);
		return item;
	}

	public ItemStack getItemUI(final Player p) {
		final ItemStack item = this.getItem().clone();

		final ItemMeta meta = item.getItemMeta();

		final List<String> strings = new ArrayList<>();

		if (this.getDescription() != null && !this.getDescription().isEmpty()) strings.addAll(this.getDescription());
		strings.add("");
		if (this.alreadyHave(p)) strings.add("§aAcheté");
		else strings.add("§aClic gauche: débloquer " + this.getColoredPrice(p));

		meta.setLore(strings);

		meta.setDisplayName(this.getName());

		item.setItemMeta(meta);

		return item;
	}

	public ItemStack getItemUIBuy(final Player p) {
		final ItemStack item = this.getItem().clone();

		final ItemMeta meta = item.getItemMeta();

		final List<String> strings = new ArrayList<>();

		if (this.getDescription() != null && !this.getDescription().isEmpty()) strings.addAll(this.getDescription());

		strings.add("");
		strings.add("§6Prix " + this.getColoredPrice(p));

		meta.setLore(strings);

		meta.setDisplayName(this.getName());

		item.setItemMeta(meta);

		return item;
	}

	public String getName() {
		return this.name;
	}

	public abstract double getPrice(Player p);

	public PriceType getPriceType() {
		return this.priceType;
	}

	public abstract void onBuy(Player p);

	public void testBuy(final Player p) {

		final PlayerData pd = BukkitAPI.get().getPlayerDataManager().getPlayerData(p.getName().toLowerCase());

		if (this.priceType == PriceType.EC) {

			if (pd.getEC() >= this.price) {
				ShopMessage.itemBoughtEC(p, ChatColor.stripColor(this.getName()), this.price);
				pd.withdrawCoins(this.price, null);
				this.onBuy(p);
			} else ShopMessage.itemNotEnoughGolds(p);

		} else if (pd.getLC() >= this.price) {
			ShopMessage.itemBoughtLC(p, ChatColor.stripColor(this.getName()), this.price);
			pd.withdrawLC(this.price, null);
			this.onBuy(p);
		} else ShopMessage.itemNotEnoughLegendaryCoins(p);
	}

	public void useOrBuy(final Player p, final GuiScreen last) {

		// if (alreadyHave(p)) {
		// return;
		// } else {
		GuiManager.openGui(new InstantBuyGui(p, this, last));
		// }

	}

}
