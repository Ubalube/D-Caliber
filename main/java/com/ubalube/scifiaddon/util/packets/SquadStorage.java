package com.ubalube.scifiaddon.util.packets;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTPrimitive;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class SquadStorage implements IStorage<ISquad>
{
    @Override 
    public NBTBase writeNBT(Capability<ISquad> capability, ISquad instance, EnumFacing side) 
    { 
        return new NBTTagInt(instance.getSquadByID()); 
    } 

    @Override 
    public void readNBT(Capability<ISquad> capability, ISquad instance, EnumFacing side, NBTBase nbt) 
    { 
        instance.setSquadByID(((NBTPrimitive) nbt).getInt()); 
    } 
}
