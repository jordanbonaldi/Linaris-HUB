package net.neferett.linaris.lobby.handlers;

import java.util.Arrays;
import java.util.HashMap;

import org.bukkit.Location;

import com.sainttx.holograms.api.Hologram;
import com.sainttx.holograms.api.line.TextLine;

import net.neferett.linaris.lobby.Main;

public class HologramsManager {

	public static HologramsManager i;

	public static HologramsManager get() {
		return i == null ? i = new HologramsManager() : i;
	}

	HashMap<String, Hologram> ho = new HashMap<>();

	public HologramsManager addHologram(final String name, final Location l, final String... a) {
		if (!l.getChunk().isLoaded())
			l.getChunk().load();

		final Hologram h = new Hologram(name, l);
		Arrays.asList(a).forEach(e -> {
			h.addLine(new TextLine(h, e));
		});
		this.ho.put(name, h);
		return this;
	}

	public void build() {
		this.ho.forEach((n, h) -> Main.getMainInstance().getHologramManager().addActiveHologram(h));
	}

}
