package com.ubalube.scifiaddon.entity;

import com.ubalube.scifiaddon.init.EntityInit;
import com.ubalube.scifiaddon.init.ModBlocks;
import com.ubalube.scifiaddon.util.Reference;

import io.netty.buffer.ByteBuf;
import net.minecraft.block.Block;
import net.minecraft.block.BlockGlass;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.RayTraceResult.Type;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;

public class EntityBullet extends EntityThrowable implements IEntityAdditionalSpawnData
{
    public float damage;
    public int range;

    public EntityBullet(World worldIn)
    {
        super(worldIn);
    }

    public EntityBullet(World worldIn, EntityLivingBase throwerIn, float damage, int r)
    {
        super(worldIn, throwerIn);
        this.damage = damage;
        this.range = r;
    }


    @Override
    public void onEntityUpdate()
    {
    	
        double speed = new Vec3d(posX, posY, posZ).distanceTo(new Vec3d(prevPosX, prevPosY, prevPosZ));
        if (!this.world.isRemote && (ticksExisted > range || speed < 0.01))
        {
            this.setDead();
        }
        
        if(this.ticksExisted >= ticksExisted / 2)
    	{
    		this.damage = this.damage / 2;
    	}
        super.onEntityUpdate();
    }
    
    int x = (int) this.posX;
    int y = (int) this.posY;
    int z = (int) this.posZ;
    @Override
    protected void onImpact(RayTraceResult result)
    {
        if (result == null || isDead)
        {
        	
        }
        if (result.typeOfHit == Type.ENTITY)
        {
        	this.setDead();
            if(result.entityHit == this.thrower); 
            result.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, getThrower()), damage);
            
            if(result.entityHit instanceof EntityGoliath)
            {
            	world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, (double) posX, (double) posY, (double) posZ, 0.0D, 0.0D, 0.0D);
            }
            else
            {
            	world.spawnParticle(EnumParticleTypes.REDSTONE, (double) posX, (double) posY, (double) posZ, 0.0D, 0.0D, 0.0D);
            }
            
            return;
        }
        
        if(result.typeOfHit == Type.BLOCK)
        {
        	World w = getEntityWorld();
        	BlockPos pos = result.getBlockPos();
        	
        	BlockPos hitLoc = result.getBlockPos();
        	if(hitLoc == null)
        	{
        		return;
        	}
        	
        	IBlockState hit = world.getBlockState(hitLoc);
        	
        	if(hit.getBlock() == Blocks.TALLGRASS || hit.getBlock() == Blocks.DOUBLE_PLANT || hit.getBlock() == Blocks.DEADBUSH 
        			|| hit.getBlock() == Blocks.BEETROOTS || hit.getBlock() == Blocks.WHEAT || hit.getBlock() == Blocks.CARROTS || hit.getBlock() == Blocks.POTATOES
        			|| hit.getBlock() == Blocks.MELON_STEM || hit.getBlock() == Blocks.PUMPKIN_STEM || hit.getBlock() == Blocks.LEAVES || hit.getBlock() == Blocks.REEDS)
        	{
        		//NOTHING
        	}
        	else
        	{
        		if(hit.getBlock() == Blocks.GLASS || hit.getBlock() == Blocks.GLASS_PANE || hit.getBlock() == Blocks.STAINED_GLASS || hit.getBlock() == Blocks.STAINED_GLASS_PANE
        				|| hit.getBlock() == Blocks.ICE || hit.getBlock() == Blocks.FROSTED_ICE || hit.getBlock() == Blocks.PACKED_ICE)
        		{
        			w.setBlockToAir(hitLoc);
        		}
        		else
        		{
        			this.setDead();
        		}
        	}
        	
        	
        }
        
    }

    @Override
    protected float getGravityVelocity()
    {
        return 0.00001F;
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);
        compound.setFloat("Damage", damage);
        compound.setFloat("Damage", damage);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound compound)
    {
        super.readEntityFromNBT(compound);
        this.damage = compound.getFloat("Damage");
    }

    @Override
    public void writeSpawnData(ByteBuf buffer)
    {
        NBTTagCompound nbt = new NBTTagCompound();
        this.writeEntityToNBT(nbt);
        ByteBufUtils.writeTag(buffer, nbt);
    }

    @Override
    public void readSpawnData(ByteBuf additionalData)
    {
        this.readEntityFromNBT(ByteBufUtils.readTag(additionalData));
    }

    @Override
    public boolean isInWater()
    {
        return false;
    }
}
