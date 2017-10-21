package net.neferett.linaris.lobby.items.epicchest;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

import net.neferett.linaris.specialitems.MenuItem;
import net.neferett.linaris.specialitems.SpecialItem;
import net.neferett.linaris.utils.gui.GuiManager;
import net.neferett.linaris.utils.json.JSONException;
import net.neferett.linaris.lobby.handlers.inventories.EpicChestInventory;
import net.neferett.linaris.lobby.handlers.inventories.MainMenuInventory;
import net.neferett.linaris.lobby.items.GetHead;

public class EpicChestItem extends MenuItem {

	public static ReadFile		rf = null;
	public static int			id			= -1;

	public static SpecialItem get() {
		return get(registerItem(new EpicChestItem()));
	}
	
	public static List<GetHead> box = new ArrayList<>();
	
	public static void init(){
		rf = new ReadFile();
		rf.getjs().forEach((js) ->{
			try {
				box.add(new GetHead(js.getSkullOwner(), js.getValue()));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}
	
	private static GetHead getRandomChest(){
		return (box.get(MainMenuInventory.getRandomInteger(rf.getjs().size(), 0)));
	}
	public EpicChestItem() {
		super("§6Boite magique", getRandomChest().getItem(),"§7Pleins d'objets a découvrir !");
	}

	@Override
	public void inventoryClickEvent(Player player) {
		GuiManager.openGui(new EpicChestInventory(player,null));
	}
	
}
