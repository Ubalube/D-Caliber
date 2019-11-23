package com.ubalube.scifiaddon.items;

import java.util.List;

import org.lwjgl.input.Keyboard;

import com.ubalube.scifiaddon.main;
import com.ubalube.scifiaddon.entity.EntityBullet;
import com.ubalube.scifiaddon.entity.EntityImpact;
import com.ubalube.scifiaddon.entity.EntityFrag;
import com.ubalube.scifiaddon.init.ModItems;
import com.ubalube.scifiaddon.util.IHasModel;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class CGrenade extends Item implements IHasModel
{
	
	public enum type
	{
		IMPACT,
		SMOKE,
		FRAG
	}
	
	public Enum typeOfGrenade;
	
	public CGrenade(String name, int StackSize, CreativeTabs tab, type t) 
	{
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(tab);
		setMaxStackSize(StackSize);
		
		this.typeOfGrenade = t;
		
		ModItems.ITEMS.add(this);
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) 
	{
		if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT))
		{
			if(typeOfGrenade == type.FRAG)
			{
				tooltip.add(TextFormatting.YELLOW + "Frag Grenade");
				tooltip.add(TextFormatting.YELLOW + "-----------------");
				tooltip.add(TextFormatting.RED + "Explodes after 5 seconds.");
			}
			
			if(typeOfGrenade == type.IMPACT)
			{
				tooltip.add(TextFormatting.YELLOW + "Impact Grenade");
				tooltip.add(TextFormatting.YELLOW + "-----------------");
				tooltip.add(TextFormatting.RED + "Explodes when it hits a block.");
			}
			
			
			if(typeOfGrenade == type.SMOKE)
			{
				tooltip.add(TextFormatting.YELLOW + "Smoke Grenade");
				tooltip.add(TextFormatting.YELLOW + "-----------------");
				tooltip.add(TextFormatting.RED + "Emits smoke once it hits a block.");
			}
			
		}
		else
		{
			tooltip.add(TextFormatting.YELLOW + "Press LSHIFT for stats!");
		}
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) 
	{
		if(!worldIn.isRemote)
		{
			
			if(this.typeOfGrenade == type.FRAG)
			{
				EntityFrag entity = new EntityFrag(worldIn, playerIn);
				entity.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 1.0F, 2.0F, 0.0F);
				entity.setGravity(0.1F);
				worldIn.spawnEntity(entity);
			}
			
			
			
			if(this.typeOfGrenade == type.IMPACT)
			{
				EntityImpact entity = new EntityImpact(worldIn, playerIn);
				entity.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 1.0F, 2.0F, 0.0F);
				entity.setGravity(0.1F);
				worldIn.spawnEntity(entity);
			}
			
		}
		playerIn.getCooldownTracker().setCooldown(this, 12);
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}
	
	@Override
	public void registerModels() 
	{
		main.proxy.registerItemRender(this, 0, "inventory");
		
	}
}


