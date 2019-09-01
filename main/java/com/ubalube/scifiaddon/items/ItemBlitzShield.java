package com.ubalube.scifiaddon.items;

import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import org.lwjgl.input.Keyboard;

import com.mojang.realmsclient.gui.ChatFormatting;
import com.ubalube.scifiaddon.main;
import com.ubalube.scifiaddon.entity.model.shields.Blitzshield;
import com.ubalube.scifiaddon.entity.model.shields.ModelShield;
import com.ubalube.scifiaddon.util.IShield;

import akka.japi.Pair;
import net.minecraft.block.BlockDispenser;
import net.minecraft.block.SoundType;
import net.minecraft.client.audio.Sound;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemBlitzShield extends ItemBase implements IShield
{

	public ItemBlitzShield(String name, int maxDamage, CreativeTabs tabToDisplayOn)
    {
    	super(name, maxDamage, tabToDisplayOn);
        this.maxStackSize = 1;
        this.setMaxDamage(876);
        this.setCreativeTab(main.gunTab);
        this.addPropertyOverride(new ResourceLocation("blocking"), new IItemPropertyGetter()
        {
            @SideOnly(Side.CLIENT)
            public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn)
            {
                return entityIn != null && entityIn.isHandActive() && entityIn.getActiveItemStack() == stack ? 1.0F : 0.0F;
            }
        });
        BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(this, ItemArmor.DISPENSER_BEHAVIOR);
    }
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) 
	{
		tooltip.add(TextFormatting.YELLOW.ITALIC + "Just a little gift from Blitz");
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}

	public String getItemStackDisplayName(ItemStack stack)
    {
        return I18n.translateToLocal("item.blitz.name");
    }
    
    public EnumAction getItemUseAction(ItemStack stack)
    {
        return EnumAction.BLOCK;
    }

    public int getMaxItemUseDuration(ItemStack stack)
    {
        return 72000;
    }
    
    @Override
    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
    	return false;
    }
    
    @Override
    public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack) 
    {
    	
    	EntityPlayer playerIn = (EntityPlayer) entityLiving;
    	World worldIn = playerIn.world;
    	
    	Vec3d playerVision = playerIn.getLookVec();

		List<Entity> locatedEntities = playerIn.world.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(playerIn.getPosition().getX(), playerIn.getPosition().getY(), playerIn.getPosition().getZ(), playerIn.getPosition().getX() + 10, playerIn.getPosition().getY() + 10, playerIn.getPosition().getZ() + 10));
		
		for(Entity e : locatedEntities)
		{
			
			Entity eb = (Entity) e;
			
			if(eb instanceof EntityLivingBase)
			{
				if(eb != playerIn)
				{
					((EntityLivingBase) eb).addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 140, 2));
					((EntityLivingBase) eb).addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 80, 2));
				}
				
			}
			
		}
		playerIn.getCooldownTracker().setCooldown(this, 40);
		
		worldIn.playSound(playerIn, playerIn.getPosition(), SoundEvents.BLOCK_GLASS_BREAK, SoundCategory.PLAYERS, 1, 1);
    	return true;
    }

    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        playerIn.setActiveHand(handIn);
        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
    }

	@Override
	public ModelShield shieldModel() {
		return new Blitzshield();
	}
}
