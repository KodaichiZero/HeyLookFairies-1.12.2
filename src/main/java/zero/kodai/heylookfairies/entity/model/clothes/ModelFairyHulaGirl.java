package zero.kodai.heylookfairies.entity.model.clothes;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import zero.kodai.heylookfairies.entity.EntityFairy;
import zero.kodai.heylookfairies.entity.model.ModelFairyBase;

public class ModelFairyHulaGirl extends ModelFairyBase {
	
    public ModelRenderer tubeTop;
    public ModelRenderer skirtWaistband;
    public ModelRenderer skirtWaistbandTie1;
    public ModelRenderer skirtWaistbandTie2;
    public ModelRenderer[][] skirtFront;
    public ModelRenderer[][] skirtBack;
    public ModelRenderer[][] skirtLeft;
    public ModelRenderer[][] skirtRight;
	
	public ModelFairyHulaGirl() {
		this.renderStage = 0;
        this.textureWidth = 32;
        this.textureHeight = 32;
    
        this.tubeTop = new ModelRenderer(this, 0, 0);
        this.tubeTop.addBox(-2.0F, 1.0F, -1.0625F, 4, 2, 2, 0.25F);
        super.bodyBase.addChild(tubeTop);
        
        this.skirtWaistband = new ModelRenderer(this, 12, 0);
        this.skirtWaistband.addBox(-2.0F, 4.5F, -1.0F, 4, 1, 2, 0.125F);
        super.bodyBase.addChild(skirtWaistband);
        
        this.skirtWaistbandTie1 = new ModelRenderer(this, 12, 4);
        this.skirtWaistbandTie1.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
        this.skirtWaistbandTie1.setRotationPoint(-0.5F, 5.0F, 1.5F);
        this.skirtWaistbandTie1.rotateAngleX = -0.5F;
        this.skirtWaistbandTie1.rotateAngleZ = -0.3F;
        this.skirtWaistband.addChild(skirtWaistbandTie1);
        
        this.skirtWaistbandTie2 = new ModelRenderer(this, 16, 4);
        this.skirtWaistbandTie2.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
        this.skirtWaistbandTie2.setRotationPoint(0.5F, 5.0F, 1.5F);
        this.skirtWaistbandTie2.rotateAngleX = -0.5F;
        this.skirtWaistbandTie2.rotateAngleZ = 0.3F;
        this.skirtWaistband.addChild(skirtWaistbandTie2);
        
        //Defining the front of the skirt.
        int texX = 3;
        int texY = 7;
        this.skirtFront = new ModelRenderer[4][3];
        
        for(int i = 0; i < 4; i++) {
        	for(int j = 0; j < 3; j++) {
        		this.skirtFront[i][j] = new ModelRenderer(this, texX + i, texY + j);
        		this.skirtFront[i][j].addBox(-0.5F, 0.0F, 0.0F, 1, 1, 0, 0.01F);
        		if(j == 0) {
        			this.skirtFront[i][j].rotateAngleX = -(float)Math.PI / 8F;
        			this.skirtFront[i][j].setRotationPoint(-1.5F + (float)i, 5.0F, -1.125F);
        			this.skirtWaistband.addChild(this.skirtFront[i][j]);
        		} else {
        			this.skirtFront[i][j].setRotationPoint(0.0F, 1.0F, 0.0F);
        			this.skirtFront[i][j-1].addChild(this.skirtFront[i][j]);
        		}
        	}
        }
        
        //Defining the back of the skirt.
        texX = 8;
        texY = 7;
        this.skirtBack = new ModelRenderer[4][3];
        
        for(int i = 0; i < 4; i++) {
        	for(int j = 0; j < 3; j++) {
        		this.skirtBack[i][j] = new ModelRenderer(this, texX + i, texY + j);
        		this.skirtBack[i][j].addBox(-0.5F, 0.0F, 0.0F, 1, 1, 0, 0.01F);
        		if(j == 0) {
        			this.skirtBack[i][j].rotateAngleX = (float)Math.PI / 8F;
        			this.skirtBack[i][j].setRotationPoint(-1.5F + (float)i, 5.0F, 1.125F);
        			this.skirtWaistband.addChild(this.skirtBack[i][j]);
        		} else {
        			this.skirtBack[i][j].setRotationPoint(0.0F, 1.0F, 0.0F);
        			this.skirtBack[i][j-1].addChild(this.skirtBack[i][j]);
        		}
        	}
        }
        
        //Defining the left side of the skirt.
        texX = 1;
        texY = 7;
        this.skirtLeft = new ModelRenderer[2][3];
        
        for(int i = 0; i < 2; i++) {
        	for(int j = 0; j < 3; j++) {
        		this.skirtLeft[i][j] = new ModelRenderer(this, texX + i, texY + j);
        		this.skirtLeft[i][j].addBox(0.0F, 0.0F, -0.5F, 0, 1, 1, 0.01F);
        		if(j == 0) {
        			this.skirtLeft[i][j].rotateAngleZ = (float)Math.PI / 8F;
        			this.skirtLeft[i][j].setRotationPoint(-2.125F, 5.0F, -0.5F + (float)i);
        			this.skirtWaistband.addChild(this.skirtLeft[i][j]);
        		} else {
        			this.skirtLeft[i][j].setRotationPoint(0.0F, 1.0F, 0.0F);
        			this.skirtLeft[i][j-1].addChild(this.skirtLeft[i][j]);
        		}
        	}
        }
        
        //Defining the right side of the skirt.
        texX = 6;
        texY = 7;
        this.skirtRight = new ModelRenderer[2][3];
        
        for(int i = 0; i < 2; i++) {
        	for(int j = 0; j < 3; j++) {
        		this.skirtRight[i][j] = new ModelRenderer(this, texX + i, texY + j);
        		this.skirtRight[i][j].addBox(0.0F, 0.0F, -0.5F, 0, 1, 1, 0.01F);
        		if(j == 0) {
        			this.skirtRight[i][j].rotateAngleZ = -(float)Math.PI / 8F;
        			this.skirtRight[i][j].setRotationPoint(2.125F, 5.0F, -0.5F + (float)i);
        			this.skirtWaistband.addChild(this.skirtRight[i][j]);
        		} else {
        			this.skirtRight[i][j].setRotationPoint(0.0F, 1.0F, 0.0F);
        			this.skirtRight[i][j-1].addChild(this.skirtRight[i][j]);
        		}
        	}
        }
	}
	
