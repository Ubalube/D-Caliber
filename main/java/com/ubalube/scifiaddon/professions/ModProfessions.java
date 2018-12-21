package com.ubalube.scifiaddon.professions;

import java.util.Random;

import com.ubalube.scifiaddon.init.ModItems;
import com.ubalube.scifiaddon.util.Reference;

import net.minecraft.entity.IMerchant;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.EntityVillager.ITradeList;
import net.minecraft.entity.passive.EntityVillager.PriceInfo;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import net.minecraftforge.fml.common.registry.VillagerRegistry.VillagerCareer;
import net.minecraftforge.fml.common.registry.VillagerRegistry.VillagerProfession;
import net.minecraftforge.registries.IForgeRegistry;

@ObjectHolder(Reference.MOD_ID)
public class ModProfessions 
{
    // instantiate VillagerProfessions
    public final static VillagerProfession skinner = null;
    
    // declare VillagerCareers
    public static VillagerCareer skin;

    @Mod.EventBusSubscriber(modid = Reference.MOD_ID)
    public static class RegistrationHandler
    {
        /**
         * Register this mod's {@link VillagerProfession}s.
         *
         * @param event
         *            The event
         */
        @SubscribeEvent
        public static void onEvent(final RegistryEvent.Register<VillagerProfession> event)
        {
            final IForgeRegistry<VillagerProfession> registry = event.getRegistry();

            // DEBUG
            System.out.println("Registering villager professions");
            
            registry.register(new VillagerProfession(
            		Reference.MOD_ID+":gunsmith", 
            		Reference.MOD_ID+":textures/entity/gunsmith.png",  //Normal
            		Reference.MOD_ID+":textures/entity/gunsmithz.png" //Zombie
                    )
             );
        }
    }
    
    /**
     * Associate careers and trades.
     */
    public static void associateCareersAndTrades()
    {
        // DEBUG
        System.out.println("Associating careers and trades to villager professions");
        
        skin = (new VillagerCareer(skinner, "skinner")).addTrade(1, new TradeEmeraldForVector());
    }
    
    public static class TradeSkin implements ITradeList
    {
        /** The  item stack to buy */
        public ItemStack stack;
        /** The price info determining the amount of emeralds to trade in for the enchanted item */
        public EntityVillager.PriceInfo priceInfo;

        /**
         * Instantiates a new trade emeralds for enchanted boots.
         */
        public TradeSkin(Item skin)
        {
            stack = new ItemStack(ModItems.DESERT_PAINT);
            priceInfo = new PriceInfo(17, 64);
            
        }

        /* (non-Javadoc)
         * @see net.minecraft.entity.passive.EntityVillager.ITradeList#addMerchantRecipe(net.minecraft.entity.IMerchant, net.minecraft.village.MerchantRecipeList, java.util.Random)
         */
        @Override
        public void addMerchantRecipe(IMerchant merchant, MerchantRecipeList recipeList, Random random)
        {
            int actualPrice = 1;

            if (priceInfo != null)
            {
                actualPrice = priceInfo.getPrice(random);
            }

            ItemStack stackToPay = new ItemStack(Items.EMERALD, actualPrice, 0);
            recipeList.add(new MerchantRecipe(stackToPay, stack));
            
            // DEBUG
            System.out.println("Merchant recipe list = "+recipeList.getRecipiesAsTags());
        }
    }    
}
