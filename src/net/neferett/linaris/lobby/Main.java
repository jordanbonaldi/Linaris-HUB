package net.neferett.linaris.lobby;

import org.bukkit.Difficulty;
import org.bukkit.entity.Monster;
import org.bukkit.plugin.java.JavaPlugin;

import com.sainttx.holograms.api.Hologram;
import com.sainttx.holograms.api.HologramManager;
import com.sainttx.holograms.api.HologramPlugin;

import net.neferett.linaris.api.API;
import net.neferett.linaris.lobby.handlers.Scoreboard;
import net.neferett.linaris.lobby.handlers.games.GamesManager;
import net.neferett.linaris.lobby.handlers.players.PlayerManager;
import net.neferett.linaris.lobby.items.epicchest.EpicChestItem;
import net.neferett.linaris.lobby.listeners.NaturalListener;
import net.neferett.linaris.lobby.listeners.PlayerConnectionListener;
import net.neferett.linaris.lobby.listeners.PlayerEvents;
import net.neferett.linaris.mistery.mounts.MountManager;
import net.neferett.linaris.mistery.mounts.PlayerMountListener;
import net.neferett.linaris.utils.tasksmanager.TaskManager;

public class Main extends API {

	static Main i;

	public static Main getMainInstance() {
		return i;
	}

	Hologram		holo1;
	Hologram		holo2;

	HologramManager	hologramManager;

	PlayerManager	pm;

	public Main() {
		super("Lobby", "Lobby", 100);
	}

	@Override
	public void addRanks() {
		// TODO Auto-generated method stub

	}

	public Hologram getHolo1() {
		return this.holo1;
	}

	public Hologram getHolo2() {
		return this.holo2;
	}

	public PlayerManager getPlayerManager() {
		return this.pm;
	}

	@Override
	public void onClose() {
		this.closeServer();
	}

	@Override
	public void onLoading() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onOpen() {
		i = this;
		this.pm = new PlayerManager();
		this.hologramManager = JavaPlugin.getPlugin(HologramPlugin.class).getHologramManager();

		this.openServer();
		this.handleWorld();

		this.setScoreBoard(Scoreboard.class);

		this.setAnnounce();

		this.w.setDifficulty(Difficulty.PEACEFUL);
		this.w.getEntities().forEach(e -> {
			if (e instanceof Monster && e.getLocation().getWorld().getName().equals("world"))
				e.remove();
		});

		this.RegisterAllEvents(new PlayerConnectionListener(), new PlayerEvents(), new NaturalListener(),
				new PlayerMountListener());

		TaskManager.scheduleSyncRepeatingTask("inv", () -> {

			PlayerConnectionListener.replace();

		}, 0, 1);

		TaskManager.scheduleSyncRepeatingTask("box", () -> {

			PlayerConnectionListener.box();

		}, 0, 60);

		TaskManager.scheduleSyncRepeatingTask("test", () -> {

			GamesManager.getInstance().inits();

		}, 0, 3 * 20);

		MountManager.init();
		GamesManager.getInstance().inits();

		EpicChestItem.init();

		if (this.getServer().getPluginManager().getPlugin("Citizens") == null
				|| !this.getServer().getPluginManager().getPlugin("Citizens").isEnabled()) {
			System.out.println("Citizens 2.0 not found or not enabled");
			this.getServer().getPluginManager().disablePlugin(this);
			return;
		}

		this.setAPIMode(true);

		this.addHealthNameTag();

	}

	@Override
	public void RegisterCommands() {
		// TODO Auto-generated method stub

	}

	public void setHolo1(final Hologram holo1) {
		this.holo1 = holo1;
	}

	public void setHolo2(final Hologram holo2) {
		this.holo2 = holo2;
	}

}
