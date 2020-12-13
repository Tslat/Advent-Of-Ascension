package net.tslat.aoa3.block.functional.altar;

import net.minecraft.block.BlockState;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.entity.boss.CottonCandorEntity;
import net.tslat.aoa3.util.LocaleUtil;

public class CandyBlock extends BossAltarBlock {
	public CandyBlock() {
		super(MaterialColor.PINK);
	}

	@Override
	protected void doActivationEffect(PlayerEntity player, Hand hand, BlockState state, BlockPos blockPos) {
		CottonCandorEntity cottonCandor = new CottonCandorEntity(AoAEntities.Mobs.COTTON_CANDOR.get(), player.world);

		cottonCandor.setPositionAndUpdate(blockPos.getX() + 0.5, blockPos.up().getY(), blockPos.getZ() + 0.5);
		player.world.addEntity(cottonCandor);
		sendSpawnMessage(player, LocaleUtil.getLocaleMessage("message.mob.cottonCandor.spawn", player.getDisplayName().getFormattedText()), blockPos);
	}

	@Override
	protected Item getActivationItem() {
		return AoAItems.TREAT_BAG.get();
	}
}
