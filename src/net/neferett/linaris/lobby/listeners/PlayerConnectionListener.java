package net.neferett.linaris.lobby.listeners;

import java.util.HashMap;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.world.ChunkUnloadEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.util.Vector;

import com.sainttx.holograms.api.Hologram;
import com.sainttx.holograms.api.line.TextLine;

import net.citizensnpcs.api.event.NPCClickEvent;
import net.citizensnpcs.api.event.NPCLeftClickEvent;
import net.citizensnpcs.api.event.NPCRightClickEvent;
import net.neferett.linaris.BukkitAPI;
import net.neferett.linaris.api.Games;
import net.neferett.linaris.api.PlayerData;
import net.neferett.linaris.api.Rank;
import net.neferett.linaris.api.SettingsManager;
import net.neferett.linaris.events.ReturnToLobbyEvent;
import net.neferett.linaris.lobby.Lobby;
import net.neferett.linaris.lobby.handlers.Scoreboard;
import net.neferett.linaris.lobby.handlers.inventories.MainMenuInventory;
import net.neferett.linaris.lobby.items.ProfileItem;
import net.neferett.linaris.lobby.items.epicchest.EpicChestItem;
import net.neferett.linaris.lobby.items.mainmenu.Boutique;
import net.neferett.linaris.lobby.items.mainmenu.Lang;
import net.neferett.linaris.lobby.items.mainmenu.MainMenuItem;
import net.neferett.linaris.lobby.items.mainmenu.Preferences;
import net.neferett.linaris.lobby.items.mainmenu.Stats;
import net.neferett.linaris.lobby.items.mainmenu.SwitchHub;
import net.neferett.linaris.lobby.items.mainmenu.spawn;
import net.neferett.linaris.lobby.npc.NPC;
import net.neferett.linaris.lobby.utils.Strings;
import net.neferett.linaris.mistery.MysteryItem;
import net.neferett.linaris.mistery.MysteryItemsManager;
import net.neferett.linaris.utils.CuboidRegion;
import net.neferett.linaris.utils.TitleUtils;
import net.neferett.linaris.utils.gui.GuiManager;
import net.neferett.linaris.utils.tasksmanager.TaskManager;

public class PlayerConnectionListener implements Listener {

	public static int					a		= -1;
	public static HashMap<Player, Long>	attack	= new HashMap<>();

	public static HashMap<Player, Long>	portail	= new HashMap<>();

	public static void box() {
		for (final Player p : Bukkit.getOnlinePlayers())
			p.getInventory().setItem(8, EpicChestItem.get().getStaticItem());
	}

	public static void replace() {
		for (final Player p : Bukkit.getOnlinePlayers()) {
			if (p.isOp())
				break;
			for (int i = 0; i < 32; i++)
				if (i != 0 && i != 1 && i != 4 && i != 8 && i != 27 && i != 30 && i != 31 && i != 32 && i != 35
						&& i != 22)
					p.getInventory().setItem(i, new ItemStack(Material.AIR));

		}
	}

	Location		portal1			= new Location(Bukkit.getWorld("world"), 7, 104, 30);
	Location		portal2			= new Location(Bukkit.getWorld("world"), -6, 132, 30);
	CuboidRegion	portalRegion	= new CuboidRegion(this.portal1, this.portal2);

	@EventHandler
	public void closeinv(final InventoryCloseEvent e) {
		final Player p = (Player) e.getPlayer();

		if (this.portalRegion.isInside(p.getLocation())
				&& e.getInventory().getTitle().equalsIgnoreCase("Menu Principal & Jeux"))
			portail.put(p, System.currentTimeMillis());
	}

	@EventHandler
	public void move(final PlayerMoveEvent e) {
		final Player p = e.getPlayer();
		if (this.portalRegion.isInside(p.getLocation()))
			if (portail.containsKey(e.getPlayer())
					&& portail.get(e.getPlayer()) / 1000 + 3.5 - System.currentTimeMillis() / 1000 <= 0
					|| !portail.containsKey(e.getPlayer())) {
				GuiManager.openGui(new MainMenuInventory(p));
				return;
			}
		if (p.getLocation().getY() < 50)
			p.teleport(Lobby.loc);
	}

	@EventHandler
	public void onChunkUnload(final ChunkUnloadEvent e) {
		for (final Entity entity : e.getChunk().getEntities())
			if (entity instanceof Villager) {
				e.setCancelled(true);
				return;
			}
	}

