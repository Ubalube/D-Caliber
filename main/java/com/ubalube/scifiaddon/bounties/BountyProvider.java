package com.ubalube.scifiaddon.bounties;

import com.ubalube.scifiaddon.util.packets.IBounty;
import com.ubalube.scifiaddon.util.packets.ISquad;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class BountyProvider implements ICapabilitySerializable<NBTBase>{

	@CapabilityInject(IBounty.class)
	public static final Capability<IBounty> BOUNTY = null;
	
	private IBounty instance = BOUNTY.getDefaultInstance();
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		return capability == BOUNTY;
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		return capability == BOUNTY ? BOUNTY.<T> cast(this.instance) : null;
	}

	@Override
	public NBTBase serializeNBT() {
		return BOUNTY.getStorage().writeNBT(BOUNTY, this.instance, null);
	}

	@Override
	public void deserializeNBT(NBTBase nbt) {
		BOUNTY.getStorage().readNBT(BOUNTY, this.instance, null, nbt); 
	}

}