	/**
	 * Override because we need to scale the model down before rendering it.
	 */
	@Override
	public void childRender(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		tubeTop.isHidden = (this.renderStage != 1);
		
		skirtWaistband.isHidden = (this.renderStage != 2);
		
		super.bodyBase.render(scale);
    }
	
	@Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
		super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
		
		if(!(entityIn instanceof EntityFairy)) {
			return;
		}
		
		EntityFairy fairy = (EntityFairy)entityIn;
		float skirtDisplacement = 0.0F;
		if(fairy.getFlightMode()) {
			skirtDisplacement = 0.65F;
		} else if(fairy.isShoulderRiding() || fairy.isRiding()) {
			skirtDisplacement = 1.25F;
		}
		
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 3; j ++) {
				this.skirtFront[i][j].rotateAngleX = (j == 0 ? -(0.375F + skirtDisplacement) : 0.0F) - MathHelper.cos(ageInTicks * 0.1F + (4.7F * (float)(j + i))) * 0.25F + (float)Math.min(0.0D, fairy.motionY);
			}
		}
		
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 3; j ++) {
				this.skirtBack[i][j].rotateAngleX = (j == 0 ? 0.375F : 0.0F) + MathHelper.cos(ageInTicks * 0.1F + (4.7F * (float)(j + i))) * 0.25F - (float)Math.min(0.0D, fairy.motionY);
			}
		}
		
		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < 3; j ++) {
				this.skirtLeft[i][j].rotateAngleZ = (j == 0 ? 0.375F : 0.0F) + MathHelper.cos(ageInTicks * 0.1F + (4.7F * (float)(j + i))) * 0.25F - (float)Math.min(0.0D, fairy.motionY);
			}
		}
		
		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < 3; j ++) {
				this.skirtRight[i][j].rotateAngleZ = (j == 0 ? -0.375F : 0.0F) - MathHelper.cos(ageInTicks * 0.1F + (4.7F * (float)(j + i))) * 0.25F + (float)Math.min(0.0D, fairy.motionY);
			}
		}
	}
}
