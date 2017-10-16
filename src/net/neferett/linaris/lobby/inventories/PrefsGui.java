package net.neferett.linaris.lobby.inventories;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import net.neferett.linaris.api.Games;
import net.neferett.linaris.api.SettingsManager;
import net.neferett.linaris.utils.gui.GuiManager;
import net.neferett.linaris.utils.gui.GuiScreen;
import net.neferett.linaris.lobby.utils.ItemBuilder;
import net.neferett.linaris.lobby.utils.NBTItem;

public class PrefsGui extends GuiScreen {

	private GuiScreen lastGui;

	public PrefsGui(Player p, GuiScreen lastGui) {
		super("Préférences", 6, p, false);
		this.lastGui = lastGui;
		build();
	}

	@SuppressWarnings("deprecation")
	@Override
	public void drawScreen() {

		setItemLine(0,
				new ItemBuilder(Material.DRAGON_EGG).setTitle("§6Afficher les familiers")
						.addLores("", "§7Activer ou désactiver les", "§7familiers des autres joueurs", "",
								"§a> §bClic gauche §7- §aFamiliers affichés", "§a> §bClic droit §7- §cFamiliers cachés",
								"", "§eCommande §b/pet enable|disable")
						.build(),
				1, 1);
		setItemLine(1,
				new ItemBuilder(Material.MONSTER_EGG).setTitle("§6Afficher son familier")
						.addLores("", "§7Activer ou désactiver ", "§7votre propre familier", "",
								"§a> §bClic gauche §7- §aFamilier affiché", "§a> §bClic droit §7- §cFamilier caché", "",
								"§eCommande §b/pet show|hide")
						.build(),
				3, 1);

		setItemLine(2,
				new ItemBuilder(Material.PAPER).setTitle("§6Chat dans les Hubs")
						.addLores("", "§7Activer ou désactiver les", "§7messages dans les hubs", "",
								"§a> §bClic gauche §7- §aActivé", "§a> §bClic droit §7- §cDésactivé", "",
								"§eCommande §b/chat on|off")
						.build(),
				1, 3);
		setItemLine(3,
				new ItemBuilder(Material.INK_SACK, 1, (short) 5).setTitle("§6Son des messages où vous apparaissez")
						.addLores("", "§7Activer ou désactiver le son", "§7des messages où vous apparaissez", "",
								"§a> §bClic gauche §7- §aSon activé", "§a> §bClic droit §7- §cSon désactivé", "",
								"§eCommande §b/hightlight sound on|off")
						.build(),
				3, 3);

		setItemLine(4, new ItemBuilder(new ItemStack(395)).setTitle("§6Messages privés")
				.addLores("", "§7Activer ou désactiver les", "§7messages privés", "",
						"§a> §bClic gauche §7- §aTout le monde", "§a> §bClic droit §7- §cPersonne",
						"§a> §bClic molette §7- §eAmis seulement", "", "§eCommande §b/w on|off|friends")
				.build(), 1, 5);
		setItemLine(5,
				new ItemBuilder(Material.GREEN_RECORD).setTitle("§6Son de messagerie").addLores("",
						"§7Activer ou désactiver le", "§7son à la reception d'un message", "", "§eCommande §b/w sound")
				.build(), 3, 5);

		setItemLine(6,
				new ItemBuilder(Material.RAW_FISH, 1, (short) 3).setTitle("§6Demandes d'amis")
						.addLores("", "§7Activer ou désactiver", "§7les demandes d'amis", "",
								"§a> §bClic gauche §7- §aActivé", "§a> §bClic droit §7- §cDésactivé", "",
								"§eCommande §b/friends enable|disable")
						.build(),
				1, 7);
		setItemLine(7,
				new ItemBuilder(Material.GREEN_RECORD).setTitle("§6Son des amis")
						.addLores("", "§7Activer ou désactiver le", "§7son ou la reception d'une demande", "",
								"§eCommande §b/friends sound")
						.build(),
				3, 7);

		setItemLine(8,
				new ItemBuilder(Material.FIREWORK).setTitle("§6Préférences des groupes")
						.addLores("", "§7Activer ou non les invitations", "", "§a> §bClic gauche §7- §aTout le monde",
								"§a> §bClic droit §7- §cPersonne", "§a> §bClic molette §7- §eAmis seulement", "",
								"§eCommande §b/party allow all|friends|none")
						.build(),
				1, 9);
		setItemLine(9,
				new ItemBuilder(Material.GREEN_RECORD).setTitle("§6Son des invitations")
						.addLores("", "§7Activer ou désactiver le", "§7son à la reception d'une invitation", "",
								"§eCommande §b/party sound")
						.build(),
				3, 9);

		setItemLine(10,
				new ItemBuilder(Material.LEASH).setTitle("§6Suivre le groupe")
						.addLores("", "§7Activer ou désactiver le", "§7suivi du chef de groupe", "",
								"§a> §bClic gauche §7- §aSon activé", "§a> §bClic droit §7- §cSon désactivé", "",
								"§eCommande §b/party follow on|off")
						.build(),
				5, 5);

		setItemLine(new ItemBuilder(Material.ARROW).setTitle("§fRevenir en arrière").build(), 6,9);
	}

