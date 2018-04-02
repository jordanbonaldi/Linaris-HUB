package net.neferett.linaris.lobby.handlers.items.games;

import org.bukkit.Material;

import net.neferett.linaris.api.Games;

public class GamesItems extends Items {

	public GamesItems(final Games g, final Material m, final boolean e, final String... strings) {
		super(g, m, e, strings);
	}

	@Override
	public void load() {
		this.ib.addLores("�aClique pour te connecter");
		this.ib.addLores(this.lores);
		this.ib.addLores("", "�6Nombre de joueurs�f: �b" + this.players, "");
	}

}
