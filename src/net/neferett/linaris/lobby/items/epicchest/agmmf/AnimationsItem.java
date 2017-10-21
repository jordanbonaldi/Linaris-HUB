package net.neferett.linaris.lobby.items.epicchest.agmmf;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import net.neferett.linaris.specialitems.MenuItem;
import net.neferett.linaris.specialitems.SpecialItem;

public class AnimationsItem extends MenuItem {

	public static int			id			= -1;

	public static SpecialItem get() {
		if (id == -1)id = registerItem(new AnimationsItem());
		return get(id);
	}
	
	public AnimationsItem() {
		super("§aAnimations", new ItemStack(Material.GLOWSTONE_DUST),"§7La classe à l'état pur !","","§eClic-Milieu: Créer un raccourci");
	}

	@Override
	public void inventoryClickEvent(Player player) {
		player.sendMessage("§6Helmet");
	}
	
}
