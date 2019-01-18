package com.ubalube.scifiaddon.vehicles;

import java.util.UUID;

import javax.annotation.Nullable;

import com.google.common.base.Optional;

import net.minecraft.client.model.ModelBoat;
import net.minecraft.client.model.ModelHorse;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.passive.AbstractHorse;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.play.client.CPacketSteerBoat;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class VehicleHumvee extends EntityCreature
{
	
	private boolean leftInputDown;
    private boolean rightInputDown;
    private boolean forwardInputDown;
    private boolean backInputDown;
    private float deltaRotation;
    private float momentum;
    
    private EntityBoat.Status status;
    
    private static final DataParameter<Optional<UUID>> OWNER_UNIQUE_ID = EntityDataManager.<Optional<UUID>>createKey(VehicleHumvee.class, DataSerializers.OPTIONAL_UNIQUE_ID);
	private static final DataParameter<Boolean> MOVING = EntityDataManager.<Boolean>createKey(VehicleHumvee.class, DataSerializers.BOOLEAN);
	
	public VehicleHumvee(World worldIn) {
		super(worldIn);
		
		this.setSize(2F, 1.25F);
		// TODO Auto-generated constructor stub
	}
	
	protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.2D);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(20.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(125.0D);
        this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(125.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(6.0D);
    }

	
	
	public boolean isMoving()
    {
        return ((Boolean)this.dataManager.get(MOVING)).booleanValue();
    }
	
	private void setMoving(boolean moving)
    {
        this.dataManager.set(MOVING, Boolean.valueOf(moving));
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
            else 
            {
            	if (!this.world.isRemote)
                {
                    player.startRiding(this);
                }
            	return true;
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
        return (double)this.height * 0.30D;
    }
    
    @Nullable
    public Entity getControllingPassenger()
    {
        return this.getPassengers().isEmpty() ? null : (Entity)this.getPassengers().get(0);
    }
    
    protected boolean canFitPassenger(Entity passenger)
    {
        return this.getPassengers().size() < 2;
    }
    
    @Override
    protected void addPassenger(Entity passenger)
    {
        super.addPassenger(passenger);
    }
    
    
    
    public void updatePassenger(Entity passenger)
    {
        if (this.isPassenger(passenger))
        {
            float f = 0.0F;
            float f1 = (float)((this.isDead ? 0.009999999776482582D : this.getMountedYOffset()) + passenger.getYOffset());

            if (this.getPassengers().size() > 1)
            {
                int i = this.getPassengers().indexOf(passenger);

                if (i == 0)
                {
                    f = 0.2F;
                }
                else
                {
                    f = -0.6F;
                }
            }

            Vec3d vec3d = (new Vec3d((double)f, 0.0D, 0.0D)).rotateYaw(-this.rotationYaw * 0.017453292F - ((float)Math.PI / 2F));
            passenger.rotationYaw += this.rotationYaw;
            passenger.setRotationYawHead(passenger.getRotationYawHead() + this.rotationYawHead);
            this.applyYawToEntity(passenger);
            if(passenger != this.getControllingPassenger())
            {
            	passenger.setPosition(this.posX, this.posY + this.getMountedYOffset() - 0.2, this.posZ + 0.3F);
            }
            else
            {
            	passenger.setPosition(this.posX + vec3d.x, this.posY + this.getMountedYOffset() + 0.4F, this.posZ + vec3d.z);
            }
            
        }
    }
    
    protected void applyYawToEntity(Entity entityToUpdate)
    {
        entityToUpdate.setRenderYawOffset(this.rotationYaw);
        float f = MathHelper.wrapDegrees(entityToUpdate.rotationYaw - this.rotationYaw);
        float f1 = MathHelper.clamp(f, -105.0F, 105.0F);
        entityToUpdate.prevRotationYaw += f1 - f;
        entityToUpdate.rotationYaw += f1 - f;
        entityToUpdate.setRotationYawHead(entityToUpdate.rotationYaw);
    }
    
    @SideOnly(Side.CLIENT)
    public void updateInputs(boolean p_184442_1_, boolean p_184442_2_, boolean p_184442_3_, boolean p_184442_4_)
    {
        this.leftInputDown = p_184442_1_;
        this.rightInputDown = p_184442_2_;
        this.forwardInputDown = p_184442_3_;
        this.backInputDown = p_184442_4_;
    }
    
    private void controlBoat()
    {
        if (this.isBeingRidden())
        {
            float f = 0.0F;

            if (this.leftInputDown)
            {
                this.deltaRotation += -1.0F;
            }

            if (this.rightInputDown)
            {
                ++this.deltaRotation;
            }

            if (this.rightInputDown != this.leftInputDown && !this.forwardInputDown && !this.backInputDown)
            {
                f += 0.005F;
            }

            this.rotationYaw += this.deltaRotation;

            if (this.forwardInputDown)
            {
                f += 0.04F;
            }

            if (this.backInputDown)
            {
                f -= 0.005F;
            }

            this.motionX += (double)(MathHelper.sin(-this.rotationYaw * 0.017453292F) * f);
            this.motionZ += (double)(MathHelper.cos(this.rotationYaw * 0.017453292F) * f);
        }
    }
    
    @Override
    public void onUpdate() {
    	this.updateMotion();

        if (this.world.isRemote)
        {
            this.controlBoat();
            this.world.sendPacketToServer(new CPacketSteerBoat(this.getPaddleState(0), this.getPaddleState(1)));
        }
    	super.onUpdate();
    }
    
    private void updateMotion()
    {
        double d0 = -0.03999999910593033D;
        double d1 = this.hasNoGravity() ? 0.0D : -0.03999999910593033D;
        double d2 = 0.0D;
        this.momentum = 0.05F;

        if (this.status == EntityBoat.Status.ON_LAND)
        {
            this.momentum = this.boatGlide;

            if (this.getControllingPassenger() instanceof EntityPlayer)
            {
                this.boatGlide /= 2.0F;
            }
        }

            this.motionX *= (double)this.momentum;
            this.motionZ *= (double)this.momentum;
            this.deltaRotation *= this.momentum;
            this.motionY += d1;

            if (d2 > 0.0D)
            {
                double d3 = 0.65D;
                this.motionY += d2 * 0.06153846016296973D;
                double d4 = 0.75D;
                this.motionY *= 0.75D;
            }
        }
    }

	public enum Status
	{
    IN_WATER,
    UNDER_WATER,
    UNDER_FLOWING_WATER,
    ON_LAND,
    IN_AIR;
	}
	
	public boolean canBeSteered()
    {
		return this.getControllingPassenger() instanceof EntityLivingBase;
    }
	
	/*public void travel(float strafe, float vertical, float forward)
    {
		if (this.isBeingRidden() && this.canBeSteered())
        {
            EntityLivingBase entitylivingbase = (EntityLivingBase)this.getControllingPassenger();
            
            strafe = entitylivingbase.moveStrafing * 0.5F;
            forward = entitylivingbase.moveForward;
            this.rotationYaw = entitylivingbase.rotationYaw;
            
            if (forward <= 0.0F)
            {
                forward *= 0.25F;
                this.setMoving(true);
            }
            else
            {
            	this.setMoving(false);
            }
            
            if (this.canPassengerSteer())
            {
            	
                this.setAIMoveSpeed((float)this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue());
                super.travel(0, vertical, forward);
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
    }*/
    
}
