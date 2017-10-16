package net.neferett.linaris.lobby.npc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton.SkeletonType;
import org.bukkit.entity.Villager.Profession;

import com.google.common.collect.Lists;
import com.sainttx.holograms.api.Hologram;
import com.sainttx.holograms.api.line.TextLine;
import com.sainttx.holograms.api.line.UpdatingHologramLine;

import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPCRegistry;
import net.citizensnpcs.trait.LookClose;
import net.citizensnpcs.trait.NPCSkeletonType;
import net.citizensnpcs.trait.Powered;
import net.citizensnpcs.trait.VillagerProfession;
import net.minecraft.server.v1_8_R3.World;
import net.neferett.linaris.BukkitAPI;
import net.neferett.linaris.api.GameServer;
import net.neferett.linaris.api.Games;
import net.neferett.linaris.lobby.Lobby;
import net.neferett.linaris.lobby.utils.Utils;
import net.neferett.linaris.utils.PlayerUtils;

public enum NPC {

	BB("BuildBattle", Games.BuildBattle, null, EntityType.PIG_ZOMBIE, "world", 18.387, 103, 18.533),
	COD("Call Of Duty", Games.COD, null, EntityType.BLAZE, "world", -15.563, 103, 21.464),
	Hika("HikaBrain", Games.HikaBrain, null, EntityType.VILLAGER, "world", -5.487, 103, 14.4),
	RUSH_PLAY("Rush", Games.RUSH, "2vs2", EntityType.VILLAGER, "world", 6.441, 103, 14.504),
	SkyWars("SkyWars", Games.SKYWARS, null, EntityType.SKELETON, "world", 10.532, 103, 26.423),
	SURVIVOR("Survivor", Games.SURVIVOR, null, EntityType.ZOMBIE, "world", 13.479, 103, 24.553),
	Tower("Towers", Games.TOWERS, "2vs2", EntityType.SKELETON, "world", 16.501, 103, 21.573),
	UHCSolo("UHCRunSolo", Games.UHCRUNSolo, null, EntityType.CREEPER, "world", -12.546, 103, 24.472),
	UHCTeam("UHCRun", Games.UHCRUN, null, EntityType.CREEPER, "world", -9.440, 103, 26.56);
	/*
	 * PVPSWAP("PvPSwap", Games.PVPSWAP,null,EntityType.VILLAGER, "world",
	 * -17.548, 103, 18.455);
	 */

	public class ChangeableLine extends TextLine implements UpdatingHologramLine {
		private final GetText	get;
		private long			lastUpdate;
		private final boolean	ok;

		public ChangeableLine(final Hologram parent, final GetText get, final boolean ok) {
			super(parent, get.getLine());
			this.ok = ok;
			this.get = get;
		}

		@Override
		public long getDelay() {
			return 2000;
		}

		@Override
		public long getLastUpdateTime() {
			return this.lastUpdate;
		}

		@Override
		public void update() {
			this.lastUpdate = System.currentTimeMillis();
			if (!NPC.this.m_lastServer.canJoin()) NPC.this.gs = NPC.this.m_lastServer.getGameServer();
			if (NPC.this.gs == null || NPC.this.m_lastServer.check()) {
				if (this.ok)
					this.setText("§f");
				else
					this.setText(this.get.getSearch());
				if (NPC.this.i == 2) NPC.this.i = -1;
				return;
			}
			NPC.this.gs = NPC.this.m_lastServer.getGameServers();
			this.setText(this.get.getLine());
			if (NPC.this.i == 2) NPC.this.i = -1;
		}

	}

	public interface GetText {
		public String getLine();

		public String getSearch();
	}

	public class POPULAR extends TextLine implements UpdatingHologramLine {

		protected List<Games>	games	= new ArrayList<>();
		private long			lastUpdate;

		public POPULAR(final Hologram parent) {
			super(parent, "");
			this.games.add(Games.RUSH);
			this.games.add(Games.SKYWARS);
			this.games.add(Games.BuildBattle);
			this.games.add(Games.SURVIVOR);
			this.games.add(Games.TOWERS);
			this.games.add(Games.PVPSWAP);
			this.games.add(Games.COD);
			this.games.add(Games.BuildBattle);
			this.games.add(Games.UHCRUN);
			this.games.add(Games.HikaBrain);
		}

		@Override
		public long getDelay() {
			return 2000;
		}

		@Override
		public long getLastUpdateTime() {
			return this.lastUpdate;
		}

		public boolean is_popular(final Games g) {
			if (this.games.get(0) == g && BukkitAPI.get().getProxyDataManager().getPlayerInGame(g) > 1)
				return true;
			else if (this.games.get(1) == g && BukkitAPI.get().getProxyDataManager().getPlayerInGame(g) > 15)
				return true;
			return false;
		}

		public void sort_games() {

			Collections.sort(this.games, (o1, o2) -> {
				if (NPC.this.mode != null)
					return BukkitAPI.get().getProxyDataManager().getPlayerInGame(o1)
							- BukkitAPI.get().getProxyDataManager().getPlayerInGameWithMap(o2, NPC.this.mode);
				else
					return BukkitAPI.get().getProxyDataManager().getPlayerInGame(o1)
							- BukkitAPI.get().getProxyDataManager().getPlayerInGame(o2);
			});
			this.games = Lists.reverse(this.games);
		}

