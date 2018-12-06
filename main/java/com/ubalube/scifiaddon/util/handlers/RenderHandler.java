package com.ubalube.scifiaddon.util.handlers;

import com.ubalube.scifiaddon.entity.EntityBullet;
import com.ubalube.scifiaddon.entity.EntityGoliath;
import com.ubalube.scifiaddon.entity.EntitySoldier;
import com.ubalube.scifiaddon.entity.render.RenderBullet;
import com.ubalube.scifiaddon.entity.render.RenderSoldier;
import com.ubalube.scifiaddon.entity.render.RenderTank;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class RenderHandler 
{
	
	
	
	public static void registerEntityRenders()
	{
		
		RenderingRegistry.registerEntityRenderingHandler(EntitySoldier.class, new IRenderFactory<EntitySoldier>()
		{
			@Override
			public Render<? super EntitySoldier> createRenderFor(RenderManager manager) 
			{
				return new RenderSoldier(manager);
			}
		});
		
		RenderingRegistry.registerEntityRenderingHandler(EntityGoliath.class, new IRenderFactory<EntityGoliath>()
		{
			@Override
			public Render<? super EntityGoliath> createRenderFor(RenderManager manager) 
			{
				return new RenderTank(manager);
			}
		});
		
		RenderingRegistry.registerEntityRenderingHandler(EntityBullet.class, new IRenderFactory<EntityBullet>()
		{
			@Override
			public Render<? super EntityBullet> createRenderFor(RenderManager manager) 
			{
				return new RenderBullet(manager);
			}
		});
		
	}
}
