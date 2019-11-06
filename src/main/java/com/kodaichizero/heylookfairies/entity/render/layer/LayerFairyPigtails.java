package com.kodaichizero.heylookfairies.entity.render.layer;

import com.kodaichizero.heylookfairies.entity.EntityFairy;
import com.kodaichizero.heylookfairies.entity.model.ModelFairyPigtails;
import com.kodaichizero.heylookfairies.entity.render.RenderFairy;
import com.kodaichizero.heylookfairies.util.References;

import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class LayerFairyPigtails implements LayerRenderer<EntityFairy> {
	
	public static final ResourceLocation TEXTURES = new ResourceLocation(References.MOD_ID + ":textures/entities/fairy.png");
    private final RenderFairy fairyRenderer;
    private final ModelFairyPigtails hairModel = new ModelFairyPigtails();
	
	public LayerFairyPigtails(RenderFairy fairyRendererIn) {
        this.fairyRenderer = fairyRendererIn;
    }

	/**
	 * Here's where we set the fancy render options, like hair color, before rendering the model.
	 */
	@Override
	public void doRenderLayer(EntityFairy entityFairyIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        if(entityFairyIn.hasPigtails() && !entityFairyIn.isInvisible()) {
            this.fairyRenderer.bindTexture(TEXTURES);

            /* 
             * Cool color stuff we're ignoring for now.
             * 
            if (entityFairyIn.hasCustomName() && "jeb_".equals(entityFairyIn.getCustomNameTag()))
            {
                int i1 = 25;
                int i = entityFairyIn.ticksExisted / 25 + entityFairyIn.getEntityId();
                int j = EnumDyeColor.values().length;
                int k = i % j;
                int l = (i + 1) % j;
                float f = ((float)(entityFairyIn.ticksExisted % 25) + partialTicks) / 25.0F;
                float[] afloat1 = EntitySheep.getDyeRgb(EnumDyeColor.byMetadata(k));
                float[] afloat2 = EntitySheep.getDyeRgb(EnumDyeColor.byMetadata(l));
                GlStateManager.color(afloat1[0] * (1.0F - f) + afloat2[0] * f, afloat1[1] * (1.0F - f) + afloat2[1] * f, afloat1[2] * (1.0F - f) + afloat2[2] * f);
            }
            else
            {
                float[] afloat = EntitySheep.getDyeRgb(entityFairyIn.getFleeceColor());
                GlStateManager.color(afloat[0], afloat[1], afloat[2]);
            }
            */

            this.hairModel.setModelAttributes(this.fairyRenderer.getMainModel());
            this.hairModel.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityFairyIn);
            this.hairModel.render(entityFairyIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        }
    }

	/**
	 * What the heck is this? I dunno. The sheep had it.
	 */
	@Override
    public boolean shouldCombineTextures() {
        return true;
    }
}
