package com.ubalube.scifiaddon.entity;

import java.util.Random;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.EntityAIZombieAttack;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntityGlitch extends EntityZombie
{

	public EntityGlitch(World worldIn) {
		super(worldIn);
		
		this.experienceValue = 0;
	}
	
	@Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(7.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.23000000417232513D);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(48.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(5.0);
    }
	
	@Override
	protected boolean shouldBurnInDay() {
		return false;
	}
	
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundEvents.ENTITY_VEX_AMBIENT;
	}
	
	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return SoundEvents.ENTITY_GENERIC_HURT;
	}
	
	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.ENTITY_BLAZE_DEATH;
	}
	
	@Override
	public void onDeath(DamageSource cause) {
		Random rand = new Random();
		
		int randNum = rand.nextInt(100);
		
		boolean spawnAnotherGlitch = randNum > 30;
		
		EntityGlitch glitch = new EntityGlitch(getEntityWorld());
		getEntityWorld().spawnParticle(EnumParticleTypes.END_ROD, getPosition().getX(), getPosition().getY(), getPosition().getZ(), 1, 1, 1);
		getEntityWorld().playSound(posX, posY, posZ, SoundEvents.BLOCK_GLASS_BREAK, SoundCategory.MASTER, 1, 0, false);
		glitch.setLocationAndAngles(posX, posY, posZ, rotationYaw, rotationPitch);
		if(spawnAnotherGlitch) getEntityWorld().spawnEntity(glitch);
		
	}
	
}
