package com.ubalube.scifiaddon.particles;

import com.ubalube.scifiaddon.particles.ParticleBlood;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.Particle;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public enum MainParticles 
{
	BLOOD(new ParticleBlood.Factory());
	
	private final IParticleFactory factory;
	
	MainParticles(IParticleFactory factory)
	{
		this.factory = factory;
	}
	
	@SideOnly(Side.CLIENT)
	public void spawn(World world, double x, double y, double z, double speedX, double speedY, double speedZ)
	{
		Particle particle = this.factory.createParticle(-1, world, x, y, z, speedX, speedY, speedZ);
		Minecraft.getMinecraft().effectRenderer.addEffect(particle);
	}
}
