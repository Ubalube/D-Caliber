package com.ubalube.scifiaddon.entity;

import com.ubalube.scifiaddon.main;
import com.ubalube.scifiaddon.init.ModItems;
import com.ubalube.scifiaddon.squads.SquadProvider;
import com.ubalube.scifiaddon.util.packets.ISquad;

import net.minecraft.advancements.AdvancementManager;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.IMerchant;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIWatchClosest2;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.World;

public class EntityTrader extends EntityCreature
{
	
	public EntityTrader(World worldIn) {
		super(worldIn);
	}
	
	@Override
	protected boolean canDespawn() {
		return false;
	}
	
	@Override
	protected void initEntityAI() {
		this.tasks.addTask(2, new EntityAIWatchClosest2(this, EntityPlayer.class, 3.0F, 1.0F));
	}
	
	@Override
	public boolean canBeHitWithPotion() {
		return false;
	}
	@Override
	public boolean canBeAttackedWithItem() {
		return false;
	}
	
	protected void applyEntityAttributes()
    {
		super.applyEntityAttributes();
        getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(10101010D);
        getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10000000D);
    }
	
	@Override
	protected boolean processInteract(EntityPlayer player, EnumHand hand) {
		ISquad squad = player.getCapability(SquadProvider.SQUAD, null);
		
		ItemStack handItem = player.getHeldItem(hand);
		
		/*if(handItem.getItem() == ModItems.SUPPLIES1)
		{
			player.inventory.clearMatchingItems(ModItems.SUPPLIES1, 0, 1, null);
			player.addExperience(12);
			squad.setSquadRep(squad.getSquadRep() + 25);
			if(!player.world.isRemote)
			{
				player.sendMessage(new TextComponentString(TextFormatting.BLUE + "+ 25 Squad Rep"));
			}
		}
		
		if(handItem.getItem() == ModItems.SUPPLIES2)
		{
			player.inventory.clearMatchingItems(ModItems.SUPPLIES2, 0, 1, null);
			player.addExperience(25);
			squad.setSquadRep(squad.getSquadRep() + 50);
			if(!player.world.isRemote)
			{
				player.sendMessage(new TextComponentString(TextFormatting.BLUE + "+ 50 Squad Rep"));
			}

			
		}*/
		
		
		main.proxy.openLoadoutGUI(player);
		
        return super.processInteract(player, hand);
		
	}
	
	@Override
	public boolean canBePushed() {
		return false;
	}
	
	@Override
	protected void playStepSound(BlockPos pos, Block blockIn) {
		return;
	}

	
	
	
}
