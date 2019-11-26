package com.kodaichizero.heylookfairies.entity.render.layer;

import com.kodaichizero.heylookfairies.entity.EntityFairy;
import com.kodaichizero.heylookfairies.entity.model.ModelFairyBase;
import com.kodaichizero.heylookfairies.entity.render.RenderFairy;
import com.kodaichizero.heylookfairies.util.Reference;
import com.kodaichizero.heylookfairies.util.RenderFairyUtil;
import com.kodaichizero.heylookfairies.util.enumerator.EnumFairyClothes;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class LayerFairyClothes implements LayerRenderer<EntityFairy> {
	
    private RenderFairy fairyRenderer;
    private EnumFairyClothes clothes;
    private ModelFairyBase clothesModel;
    private ResourceLocation texture;
	
	public LayerFairyClothes(RenderFairy fairyRendererIn, EnumFairyClothes clothes) {
        this.fairyRenderer = fairyRendererIn;
        this.clothes = clothes;
        this.clothesModel = clothes.getModel();
        this.texture = new ResourceLocation(Reference.MOD_ID + ":textures/entities/fairyclothes_" + clothes.getUnlocalizedName() + ".png");
    }

	/**
	 * Here's where we set the fancy render options, like hair color, before rendering the model.
	 */
	@Override
	public void doRenderLayer(EntityFairy entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        if(entity.getClothes() == this.clothes && !entity.isInvisible()) {
            
        	//Get the model ready for rendering
        	this.fairyRenderer.bindTexture(texture);
            this.clothesModel.setModelAttributes(this.fairyRenderer.getMainModel());
            this.clothesModel.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entity);
            
            //First, render it without coloration.
            GlStateManager.color(1.0F, 1.0F, 1.0F);
            this.clothesModel.renderStage = 0;
            this.clothesModel.render(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
            
            //Second, apply the dye color and then render.
            Object dye = entity.getClothesColor();
            float[] colors = RenderFairyUtil.getColorComponents(dye);
            GlStateManager.color(colors[0], colors[1], colors[2]);
            this.clothesModel.renderStage = 1;
            this.clothesModel.render(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
            
            //Third, apply biome-specific foliage colors if applicable.
            colors = new float[] {0.0F, 1.0F, 0.0F};
            Biome biome = entity.world.getBiome(entity.getPosition());
            if(biome != null) {
            	colors = RenderFairyUtil.intToComponents(biome.getFoliageColorAtPos(entity.getPosition()));
            }
            
            GlStateManager.color(colors[0], colors[1], colors[2]);
            this.clothesModel.renderStage = 2;
            this.clothesModel.render(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
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
