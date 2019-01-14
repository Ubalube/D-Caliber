package com.ubalube.scifiaddon.items;

import com.ubalube.scifiaddon.main;
import com.ubalube.scifiaddon.init.ModItems;
import com.ubalube.scifiaddon.util.IHasModel;

import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSword;
import scala.util.Random;

public class GunBase extends Item implements IHasModel
{
	
	public int Recoil;
	
	public GunBase(String name) 
	{
		setUnlocalizedName(name);
		setRegistryName(name);
		setMaxStackSize(1);
		
		ModItems.ITEMS.add(this);
	}
	
	/*
	 * -----------------------------
	 * |           RECOIL          |
	 * -----------------------------
	 */
	public void setRecoil(int amt)
	{
		this.Recoil = amt;
	}
	
	public int getRecoil()
	{
		return this.Recoil;
	}
	
	public void doRecoil(EntityPlayer p)
	{
		Minecraft mc = Minecraft.getMinecraft();
		mc.player.rotationPitch += -this.Recoil;
		Random rand = new Random();
		int yawWay = rand.nextInt(2);
		if(yawWay == 1)
		{
			mc.player.rotationYaw += this.Recoil + 1;
		}
		
		if(yawWay == 2)
		{
			mc.player.rotationYaw += -this.Recoil - 1;
		}
		
	}
	
	
	
	@Override
	public void registerModels() 
	{
		main.proxy.registerItemRender(this, 0, "inventory");
		
	}
	
}
