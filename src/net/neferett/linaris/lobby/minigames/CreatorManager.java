package net.neferett.linaris.lobby.minigames;

import net.neferett.linaris.api.SocketEvent.SendMessage;

public class CreatorManager {

	private String name;

	public CreatorManager(final String name, final String mode, final boolean ml) {
		this.name = name + (mode != null ? mode : "");
		if (ml)
			this.getRandomGame();
	}

	public void build() {
		System.out.println("SEND -> " + "start " + this.name);
		new SendMessage("163.172.71.30", 12000, "start " + this.name).build();
	}

	private void getRandomGame() {
		this.name = this.name + this.getRandomInteger(3, 1);
	}

	private int getRandomInteger(final int maximum, final int minimum) {
		return (int) (Math.random() * (maximum - minimum)) + minimum;
	}

}
