package com.ubalube.scifiaddon.items;

import java.util.Random;

import javax.annotation.Nullable;

import com.ubalube.scifiaddon.main;
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
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraftforge.client.event.sound.SoundSetupEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TraderCase extends Item implements IHasModel
{
	public TraderCase(String name, int StackSize, CreativeTabs tab) 
	{
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(tab);
		setMaxStackSize(StackSize);
		
		setMaxDamage(5);
		
		this.addPropertyOverride(new ResourceLocation("opened"), new IItemPropertyGetter()
        {
			@SideOnly(Side.CLIENT)
	        public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn)
	        {
	            if (entityIn == null) {
	                return 0.0F;
	            }
	            NBTTagCompound nbt = checkNBTTags(stack);
	            float j = nbt.getBoolean("OPEN") ? 1.0F : 0.0F;
	            return j; 
	        }
        });
		
		ModItems.ITEMS.add(this);
	}
	
	public static NBTTagCompound checkNBTTags(ItemStack stack) {
        NBTTagCompound nbt = stack.getTagCompound();
        if (nbt == null) {
            nbt = new NBTTagCompound();
            stack.setTagCompound(nbt);
        }
        if (!nbt.hasKey("OPEN")) {
            nbt.setBoolean("OPEN", false);
        }
        
        return nbt;
    }
	
	public ItemStack getRandomItem()
	{
		
		ItemStack stack = new ItemStack(ModItems.FAL);
		Random r = new Random();
		int result = r.nextInt(8);
		NBTTagCompound nbt = stack.getTagCompound();
        if(nbt == null)
        {
            nbt = new NBTTagCompound();
            stack.setTagCompound(nbt);
        }
		
		switch(result)
		{
		case 1:
			stack = new ItemStack(ModItems.FAL);
			nbt.setBoolean("reload", false);
			nbt.setBoolean("ADS", false);
			break;
		case 2:
			stack = new ItemStack(ModItems.UZI);
			nbt.setBoolean("reload", false);
			nbt.setBoolean("ADS", false);
			break;
		case 3:
			stack = new ItemStack(ModItems.SCAR);
			nbt.setBoolean("reload", false);
			nbt.setBoolean("ADS", false);
			break;
		case 4:
			stack = new ItemStack(ModItems.BLUE_PAINT);
			break;
		case 5:
			stack = new ItemStack(ModItems.RED_PAINT);
			break;
		case 6:
			stack = new ItemStack(ModItems.ORANGE_PAINT);
			break;
		case 7:
			stack = new ItemStack(ModItems.GREEN_PAINT);
			break;
		}
		
		return stack;
	}
	
	public void open(EntityPlayer playerIn, ItemStack stack)
	{
		
		NBTTagCompound nbt = stack.getTagCompound();
        if(nbt == null)
        {
            nbt = new NBTTagCompound();
            stack.setTagCompound(nbt);
        }
        
        CooldownTracker cd = playerIn.getCooldownTracker();
		
		if(this.getRandomItem().getItem() instanceof GunBase)
		{
			nbt.setBoolean("reload", false);
			nbt.setBoolean("ADS", false);
		}
		
		ItemStack stackS = this.getRandomItem();
				
		
		playerIn.addItemStackToInventory(stackS);
		cd.removeCooldown(stackS.getItem());
		
		World worldIn = playerIn.getEntityWorld();
		
		worldIn.playSound(playerIn,	playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.BLOCK_CHEST_OPEN, SoundCategory.MASTER, 1, 1);
		
		
        
        nbt.setBoolean("OPEN", true);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		
		ItemStack stack = playerIn.getHeldItem(handIn);
		
		NBTTagCompound nbt = stack.getTagCompound();
        if(nbt == null)
        {
            nbt = new NBTTagCompound();
            stack.setTagCompound(nbt);
        }
        
        if(nbt.getBoolean("OPEN") != true)
        {
        	if(stack.getItemDamage() == 5)
			{
				this.open(playerIn, stack);
				worldIn.playSound(playerIn,	playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ENTITY_ZOMBIE_ATTACK_DOOR_WOOD, SoundCategory.MASTER, 1, 1);
			}
			else
			{
				stack.damageItem(1, playerIn);
			}
        }
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}
	
	@Override
	public void registerModels() 
	{
		main.proxy.registerItemRender(this, 0, "inventory");
		
	}
	
}
