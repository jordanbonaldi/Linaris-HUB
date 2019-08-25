package net.neferett.linaris.handlers.items.games;

import net.neferett.linaris.minigames.GamesManager;
import org.bukkit.Material;

import net.neferett.linaris.api.GameServer;

public class MiniGamesItems extends Items {

	private final GamesManager gm;

	public MiniGamesItems(final GamesManager gm, final Material m, final boolean e, final String... strings) {
		super(gm.getG(), m, e, strings);
		this.gm = gm;
	}

	@Override
	public void load() {

		this.ib.addLores("");
		this.ib.addLores("net.neferett.linaris7Joueurs en lignenet.neferett.linarisf: net.neferett.linarise" + this.players);
		this.ib.addLores("");
		this.ib.addLores("net.neferett.linaris7net.neferett.linarism-----------------------");

		final GameServer g = this.gm.getSelected();

		if (g == null || !this.gm.isSelectedAlwaysAvailable()) {
			this.ib.addLores("", "net.neferett.linariscCréation en cours...");
			return;
		}

		this.ib.addLores("net.neferett.linaris7Serveurnet.neferett.linarisf: net.neferett.linarise" + g.getServName());
		this.ib.addLores("net.neferett.linaris7Statusnet.neferett.linarisf: net.neferett.linarisa" + this.VIPText(g));
		if (this.gm.isLogGame())
			this.ib.addLores("net.neferett.linaris7Joueur" + (g.getPlayers() > 1 ? "s" : "") + "net.neferett.linarisf: net.neferett.linarisa" + g.getPlayers() + "net.neferett.linarisf/net.neferett.linarisa"
					+ (g.getMaxPlayers() - 1));
	}

	private String VIPText(final GameServer g) {
		return g.getMaxPlayers() == g.getPlayers() - 1 ? "net.neferett.linariscFULL"
				: g.getPlayers() > 9 && g.getPlayers() >= g.getMaxPlayers() - 3 ? "net.neferett.linarisd✪ VIP ✪"
						: this.gm.isLogGame() ? "net.neferett.linarisaAttente..." : "net.neferett.linarisaOuvert à tous";
	}

}
