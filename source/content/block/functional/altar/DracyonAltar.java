package net.tslat.aoa3.content.block.functional.altar;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MaterialColor;
import net.tslat.aoa3.common.registration.worldgen.AoADimensions;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.util.WorldUtil;

public class DracyonAltar extends BossAltarBlock {
	public DracyonAltar() {
		super(MaterialColor.COLOR_BLUE);
	}

	@Override
	protected void doActivationEffect(Player player, InteractionHand hand, BlockState state, BlockPos blockPos) {
		/*DracyonEntity dracyon = new DracyonEntity(AoAMobs.DRACYON.get(), player.level);

		dracyon.moveTo(blockPos.getX(), blockPos.getY() + 3, blockPos.getZ(), 0, 0);
		player.level.addFreshEntity(dracyon);
		sendSpawnMessage(player, LocaleUtil.getLocaleMessage(AoAMobs.DRACYON.get().getDescriptionId() + ".spawn", player.getDisplayName()), blockPos);*/
	}

	@Override
	protected boolean checkActivationConditions(Player player, InteractionHand hand, BlockState state, BlockPos pos) {
		return WorldUtil.isWorld(player.level, AoADimensions.LBOREAN.key);
	}

	@Override
	protected Item getActivationItem() {
		return AoAItems.CALL_OF_THE_DRAKE.get();
	}
}
