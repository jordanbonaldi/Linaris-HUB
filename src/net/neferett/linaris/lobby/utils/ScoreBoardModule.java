package net.neferett.linaris.lobby.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import net.neferett.linaris.lobby.Lobby;

public abstract class ScoreBoardModule implements Runnable {

	Lobby game;

	public ScoreBoardModule(final Lobby game) {
		this.game = game;
		game.getServer().getScheduler().scheduleSyncRepeatingTask(game, this, 0, 60);
	}

	public Lobby getGame() {
		return this.game;
	}

	public abstract void onUpdate();

	public abstract void onUpdate(Player p);

	@Override
	public void run() {
		this.update();
	}

	public void update() {
		this.onUpdate();
		for (final Player p : Bukkit.getOnlinePlayers())
			this.onUpdate(p);
	}

}
