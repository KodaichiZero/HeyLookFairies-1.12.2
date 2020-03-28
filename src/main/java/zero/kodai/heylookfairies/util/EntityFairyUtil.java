package zero.kodai.heylookfairies.util;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import zero.kodai.heylookfairies.entity.EntityFairy;
import zero.kodai.heylookfairies.util.enumerator.EnumShoulderSide;

public class EntityFairyUtil {
	
	/**
	 * Determines if the player given as a parameter has a fairy on their shoulder. Returns an enum value.
	 */
	public static EnumShoulderSide playerHasFairyOnShoulder(EntityPlayer player) {
		boolean left = false;
		boolean right = false;
		Vec3d pos = player.getPositionVector();
		List<EntityFairy> fairies = player.world.getEntitiesWithinAABB(EntityFairy.class, new AxisAlignedBB(pos.x - 5D, pos.y - 5D, pos.z - 5D, pos.x + 5D, pos.y + 5D, pos.z + 5D));
		for(EntityFairy fairy : fairies) {
			if(fairy.isShoulderRiding()) {
				if(fairy.getShoulderSide() == EnumShoulderSide.LEFT) {
					left = true;
				} else if(fairy.getShoulderSide() == EnumShoulderSide.RIGHT) {
					right = true;
				}
			}
			
			if(left && right) {
				break;
			}
		}
		
		if(left && right) {
			return EnumShoulderSide.BOTH;
		} else if(left) {
			return EnumShoulderSide.LEFT;
		} else if(right) {
			return EnumShoulderSide.RIGHT;
		} 
		
		return EnumShoulderSide.NONE;
	}
}
