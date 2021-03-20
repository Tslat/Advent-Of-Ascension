package net.tslat.aoa3.block.functional.utility;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoADimensions;
import net.tslat.aoa3.util.BlockUtil;
import net.tslat.aoa3.util.WorldUtil;
import net.tslat.aoa3.util.constant.Deities;
import net.tslat.aoa3.util.player.PlayerDataManager;
import net.tslat.aoa3.util.player.PlayerUtil;

public class AncientCavernShrine extends Block {
	private final Deities deity;

	public AncientCavernShrine(Deities deity) {
		super(BlockUtil.generateBlockProperties(Material.STONE, MaterialColor.COLOR_GRAY, BlockUtil.UNBREAKABLE_HARDNESS, BlockUtil.UNBREAKABLE_RESISTANCE, SoundType.STONE));

		this.deity = deity;
	}

	@Override
	public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
		if (player instanceof ServerPlayerEntity && !player.isShiftKeyDown() && WorldUtil.isWorld(world, AoADimensions.ANCIENT_CAVERN.key)) {
			PlayerDataManager plData = PlayerUtil.getAdventPlayer((ServerPlayerEntity)player);
			BlockPos teleportPos = new BlockPos(0, 17, 0);

			player.teleportTo(teleportPos.getX(), teleportPos.getY(), teleportPos.getZ());
		}

		return ActionResultType.SUCCESS;
	}
}
