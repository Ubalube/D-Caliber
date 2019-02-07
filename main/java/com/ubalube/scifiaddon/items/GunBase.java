package com.ubalube.scifiaddon.items;

import java.util.List;

import javax.annotation.Nullable;

import org.lwjgl.input.Keyboard;

import com.ubalube.scifiaddon.main;
import com.ubalube.scifiaddon.entity.EntityBullet;
import com.ubalube.scifiaddon.init.ModItems;
import com.ubalube.scifiaddon.util.IHasModel;
import com.ubalube.scifiaddon.util.handlers.SoundHandler;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.CooldownTracker;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import scala.util.Random;

public class GunBase extends Item implements IHasModel
{
	
	public int Recoil;
	
	int Firerate;
	int clipsize;
	int ReloadTime;
	int AutoFiremode;
	int SingleFiremode;
	float damage;
	int range;
	Item ammo;
	int type;
	
	String description;
	
	public GunBase(String name, int fireRate, int ammocap, int reloadtm, int recoil, float bulletDamage, int bulletDuration, Item ammunition, int guntype, String desc) 
	{
		setUnlocalizedName(name);
		setRegistryName(name);
		setMaxStackSize(1);
		
		this.Firerate = fireRate;
		this.clipsize = ammocap;
		this.ReloadTime = reloadtm;
		this.damage = bulletDamage;
		this.range = bulletDuration;
		this.ammo = ammunition;
		this.Recoil = recoil;
		this.description = desc;
		
		this.addPropertyOverride(new ResourceLocation("aiming"), new IItemPropertyGetter()
        {
			@SideOnly(Side.CLIENT)
	        public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn)
	        {
	            if (entityIn == null) {
	                return 0.0F;
	            }
	            NBTTagCompound nbt = checkNBTTags(stack);
	            float j = nbt.getBoolean("ADS") ? 1.0F : 0.0F;
	            return j; 
	        }
        });
		
