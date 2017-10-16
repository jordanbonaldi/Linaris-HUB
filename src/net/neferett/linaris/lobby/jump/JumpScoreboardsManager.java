package net.neferett.linaris.lobby.jump;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.entity.Player;

import net.neferett.linaris.utils.tasksmanager.TaskManager;
import net.neferett.linaris.lobby.utils.ScoreboardSign;

public class JumpScoreboardsManager {

	Player p;
	JumpInfo info;
	JumpsManager manager;
	
	public JumpScoreboardsManager(JumpsManager manager,Player p,JumpInfo info) {
		this.manager = manager;
		this.p = p;
		this.info = info;
		TaskManager.scheduleSyncRepeatingTask("Jump_" + p.getName(), this::update, 0, 20);
	}

	public void update() {

		if (info == null) return;
		
		info.totalTime++;
		
		ScoreboardSign bar = ScoreboardSign.get(p);
		if (bar == null) {
			bar = new ScoreboardSign(p, "§b§lJump des dieux");
			bar.create();	
		}

		HashMap<Integer, String> lines = new HashMap<Integer, String>();

		lines.put(15, "§1");
		
		int slot = 14;
		
		if (info.getNowCheckpoint() != 0)
			for (int i = 1 ; i <= info.getNowCheckpoint() ; i++) {
				lines.put(slot, "§a" + (i) + ". §7>> §e" + getTimeFormat(info.getTime(i)) + " §7<<");
				slot--;
			}
		
		lines.put(slot, "§a" + (info.getNowCheckpoint()+1) + ". §7>> §e§l" + getTimeFormat(info.getNowTime()) + " §7<<");
		
		lines.put(4, "§2");
		lines.put(3, "§aTotal: §e" + getTimeFormat(info.getTotalTime()));
		lines.put(2, "§7Abandon: §f/jump end");
		lines.put(1, "§bVie(s): §e" + info.getLife());

		if (lines.isEmpty())
			return;
		
		ArrayList<Integer> needRemoved = new ArrayList<>();
		
		for (int i = 1; i < 16; i++) {
			if (!lines.containsKey(i)) {
				if (bar.getLine(i) != null)
					needRemoved.add(i);
			} else {
				if (bar.getLine(i) == null)
					bar.setLine(i, lines.get(i));
				else if (!bar.getLine(i).equals(lines.get(i)))
					bar.setLine(i, lines.get(i));
			}
		}
		
		for (int i : needRemoved)
			bar.removeLine(i);

	}
	
	public String getTimeFormat(long time) {
		
		int minutes = (int) time/60;
		int seconds = (int) time%60;
		
		return String.format("%02d", minutes) + ":" + String.format("%02d", seconds);
	}

}
