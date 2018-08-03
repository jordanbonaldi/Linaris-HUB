package net.neferett.linaris.lobby;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import com.sainttx.holograms.api.HologramManager;
import com.sainttx.holograms.api.HologramPlugin;

import net.neferett.linaris.api.API;
import net.neferett.linaris.lobby.handlers.Scoreboard;
import net.neferett.linaris.lobby.handlers.games.GamesManager;
import net.neferett.linaris.lobby.handlers.items.ItemsManager;
import net.neferett.linaris.lobby.handlers.items.magicbox.MagicboxItem;
import net.neferett.linaris.lobby.handlers.items.mainmenu.Boutique;
import net.neferett.linaris.lobby.handlers.items.mainmenu.MainMenuItem;
import net.neferett.linaris.lobby.handlers.items.mainmenu.Stats;
import net.neferett.linaris.lobby.handlers.items.mainmenu.SwitchHub;
import net.neferett.linaris.lobby.handlers.items.mainmenu.spawn;
import net.neferett.linaris.lobby.listeners.InteractOnNPC;
import net.neferett.linaris.lobby.listeners.InventoryListener;
import net.neferett.linaris.lobby.listeners.JoinAndLeave;
import net.neferett.linaris.lobby.listeners.MoveListener;
import net.neferett.linaris.lobby.listeners.NaturalListener;
import net.neferett.linaris.lobby.listeners.PlayerChat;
import net.neferett.linaris.lobby.listeners.PlayerEvents;
import net.neferett.linaris.lobby.minigames.GamesEnum;
import net.neferett.linaris.lobby.minigames.GamesThread;
import net.neferett.linaris.mistery.mounts.MountManager;
import net.neferett.linaris.mistery.mounts.PlayerMountListener;
import net.neferett.linaris.utils.tasksmanager.TaskManager;

public class Main extends API {

	static Main i;

	public static Main getMainInstance() {
		return i;
	}

	boolean			close		= false;

	HologramManager	hologramManager;

	public boolean	willClose	= false;

	public Main() {
		super("Lobby", "Lobby", 100);
	}

	public void additems() {
		ItemsManager.get().addItems(Boutique.get(), 0);
		ItemsManager.get().addItems(Stats.get(), 2);
		ItemsManager.get().addItems(MainMenuItem.get(), 4);
		ItemsManager.get().addItems(SwitchHub.get(), 27);
		ItemsManager.get().addItems(spawn.get(), 35);
	}

	@Override
	public void addRanks() {
		// TODO Auto-generated method stub

	}

	public HologramManager getHologramManager() {
		return this.hologramManager;
	}

	public boolean isClose() {
		return this.close;
	}

	public boolean isWillClose() {
		return this.willClose;
	}

	// public void loadHolos() {
	// HologramsManager.get().addHologram("box1", new
	// Location(Bukkit.getWorld("world"), 6.448, 104.812, 3.513),
	// "§dBoite Mystere", "§eCrate §f- §aClique droit pour ouvrir",
	// "§eClique gauche §7pour regarder les §cGains");
	// HologramsManager.get().addHologram("box2", new
	// Location(Bukkit.getWorld("world"), -5.564, 104.812, 3.522),
	// "§dBoite Mystere", "§eCrate §f- §aClique droit pour ouvrir",
	// "§eClique gauche §7pour regarder les §cGains");
	// }

	@Override
	public void onClose() {
		this.willClose = true;
		this.closeServer();
		this.close = true;
		Arrays.asList(GamesEnum.values()).forEach((a) -> a.getN().Dispawn());
	}

	@Override
	public void onLoading() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onOpen() {
		i = this;
		this.hologramManager = JavaPlugin.getPlugin(HologramPlugin.class).getHologramManager();

		this.openServer();

		this.handleWorld();

		this.setScoreBoard(Scoreboard.class);

		// this.loadHolos();

		this.additems();

		this.w.setDifficulty(Difficulty.EASY);
		this.w.getEntities().forEach(e -> {
			e.remove();
		});

		this.RegisterAllEvents(new PlayerEvents(), new NaturalListener(), new PlayerChat(), new PlayerMountListener(),
				new InteractOnNPC(), new InventoryListener(), new JoinAndLeave(), new MoveListener());

		TaskManager.scheduleSyncRepeatingTask("box", () -> {

			Bukkit.getOnlinePlayers().forEach(p -> {
				p.getInventory().setItem(8, MagicboxItem.get().getStaticItem());
				final AtomicInteger i = new AtomicInteger();
				for (i.set(0); i.get() < 32; i.incrementAndGet())
					if (ItemsManager.get().getItems().values().stream().filter(e -> i.get() != e).findFirst() == null)
						p.getInventory().setItem(i.get(), new ItemStack(Material.AIR));
			});

		}, 0, 60);

		TaskManager.scheduleSyncRepeatingTask("test", () -> {

			GamesManager.getInstance().inits();

		}, 0, 3 * 20);

		MountManager.init();
		MagicboxItem.init();

		if (this.getServer().getPluginManager().getPlugin("Citizens") == null
				|| !this.getServer().getPluginManager().getPlugin("Citizens").isEnabled()) {
			System.out.println("Citizens 2.0 not found or not enabled");
			this.getServer().getPluginManager().disablePlugin(this);
			return;
		}

		this.setAPIMode(true);

		this.setAnnounce();

		new Thread(new GamesThread()).start();

		this.addHealthNameTag();

		Arrays.asList(GamesEnum.values()).forEach((a) -> {
			a.getN().Spawn();
			a.getN().createHolo(a.getGm());
		});

	}

	@Override
	public void RegisterCommands() {
		// TODO Auto-generated method stub

	}

}
