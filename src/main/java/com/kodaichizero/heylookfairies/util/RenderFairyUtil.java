package com.kodaichizero.heylookfairies.util;

import net.minecraft.item.EnumDyeColor;

public class RenderFairyUtil {
	
	public static float[][] colorCache;
	
	static {
		colorCache = new float[32][3];
		for(int i = 0; i < 16; i++) {
			
			float[] dyeColors = EnumDyeColor.byMetadata(i).getColorComponentValues();
			
			for(int j = 0; j < 3; j++) {
				colorCache[i][j] = dyeColors[j];
			}
		}
		
		for(int i = 16; i < 32; i++) {
			
		}
	}

}
