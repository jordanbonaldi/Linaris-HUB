package net.neferett.linaris.handlers.games;

import java.util.ArrayList;
import java.util.List;

import net.neferett.linaris.handlers.games.games.Rushs;
import net.neferett.linaris.handlers.games.games.SkyWars;
import net.neferett.linaris.api.Games;

public class GamesManager {

	public static Game			ARROW;
	public static Game			BUILDBATTLE;
	public static Game			COD;
	public static Game			FALLEN_KINGDOM;
	public static Game			GLADIATORS;
	public static Game			HUNGERGAMES;
	private static GamesManager	instance;
	public static Game			PVP_SWAP;
	public static Game			PVPTRAINING;
	public static Game			RUSH;
	public static Game			SHEEPWARS;
	public static Game			SKYWARS;
	public static Game			SPEEDUHC;
	public static Game			SURVIVOR;
	public static Game			TOTEM;
	public static Game			TOWERS;

	public static Game			UHCRUN;

	public static GamesManager getInstance() {
		return instance == null ? instance = new GamesManager() : instance;
	}

	List<Game> games = new ArrayList<>();

	public Game getGame(final Games g) {
		if (this.games.isEmpty())
			return null;
		for (final Game game : this.games)
			if (g.equals(game.getGame()))
				return game;
		return null;
	}

	public Game getGame(final String name) {
		if (this.games.isEmpty())
			return null;
		for (final Game game : this.games)
			if (game.getName().equals(name))
				return game;
		return null;
	}

	public List<Game> getGames() {
		return this.games;
	}

	public void inits() {

		SKYWARS = new SkyWars();
		this.registerGame(SKYWARS);

		RUSH = new Rushs();
		this.registerGame(RUSH);

	}

	private void registerGame(final Game game) {
		this.games.add(game);
	}

}
