package com.ubalube.scifiaddon.util;

import java.util.Random;

import com.ubalube.scifiaddon.entity.EntityGhost;
import com.ubalube.scifiaddon.entity.EntitySoldier;
import com.ubalube.scifiaddon.init.ModItems;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityElderGuardian;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CamoDropEvent 
{
	Random rand = new Random();
    @SubscribeEvent
    public void onMobDrops(LivingDropsEvent event)
    {
        if (event.getEntity() instanceof EntityZombie || event.getEntity() instanceof EntitySoldier || event.getEntity() instanceof EntityGhost
        		|| event.getEntity() instanceof EntitySkeleton)
        {
        	World world = event.getEntity().getEntityWorld();
            
        	int dropCamo = rand.nextInt(3);
            int camo = rand.nextInt(6);
            
            Item dropItem = null;
            
            switch(camo)
            {
            case 0:
            	dropItem = ModItems.DEFAULT_PAINT;
            	break;
            	
            case 1:
            	dropItem = ModItems.DESERT_PAINT;
            	break;
            	
            case 2:
            	dropItem = ModItems.FOREST_PAINT;
            	break;
            	
            case 3:
            	dropItem = ModItems.FADE_PAINT;
            	break;
            	
            case 4:
            	dropItem = ModItems.LIGHTNING_PAINT;
            	break;
            	
            case 5:
            	dropItem = ModItems.REDSTONE_PAINT;
            	break;
            	
            case 6:
            	dropItem = ModItems.VOLCANIC_PAINT;
            	break;
            	
            }
            
            if(dropCamo == 2)
            {
            	ItemStack stack = new ItemStack(dropItem);
                EntityItem drop = new EntityItem(event.getEntity().world, event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ, stack);
                event.getDrops().add(drop);
            }
            
            
    }}
}
