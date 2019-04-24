package net.tslat.aoa3.block.functional.altar;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.entity.boss.vinocorne.EntityVinocorne;
import net.tslat.aoa3.utils.StringUtil;

public class VinocorneShrine extends BossAltarBlock {
	public VinocorneShrine() {
		super("VinocorneShrine", "vinocorne_shrine");
	}

	@Override
	protected void doActivationEffect(EntityPlayer player, EnumHand hand, IBlockState state, BlockPos blockPos) {
		EntityVinocorne vinocorne = new EntityVinocorne(player.world);

		vinocorne.setLocationAndAngles(blockPos.getX(), blockPos.getY() + 3, blockPos.getZ(), 0, 0);
		player.world.spawnEntity(vinocorne);
		sendSpawnMessage(player, StringUtil.getLocaleWithArguments("message.mob.vinocorne.spawn", player.getDisplayNameString()), blockPos);
	}

	@Override
	protected Item getActivationItem() {
		return ItemRegister.petals;
	}
}
