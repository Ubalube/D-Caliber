package com.ubalube.scifiaddon.util;

import java.util.Random;

import com.ubalube.scifiaddon.entity.EntityBandit;
import com.ubalube.scifiaddon.entity.EntityGhost;
import com.ubalube.scifiaddon.entity.EntitySoldier;
import com.ubalube.scifiaddon.init.ModItems;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityElderGuardian;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class CamoDropEvent 
{
	Random rand = new Random();
    @SubscribeEvent
    public void onMobDrops(LivingDropsEvent event)
    {
        if (event.getEntity() instanceof EntityZombie || event.getEntity() instanceof EntitySoldier || event.getEntity() instanceof EntityGhost
        		|| event.getEntity() instanceof EntitySkeleton || event.getEntity() instanceof EntityBandit)
        {
        	World world = event.getEntity().getEntityWorld();
            
        	int dropCamo = rand.nextInt(3);
            int camo = rand.nextInt(5);
            
            Item dropItem = null;
            
            switch(camo)
            {
            case 0:
            	dropItem = ModItems.DEFAULT_PAINT;
            	break;
            	
            case 1:
            	dropItem = ModItems.ORANGE_PAINT;
            	break;
            	
            case 2:
            	dropItem = ModItems.BLUE_PAINT;
            	break;
            	
            case 3:
            	dropItem = ModItems.RED_PAINT;
            	break;
            	
            case 4:
            	dropItem = ModItems.GREEN_PAINT;
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
