package net.neferett.linaris.lobby.jump;

import java.util.concurrent.ConcurrentHashMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import net.neferett.linaris.BukkitAPI;
import net.neferett.linaris.api.Games;
import net.neferett.linaris.utils.tasksmanager.TaskManager;
import net.neferett.linaris.lobby.Lobby;
import net.neferett.linaris.lobby.managers.ScoreboardManager;
import net.neferett.linaris.lobby.utils.ScoreboardSign;

public class JumpsManager {

	protected Lobby plugin;

	protected Location leaderboardLocation;
	protected Location startLocation;
	protected Location baseLocation;
	protected ConcurrentHashMap<Integer, Location> checkpoints = new ConcurrentHashMap<>();

	protected ConcurrentHashMap<String, JumpInfo> cachedData = new ConcurrentHashMap<>();

	public JumpsManager(Lobby plugin) {
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(new PlayerJumpListener(this), plugin);
		plugin.getCommand("jump").setExecutor(new JumpCommand(this));

		World world = plugin.getServer().getWorld("world");
		startLocation = new Location(world, -2197.434, 162, -440.490);
		leaderboardLocation = new Location(world, -2197.490, 162, -436.555);
		baseLocation = new Location(world, -2195, 161, -442.956);
		baseLocation.setYaw((float)46.8);
		baseLocation.setPitch((float)-3.1);
		checkpoints.put(1, new Location(world, -2229.467, 176, -414.526));
		checkpoints.put(2, new Location(world, -2266.496, 200, -395.449));
		checkpoints.put(3, new Location(world, -2313.196, 196, -387.462));
		checkpoints.put(4, new Location(world, -2275.555, 129, -393.556));
		checkpoints.put(5, new Location(world, -2149.350, 139, -361.600));
		checkpoints.put(6, new Location(world, -2180.517, 183, -371.502));
		checkpoints.put(7, new Location(world, -2183.478, 201, -433.449));
		
		
	}

	public Location getBaseLocation() {
		return baseLocation;
	}

	public Location getStartLocation() {
		return startLocation;
	}
	
	public Location getLeaderboardLocation() {
		return leaderboardLocation;
	}

	public ConcurrentHashMap<Integer, Location> getCheckpoints() {
		return checkpoints;
	}

	public ConcurrentHashMap<String, JumpInfo> getCachedData() {
		return cachedData;
	}

	public Lobby getPlugin() {
		return plugin;
	}

	public boolean inJump(Player p) {
		return (cachedData.containsKey(p.getName().toLowerCase()));
	}

	public int containsBlock(Block b) {
		for (int i = 1; i <= checkpoints.size(); i++) {
			if (checkpoints.get(i).getBlock().equals(b))
				return i;
		}
		return -1;
	}

	public void startJump(Player p) {
		if (inJump(p))
			return;
		String name = p.getName().toLowerCase();
		JumpInfo info = new JumpInfo(name);
		info.setManager(new JumpScoreboardsManager(this, p, info));

		cachedData.put(name, info);
		p.sendMessage("§7Vous commencez le jump des §bDieux§7, bonne chance !");
		p.setAllowFlight(false);
		p.setFlying(false);
		ScoreboardManager.jump.put(p, true);
	}

	public void stopJump(Player p) {
		if (!inJump(p))
			return;
		cachedData.remove(p.getName().toLowerCase());
		TaskManager.cancelTaskByName("Jump_" + p.getName());
		p.setAllowFlight(true);
		p.setFlying(true);
		ScoreboardManager.jump.put(p.getPlayer(), false);
		ScoreboardSign bar = ScoreboardSign.get(p);
		if (bar != null) {
			bar.destroy();
		}
	}

	public JumpInfo getJumpInfo(Player p) {
		if (!inJump(p))
			return null;
		return cachedData.get(p.getName().toLowerCase());
	}

	public void die(Player p) {
		if (!inJump(p))
			return;
		JumpInfo info = getJumpInfo(p);
		if (info.getNowCheckpoint() == 0) {
			p.teleport(getBaseLocation());
			stopJump(p);
		} else {
			info.setLife(info.getLife() - 1);
			if (info.getLife() < 0) {
				p.teleport(getBaseLocation());
				p.sendMessage("§7Vous avez échoué... :(");
				stopJump(p);
			} else {
				p.teleport(checkpoints.get(info.getNowCheckpoint()));
				p.sendMessage("§7Vous êtes tombé... il vous reste §c" + info.getLife() + " §7vie(s)");
			}
		}

	}

	public void checkPoint(Player p, int checkpoint) {
		if (!inJump(p))
			return;
		JumpInfo info = getJumpInfo(p);
		if (info.getNowCheckpoint() >= checkpoint)
			return;
		if (info.getNowCheckpoint()+1 != checkpoint)
			return;
		info.setNowCheckpoint(checkpoint);
		if (checkpoint == 1) {
			info.giveLife(8);
			p.sendMessage("§7CheckPoint §e+8 Vies §7!");
		} else if (checkpoint == 7) {
			long time = info.getTotalTime();
			Bukkit.broadcastMessage("§a" + p.getName() + " §7a terminé le §b§lJump Des Dieux§7: §e" + getTimeFormat(time) + " §7et §e" + info.getLife() + "§c�?�");
			ScoreboardManager.jump.put(p, false);
			try {
				BukkitAPI.get().getStatsManager().setValue(info.getPlayerName(), Games.GOODJUMP, "time", time);
			}catch (Exception e) {}
			stopJump(p);
		} else {

			info.giveLife(3);
			p.sendMessage("§7CheckPoint §e+3 Vies §7!");
		}

	}
	
	public String getTimeFormat(long time) {
		
		int minutes = (int) time/60;
		int seconds = (int) time%60;
		
		return String.format("%02d", minutes) + ":" + String.format("%02d", seconds);
	}
}
