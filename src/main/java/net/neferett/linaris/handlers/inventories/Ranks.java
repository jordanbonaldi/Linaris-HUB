package net.neferett.linaris.handlers.inventories;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import net.neferett.linaris.utils.gui.GuiScreen;
import net.neferett.linaris.utils.ItemBuilder;
import net.neferett.linaris.utils.NBTItem;

public class Ranks extends GuiScreen{

	
	
	
	
	
	
	
	
	public Ranks(String name, int size, Player p, boolean update) {
		super("Grades", 3, p, false);
		build();
	}

	

	@Override
	public void drawScreen() {
		setItemLine(1,
				new ItemBuilder(new ItemStack(Material.IRON_INGOT)).setTitle("net.neferett.linaris7Grade net.neferett.linarisfVIP net.neferett.linaris7| net.neferett.linariseà vie")
				.addLores(getLoreVIP()).build(), 2,
				3);
		
		setItemLine(2,
				new ItemBuilder(new ItemStack(Material.GOLD_INGOT)).setTitle("net.neferett.linaris7Grade net.neferett.linarisbMegaVIP net.neferett.linaris7| net.neferett.linariseà vie")
				.addLores(getLoreMegaVIP()).build(), 2,
				5);
		
		setItemLine(3,
				new ItemBuilder(new ItemStack(Material.DIAMOND)).setTitle("net.neferett.linaris7Grade net.neferett.linarisaEpicVIP net.neferett.linarise✪ net.neferett.linaris7| net.neferett.linariseà vie")
				.addLores(getLoreepicvip()).build(), 2,
				7);
		
		setItemLine(4,
				new ItemBuilder(new ItemStack(Material.ARROW)).setTitle("net.neferett.linariscRetour")
				.build(), 3,
				9);
	}
	
