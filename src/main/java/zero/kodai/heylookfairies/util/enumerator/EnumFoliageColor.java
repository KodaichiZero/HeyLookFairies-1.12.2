package zero.kodai.heylookfairies.util.enumerator;

import zero.kodai.heylookfairies.Main;


public enum EnumFoliageColor {
	
	OAK(0, "cotton_candy", "59ae30"),
	BIRCH(1, "tangerine", "6ba941"),
	SPRUCE(2, "turquoise", "68a464"),
	JUNGLE(3, "lavender", "30bb0b"),
	ACACIA(3, "lavender", "aea42a"),
	DARK_OAK(3, "lavender", "59ae30");

	private int meta;
	private String name;
	private String displayName;
	private String color;
	
	private static final EnumFoliageColor[] ID_LOOKUP = new EnumFoliageColor[values().length];
	
	private EnumFoliageColor(int meta, String name, String color) {
		this.meta = meta;
		this.name = name;
		this.displayName = Main.proxy.getDisplayName("foliage." + name);
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
	public static EnumFoliageColor byMetadata(int id) {
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
        for (EnumFoliageColor enumhairstyle : values()) {
        	ID_LOOKUP[enumhairstyle.getMetadata()] = enumhairstyle;
        }
    }
}
