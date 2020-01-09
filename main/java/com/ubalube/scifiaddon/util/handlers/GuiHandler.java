package com.ubalube.scifiaddon.util.handlers;

import com.ubalube.scifiaddon.client.gui.GuiWorkshop;
import com.ubalube.scifiaddon.tileentity.ContainerWorkshop;
import com.ubalube.scifiaddon.tileentity.TileEntityWorkshop;
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
		if(ID == Reference.GUI_WORKSHOP) return new ContainerWorkshop(player.inventory, (TileEntityWorkshop)world.getTileEntity(new BlockPos(x,y,z)));
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) 
	{
		if(ID == Reference.GUI_WORKSHOP) return new GuiWorkshop(player.inventory, (TileEntityWorkshop)world.getTileEntity(new BlockPos(x,y,z)));
		
		return null;
	}

}
