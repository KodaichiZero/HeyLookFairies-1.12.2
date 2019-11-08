package com.kodaichizero.heylookfairies.entity.render.layer;

import com.kodaichizero.heylookfairies.entity.EntityFairy;
import com.kodaichizero.heylookfairies.entity.render.RenderFairy;
import com.kodaichizero.heylookfairies.util.EnumHairStyle;
import com.kodaichizero.heylookfairies.util.Reference;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

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
        this.texture = new ResourceLocation(Reference.MOD_ID + ":textures/entities/fairyhair_" + hairStyle.getName() + ".png");
    }

	/**
	 * Here's where we set the fancy render options, like hair color, before rendering the model.
	 */
	@Override
	public void doRenderLayer(EntityFairy entityFairyIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        if(entityFairyIn.getHairStyle() == this.hairStyle && !entityFairyIn.isInvisible()) {
            this.fairyRenderer.bindTexture(texture);

            //Get the fairy's hair color and color the model.
            // TODO make it so we don't calculate this every single frame.
			float[] colors = entityFairyIn.getHairColor().getColorComponentValues().clone();
			
			float avg = (colors[0] + colors[1] + colors[2]) / 3F;
			for(int i = 0; i < 3; i++) {
				colors[i] = ((colors[i] * 5F) + avg) / 6F;
			}
			
            GlStateManager.color(colors[0], colors[1], colors[2]);

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
