package com.ubalube.scifiaddon.util.handlers;

import com.ubalube.scifiaddon.tileentity.TileEntitySkinner;

import net.minecraftforge.fml.common.registry.GameRegistry;

public class TileEntityHandler 
{
	public static void registerTileEntities()
	{
		GameRegistry.registerTileEntity(TileEntitySkinner.class, "skinner");
	}
}
