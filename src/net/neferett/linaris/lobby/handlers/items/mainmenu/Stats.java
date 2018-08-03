package net.neferett.linaris.lobby.handlers.items.mainmenu;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import net.neferett.linaris.BukkitAPI;
import net.neferett.linaris.lobby.handlers.inventories.MainMenuInventory;
import net.neferett.linaris.lobby.handlers.inventories.StatsInventory;
import net.neferett.linaris.lobby.minigames.GamesEnum;
import net.neferett.linaris.mistery.GlowUtils;
import net.neferett.linaris.specialitems.MenuItem;
import net.neferett.linaris.specialitems.SpecialItem;
import net.neferett.linaris.utils.gui.GuiManager;

public class Stats extends MenuItem {

	public static int id = -1;

	public static SpecialItem get() {
		if (id == -1)
			id = registerItem(new Stats());
		return get(id);
	}

	public Stats() {
		super("§6Stats et Succès", GlowUtils.addGlow(new ItemStack(Material.BOOK_AND_QUILL)), "§7Vos stats et succès");
	}

	@Override
	public void inventoryClickEvent(final Player player) {
		if (GamesEnum.getGamesPlayed(BukkitAPI.get().getPlayerDataManager().getPlayerData(player.getName())) == null) {
			player.sendMessage("§cVous n'avez joué à aucun jeu rassemblant des statistiques pour le moment");
			return;
		}
		GuiManager.openGui(new StatsInventory(player, new MainMenuInventory(player)));
		this.setCancelInteractEvent(true);
	}

}
