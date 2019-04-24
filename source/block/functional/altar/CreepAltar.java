package net.tslat.aoa3.block.functional.altar;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.entity.boss.creep.EntityCreep;
import net.tslat.aoa3.utils.StringUtil;

public class CreepAltar extends BossAltarBlock {
	public CreepAltar() {
		super("CreepAltar", "creep_altar");
	}

	@Override
	protected void doActivationEffect(EntityPlayer player, EnumHand hand, IBlockState state, BlockPos blockPos) {
		EntityCreep creep = new EntityCreep(player.world);

		creep.setPositionAndUpdate(blockPos.getX() + 0.5, blockPos.up().getY(), blockPos.getZ() + 0.5);
		player.world.spawnEntity(creep);
		sendSpawnMessage(player, StringUtil.getLocaleWithArguments("message.mob.creep.spawn", player.getDisplayNameString()), blockPos);
	}

	@Override
	protected Item getActivationItem() {
		return ItemRegister.explosiveGems;
	}
}
