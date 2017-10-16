package net.neferett.linaris.lobby.gadgets.cookie;

import org.bukkit.entity.Item;

import net.neferett.linaris.utils.tasksmanager.TaskManager;

public class CookieRunnable implements Runnable {

	Item    item;
	int		actualTicks;

	public CookieRunnable(Item item,int time) {
		this.item = item;
		this.actualTicks = time*20;
	}

	@Override
	public void run() {
		if (item == null || item.isDead()) {
			TaskManager.cancelTaskByName("cookie-" + item.getEntityId());
			return;
		}
		if (actualTicks > 0) {
			if (item.isOnGround()) {
//				Particles.CRIT.display(0, 0, 0, 0, 1,item.getLocation().add(0, 1.5, 0));
//				Particles.CRIT.display(0, 0, 0, 0, 1,item.getLocation().add(0, 2, 0));
//				Particles.CRIT.display(0, 0, 0, 0, 1,item.getLocation().add(0, 2.5, 0));
				actualTicks--;
			}
		} else {
			item.remove();
			TaskManager.cancelTaskByName("cookie-" + item.getEntityId());
		}
	}
}
