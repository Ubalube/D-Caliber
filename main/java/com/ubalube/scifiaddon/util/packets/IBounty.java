package com.ubalube.scifiaddon.util.packets;

public interface IBounty {
	public void stopBounty();
	public void startBounty(int squad);
	public void addBountyPoint(int amt);
	public void removeBountyPoint(int amt);
	public String getBountyName(int id);
	public int getBountyPoints();
	public void setActiveBounty(int id);
	public int getActiveBounty();
	public void bountyState(boolean completed);
	public void setBountyPoints(int points);
	public void setActive(boolean active);
	
	public int getBountyByID();
	public boolean activeBounty();
	public boolean bountyCompleted();
}
