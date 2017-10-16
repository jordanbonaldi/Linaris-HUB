package net.neferett.linaris.lobby;

import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.plugin.java.JavaPlugin;

import com.sainttx.holograms.api.Hologram;
import com.sainttx.holograms.api.HologramManager;
import com.sainttx.holograms.api.HologramPlugin;

import net.neferett.linaris.BukkitAPI;
import net.neferett.linaris.api.ServerInfo;
import net.neferett.linaris.lobby.games.GamesManager;
import net.neferett.linaris.lobby.items.epicchest.EpicChestItem;
import net.neferett.linaris.lobby.jump.JumpsManager;
import net.neferett.linaris.lobby.listeners.NaturalListener;
import net.neferett.linaris.lobby.listeners.PermissionsModuleListener;
import net.neferett.linaris.lobby.listeners.PlayerChatListener;
import net.neferett.linaris.lobby.listeners.PlayerConnectionListener;
import net.neferett.linaris.lobby.managers.AnnoncesManager;
import net.neferett.linaris.lobby.managers.PermissionsModuleManager;
import net.neferett.linaris.lobby.managers.ScoreboardManager;
import net.neferett.linaris.lobby.npc.NPC;
import net.neferett.linaris.mistery.mounts.MountManager;
import net.neferett.linaris.mistery.mounts.PlayerMountListener;
import net.neferett.linaris.utils.ServerUtils;
import net.neferett.linaris.utils.tasksmanager.TaskManager;

public class Lobby extends JavaPlugin {

	public static HologramManager	hologramManager;
	private static Lobby			instance;
	public static Location			loc;
	public static boolean			open;

	public static Lobby getInstance() {
		return instance;
	}

	private AnnoncesManager				annoncesManager;
	private Hologram					holo1;
	private Hologram					holo2;

	ServerInfo							infos;

	private JumpsManager				jumpsManager;

	private PermissionsModuleManager	permsManager;

	public AnnoncesManager getAnnoncesManager() {
		return this.annoncesManager;
	}

	public Hologram getHolo1() {
		return this.holo1;
	}

	public Hologram getHolo2() {
		return this.holo2;
	}

	public ServerInfo getInfos() {
		return this.infos;
	}

	public JumpsManager getJumpsManager() {
		return this.jumpsManager;
	}

	public PermissionsModuleManager getPermissionsManager() {
		return this.permsManager;
	}

	public String getTimeFormat(final long time) {

		final int minutes = (int) time / 60;
		final int seconds = (int) time % 60;

		return String.format("%02d", minutes) + ":" + String.format("%02d", seconds);
	}

	@Override
	public void onDisable() {
		open = false;
		for (final NPC npc : NPC.values())
			npc.dispawn();

		this.getHolo1().despawn();
		this.infos.setCanJoin(false, false);
		this.infos.setCanSee(false, false);

	}

