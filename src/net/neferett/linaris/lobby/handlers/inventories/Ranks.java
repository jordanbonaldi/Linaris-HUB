package net.neferett.linaris.lobby.handlers.inventories;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import net.neferett.linaris.utils.gui.GuiScreen;
import net.neferett.linaris.lobby.utils.ItemBuilder;
import net.neferett.linaris.lobby.utils.NBTItem;

public class Ranks extends GuiScreen{

	
	
	
	
	
	
	
	
	public Ranks(String name, int size, Player p, boolean update) {
		super("Grades", 3, p, false);
		build();
	}

	

	@Override
	public void drawScreen() {
		setItemLine(1,
				new ItemBuilder(new ItemStack(Material.IRON_INGOT)).setTitle("§7Grade §fVIP §7| §eà vie")
				.addLores(getLoreVIP()).build(), 2,
				3);
		
		setItemLine(2,
				new ItemBuilder(new ItemStack(Material.GOLD_INGOT)).setTitle("§7Grade §bMegaVIP §7| §eà vie")
				.addLores(getLoreMegaVIP()).build(), 2,
				5);
		
		setItemLine(3,
				new ItemBuilder(new ItemStack(Material.DIAMOND)).setTitle("§7Grade §aEpicVIP §e✪ §7| §eà vie")
				.addLores(getLoreepicvip()).build(), 2,
				7);
		
		setItemLine(4,
				new ItemBuilder(new ItemStack(Material.ARROW)).setTitle("§cRetour")
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
            p.sendMessage("§7§m-----------------------------------------------------");
            p.sendMessage("§7Grade §fVIP §7| §eà vie");
            p.sendMessage("§f» §6§nhttp://linaris.fr/article/2/vip");
            p.sendMessage("§7§m-----------------------------------------------------");
            p.closeInventory();
			return;
		}
		if (itemID == 2) {
            p.sendMessage("§7§m-----------------------------------------------------");
            p.sendMessage("§7Grade §bMegaVIP §7| §eà vie");
            p.sendMessage("§f» §6§nhttp://linaris.fr/article/3/megavip");
            p.sendMessage("§7§m-----------------------------------------------------");
            p.closeInventory();
			return;
		}
		if (itemID == 3) {
            p.sendMessage("§7§m-----------------------------------------------------");
            p.sendMessage("§7Grade §aEpicVIP §e✪ §7| §eà vie");
            p.sendMessage("§f» §6§nhttp://linaris.fr/article/4/epicvip");
            p.sendMessage("§7§m-----------------------------------------------------");
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
        lorevip.add("§eCe grade vous offre :");
        lorevip.add("");
        lorevip.add("§6• §7Kit §fVIP §7sur tous les jeux");
        lorevip.add("§6• §7Un multiplicateur des §cCoins §7à §e200%");
        lorevip.add("§6• §7Des cosmétiques uniques aux §fVIP");
        lorevip.add("§6• §fVIP §7sur§f: §aFreeBuild, IS, PvPBox/Train.");
        lorevip.add("§6• §7Accès au serveur quand il est complet");
        lorevip.add("§6• §7Rejoindre les slots §fVIP §7dans les jeux.");
        lorevip.add("§6• §7Place prioritaire dans la file d'attente");
        lorevip.add("§6• §7Gain de §e1200 §cCoins §7à l'achat");
        lorevip.add("§6• §7Démarcation dans le chat grâce au grade");
        lorevip.add("§7§f[VIP]§7 ainsi que le pseudo en §fBLANC§7.");
        lorevip.add("");
        lorevip.add("§f» §eClique pour ouvrir la page de l'offre.");
        String[] loreinstring = new String[lorevip.size()];
        loreinstring = lorevip.toArray(loreinstring);
		return loreinstring;
	}
	
	public static String[] getLoreMegaVIP() {
		List<String> loremegavip = new ArrayList<String>();
        loremegavip.add("");
        loremegavip.add("§eCe grade vous offre :");
        loremegavip.add("");
        loremegavip.add("§6• §7Kit §bMegaVIP §7sur tous les jeux");
        loremegavip.add("§6• §7Un multiplicateur des §cCoins §7à §e300%");
        loremegavip.add("§6• §7Des cosmétiques uniques aux §bMegaVIP");
        loremegavip.add("§6• §bMegaVIP §7sur§f: §aFreeBuild, IS, PvPBox/Train.");
        loremegavip.add("§6• §7Accès au serveur quand il est complet");
        loremegavip.add("§6• §7Rejoindre les slots §bMegaVIP §7dans les jeux.");
        loremegavip.add("§6• §7Place prioritaire dans la file d'attente");
        loremegavip.add("§6• §7Gain de §e2500 §cCoins §7à l'achat");
        loremegavip.add("§6• §7Démarcation dans le chat grâce au grade");
        loremegavip.add("§7§b[MegaVIP]§7 ainsi que le pseudo en §bBLEU§7.");
        loremegavip.add("");
        loremegavip.add("§f» §eClique pour ouvrir la page de l'offre.");
        String[] loreinstring = new String[loremegavip.size()];
        loreinstring = loremegavip.toArray(loreinstring);
		return loreinstring;
	}
	
	public static String[] getLoreepicvip() {
		List<String> loreepicvip = new ArrayList<String>();
        loreepicvip.add("");
        loreepicvip.add("§eCe grade vous offre :");
        loreepicvip.add("");
        loreepicvip.add("§6• §7Kit §aEpicVIP §e✪  §7sur tous les jeux");
        loreepicvip.add("§6• §7Un multiplicateur des §cCoins §7à §e400%");
        loreepicvip.add("§6• §7Des cosmétiques uniques aux §aEpicVIP §e✪");
        loreepicvip.add("§6• §aEpicVIP §e✪  §7sur§f: §aFreeBuild, IS, PvPBox/Train.");
        loreepicvip.add("§6• §7Accès au serveur quand il est complet");
        loreepicvip.add("§6• §7Rejoindre les slots §aEpicVIP §e✪ §7dans les jeux.");
        loreepicvip.add("§6• §7Place prioritaire dans la file d'attente");
        loreepicvip.add("§6• §7Gain de §e5000 §cCoins §7à l'achat");
        loreepicvip.add("§6• §7Message lorsque vous rejoindez un lobby !");
        loreepicvip.add("§6• §7Démarcation dans le chat grâce au grade");
        loreepicvip.add("§7§aEpicVIP §e✪§a §7ainsi que le pseudo en §aVERT§7.");
        loreepicvip.add("");
        loreepicvip.add("§f» §eClique pour ouvrir la page de l'offre.");
        String[] loreinstring = new String[loreepicvip.size()];
        loreinstring = loreepicvip.toArray(loreinstring);
		return loreinstring;
	}
	
	public void setItemLine(int id, ItemStack item, int line, int slot) {
		super.setItemLine(new NBTItem(item).setInteger("itemID", id).getItem(), line, slot);
	}
}
