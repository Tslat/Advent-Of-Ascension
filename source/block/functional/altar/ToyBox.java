package net.tslat.aoa3.block.functional.altar;

import net.minecraft.block.BlockState;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.util.LocaleUtil;

public class ToyBox extends BossAltarBlock {
	public ToyBox() {
		super(MaterialColor.BLUE);
	}

	@Override
	protected void doActivationEffect(PlayerEntity player, Hand hand, BlockState state, BlockPos blockPos) {
		/*GyroEntity gyro = new GyroEntity(player.world);

		gyro.setLocationAndAngles(blockPos.getX(), blockPos.getY() + 3, blockPos.getZ(), 0, 0);
		player.world.addEntity(gyro);*/
		sendSpawnMessage(player, LocaleUtil.getLocaleMessage("message.mob.gyro.spawn", player.getDisplayName().getFormattedText()), blockPos);
	}

	@Override
	protected Item getActivationItem() {
		return AoAItems.TOY_GYROCOPTER.get();
	}
}
