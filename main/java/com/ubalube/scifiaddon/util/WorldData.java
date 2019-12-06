package com.ubalube.scifiaddon.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.util.Constants;
import net.minecraft.world.World;
import net.minecraft.world.storage.MapStorage;
import net.minecraft.world.storage.WorldSavedData;
import scala.Array;

public class WorldData extends WorldSavedData
{

	public static Map<UUID, String> team = new HashMap<>();
	public static Map<String, List<UUID>> teams = new HashMap<>();
	public static Map<String, UUID> teamOwner = new HashMap<>();
	
	public WorldData()
	{
		super(Reference.MOD_ID);
	}
	
	public WorldData(String name) {
		super(name);
		markDirty();
	}
	
	public void setTeamOwner(EntityPlayer owner, String name)
	{
		teamOwner.put(name, owner.getUniqueID());
	}
	
	public UUID getTeamOwner(String teamName)
	{
		return teamOwner.get(teamName);
	}
	
	public void addNewTeam(String name, EntityPlayer owner)
	{
		List<UUID> list = new ArrayList<>();
		list.add(owner.getUniqueID());
		teams.put(name, list);
		team.put(owner.getUniqueID(), name);
		teamOwner.put(name, owner.getUniqueID());
		markDirty();
	}
	
	public void addPlayerToTeam(EntityPlayer owner, UUID uuid)
	{
		String name = team.get(owner.getUniqueID());
		teams.get(name).add(uuid);
		team.put(uuid, name);
		markDirty();
	}

    public void removePlayer(EntityPlayer p,UUID uid) {
        String name = team.get(p.getUniqueID());
        teams.get(name).remove(uid);
        team.remove(uid);
        markDirty();
    }

    public void removeTeam(String name) {
        Iterator<UUID> uuidIterator = teams.get(name).iterator();
        while(uuidIterator.hasNext()) {
            UUID id = uuidIterator.next();
            team.remove(id);
        }
        teams.remove(name);
        teamOwner.remove(name);
        markDirty();
    }


	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		teams.clear();
		team.clear();
		String name = "";
		Iterator<NBTBase> tagList = nbt.getTagList("Teams", Constants.NBT.TAG_COMPOUND).iterator();
        while(tagList.hasNext()) {
            NBTTagCompound tagCompound = (NBTTagCompound)tagList.next();
            Iterator<NBTBase> playerTagListIterator = tagCompound.getTagList("Player List",Constants.NBT.TAG_COMPOUND).iterator();
            List<UUID> uuidList = new ArrayList();
            while(playerTagListIterator.hasNext()) {
                NBTTagCompound playerTag = (NBTTagCompound)playerTagListIterator.next();
                UUID id = UUID.fromString(playerTag.getString("uuid"));
                name = tagCompound.getString("Team Name");
                team.put(id,name);
                uuidList.add(id);
            }
            teams.put(name,uuidList);
        }
		
		
	}


	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		NBTTagList tagList = new NBTTagList();
        Iterator<String> iteratorTeams = teams.keySet().iterator();
        while(iteratorTeams.hasNext()) {
            NBTTagCompound tagCompound = new NBTTagCompound();
            String team = iteratorTeams.next();
            tagCompound.setString("Team Name",team);


            NBTTagList playerListTag = new NBTTagList();
            Iterator<UUID> uuidIterator = teams.get(team).iterator();
            while(uuidIterator.hasNext()) {
                UUID id = uuidIterator.next();
                NBTTagCompound playerTag = new NBTTagCompound();
                playerTag.setString("uuid",id.toString());
                playerListTag.appendTag(playerTag);
            }
            tagCompound.setTag("Player List",playerListTag);
            tagList.appendTag(tagCompound);
        }
        compound.setTag("Teams",tagList);
        return compound;
	}
	
	public static WorldData get(World world) {
        MapStorage storage = world.getMapStorage();
        WorldData data = (WorldData)storage.getOrLoadData(WorldData.class,Reference.MOD_ID);
        if (data==null) {
            data = new WorldData();
            world.setData(Reference.MOD_ID,data);
        }
        return data;
    }

}
