package net.tslat.aoa3.block.functional.altar;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.entity.boss.fourguardians.EntityBlueGuardian;
import net.tslat.aoa3.entity.boss.fourguardians.EntityGreenGuardian;
import net.tslat.aoa3.entity.boss.fourguardians.EntityRedGuardian;
import net.tslat.aoa3.entity.boss.fourguardians.EntityYellowGuardian;
import net.tslat.aoa3.utils.StringUtil;

public class GuardianAltar extends BossAltarBlock {
	public GuardianAltar() {
		super("GuardianAltar", "guardian_altar");
	}

	@Override
	protected void doActivationEffect(EntityPlayer player, EnumHand hand, IBlockState state, BlockPos blockPos) {
		EntityBlueGuardian blueGuardian = new EntityBlueGuardian(player.world);
		EntityYellowGuardian yellowGuardian = new EntityYellowGuardian(player.world);
		EntityGreenGuardian greenGuardian = new EntityGreenGuardian(player.world);
		EntityRedGuardian redGuardian = new EntityRedGuardian(player.world);

		blueGuardian.setLocationAndAngles(blockPos.getX(), blockPos.getY() + 3, blockPos.getZ(), 0, 0);
		redGuardian.setLocationAndAngles(blockPos.getX(), blockPos.getY() + 3, blockPos.getZ(), 0, 0);
		yellowGuardian.setLocationAndAngles(blockPos.getX(), blockPos.getY() + 3, blockPos.getZ(), 0, 0);
		greenGuardian.setLocationAndAngles(blockPos.getX(), blockPos.getY() + 3, blockPos.getZ(), 0, 0);
		player.world.spawnEntity(blueGuardian);
		player.world.spawnEntity(greenGuardian);
		player.world.spawnEntity(yellowGuardian);
		player.world.spawnEntity(redGuardian);
		sendSpawnMessage(player, StringUtil.getLocaleWithArguments("message.mob.fourGuardians.spawn", player.getDisplayNameString()), blockPos);
	}

	@Override
	protected Item getActivationItem() {
		return ItemRegister.voliantHeart;
	}
}
