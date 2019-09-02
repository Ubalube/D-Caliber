package com.ubalube.scifiaddon.items;

import java.util.List;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemModification extends ItemBase
{

	private GunAttachments type;
	
	public ItemModification(String name, int StackSize, CreativeTabs tab) {
		super(name, StackSize, tab);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		if(this.type == GunAttachments.INCREASEDAMAGE)
		{
			tooltip.add("Upgraded Caliber");
		}
		else
		{
			if(this.type == GunAttachments.LOWRECOIL)
			{
				tooltip.add("Upgraded Caliber");
			}
			
		}
	}

}
