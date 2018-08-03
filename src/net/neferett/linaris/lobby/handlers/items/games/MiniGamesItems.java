package net.neferett.linaris.lobby.handlers.items.games;

import org.bukkit.Material;

import net.neferett.linaris.api.GameServer;
import net.neferett.linaris.lobby.minigames.GamesManager;

public class MiniGamesItems extends Items {

	private final GamesManager gm;

	public MiniGamesItems(final GamesManager gm, final Material m, final boolean e, final String... strings) {
		super(gm.getG(), m, e, strings);
		this.gm = gm;
	}

	@Override
	public void load() {

		this.ib.addLores("");
		this.ib.addLores("§7Joueurs en ligne§f: §e" + this.players);
		this.ib.addLores("");
		this.ib.addLores("§7§m-----------------------");

		final GameServer g = this.gm.getSelected();

		if (g == null || !this.gm.isSelectedAlwaysAvailable()) {
			this.ib.addLores("", "§cCréation en cours...");
			return;
		}

		this.ib.addLores("§7Serveur§f: §e" + g.getServName());
		this.ib.addLores("§7Status§f: §a" + this.VIPText(g));
		if (this.gm.isLogGame())
			this.ib.addLores("§7Joueur" + (g.getPlayers() > 1 ? "s" : "") + "§f: §a" + g.getPlayers() + "§f/§a"
					+ (g.getMaxPlayers() - 1));
	}

	private String VIPText(final GameServer g) {
		return g.getMaxPlayers() == g.getPlayers() - 1 ? "§cFULL"
				: g.getPlayers() > 9 && g.getPlayers() >= g.getMaxPlayers() - 3 ? "§d✪ VIP ✪"
						: this.gm.isLogGame() ? "§aAttente..." : "§aOuvert à tous";
	}

}
