package com.ubalube.scifiaddon.entity;

import javax.annotation.Nullable;

import com.ubalube.scifiaddon.init.ModItems;

import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.entity.RenderZombie;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIFollow;
import net.minecraft.entity.ai.EntityAIFollowOwner;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.monster.AbstractSkeleton;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityElderGuardian;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityWitherSkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityAmbientCreature;
import net.minecraft.entity.passive.EntityRabbit;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.BossInfo;
import net.minecraft.world.BossInfoServer;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityGoliath extends EntityMob
{
	
	private final BossInfoServer bossInfo = (BossInfoServer)(new BossInfoServer(this.getDisplayName(), BossInfo.Color.RED, BossInfo.Overlay.NOTCHED_6)).setDarkenSky(true);
	
	public EntityGoliath(World worldIn) 
	{
		super(worldIn);
		
		this.setSize(5F, 4F);
	}
	
	@Override
	protected void updateAITasks() {
		this.bossInfo.setPercent(this.getHealth() / this.getMaxHealth());
		super.updateAITasks();
	}
	
	public void setCustomNameTag(String name)
    {
        super.setCustomNameTag(name);
        this.bossInfo.setName(this.getDisplayName());
    }
	
	public void addTrackingPlayer(EntityPlayerMP player)
    {
        super.addTrackingPlayer(player);
        this.bossInfo.addPlayer(player);
    }

    public void removeTrackingPlayer(EntityPlayerMP player)
    {
        super.removeTrackingPlayer(player);
        this.bossInfo.removePlayer(player);
    }
	
	@Override
	public void onDeath(DamageSource cause) 
	{
		boolean flag = net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.world, this);
		this.world.createExplosion(this, this.posX, this.posY, this.posZ, 5.0F, flag);
		super.onDeath(cause);
	}
    
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(6.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(1000.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).setBaseValue(5.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.10D);
        this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(1000.0D);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(48.0D);
    }
    
    @Override
    public boolean canBeHitWithPotion() {
    	return false;
    }
    
	@Override
	protected void initEntityAI() 
	{
		this.tasks.addTask(2, new EntityGoliath.AIShoot(this));
		this.tasks.addTask(3, new EntityAIWanderAvoidWater(this, this.getAIMoveSpeed()));
		this.tasks.addTask(3, new EntityGoliath.AIMoveTowards(this, this.getAIMoveSpeed(), 200, 200));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
	}
	
	static class AIMoveTowards extends EntityAIMoveTowardsTarget
	{
		private EntityLivingBase targetEntity;
		EntityCreature c;
		float MoveToDistance;
		private double movePosX;
	    private double movePosY;
	    private double movePosZ;
	    private final double speed;
		
		public AIMoveTowards(EntityCreature creature, double speedIn, float targetMaxDistance, float moveDistance) 
		{
			super(creature, speedIn, targetMaxDistance);
			c = creature;
			MoveToDistance = moveDistance;
			this.speed = speedIn;
		}
		
		@Override
		public boolean shouldExecute() 
		{
			
			this.targetEntity = this.c.getAttackTarget();
			
			if(this.targetEntity == null)
			{
				return false;
			}
			else
			{
				if(this.targetEntity.getDistance(c) < MoveToDistance)
				{
					return true;
				}
				else
				{
					if(this.targetEntity.getDistance(c) > MoveToDistance)
					{
						return false;
					}
				}
			}
			
			
			return super.shouldExecute();
		}
		
	    public boolean shouldContinueExecuting()
	    {
	        return !this.c.getNavigator().noPath() && this.targetEntity.isEntityAlive() && this.targetEntity.getDistanceSq(this.c) < (double)(this.MoveToDistance * this.MoveToDistance);
	    }

	    /**
	     * Reset the task's internal state. Called when this task is interrupted by another one
	     */
	    public void resetTask()
	    {
	        this.targetEntity = null;
	    }

	    /**
	     * Execute a one shot task or start executing a continuous task
	     */
	    public void startExecuting()
	    {
	        this.c.getNavigator().tryMoveToXYZ(this.movePosX, this.movePosY, this.movePosZ, this.speed);
	    }
		
	}
	
    static class AIShoot extends EntityAIBase
    {
        private final EntityGoliath e;
        private int attackStep;
        private int attackTime;

        public AIShoot(EntityGoliath en)
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
                        	EntitySmallFireball entitysmallfireball = new EntitySmallFireball(this.e.world, this.e, d1 + this.e.getRNG().nextGaussian() * (double)f, d2, d3 + this.e.getRNG().nextGaussian() * (double)f);
                            entitysmallfireball.posY = this.e.posY + (double)(this.e.height / 2.0F) + 0.5D;
                            this.e.world.spawnEntity(entitysmallfireball);
                        }
                    }
                }

                this.e.getLookHelper().setLookPositionWithEntity(entitylivingbase, 10.0F, 10.0F);
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
