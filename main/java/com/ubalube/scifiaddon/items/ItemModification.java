package com.ubalube.scifiaddon.items;

import java.util.List;

import com.mojang.realmsclient.gui.ChatFormatting;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemModification extends ItemBase
{

	private GunAttachments type;
	private PotionEffect boundEffect;
	
	public ItemModification(String name, int StackSize, CreativeTabs tab, GunAttachments type) {
		super(name, StackSize, tab);
		this.type = type;
	}
	
	public void setPotionEffect(PotionEffect effect)
	{
		this.boundEffect = effect;
	}
	
	public PotionEffect getPotionEffect()
	{
		return this.boundEffect;
	}
	
	public GunAttachments getModification()
	{
		return this.type;
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		if(this.type == GunAttachments.POTIONEFFECT)
		{
			if(playerIn.getHeldItemOffhand().getItem() instanceof ItemPotion)
			{
				PotionEffect effect = null;
				List<PotionEffect> peffect = PotionUtils.getEffectsFromStack(playerIn.getHeldItemOffhand());
				
				for(int i = 0; i < playerIn.inventory.getSizeInventory(); i++)
				{
					ItemStack stackInSlot = playerIn.inventory.getStackInSlot(i);
					
					if(stackInSlot == playerIn.getHeldItemOffhand())
					{
						playerIn.inventory.removeStackFromSlot(i);
						break;
					}
					
				}
				
				effect = peffect.get(0);
				
				setPotionEffect(effect);
				
			}
		}
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		if(this.type == GunAttachments.INCREASEDAMAGE)
		{
			tooltip.add(ChatFormatting.GREEN + "Upgraded Caliber");
		}
		else
		{
			if(this.type == GunAttachments.LOWRECOIL)
			{
				tooltip.add(ChatFormatting.GREEN + "Recoil Control");
			}
			else
			{
				if(this.type == GunAttachments.POTIONEFFECT)
				{
					if(getPotionEffect() != null)
					{
						tooltip.add(ChatFormatting.GREEN + "Bullet Effect - [" + ChatFormatting.RED + this.boundEffect.getEffectName() + ChatFormatting.GREEN + "]");
					}
					else
					{
						tooltip.add(ChatFormatting.GREEN + "Bullet Effect - [" + "NONE" + ChatFormatting.GREEN + "]");
					}
					
				}
				else
				{
					if(this.type == GunAttachments.STATTRACK)
					{
						tooltip.add(ChatFormatting.GREEN + "StatTrack");
					}
				}
			}
			
		}
	}

}
