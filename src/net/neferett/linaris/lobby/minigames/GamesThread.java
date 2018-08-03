package net.neferett.linaris.lobby.minigames;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import net.neferett.linaris.lobby.Main;

public class GamesThread implements Runnable {

	@Override
	public void run() {
		while (!Main.getMainInstance().willClose) {
			Arrays.asList(GamesEnum.values()).forEach((a) -> {
				final GamesManager gm = a.getGm();

				gm.selectGame();
			});

			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (final InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
