package com.kodaichizero.heylookfairies.entity.model.hair;

import com.kodaichizero.heylookfairies.entity.EntityFairy;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelFairyBraidedponytail extends ModelBase {
	
	public static final float fairyScale = 0.5F;
	
    public ModelRenderer hairTop;
    public ModelRenderer hairFrontMid;
    public ModelRenderer hairFrontMidLeft1;
    public ModelRenderer hairFrontMidLeft2;
    public ModelRenderer hairFrontLeft1;
    public ModelRenderer hairFrontLeft2;
    public ModelRenderer hairFrontMidRight1;
    public ModelRenderer hairFrontMidRight2;
    public ModelRenderer hairFrontRight1;
    public ModelRenderer hairFrontRight2;
    public ModelRenderer hairLeft;
    public ModelRenderer hairRight;
    public ModelRenderer hairBack;
    public ModelRenderer hairPonytail1;
    public ModelRenderer hairPonytail2;
    public ModelRenderer hairPonytail3;
    public ModelRenderer hairPonytail4;
    public ModelRenderer hairPonytail4a;
    public ModelRenderer hairPonytail4b;
	
	public ModelFairyBraidedponytail() {
        this.textureWidth = 32;
        this.textureHeight = 32;
    
	    //Top
	    this.hairTop = new ModelRenderer(this, 0, 0);
	    this.hairTop.addBox(-3.0F, -7.0F, -3.0F, 6, 1, 6);
	    this.hairTop.setRotationPoint(0.0F, 0.0F, 0.0F);
	    
	    //Front
	    this.hairFrontMid = new ModelRenderer(this, 0, 0);
	    this.hairFrontMid.addBox(-0.5F, -6.0F, -4.0F, 1, 1, 1);
	    this.hairTop.addChild(hairFrontMid);
	    
	    this.hairFrontMidLeft1 = new ModelRenderer(this, 18, 0);
	    this.hairFrontMidLeft1.addBox(-2.5F, -6.0F, -4.0F, 2, 2, 1);
	    this.hairTop.addChild(hairFrontMidLeft1);
	    
	    this.hairFrontMidLeft2 = new ModelRenderer(this, 0, 2);
	    this.hairFrontMidLeft2.addBox(-2.5F, -6.0F, -5.0F, 2, 1, 1);
	    this.hairTop.addChild(hairFrontMidLeft2);
	    
	    this.hairFrontLeft1 = new ModelRenderer(this, 24, 0);
	    this.hairFrontLeft1.addBox(-3.5F, -6.0F, -4.0F, 1, 4, 1);
	    this.hairTop.addChild(hairFrontLeft1);
	    
	    this.hairFrontLeft2 = new ModelRenderer(this, 28, 5);
	    this.hairFrontLeft2.addBox(-3.5F, -6.0F, -5.0F, 1, 3, 1);
	    this.hairTop.addChild(hairFrontLeft2);
	    
	    this.hairFrontMidRight1 = new ModelRenderer(this, 18, 3);
	    this.hairFrontMidRight1.addBox(0.5F, -6.0F, -4.0F, 2, 2, 1);
	    this.hairTop.addChild(hairFrontMidRight1);
	    
	    this.hairFrontMidRight2 = new ModelRenderer(this, 0, 4);
	    this.hairFrontMidRight2.addBox(0.5F, -6.0F, -5.0F, 2, 1, 1);
	    this.hairTop.addChild(hairFrontMidRight2);
	    
	    this.hairFrontRight1 = new ModelRenderer(this, 28, 0);
	    this.hairFrontRight1.addBox(2.5F, -6.0F, -4.0F, 1, 4, 1);
	    this.hairTop.addChild(hairFrontRight1);
	    
	    this.hairFrontRight2 = new ModelRenderer(this, 28, 9);
	    this.hairFrontRight2.addBox(2.5F, -6.0F, -5.0F, 1, 3, 1);
	    this.hairTop.addChild(hairFrontRight2);
	    
	    //Left
	    this.hairLeft = new ModelRenderer(this, 0, 7);
	    this.hairLeft.addBox(-4.0F, -6.0F, -3.0F, 1, 5, 6);
	    this.hairTop.addChild(hairLeft);
	    
	    //Right
	    this.hairRight = new ModelRenderer(this, 0, 7);
	    this.hairRight.addBox(3.0F, -6.0F, -3.0F, 1, 5, 6);
	    this.hairTop.addChild(hairRight);
	    
	    //Back
	    this.hairBack = new ModelRenderer(this, 14, 7);
	    this.hairBack.addBox(-3.0F, -6.0F, 3.0F, 6, 5, 1);
	    this.hairTop.addChild(hairBack);
	    
	    //Ponytail
	    this.hairPonytail1 = new ModelRenderer(this, 0, 18);
	    this.hairPonytail1.addBox(-2.0F, -1.0F, -1.0F, 4, 4, 2);
	    this.hairPonytail1.setRotationPoint(0.0F, -4.0F, 4.5F);
	    this.hairTop.addChild(hairPonytail1);
	    
	    this.hairPonytail2 = new ModelRenderer(this, 0, 24);
	    this.hairPonytail2.addBox(-1.5F, 0.0F, -1.0F, 3, 3, 2);
	    this.hairPonytail2.setRotationPoint(0.0F, 3.0F, 0.0F);
	    this.hairPonytail1.addChild(hairPonytail2);
	    
	    this.hairPonytail3 = new ModelRenderer(this, 14, 13);
	    this.hairPonytail3.addBox(-1.0F, 0.0F, -1.0F, 2, 2, 2);
	    this.hairPonytail3.setRotationPoint(0.0F, 3.0F, 0.0F);
	    this.hairPonytail2.addChild(hairPonytail3);
	    
	    this.hairPonytail4 = new ModelRenderer(this, 14, 17);
	    this.hairPonytail4.addBox(-1.5F, 0.0F, -1.0F, 3, 2, 2);
	    this.hairPonytail4.setRotationPoint(0.0F, 2.0F, 0.0F);
	    this.hairPonytail3.addChild(hairPonytail4);
	    
	    this.hairPonytail4a = new ModelRenderer(this, 14, 21);
	    this.hairPonytail4a.addBox(-1.0F, 0.0F, -1.5F, 2, 2, 3);
	    this.hairPonytail4.addChild(hairPonytail4a);
	    
	    this.hairPonytail4b = new ModelRenderer(this, 14, 26);
	    this.hairPonytail4b.addBox(-1.0F, 1.5F, -1.0F, 2, 1, 2);
	    this.hairPonytail4.addChild(hairPonytail4b);
	}
	
	/**
	 * Override because we need to scale the model down before rendering it.
	 */
	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(0.0F, 18.0F * scale, 0.0F);
		GlStateManager.scale(fairyScale, fairyScale, fairyScale);
        
		this.hairTop.render(scale);
		
        GlStateManager.popMatrix();
    }
	
	/**
	 * Defining custom animations for the hair.
	 */
	@Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
    	hairTop.rotateAngleY = netHeadYaw * 0.017453292F;
        hairTop.rotateAngleX = headPitch * 0.017453292F;

        //Ponytail Animation
        
        //My math skills are unheard of.
        //This right here is a function that I just came up with that is used for the ponytail animation, 
        //which makes the intensity of the blowing affect proportional to how close they are moving in the direction on their look vector.
        //This stops their ponytails from blowing sideways when riding your shoulder.
        float angleAmount = Math.max(0.0F, (float)Math.sin((float)Math.atan2((entityIn.posZ - entityIn.prevPosZ), (entityIn.posX - entityIn.prevPosX)) - (entityIn.getRotationYawHead() * ((float)Math.PI / 180F))));
        
        //Reset all rotation to start.
        hairPonytail1.rotateAngleX = hairPonytail2.rotateAngleX = hairPonytail3.rotateAngleX = hairPonytail4.rotateAngleX = 0.0F;
        
        //Start off by adjusting according to head angle
        hairPonytail1.rotateAngleX = hairPonytail2.rotateAngleX = headPitch * 0.017453292F * -0.5F;
        
        //Adjust more if in flying mode
        if(entityIn instanceof EntityFairy && ((EntityFairy)entityIn).getFlightMode()) hairPonytail1.rotateAngleX += Math.PI / 8.0F;
        
        //Swing back according to movement speed
        hairPonytail1.rotateAngleX += limbSwingAmount * 1.25F * (entityIn.onGround ? 1.0F : 0.75F) * angleAmount;
        
        //Swing up when falling
        hairPonytail1.rotateAngleX -= Math.min(0.0F, entityIn.motionY) * 1.0F;
        hairPonytail2.rotateAngleX -= Math.min(0.0F, entityIn.motionY) * 1.0F;
        hairPonytail3.rotateAngleX -= Math.min(0.0F, entityIn.motionY) * 0.75F;
        hairPonytail4.rotateAngleX -= Math.min(0.0F, entityIn.motionY) * 0.5F;
        
        //Add some sweet gyrations.
        hairPonytail1.rotateAngleX += MathHelper.cos(limbSwing) * limbSwingAmount * 0.5F * (entityIn.onGround ? 1.0F : 0.5F) * angleAmount;
        hairPonytail2.rotateAngleX += MathHelper.cos(limbSwing + ((float)Math.PI * 0.5F)) * limbSwingAmount * 0.75F * (entityIn.onGround ? 1.0F : 0.5F) * angleAmount;
        hairPonytail3.rotateAngleX += MathHelper.cos(limbSwing + ((float)Math.PI * 1.0F)) * limbSwingAmount * 1.0F * (entityIn.onGround ? 1.0F : 0.5F) * angleAmount;
        hairPonytail4.rotateAngleX += MathHelper.cos(limbSwing + ((float)Math.PI * 1.5F)) * limbSwingAmount * 1.25F * (entityIn.onGround ? 1.0F : 0.5F) * angleAmount;
	}
}
