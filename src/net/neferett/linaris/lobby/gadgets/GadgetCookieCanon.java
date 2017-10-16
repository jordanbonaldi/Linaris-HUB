package net.neferett.linaris.lobby.gadgets;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import net.neferett.linaris.specialitems.SpecialItem;
import net.neferett.linaris.utils.tasksmanager.TaskManager;
import net.neferett.linaris.lobby.Lobby;
import net.neferett.linaris.lobby.gadgets.cookie.CookieItem;
import net.neferett.linaris.lobby.gadgets.cookie.CookieRunnable;

public class GadgetCookieCanon extends GadgetItem {
	private static final String	CANON_FLAG		= "GadgetItemCookieCanon";
	
	public GadgetCookieCanon() {
		super("§6Canon à Cookie ! ( §eGratuit §6)",  new ItemStack(Material.COOKIE),CANON_FLAG,3,"§7Cookie pour tout le monde !");
		registerItem(this);
		setDroppable(false);
	}

	@Override
	public void onUse(Player player) {
		SpecialItem item = CookieItem.get(player);
		for (int i = 0; i < 5; i++)
			sendLater(player,item, 5 + i * 6);
	}


	private void sendLater(final Player player, SpecialItem items,long delay) {
		TaskManager.scheduler.runTaskLater(Lobby.getInstance(), new Runnable() {
			@Override
			public void run() {
				Location spawn = player.getEyeLocation().add(player.getLocation().getDirection());
				Item item = player.getWorld().dropItem(spawn, items.getStaticItem());
				item.setVelocity(player.getLocation().getDirection());
				TaskManager.scheduleSyncRepeatingTask("cookie-" + item.getEntityId(), new CookieRunnable(item, 10), 0, 1);

				spawn.getWorld().playSound(spawn, Sound.SHOOT_ARROW, 1F, 1F);
				spawn.getWorld().playSound(spawn, Sound.FIREWORK_LARGE_BLAST2, 1F, 1F);
			}
		}, delay);
	}



	@Override
	public void onOption(Player player) {
		// TODO Auto-generated method stub
		
	}

}
