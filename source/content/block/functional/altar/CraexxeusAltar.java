package net.tslat.aoa3.content.block.functional.altar;

import net.minecraft.block.BlockState;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.tslat.aoa3.common.registration.AoADimensions;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.content.entity.boss.CraexxeusEntity;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.WorldUtil;

public class CraexxeusAltar extends BossAltarBlock {
	public CraexxeusAltar() {
		super(MaterialColor.COLOR_CYAN);
	}

	@Override
	protected void doActivationEffect(PlayerEntity player, Hand hand, BlockState state, BlockPos blockPos) {
		CraexxeusEntity craexxeus = new CraexxeusEntity(AoAEntities.Mobs.CRAEXXEUS.get(), player.level);

		int offsetX = player.getRandom().nextBoolean() ? -11 : 11;
		int offsetZ = player.getRandom().nextBoolean() ? -11 : 11;

		craexxeus.moveTo(blockPos.getX() + offsetX, blockPos.getY() + 5, blockPos.getZ() + offsetZ, 0, 0);
		player.level.addFreshEntity(craexxeus);
		sendSpawnMessage(player, LocaleUtil.getLocaleMessage(AoAEntities.Mobs.CRAEXXEUS.get().getDescriptionId() + ".spawn", player.getDisplayName()), blockPos);
	}

	@Override
	protected boolean checkActivationConditions(PlayerEntity player, Hand hand, BlockState state, BlockPos pos) {
		return WorldUtil.isWorld(player.level, AoADimensions.SHYRELANDS.key);
	}

	@Override
	protected Item getActivationItem() {
		return AoAItems.ANCIENT_RING.get();
	}
}
