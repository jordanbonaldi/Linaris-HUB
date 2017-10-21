package net.neferett.linaris.lobby.handlers.players;

import java.util.Collection;
import java.util.HashMap;
import java.util.function.Consumer;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import net.neferett.linaris.lobby.Main;

public class PlayerManager {

	public static PlayerManager get() {
		return Main.getMainInstance().getPlayerManager();
	}

	private final HashMap<String, M_Player> players = new HashMap<>();

	public void ActionOnPlayers(final Consumer<Player> p) {
		Bukkit.getOnlinePlayers().forEach(p);
	}

	public M_Player getPlayer(final Player p) {
		if (!this.players.containsKey(p.getName().toLowerCase())) {
			final M_Player newp = new M_Player(p);
			this.players.put(p.getName().toLowerCase(), newp);
			return newp;
		}
		return this.players.get(p.getName().toLowerCase());
	}

	public Collection<M_Player> getPlayers() {
		return this.players.values();
	}

	public void removePlayer(final Player p) {
		if (this.players.containsKey(p.getName().toLowerCase()))
			this.players.remove(p.getName().toLowerCase());
	}

}
