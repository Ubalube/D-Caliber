package com.ubalube.scifiaddon.util.Player;

import com.ubalube.scifiaddon.util.Player.util.ILoadout;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class LoadoutProvider implements ICapabilitySerializable<NBTBase> { 
	@CapabilityInject(ILoadout.class) 
	public static final Capability<ILoadout> LOADOUTS = null; 

	private ILoadout instance = LOADOUTS.getDefaultInstance(); 

	@Override 
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) { 
		return capability == LOADOUTS; 
	} 

	@Override 
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) { 
		return capability == LOADOUTS ? LOADOUTS.<T> cast(this.instance) : null; 
	} 

	@Override 
	public NBTBase serializeNBT() { 
		return LOADOUTS.getStorage().writeNBT(LOADOUTS, this.instance, null); 
	} 

	@Override 
	public void deserializeNBT(NBTBase nbt) { 
		LOADOUTS.getStorage().readNBT(LOADOUTS, this.instance, null, nbt); 
	}
}
