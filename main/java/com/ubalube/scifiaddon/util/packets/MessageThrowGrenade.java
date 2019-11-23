package com.ubalube.scifiaddon.util.packets;

import com.ubalube.scifiaddon.entity.EntityFrag;
import com.ubalube.scifiaddon.entity.EntityImpact;
import com.ubalube.scifiaddon.items.CGrenade;
import com.ubalube.scifiaddon.items.GunBase;
import com.ubalube.scifiaddon.items.GunHybrid;
import com.ubalube.scifiaddon.items.CGrenade.type;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageThrowGrenade implements IMessage{
	
	//The Item
	private static ItemStack stack;
	//The Player ID
	private int playerId;
	//The method to give the PLAYER the ITEM AMOUNT times.
	public MessageThrowGrenade(EntityPlayer player, Item stack) {
		this.stack = new ItemStack(stack);
		this.playerId = player.getEntityId();
	}
	
	public MessageThrowGrenade() {
	}
	
	//BYTES
	
	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(playerId);
		ByteBufUtils.writeItemStack(buf, stack);
	}
	
	
	@Override
	public void fromBytes(ByteBuf buf) {
		playerId = buf.readInt();
		stack = ByteBufUtils.readItemStack(buf);
	}
	//The message to give the items
	public static class HandleThrowGrenade implements IMessageHandler<MessageThrowGrenade, IMessage>{

		@Override
		public IMessage onMessage(MessageThrowGrenade message, MessageContext ctx) {
			//Multiplayer Player
			EntityPlayerMP mp = ctx.getServerHandler().player;
			World world = mp.getEntityWorld();
			if (world == null) {
				
			} else {
				Entity p = world.getEntityByID(message.playerId);
				if(p != null && p instanceof EntityPlayer)
				{
					EntityPlayer pla = (EntityPlayer) p;
					int foundNades = 0;
					for(int i = 0; i < pla.inventory.getSizeInventory(); i ++)
					{
						if(pla.inventory.getStackInSlot(i).getItem() instanceof CGrenade)
						{
							foundNades = 1;
							//pla.inventory.clearMatchingItems(pla.inventory.getStackInSlot(i).getItem(), 0, 1, null);
							CGrenade grenade = (CGrenade) pla.inventory.getStackInSlot(i).getItem();
							if(grenade.typeOfGrenade == type.IMPACT)
							{
								EntityImpact entity = new EntityImpact(pla.world, pla);
								entity.shoot(pla, pla.rotationPitch, pla.rotationYaw, 1.0F, 2.0F, 0.0F);
								entity.setGravity(0.1F);
								pla.world.spawnEntity(entity);
							}
							break;
						}
					}
					
					if(foundNades < 1)
					{
						pla.sendMessage(new TextComponentString(TextFormatting.RED + "You need a grenade in your inventory!"));
					}
					
				}
			}
			return null;
		}
	}
	
}
