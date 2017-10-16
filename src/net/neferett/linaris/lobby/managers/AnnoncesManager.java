package net.neferett.linaris.lobby.managers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bukkit.Bukkit;

import net.neferett.linaris.utils.PlayerUtils;
import net.neferett.linaris.utils.tasksmanager.TaskManager;
import net.neferett.linaris.lobby.Lobby;

public class AnnoncesManager {

	protected String			message;

	protected List<String>		messages;
	protected Iterator<String>	msgs;
	protected Lobby				plugin;
	protected int				time	= 0;

	public AnnoncesManager(final Lobby plugin) {
		this.plugin = plugin;

		this.messages = new ArrayList<>();
		this.messages.add("§6Vous pouvez acheter des §cTokens §6en jeu avec §c/token §6!");
		this.messages.add("§eSuivez nous sur §dTwitter§e ! §b§n@LinarisMC");
		this.messages.add(
				"§7§lEnvie d'un §d§lgrade§7§l, §a§l+ §7§lde §b§lcrédits§7§l, §e§lcoins §7§lou §c§ltokens§7§l, §7§lou des §a§lclés §7§l? §c§l/shop §7§l!");
		this.messages.add("§eRejoignez-nous sur §bTeamSpeak§e ! §6§nts.linaris.fr");
		this.msgs = this.messages.iterator();

		TaskManager.scheduleSyncRepeatingTask("Broadcasts", this::update, 0, 20);

	}

	public void update() {
		this.time--;
		if (this.time <= 0) {
			if (!this.msgs.hasNext()) this.msgs = this.messages.iterator();

			this.message = this.msgs.next();

			this.time = 10;
		}

		if (this.time > 1) Bukkit.getOnlinePlayers().forEach(p -> {
			PlayerUtils.sendActionMessage(p, this.message);
		});
	}

}
