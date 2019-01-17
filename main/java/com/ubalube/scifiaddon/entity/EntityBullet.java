package com.ubalube.scifiaddon.entity;

import java.util.Random;

import com.ubalube.scifiaddon.init.EntityInit;
import com.ubalube.scifiaddon.init.ModBlocks;
import com.ubalube.scifiaddon.particles.MainParticles;
import com.ubalube.scifiaddon.particles.ParticleBlood;
import com.ubalube.scifiaddon.util.Reference;
import com.ubalube.scifiaddon.util.handlers.SoundHandler;

import io.netty.buffer.ByteBuf;
import net.minecraft.block.Block;
import net.minecraft.block.BlockGlass;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.Sound;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.RayTraceResult.Type;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import scala.collection.concurrent.Debug;

public class EntityBullet extends EntityArrow
{
    float damage;
    int range;
    
    public EntityBullet(World a) {
		super(a);
	}

	public EntityBullet(World worldIn, double x, double y, double z) {
		super(worldIn, x, y, z);
	}

	public EntityBullet(World worldIn, EntityLivingBase shooter) {
		super(worldIn, shooter);
	}
	
	

	@Override
	public void onUpdate() {
		super.onUpdate();
		int x = MathHelper.floor(this.getEntityBoundingBox().minX);
		int y = MathHelper.floor(this.getEntityBoundingBox().minY);
		int z = MathHelper.floor(this.getEntityBoundingBox().minZ);
		World world = this.world;
		Entity entity = (Entity) shootingEntity;
		if (this.inGround) {
			this.world.removeEntity(this);
		}
	}
	
	public void setRange(int range) 
	{
		this.range = range;
	}
	
	public int getRange()
	{
		return this.range;
	}
	
	@Override
	public boolean hasNoGravity() {
		return true;
	}
	
	@Override
	protected void onHit(RayTraceResult raytraceResultIn) 
	{
		Entity e = raytraceResultIn.entityHit;
		
		if(e != null)
		{
			
			this.setDead();
			
			if(e instanceof EntityGoliath)
            {
            	world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, (double) posX, (double) posY, (double) posZ, 0.0D, 0.0D, 0.0D);
            }
            else
            {
            	Vec3d pos = e.getPositionVector();
            	double d0 = (double)((float)pos.x + rand.nextFloat());
                double d1 = (double)((float)pos.y + rand.nextFloat());
                double d2 = (double)((float)pos.z + rand.nextFloat());
                double d3 = ((double)rand.nextFloat() - 0.5D) * 0.5D;
                double d4 = ((double)rand.nextFloat() - 0.5D) * 0.5D;
                double d5 = ((double)rand.nextFloat() - 0.5D) * 0.5D;
            	//MainParticles.BLOOD.spawn(e.world, d0, d1, d2, d3, d4, d5);
            }
			
		}
		
		this.playSound(SoundEvents.ENTITY_BLAZE_HURT, 1.0F, 1.2F / (this.rand.nextFloat() * 0.2F + 0.9F));
		super.onHit(raytraceResultIn);
	}
	
	@Override
    public void onEntityUpdate()
    {
        double speed = new Vec3d(posX, posY, posZ).distanceTo(new Vec3d(prevPosX, prevPosY, prevPosZ));
        if (!this.world.isRemote && (ticksExisted > this.getRange() || speed < 0.01))
        {
            this.setDead();
        }
        super.onEntityUpdate();
    }

	@Override
	protected ItemStack getArrowStack() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
