package net.tslat.aoa3.block.functional.altar;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.entity.boss.visualent.EntityVisualent;
import net.tslat.aoa3.utils.StringUtil;

public class VisualentAltar extends BossAltarBlock {
	public VisualentAltar() {
		super("VisualentAltar", "visualent_altar");
	}

	@Override
	protected void doActivationEffect(EntityPlayer player, EnumHand hand, IBlockState state, BlockPos blockPos) {
		EntityVisualent visualent = new EntityVisualent(player.world);

		visualent.setPositionAndUpdate(blockPos.getX() + 0.5, blockPos.up().getY(), blockPos.getZ() + 0.5);
		player.world.spawnEntity(visualent);
		sendSpawnMessage(player, StringUtil.getLocaleWithArguments("message.mob.visualent.spawn", player.getDisplayNameString()), blockPos);
	}

	@Override
	protected Item getActivationItem() {
		return ItemRegister.observingEye;
	}
}
