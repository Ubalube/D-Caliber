package com.ubalube.scifiaddon.util.packets;

public interface ISquad 
{
	public void leaveSquad();
	public void joinSquad(int squad);
	public void setSquadByID(int id); 
	public void setSquadRep(int amt);
	public void removeSquadRep(int amt);
	public String getSquadName(int id);
	
	public int getSquadRep();
	public int getSquadByID();
	public boolean isInSquad();
}
