package com.ubalube.scifiaddon.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.EntityMountEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityVehicle extends EntityCreature
{
	
	public EntityVehicle(World worldIn)
    {
        super(worldIn);
    }

	@Override
	protected void entityInit() 
	{
	}
	
	@Override
	public boolean processInteract(EntityPlayer player, EnumHand hand) {
		
		if(this.canBeRidden(player) && !this.world.isRemote)
		{
			player.startRiding(this);
		}
		
		return true;
	}
	
	@Override
	public double getMountedYOffset() 
	{
		
		return 5.0F;
	}
	
	@Override
	protected boolean canBeRidden(Entity entityIn) {
		return true;
	}
	
	@Override
	public void updatePassenger(Entity passenger) {
		// TODO Auto-generated method stub
		super.updatePassenger(passenger);
	}
}
