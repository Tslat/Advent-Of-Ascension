package net.tslat.aoa3.object.block.functional.altar;

import net.minecraft.block.BlockState;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.tslat.aoa3.common.registration.AoADimensions;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.object.entity.boss.VoxxulonEntity;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.WorldUtil;

public class VoxxulonAltar extends BossAltarBlock {
	public VoxxulonAltar() {
		super(MaterialColor.TERRACOTTA_GREEN);
	}

	@Override
	protected void doActivationEffect(PlayerEntity player, Hand hand, BlockState state, BlockPos blockPos) {
		VoxxulonEntity voxxulon = new VoxxulonEntity(AoAEntities.Mobs.VOXXULON.get(), player.level);

		voxxulon.moveTo(blockPos.getX(), blockPos.getY() + 3, blockPos.getZ(), 0, 0);
		player.level.addFreshEntity(voxxulon);
		sendSpawnMessage(player, LocaleUtil.getLocaleMessage(AoAEntities.Mobs.VOXXULON.get().getDescriptionId() + ".spawn", player.getDisplayName()), blockPos);
	}

	@Override
	protected boolean checkActivationConditions(PlayerEntity player, Hand hand, BlockState state, BlockPos pos) {
		return WorldUtil.isWorld(player.level, AoADimensions.VOX_PONDS.key);
	}

	@Override
	protected Item getActivationItem() {
		return AoAItems.VILE_STONE.get();
	}
}
