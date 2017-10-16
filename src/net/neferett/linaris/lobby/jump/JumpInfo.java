package net.neferett.linaris.lobby.jump;

public class JumpInfo {

	String playerName;
	
	int life;
	
	int nowCheckpoint;
	
	int totalTime;
	int[] finishTime;
	
	JumpScoreboardsManager manager;
	
	public JumpInfo(String playerName) {
		this.playerName = playerName;
		this.life = 0;
		this.totalTime = 0;
		this.nowCheckpoint = 0;
		this.finishTime = new int[7];	
	}
	
	public void setManager(JumpScoreboardsManager manager) {
		this.manager = manager;
	}
	
	public JumpScoreboardsManager getManager() {
		return manager;
	}
	
	public String getPlayerName() {
		return playerName;
	}
	
	public int getLife() {
		return life;
	}
	
	public int getNowCheckpoint() {
		return nowCheckpoint;
	}

	public int getNowTime() {
		if (nowCheckpoint != 0)
			return totalTime - finishTime[nowCheckpoint-1];
		else 
			return totalTime;
	}
	
	public int getLastTime() {
		if (nowCheckpoint != 0)
			return finishTime[nowCheckpoint-1];
		else 
			return 0;
	}
	
	public int getTotalTime() {
		return totalTime;
	}
	
	public int getTime(int checkpoint) {
		return finishTime[checkpoint-1];
	}
	
	public void setNowCheckpoint(int nowCheckpoint) {
		this.finishTime[this.nowCheckpoint] = getNowTime();
		this.nowCheckpoint = nowCheckpoint;
	}
	
	public void giveLife(int lifes) {
		this.life += lifes;
	}
	
	public void setLife(int life) {
		this.life = life;
	}
}