	@Override
	public void onEnable() {
		open = true;
		BukkitAPI.get().setScoreload(true);
		hologramManager = JavaPlugin.getPlugin(HologramPlugin.class).getHologramManager();
		ServerUtils.changeMaxPlayers(100);
		final World world = Bukkit.getWorlds().get(0);
		world.setStorm(false);
		world.setThundering(false);
		world.setWeatherDuration(Integer.MAX_VALUE);
		world.setFullTime(6000);
		world.setGameRuleValue("doDaylightCycle", "false");
		world.setGameRuleValue("doFireTick", "false");
		world.setDifficulty(Difficulty.EASY);
		world.setSpawnFlags(false, false);
		for (final Entity e : world.getEntities())
			e.remove();

		loc = new Location(Bukkit.getWorld("world"), 0.476, 103, -3.617, (float) -0.4, (float) 0.5);
		this.permsManager = new PermissionsModuleManager();
		this.permsManager.itemPickup = true;

		this.jumpsManager = new JumpsManager(this);
		this.annoncesManager = new AnnoncesManager(this);
		//
		this.getServer().getPluginManager().registerEvents(new PlayerConnectionListener(), this);
		this.getServer().getPluginManager().registerEvents(new PermissionsModuleListener(this), this);
		this.getServer().getPluginManager().registerEvents(new PlayerChatListener(), this);
		this.getServer().getPluginManager().registerEvents(new NaturalListener(), this);
		this.getServer().getPluginManager().registerEvents(new PlayerMountListener(), this);

		/*
		 * List<String> leaderboard = new ArrayList<String>();
		 * leaderboard.add("§6Classement des joueurs"); int number = 1; for
		 * (Entry<String, Double> scores :
		 * BukkitAPI.get().getStatsManager().getStatsValueDec(Games.GOODJUMP,
		 * "time", 9).entrySet()) leaderboard.add("§e" + number++ + "§7> §a" +
		 * scores.getKey() + " §7temps: §e" +
		 * getTimeFormat(scores.getValue().intValue())); Hologram hologram1 =
		 * new Hologram("2",this.jumpsManager.getLeaderboardLocation());
		 * hologramManager.addActiveHologram(hologram1); hologram1.addLine(new
		 * TextLine(hologram1, leaderboard.get(0))); hologram1.addLine(new
		 * TextLine(hologram1, leaderboard.get(1))); hologram1.addLine(new
		 * TextLine(hologram1, leaderboard.get(2))); hologram1.addLine(new
		 * TextLine(hologram1, leaderboard.get(3))); hologram1.addLine(new
		 * TextLine(hologram1, leaderboard.get(4))); hologram1.addLine(new
		 * TextLine(hologram1, leaderboard.get(5))); hologram1.addLine(new
		 * TextLine(hologram1, leaderboard.get(6))); if(leaderboard.size() ==
		 * 8){ hologram1.addLine(new TextLine(hologram1, leaderboard.get(7))); }
		 * if(leaderboard.size() == 9){ hologram1.addLine(new
		 * TextLine(hologram1, leaderboard.get(8))); } if(leaderboard.size() ==
		 * 10){ hologram1.addLine(new TextLine(hologram1, leaderboard.get(9)));
		 * }
		 */

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

		// ProtocolLibrary.getProtocolManager()
		// .addPacketListener(new PacketAdapter(this,
		// PacketType.Play.Client.ENTITY_ACTION) {
		//
		// @Override
		// public void onPacketReceiving(PacketEvent event) {
		//
		// int action = event.getPacket().getIntegers().read(1);
		// if (action == 0) {
		// event.setCancelled(true);
		// }
		// }
		//
		// });

		// ProtocolLibrary.getProtocolManager()
		// .addPacketListener(new PacketAdapter(this,
		// PacketType.Play.Server.POSITION) {
		//
		// @Override
		// public void onPacketSending(PacketEvent event) {
		//
		// event.setCancelled(true);
		//
		// }
		//
		// });

		EpicChestItem.init();

		if (this.getServer().getPluginManager().getPlugin("Citizens") == null
				|| !this.getServer().getPluginManager().getPlugin("Citizens").isEnabled()) {
			System.out.println("Citizens 2.0 not found or not enabled");
			this.getServer().getPluginManager().disablePlugin(this);
			return;
		}

		new ScoreboardManager(this);

		BukkitAPI.get().getTasksManager().addTask(() -> {
			this.infos.setCanJoin(true, true);
			this.infos.setCanSee(true, true);
		});

		new AnnoncesManager(this);

		this.saveDefaultConfig();

	}

	@Override
	public void onLoad() {
		instance = this;
		this.infos = BukkitAPI.get().getServerInfos();
		this.infos.setGameName("Lobby");
		this.infos.setMapName("Lobby");
	}

	public void setHolo1(final Hologram holo1) {
		this.holo1 = holo1;
	}

	public void setHolo2(final Hologram holo2) {
		this.holo2 = holo2;
	}

}
