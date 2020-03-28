package zero.kodai.heylookfairies.util;

import net.minecraft.item.EnumDyeColor;
import zero.kodai.heylookfairies.util.enumerator.EnumMagicDyeColor;

public class RenderFairyUtil {
	
	public static float[][] regularColorCache;
	public static float[][] magicColorCache;
	
	/**
	 * Return the color components of a specified dye.
	 */
	public static float[] getColorComponents(Object dye) {
		// TODO This will eventually take an Object as a parameter, which will be either an EnumDyeColor or an EnumMagicDyeColor.
		if(dye instanceof EnumDyeColor) {
			int id = ((EnumDyeColor)dye).getMetadata();
			return new float[] {regularColorCache[id][0], regularColorCache[id][1], regularColorCache[id][2]}; 
		} else if(dye instanceof EnumMagicDyeColor) {
			int id = ((EnumMagicDyeColor)dye).getMetadata();
			return new float[] {magicColorCache[id][0], magicColorCache[id][1], magicColorCache[id][2]}; 
		} else {
			throw new IllegalArgumentException("ERROR: Object given was not an an EnumDyeColor or EnumMagicDyeColor!");
		}
	}
	
	static {		
		regularColorCache = new float[16][3];
		magicColorCache = new float[16][3];
		for(int i = 0; i < 16; i++) {
			
			float[] dyeColors = EnumDyeColor.byMetadata(i).getColorComponentValues().clone();
			float[] magicDyeColors = EnumMagicDyeColor.byMetadata(i).getColorComponentValues().clone();
			
			for(int j = 0; j < 3; j++) {
				
				//Load in a default dye.
				regularColorCache[i][j] = dyeColors[j];
				
				//Load in a magic dye.
				magicColorCache[i][j] = magicDyeColors[j];
			}
			
			//This part reduces the sautration of the colors by averaging them out.
			float avg1 = (regularColorCache[i][0] + regularColorCache[i][1] + regularColorCache[i][2]) / 3F;
			float avg2 = (magicColorCache[i][0] + magicColorCache[i][1] + magicColorCache[i][2]) / 3F;
			for(int j = 0; j < 3; j++) {
				regularColorCache[i][j] = ((regularColorCache[i][j] * 5F) + avg1) / 7F; // TODO Divide by 1 more than necessary to reduce the overall brightness.
				magicColorCache[i][j] = ((magicColorCache[i][j] * 5F) + avg2) / 7F;
			}
		}
	}
	
	/**
	 * Used to convert an integer to individual color components brooooooo.
	 * We use this when we have to convert a foliage color on the fly, this is the most efficient way.
	 */
	public static float[] intToComponents(int colorIn) {
		float r = (float)((colorIn >> 16) & 0xFF) / 255F; 
		float g = (float)((colorIn >> 8) & 0xFF) / 255F; 
		float b = (float)((colorIn) & 0xFF) / 255F;
		return new float[] {r, g, b};
	}
}
