package com.kodaichizero.heylookfairies.util;

public enum EnumShoulderSide {
	
	NONE(0, "none"),
	LEFT(1, "left"),
	RIGHT(2, "right"),
	BOTH(3, "both");

	int meta;
	String name;
	
	private static final EnumShoulderSide[] ID_LOOKUP = new EnumShoulderSide[values().length];
	
	private EnumShoulderSide(int meta, String name) {
		this.meta = meta;
		this.name = name;
	}
	
	/**
	 * Get the ID of the hair style.
	 */
    public int getMetadata() {
        return this.meta;
    }
    
	/**
	 * Get the ID of the hair style.
	 */
    public byte getByte() {
        return (byte)this.meta;
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
	public static EnumShoulderSide byMetadata(int id) {
		if(id < 0) {
			id = 0;
		} else if(id >= values().length) {
			id = values().length - 1;
		}
		
		return ID_LOOKUP[id];
	}
	
	/**
	 * Fill out the static arrays.
	 */
	static {
        for (EnumShoulderSide enumShoulderSide : values()) {
        	ID_LOOKUP[enumShoulderSide.getMetadata()] = enumShoulderSide;
        }
    }
}
