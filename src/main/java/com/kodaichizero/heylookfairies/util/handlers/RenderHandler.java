package com.kodaichizero.heylookfairies.util.handlers;

import com.kodaichizero.heylookfairies.entity.EntityFairy;
import com.kodaichizero.heylookfairies.entity.render.RenderFairy;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class RenderHandler {
	
	@SideOnly(Side.CLIENT)
	public static void registerEntityRenderers() {
		RenderingRegistry.registerEntityRenderingHandler(EntityFairy.class, new IRenderFactory<EntityFairy>() {
			@Override
			public Render<? super EntityFairy> createRenderFor(RenderManager manager) {
				return new RenderFairy(manager);
			}
		});
	}
}
