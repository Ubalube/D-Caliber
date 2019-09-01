package com.ubalube.scifiaddon.data;

import java.util.ArrayList;
import java.util.List;

import com.ubalube.scifiaddon.commands.util.Loadout;
import com.ubalube.scifiaddon.commands.util.LoadoutSlots;
import com.ubalube.scifiaddon.init.ModItems;
import com.ubalube.scifiaddon.util.Reference;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.storage.MapStorage;
import net.minecraft.world.storage.WorldSavedData;

public class LoadoutData extends WorldSavedData
{
	private static final String DATA_NAME = Reference.MOD_ID + "_loadoutData";
	public static List<Loadout> loadouts = new ArrayList<>();
	private boolean loaded;
	
	public LoadoutData()
	{
		super(DATA_NAME);
		loaded = false;
	}
	
	public LoadoutData(String name) {
		super(name);
		loaded = false;
	}
	
	public void load(World worldIn)
	{
		if(!loaded)
		{
			loadouts = get(worldIn).loadouts;
			for(Loadout loadout : loadouts)
			{
				System.out.println("Loaded Loadout");
			}
			loaded = true;
		}
	}
	
	public LoadoutData get(World world)
	{
		MapStorage storage = world.getMapStorage();
		LoadoutData data = (LoadoutData)storage.getOrLoadData(LoadoutData.class, DATA_NAME);
		if(data == null)
		{
			data = new LoadoutData();
			storage.setData(DATA_NAME, data);
		}
		
		return data;
	}
	
	public void setLoadout(LoadoutSlots slots, Loadout loadout)
	{
		int slotToModify = 0;
		if(slots.equals(LoadoutSlots.LOADOUT1))
			slotToModify = 1;
		
		if(slots.equals(LoadoutSlots.LOADOUT2))
			slotToModify = 2;

		if(slots.equals(LoadoutSlots.LOADOUT3))
			slotToModify = 3;
		
		if(slots.equals(LoadoutSlots.LOADOUT4))
			slotToModify = 4;
		
		loadouts.get(slotToModify).items = loadout.items;
		loadouts.get(slotToModify).Primary = loadout.Primary;
		loadouts.get(slotToModify).Secondary = loadout.Secondary;
	}
	
	public List<Loadout> getLoadouts()
	{
		
		//Will add more loadouts if there aren't enough
		if(loadouts.size() < 3)
		{
			for(int i = loadouts.size(); i < 3; i++)
			{
				Loadout loadout = new Loadout();
				loadout.Primary = new ItemStack(ModItems.AK12);
				loadout.Secondary = new ItemStack(ModItems.GLOCK_SCOPED);
				loadouts.add(loadout);
			}
		}
		
		return loadouts;
	}
	
	public Loadout getLoadout(String name)
	{
		for(Loadout l : loadouts)
		{
			if(l.loadoutName == name)
			{
				return l;
			}
		}
		return null;
	}
	
	public String getPrefix(Loadout l)
	{
		
		String prefix = "";
		for(Loadout loadout : loadouts)
		{
			if(loadout == l)
			{
				prefix = getLoadoutSlotByName(l.loadoutName).toString();
			}
		}
		
		return prefix;
	}
	
	public LoadoutSlots getLoadoutSlotByName(String s)
	{
		
		int i = 0;
		LoadoutSlots slot = null;
		
		for(Loadout l : loadouts)
		{
			if(l.loadoutName == s)
			{
				if(i == 1)
					slot = LoadoutSlots.LOADOUT1;
				
				if(i == 2)
					slot = LoadoutSlots.LOADOUT2;
					
				if(i == 3)
					slot = LoadoutSlots.LOADOUT3;
				
				if(i == 4)
					slot = LoadoutSlots.LOADOUT4;
				
				break;
			}
			i++;
		}
		
		return slot;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		
		List<Loadout> newLoadouts = new ArrayList<>();
		
		for(LoadoutSlots l : LoadoutSlots.values())
		{
			if(nbt.hasKey(l.toString()))
			{
				newLoadouts.add(getLoadout(nbt.getString(l.toString())));
			}
		}
		
		loadouts = newLoadouts;
		
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		for(Loadout loadout : loadouts)
		{
			compound.setString(getPrefix(loadout), loadout.loadoutName);
		}
		return compound;
	}
}
