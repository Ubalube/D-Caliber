package com.ubalube.scifiaddon.items;

import java.util.List;

import javax.annotation.Nullable;

import org.lwjgl.input.Keyboard;

import com.ubalube.scifiaddon.main;
import com.ubalube.scifiaddon.bounties.BountyProvider;
import com.ubalube.scifiaddon.init.ModItems;
import com.ubalube.scifiaddon.squads.SquadProvider;
import com.ubalube.scifiaddon.util.IHasModel;
import com.ubalube.scifiaddon.util.packets.IBounty;
import com.ubalube.scifiaddon.util.packets.ISquad;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemEnchantedBook;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemBounty extends Item implements IHasModel
{
	public ItemBounty(String name, int StackSize, CreativeTabs tab) 
	{
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(tab);
		setMaxStackSize(StackSize);
		
		this.addPropertyOverride(new ResourceLocation("isdone"), new IItemPropertyGetter()
        {
			@SideOnly(Side.CLIENT)
	        public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn)
	        {
	            if (entityIn == null) {
	                return 0.0F;
	            }
	            NBTTagCompound nbt = checkNBTTags(stack);
	            float j = nbt.getBoolean("DONE") ? 1.0F : 0.0F;
	            return j; 
	        }
        });
		
		ModItems.ITEMS.add(this);
	}
	
	@Override
	public boolean hasEffect(ItemStack stack) {
		NBTTagCompound nbt = checkNBTTags(stack);
		return nbt.getBoolean("DONE");
	}
	
	public static NBTTagCompound checkNBTTags(ItemStack stack) {
        NBTTagCompound nbt = stack.getTagCompound();
        if (nbt == null) {
            nbt = new NBTTagCompound();
            stack.setTagCompound(nbt);
        }
        if (!nbt.hasKey("DONE")) {
            nbt.setBoolean("DONE", false);
        }
        
        return nbt;
    }
	
	@Override
	public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
		return false;
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) 
	{
		NBTTagCompound nbt = stack.getTagCompound();
        if (nbt == null) {
            nbt = new NBTTagCompound();
            stack.setTagCompound(nbt);
        }
		boolean isDone = nbt.getBoolean("DONE");
		if(isDone)
		{
			tooltip.add("DONE");
		}
		else
		{
			tooltip.add("NOT DONE");
		}
		
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		
		ItemStack stack = playerIn.getHeldItem(handIn);
		
		NBTTagCompound nbt = stack.getTagCompound();
        if (nbt == null) {
            nbt = new NBTTagCompound();
            stack.setTagCompound(nbt);
        }
        IBounty bounty = playerIn.getCapability(BountyProvider.BOUNTY, null);
        
        if(bounty.activeBounty() == true)
        {
        	if(bounty.bountyCompleted() == true)
        	{
        		nbt.setBoolean("DONE", true);
        		nbt.setBoolean("ACTIVE", false);
        		bounty.bountyState(true);
        	}
        	
        	nbt.setBoolean("ACTIVE", true);
        	bounty.setActive(true);
        }
        else
        {
        	nbt.setBoolean("ACTIVE", false);
        	bounty.setActive(false);
        }
        
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}
	
	@Override
	public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack) {
		NBTTagCompound nbt = stack.getTagCompound();
        if (nbt == null) {
            nbt = new NBTTagCompound();
            stack.setTagCompound(nbt);
        }
        
        EntityPlayer playerIn = (EntityPlayer) entityLiving;
        
        IBounty bounty = playerIn.getCapability(BountyProvider.BOUNTY, null);
        
        if(bounty.activeBounty() != true && bounty.bountyCompleted() != true)
        {
        	bounty.setActive(true);
        }
        
		return super.onEntitySwing(entityLiving, stack);
	}
	
	@Override
	public void registerModels() 
	{
		main.proxy.registerItemRender(this, 0, "inventory");
		
	}
	
}
