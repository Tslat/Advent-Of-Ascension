package net.tslat.aoa3.block.generation.special;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.tslat.aoa3.block.functional.altar.BossAltarBlock;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.entity.boss.hydrolisk.EntityHydrolisk;
import net.tslat.aoa3.utils.StringUtil;

public class HydroTable extends BossAltarBlock {
	public HydroTable() {
		super("HydroTable", "hydro_table");
	}

	@Override
	protected void doActivationEffect(EntityPlayer player, EnumHand hand, IBlockState state, BlockPos blockPos) {
		EntityHydrolisk hydrolisk = new EntityHydrolisk(player.world);

		hydrolisk.setLocationAndAngles(blockPos.getX(), blockPos.getY() + 3, blockPos.getZ(), 0, 0);
		player.world.spawnEntity(hydrolisk);
		sendSpawnMessage(player, StringUtil.getLocaleWithArguments("message.mob.hydrolisk.spawn", player.getDisplayNameString()), blockPos);
	}

	@Override
	protected Item getActivationItem() {
		return ItemRegister.pureWaterStone;
	}
}
