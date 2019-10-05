package net.neferett.linaris.handlers;

import java.util.HashMap;

import net.neferett.linaris.minigames.GamesEnum;
import org.bukkit.entity.Player;

import net.neferett.linaris.BukkitAPI;
import net.neferett.linaris.api.GameServer;
import net.neferett.linaris.api.PlayerData;
import net.neferett.linaris.utils.RainbowEffect;
import net.neferett.linaris.utils.ScoreBoardModule;
import net.neferett.linaris.utils.ScoreboardSign;

public class Scoreboard extends ScoreBoardModule {

	RainbowEffect	NAME;

	String			title;

	public Scoreboard(final BukkitAPI game) {
		super(game);

		this.NAME = new RainbowEffect("BattleMine", "§l", "§6§l", 40);

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

		ScoreboardSign bar = ScoreboardSign.get(p);
		if (bar == null) {
			bar = new ScoreboardSign(p, this.title);
			bar.create();
		}

		final HashMap<Integer, String> lines = new HashMap<>();

		final PlayerData data = BukkitAPI.get().getPlayerDataManager().getPlayerData(p.getName());

		bar.setObjectiveName(this.title);
		lines.put(12, "§f");
		lines.put(11, "§fGrade:" + (data.getRank().getColor() == '7' ? "§f"
				: "§" + data.getRank().getColor() + " " + data.getRank().getName()));
		lines.put(10, "§2");
		lines.put(9, "§fJeu préféré§f: §e" + GamesEnum.getMostPlayedGame(data));
		lines.put(8, "§1");
		lines.put(7, "Senzus: §e" + data.getTokens());
		lines.put(6, "§9");
		lines.put(5, "§fHub§f: §e#" + BukkitAPI.get().getServerInfos().getServerName().charAt(3));
		lines.put(4, "§e");
		lines.put(3, "§fJoueurs§f: §a" + this.getOnlinePlayers());
		lines.put(2, "§a");
		lines.put(1, "§e► play.battlemine.fr");

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
