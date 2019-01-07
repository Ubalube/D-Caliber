package com.ubalube.scifiaddon.squads;

import com.ubalube.scifiaddon.util.packets.ISquad;

public class SquadMain implements ISquad
{
	//Squad IDS: 0 = None, 1 = Scorpions, 2 = Ghost, 3 = Bandits, 4 = Militia
	public int squadID;
	public int squadRep;
	
	@Override
	public void leaveSquad() {
		this.squadID = 0;
	}
	@Override
	public void joinSquad(int squad) 
	{
		this.squadID = squad;
		this.squadRep = 100;
	}
	@Override
	public void setSquadByID(int id) {
		this.squadID = id;
		
	}
	@Override
	public int getSquadByID() {
		return this.squadID;
	}
	@Override
	public boolean isInSquad() {
		if(this.squadID == 0)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	@Override
	public int getSquadRep() {
		return squadRep;
	}
	@Override
	public void setSquadRep(int amt) 
	{
		this.squadRep = amt;
	}
	
	@Override
	public void removeSquadRep(int amt) {
		this.squadRep -= amt;
		
		if(this.squadRep > 0) this.squadRep = 0;
		
	}
	@Override
	public String getSquadName(int id) {
		
		String squadName = null;
		
		switch(id)
		{
		case 0:
			squadName = "None";
			break;
		case 1:
			squadName = "Ghost";
			break;
		case 2:
			squadName = "Bandits";
			break;
		case 3:
			squadName = "Milita";
			break;
		case 4:
			squadName = "Scorpions";
			break;
		default:
			squadName = "None";
			break;
		}
		return squadName;
	}
	
}
