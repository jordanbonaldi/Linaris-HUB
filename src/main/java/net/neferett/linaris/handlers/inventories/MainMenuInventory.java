package net.neferett.linaris.handlers.inventories;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import net.neferett.linaris.minigames.GamesEnum;
import net.neferett.linaris.minigames.GamesManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import net.neferett.linaris.BukkitAPI;
import net.neferett.linaris.api.Games;
import net.neferett.linaris.handlers.items.games.GamesItems;
import net.neferett.linaris.handlers.items.games.MiniGamesItems;
import net.neferett.linaris.utils.ConfigDatas;
import net.neferett.linaris.utils.ItemBuilder;
import net.neferett.linaris.utils.NBTItem;
import net.neferett.linaris.utils.gui.GuiManager;
import net.neferett.linaris.utils.gui.GuiScreen;

public class MainMenuInventory extends GuiScreen {

	public static int getRandomInteger(final int maximum, final int minimum) {
		return (int) (Math.random() * (maximum - minimum)) + minimum;
	}

	private List<GamesEnum> g = new ArrayList<>();

	public MainMenuInventory(final Player p) {
		super("Menu Principal & Jeux", 5, p, true);
		this.build();
	}

	@Override
	public void drawScreen() {
		final ItemBuilder glass = new ItemBuilder(Material.STAINED_GLASS_PANE);
		glass.setTitle("");
		glass.setDamage((short) 1);
		for (int i = 1; i < 5 * 9; i++)
			this.setItem(glass.build(), i);

		final AtomicInteger i = new AtomicInteger(500);

		this.g.forEach((e) -> {
			this.setMiniGameItemLine(i.incrementAndGet(), e.getGm(), e.getM(), false, e.getLine(), e.getSlot(), "");
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

		super.setItemLine(new NBTItem(i).setInteger("itemID", id).getItem(), line, slot);
	}

}