	@EventHandler
	public void onInvOpen(final InventoryOpenEvent e) {
		if (e.getInventory().getType() == InventoryType.MERCHANT)
			e.setCancelled(true);
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onMove(final PlayerMoveEvent e) {

		final Player p = e.getPlayer();

		if (e.getPlayer().getLocation().getBlock().getRelative(BlockFace.DOWN).getType() == Material.SEA_LANTERN)
			if (!p.isOnGround() && e.getPlayer().getVelocity().getY() > 0) {
				e.getPlayer()
						.setVelocity(e.getPlayer().getLocation().getDirection().multiply(4.0).add(new Vector(0, 8, 0)));
				e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.FIREWORK_LAUNCH, 5.0f, 2.0f);
				this.spawnfireworks(e.getPlayer());
				this.spawnfireworks(e.getPlayer());
			}

	}

	public void onNPCClick(final NPCClickEvent event) {
		final Player p = event.getClicker();
		for (final NPC npc : NPC.values())
			if (npc.getNpc().equals(event.getNPC())) {
				final PlayerData pd = BukkitAPI.get().getPlayerDataManager().getPlayerData(p.getName());
				if (npc.getInfo().getMaxPlayers() == npc.getInfo().getMaxPlayers() - 1) {
					p.sendMessage("§cLe serveur est complet !");
					return;
				}
				if (npc.getInfo().getMaxPlayers() > 9
						&& npc.getInfo().getPlayers() >= npc.getInfo().getMaxPlayers() - 3) {
					if (pd.getRank().getVipLevel() > 1)
						npc.teleport(p);
					else
						p.sendMessage("§cLes places restantes sont reservés aux §dVIPs");
					return;
				}
				npc.teleport(p);
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

	@EventHandler
	public void onPlayerHandler(final PlayerItemHeldEvent e) {
		final Player p = e.getPlayer();
		// ItemStack from = p.getInventory().getItem(e.getPreviousSlot());
		final ItemStack to = p.getInventory().getItem(e.getNewSlot());
		if (to != null && to.getType() == Material.COOKIE)
			p.setFoodLevel(19);
		else
			p.setFoodLevel(20);
	}

	@EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
	public void onPlayerJoin(final PlayerJoinEvent event) {

		final Player player = event.getPlayer();
		Scoreboard.jump.put(player, false);
		final PlayerData pd = BukkitAPI.get().getPlayerDataManager().getPlayerData(player.getName(), true);
		BukkitAPI.get().getPlayerLocalManager().getPlayerLocal(player.getName());

		player.setAllowFlight(true);
		player.setFlying(true);

		event.setJoinMessage("");
		player.setWalkSpeed(0.3F);
		player.getInventory().clear();

		player.getInventory().setItem(0, MainMenuItem.get().getStaticItem());

		player.getInventory().setItem(27, SwitchHub.get().getStaticItem());
		player.getInventory().setItem(30, Stats.get().getStaticItem());
		player.getInventory().setItem(31, spawn.get().getStaticItem());
		player.getInventory().setItem(32, Preferences.get().getStaticItem());
		player.getInventory().setItem(35, Lang.get().getStaticItem());
		player.getInventory().setItem(22, ProfileItem.getInstance(player).getStaticItem());

		player.getInventory().setItem(8, EpicChestItem.get().getStaticItem());

		player.getInventory().setItem(4, Boutique.get().getStaticItem());

		if (pd.getRank().getVipLevel() > 3)
			Bukkit.broadcastMessage(pd.getRank().getPrefix(pd) + player.getName() + " §6§oa rejoint !");
		BukkitAPI.get().getPlayerLocalManager().getPlayerLocal(player.getName()).setPrefix(pd.getRank().getTablist(pd));

		player.teleport(Lobby.loc);
		if (!pd.contains("don"))
			pd.setBoolean("don", false);
		if (!pd.contains("real_checkedbis")) {
			if (player.hasPermission("game.vip") && !player.isOp())
				pd.setRank(Rank.VIP);
			else if (player.hasPermission("game.megavip") && !player.isOp())
				pd.setRank(Rank.VIPPLUS);
			else if (player.hasPermission("game.epicvip") && !player.isOp())
				pd.setRank(Rank.SUPERVIP);
			pd.setBoolean("real_checkedbis", true);
		}
		TitleUtils.sendTitle(player, "§cLinaris...", "§aFun, PvP, émotions et plein de §cTNT §a!");

		BukkitAPI.get().getTasksManager().addTask(() -> {

			final String helmetID = SettingsManager.getSetting(player.getName(), Games.LOBBY, "armors-helmet");
			if (helmetID != null) {
				final MysteryItem item = MysteryItemsManager.getInstance().getMysteryItem(helmetID);
				if (item != null)
					item.onUse(player, false);
			}

			final String chestplateID = SettingsManager.getSetting(player.getName(), Games.LOBBY, "armors-chestplate");
			if (chestplateID != null) {
				final MysteryItem item = MysteryItemsManager.getInstance().getMysteryItem(chestplateID);
				if (item != null)
					item.onUse(player, false);
			}

			final String leggingsID = SettingsManager.getSetting(player.getName(), Games.LOBBY, "armors-leggings");
			if (leggingsID != null) {
				final MysteryItem item = MysteryItemsManager.getInstance().getMysteryItem(leggingsID);
				if (item != null)
					item.onUse(player, false);
			}

			final String bootsID = SettingsManager.getSetting(player.getName(), Games.LOBBY, "armors-boots");
			if (bootsID != null) {
				final MysteryItem item = MysteryItemsManager.getInstance().getMysteryItem(bootsID);
				if (item != null)
					item.onUse(player, false);
			}

			final String metaID = SettingsManager.getSetting(player.getName(), Games.LOBBY, "metamorphose");

			if (metaID != null) {
				final MysteryItem mitem = MysteryItemsManager.getInstance().getMysteryItem(metaID);
				if (mitem != null) {
					mitem.onUse(player, false);
					player.sendMessage(Strings.shopPrefix + "§7§oMétamorphose active: §b" + mitem.getName());
					if (!SettingsManager.isEnabled(player.getName(), Games.LOBBY, "metamorphose-viewself", true))
						player.sendMessage(
								"§7§oLa métamorphose est visible que par les autres joueurs, pour la voir vous aussi, activez l'oeil de l'ender dans le menu");
				}
			}

		});

		if (a == -1) {
			for (final NPC npc : NPC.values())
				npc.spawn();
			TaskManager.runTaskLater(() -> {
				Lobby.getInstance()
						.setHolo1(new Hologram("coco", new Location(Bukkit.getWorld("world"), 6.448, 104.812, 3.513)));
				Lobby.hologramManager.addActiveHologram(Lobby.getInstance().getHolo1());
				Lobby.getInstance().getHolo1().addLine(new TextLine(Lobby.getInstance().getHolo1(), "§dBoite Mystere"));
				Lobby.getInstance().getHolo1().addLine(
						new TextLine(Lobby.getInstance().getHolo1(), "§eCrate §f- §aClique droit pour ouvrir"));
				Lobby.getInstance().getHolo1().addLine(
						new TextLine(Lobby.getInstance().getHolo1(), "§eClique gauche §7pour regarder les §cGains"));

				Lobby.getInstance().setHolo2(
						new Hologram("coco2", new Location(Bukkit.getWorld("world"), -5.564, 104.812, 3.522)));
				Lobby.hologramManager.addActiveHologram(Lobby.getInstance().getHolo2());
				Lobby.getInstance().getHolo2().addLine(new TextLine(Lobby.getInstance().getHolo2(), "§dBoite Mystere"));
				Lobby.getInstance().getHolo2().addLine(
						new TextLine(Lobby.getInstance().getHolo2(), "§eCrate §f- §aClique droit pour ouvrir"));
				Lobby.getInstance().getHolo2().addLine(
						new TextLine(Lobby.getInstance().getHolo2(), "§eClique gauche §7pour regarder les §cGains"));
			}, 20);
			++a;
		}

	}

	@EventHandler
	public void onPlayerReturnToHUB(final ReturnToLobbyEvent e) {
		e.getTarget().teleport(Lobby.loc);
	}

	public void spawnfireworks(final Player p) {
		final Firework fw = (Firework) p.getWorld().spawnEntity(p.getLocation(), EntityType.FIREWORK);
		final FireworkMeta fwm = fw.getFireworkMeta();

		final Random r = new Random();

		final int rt = r.nextInt(4) + 1;
		Type type = Type.BALL;
		if (rt == 1)
			type = Type.BALL;
		if (rt == 2)
			type = Type.BALL_LARGE;
		if (rt == 3)
			type = Type.BURST;
		if (rt == 4)
			type = Type.CREEPER;
		if (rt == 5)
			type = Type.STAR;

		final int r1i = r.nextInt(17) + 1;
		final int r2i = r.nextInt(17) + 1;
		final Color c1 = Strings.getColor(r1i);
		final Color c2 = Strings.getColor(r2i);

		final FireworkEffect effect = FireworkEffect.builder().flicker(r.nextBoolean()).withColor(c1).withFade(c2)
				.with(type).trail(r.nextBoolean()).build();

		fwm.addEffect(effect);

		final int rp = r.nextInt(2) + 1;
		fwm.setPower(rp);

		fw.setFireworkMeta(fwm);
	}

}
