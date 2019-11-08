package com.kodaichizero.heylookfairies.proxy;

import com.kodaichizero.heylookfairies.entity.EntityFairy;
import com.kodaichizero.heylookfairies.entity.render.RenderFairy;
import com.kodaichizero.heylookfairies.util.Reference;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class ClientProxy<E> extends CommonProxy {
	
	/**
	 * Register an item's renderer.
	 */
	@Override
	public void registerItemRenderer(Item item, int meta, String id) {
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), id));
	}

	/**
	 * Register all entities' renderers.
	 */
	@Override
	public void registerEntityRenderers() {
		//Fairies
		RenderingRegistry.registerEntityRenderingHandler(EntityFairy.class, new IRenderFactory<EntityFairy>() {
			@Override
			public Render<? super EntityFairy> createRenderFor(RenderManager manager) {
				return new RenderFairy(manager);
			}
		});
	}
	
	/**
	 * Get the name of a hairstyle from the player's locale.
	 */
	@Override
	public String getHairstyleDisplayName(String name) {
		return I18n.format(Reference.MOD_ID + ".hairstyle." + name);
	}
}
