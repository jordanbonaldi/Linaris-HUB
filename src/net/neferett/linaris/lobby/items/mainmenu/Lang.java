package net.neferett.linaris.lobby.items.mainmenu;

import org.bukkit.entity.Player;

import net.neferett.linaris.specialitems.MenuItem;
import net.neferett.linaris.specialitems.SpecialItem;
import net.neferett.linaris.lobby.items.GetHead;

public class Lang extends MenuItem {

	public static int			id			= -1;

	public static SpecialItem get() {
		if (id == -1)id = registerItem(new Lang());
		return get(id);
	}
	
	
	public Lang() {
		super("Langages", new GetHead("699b59a0-e90b-4c78-a3e4-08ac80723a5f", 
				"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOWIzNDk1ZTlkYmQ1YTQyNmUxNDQ2ZTY2MjdiZjhkZDU1ZDk2MTJjZTNiNTVhODU5NmUxMTJiMjhkYjllYTNhIn19fQ==").getItem(),"§7Changer de langage");
	}

	@Override
	public void inventoryClickEvent(Player player) {
		setCancelInteractEvent(true);
	}
	
}
