package net.neferett.linaris.handlers.npc;

import net.neferett.linaris.minigames.GamesManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Skeleton.SkeletonType;
import org.bukkit.entity.Villager.Profession;

import com.sainttx.holograms.api.Hologram;
import com.sainttx.holograms.api.line.TextLine;

import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPCRegistry;
import net.citizensnpcs.trait.LookClose;
import net.citizensnpcs.trait.NPCSkeletonType;
import net.citizensnpcs.trait.Powered;
import net.citizensnpcs.trait.VillagerProfession;
import net.minecraft.server.v1_8_R3.World;
import net.neferett.linaris.Main;

public class NPC {

	public interface getText {
		public String getMsg(GamesManager gm);
	};

	static int						j	= 0;
	boolean							creation;
	Hologram						hg;
	String							name;
	net.citizensnpcs.api.npc.NPC	npc;
	NPCRegistry						npcr;
	EntityType						type;
	World							w;
	String							world;

	double							x;

	double							y;

	double							z;

	public NPC(final String name, final EntityType type, final String world, final double x, final double y,
			final double z, final boolean creation) {
		this.name = name;
		this.type = type;
		this.world = world;
		this.creation = creation;
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = ((CraftWorld) Bukkit.getServer().getWorld(this.world)).getHandle();
		this.npcr = CitizensAPI.getNPCRegistry();
		this.npc = this.npcr.createNPC(this.type, "net.neferett.linarisf");
	}

	public void createHolo(final GamesManager gm) {

		this.hg.addLine(new TextLine(this.hg, "&e" + this.name + " &f- (&6Clic-Droit&f)"));

		this.hg.addLine(new NPCHologram(this.hg, gm1 -> NPC.this.VIPText(gm1), gm));
		this.hg.addLine(new NPCHologram(this.hg, gm1 -> NPC.this.ServerText(gm1), gm));

	}

	public void Dispawn() {
		this.npc.despawn();
		this.npc.destroy();
		this.hg.despawn();
	}

	public String getName() {
		return this.name;
	}

	public net.citizensnpcs.api.npc.NPC getNpc() {
		return this.npc;
	}

	private String ServerText(final GamesManager gm) {
		return gm.isSelectedAlwaysAvailable() && gm.getSelected() != null || gm.isLogGame() ? this.creation
				? "net.neferett.linarisa" + gm.getSelected().getPlayers() + "net.neferett.linarisr/net.neferett.linarisa" + (gm.getSelected().getMaxPlayers() - 1)
				: "net.neferett.linarise" + gm.getSelected().getPlayers() + " net.neferett.linaris7Joueurs" : "net.neferett.linariscCréation d'un serveur en cours...";
	}

	private void setParameters() {
		this.npc.getTrait(LookClose.class).lookClose(true);
	}

	private void setProfession() {
		if (this.type.equals(EntityType.VILLAGER))
			this.npc.getTrait(VillagerProfession.class).setProfession(Profession.LIBRARIAN);
		else if (this.name.contains("SkyWars"))
			this.npc.getTrait(NPCSkeletonType.class).setType(SkeletonType.WITHER);
		else if (this.type.equals(EntityType.CREEPER) && this.name.contains("UHCRun") && !this.name.contains("Solo"))
			this.npc.getTrait(Powered.class).toggle();
	}

	public void Spawn() {

		Bukkit.getServer().getWorld(this.world)
				.getChunkAt(new Location(Bukkit.getServer().getWorld(this.world), this.x, this.y, this.z)).load(true);

		this.setProfession();
		this.setParameters();

		this.npc.spawn(new Location(this.w.getWorld(), this.x, this.y, this.z));

		this.hg = new Hologram("id" + j++, new Location(Bukkit.getServer().getWorld(this.world), this.x,
				this.y + 2.7 + (this.name.contains("SkyWars") ? 0.6 : 0), this.z));

		Main.getMainInstance().getHologramManager().addActiveHologram(this.hg);
	}

	private String VIPText(final GamesManager gm) {
		return gm.isSelectedAlwaysAvailable() && gm.getSelected() != null
				? gm.getSelected().getMaxPlayers() == gm.getSelected().getPlayers() - 1 ? "net.neferett.linariscFULL"
						: gm.getSelected().getPlayers() > 9
								&& gm.getSelected().getPlayers() >= gm.getSelected().getMaxPlayers() - 3 ? "net.neferett.linarisd✪ VIP ✪"
										: this.creation ? "net.neferett.linarisaAttente..." : ""
				: "";
	}

}