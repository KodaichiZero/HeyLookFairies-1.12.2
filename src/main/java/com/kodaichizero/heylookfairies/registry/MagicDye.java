package com.kodaichizero.heylookfairies.registry;

import com.kodaichizero.heylookfairies.util.Reference;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;
import net.minecraftforge.registries.RegistryBuilder;

@ObjectHolder(Reference.MOD_ID)
public class MagicDye extends IForgeRegistryEntry.Impl<MagicDye> {

	public static final MagicDye COTTON_CANDY = new MagicDye("cotton_candy");
	public static final MagicDye TANGERINE = new MagicDye("tangerine");
	public static final MagicDye TURQUOISE = new MagicDye("turquoise");
	public static final MagicDye LAVENDER = new MagicDye("lavender");
	public static final MagicDye BUBBLE_GUM = new MagicDye("bubble_gum");
	public static final MagicDye HONEY = new MagicDye("honey");
	public static final MagicDye AQUA = new MagicDye("aqua");
	public static final MagicDye LEMONGRASS = new MagicDye("lemongrass");
	public static final MagicDye AUBERGINE = new MagicDye("aubergine");
	public static final MagicDye PINE = new MagicDye("pine");
	public static final MagicDye BEESWAX = new MagicDye("beeswax");
	public static final MagicDye MAHOGANY = new MagicDye("mahogany");
	public static final MagicDye PERIWINKLE = new MagicDye("periwinkle");
	public static final MagicDye INDIGO = new MagicDye("indigo");
	public static final MagicDye COSMIC = new MagicDye("cosmic");
	public static final MagicDye PEARL = new MagicDye("pearl");
	
	private final String name;
	
	public MagicDye(String name) {
		this.name = name.toLowerCase();
		this.setRegistryName(new ResourceLocation(Reference.MOD_ID, "dye." + this.name));
	}
	
	public String getName() {
		return this.name;
	}
	
	@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
	public static class Register {
		
		@SubscribeEvent
		public static void create(RegistryEvent.NewRegistry event) {
			RegistryBuilder<MagicDye> dye = new RegistryBuilder<MagicDye>();
			dye.setType(MagicDye.class);
			ResourceLocation key = new ResourceLocation(Reference.MOD_ID, "dyes");
			dye.setName(key);
			dye.setDefaultKey(key);
			dye.create();
		}
		
		@SubscribeEvent
		public static void add(final RegistryEvent.Register<MagicDye> event) {
			final IForgeRegistry<MagicDye> registry = event.getRegistry();
			
			final MagicDye[] dyes = {
				COTTON_CANDY,
				TANGERINE,
				TURQUOISE,
				LAVENDER,
				BUBBLE_GUM,
				HONEY,
				AQUA,
				LEMONGRASS,
				AUBERGINE,
				PINE,
				BEESWAX,
				MAHOGANY,
				PERIWINKLE,
				INDIGO,
				COSMIC,
				PEARL
			};
			
			registry.registerAll(dyes);
		}
	}
}
