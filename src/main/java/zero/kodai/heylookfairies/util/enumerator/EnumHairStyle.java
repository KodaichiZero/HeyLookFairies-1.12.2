package zero.kodai.heylookfairies.util.enumerator;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import zero.kodai.heylookfairies.Main;

public enum EnumHairStyle {
	
	PIGTAILS(0, "pigtails"),
	BIGBUN(1, "bigbun"),
	BRAIDEDPONYTAIL(2, "braidedponytail"),
	NECKPILLOW(3, "neckpillow");

	private int meta;
	private String name;
	private String displayName;
	private Object model;
	
	private static final EnumHairStyle[] ID_LOOKUP = new EnumHairStyle[values().length];
	
	private EnumHairStyle(int meta, String name) {
		this.meta = meta;
		this.name = name;
		this.displayName = Main.proxy.getDisplayName("hairstyle." + name);
		this.model = Main.proxy.getHairModel(meta);
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
	public static EnumHairStyle byMetadata(int id) {
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
	public String getDisplayName() {
		return displayName;
	}

	/**
	 * Gets the model which will be used in the render layer.
	 */
	@SideOnly(Side.CLIENT)
	public Object getModel() {
		return model;
	}

	/**
	 * Fill out the static arrays.
	 */
	static {
        for (EnumHairStyle enumhairstyle : values()) {
        	ID_LOOKUP[enumhairstyle.getMetadata()] = enumhairstyle;
        }
    }
}