	@Override
	public void onOpen() {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void onClick(ItemStack item, InventoryClickEvent event) {
		event.setCancelled(true);

		NBTItem nbt = new NBTItem(item);
		if (!nbt.hasKey("itemID")) {
			return;
		}

		int itemID = nbt.getInteger("itemID");

		Player p = (Player)event.getWhoClicked();

		if (itemID == 1) {
            p.sendMessage("net.neferett.linaris7net.neferett.linarism-----------------------------------------------------");
            p.sendMessage("net.neferett.linaris7Grade net.neferett.linarisfVIP net.neferett.linaris7| net.neferett.linariseà vie");
            p.sendMessage("net.neferett.linarisf» net.neferett.linaris6net.neferett.linarisnhttp://linaris.fr/article/2/vip");
            p.sendMessage("net.neferett.linaris7net.neferett.linarism-----------------------------------------------------");
            p.closeInventory();
			return;
		}
		if (itemID == 2) {
            p.sendMessage("net.neferett.linaris7net.neferett.linarism-----------------------------------------------------");
            p.sendMessage("net.neferett.linaris7Grade net.neferett.linarisbMegaVIP net.neferett.linaris7| net.neferett.linariseà vie");
            p.sendMessage("net.neferett.linarisf» net.neferett.linaris6net.neferett.linarisnhttp://linaris.fr/article/3/megavip");
            p.sendMessage("net.neferett.linaris7net.neferett.linarism-----------------------------------------------------");
            p.closeInventory();
			return;
		}
		if (itemID == 3) {
            p.sendMessage("net.neferett.linaris7net.neferett.linarism-----------------------------------------------------");
            p.sendMessage("net.neferett.linaris7Grade net.neferett.linarisaEpicVIP net.neferett.linarise✪ net.neferett.linaris7| net.neferett.linariseà vie");
            p.sendMessage("net.neferett.linarisf» net.neferett.linaris6net.neferett.linarisnhttp://linaris.fr/article/4/epicvip");
            p.sendMessage("net.neferett.linaris7net.neferett.linarism-----------------------------------------------------");
            p.closeInventory();
			return;
		}
		if(itemID == 4){
			p.closeInventory();
			return;
		}
	}
	
	@Override
	public void onClose() {
		// TODO Auto-generated method stub

	}
	public static String[] getLoreVIP() {
		List<String> lorevip = new ArrayList<String>();
        lorevip.add("");
        lorevip.add("net.neferett.linariseCe grade vous offre :");
        lorevip.add("");
        lorevip.add("net.neferett.linaris6• net.neferett.linaris7Kit net.neferett.linarisfVIP net.neferett.linaris7sur tous les jeux");
        lorevip.add("net.neferett.linaris6• net.neferett.linaris7Un multiplicateur des net.neferett.linariscCoins net.neferett.linaris7à net.neferett.linarise200%");
        lorevip.add("net.neferett.linaris6• net.neferett.linaris7Des cosmétiques uniques aux net.neferett.linarisfVIP");
        lorevip.add("net.neferett.linaris6• net.neferett.linarisfVIP net.neferett.linaris7surnet.neferett.linarisf: net.neferett.linarisaFreeBuild, IS, PvPBox/Train.");
        lorevip.add("net.neferett.linaris6• net.neferett.linaris7Accès au serveur quand il est complet");
        lorevip.add("net.neferett.linaris6• net.neferett.linaris7Rejoindre les slots net.neferett.linarisfVIP net.neferett.linaris7dans les jeux.");
        lorevip.add("net.neferett.linaris6• net.neferett.linaris7Place prioritaire dans la file d'attente");
        lorevip.add("net.neferett.linaris6• net.neferett.linaris7Gain de net.neferett.linarise1200 net.neferett.linariscCoins net.neferett.linaris7à l'achat");
        lorevip.add("net.neferett.linaris6• net.neferett.linaris7Démarcation dans le chat grâce au grade");
        lorevip.add("net.neferett.linaris7net.neferett.linarisf[VIP]net.neferett.linaris7 ainsi que le pseudo en net.neferett.linarisfBLANCnet.neferett.linaris7.");
        lorevip.add("");
        lorevip.add("net.neferett.linarisf» net.neferett.linariseClique pour ouvrir la page de l'offre.");
        String[] loreinstring = new String[lorevip.size()];
        loreinstring = lorevip.toArray(loreinstring);
		return loreinstring;
	}
	
	public static String[] getLoreMegaVIP() {
		List<String> loremegavip = new ArrayList<String>();
        loremegavip.add("");
        loremegavip.add("net.neferett.linariseCe grade vous offre :");
        loremegavip.add("");
        loremegavip.add("net.neferett.linaris6• net.neferett.linaris7Kit net.neferett.linarisbMegaVIP net.neferett.linaris7sur tous les jeux");
        loremegavip.add("net.neferett.linaris6• net.neferett.linaris7Un multiplicateur des net.neferett.linariscCoins net.neferett.linaris7à net.neferett.linarise300%");
        loremegavip.add("net.neferett.linaris6• net.neferett.linaris7Des cosmétiques uniques aux net.neferett.linarisbMegaVIP");
        loremegavip.add("net.neferett.linaris6• net.neferett.linarisbMegaVIP net.neferett.linaris7surnet.neferett.linarisf: net.neferett.linarisaFreeBuild, IS, PvPBox/Train.");
        loremegavip.add("net.neferett.linaris6• net.neferett.linaris7Accès au serveur quand il est complet");
        loremegavip.add("net.neferett.linaris6• net.neferett.linaris7Rejoindre les slots net.neferett.linarisbMegaVIP net.neferett.linaris7dans les jeux.");
        loremegavip.add("net.neferett.linaris6• net.neferett.linaris7Place prioritaire dans la file d'attente");
        loremegavip.add("net.neferett.linaris6• net.neferett.linaris7Gain de net.neferett.linarise2500 net.neferett.linariscCoins net.neferett.linaris7à l'achat");
        loremegavip.add("net.neferett.linaris6• net.neferett.linaris7Démarcation dans le chat grâce au grade");
        loremegavip.add("net.neferett.linaris7net.neferett.linarisb[MegaVIP]net.neferett.linaris7 ainsi que le pseudo en net.neferett.linarisbBLEUnet.neferett.linaris7.");
        loremegavip.add("");
        loremegavip.add("net.neferett.linarisf» net.neferett.linariseClique pour ouvrir la page de l'offre.");
        String[] loreinstring = new String[loremegavip.size()];
        loreinstring = loremegavip.toArray(loreinstring);
		return loreinstring;
	}
	
	public static String[] getLoreepicvip() {
		List<String> loreepicvip = new ArrayList<String>();
        loreepicvip.add("");
        loreepicvip.add("net.neferett.linariseCe grade vous offre :");
        loreepicvip.add("");
        loreepicvip.add("net.neferett.linaris6• net.neferett.linaris7Kit net.neferett.linarisaEpicVIP net.neferett.linarise✪  net.neferett.linaris7sur tous les jeux");
        loreepicvip.add("net.neferett.linaris6• net.neferett.linaris7Un multiplicateur des net.neferett.linariscCoins net.neferett.linaris7à net.neferett.linarise400%");
        loreepicvip.add("net.neferett.linaris6• net.neferett.linaris7Des cosmétiques uniques aux net.neferett.linarisaEpicVIP net.neferett.linarise✪");
        loreepicvip.add("net.neferett.linaris6• net.neferett.linarisaEpicVIP net.neferett.linarise✪  net.neferett.linaris7surnet.neferett.linarisf: net.neferett.linarisaFreeBuild, IS, PvPBox/Train.");
        loreepicvip.add("net.neferett.linaris6• net.neferett.linaris7Accès au serveur quand il est complet");
        loreepicvip.add("net.neferett.linaris6• net.neferett.linaris7Rejoindre les slots net.neferett.linarisaEpicVIP net.neferett.linarise✪ net.neferett.linaris7dans les jeux.");
        loreepicvip.add("net.neferett.linaris6• net.neferett.linaris7Place prioritaire dans la file d'attente");
        loreepicvip.add("net.neferett.linaris6• net.neferett.linaris7Gain de net.neferett.linarise5000 net.neferett.linariscCoins net.neferett.linaris7à l'achat");
        loreepicvip.add("net.neferett.linaris6• net.neferett.linaris7Message lorsque vous rejoindez un lobby !");
        loreepicvip.add("net.neferett.linaris6• net.neferett.linaris7Démarcation dans le chat grâce au grade");
        loreepicvip.add("net.neferett.linaris7net.neferett.linarisaEpicVIP net.neferett.linarise✪net.neferett.linarisa net.neferett.linaris7ainsi que le pseudo en net.neferett.linarisaVERTnet.neferett.linaris7.");
        loreepicvip.add("");
        loreepicvip.add("net.neferett.linarisf» net.neferett.linariseClique pour ouvrir la page de l'offre.");
        String[] loreinstring = new String[loreepicvip.size()];
        loreinstring = loreepicvip.toArray(loreinstring);
		return loreinstring;
	}
	
	public void setItemLine(int id, ItemStack item, int line, int slot) {
		super.setItemLine(new NBTItem(item).setInteger("itemID", id).getItem(), line, slot);
	}
}
