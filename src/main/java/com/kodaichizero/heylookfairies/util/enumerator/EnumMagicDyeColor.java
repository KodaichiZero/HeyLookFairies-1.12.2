package com.kodaichizero.heylookfairies.util.enumerator;

import com.kodaichizero.heylookfairies.Main;


public enum EnumMagicDyeColor {
	
	COTTON_CANDY(0, "cotton_candy", "ffaff0"),
	TANGERINE(1, "tangerine", "ff9c50"),
	TURQUOISE(2, "turquoise", "61cfbd"),
	LAVENDER(3, "lavender", "ab97ff"),
	BUBBLE_GUM(4, "bubble_gum", "f06cba"),
	HONEY(5, "honey", "ffce73"),
	AQUA(6, "aqua", "7ec8e6"),
	LEMONGRASS(7, "lemongrass", "cae584"),
	EGGPLANT(8, "eggplant", "7859ac"),
	PINE(9, "pine", "348063"),
	BEESWAX(10, "beeswax", "b6985f"),
	MAHOGANY(11, "mahogany", "a14c4c"),
	PERIWINKLE(12, "periwinkle", "bbceff"),
	INDIGO(13, "indigo", "6f5fd9"),
	COSMIC(14, "cosmic", "3b2e66"),
	PEARL(15, "pearl", "efcefe");

	private int meta;
	private String name;
	private String displayName;
	private String color;
	
	private static final EnumMagicDyeColor[] ID_LOOKUP = new EnumMagicDyeColor[values().length];
	
	private EnumMagicDyeColor(int meta, String name, String color) {
		this.meta = meta;
		this.name = name;
		this.displayName = Main.proxy.getDisplayName("magicdye." + name);
		this.color = color;
	}
	
	/**
	 * Get the ID of the hair style.
	 */
    public int getMetadata() {
        return this.meta;
    }
    
	/**
	 * Get the total number of available hair styles.
	 */
    public String getUnlocalizedName() {
        return this.name;
    }
    
	/**
	 * Get the ID of the hair style.
	 */
    public static int getLength() {
        return values().length;
    }
	
	/**
	 * Returns a specified hair style based on the ID parameter.
	 */
	public static EnumMagicDyeColor byMetadata(int id) {
		if(id < 0) {
			id = 0;
		} else if(id >= values().length) {
			id = values().length - 1;
		}
		
		return ID_LOOKUP[id];
	}

	/**
	 * Gets the name of the hair style as defined in the local translation file.
	 */
	public String getDyeColorName() {
		return displayName;
	}

	/**
	 * Gets the color of the magic dye as a hex string.
	 */
	public String getColor() {
		return color;
	}
	
	/**
	 * Gets the color of the magic dye as an integer.
	 */
	public int getIntColor() {
		return Integer.valueOf(color, 16);
	}
	
	/**
	 * Gets the color components as an array of floats.
	 */
	public float[] getColorComponentValues() {
		float components[] = new float[3];
		
		for(int i = 0; i < 3; i++) {
			String smallHex = color.substring((i*2), (i*2) + 2);
			components[i] = (float)(Integer.parseInt(smallHex, 16)) / 255.0F;
		}
		
		return components;
	}

	/**
	 * Fill out the static arrays.
	 */
	static {
        for (EnumMagicDyeColor enumhairstyle : values()) {
        	ID_LOOKUP[enumhairstyle.getMetadata()] = enumhairstyle;
        }
    }
}
