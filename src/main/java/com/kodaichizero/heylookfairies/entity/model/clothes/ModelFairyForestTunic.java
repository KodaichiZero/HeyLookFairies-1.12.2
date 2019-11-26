package com.kodaichizero.heylookfairies.entity.model.clothes;

import com.kodaichizero.heylookfairies.entity.model.ModelFairyBase;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelFairyForestTunic extends ModelFairyBase {
	
    public ModelRenderer tunicTop;
    public ModelRenderer tunicBelt;
    public ModelRenderer tunicBeltBuckle;
    public ModelRenderer tunicSkirt;
    public ModelRenderer bracelet;
    public ModelRenderer leftBoot;
    public ModelRenderer rightBoot;
	
	public ModelFairyForestTunic() {
		this.renderStage = 0;
        this.textureWidth = 32;
        this.textureHeight = 32;
    
        this.tunicTop = new ModelRenderer(this, 0, 0);
        this.tunicTop.addBox(-2.0F, 0.0F, -1.0F, 4, 4, 2, 0.125F);
        super.bodyBase.addChild(tunicTop);
        
        this.tunicBelt = new ModelRenderer(this, 0, 6);
        this.tunicBelt.addBox(-2.0F, 3.0F, -1.0F, 4, 1, 2, 0.25F);
        super.bodyBase.addChild(tunicBelt);
        
        this.tunicBeltBuckle = new ModelRenderer(this, 12, 6);
        this.tunicBeltBuckle.addBox(-1.0F, 3.0F, -1.125F, 2, 1, 1, 0.25F);
        super.bodyBase.addChild(tunicBeltBuckle);
        
        this.tunicSkirt = new ModelRenderer(this, 0, 9);
        this.tunicSkirt.addBox(-2.5F, 4.5F, -1.5F, 5, 2, 3, 0.25F);
        super.bodyBase.addChild(tunicSkirt);
        
        this.bracelet = new ModelRenderer(this, 0, 15);
        this.bracelet.addBox(-1.0F, 2.5F, -1.0F, 2, 1, 2, 0.125F);
        super.leftArmBase.addChild(bracelet);
        
        this.leftBoot = new ModelRenderer(this, 0, 18);
        this.leftBoot.addBox(-1.0F, 3.0F, -1.0F, 2, 3, 2, 0.25F);
        super.leftLegBase.addChild(leftBoot);
        
        this.rightBoot = new ModelRenderer(this, 8, 18);
        this.rightBoot.addBox(-1.0F, 3.0F, -1.0F, 2, 3, 2, 0.25F);
        super.rightLegBase.addChild(rightBoot);
	}
	
	/**
	 * Override because we need to scale the model down before rendering it.
	 */
	@Override
	public void childRender(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		tunicBelt.isHidden = 
		tunicBeltBuckle.isHidden =
		bracelet.isHidden = 
		leftBoot.isHidden =
		rightBoot.isHidden = (this.renderStage != 0);
		
		tunicTop.isHidden =
		tunicSkirt.isHidden = (this.renderStage != 1);
		
		super.bodyBase.render(scale);
		super.leftArmBase.render(scale);
		super.rightArmBase.render(scale);
    }
}
