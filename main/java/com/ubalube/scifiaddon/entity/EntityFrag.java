package com.ubalube.scifiaddon.entity;

import java.util.Random;

import com.ubalube.scifiaddon.init.EntityInit;
import com.ubalube.scifiaddon.init.ModBlocks;
import com.ubalube.scifiaddon.util.Reference;

import io.netty.buffer.ByteBuf;
import net.minecraft.block.Block;
import net.minecraft.block.BlockGlass;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.model.ModelShulkerBullet;
import net.minecraft.client.renderer.entity.RenderShulkerBullet;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemLingeringPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.RayTraceResult.Type;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;

public class EntityFrag extends EntityThrowable
{
	
	int x;
    int y;
    int z;
    
    float gravity;
	
    public EntityFrag(World worldIn)
    {
        super(worldIn);
        this.setSize(0.25F, 0.25F);
        this.height=0.5f;
    }

    public EntityFrag(World worldIn, EntityLivingBase throwerIn)
    {
        super(worldIn, throwerIn);
        
        this.thrower = throwerIn;
    }
    
    @Override
    public boolean canBeCollidedWith() {
    	return true;
    }
    
    @Override
    protected void doBlockCollisions() {
    	// TODO Auto-generated method stub
    	super.doBlockCollisions();
    }
    
    

    @Override
    public void onEntityUpdate()
    {
        double speed = new Vec3d(posX, posY, posZ).distanceTo(new Vec3d(prevPosX, prevPosY, prevPosZ));
        
        this.x = (int)this.posX;
        this.y = (int)this.posY;
        this.z = (int)this.posZ;
        
        if (!this.world.isRemote && (ticksExisted > 100 || speed < 0.01))
        {
        	this.setDead();
			this.world.createExplosion(this, x, y, z, 5.0F, true);
        }
        
        this.pushOutOfBlocks(this.posX, (this.getEntityBoundingBox().minY + this.getEntityBoundingBox().maxY) / 2.0D, this.posZ);
        
        super.onEntityUpdate();
    }
    
    @Override
    public void setEntityBoundingBox(AxisAlignedBB bb) 
    {
    	// TODO Auto-generated method stub
    	super.setEntityBoundingBox(bb);
    }
    
    @Override
    protected float getGravityVelocity() {
    	return gravity;
    }
    
    public void setGravity(float g)
    {
    	this.gravity = g;
    }

    @Override
    public boolean isInWater()
    {
        return false;
    }

	@Override
	protected void onImpact(RayTraceResult result) 
	{
		
		if(result.typeOfHit == Type.BLOCK)
		{
			
			BlockPos pos = result.getBlockPos();
			
			EnumFacing face = result.sideHit;
			
			if(face == EnumFacing.UP)
			{
				double rand = Math.random() * 0.35D;
				if(rand <= 0)
				{
					rand = 1;
				}
				this.motionY += rand;
			}
			
		}
		
	}
	
}
