package net.neferett.linaris.lobby.minigames;

import java.util.LinkedList;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import net.neferett.linaris.BukkitAPI;
import net.neferett.linaris.api.GameServer;
import net.neferett.linaris.api.Games;
import net.neferett.linaris.lobby.utils.Utils;
import net.neferett.linaris.utils.PlayerUtils;

public class GamesManager {

	private final CreatorManager	cm;
	private final Games				g;
	private GameServer				selected;
	private LinkedList<GameServer>	servers;

	public GamesManager(final Games g, final String mode, final boolean ml) {
		this.g = g;
		this.cm = new CreatorManager(g.getGameName().contains("cod") ? "COD" : g.getDisplayName(), mode, ml);
	}

	public void createGame() {
		System.out.println("CREATION LAUNCH REQUEST");
		this.cm.build();
	}

	private void fillGames() {
		this.servers = BukkitAPI.get().getProxyDataManager().getServersByGameName(this.g.getDisplayName());
	}

	public Games getG() {
		return this.g;
	}

	public int getPlayers() {
		return BukkitAPI.get().getProxyDataManager().getPlayerInGame(this.g);
	}

	public GameServer getSelected() {

		final LinkedList<GameServer> s = this.joinableGames();

		if (s.size() == 0)
			return null;
		return BukkitAPI.get().getProxyDataManager().getServersByGameName(this.g.getDisplayName()).stream()
				.filter((a) -> a.getServName().equals(s.get(0).getServName())).findFirst().orElse(null);
	}

	public boolean isSelectedAlwaysAvailable() {
		return this.isValidGames(this.selected);
	}

	private boolean isValidGames(final GameServer g) {
		return g == null ? false
				: g.canJoin() && g.canSee() && g.getPlayers() > 0 && g.getPlayers() == g.getMaxPlayers() - 1 ? false
						: g.getPlayers() < g.getMaxPlayers() && g.canJoin() && g.canSee();

	}

	private LinkedList<GameServer> joinableGames() {
		final LinkedList<GameServer> js = new LinkedList<>();
		this.fillGames();
		this.servers.forEach((g) -> {
			if (this.isValidGames(g))
				js.add(g);
		});
		return js;
	}

	public boolean needCreation() {
		return this.joinableGames().size() < 1;
	}

	public void selectGame() {
		final LinkedList<GameServer> js = this.joinableGames();
		this.selected = js.size() == 0 ? null : js.getFirst();
	}

	public void TeleportToSelectedGame(final Player p) {
		if (this.isSelectedAlwaysAvailable()) {
			p.sendMessage(Utils.colorize(new Object[] { "&a&oTransfert vers " + this.selected.getGameName() }));
			PlayerUtils.goToServer(p, this.getSelected());
		} else
			p.sendMessage(ChatColor.RED + "Attendez quelques secondes le temps que le serveur se créer ...");
	}

}
