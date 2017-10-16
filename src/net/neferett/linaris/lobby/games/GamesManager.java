package net.neferett.linaris.lobby.games;

import java.util.ArrayList;
import java.util.List;

import net.neferett.linaris.api.Games;
import net.neferett.linaris.lobby.games.games.BuildBattle;
import net.neferett.linaris.lobby.games.games.FallenKingdoms;
import net.neferett.linaris.lobby.games.games.Gladiator;
import net.neferett.linaris.lobby.games.games.PvpSwap;
import net.neferett.linaris.lobby.games.games.Rushs;
import net.neferett.linaris.lobby.games.games.SkyWars;
import net.neferett.linaris.lobby.games.games.Towers;
import net.neferett.linaris.lobby.games.games.UhcRun;

public class GamesManager {

	public static Game PVP_SWAP;
	public static Game FALLEN_KINGDOM;
	public static Game UHCRUN;
	public static Game SKYWARS;
	public static Game GLADIATORS;
	public static Game HUNGERGAMES;
	public static Game RUSH;
	public static Game TOWERS;
	public static Game BUILDBATTLE;
	public static Game SHEEPWARS;
	public static Game COD;
	public static Game ARROW;
	public static Game TOTEM;
	public static Game SURVIVOR;
	public static Game PVPTRAINING;
	public static Game SPEEDUHC;
	

	List<Game> games = new ArrayList<Game>();

	private static GamesManager instance;

	public static GamesManager getInstance() {
		return (instance == null) ? instance = new GamesManager() : instance;
	}

	public void inits() {

		PVP_SWAP = new PvpSwap();
		registerGame(PVP_SWAP);

		UHCRUN = new UhcRun();
		registerGame(UHCRUN);

		FALLEN_KINGDOM = new FallenKingdoms();
		registerGame(FALLEN_KINGDOM);
		
		SKYWARS = new SkyWars();
		registerGame(SKYWARS);
		RUSH = new Rushs();
		registerGame(RUSH);
		TOWERS = new Towers();
		registerGame(TOWERS);
		
		BUILDBATTLE = new BuildBattle();
		registerGame(BUILDBATTLE);
		
		GLADIATORS = new Gladiator();
		registerGame(GLADIATORS);
		
	}

	public List<Game> getGames() {
		return games;
	}

	public Game getGame(String name) {
		if (games.isEmpty())
			return null;
		for (Game game : games)
			if (game.getName().equals(name))
				return game;
		return null;
	}
	
	public Game getGame(Games g) {
		if (games.isEmpty())
			return null;
		for (Game game : games)
			if (g.equals(game.getGame()))
				return game;
		return null;
	}

	private void registerGame(Game game) {
		this.games.add(game);
	}

}
