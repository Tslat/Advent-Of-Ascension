package net.tslat.aoa3.block.functional.altar;

import net.minecraft.block.BlockState;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.entity.boss.SilverfootEntity;
import net.tslat.aoa3.util.LocaleUtil;

public class SilverfootAltar extends BossAltarBlock {
	public SilverfootAltar() {
		super(MaterialColor.LIGHT_GRAY);
	}

	@Override
	protected void doActivationEffect(PlayerEntity player, Hand hand, BlockState state, BlockPos blockPos) {
		SilverfootEntity silverfoot = new SilverfootEntity(AoAEntities.Mobs.SILVERFOOT.get(), player.world);

		silverfoot.setLocationAndAngles(blockPos.getX() + 0.5, blockPos.getY() + 1, blockPos.getZ() + 0.5, -90, 0);
		player.world.addEntity(silverfoot);
		sendSpawnMessage(player, LocaleUtil.getLocaleMessage("message.mob.silverfoot.spawn", player.getDisplayName().getFormattedText()), blockPos);
	}

	@Override
	protected Item getActivationItem() {
		return AoAItems.SILVRO_COIN.get();
	}
}
