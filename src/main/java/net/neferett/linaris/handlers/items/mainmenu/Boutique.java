package net.neferett.linaris.handlers.items.mainmenu;

import org.bukkit.entity.Player;

import net.neferett.linaris.handlers.items.GetHead;
import net.neferett.linaris.specialitems.MenuItem;
import net.neferett.linaris.specialitems.SpecialItem;

public class Boutique extends MenuItem {

	public static int			id			= -1;

	public static SpecialItem get() {
		if (id == -1)id = registerItem(new Boutique());
		return get(id);
	}
	
	public Boutique() {
		super("§a§lBoutique en ligne", new GetHead("4bb7ea44-a33a-4023-91d7-d44d28ae5aac",
				"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDhjMWUxYzYyZGM2OTVlYjkwZmExOTJkYTZhY2E0OWFiNGY5ZGZmYjZhZGI1ZDI2MjllYmZjOWIyNzg4ZmEyIn19fQ==").getItem());
	}

	@Override
	public void inventoryClickEvent(Player player) {
	}
	
}
