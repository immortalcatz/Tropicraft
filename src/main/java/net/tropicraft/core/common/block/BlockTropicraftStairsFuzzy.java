package net.tropicraft.core.common.block;

import net.minecraft.block.BlockStairs;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;

public class BlockTropicraftStairsFuzzy extends BlockTropicraftStairs {

	public BlockTropicraftStairsFuzzy(IBlockState modelState) {
		super(modelState);
		this.setDefaultState(getDefaultState().withProperty(FACING, EnumFacing.EAST));
		this.useNeighborBrightness = true;
	}

	@Override
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT_MIPPED;
	}
}
