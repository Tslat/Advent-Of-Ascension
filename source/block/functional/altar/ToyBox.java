package net.tslat.aoa3.block.functional.altar;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.entity.boss.gyro.EntityGyro;
import net.tslat.aoa3.utils.StringUtil;

public class ToyBox extends BossAltarBlock {
	public ToyBox() {
		super("ToyBox", "toy_box");
	}

	@Override
	protected void doActivationEffect(EntityPlayer player, EnumHand hand, IBlockState state, BlockPos blockPos) {
		EntityGyro gyro = new EntityGyro(player.world);

		gyro.setLocationAndAngles(blockPos.getX(), blockPos.getY() + 3, blockPos.getZ(), 0, 0);
		player.world.spawnEntity(gyro);
		sendSpawnMessage(player, StringUtil.getLocaleWithArguments("message.mob.gyro.spawn", player.getDisplayNameString()), blockPos);
	}

	@Override
	protected Item getActivationItem() {
		return ItemRegister.toyGyrocopter;
	}
}
