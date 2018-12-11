package com.ubalube.scifiaddon.items.music;

import com.ubalube.scifiaddon.main;
import com.ubalube.scifiaddon.init.ModItems;
import com.ubalube.scifiaddon.util.IHasModel;

import net.minecraft.item.ItemRecord;
import net.minecraft.util.SoundEvent;

public class RecordItem extends ItemRecord implements IHasModel{

	public RecordItem(String name, SoundEvent soundIn) {
		super(name, soundIn);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(main.partTab);
		
		ModItems.ITEMS.add(this);
	}
	@Override
	public void registerModels() {
		main.proxy.registerItemRender(this, 0, "inventory");
	}
}