	public void setItemLine(int id, ItemStack item, int line, int slot) {
		super.setItemLine(new NBTItem(item).setInteger("itemID", id).getItem(), line, slot);
	}

	@Override
	public void onOpen() {
	}

	@Override
	public void onClick(ItemStack item, InventoryClickEvent event) {

		event.setCancelled(true);

		if (item.getType() == Material.ARROW) {
			if (lastGui != null) {
				getPlayer().closeInventory();
				GuiManager.openGui(lastGui);
			} else
				getPlayer().closeInventory();

			return;
		}

		NBTItem nbt = new NBTItem(item);
		if (!nbt.hasKey("itemID")) {
			return;
		}

		int itemID = nbt.getInteger("itemID");

		ClickType action = event.getClick();

		if (itemID == 0) {

			boolean see = SettingsManager.isEnabled(getPlayer().getName(), Games.PETS, "pets-see-others", true);

			if (action.equals(ClickType.LEFT)) {

				if (see) {
					getPlayer().sendMessage("§cVous affichez déjà les familiers des autres joueurs !");
				} else {
					SettingsManager.setSetting(getPlayer().getName(), Games.PETS, "pets-see-others",
							String.valueOf(true));
					getPlayer().sendMessage("§7Visibilité des autres Familiers §aactivé");
				}

			} else if (action.equals(ClickType.RIGHT)) {

				if (!see) {
					getPlayer().sendMessage("§cVous cachez déjà les familiers des autres joueurs !");
				} else {
					SettingsManager.setSetting(getPlayer().getName(), Games.PETS, "pets-see-others",
							String.valueOf(false));
					getPlayer().sendMessage("§7Visibilité des autres Familiers §cdésactivé");
				}

			}

		} else if (itemID == 1) {

			boolean see = SettingsManager.isEnabled(getPlayer().getName(), Games.PETS, "pets-see-own", true);

			if (action.equals(ClickType.LEFT)) {

				if (see) {
					getPlayer().sendMessage("§cVotre familier est déjà visible");
				} else {
					SettingsManager.setSetting(getPlayer().getName(), Games.PETS, "pets-see-own", String.valueOf(true));
					getPlayer().sendMessage("§aFamilier visible §f(/pet hide pour le faire disparaitre)");
				}

			} else if (action.equals(ClickType.RIGHT)) {

				if (!see) {
					getPlayer().sendMessage("§cFamilier déjà camouflé §f(/pet show pour le faire revenir)");
				} else {
					SettingsManager.setSetting(getPlayer().getName(), Games.PETS, "pets-see-own",
							String.valueOf(false));
					getPlayer().sendMessage("§aFamilier camouflé §f(/pet show pour le faire revenir)");
				}

			}

		} else if (itemID == 2) {

			if (action.equals(ClickType.LEFT)) {

				getPlayer().performCommand("chat on");

			} else if (action.equals(ClickType.RIGHT)) {

				getPlayer().performCommand("chat off");

			}

		} else if (itemID == 3) {

			if (action.equals(ClickType.LEFT)) {

				SettingsManager.setSetting(getPlayer().getName(), Games.LOBBY, "hightlight-sound",
						String.valueOf(true));
				getPlayer().sendMessage("§7Son des notifications dans me chat: §aactivé");

			} else if (action.equals(ClickType.RIGHT)) {

				SettingsManager.setSetting(getPlayer().getName(), Games.LOBBY, "hightlight-sound",
						String.valueOf(false));
				getPlayer().sendMessage("§7Son des notifications dans me chat: §cdésactivé");

			}

		} else if (itemID == 4) {

			if (action.equals(ClickType.LEFT)) {

				SettingsManager.setSetting(getPlayer().getName(), Games.LOBBY, "messages-enable", "on");
				getPlayer().sendMessage("§7Messages privés : §aActivés");

			} else if (action.equals(ClickType.RIGHT)) {

				SettingsManager.setSetting(getPlayer().getName(), Games.LOBBY, "messages-enable", "off");
				getPlayer().sendMessage("§7Messages privés : §cDésactivés");

			} else if (action.equals(ClickType.MIDDLE)) {

				SettingsManager.setSetting(getPlayer().getName(), Games.LOBBY, "messages-enable", "friends");
				getPlayer().sendMessage("§7Messages privés : §cAmis uniquement");

			}

		} else if (itemID == 5) {

			boolean sound = SettingsManager.isEnabled(getPlayer().getName(), Games.LOBBY, "messages-sound", true);
			SettingsManager.setSetting(getPlayer().getName(), Games.LOBBY, "messages-sound",
					(sound ? Boolean.toString(false) : Boolean.toString(true)));

			getPlayer().sendMessage("§aSon des messages privés: " + ((sound) ? "§cDésactivé" : "§aActivé"));

		} else if (itemID == 6) {

			if (action.equals(ClickType.LEFT)) {

				SettingsManager.setSetting(getPlayer().getName(), Games.LOBBY, "friends-enabled", "on");
				getPlayer().sendMessage("§7Demandes d'amis : §aActivés");

			} else if (action.equals(ClickType.RIGHT)) {

				SettingsManager.setSetting(getPlayer().getName(), Games.LOBBY, "friends-enabled", "off");
				getPlayer().sendMessage("§7Messages privés : §cDésactivés");

			} else if (action.equals(ClickType.MIDDLE)) {

				SettingsManager.setSetting(getPlayer().getName(), Games.LOBBY, "friends-enabled", "off");
				getPlayer().sendMessage("§7Messages privés : §eAmis uniquement");

			}

		} else if (itemID == 7) {

			boolean sound = SettingsManager.isEnabled(getPlayer().getName(), Games.LOBBY, "messages-sound", true);
			SettingsManager.setSetting(getPlayer().getName(), Games.LOBBY, "messages-sound",
					(sound ? Boolean.toString(false) : Boolean.toString(true)));

			getPlayer().sendMessage("§aSon des demandes d'amis: " + ((sound) ? "§cDésactivé" : "§aActivé"));

		} else if (itemID == 8) {

			if (action.equals(ClickType.LEFT)) {

				SettingsManager.setSetting(getPlayer().getName(), Games.LOBBY, "party-enable", "on");
				getPlayer().sendMessage("§7Invitations de groupes : §aAccepter toutes les invitations");

			} else if (action.equals(ClickType.RIGHT)) {

				SettingsManager.setSetting(getPlayer().getName(), Games.LOBBY, "party-enable", "off");
				getPlayer().sendMessage("§7Invitations de groupes : §cRefuser toutes les invitations");

			} else if (action.equals(ClickType.MIDDLE)) {

				SettingsManager.setSetting(getPlayer().getName(), Games.LOBBY, "party-enable", "friends");
				getPlayer().sendMessage("§7Invitations de groupes : §eAccepter seulement les amis");

			}

		} else if (itemID == 9) {

			boolean sound = SettingsManager.isEnabled(getPlayer().getName(), Games.LOBBY, "party-sound", true);
			SettingsManager.setSetting(getPlayer().getName(), Games.LOBBY, "party-sound",
					(sound ? Boolean.toString(false) : Boolean.toString(true)));

			getPlayer().sendMessage("§aSon des invitations de groupes: " + ((sound) ? "§cDésactivé" : "§aActivé"));

		} else if (itemID == 10) {

			if (action.equals(ClickType.LEFT)) {

				SettingsManager.setSetting(getPlayer().getName(), Games.LOBBY, "party-follow", String.valueOf(true));

				getPlayer().sendMessage("§7Party Follow : §aActivé");

			} else if (action.equals(ClickType.RIGHT)) {

				SettingsManager.setSetting(getPlayer().getName(), Games.LOBBY, "party-follow", String.valueOf(false));

				getPlayer().sendMessage("§7Party Follow : §cDésactivé");

			}

		}

	}

	@Override
	public void onClose() {

	}

}
