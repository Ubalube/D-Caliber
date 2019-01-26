package com.ubalube.scifiaddon.util.handlers;

import com.ubalube.scifiaddon.entity.EntityBandit;
import com.ubalube.scifiaddon.entity.EntityBullet;
import com.ubalube.scifiaddon.entity.EntityImpact;
import com.ubalube.scifiaddon.entity.EntityMilita;
import com.ubalube.scifiaddon.entity.EntityGhost;
import com.ubalube.scifiaddon.entity.EntityGoliath;
import com.ubalube.scifiaddon.entity.EntityFrag;
import com.ubalube.scifiaddon.entity.EntitySoldier;
import com.ubalube.scifiaddon.entity.EntityTrader;
import com.ubalube.scifiaddon.entity.render.RenderBandit;
import com.ubalube.scifiaddon.entity.render.RenderBullet;
import com.ubalube.scifiaddon.entity.render.RenderImpact;
import com.ubalube.scifiaddon.entity.render.RenderMilita;
import com.ubalube.scifiaddon.entity.render.RenderGhost;
import com.ubalube.scifiaddon.entity.render.RenderFrag;
import com.ubalube.scifiaddon.entity.render.RenderSoldier;
import com.ubalube.scifiaddon.entity.render.RenderTank;
import com.ubalube.scifiaddon.entity.render.RenderTrader;
import com.ubalube.scifiaddon.vehicles.VehicleHumvee;
import com.ubalube.scifiaddon.vehicles.render.RenderHumvee;

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
		
		/*
		 * 
		 * ENTITIES
		 * 
		 */
		
		RenderingRegistry.registerEntityRenderingHandler(EntitySoldier.class, new IRenderFactory<EntitySoldier>()
		{
			@Override
			public Render<? super EntitySoldier> createRenderFor(RenderManager manager) 
			{
				return new RenderSoldier(manager);
			}
		});
		
		RenderingRegistry.registerEntityRenderingHandler(EntityBandit.class, new IRenderFactory<EntityBandit>()
		{
			@Override
			public Render<? super EntityBandit> createRenderFor(RenderManager manager) 
			{
				return new RenderBandit(manager);
			}
		});
		
		RenderingRegistry.registerEntityRenderingHandler(EntityGhost.class, new IRenderFactory<EntityGhost>()
		{
			@Override
			public Render<? super EntityGhost> createRenderFor(RenderManager manager) 
			{
				return new RenderGhost(manager);
			}
		});
		
		RenderingRegistry.registerEntityRenderingHandler(EntityMilita.class, new IRenderFactory<EntityMilita>()
		{
			@Override
			public Render<? super EntityMilita> createRenderFor(RenderManager manager) 
			{
				return new RenderMilita(manager);
			}
		});
		
		/*RenderingRegistry.registerEntityRenderingHandler(EntityTrader.class, new IRenderFactory<EntityTrader>()
		{
			@Override
			public Render<? super EntityTrader> createRenderFor(RenderManager manager) 
			{
				return new RenderTrader(manager);
			}
		});*/
		
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
		
		RenderingRegistry.registerEntityRenderingHandler(EntityImpact.class, new IRenderFactory<EntityImpact>()
		{
			@Override
			public Render<? super EntityImpact> createRenderFor(RenderManager manager) 
			{
				return new RenderImpact(manager);
			}
		});
		
		RenderingRegistry.registerEntityRenderingHandler(EntityFrag.class, new IRenderFactory<EntityFrag>()
		{
			@Override
			public Render<? super EntityFrag> createRenderFor(RenderManager manager) 
			{
				return new RenderFrag(manager);
			}
		});
		
		/*
		 * 
		 * VEHICLES
		 * 
		 */
		
		RenderingRegistry.registerEntityRenderingHandler(VehicleHumvee.class, new IRenderFactory<VehicleHumvee>()
		{
			@Override
			public Render<? super VehicleHumvee> createRenderFor(RenderManager manager) 
			{
				return new RenderHumvee(manager);
			}
		});
		
	}
}
