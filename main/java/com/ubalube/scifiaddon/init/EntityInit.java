package com.ubalube.scifiaddon.init;

import org.apache.http.util.EntityUtils;

import com.ubalube.scifiaddon.main;
import com.ubalube.scifiaddon.entity.EntityBandit;
import com.ubalube.scifiaddon.entity.EntityBullet;
import com.ubalube.scifiaddon.entity.EntityImpact;
import com.ubalube.scifiaddon.entity.EntityGhost;
import com.ubalube.scifiaddon.entity.EntityGoliath;
import com.ubalube.scifiaddon.entity.EntityFrag;
import com.ubalube.scifiaddon.entity.EntitySoldier;
import com.ubalube.scifiaddon.entity.EntityTrader;
import com.ubalube.scifiaddon.util.Reference;

import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class EntityInit 
{
	
	public static void registerEntities() 
	{
		registerEntity("soldier", EntitySoldier.class, Reference.ENTITY_SOLDIER, 50, 7326320, 17920);
		registerEntity("ghost", EntityGhost.class, Reference.ENTITY_GHOST, 50, 7326320, 17920);
		registerEntity("bandit", EntityBandit.class, Reference.ENTITY_BANDIT, 50, 7326320, 17920);
		registerEntity("goliath", EntityGoliath.class, Reference.ENTITY_GOLIATH, 50, 000000, 10289152);
		registerEntity("trader", EntityTrader.class, Reference.ENTITY_TRADER, 50, 000000, 10289152);
	}
	
	private static void registerEntity(String name, Class<? extends Entity> entity, int id, int range, int color1, int color2)
	{
		EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID + ":" + name), entity, name, id, main.instance, range, 1, true, color1, color2);
	}
	
	public static void registerProjectile() 
	{
		registerProjectileType("gunBullet", EntityBullet.class , Reference.ENTITY_BULLET, 50);
		registerProjectileType("frag", EntityImpact.class , Reference.ENTITY_FRAG, 50);
		registerProjectileType("grenade", EntityFrag.class , Reference.ENTITY_FRAG + 1, 50);
	}
	
	private static void registerProjectileType(String entityName, Class<? extends Entity> entityClass, int id, int range) 
	{
		EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID + ":" + entityName), entityClass, entityName, id, main.instance, range, 1, true);
	}
	
}
