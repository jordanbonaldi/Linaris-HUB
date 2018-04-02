package net.neferett.linaris.lobby.handlers.items.games;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import net.neferett.linaris.BukkitAPI;
import net.neferett.linaris.api.Games;
import net.neferett.linaris.utils.ItemBuilder;

public abstract class Items {

	private final boolean			e;
	private final Games				g;
	protected ItemBuilder			ib;
	protected final List<String>	lores;
	private final Material			m;
	protected int					players;

	public Items(final Games g, final Material m, final boolean e, final String... strings) {
		this.g = g;
		this.m = m;
		this.e = e;
		this.lores = Arrays.asList(strings);
		this.ib = new ItemBuilder(this.m);
	}

	public Games getG() {
		return this.g;
	}

	public ItemStack getItem() {
		this.players = BukkitAPI.get().getProxyDataManager().getPlayerInGame(this.g);
		this.load();
		this.loadIB();
		return this.ib.build();
	}

	public List<String> getLores() {
		return this.lores;
	}

	public abstract void load();

	private void loadIB() {
		if (this.e)
			this.ib.addEnchantment(Enchantment.DURABILITY, 1);
		this.ib.setTitle("§b§n" + this.g.getDisplayName());
	}

}
