package net.neferett.linaris.lobby.handlers;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import net.neferett.linaris.BukkitAPI;
import net.neferett.linaris.api.GameServer;
import net.neferett.linaris.api.PlayerData;
import net.neferett.linaris.utils.RainbowEffect;
import net.neferett.linaris.utils.ScoreBoardModule;
import net.neferett.linaris.utils.ScoreboardSign;

public class Scoreboard extends ScoreBoardModule {

	public static HashMap<Player, Boolean>	jump	= new HashMap<>();
	RainbowEffect							NAME;

	String									title;

	public Scoreboard(final BukkitAPI game) {
		super(game);

		this.NAME = new RainbowEffect("LINARIS", "§f§l", "§6§l", 40);

	}

	public int getOnlinePlayers() {
		int count = 0;
		for (final GameServer s : BukkitAPI.get().getProxyDataManager().getServers().values())
			count += s.getPlayers();
		return count;
	}

	public String getTimeFormat(final int time) {

		final int minutes = time / 60;
		final int seconds = time % 60;

		return String.format("%02d", minutes) + ":" + String.format("%02d", seconds);
	}

	@Override
	public void onUpdate() {
		this.title = this.NAME.next();
	}

	@Override
	public void onUpdate(final Player p) {
		if (jump.get(p) == false) {

			ScoreboardSign bar = ScoreboardSign.get(p);
			if (bar == null) {
				bar = new ScoreboardSign(p, this.title);
				bar.create();
			}

			final HashMap<Integer, String> lines = new HashMap<>();

			final PlayerData data = BukkitAPI.get().getPlayerDataManager().getPlayerData(p.getName());

			bar.setObjectiveName(this.title);
			lines.put(13, "§8● §7§lGrade§f§l:");
			lines.put(12,
					"    §b➜" + (data.getRank().getColor() == '7' ? "§f§l" : "§" + data.getRank().getColor() + "§l")
							+ " " + data.getRank().getName());
			lines.put(11, "§d");
			lines.put(10, "§8●§e§l Coins§f§l:");
			lines.put(9, "    §b➜§f§l " + data.getEC());
			lines.put(8, "§8●§a§l Crédits§f§l:");
			lines.put(7, "    §b➜§f§l " + data.getLC());
			lines.put(6, "§8● §c§lTokens§f§l:");
			lines.put(5, "    §b➜§f§l " + data.getTokens());
			lines.put(4, "§8● §6§lJoueurs§f§l:");
			lines.put(3, "    §b➜§f§l " + this.getOnlinePlayers());
			lines.put(2, "§f");
			lines.put(1, "§e► play.linaris.fr §7§l| §a§l" + Bukkit.getServerName());

			if (lines.isEmpty())
				return;
			for (int i = 1; i < 16; i++)
				if (!lines.containsKey(i)) {
					if (bar.getLine(i) != null)
						bar.removeLine(i);
				} else if (bar.getLine(i) == null)
					bar.setLine(i, lines.get(i));
				else if (!bar.getLine(i).equals(lines.get(i)))
					bar.setLine(i, lines.get(i));
		}

	}

}
