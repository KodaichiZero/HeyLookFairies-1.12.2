package com.kodaichizero.heylookfairies.entity.render.layer;

import com.kodaichizero.heylookfairies.entity.EntityFairy;
import com.kodaichizero.heylookfairies.entity.model.ModelFairyPigtails;
import com.kodaichizero.heylookfairies.entity.render.RenderFairy;
import com.kodaichizero.heylookfairies.util.References;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class LayerFairyPigtails implements LayerRenderer<EntityFairy> {
	
	public static final ResourceLocation TEXTURE = new ResourceLocation(References.MOD_ID + ":textures/entities/fairypigtails.png");
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
            this.fairyRenderer.bindTexture(TEXTURE);

            //Get the fairy's hair color and color the model.
			float[] afloat = entityFairyIn.getHairColor().getColorComponentValues();
			float avg = (afloat[0] + afloat[1] + afloat[2]) / 3F;
			for(int i = 0; i < 3; i++) {
				afloat[i] = (afloat[i] * 3F + avg) / 4F;
			}
            GlStateManager.color(afloat[0], afloat[1], afloat[2]);

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
        return false;
    }
}
