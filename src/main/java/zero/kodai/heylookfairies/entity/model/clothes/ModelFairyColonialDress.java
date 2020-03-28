package zero.kodai.heylookfairies.entity.model.clothes;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import zero.kodai.heylookfairies.entity.model.ModelFairyBase;

public class ModelFairyColonialDress extends ModelFairyBase {
	
    public ModelRenderer dressTop;
    public ModelRenderer dressBelt;
    public ModelRenderer dressBeltBuckle;
    public ModelRenderer dressSkirt;
    public ModelRenderer dressSkirt2;
    public ModelRenderer dressSkirtBib;
    public ModelRenderer dressSkirtBib2;
    public ModelRenderer dressLeftShoulder;
    public ModelRenderer dressLeftShoulderTrim;
    public ModelRenderer dressRightShoulder;
    public ModelRenderer dressRightShoulderTrim;
	
	public ModelFairyColonialDress() {
		this.renderStage = 0;
        this.textureWidth = 32;
        this.textureHeight = 32;
    
        this.dressTop = new ModelRenderer(this, 0, 0);
        this.dressTop.addBox(-2.0F, 0.0F, -1.0F, 4, 3, 2, 0.25F);
        super.bodyBase.addChild(dressTop);
        
        this.dressBelt = new ModelRenderer(this, 0, 5);
        this.dressBelt.addBox(-2.0F, 3.0F, -1.0F, 4, 3, 2, 0.125F);
        super.bodyBase.addChild(dressBelt);
        
        this.dressBeltBuckle = new ModelRenderer(this, 20, 3);
        this.dressBeltBuckle.addBox(-1.0F, 3.0F, -1.5F, 2, 1, 1, 0.0625F);
        super.bodyBase.addChild(dressBeltBuckle);
        
        this.dressSkirt = new ModelRenderer(this, 0, 8);
        this.dressSkirt.addBox(-2.5F, 0.0F, -1.5F, 5, 1, 3);
        this.dressSkirt.setRotationPoint(0.0F, 4.0F, 0.0F);
        super.bodyBase.addChild(dressSkirt);
        
        this.dressSkirt2 = new ModelRenderer(this, 0, 12);
        this.dressSkirt2.addBox(-3.5F, 1.0F, -2.5F, 7, 2, 5);
        dressSkirt.addChild(dressSkirt2);
        
        this.dressSkirtBib = new ModelRenderer(this, 0, 19);
        this.dressSkirtBib.addBox(-1.5F, -0.125F, -1.625F, 3, 1, 3);
        this.dressSkirtBib.setRotationPoint(0.0F, 4.0F, 0.0F);
        super.bodyBase.addChild(dressSkirtBib);
        
        this.dressSkirtBib2 = new ModelRenderer(this, 0, 25);
        this.dressSkirtBib2.addBox(-1.5F, 0.875F, -2.625F, 3, 2, 3);
        dressSkirtBib.addChild(dressSkirtBib2);
        
        /*
        this.dressKnickers = new ModelRenderer(this, 20, 3);
        this.dressKnickers.addBox(-2.0F, 5.0F, -1.0F, 4, 1, 2, 0.25F);
        super.bodyBase.addChild(dressKnickers);
        */
        
        this.dressLeftShoulder = new ModelRenderer(this, 12, 0);
        this.dressLeftShoulder.addBox(-1.0F, -1.0F, -1.0F, 2, 2, 2, 0.375F);
        super.leftArmBase.addChild(dressLeftShoulder);
        
        this.dressLeftShoulderTrim = new ModelRenderer(this, 20, 0);
        this.dressLeftShoulderTrim.addBox(-1.0F, 0.625F, -1.0F, 2, 1, 2, 0.125F);
        super.leftArmBase.addChild(dressLeftShoulderTrim);
        
        this.dressRightShoulder = new ModelRenderer(this, 12, 0);
        this.dressRightShoulder.mirror = true;
        this.dressRightShoulder.addBox(-1.0F, -1.0F, -1.0F, 2, 2, 2, 0.375F);
        super.rightArmBase.addChild(dressRightShoulder);
        
        this.dressRightShoulderTrim = new ModelRenderer(this, 20, 0);
        this.dressRightShoulder.mirror = true;
        this.dressRightShoulderTrim.addBox(-1.0F, 0.625F, -1.0F, 2, 1, 2, 0.125F);
        super.rightArmBase.addChild(dressRightShoulderTrim);
	}
	
	/**
	 * Override because we need to scale the model down before rendering it.
	 */
	@Override
	public void childRender(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		dressBelt.isHidden =
		dressBeltBuckle.isHidden =
		dressLeftShoulderTrim.isHidden = 
		dressRightShoulderTrim.isHidden =
		dressSkirtBib.isHidden =
		dressSkirtBib2.isHidden = (this.renderStage != 0);
		
		dressTop.isHidden =
		dressSkirt.isHidden =
		dressLeftShoulder.isHidden =
		dressRightShoulder.isHidden = (this.renderStage != 1);
		
		super.bodyBase.render(scale);
		super.leftArmBase.render(scale);
		super.rightArmBase.render(scale);
    }
	
	@Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
		super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
		//this.dressSkirt.rotateAngleX = (super.leftLegBase.rotateAngleX + super.rightLegBase.rotateAngleX) / 3F;
	}
}
