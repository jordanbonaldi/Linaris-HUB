package net.neferett.linaris.minigames;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;

import lombok.Getter;
import net.neferett.linaris.api.Games;
import net.neferett.linaris.api.PlayerData;
import net.neferett.linaris.handlers.npc.NPC;

public enum GamesEnum {

	// BB(
	// new GamesManager(Games.BuildBattle, null, false),
	// new NPC("BuildBattle", EntityType.PIG_ZOMBIE, "world", 18.387, 103.0,
	// 18.533),
	// Material.GRASS),
	// COD(
	// new GamesManager(Games.COD, null, true, true),
	// new NPC("Call Of Duty", EntityType.BLAZE, "world", 54.5, 40, -18.5,
	// true),
	// Material.BLAZE_ROD,
	// 3,
	// 6),
	FACTION(
			new GamesManager(Games.FacMagie, null, false, false),
			new NPC("Faction", EntityType.VILLAGER, "world", 6.5, 103, 14.5, false),
			Material.GOLDEN_APPLE,
			3,
			2),
//	PRISON(
//			new GamesManager(Games.Prison, null, false, false),
//			new NPC("Prison", EntityType.VILLAGER, "world", 49.5, 40, -40.5, false),
//			Material.IRON_FENCE,
//			5,
//			2),
	// Hika(
	// new GamesManager(Games.HikaBrain, null, false),
	// new NPC("HikaBrain", EntityType.VILLAGER, "world", -5.487, 103, 14.4),
	// Material.OBSIDIAN),
	// RUSH(
	// new GamesManager(Games.RUSH, "2vs2", false, true),
	// new NPC("Rush", EntityType.VILLAGER, "world", 50.5, 40, -15.5, true),
	// Material.BED,
	// 5,
	// 6),
	SKYBLOCK(
			new GamesManager(Games.SKYBLOCK, null, false, false),
			new NPC("SkyBlock", EntityType.VILLAGER, "world", -5.5, 103, 14.5, false),
			Material.GRASS,
			5,
			4),
	SKYPVP(
			new GamesManager(Games.SkyPvP, null, false, false),
			new NPC("SkyPvP", EntityType.VILLAGER, "world", 0.50, 102, 20.5, false),
			Material.IRON_SWORD,
			7,
			2);
	// SW(
	// new GamesManager(Games.SKYWARS, null, false, true),
	// new NPC("SkyWars", EntityType.SKELETON, "world", 45.5, 40, -14, true),
	// Material.DIAMOND_PICKAXE,
	// 7,
	// 6),
	// TRAINING(
	// new GamesManager(Games.PvPTraining, null, false, false),
	// new NPC("PvPTraining", EntityType.VILLAGER, "world", 52.5, 40, -39.5,
	// false),
	// Material.WOOD_SWORD,
	// 7,
	// 2);
	// Tower(
	// new GamesManager(Games.TOWERS, "2vs2", false),
	// new NPC("Towers", EntityType.SKELETON, "world", 16.501, 103, 21.573),
	// Material.NETHER_FENCE);

	// US(
	// new GamesManager(Games.UHCRUNSolo, null, false),
	// new NPC("UHCRunSolo", EntityType.CREEPER, "world", -12.546, 103, 24.472),
	// Material.APPLE),
	// UT(
	// new GamesManager(Games.UHCRUN, null, false),
	// new NPC("UHCRun", EntityType.CREEPER, "world", -9.440, 103, 26.56),
	// Material.GOLDEN_APPLE);

	public static GamesEnum getGameByString(final String name) {
		return Arrays.asList(GamesEnum.values()).stream().filter((a) -> a.getN().getName() == name).findFirst()
				.orElse(null);
	}

	public static List<String> getGamesPlayed(final PlayerData pd) {
		return Arrays.asList(GamesEnum.values()).stream().map(e -> e.getN())
				.filter((a) -> pd.contains(a.getName() + "_played"))
				.sorted((a, b) -> (int) (pd.getLong(b.getName() + "_played") - pd.getLong(a.getName() + "_played")))
				.map(e -> e.getName()).collect(Collectors.toList());
	}

	public static String getMostPlayedGame(final PlayerData pd) {
		final NPC npc = Arrays.asList(GamesEnum.values()).stream().map(e -> e.getN())
				.filter((a) -> pd.contains(a.getName() + "_played"))
				.sorted((a, b) -> (int) (pd.getLong(b.getName() + "_played") - pd.getLong(a.getName() + "_played")))
				.findFirst().orElse(null);
		if (npc == null)
			return "Aucun";
		return npc.getName();
	}

	private GamesManager	gm;
	@Getter
	int						line;

	private Material		m;

	private NPC				n;
	@Getter
	int						slot;

	private GamesEnum(final GamesManager gm, final NPC n, final Material m, final int slot, final int line) {
		this.gm = gm;
		this.n = n;
		this.m = m;
		this.slot = slot;
		this.line = line;
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
