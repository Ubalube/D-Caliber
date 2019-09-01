package com.ubalube.scifiaddon.entity;

import java.util.Random;

import javax.annotation.Nullable;

import com.ubalube.scifiaddon.init.ModItems;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIOpenDoor;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

public class EntitySoldierBase extends EntityMob
{
	
	private static final DataParameter<Integer> VARIANT = EntityDataManager.<Integer>createKey(EntityBandit.class, DataSerializers.VARINT);
	
	public EntitySoldierBase(World worldIn) 
	{
		super(worldIn);
		this.setSize(0.6F, 1.95F);
		
	}
	
	@Override
    protected void setEquipmentBasedOnDifficulty(DifficultyInstance difficulty)
    {
    	int d = Math.round(difficulty.getClampedAdditionalDifficulty()*3f);
    	this.addGear(d);
    }
	
	protected void addGear(int difficulty){
		Random r = new Random();
		Item weapon = null;
		switch (r.nextInt(2)) {
		case 0:
			weapon = ModItems.TOMMYGUN;
			break;
		default:
			weapon = ModItems.GLOCK;
			break;
		}
		if (weapon != null) this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(weapon));
        
    }
    
    @Override
    protected boolean canDespawn() {
    	return false;
    } 
	
	protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(6.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.23000000417232513D);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(48.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0);
    }
    
	@Override
	protected void initEntityAI() 
	{
		this.tasks.addTask(4, new EntitySoldierBase.AIShoot(this));
		this.tasks.addTask(3, new EntityAIWander(this, this.getAIMoveSpeed()));
		this.tasks.addTask(2, new EntityAIOpenDoor(this, false));
		this.tasks.addTask(1, new EntityAISwimming(this));
		
	}
	
	
	@Nullable
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata)
    {
        this.setVariant(this.rand.nextInt(4));
        this.setEquipmentBasedOnDifficulty(difficulty);
        return super.onInitialSpawn(difficulty, livingdata);
    }
	
	public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);
        compound.setInteger("Variant", this.getVariant());
    }

    
    public void readEntityFromNBT(NBTTagCompound compound)
    {
        super.readEntityFromNBT(compound);
        this.setVariant(compound.getInteger("Variant"));
    }
	
	protected void entityInit()
    {
        super.entityInit();
        this.dataManager.register(VARIANT, Integer.valueOf(0));
    }
	
	public int getVariant()
    {
        return MathHelper.clamp(((Integer)this.dataManager.get(VARIANT)).intValue(), 0, 4);
    }

    public void setVariant(int variant)
    {
        this.dataManager.set(VARIANT, Integer.valueOf(variant));
    }
	
	static class AIShoot extends EntityAIBase
    {
        private final EntitySoldierBase e;
        private int attackStep;
        private int attackTime;

        public AIShoot(EntitySoldierBase en)
        {
            this.e = en;
            this.setMutexBits(3);
        }

        /**
         * Returns whether the EntityAIBase should begin execution.
         */
        public boolean shouldExecute()
        {
            EntityLivingBase entitylivingbase = this.e.getAttackTarget();
            return entitylivingbase != null && entitylivingbase.isEntityAlive();
        }

        /**
         * Execute a one shot task or start executing a continuous task
         */
        public void startExecuting()
        {
            this.attackStep = 0;
        }

        /**
         * Reset the task's internal state. Called when this task is interrupted by another one
         */
        public void resetTask()
        {
        }

        /**
         * Keep ticking a continuous task that has already been started
         */
        public void updateTask()
        {
            --this.attackTime;
            EntityLivingBase entitylivingbase = this.e.getAttackTarget();
            double d0 = this.e.getDistanceSq(entitylivingbase);

            if (d0 < 4.0D)
            {
                if (this.attackTime <= 0)
                {
                    this.attackTime = 20;
                    this.e.attackEntityAsMob(entitylivingbase);
                }

                this.e.getMoveHelper().setMoveTo(entitylivingbase.posX, entitylivingbase.posY, entitylivingbase.posZ, 1.0D);
            }
            else if (d0 < this.getFollowDistance() * this.getFollowDistance())
            {
                double d1 = entitylivingbase.posX - this.e.posX;
                double d2 = entitylivingbase.getEntityBoundingBox().minY + (double)(entitylivingbase.height / 2.0F) - (this.e.posY + (double)(this.e.height / 2.0F));
                double d3 = entitylivingbase.posZ - this.e.posZ;

                if (this.attackTime <= 0)
                {
                    ++this.attackStep;

                    if (this.attackStep == 1)
                    {
                        this.attackTime = 20;
                    }
                    else if (this.attackStep <= 4)
                    {
                        this.attackTime = 6;
                    }
                    else
                    {
                        this.attackTime = 20;
                        this.attackStep = 0;
                    }

                    if (this.attackStep > 1)
                    {
                        float f = MathHelper.sqrt(MathHelper.sqrt(d0)) * 0.5F;
                        this.e.world.playEvent((EntityPlayer)null, 1018, new BlockPos((int)this.e.posX, (int)this.e.posY, (int)this.e.posZ), 0);
                        

                        for (int i = 0; i < 1; ++i)
                        	
                        {
                        	World w = this.e.getEntityWorld();
                        	EntityBullet entity = new EntityBullet(w, this.e, 1);
    						entity.setGunDamage((double)6.0);
    						entity.setRange(500);
    						entity.shoot(this.e, e.rotationPitch, e.rotationYaw, 1.0F, 2.0F, 0.0F);
    						entity.rotationYaw = e.rotationYawHead;
    						w.spawnEntity(entity);
    						
                        }
                    }
                }

                this.e.faceEntity(entitylivingbase, 180.0f, 180.0f);
                this.e.getLookHelper().setLookPositionWithEntity(entitylivingbase, 30.0F, 55.0F);
            }
            else
            {
                this.e.getNavigator().clearPath();
                this.e.getMoveHelper().setMoveTo(entitylivingbase.posX, entitylivingbase.posY, entitylivingbase.posZ, 1.0D);
            }

            super.updateTask();
        }

        private double getFollowDistance()
        {
            IAttributeInstance iattributeinstance = this.e.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE);
            return iattributeinstance == null ? 16.0D : iattributeinstance.getAttributeValue();
        }
    }
	
}