		this.addPropertyOverride(new ResourceLocation("reloading"), new IItemPropertyGetter()
        {
			@SideOnly(Side.CLIENT)
	        public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn)
	        {
	            if (entityIn == null) {
	                return 0.0F;
	            }
	            NBTTagCompound nbt = checkNBTTags(stack);
	            float j = nbt.getBoolean("reload") ? 1.0F : 0.0F;
	            return j; 
	        }
        });
		
		this.addPropertyOverride(new ResourceLocation("running"), new IItemPropertyGetter()
        {
			@SideOnly(Side.CLIENT)
	        public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn)
	        {
	            if (entityIn == null) {
	                return 0.0F;
	            }
	            NBTTagCompound nbt = checkNBTTags(stack);
	            float j = nbt.getBoolean("running") ? 1.0F : 0.0F;
	            return j; 
	        }
        });
		
		ModItems.ITEMS.add(this);
	}
	

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) 
	{
		if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT))
		{
			tooltip.add(TextFormatting.YELLOW + "Weapon Information <!>");
			tooltip.add(TextFormatting.BLUE + "Impact: " + TextFormatting.GREEN + damage);
			tooltip.add(TextFormatting.BLUE + "Range:  " + TextFormatting.GREEN + range);
			tooltip.add(TextFormatting.BLUE + "Clipsize: " + TextFormatting.GREEN + clipsize);
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_LCONTROL) || Keyboard.isKeyDown(Keyboard.KEY_RCONTROL))
		{
			tooltip.add(TextFormatting.YELLOW + "Recoil Patterns <!>");
			tooltip.add(TextFormatting.RED + "Verticle Recoil: 1");
			tooltip.add(TextFormatting.RED + "Horizontal Recoil <Left> : " + (this.getRecoil() - 1));
			tooltip.add(TextFormatting.RED + "Horizontal Recoil <Right>: " + (this.getRecoil() + 1));
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_RSHIFT))
		{
			tooltip.add(TextFormatting.AQUA + this.description);
		}
		
		
		
		else
		{
			tooltip.add(TextFormatting.YELLOW + "Show Information (LSHIFT)");
			tooltip.add(TextFormatting.YELLOW + "Show Recoil Patterns (CTRL)");
			tooltip.add(TextFormatting.YELLOW + "Show Recoil Patterns (RSHIFT)");
			tooltip.add(TextFormatting.GREEN + "----------------------");
			
			
		}
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
	
	/*
	 * Sets the Recoil
	 */
	
	public void setRecoil(int amt)
	{
		this.Recoil = amt;
	}
	
	/*
	 * Returns the recoil
	 */
	
	public int getRecoil()
	{
		return this.Recoil;
	}
	
	/*
	 * Initiates the Verticle and Horizontal Recoil
	 */
	public void doRecoil(EntityPlayer p)
	{
		Minecraft mc = Minecraft.getMinecraft();
		
		Random rand = new Random();
		int yawWay = rand.nextInt(2);
		/*p.rotationPitch += -this.Recoil;
		if(yawWay == 1)
		{
			p.rotationYaw += this.Recoil + 1;
		}
		
		if(yawWay == 2)
		{
			p.rotationYaw += -this.Recoil - 1;
		}*/
		
		
		
	}
	public static NBTTagCompound checkNBTTags(ItemStack stack) {
        NBTTagCompound nbt = stack.getTagCompound();
        if (nbt == null) {
            nbt = new NBTTagCompound();
            stack.setTagCompound(nbt);
        }
        if (!nbt.hasKey("ADS")) {
            nbt.setBoolean("ADS", false);
        }
        
        if (!nbt.hasKey("reload")) {
            nbt.setBoolean("reload", false);
        }
        
        if (!nbt.hasKey("running")) {
            nbt.setBoolean("running", false);
        }
        
        return nbt;
    }
	
	
	
	public void doAim(ItemStack stack)
	{
		NBTTagCompound nbt = stack.getTagCompound();
        if(nbt == null)
        {
            nbt = new NBTTagCompound();
            stack.setTagCompound(nbt);
        }
        
        nbt.setBoolean("ADS", !nbt.getBoolean("ADS"));
	}
	
	
	
	/*
	 * Checks for the gun states (NBT)
	 */
	public void checkStates(ItemStack stack, World worldIn, Entity entityIn)
	{
		NBTTagCompound nbt = stack.getTagCompound();
		
		EntityPlayer playerIn = (EntityPlayer) entityIn;
		
		CooldownTracker cd = playerIn.getCooldownTracker();
        if (!cd.hasCooldown(this) && nbt.getBoolean("reload") == true && stack.getItem() instanceof GunBase) {
            nbt.setBoolean("reload", false);
        }
        
        if(playerIn.isSprinting())
        {
        	if(!nbt.getBoolean("reload"))
        	{
        		nbt.setBoolean("running", true);
            	nbt.setBoolean("ADS", false);
        	}
        	
        }
        else
        {
        	nbt.setBoolean("running", false);
        }
	}
	
	public void playShootSound(EntityPlayer playerIn)
	{
		World worldIn = playerIn.getEntityWorld();
		ItemStack stack = playerIn.getHeldItem(playerIn.getActiveHand());
		Item i = stack.getItem();
		
		if(i == ModItems.AKM || i == ModItems.SCAR || i == ModItems.SCARACOG || i == ModItems.HK416 || i == ModItems.FAL)
		{
			//worldIn.playSound(playerIn,	playerIn.posX, playerIn.posY, playerIn.posZ, sound, SoundCategory.MASTER, 1, 1);
		}
		
		
	}
	
	/*
	 * Shoots the gun
	 */
	public void shootGun(World worldIn, EntityPlayer playerIn, EnumHand handIn, ItemStack itemstack)
	{
		int firemode = AutoFiremode;
		
		boolean attach = false;
		
		NBTTagCompound nbt = itemstack.getTagCompound();
		if(nbt == null)
		{
			nbt = new NBTTagCompound();
		}
			if (!playerIn.capabilities.isCreativeMode)
			{
				if(itemstack.isItemDamaged())
				{
					if(itemstack.getItemDamage() == clipsize)
					{
						if(playerIn.inventory.hasItemStack(new ItemStack(ammo)))
						{
							itemstack.setItemDamage(-clipsize);
							playerIn.inventory.clearMatchingItems(ammo, 0, 1, null);
							playerIn.getCooldownTracker().setCooldown(this, ReloadTime);
							if(nbt.getBoolean("ADS") == true)
							{
								nbt.setBoolean("ADS", false);
							}
							nbt.setFloat("reload", 1);
						}
					}
					else
					{
						playerIn.getCooldownTracker().setCooldown(this, firemode);
						if (!worldIn.isRemote)
						{
							if(!playerIn.isSprinting())
							{
								EntityBullet entity = new EntityBullet(worldIn, playerIn);
								entity.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 1.0F, 7 * 3, 0.0F);
								entity.setDamage((double)this.damage);
								entity.shootingEntity = playerIn;
								entity.setRange(this.range);
								worldIn.spawnEntity(entity);
								itemstack.damageItem(1, playerIn);
								this.doRecoil(playerIn);
							}
							
							worldIn.playSound(playerIn,	playerIn.posX, playerIn.posY, playerIn.posZ, SoundHandler.G17_SHOOT, SoundCategory.MASTER, 1, 1);
							
						}
						
						
					}
					
				}
				else
				{
					//First Bullet
					playerIn.getCooldownTracker().setCooldown(this, firemode);
					if(!worldIn.isRemote)
					{
						if(!playerIn.isSprinting())
						{
							EntityBullet entity = new EntityBullet(worldIn, playerIn);
							entity.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 1.0F, 7 * 3, 0.0F);
							entity.setDamage((double)this.damage);
							entity.shootingEntity = playerIn;
							entity.setRange(this.range);
							worldIn.spawnEntity(entity);
							itemstack.damageItem(1, playerIn);
							this.doRecoil(playerIn);
						}
						
					}
					worldIn.playSound(playerIn,	playerIn.posX, playerIn.posY, playerIn.posZ, SoundHandler.G17_SHOOT, SoundCategory.MASTER, 1, 1);
					
				}
			}
			
			else
			{
				
				//Creative Move
				playerIn.getCooldownTracker().setCooldown(this, firemode);
				if(!worldIn.isRemote)
				{
					if(!playerIn.isSprinting())
					{
						EntityBullet entity = new EntityBullet(worldIn, playerIn);
						entity.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 1.0F, 7 * 3, 0.0F);
						entity.setDamage((double)this.damage);
						entity.setRange(this.range);
						entity.shootingEntity = playerIn;
						worldIn.spawnEntity(entity);
						itemstack.damageItem(1, playerIn);
						this.doRecoil(playerIn);
					}
				}
				worldIn.playSound(playerIn,	playerIn.posX, playerIn.posY, playerIn.posZ, SoundHandler.G17_SHOOT, SoundCategory.MASTER, 1, 1);
		}
	}
	
	@Override
	public void registerModels() 
	{
		main.proxy.registerItemRender(this, 0, "inventory");
		
	}
	
}
