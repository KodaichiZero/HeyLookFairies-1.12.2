package zero.kodai.heylookfairies.entity.render;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import zero.kodai.heylookfairies.entity.EntityFairy;
import zero.kodai.heylookfairies.entity.model.ModelFairy;
import zero.kodai.heylookfairies.entity.render.layer.LayerFairyClothes;
import zero.kodai.heylookfairies.entity.render.layer.LayerFairyHair;
import zero.kodai.heylookfairies.util.Reference;
import zero.kodai.heylookfairies.util.enumerator.EnumFairyClothes;
import zero.kodai.heylookfairies.util.enumerator.EnumHairStyle;

public class RenderFairy extends RenderLiving<EntityFairy> {
	
	public static final ResourceLocation TEXTURE = new ResourceLocation(Reference.MOD_ID + ":textures/entities/fairy.png");
	public ModelFairy mainFairyModel;
	
	public RenderFairy(RenderManager manager) {
		super(manager, new ModelFairy(), 0.2F);
		this.mainFairyModel = (ModelFairy)this.getMainModel();
		this.addLayer(new LayerFairyHair(this, EnumHairStyle.PIGTAILS));
		this.addLayer(new LayerFairyHair(this, EnumHairStyle.BIGBUN));
		this.addLayer(new LayerFairyHair(this, EnumHairStyle.BRAIDEDPONYTAIL));
		this.addLayer(new LayerFairyHair(this, EnumHairStyle.NECKPILLOW));
		this.addLayer(new LayerFairyClothes(this, EnumFairyClothes.COLONIALDRESS));
		this.addLayer(new LayerFairyClothes(this, EnumFairyClothes.HULAGIRL));
		this.addLayer(new LayerFairyClothes(this, EnumFairyClothes.FORESTTUNIC));
		this.addLayer(new LayerFairyClothes(this, EnumFairyClothes.SHROOMYTOP));
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityFairy entity) {
		return TEXTURE;
	}
	
	@Override
    protected void renderModel(EntityFairy entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {
		if(!entity.isInvisible()) {
			GlStateManager.color(1.0F, 1.0F, 1.0F);
			
			//Render the main body first
			mainFairyModel.renderStage = 0;
			super.renderModel(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
			
			//Now just render the wings
			GlStateManager.alphaFunc(516, 0.1F);
	        GlStateManager.enableBlend();
			mainFairyModel.renderStage = 1;
			mainFairyModel.render(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
	        GlStateManager.disableBlend();
		}
	}
}
