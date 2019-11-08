package com.kodaichizero.heylookfairies.util;

import java.util.List;
import com.kodaichizero.heylookfairies.entity.EntityFairy;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;

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
				if(fairy.shoulderSide == EnumShoulderSide.LEFT) {
					left = true;
				} else if(fairy.shoulderSide == EnumShoulderSide.RIGHT) {
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
