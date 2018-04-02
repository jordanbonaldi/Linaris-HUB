package net.neferett.linaris.lobby.handlers.inventories;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import net.neferett.linaris.BukkitAPI;
import net.neferett.linaris.api.Games;
import net.neferett.linaris.lobby.handlers.items.games.GamesItems;
import net.neferett.linaris.lobby.handlers.items.games.MiniGamesItems;
import net.neferett.linaris.lobby.minigames.GamesEnum;
import net.neferett.linaris.lobby.minigames.GamesManager;
import net.neferett.linaris.lobby.utils.ConfigDatas;
import net.neferett.linaris.lobby.utils.NBTItem;
import net.neferett.linaris.utils.QueueUtils;
import net.neferett.linaris.utils.gui.GuiManager;
import net.neferett.linaris.utils.gui.GuiScreen;

public class MainMenuInventory extends GuiScreen {

	public static int getRandomInteger(final int maximum, final int minimum) {
		return (int) (Math.random() * (maximum - minimum)) + minimum;
	}

	private List<GamesEnum> g = new ArrayList<>();

	public MainMenuInventory(final Player p) {
		super("Menu Principal & Jeux", 4, p, true);
		this.build();
	}

	@Override
	public void drawScreen() {

		this.setGameItemLine(12, Games.PvPTraining, Material.WOOD_SWORD, false, 3, 1, "§b§nRègle§r§f:",
				"§7Combat dans une arene,", "§7gagne de l'argent en tuant d'autres joueurs",
				"§7puis achete-toi les meilleurs stuff", "§7pour devenir encore plus redoutable !");

		this.setGameItemLine(14, Games.PvPBox, Material.STONE_SWORD, false, 3, 2, "§b§nRègle§r§f:",
				"§7Entraine toi dans une arene", "§7et ameliore ton stuff grace",
				"§7à tes points uniquement gagné en tuant", "§7puis deviens le meilleur tueur !");

		this.setGameItemLine(15, Games.PvPBoxCheat, Material.IRON_SWORD, false, 3, 3, "§b§nRègle§r§f:",
				"§7Combat dans une arene,", "§7gagne de l'argent en tuant d'autres joueurs",
				"§7puis achete-toi les meilleurs stuff", "§7pour devenir encore plus redoutable !");

		this.setGameItemLine(400, Games.PRACTICE, Material.DIAMOND_SWORD, true, 3, 4, "");

		this.setGameItemLine(13, Games.SkyPvP, Material.GOLD_SWORD, true, 4, 1, "§b§nRègle§r§f:",
				"§7Combat dans une arene,", "§7gagne de l'argent en tuant d'autres joueurs",
				"§7puis achete-toi les meilleurs stuff", "§7pour devenir encore plus redoutable !");

		this.setGameItemLine(28, Games.Fac, Material.DIAMOND, true, 4, 2, "§b§nRègle§r§f:",
				"§7Un faction totalement innovant", "§7avec des classes, des sorts, et des pouvoirs !",
				"§7Viens tester dès maintenant cette", "§7nouvelle expérience de jeu inoubliable !");

		this.setGameItemLine(17, Games.Cheat, Material.DIAMOND_SWORD, true, 4, 3, "§b§nRègle§r§f:",
				"§7Créer ta faction puis", "§7combat les autres factions et deviens", "§7la faction la plus populaire",
				"§7et plus puissante du serveur !");

		this.setGameItemLine(29, Games.FacMagie, Material.GOLDEN_APPLE, false, 4, 6, "§b§nRègle§r§f:",
				"§7Créer ta faction puis", "§7combat les autres factions et deviens", "§7la faction la plus populaire",
				"§7et plus puissante du serveur !");

		// symetric

		this.setGameItemLine(18, Games.SKYBLOCK, Material.GRASS, false, 3, 9, "§b§nRègle§r§f:",
				"§7Survie seul ou a plusieurs", "§7sur une île dans le vide.",
				"§7Puis en reussissant les objectifs augmente", "§7de level et deviens le plus haut level !");

		this.setGameItemLine(22, Games.FB, Material.WORKBENCH, false, 4, 9, "§b§nRègle§r§f:",
				"§7Une envie de construire ?", "§7Notre FreeBuild te le permet librement",
				"§7et la seule limite est celle de ton imagination !");

		this.setGameItemLine(19, Games.TWD, Material.SKULL_ITEM, false, 3, 8, "§b§nRègle§r§f:",
				"§7Survie face a des hordes", "§7de zombies de partout dans", "§7les fameux endroits de la série",
				"§7TWD, accomplie les quêtes des", "§7personnages de la serie et debloque leurs kits");

		this.setGameItemLine(20, Games.GTA, Material.EXPLOSIVE_MINECART, false, 4, 8, "§b§nRègle§r§f:",
				"§7Vie ton experience GTA", "§7dans un monde libre et", "§7sans règles a suivre !");

		this.setGameItemLine(21, Games.Prison, Material.IRON_FENCE, false, 4, 7, "§b§nRègle§r§f:",
				"§7Essaie de t'évader de notre prison", "§7en minant et devenant", "§7le plus respecté du serveur !");

		final AtomicInteger i = new AtomicInteger(500);
		final AtomicInteger j = new AtomicInteger(0);

		this.g.forEach((e) -> {
			this.setMiniGameItemLine(i.incrementAndGet(), e.getGm(), e.getM(), false, 1, j.incrementAndGet(), "");
		});

	}

