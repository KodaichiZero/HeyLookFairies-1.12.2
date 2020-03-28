package zero.kodai.heylookfairies.entity.render.layer;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import zero.kodai.heylookfairies.entity.EntityFairy;
import zero.kodai.heylookfairies.entity.render.RenderFairy;
import zero.kodai.heylookfairies.util.Reference;
import zero.kodai.heylookfairies.util.RenderFairyUtil;
import zero.kodai.heylookfairies.util.enumerator.EnumHairStyle;

@SideOnly(Side.CLIENT)
public class LayerFairyHair implements LayerRenderer<EntityFairy> {
	
    private RenderFairy fairyRenderer;
    private EnumHairStyle hairStyle;
    private ModelBase hairModel;
    private ResourceLocation texture;
	
	public LayerFairyHair(RenderFairy fairyRendererIn, EnumHairStyle hairStyle) {
        this.fairyRenderer = fairyRendererIn;
        this.hairStyle = hairStyle;
        this.hairModel = hairStyle.getModel();
        this.texture = new ResourceLocation(Reference.MOD_ID + ":textures/entities/fairyhair_" + hairStyle.getUnlocalizedName() + ".png");
    }

	/**
	 * Here's where we set the fancy render options, like hair color, before rendering the model.
	 */
	@Override
	public void doRenderLayer(EntityFairy entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        if(entity.getHairStyle() == this.hairStyle && !entity.isInvisible()) {
            this.fairyRenderer.bindTexture(texture);

            Object dye = entity.getHairColor();
            float[] colors = RenderFairyUtil.getColorComponents(dye);
            
            GlStateManager.color(colors[0], colors[1], colors[2]);

            this.hairModel.setModelAttributes(this.fairyRenderer.getMainModel());
            this.hairModel.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entity);
            this.hairModel.render(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        }
    }

	/**
	 * What the heck is this? I dunno. The sheep had it.
	 */
	@Override
    public boolean shouldCombineTextures() {
        return false;
    }
}
