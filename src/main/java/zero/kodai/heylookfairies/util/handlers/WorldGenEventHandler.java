package zero.kodai.heylookfairies.util.handlers;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeForest;
import net.minecraft.world.biome.BiomeForestMutated;
import net.minecraft.world.biome.BiomeHills;
import net.minecraft.world.biome.BiomeJungle;
import net.minecraft.world.biome.BiomeMesa;
import net.minecraft.world.biome.BiomePlains;
import net.minecraft.world.biome.BiomeSavanna;
import net.minecraft.world.biome.BiomeSwamp;
import net.minecraft.world.biome.BiomeTaiga;
import net.minecraft.world.gen.ChunkGeneratorOverworld;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import zero.kodai.heylookfairies.worldgen.WorldGenBoulder;
import zero.kodai.heylookfairies.worldgen.WorldGenFairyRing;

@EventBusSubscriber
public class WorldGenEventHandler {
	
	/**
	 * Populate fairy rings into the world.
	 */
	@SubscribeEvent
	public static void onPopulateChunk(PopulateChunkEvent.Post event) {
		if(!(event.getGen() instanceof ChunkGeneratorOverworld)) {
			return;
		}
		
		ChunkGeneratorOverworld gen = (ChunkGeneratorOverworld)event.getGen();
		Random rand = event.getRand();
		World world = event.getWorld();
		/*
		int x = event.getChunkX();
		int z = event.getChunkZ();
		
		int i = x * 16;
        int j = z * 16;
        BlockPos blockpos = new BlockPos(i, 0, j);
        Biome biome = world.getBiome(blockpos.add(16, 0, 16));
		
		if((biome == Biomes.DESERT || biome == Biomes.DESERT_HILLS)) {
	        if(net.minecraftforge.event.terraingen.TerrainGen.populate(gen, world, rand, x, z, false, net.minecraftforge.event.terraingen.PopulateChunkEvent.Populate.EventType.LAKE))
	        {
	            int i1 = event.getRand().nextInt(16) + 8;
	            int j1 = event.getRand().nextInt(256);
	            int k1 = event.getRand().nextInt(16) + 8;
	            (new WorldGenLakes(Blocks.WATER)).generate(world, rand, blockpos.add(i1, j1, k1));
	        }
		}
		
		BlockPos chunkPos = new BlockPos(event.getChunkX(), 0, event.getChunkZ());
		
		for(int i = 0; i < 4; i++) {
	        int x = rand.nextInt(16) + 8;
	        int z = rand.nextInt(16) + 8;
	        
	        Biome biome = world.getBiome(chunkPos.add(x, 0, z));
	        if(biome instanceof BiomeForest || biome instanceof BiomeTaiga || biome instanceof BiomePlains || biome instanceof BiomeHills || biome instanceof BiomeMesa
	        		|| biome instanceof BiomeJungle || biome instanceof BiomeSavanna || biome instanceof BiomeSwamp || biome instanceof BiomeForestMutated) {
	        	new WorldGenFairyRing().generate(world, rand, world.getHeight(chunkPos.add(x, 0, z)));
	        }
        }
        */
	}
	
	/**
	 * Decorate chunks as they generate
	 */
	@SubscribeEvent
	public static void onDecorateBiome(DecorateBiomeEvent.Post event) {
		World world = event.getWorld();
		Random rand = event.getRand();
		
		//Create random boulders.
        if(rand.nextInt(32) == 0) {
            int x = rand.nextInt(16) + 8;
            int z = rand.nextInt(16) + 8;
            
            Biome biome = world.getBiome(event.getChunkPos().getBlock(x, 0, z));
            if(biome.decorator.grassPerChunk > 0 && biome.decorator.treesPerChunk <= 0 && biome.decorator.extraTreeChance > 0) {
            	new WorldGenBoulder().generate(world, rand, world.getHeight(event.getChunkPos().getBlock(x, 0, z)));
            }
        }
        
        //Create fairy rings.
        for(int i = 0; i < 4; i++) {
	        int x = rand.nextInt(16) + 8;
	        int z = rand.nextInt(16) + 8;
	        
	        Biome biome = world.getBiome(event.getChunkPos().getBlock(x, 0, z));
	        if(biome instanceof BiomeForest || biome instanceof BiomeTaiga || biome instanceof BiomePlains || biome instanceof BiomeHills || biome instanceof BiomeMesa
	        		|| biome instanceof BiomeJungle || biome instanceof BiomeSavanna || biome instanceof BiomeSwamp || biome instanceof BiomeForestMutated) {
	        	new WorldGenFairyRing().generate(world, rand, world.getHeight(event.getChunkPos().getBlock(x, 0, z)));
	        }
        }
	}
}
