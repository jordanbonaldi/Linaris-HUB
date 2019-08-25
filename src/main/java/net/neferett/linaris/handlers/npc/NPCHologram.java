package net.neferett.linaris.handlers.npc;

import com.sainttx.holograms.api.Hologram;
import com.sainttx.holograms.api.line.TextLine;
import com.sainttx.holograms.api.line.UpdatingHologramLine;

import net.neferett.linaris.minigames.GamesManager;

public class NPCHologram extends TextLine implements UpdatingHologramLine {

	GamesManager gm;
	private long				lastupdate;
	private final NPC.getText	text;

	public NPCHologram(final Hologram parent, final NPC.getText t, final GamesManager gm) {
		super(parent, t.getMsg(gm));
		this.text = t;
		this.gm = gm;
	}

	@Override
	public long getDelay() {
		return 2000;
	}

	@Override
	public long getLastUpdateTime() {
		return this.lastupdate;
	}

	@Override
	public void update() {
		this.lastupdate = System.currentTimeMillis();
		if (this.gm.isSelectedAlwaysAvailable())
			this.setText(this.text.getMsg(this.gm));
	}

}
