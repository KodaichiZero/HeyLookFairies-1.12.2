package zero.kodai.heylookfairies.proxy;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import zero.kodai.heylookfairies.entity.EntityFairy;
import zero.kodai.heylookfairies.entity.render.RenderFairy;
import zero.kodai.heylookfairies.init.InitItems;

public class ClientProxy<E> extends CommonProxy {
	
	/**
	 * Register an item's renderer.
	 */
	@Override
	public void registerItemRenderer(Item item, int meta, String id) {
		if(item == InitItems.MAGIC_DYE) {
			
		} else {
			
		}
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
	 * Get a localized display name.
	 */
	@Override
	public String getDisplayName(String name) {
		return I18n.format(name + ".name");
	}
	
	/**
	 * Get an item or block's localized lore.
	 */
	@Override
	public String getLore(String name) {
		return I18n.format(name + ".lore");
	}
}
