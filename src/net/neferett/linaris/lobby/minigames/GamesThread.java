package net.neferett.linaris.lobby.minigames;

import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import net.neferett.linaris.api.Games;
import net.neferett.linaris.lobby.Main;
import net.neferett.linaris.utils.TimeUtils;

public class GamesThread implements Runnable {

	private final HashMap<Games, Long> servers = new HashMap<>();

	@Override
	public void run() {
		while (!Main.getMainInstance().willClose) {
			Arrays.asList(GamesEnum.values()).forEach((a) -> {
				final GamesManager gm = a.getGm();

				if (gm.getSelected() == null && !gm.needCreation())
					gm.selectGame();
				if (gm.needCreation() && (!this.servers.containsKey(gm.getG())
						|| !TimeUtils.CreateTestCoolDown(35).test(this.servers.get(gm.getG())))) {
					gm.createGame();
					this.servers.put(gm.getG(), System.currentTimeMillis());
					System.out.println("CREATED");
				} else if (!gm.isSelectedAlwaysAvailable())
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
