package com.ubalube.scifiaddon.util.handlers;

import com.ubalube.scifiaddon.entity.EntityBullet;
import com.ubalube.scifiaddon.entity.render.RenderBullet;
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
