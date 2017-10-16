package net.neferett.linaris.lobby.npc;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

import net.neferett.linaris.BukkitAPI;
import net.neferett.linaris.api.GameServer;
import net.neferett.linaris.api.Games;
import net.neferett.linaris.api.SocketEvent.SendMessage;
import net.neferett.linaris.lobby.Lobby;
import net.neferett.linaris.lobby.inventories.MainMenuInventory;

public class Info implements GetInfo {
	public class LaunchSignal implements Runnable {

		protected String	mode;
		protected String	name;

		public LaunchSignal(final String name, final String mode) {
			this.name = name;
			this.mode = mode;
		}

		@Override
		public void run() {
			if (!BukkitAPI.get().getServerInfos().getServerName().equals("hub1"))
				return;

			String msg = null;

			if (Info.this.isMultipletypes())
				msg = "start " + this.name + Info.this.getRandTypes();
			else if (Info.this.secondaryname != null)
				msg = "start " + Info.this.secondaryname + MainMenuInventory.getRandomInteger(0, 3);
			else
				msg = "start " + this.name + (this.mode != null ? this.mode : "");

			new SendMessage("163.172.71.30", 12000, msg).build();
			Info.this.search = System.currentTimeMillis();

		}

	}

	private GameServer			gs		= null;
	private boolean				launch;
	private final String		mode;
	private final boolean		multipletypes;
	private final String		name;
	private Long				search;
	private String				secondaryname;
	private int					tmp;

	private final List<String>	types	= new ArrayList<>();

	public Info(final Games g, final String name, final String mode) {
		this.name = name;
		if (name.equals("Call Of Duty"))
			this.secondaryname = "COD";
		this.mode = mode;
		this.launch = false;
		this.search = -1L;
		this.multipletypes = false;
	}

	@Override
	public boolean canJoin() {
		if (this.tmp == 0 || this.gs == null)
			return false;
		return this.canJoin(BukkitAPI.get().getProxyDataManager().getServers().get(this.gs.getServName()));
	}

	@Override
	public boolean canJoin(final GameServer server) {
		if (server == null)
			return false;
		this.gs = server;
		if (server.canJoin() && server.canSee() && server.getPlayers() > 0
				&& server.getPlayers() == server.getMaxPlayers() - 1)
			return false;
		return server.getPlayers() < server.getMaxPlayers() && server.canJoin() && server.canSee();
	}

	@Override
	public boolean check() {
		if (this.gs == null)
			return true;
		return false;
	}

	public Predicate<Long> CreateTestCoolDown(final long time) {
		return past -> this.getTimeLeft(past, time) > 0;
	}

	@Override
	public GameServer getGameServer() {
		if (this.name.equals("Survivor"))
			return null;
		LinkedList<GameServer> servers = new LinkedList<>();
		servers = BukkitAPI.get().getProxyDataManager().getServersByGameName(this.name);
		if (!this.name.contains("UHC") && Lobby.open && this.search != -1
				&& !this.CreateTestCoolDown(30).test(this.search)) {
			new Thread(new LaunchSignal(this.name, this.mode)).start();
			return null;
		}
		this.tmp = 0;
		for (final GameServer sv : servers) {
			++this.tmp;
			if (this.canJoin(sv)) {
				if (this.mode != null && sv.getMapName().equals(this.mode)) {
					this.launch = false;
					this.gs = sv;
					this.search = -1L;
					return sv;
				} else if (this.mode != null)
					continue;
				this.launch = false;
				this.search = -1L;
				this.gs = sv;
				return sv;
			}
		}
		if (Lobby.open && this.launch == false) {
			new Thread(new LaunchSignal(this.name, this.mode)).start();
			System.out.println(this.name);
			this.launch = true;
		}
		this.search = System.currentTimeMillis();
		this.gs = null;
		return null;
	}

	public GameServer getGameServers() {
		return this.gs;
	}

	@Override
	public int getMaxPlayers() {
		return this.gs == null ? 0 : this.gs.getMaxPlayers();
	}

	@Override
	public int getPlayers() {
		return this.gs == null ? 0 : this.gs.getPlayers();
	}

	public String getRandTypes() {
		if (MainMenuInventory.getRandomInteger(25, 0) > 15)
			return this.types.get(1);
		else
			return this.types.get(0);
	}

	public Long getTimeLeft(final Long past, final long time) {
		return past / 1000 + time - System.currentTimeMillis() / 1000;
	}

	public boolean isMultipletypes() {
		return this.multipletypes;
	}

	@Override
	public String toString() {
		return this.name;

	}
}