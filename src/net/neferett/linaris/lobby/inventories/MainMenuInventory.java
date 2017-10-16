package net.neferett.linaris.lobby.inventories;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import com.google.common.collect.Lists;

import net.neferett.linaris.BukkitAPI;
import net.neferett.linaris.api.Games;
import net.neferett.linaris.lobby.Lobby;
import net.neferett.linaris.lobby.utils.ItemBuilder;
import net.neferett.linaris.lobby.utils.NBTItem;
import net.neferett.linaris.utils.QueueUtils;
import net.neferett.linaris.utils.gui.GuiManager;
import net.neferett.linaris.utils.gui.GuiScreen;

public class MainMenuInventory extends GuiScreen {

	public static int getRandomInteger(final int maximum, final int minimum) {
		return (int) (Math.random() * (maximum - minimum)) + minimum;
	}

	protected List<Games>		games		= new ArrayList<>();

	protected List<Material>	minecart	= new ArrayList<>();

	public MainMenuInventory(final Player p) {
		super("Menu Principal & Jeux", 3, p, false);
		this.build();
	}

	@Override
	public void drawScreen() {
		this.minecart.add(Material.MINECART);
		this.minecart.add(Material.STORAGE_MINECART);
		this.minecart.add(Material.EXPLOSIVE_MINECART);
		this.minecart.add(Material.HOPPER_MINECART);
		this.minecart.add(Material.POWERED_MINECART);

		this.games.add(Games.PvPTraining);
		this.games.add(Games.PvPBox);
		this.games.add(Games.PvPBoxCheat);
		this.games.add(Games.Cheat);
		this.games.add(Games.SKYBLOCK);
		this.games.add(Games.TWD);
		this.games.add(Games.COC);
		this.games.add(Games.Fac);
		this.games.add(Games.GTA);
		this.games.add(Games.Prison);
		this.games.add(Games.FB);
		this.games.add(Games.PRACTICE);
		this.games.add(Games.SkyPvP);

		this.sort_games();

		int data = getRandomInteger(15, 0);
		for (int i = 1; i < 4; i++)
			for (int j = 1; j < 10; j++)
				this.setItemLine(-1, new ItemBuilder(new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) data))
						.setTitle("§6").build(), i, j);

		this.setItemLine(28,
				new ItemBuilder(new ItemStack(Material.DIAMOND)).addEnchantment(Enchantment.DURABILITY, 100)
						.setTitle(this.get_Title(Games.Fac)).addLores("§aClique pour te connecter", "",
								"§b§nRègle§r§f:", "§7Un faction totalement innovant",
								"§7avec des classes, des sorts, et des pouvoirs !",
								"§7Viens tester dès maintenant cette", "§7nouvelle expérience de jeu inoubliable !", "",
								"§6Nombre de joueurs§f: §b"
										+ BukkitAPI.get().getProxyDataManager().getPlayerInGame(Games.Fac),
								"")
						.build(),
				2, 3);

		this.setItemLine(12,
				new ItemBuilder(new ItemStack(Material.WOOD_SWORD)).setTitle(this.get_Title(Games.PvPTraining))
						.addLores("§aClique pour te connecter", "", "§b§nRègle§r§f:", "§7Entraine toi dans une arene",
								"§7et ameliore ton stuff grace", "§7à tes points uniquement gagné en tuant",
								"§7puis deviens le meilleur tueur !", "",
								"§6Nombre de joueurs§f: §b"
										+ BukkitAPI.get().getProxyDataManager().getPlayerInGame(Games.PvPTraining),
								"")
						.build(),
				1, 1);

		this.setItemLine(14, new ItemBuilder(new ItemStack(Material.STONE_SWORD)).setTitle(this.get_Title(Games.PvPBox))
				.addLores("§aClique pour te connecter", "", "§b§nRègle§r§f:", "§7Combat dans une arene,",
						"§7gagne de l'argent en tuant d'autres joueurs", "§7puis achete-toi les meilleurs stuff",
						"§7pour devenir encore plus redoutable !", "",
						"§6Nombre de joueurs§f: §b"
								+ BukkitAPI.get().getProxyDataManager().getPlayerInGame(Games.PvPBox),
						"")
				.build(), 1, 2);

		this.setItemLine(15,
				new ItemBuilder(new ItemStack(Material.IRON_SWORD)).setTitle(this.get_Title(Games.PvPBoxCheat))
						.addLores("§aClique pour te connecter", "", "§b§nRègle§r§f:", "§7Combat dans une arene,",
								"§7gagne de l'argent en tuant d'autres joueurs",
								"§7puis achete-toi les meilleurs stuff", "§7pour devenir encore plus redoutable !", "",
								"§6Nombre de joueurs§f: §b"
										+ BukkitAPI.get().getProxyDataManager().getPlayerInGame(Games.PvPBoxCheat),
								"")
						.build(),
				1, 3);

		this.setItemLine(18, new ItemBuilder(new ItemStack(Material.GRASS)).setTitle(this.get_Title(Games.SKYBLOCK))
				.addLores("§aClique pour te connecter", "", "§b§nRègle§r§f:", "§7Survie seul ou a plusieurs",
						"§7sur une île dans le vide.", "§7Puis en reussissant les objectifs augmente",
						"§7de level et deviens le plus haut level !", "",
						"§6Nombre de joueurs§f: §b"
								+ BukkitAPI.get().getProxyDataManager().getPlayerInGame(Games.SKYBLOCK),
						"")
				.build(), 1, 6);

		data = getRandomInteger(3, 0);
		this.setItemLine(19, new ItemBuilder(new ItemStack(Material.SKULL_ITEM, 1, (short) data))
				.setTitle(this.get_Title(Games.TWD))
				.addLores("§aClique pour te connecter", "", "§b§nRègle§r§f:", "§7Survie face a des hordes",
						"§7de zombies de partout dans", "§7les fameux endroits de la série",
						"§7TWD, accomplie les quêtes des", "§7personnages de la serie et debloque leurs kits", "",
						"§6Nombre de joueurs§f: §b" + BukkitAPI.get().getProxyDataManager().getPlayerInGame(Games.TWD),
						"")
				.build(), 1, 8);

		this.setItemLine(13, new ItemBuilder(new ItemStack(Material.GOLD_SWORD))
				.addEnchantment(Enchantment.FIRE_ASPECT, 1).setTitle(this.get_Title(Games.SkyPvP))
				.addLores("§aClique pour te connecter", "", "§b§nRègle§r§f:", "§7Combat dans une arene,",
						"§7gagne de l'argent en tuant d'autres joueurs", "§7puis achete-toi les meilleurs stuff",
						"§7pour devenir encore plus redoutable !", "",
						"§6Nombre de joueurs§f: §b"
								+ BukkitAPI.get().getProxyDataManager().getPlayerInGame(Games.SkyPvP),
						"")
				.build(), 2, 2);

		this.setItemLine(17,
				new ItemBuilder(new ItemStack(Material.DIAMOND_SWORD)).setTitle(this.get_Title(Games.Cheat))
						.addLores("§aClique pour te connecter", "", "§b§nRègle§r§f:", "§7Créer ta faction puis",
								"§7combat les autres factions et deviens", "§7la faction la plus populaire",
								"§7et plus puissante du serveur !", "",
								"§6Nombre de joueurs§f: §b"
										+ BukkitAPI.get().getProxyDataManager().getPlayerInGame(Games.Cheat),
								"")
						.build(),
				1, 4);

		data = getRandomInteger(5, 0);
		this.setItemLine(20,
				new ItemBuilder(new ItemStack(this.minecart.get(data))).setTitle(this.get_Title(Games.GTA))
						.addLores("§aClique pour te connecter", "", "§b§nRègle§r§f:", "§7Vie ton experience GTA",
								"§7dans un monde libre et", "§7sans règles a suivre !", "", "§6Nombre de joueurs§f: §b"
										+ BukkitAPI.get().getProxyDataManager().getPlayerInGame(Games.GTA),
								"")
						.build(),
				1, 7);

		this.setItemLine(21, new ItemBuilder(new ItemStack(Material.IRON_FENCE)).setTitle(this.get_Title(Games.Prison))
				.addLores("§aClique pour te connecter", "", "§b§nRègle§r§f:", "§7Essaie de t'évader de notre prison",
						"§7en minant et devenant", "§7le plus respecté du serveur !", "",
						"§6Nombre de joueurs§f: §b"
								+ BukkitAPI.get().getProxyDataManager().getPlayerInGame(Games.Prison),
						"")
				.build(), 1, 9);

		this.setItemLine(22,
				new ItemBuilder(new ItemStack(Material.WORKBENCH)).setTitle(this.get_Title(Games.FB))
						.addLores("§aClique pour te connecter", "", "§b§nRègle§r§f:", "§7Une envie de construire ?",
								"§7Notre FreeBuild te le permet librement",
								"§7et la seule limite est celle de ton imagination !", "",
								"§6Nombre de joueurs§f: §b"
										+ BukkitAPI.get().getProxyDataManager().getPlayerInGame(Games.FB),
								"")
						.build(),
				2, 8);
		// int nb =
		// BukkitAPI.get().getProxyDataManager().getPlayerInGame((Games.COC))+
		// BukkitAPI.get().getProxyDataManager().getPlayerInGame((Games.COCV));
		// setItemLine(400,
		// new ItemBuilder(new
		// ItemStack(Material.DIAMOND_CHESTPLATE)).setTitle("§6§kM §b§lClash Of
		// Clans §6§kM")
		// .addLores("§aClique pour te connecter","","§cRetour Prochainement",
		// "§cRefonte en cours, réduction", "§cBugs et lags à 100%"
		// , "§6Nombre de joueurs§f: §b"+ nb,
		// "").addEnchantment(Enchantment.LURE, 1).build(), 4,
		// 5);

		this.setItemLine(400,
				new ItemBuilder(new ItemStack(Material.DIAMOND_SWORD)).setTitle(this.get_Title(Games.PRACTICE))
						.addLores("§aClique pour te connecter", "",
								"§6Nombre de joueurs§f: §b"
										+ BukkitAPI.get().getProxyDataManager().getPlayerInGame(Games.PRACTICE),
								"")
						.addEnchantment(Enchantment.LURE, 1).build(),
				3, 5);

		this.setItemLine(10, new ItemBuilder(new ItemStack(Material.ARROW)).setTitle("§fRevenir en arrière").build(), 3,
				9);

	}

	public String get_Title(final Games g) {
		return (this.is_popular(g) ? "§c ❤ §c§lPOPULAIRE §c❤ §r" : "") + "§6" + g.getDisplayName();
	}

	public boolean is_popular(final Games g) {
		if (this.games.get(0) == g)
			return true;
		else if (this.games.get(1) == g)
			return true;
		return false;
	}

	@Override
	public void onClick(final ItemStack item, final InventoryClickEvent event) {

		event.setCancelled(true);

		final NBTItem nbt = new NBTItem(item);
		if (!nbt.hasKey("itemID"))
			return;

		final int itemID = nbt.getInteger("itemID");

		if (itemID == 4) {
			this.getPlayer().teleport(Lobby.getInstance().getJumpsManager().getBaseLocation());
			return;
		}
		if (itemID == 5) {
			this.getPlayer().teleport(Lobby.loc);
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
	}

	@Override
	public void onClose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onOpen() {
		// TODO Auto-generated method stub

	}

	public void setItemLine(final int id, final ItemStack item, final int line, final int slot) {
		super.setItemLine(new NBTItem(item).setInteger("itemID", id).getItem(), line, slot);
	}

	public void sort_games() {

		Collections.sort(this.games, (o1, o2) -> BukkitAPI.get().getProxyDataManager().getPlayerInGame(o1)
				- BukkitAPI.get().getProxyDataManager().getPlayerInGame(o2));
		this.games = Lists.reverse(this.games);
	}

}
