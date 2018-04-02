package net.neferett.linaris.lobby.listeners;

import java.util.Arrays;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkUnloadEvent;

import net.citizensnpcs.api.event.NPCClickEvent;
import net.citizensnpcs.api.event.NPCLeftClickEvent;
import net.citizensnpcs.api.event.NPCRightClickEvent;
import net.neferett.linaris.BukkitAPI;
import net.neferett.linaris.api.PlayerData;
import net.neferett.linaris.lobby.minigames.GamesEnum;

public class InteractOnNPC implements Listener {

	@EventHandler
	public void onChunkUnload(final ChunkUnloadEvent e) {
		for (final Entity entity : e.getChunk().getEntities())
			if (entity instanceof Villager) {
				e.setCancelled(true);
				return;
			}
	}

	public void onNPCClick(final NPCClickEvent event) {
		final Player p = event.getClicker();
		for (final GamesEnum g : Arrays.asList(GamesEnum.values()))
			if (g.getN().getNpc().equals(event.getNPC())) {
				final PlayerData pd = BukkitAPI.get().getPlayerDataManager().getPlayerData(p.getName());
				if (g.getGm().getSelected() != null
						&& g.getGm().getSelected().getMaxPlayers() == g.getGm().getSelected().getMaxPlayers() - 1) {
					p.sendMessage("§cLe serveur est complet !");
					return;
				}
				if (g.getGm().getSelected() != null && g.getGm().getSelected().getMaxPlayers() > 9
						&& g.getGm().getSelected().getPlayers() >= g.getGm().getSelected().getMaxPlayers() - 3) {
					if (pd.getRank().getVipLevel() > 1)
						g.getGm().TeleportToSelectedGame(p);
					else
						p.sendMessage("§cLes places restantes sont reservés aux §dVIPs");
					return;
				}
				g.getGm().TeleportToSelectedGame(p);
				break;
			}
	}

	@EventHandler
	public void onNPCLeftClick(final NPCLeftClickEvent event) {
		this.onNPCClick(event);
	}

	@EventHandler
	public void onNPCRightClick(final NPCRightClickEvent event) {
		this.onNPCClick(event);
	}

}
