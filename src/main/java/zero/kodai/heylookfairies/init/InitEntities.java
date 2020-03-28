package zero.kodai.heylookfairies.init;

import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import zero.kodai.heylookfairies.Main;
import zero.kodai.heylookfairies.entity.EntityFairy;
import zero.kodai.heylookfairies.util.Reference;

public class InitEntities {
	
	public static void registerEntities() {
		registerEntity("fairy", EntityFairy.class, Reference.ENTITY_FAIRY, 32, 6235277, 16753401);
		Main.proxy.registerEntityRenderers();
	}
	
	private static void registerEntity(String name, Class<? extends Entity> entity, int id, int range, int color1, int color2) {
		EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID + ":" + name), entity, name, id, Main.instance, range, 1, true, color1, color2);
	}

}
