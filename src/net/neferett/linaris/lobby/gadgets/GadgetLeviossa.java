package net.neferett.linaris.lobby.gadgets;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import net.neferett.linaris.utils.Particles;
import net.neferett.linaris.utils.tasksmanager.TaskManager;
import net.neferett.linaris.lobby.Lobby;

public class GadgetLeviossa extends GadgetItem {
	
	public GadgetLeviossa() {
		super("§6Leviossa", new ItemStack(Material.BLAZE_ROD), "GadgetItemLeviossaFlag", 5,"§7Han leviossaaa", "§rPour léviter");
	}

	private void tpLater(final Player player, final Vector vector, long delay) {
		TaskManager.scheduler.runTaskLater(Lobby.getInstance(), new Runnable() {
			@Override
			public void run() {
				player.setVelocity(vector);
			}
		}, delay);
	}


	@Override
	public void onUse(Player player) {
		Location location = player.getLocation();
		Particles.LAVA.display(0.5F, 0.5F, 0.5F, 1, 5,location,new ArrayList<>(Bukkit.getOnlinePlayers()));
		location.add(0, 1, 0);
		Particles.CLOUD.display(0.5F, 0.5F, 0.5F, 1, 5,location,new ArrayList<>(Bukkit.getOnlinePlayers()));
//		ParticleUtils.displayParticlesOnEntity(player, 5, 5, 3, Particles.SPELL, 3, 0, 0.5F, 0.1F, 0.5F,50, new Vector(0, -0.3F, 0));
//		ParticleUtils.displayParticlesOnEntity(player, 5, 1, 3, Particles.SPELL_INSTANT, 5, 0, 0.1F, 0.3F, 0.1F, 50,new Vector(0, -0.3F, 0));
		// player.teleport(location);
		player.setVelocity(new Vector(0, 2, 0));
		location.getWorld().playSound(location, Sound.CREEPER_HISS, 0.8F, 0.5F);
		location.getWorld().playSound(location, Sound.LEVEL_UP, 0.8F, 1.6F);

		for (int i = 0; i < 5; i++)
			tpLater(player, new Vector(0, 0.15, 0), 5 + i * 5);
	}

	@Override
	public void onOption(Player player) {
		// TODO Auto-generated method stub
		
	}
}
