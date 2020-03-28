package zero.kodai.heylookfairies.util.handlers;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import zero.kodai.heylookfairies.init.InitBlocks;
import zero.kodai.heylookfairies.init.InitEntities;
import zero.kodai.heylookfairies.init.InitItems;
import zero.kodai.heylookfairies.util.IHasModel;
import zero.kodai.heylookfairies.util.enumerator.EnumMagicDyeColor;

@EventBusSubscriber
public class RegistryEventHandler {
	
	@SubscribeEvent
	public static void onItemRegister(RegistryEvent.Register<Item> event) {
		event.getRegistry().registerAll(InitItems.ITEMS.toArray(new Item[0]));
	}
	
	@SubscribeEvent
	public static void onBlockRegister(RegistryEvent.Register<Block> event) {
		event.getRegistry().registerAll(InitBlocks.BLOCKS.toArray(new Block[0]));
	}
	
	@SubscribeEvent
	public static void onModelRegister(ModelRegistryEvent event) {
		for(Item item : InitItems.ITEMS) {
			if(item instanceof IHasModel) {
				((IHasModel)item).registerModels();
			}
		}
		
		for(Block block : InitBlocks.BLOCKS) {
			if(block instanceof IHasModel) {
				((IHasModel)block).registerModels();
			}
		}
	}
	
	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public static void onItemColorHandler(ColorHandlerEvent.Item event) {
		event.getItemColors().registerItemColorHandler(new IItemColor() {
            public int colorMultiplier(ItemStack stack, int tintIndex) {
                return EnumMagicDyeColor.byMetadata(stack.getMetadata()).getIntColor();
            }
        }, InitItems.MAGIC_DYE);
	}

	/**
	 * A hook in the mod's pre-inititalization to get all entities registered.
	 */
	public static void preInitRegistries() {
		InitEntities.registerEntities();
	}
}
