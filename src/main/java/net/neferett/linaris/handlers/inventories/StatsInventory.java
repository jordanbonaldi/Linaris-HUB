package net.neferett.linaris.handlers.inventories;

import java.text.DecimalFormat;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import net.neferett.linaris.minigames.GamesEnum;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import lombok.Getter;
import net.neferett.linaris.BukkitAPI;
import net.neferett.linaris.api.PlayerData;
import net.neferett.linaris.utils.ItemBuilder;
import net.neferett.linaris.utils.NBTItem;
import net.neferett.linaris.specialitems.SpecialItem;
import net.neferett.linaris.utils.TimeUtils;
import net.neferett.linaris.utils.gui.GuiManager;
import net.neferett.linaris.utils.gui.GuiScreen;

public class StatsInventory extends GuiScreen {

	class Stats {
		@Getter
		int			deaths;
		@Getter
        GamesEnum games;
		@Getter
		ItemStack	item;
		@Getter
		int			kills;
		@Getter
		int			level;
		@Getter
		int			money;

		@Getter
		String		ratio;

		@Getter
		int			score;

		@Getter
		long		time;

		Stats(final PlayerData pd, final String str) {
			this.deaths = pd.getInt(str + "_deaths", 0);
			this.kills = pd.getInt(str + "_kills", 0);
			this.level = pd.getInt(str + "_level", 0);
			this.money = pd.getInt(str + "_money", 0);
			this.score = pd.getInt(str + "_score", 0);
			this.time = pd.getLong(str + "_played", 0);
			this.ratio = this.deaths <= 0 ? this.kills == 0 ? "§cN/A" : this.kills + "" : new DecimalFormat("####.##")
					.format(this.deaths <= 0 ? this.kills : this.kills / (float) this.deaths);
			this.games = GamesEnum.getGameByString(str);
			this.buildItem();
		}

		private void buildItem() {

			final ItemBuilder ib = new ItemBuilder(this.games.getM());

			ib.setTitle("§7Statistiques du jeu: §e" + this.games.getN().getName());
			ib.addLores("");
			ib.addLores("§7Kills: §e" + this.kills);
			ib.addLores("§7Morts: §e" + this.deaths);
			ib.addLores("§7Ratio: §e" + this.ratio);
			ib.addLores("§7Level: §e" + this.level);
			ib.addLores("§7Score: §e" + this.score);
			ib.addLores("");
			ib.addLores("§7Temps de jeu: " + TimeUtils.secondesToDayHoursMinutes(this.getTime()));
			this.item = ib.build();
		}

	}

	GuiScreen	lastGui;

	Player		p;

	public StatsInventory(final Player p, final GuiScreen lastScreen) {
		super("Statistiques", 2, p, false);
		this.lastGui = lastScreen;
		this.p = p;
		this.build();
	}

	public void addItem(final int id, final SpecialItem specialItemId, final int row, final int slot) {
		this.setItemLine(new NBTItem(specialItemId.getStaticItem()).setInteger("itemID", id).getItem(), row, slot);
	}

	@Override
	public void drawScreen() {

		final PlayerData pd = BukkitAPI.get().getPlayerDataManager().getPlayerData(this.p.getName());

		final List<Stats> stats = GamesEnum.getGamesPlayed(pd).stream().map(e -> new Stats(pd, e))
				.collect(Collectors.toList());
		final AtomicInteger slot = new AtomicInteger(-1);
		final AtomicInteger pos = new AtomicInteger(0);

		stats.forEach(e -> {

			if (pos.get() == 0 || pos.get() == 7) {
				pos.set(3);
				slot.addAndGet(2);
			} else
				pos.addAndGet(2);

			this.setItemLine(e.getItem(), slot.get(), pos.get());

		});

		this.setItemLine(15, new ItemBuilder(Material.ARROW).setTitle("§fRevenir en arri§re").build(), 2, 9);

	}

	@Override
	public void onClick(final ItemStack item, final InventoryClickEvent event) {

		event.setCancelled(true);

		if (item.getType() == Material.ARROW) {
			if (this.lastGui != null) {
				this.getPlayer().closeInventory();
				GuiManager.openGui(this.lastGui);
			} else
				this.getPlayer().closeInventory();

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

}
