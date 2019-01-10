package com.ubalube.scifiaddon.triggers;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;

import net.minecraft.advancements.ICriterionTrigger;
import net.minecraft.advancements.PlayerAdvancements;
import net.minecraft.advancements.critereon.AbstractCriterionInstance;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ResourceLocation;

public class TriggerInteract implements ICriterionTrigger
{
	   private final ResourceLocation RL;
	   private final Map listeners = Maps.newHashMap();

	    public TriggerInteract(String parString)
	    {
	        super();
	        RL = new ResourceLocation(parString);
	    }
	    
	    public TriggerInteract(ResourceLocation parRL)
	    {
	        super();
	        RL = parRL;
	    }
	    
	    /* (non-Javadoc)
	     * @see net.minecraft.advancements.ICriterionTrigger#getId()
	     */
	    @Override
	    public ResourceLocation getId()
	    {
	        return RL;
	    }

	    @Override
	    public void addListener(PlayerAdvancements playerAdvancementsIn, ICriterionTrigger.Listener listener)
	    {
	    	TriggerInteract.Listeners myCustomTrigger$listeners = listeners.get(playerAdvancementsIn);

	        if (myCustomTrigger$listeners == null)
	        {
	            myCustomTrigger$listeners = new TriggerInteract.Listeners(playerAdvancementsIn);
	            listeners.put(playerAdvancementsIn, myCustomTrigger$listeners);
	        }

	        myCustomTrigger$listeners.add(listener);
	    }

	    @Override
	    public void removeListener(PlayerAdvancements playerAdvancementsIn, ICriterionTrigger.Listener listener)
	    {
	    	TriggerInteract.Listeners tameanimaltrigger$listeners = listeners.get(playerAdvancementsIn);

	        if (tameanimaltrigger$listeners != null)
	        {
	            tameanimaltrigger$listeners.remove(listener);

	            if (tameanimaltrigger$listeners.isEmpty())
	            {
	                listeners.remove(playerAdvancementsIn);
	            }
	        }
	    }

	    @Override
	    public void removeAllListeners(PlayerAdvancements playerAdvancementsIn)
	    {
	        listeners.remove(playerAdvancementsIn);
	    }

	    /**
	     * Deserialize a ICriterionInstance of this trigger from the data in the JSON.
	     *
	     * @param json the json
	     * @param context the context
	     * @return the tame bird trigger. instance
	     */
	    @Override
	    public TriggerInteract.Instance deserializeInstance(JsonObject json, JsonDeserializationContext context)
	    {
	        return new TriggerInteract.Instance(getId());
	    }

	    /**
	     * Trigger.
	     *
	     * @param parPlayer the player
	     */
	    public void trigger(EntityPlayerMP parPlayer)
	    {
	    	TriggerInteract.Listeners tameanimaltrigger$listeners = Listeners.get(parPlayer.getAdvancements());

	        if (tameanimaltrigger$listeners != null)
	        {
	            tameanimaltrigger$listeners.trigger(parPlayer);
	        }
	    }

	    public static class Instance extends AbstractCriterionInstance
	    {
	        
	        /**
	         * Instantiates a new instance.
	         */
	        public Instance(ResourceLocation parRL)
	        {
	            super(parRL);
	        }

	        /**
	         * Test.
	         *
	         * @return true, if successful
	         */
	        public boolean test()
	        {
	            return true;
	        }
	    }

	    static class Listeners
	    {
	        private final PlayerAdvancements playerAdvancements;
	        private final Set listeners = Sets.newHashSet();

	        /**
	         * Instantiates a new listeners.
	         *
	         * @param playerAdvancementsIn the player advancements in
	         */
	        public Listeners(PlayerAdvancements playerAdvancementsIn)
	        {
	            playerAdvancements = playerAdvancementsIn;
	        }

	        /**
	         * Checks if is empty.
	         *
	         * @return true, if is empty
	         */
	        public boolean isEmpty()
	        {
	            return listeners.isEmpty();
	        }

	        /**
	         * Adds the listener.
	         *
	         * @param listener the listener
	         */
	        public void add(ICriterionTrigger.Listener listener)
	        {
	            listeners.add(listener);
	        }

	        /**
	         * Removes the listener.
	         *
	         * @param listener the listener
	         */
	        public void remove(ICriterionTrigger.Listener listener)
	        {
	            listeners.remove(listener);
	        }

	        /**
	         * Trigger.
	         *
	         * @param player the player
	         */
	        public void trigger(EntityPlayerMP player)
	        {
	            ArrayList list = null;

	            for (ICriterionTrigger.Listener listener : listeners)
	            {
	                if (listener.getCriterionInstance().test())
	                {
	                    if (list == null)
	                    {
	                        list = Lists.newArrayList();
	                    }

	                    list.add(listener);
	                }
	            }

	            if (list != null)
	            {
	                for (ICriterionTrigger.Listener listener1 : list)
	                {
	                    listener1.grantCriterion(playerAdvancements);
	                }
	            }
	        }
	    }
	}
}
