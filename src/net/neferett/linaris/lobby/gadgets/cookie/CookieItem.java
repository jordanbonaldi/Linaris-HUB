package net.neferett.linaris.lobby.gadgets.cookie;

import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;

import net.neferett.linaris.specialitems.SpecialItem;
import net.neferett.linaris.specialitems.SpecialItemConsumable;
import net.neferett.linaris.utils.PlayerUtils;

public class CookieItem extends SpecialItem implements SpecialItemConsumable{

	static HashMap<String,Integer> players = new HashMap<>();
	
	String playerName;
	Player player;
	
	public static SpecialItem get(Player player) {
		if (!players.containsKey(player.getName())) {
			players.put(player.getName(), registerItem(new CookieItem(player)));
		}
		return SpecialItem.get(players.get(player.getName()));
	}

	private CookieItem(Player player) {
		super("§6Oh, un Cookie !", new ItemStack(Material.COOKIE),"§7Miam ?");
		this.player = player;
		this.playerName = player.getName();
		setCancelInteractEvent(false);
	}

	@Override
	public void consumeItemEvent(Player player, ItemStack item) {
		
		PlayerUtils.consumItemInHand(player);
		
		if (this.player != null  && player.equals(this.player)) {
			this.player.sendMessage("§fVous mangez votre propre cookie... Où est passé votre sens du partage ?");
			return;
		}
		
		player.sendMessage("§aVous mangez le cookie de §e" + playerName + " §a! Dites lui merci =)");
		if (this.player!= null && this.player.isOnline());
			this.player.sendMessage("§aUn de vos cookie à été mangé par §e" + player.getName() + " §a! L'a t'il trouvé bon ?");
		
	}

	@Override
	public void inventoryClickEvent(Player player) {
		player.setFoodLevel(18);
	}
	
	@Override
	protected void dropEvent(PlayerDropItemEvent e) {
		e.getItemDrop().remove();
	}


}
