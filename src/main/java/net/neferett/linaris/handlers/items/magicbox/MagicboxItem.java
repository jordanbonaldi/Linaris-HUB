package net.neferett.linaris.handlers.items.magicbox;

import java.util.ArrayList;
import java.util.List;

import net.neferett.linaris.handlers.inventories.MagicBoxInventory;
import net.neferett.linaris.handlers.inventories.MainMenuInventory;
import org.bukkit.entity.Player;

import net.neferett.linaris.specialitems.MenuItem;
import net.neferett.linaris.specialitems.SpecialItem;
import net.neferett.linaris.utils.gui.GuiManager;
import net.neferett.linaris.utils.json.JSONException;
import net.neferett.linaris.handlers.items.GetHead;

public class MagicboxItem extends MenuItem {

	public static ReadFile		rf = null;
	public static int			id			= -1;

	public static SpecialItem get() {
		return get(registerItem(new MagicboxItem()));
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
	public MagicboxItem() {
		super("§6Boite magique", getRandomChest().getItem(),"§7Pleins d'objets a découvrir !");
	}

	@Override
	public void inventoryClickEvent(Player player) {
		GuiManager.openGui(new MagicBoxInventory(player,null));
	}
	
}
