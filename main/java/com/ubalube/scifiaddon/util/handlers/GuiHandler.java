package com.ubalube.scifiaddon.util.handlers;

import com.ubalube.scifiaddon.client.gui.GuiSkinner;
import com.ubalube.scifiaddon.tileentity.ContainerSkinner;
import com.ubalube.scifiaddon.tileentity.TileEntitySkinner;
import com.ubalube.scifiaddon.util.Reference;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler
{
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		if(ID == Reference.GUI_SKINNER) return new ContainerSkinner(player.inventory, (TileEntitySkinner)world.getTileEntity(new BlockPos(x,y,z)));
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) 
	{
		if(ID == Reference.GUI_SKINNER) return new GuiSkinner(player.inventory, (TileEntitySkinner)world.getTileEntity(new BlockPos(x,y,z)));
		return null;
	}

}
