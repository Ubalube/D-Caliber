package com.ubalube.scifiaddon.vehicles;

import java.util.UUID;

import javax.annotation.Nullable;

import com.google.common.base.Optional;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class VehicleHumvee extends EntityCreature
{
	public VehicleHumvee(World worldIn) {
		super(worldIn);
		
		this.setSize(2F, 1.25F);
		// TODO Auto-generated constructor stub
	}

	private static final DataParameter<Optional<UUID>> OWNER_UNIQUE_ID = EntityDataManager.<Optional<UUID>>createKey(VehicleHumvee.class, DataSerializers.OPTIONAL_UNIQUE_ID);
	private static final DataParameter<Boolean> MOVING = EntityDataManager.<Boolean>createKey(VehicleHumvee.class, DataSerializers.BOOLEAN);
	
	public boolean isMoving()
    {
        return ((Boolean)this.dataManager.get(MOVING)).booleanValue();
    }
	
	@Override
	public boolean canBePushed() {
		return !this.isBeingRidden();
	}
	
	protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        return SoundEvents.BLOCK_METAL_HIT;
    }

    protected SoundEvent getDeathSound()
    {
        return SoundEvents.ENTITY_GENERIC_EXPLODE;
    }

    private void setMoving(boolean moving)
    {
        this.dataManager.set(MOVING, Boolean.valueOf(moving));
    }
    
    protected void entityInit()
    {
        super.entityInit();
        this.dataManager.register(OWNER_UNIQUE_ID, Optional.absent());
        this.dataManager.register(MOVING, Boolean.valueOf(false));
    }
	
    protected void mountTo(EntityPlayer player)
    {
        player.rotationYaw = this.rotationYaw;
        player.rotationPitch = this.rotationPitch;

        if (!this.world.isRemote)
        {
            player.startRiding(this);
        }
    }
    
    public boolean processInteract(EntityPlayer player, EnumHand hand)
    {
        if (!super.processInteract(player, hand))
        {
            ItemStack itemstack = player.getHeldItem(hand);

            if (itemstack.getItem() == Items.NAME_TAG)
            {
                itemstack.interactWithEntity(player, this, hand);
                return true;
            }
            else if (!this.isBeingRidden())
            {
                if (!this.world.isRemote)
                {
                    player.startRiding(this);
                }

                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            return true;
        }
    }
    
    @Override
    public double getMountedYOffset()
    {
        return (double)this.height * 0.10D;
    }
    
    @Nullable
    public Entity getControllingPassenger()
    {
        return this.getPassengers().isEmpty() ? null : (Entity)this.getPassengers().get(0);
    }
	
	public boolean canBeSteered()
    {
		return this.getControllingPassenger() instanceof EntityLivingBase;
    }
	
	public void travel(float strafe, float vertical, float forward)
    {
		if (this.isBeingRidden() && this.canBeSteered())
        {
            EntityLivingBase entitylivingbase = (EntityLivingBase)this.getControllingPassenger();
            this.rotationYaw = entitylivingbase.rotationYaw;
            strafe = entitylivingbase.moveStrafing * 0.5F;
            forward = entitylivingbase.moveForward;
            
            if (this.canPassengerSteer())
            {
                this.setAIMoveSpeed((float)this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue());
                super.travel(strafe, vertical, forward);
            }
            else if (entitylivingbase instanceof EntityPlayer)
            {
                this.motionX = 0.0D;
                this.motionY = 0.0D;
                this.motionZ = 0.0D;
            }
        }
        else
        {
            this.jumpMovementFactor = 0.02F;
            super.travel(strafe, vertical, forward);
        }
    }
    
}
