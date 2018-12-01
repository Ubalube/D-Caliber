package com.ubalube.scifiaddon.entity;

import io.netty.buffer.ByteBuf;
import net.minecraft.block.Block;
import net.minecraft.block.BlockGlass;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityThrowable;
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
            if(result.entityHit == this.thrower); 
            result.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, getThrower()), damage);
            world.spawnParticle(EnumParticleTypes.REDSTONE, (double) posX, (double) posY, (double) posZ, 0.0D, 0.0D, 0.0D);
            return;
        }
        
        if(result.typeOfHit == Type.BLOCK)
        {
        	
        }
        

        if (!this.world.isRemote)
            this.setDead();
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
