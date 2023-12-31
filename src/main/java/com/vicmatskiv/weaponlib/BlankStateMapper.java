package com.vicmatskiv.weaponlib;

import java.util.Collections;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.IStateMapper;

public class BlankStateMapper implements IStateMapper {
	
	public static final BlankStateMapper DEFAULT = new BlankStateMapper();

	@SuppressWarnings("unchecked")
	@Override
	public Map<IBlockState, ModelResourceLocation> putStateModelLocations(Block blockIn) {
		return Collections.EMPTY_MAP;
	}

}
