package com.ubalube.scifiaddon.init;

import com.ubalube.scifiaddon.enchantment.EnchantmentJohnWickReload;
import com.ubalube.scifiaddon.util.Reference;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import net.minecraftforge.registries.IForgeRegistry;

@ObjectHolder(Reference.MOD_ID)
public class ModEnchantments {
	public static final Enchantment johnwickreload = new EnchantmentJohnWickReload();
	
	@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
    public static class EnchantmentRegistration
    {
        
        /**
         * On event.
         *
         * @param event the event
         */
        @SubscribeEvent
        public static void onEvent(final RegistryEvent.Register<Enchantment> event)
        {
            // DEBUG
            System.out.println("Registering Enchantments");

            final IForgeRegistry<Enchantment> registry = event.getRegistry();
                        
            registry.register(johnwickreload);            
        }
    }
	
}
