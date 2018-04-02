package net.neferett.linaris.lobby.minigames;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;

import net.neferett.linaris.api.Games;
import net.neferett.linaris.lobby.handlers.npc.NPC;

public enum GamesEnum {

	BB(
			new GamesManager(Games.BuildBattle, null, false),
			new NPC("BuildBattle", EntityType.PIG_ZOMBIE, "world", 18.387, 103.0, 18.533),
			Material.GRASS),
	COD(
			new GamesManager(Games.COD, null, true),
			new NPC("Call Of Duty", EntityType.BLAZE, "world", -15.563, 103, 21.464),
			Material.BLAZE_ROD),
	Hika(
			new GamesManager(Games.HikaBrain, null, false),
			new NPC("HikaBrain", EntityType.VILLAGER, "world", -5.487, 103, 14.4),
			Material.OBSIDIAN),
	RUSH(
			new GamesManager(Games.RUSH, "2vs2", false),
			new NPC("Rush", EntityType.VILLAGER, "world", 6.441, 103, 14.504),
			Material.BED),
	SW(
			new GamesManager(Games.SKYWARS, null, false),
			new NPC("SkyWars", EntityType.SKELETON, "world", 10.532, 103, 26.423),
			Material.DIAMOND_PICKAXE),
	Tower(
			new GamesManager(Games.TOWERS, "2vs2", false),
			new NPC("Towers", EntityType.SKELETON, "world", 16.501, 103, 21.573),
			Material.NETHER_FENCE);
	// US(
	// new GamesManager(Games.UHCRUNSolo, null, false),
	// new NPC("UHCRunSolo", EntityType.CREEPER, "world", -12.546, 103, 24.472),
	// Material.APPLE),
	// UT(
	// new GamesManager(Games.UHCRUN, null, false),
	// new NPC("UHCRun", EntityType.CREEPER, "world", -9.440, 103, 26.56),
	// Material.GOLDEN_APPLE);

	private GamesManager	gm;
	private Material		m;
	private NPC				n;

	private GamesEnum(final GamesManager gm, final NPC n, final Material m) {
		this.gm = gm;
		this.n = n;
		this.m = m;
	}

	public GamesManager getGm() {
		return this.gm;
	}

	public Material getM() {
		return this.m;
	}

	public NPC getN() {
		return this.n;
	}

}
