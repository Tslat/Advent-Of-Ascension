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

public class VoxxulonAltar extends BossAltarBlock {
	public VoxxulonAltar() {
		super(MaterialColor.TERRACOTTA_GREEN);
	}

	@Override
	protected void doActivationEffect(Player player, InteractionHand hand, BlockState state, BlockPos blockPos) {
		/*VoxxulonEntity voxxulon = new VoxxulonEntity(AoAMobs.VOXXULON.get(), player.level);

		voxxulon.moveTo(blockPos.getX(), blockPos.getY() + 3, blockPos.getZ(), 0, 0);
		player.level.addFreshEntity(voxxulon);
		sendSpawnMessage(player, LocaleUtil.getLocaleMessage(AoAMobs.VOXXULON.get().getDescriptionId() + ".spawn", player.getDisplayName()), blockPos);*/
	}

	@Override
	protected boolean checkActivationConditions(Player player, InteractionHand hand, BlockState state, BlockPos pos) {
		return WorldUtil.isWorld(player.level, AoADimensions.VOX_PONDS.key);
	}

	@Override
	protected Item getActivationItem() {
		return AoAItems.VILE_STONE.get();
	}
}