	@Override
	public void onClick(final ItemStack item, final InventoryClickEvent event) {

		event.setCancelled(true);

		final NBTItem nbt = new NBTItem(item);
		if (!nbt.hasKey("itemID"))
			return;

		final int itemID = nbt.getInteger("itemID");

		if (itemID == 5) {
			this.getPlayer().teleport(ConfigDatas.getInstance().getSpawn());
			return;
		}
		if (itemID == 6)
			return;
		if (itemID == 7)
			return;
		if (itemID == 8)
			return;
		if (itemID == 9) {
			GuiManager.openGui(new PrefsGui(this.getPlayer(), this));
			return;
		}
		if (itemID == 10) {
			this.getPlayer().closeInventory();
			return;
		}

		/**
		 * Linaris Games
		 */

		if (itemID == 11) {
			QueueUtils.addInQueue(this.getPlayer(), Games.SKYWARS);
			return;
		}

		if (itemID == 12) {
			QueueUtils.addInQueue(this.getPlayer(), Games.PvPTraining);
			return;
		}

		if (itemID == 13) {
			QueueUtils.addInQueue(this.getPlayer(), Games.SkyPvP);
			return;
		}

		if (itemID == 14) {
			QueueUtils.addInQueue(this.getPlayer(), Games.PvPBox);
			return;
		}

		if (itemID == 15) {
			QueueUtils.addInQueue(this.getPlayer(), Games.PvPBoxCheat);
			return;
		}

		if (itemID == 17) {
			QueueUtils.addInQueue(this.getPlayer(), Games.Cheat);
			return;
		}

		if (itemID == 18) {
			QueueUtils.addInQueue(this.getPlayer(), Games.SKYBLOCK);
			return;
		}

		if (itemID == 19) {
			QueueUtils.addInQueue(this.getPlayer(), Games.TWD);
			return;
		}

		if (itemID == 20) {
			QueueUtils.addInQueue(this.getPlayer(), Games.GTA);
			return;
		}

		if (itemID == 21) {
			QueueUtils.addInQueue(this.getPlayer(), Games.Prison);
			return;
		}

		if (itemID == 22) {
			QueueUtils.addInQueue(this.getPlayer(), Games.FB);
			return;
		}

		if (itemID == 28) {
			QueueUtils.addInQueue(this.getPlayer(), Games.Fac);
			return;
		}

		if (itemID == 29) {
			QueueUtils.addInQueue(this.getPlayer(), Games.FacMagie);
			return;
		}

		if (itemID == 140) {
			QueueUtils.addInQueue(this.getPlayer(), Games.BuildBattle);
			return;
		}
		// if (itemID == 141) {
		// QueueUtils.addInQueue(getPlayer(), Games.GLADIATOR);
		// return;
		// }
		if (itemID == 400) {
			// getPlayer().sendMessage("§cLe jeu est en \"refonte\", nous
			// refaisons tous le systeme,"
			// + " et reduisons les lags et bugs à 100%, §a§lSortie
			// prochainement (§e§lFin Septembre - Début Octobre)");
			QueueUtils.addInQueue(this.getPlayer(), Games.PRACTICE);
			return;
		}

		final AtomicInteger i = new AtomicInteger(500);

		this.g.forEach((e) -> {
			if (itemID == i.incrementAndGet())
				e.getGm().TeleportToSelectedGame(this.getPlayer());
		});

	}

	@Override
	public void onClose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onOpen() {
		this.g = Arrays.asList(GamesEnum.values()).stream()
				.sorted((a, b) -> b.getGm().getPlayers() - a.getGm().getPlayers()).collect(Collectors.toList());
	}

	public void setGameItemLine(final int id, final Games g, final Material m, final boolean e, final int line,
			final int slot, final String... string) {
		final ItemStack i = new GamesItems(g, m, e, string).getItem();

		if (BukkitAPI.get().getProxyDataManager().getServersByGameName(g.getDisplayName()).size() == 0)
			return;

		super.setItemLine(new NBTItem(i).setInteger("itemID", id).getItem(), line, slot);
	}

	public void setItemLine(final int id, final ItemStack i, final int line, final int slot) {
		super.setItemLine(new NBTItem(i).setInteger("itemID", id).getItem(), line, slot);
	}

	public void setMiniGameItemLine(final int id, final GamesManager gm, final Material m, final boolean e,
			final int line, final int slot, final String... string) {
		final ItemStack i = new MiniGamesItems(gm, m, e, string).getItem();

		if (BukkitAPI.get().getProxyDataManager().getServersByGameName(gm.getG().getDisplayName()).size() == 0)
			return;

		super.setItemLine(new NBTItem(i).setInteger("itemID", id).getItem(), line, slot);
	}

}
