package zero.kodai.heylookfairies.items;

import javax.annotation.Nullable;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import scala.Int;

public class ItemFairyComm extends ItemBase {

	public ItemFairyComm(String name) {
		super(name);
        this.addPropertyOverride(new ResourceLocation("blocking"), new IItemPropertyGetter()
        {
            @SideOnly(Side.CLIENT)
            public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn)
            {
                return entityIn != null && entityIn.isHandActive() && entityIn.getActiveItemStack() == stack ? 1.0F : 0.0F;
            }
        });
		this.setCreativeTab(CreativeTabs.TOOLS);
	}
	
    /**
     * Called when the equipped item is right clicked.
     */
	@Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		ItemStack itemstack = playerIn.getHeldItem(handIn);
		
		if(handIn == EnumHand.MAIN_HAND && playerIn instanceof EntityPlayerSP) {
			EntityPlayerSP sp = (EntityPlayerSP)playerIn;
			sp.setActiveHand(handIn);
	        //sp.openGui(mod, modGuiId, world, x, y, z);
	        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
		}
        
		return new ActionResult<ItemStack>(EnumActionResult.FAIL, itemstack);
    }
	
    /**
     * Called when the player stops using an Item (stops holding the right mouse button).
     */
	@Override
    public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft) {
		if(entityLiving instanceof EntityPlayerSP) {
			EntityPlayerSP sp = (EntityPlayerSP)entityLiving;
	        sp.jump();
		}
    }

	
    /**
     * How long it takes to use or consume an item
     */
	@Override
    public int getMaxItemUseDuration(ItemStack stack) {
        return Int.MaxValue();
    }
	
    /**
     * returns the action that specifies what animation to play when the items is being used
     */
    public EnumAction getItemUseAction(ItemStack stack)
    {
        return EnumAction.BOW;
    }
}
