package com.vicmatskiv.weaponlib;

import net.minecraft.entity.EntityLivingBase;

public interface PlayerContext {

	public EntityLivingBase getPlayer();
	
	public void setPlayer(EntityLivingBase player);
}