		@Override
		public void update() {
			this.lastUpdate = System.currentTimeMillis();

			this.sort_games();
			if (this.is_popular(NPC.this.g)) {
				if (NPC.this.mode != null)
					this.setText("§c ❤§c§lPOPULAIRE §c❤  §f-  §7Joueurs: §e"
							+ BukkitAPI.get().getProxyDataManager().getPlayerInGameWithMap(NPC.this.g, NPC.this.mode));
				else
					this.setText("§c ❤ §c§lPOPULAIRE §c❤  §f-  §7Joueurs: §e"
							+ BukkitAPI.get().getProxyDataManager().getPlayerInGame(NPC.this.g));
			} else
				this.setText("");
		}

	}

	static int								j				= 0;
	private Games							g;
	private GameServer						gs;
	private Hologram						holograms;
	int										i				= -1;
	public Info								m_lastServer	= null;

	private String							mode;

	private String							name;

	private net.citizensnpcs.api.npc.NPC	npc;

	private EntityType						type;

	private String							worldName;

	private double							x, y, z;

	private NPC(final String name, final Games g, final String mode, final EntityType ent, final String worldName,
			final double x, final double y, final double z) {
		this.name = name;
		this.type = ent;
		this.g = g;
		this.mode = mode;
		this.worldName = worldName;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public void dispawn() {
		this.holograms.despawn();
		this.npc.despawn();
		this.npc.destroy();
	}

	public GameServer getGs() {
		return this.gs;
	}

	public Hologram getHolograms() {
		return this.holograms;
	}

	public Info getInfo() {
		return this.m_lastServer;
	}

	public String getName() {
		return this.name;
	}

	public net.citizensnpcs.api.npc.NPC getNpc() {
		return this.npc;
	}

	public EntityType getType() {
		return this.type;
	}

	public String getWorldName() {
		return this.worldName;
	}

	public double getX() {
		return this.x;
	}

	public double getY() {
		return this.y;
	}

	public double getZ() {
		return this.z;
	}

	public void spawn() {
		Bukkit.getServer().getWorld(this.worldName).getChunkAt(
				new Location(Bukkit.getServer().getWorld(this.worldName), this.getX(), this.getY(), this.getZ()))
				.load(true);
		final World w = ((CraftWorld) Bukkit.getServer().getWorld(this.worldName)).getHandle();
		final NPCRegistry reg = CitizensAPI.getNPCRegistry();
		this.npc = reg.createNPC(this.type, "§f");
		if (this.getType().equals(EntityType.VILLAGER))
			this.npc.getTrait(VillagerProfession.class).setProfession(Profession.LIBRARIAN);
		else if (this.name().contains("SkyWars"))
			this.npc.getTrait(NPCSkeletonType.class).setType(SkeletonType.WITHER);
		else if (this.getType().equals(EntityType.CREEPER) && this.name.contains("UHCRun")
				&& !this.name.contains("Solo"))
			this.npc.getTrait(Powered.class).toggle();
		this.npc.getTrait(LookClose.class).lookClose(true);
		this.npc.spawn(new Location(w.getWorld(), this.getX(), this.getY(), this.getZ()));
		this.holograms = new Hologram("id" + j++, new Location(Bukkit.getServer().getWorld(this.worldName), this.getX(),
				this.getY() + 2.7 + (this.name().contains("SkyWars") ? 0.6 : 0), this.getZ()));
		Lobby.hologramManager.addActiveHologram(this.holograms);
		this.update();
	}

	public void teleport(final Player player) {
		if (this.m_lastServer != null && this.m_lastServer.canJoin()) {
			player.sendMessage(Utils.colorize(new Object[] { "&a&oTransfert vers " + this.getName() }));
			PlayerUtils.goToServer(player, this.gs);
		} else
			player.sendMessage(ChatColor.RED + "Attendez quelques secondes le temps que le serveur se créer ...");
	}

	public void update() {
		if (this.m_lastServer == null) this.m_lastServer = new Info(this.g, this.name, this.mode);
		this.gs = this.m_lastServer.getGameServer();
		this.holograms.addLine(new POPULAR(this.holograms));
		this.holograms.addLine(new TextLine(this.holograms,
				"&e" + this.name + (this.mode != null ? this.mode : "") + " &f- (&6Clic-Droit&f)"));
		this.holograms.addLine(new ChangeableLine(this.holograms, new GetText() {
			@Override
			public String getLine() {
				if (NPC.this.m_lastServer == null) return "NULL";
				if (NPC.this.m_lastServer.getPlayers() == NPC.this.m_lastServer.getMaxPlayers() - 1) return "§cFULL";
				if (NPC.this.m_lastServer.getMaxPlayers() > 9
						&& NPC.this.m_lastServer.getPlayers() >= NPC.this.m_lastServer.getMaxPlayers() - 3)
					return "§d✪ VIP ✪";
				else
					return "§aAttente" + (++NPC.this.i == 0 ? "." : NPC.this.i == 1 ? ".." : "...");
			}

			@Override
			public String getSearch() {
				return "§cCreation en cours" + (++NPC.this.i == 0 ? "." : NPC.this.i == 1 ? ".." : "...");
			}
		}, false));
		this.holograms.addLine(new ChangeableLine(this.holograms, new GetText() {
			@Override
			public String getLine() {
				return "§a" + NPC.this.m_lastServer.getPlayers() + "§r/§a"
						+ (NPC.this.m_lastServer.getMaxPlayers() - 1);
			}

			@Override
			public String getSearch() {
				return "§cCreation en cours" + (++NPC.this.i == 0 ? "." : NPC.this.i == 1 ? ".." : "...");
			}
		}, true));
	}

}
