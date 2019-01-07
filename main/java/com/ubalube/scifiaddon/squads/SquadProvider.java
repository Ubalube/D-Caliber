package com.ubalube.scifiaddon.squads;

import com.ubalube.scifiaddon.util.packets.ISquad;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class SquadProvider implements ICapabilitySerializable<NBTBase>
{
	@CapabilityInject(ISquad.class)
	public static final Capability<ISquad> SQUAD = null;
	
	private ISquad instance = SQUAD.getDefaultInstance();

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		return capability == SQUAD;
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		return capability == SQUAD ? SQUAD.<T> cast(this.instance) : null;
	}

	@Override
	public NBTBase serializeNBT() {
		return SQUAD.getStorage().writeNBT(SQUAD, this.instance, null);
	}

	@Override
	public void deserializeNBT(NBTBase nbt) 
	{
		SQUAD.getStorage().readNBT(SQUAD, this.instance, null, nbt); 
	}
	
}
