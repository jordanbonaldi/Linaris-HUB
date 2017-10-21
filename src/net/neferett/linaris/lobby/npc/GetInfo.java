package net.neferett.linaris.lobby.npc;

import net.neferett.linaris.api.GameServer;

public interface GetInfo {
	
	public GameServer getGameServer();
	public boolean check();
	public boolean canJoin();
	public boolean canJoin(GameServer server);
	public int getPlayers();
	public int getMaxPlayers();
	
}
