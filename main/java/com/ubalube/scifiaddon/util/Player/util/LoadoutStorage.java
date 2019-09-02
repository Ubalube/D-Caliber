package com.ubalube.scifiaddon.util.Player.util;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTPrimitive;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class LoadoutStorage implements IStorage<ILoadout> {

	@Override 
	public NBTBase writeNBT(Capability<ILoadout> capability, ILoadout instance, EnumFacing side) 
	{ 
		return new NBTTagInt(instance.getLoadoutID()); 
	} 

	@Override 
	public void readNBT(Capability<ILoadout> capability, ILoadout instance, EnumFacing side, NBTBase nbt) 
	{ 
		instance.setLoadoutID(((NBTPrimitive) nbt).getInt());
	} 
}
