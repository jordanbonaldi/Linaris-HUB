package net.neferett.linaris.lobby.gadgets.spectral;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import net.neferett.linaris.utils.Particles;
import net.neferett.linaris.utils.tasksmanager.TaskManager;

public class ParticleRunnable implements Runnable {

	Player	player;
	int		actualTicks	= 200;

	public ParticleRunnable(Player player) {
		this.player = player;
	}

	@Override
	public void run() {
		if (actualTicks > 0) {
			Particles.SNOW_SHOVEL.display(0, 0, 0, 0, 5,player.getLocation().add(0, 1, 0),new ArrayList<>(Bukkit.getOnlinePlayers()));
			actualTicks--;
		} else {
			TaskManager.cancelTaskByName("spectral-" + player.getName());
		}
	}
}
