package net.neferett.linaris.lobby.shop;

import java.util.List;

public class LevelInfo {

	private List<String>	lore;
	private double price;

	public LevelInfo(List<String> lore,double price) {
		this.lore = lore;
		this.price = price;
	}

	public List<String> getLore() {
		return lore;
	}

	public void setLore(List<String> lore) {
		this.lore = lore;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}

}
