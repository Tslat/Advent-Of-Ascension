package net.tslat.aoa3.content.block.functional.utility;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.tslat.aoa3.common.registration.worldgen.AoADimensions;
import net.tslat.aoa3.content.item.misc.summoning.BossSpawningItem;
import net.tslat.aoa3.util.BlockUtil;
import net.tslat.aoa3.util.WorldUtil;

public class BossAltar extends Block {
	private static final VoxelShape SHAPE = BlockUtil.pixelBasedCube(4, 0, 4, 12, 12, 12);

	public BossAltar() {
		super(new BlockUtil.CompactProperties(Material.HEAVY_METAL, MaterialColor.COLOR_BLACK).unbreakable().light(2).emissive().noOcclusion().get());
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		return SHAPE;
	}

	@Override
	public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
		if (!WorldUtil.isWorld(level, AoADimensions.NOWHERE.key))
			return InteractionResult.PASS;

		ItemStack heldItem = player.getItemInHand(hand);

		if (!(heldItem.getItem() instanceof BossSpawningItem bossItem))
			return InteractionResult.FAIL;

		return InteractionResult.SUCCESS;
	}
}
