package com.ubalube.scifiaddon.items;

import java.util.Random;

import javax.annotation.Nullable;

import com.ubalube.scifiaddon.main;
import com.ubalube.scifiaddon.entity.EntityDeployableShield;
import com.ubalube.scifiaddon.entity.EntitySimBoss;
import com.ubalube.scifiaddon.init.ModItems;
import com.ubalube.scifiaddon.util.IHasModel;
import com.ubalube.scifiaddon.util.handlers.SoundHandler;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.CooldownTracker;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.event.sound.SoundSetupEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemSimCore extends ItemBase
{

	public ItemSimCore(String name, int StackSize, CreativeTabs tab) {
		super(name, StackSize, tab);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		if(!worldIn.isRemote)
		{
			EntitySimBoss simBoss = new EntitySimBoss(worldIn);
			simBoss.setPosition(playerIn.posX, playerIn.posY, playerIn.posZ);
			worldIn.spawnEntity(simBoss);
		}
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}
	
}
