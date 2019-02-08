package com.ubalube.scifiaddon.bounties;

import com.ubalube.scifiaddon.util.packets.IBounty;

public class Bounty implements IBounty
{

	public boolean bountyActive;
	public boolean bountyFinished;
	
	public int bountyPoints;
	public int bountyId;
	
	@Override
	public void stopBounty() {
		this.bountyActive = false;
	}

	@Override
	public void startBounty(int squad) {
		this.bountyActive = true;
	}

	@Override
	public void addBountyPoint(int amt) {
		this.addBountyPoint(this.bountyPoints + amt);
	}

	@Override
	public void removeBountyPoint(int amt) {
		this.addBountyPoint(this.bountyPoints - amt);
	}

	@Override
	public String getBountyName(int id) {
		return "Bounty";
	}

	@Override
	public int getBountyPoints() {
		return this.bountyPoints;
	}

	@Override
	public int getBountyByID() {
		return this.bountyId;
	}

	@Override
	public boolean activeBounty() {
		return this.bountyActive;
	}

	@Override
	public boolean bountyCompleted() {
		return this.bountyFinished;
	}

	@Override
	public void setActiveBounty(int id) {
		this.bountyId = id;
		this.bountyActive = true;
	}

	@Override
	public int getActiveBounty() {
		return this.bountyId;
	}

	@Override
	public void bountyState(boolean completed) {
		if(completed)
		{
			this.setActive(false);
			this.bountyFinished = true;
		}
	}

	@Override
	public void setBountyPoints(int points) {
		this.bountyPoints = points;
	}

	@Override
	public void setActive(boolean active) {
		this.bountyActive = active;
	}

}
