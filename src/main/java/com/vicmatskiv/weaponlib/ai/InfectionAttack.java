package com.vicmatskiv.weaponlib.ai;

import static com.vicmatskiv.weaponlib.compatibility.CompatibilityProvider.compatibility;
import java.util.UUID;

import com.vicmatskiv.weaponlib.Spreadable;
import com.vicmatskiv.weaponlib.SpreadableExposure;
import com.vicmatskiv.weaponlib.compatibility.CompatibleExposureCapability;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

public class InfectionAttack implements CustomMobAttack, Spreadable {
    
    private static UUID uuid = UUID.randomUUID();
    
    private float dose;
    private long firstExposureImpactDelay;
    private float colorImpairmentR;
    private float colorImpairmentG;
    private float colorImpairmentB;
    
    public InfectionAttack(float dose, long firstExposureImpactDelay, 
            float colorImpairmentR, float colorImpairmentG, float colorImpairmentB) {
        this.dose = dose;
        this.firstExposureImpactDelay = firstExposureImpactDelay;
        this.colorImpairmentR = colorImpairmentR;
        this.colorImpairmentG = colorImpairmentG;
        this.colorImpairmentB = colorImpairmentB;
    }

    @Override
    public boolean attackEntity(EntityCustomMob entityMob, Entity target) {
        if(target instanceof EntityLivingBase) {
            SpreadableExposure exposure = CompatibleExposureCapability.getExposure(target, SpreadableExposure.class);
            if(exposure == null) {
                exposure = new SpreadableExposure(firstExposureImpactDelay, compatibility.world(entityMob).getTotalWorldTime())
                        .withColorImpairment(colorImpairmentR, colorImpairmentG, colorImpairmentB);
                CompatibleExposureCapability.updateExposure((EntityLivingBase) target, exposure);
            }
            
            exposure.apply(this, target, dose);
        }
        
        return false;
    }

    @Override
    public UUID getId() {
        return uuid;
    }

}
