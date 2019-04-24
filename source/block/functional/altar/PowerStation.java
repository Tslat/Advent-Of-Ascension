package net.tslat.aoa3.block.functional.altar;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.entity.boss.crystocore.EntityCrystocore;
import net.tslat.aoa3.utils.StringUtil;

public class PowerStation extends BossAltarBlock {
	public PowerStation() {
		super("PowerStation", "power_station");
	}

	@Override
	protected void doActivationEffect(EntityPlayer player, EnumHand hand, IBlockState state, BlockPos blockPos) {
		EntityCrystocore crystocore = new EntityCrystocore(player.world);

		crystocore.setLocationAndAngles(blockPos.getX(), blockPos.getY() + 3, blockPos.getZ(), 0, 0);
		player.world.spawnEntity(crystocore);
		sendSpawnMessage(player, StringUtil.getLocaleWithArguments("message.mob.crystocore.spawn", player.getDisplayNameString()), blockPos);
	}

	@Override
	protected Item getActivationItem() {
		return ItemRegister.giantCrystal;
	}
}
