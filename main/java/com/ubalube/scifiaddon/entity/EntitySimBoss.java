package com.ubalube.scifiaddon.entity;

import javax.annotation.Nullable;

import com.ubalube.scifiaddon.init.ModItems;

import net.minecraft.client.model.ModelEnderman;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.entity.RenderEnderman;
import net.minecraft.client.renderer.entity.RenderZombie;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIAttackRanged;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIFollow;
import net.minecraft.entity.ai.EntityAIFollowOwner;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.monster.AbstractSkeleton;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityElderGuardian;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySnowman;
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
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.BossInfo;
import net.minecraft.world.BossInfo.Color;
import net.minecraft.world.BossInfoServer;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntitySimBoss extends EntityMob implements IRangedAttackMob
{
	
	private final BossInfoServer bossInfo = (BossInfoServer)(new BossInfoServer(this.getDisplayName(), BossInfo.Color.BLUE, BossInfo.Overlay.NOTCHED_6)).setDarkenSky(false);
	private static final DataParameter<Integer> DAMAGE_STAGE = EntityDataManager.<Integer>createKey(EntitySimBoss.class, DataSerializers.VARINT);
	private static final DataParameter<Boolean> FINAL_PHASE = EntityDataManager.<Boolean>createKey(EntitySimBoss.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> REGENERATING = EntityDataManager.<Boolean>createKey(EntitySimBoss.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Integer> LAST_GLITCH = EntityDataManager.<Integer>createKey(EntitySimBoss.class, DataSerializers.VARINT);
	
	public EntitySimBoss(World worldIn) 
	{
		super(worldIn);
		
		this.setSize(2F, 2F);
		this.setNoGravity(true);
	}
	
	@Override
	public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, IEntityLivingData livingdata) {
		this.setDamageStage(0);
		this.setFinalPhase(false);
		this.setRegenerating(false);
		this.setLastGlitch(0);
		return super.onInitialSpawn(difficulty, livingdata);
	}
	
	public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);
        compound.setInteger("DamageStage", this.getDamageStage());
        compound.setBoolean("FinalPhase", this.isOnFinalPhase());
        compound.setBoolean("Regenerating", this.isRegenerating());
        compound.setInteger("lastGlitch", this.getLastGlitch());
    }

    
    public void readEntityFromNBT(NBTTagCompound compound)
    {
        super.readEntityFromNBT(compound);
        this.setDamageStage(compound.getInteger("DamageStage"));
        this.setFinalPhase(compound.getBoolean("FinalPhase"));
        this.setRegenerating(compound.getBoolean("Regenerating"));
        this.setLastGlitch(compound.getInteger("lastGlitch"));
    }
	
	public int getDamageStage()
    {
        return MathHelper.clamp(((Integer)this.dataManager.get(DAMAGE_STAGE)).intValue(), 0, 3);
    }
	
	@Override
	protected void entityInit() {
		super.entityInit();
        this.dataManager.register(DAMAGE_STAGE, Integer.valueOf(0));
        this.dataManager.register(FINAL_PHASE, false);
        this.dataManager.register(REGENERATING, false);
        this.dataManager.register(LAST_GLITCH, Integer.valueOf(0));
	}
	
	@Override
	public void onUpdate() {
		super.onUpdate();
		
		if(this.getLastGlitch() < this.ticksExisted - 75)
		{
			float x = MathHelper.getInt(this.rand, this.getPosition().getX() - 5, this.getPosition().getX() + 5);
			float z = MathHelper.getInt(this.rand, this.getPosition().getZ() - 5, this.getPosition().getZ() + 5);
			BlockPos pos = new BlockPos(x, this.getPosition().getY(), z);
			
			EntityGlitch glitch = new EntityGlitch(getEntityWorld());
			glitch.setPosition(pos.getX(), pos.getY(), pos.getZ());
			this.getEntityWorld().spawnEntity(glitch);
			this.setLastGlitch(this.ticksExisted);
		}
		
		if(this.isRegenerating())
		{
			if(this.getHealth() < this.getMaxHealth() / 2)
			{
				this.setHealth(this.getHealth() + 0.5f);
			}
			else
			{
				this.setRegenerating(false);
				this.setEntityInvulnerable(false);
			}
		}
		
		if(getHealth() <= 20 && !this.isOnFinalPhase())
		{
			this.world.createExplosion(this, this.posX, this.posY, this.posZ, 4.0F, true);
			this.setFinalPhase(true);
			this.setRegenerating(true);
			this.setEntityInvulnerable(true);
		}
		
		if(getHealth() <= getMaxHealth() / 3 && this.getDamageStage() != 2)
		{
			this.setDamageStage(2);
			this.bossInfo.setColor(Color.RED);
		}
		else
		{
			if(getHealth() <= getMaxHealth() / 2 && this.getDamageStage() != 1 && this.getDamageStage() != 2)
			{
				this.setDamageStage(1);
				this.bossInfo.setColor(Color.PURPLE);
			}
		}
	}

    public void setDamageStage(int variant)
    {
        this.dataManager.set(this.DAMAGE_STAGE, Integer.valueOf(variant));
    }
    
    public void setFinalPhase(boolean isFinal)
    {
    	this.dataManager.set(FINAL_PHASE, isFinal);
    }
    
    public boolean isOnFinalPhase()
    {
    	return this.dataManager.get(FINAL_PHASE);
    }
    
    public void setRegenerating(boolean regenerating)
    {
    	this.dataManager.set(REGENERATING, regenerating);
    }
	
    public boolean isRegenerating()
    {
    	return this.dataManager.get(REGENERATING);
    }
    
    public void setLastGlitch(int tick)
    {
    	this.dataManager.set(LAST_GLITCH, tick);
    }
	
    public int getLastGlitch()
    {
    	return this.dataManager.get(LAST_GLITCH);
    }
    
    @Override
    public boolean canBePushed() {
    	return false;
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
    
    protected void dropFewItems(boolean wasRecentlyHit, int lootingModifier)
    {
        EntityItem entityitem = this.dropItem(ModItems.SIMSAVOR, 1);

        if (entityitem != null)
        {
            entityitem.setNoDespawn();
        }
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
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(5300.0D);
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
        this.tasks.addTask(1, new EntityAIAttackRanged(this, 1.25D, 40, 10.0F));
		this.tasks.addTask(3, new EntityAIWanderAvoidWater(this, this.getAIMoveSpeed()));
        this.tasks.addTask(0, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		this.targetTasks.addTask(0, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
	}

	@Override
	public void attackEntityWithRangedAttack(EntityLivingBase target, float distanceFactor) {
		
		World w = getEntityWorld();
    	EntityBullet entity = new EntityBullet(w, this, 1);
		entity.setGunDamage((double)6.0);
		entity.setRange(500);
		entity.shoot(this, this.rotationPitch, this.getRotationYawHead(), 1.0F, 2.0F, 0.0F);
		entity.rotationYaw = this.rotationYawHead;
		entity.setGlowing(true);
		entity.setPotionEffect(MobEffects.SLOWNESS);
		w.spawnEntity(entity);
	}

	@Override
	public void setSwingingArms(boolean swingingArms) {
		// TODO Auto-generated method stub
		
	}
	
}
