package net.neferett.linaris.handlers.items;

import java.util.HashMap;
import java.util.function.BiConsumer;

import net.neferett.linaris.specialitems.SpecialItem;

public class ItemsManager {

	public static ItemsManager i;

	public static ItemsManager get() {
		return i == null ? i = new ItemsManager() : i;
	}

	HashMap<SpecialItem, Integer> items = new HashMap<>();

	public void ActionOnEachItems(final BiConsumer<SpecialItem, Integer> a) {
		this.items.forEach(a);
	}

	public void addItems(final SpecialItem i, final int pos) {
		this.items.put(i, pos);
	}

	public HashMap<SpecialItem, Integer> getItems() {
		return this.items;
	}
}
