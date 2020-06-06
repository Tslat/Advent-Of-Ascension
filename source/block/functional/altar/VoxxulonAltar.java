package net.tslat.aoa3.block.functional.altar;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.entity.boss.voxxulon.EntityVoxxulon;
import net.tslat.aoa3.utils.StringUtil;

public class VoxxulonAltar extends BossAltarBlock {
	public VoxxulonAltar() {
		super("VoxxulonAltar", "voxxulon_altar");
	}

	@Override
	protected void doActivationEffect(EntityPlayer player, EnumHand hand, IBlockState state, BlockPos blockPos) {
		EntityVoxxulon voxxulon = new EntityVoxxulon(player.world);

		voxxulon.setLocationAndAngles(blockPos.getX(), blockPos.getY() + 3, blockPos.getZ(), 0, 0);
		player.world.spawnEntity(voxxulon);
		sendSpawnMessage(player, StringUtil.getLocaleWithArguments("message.mob.voxxulon.spawn", player.getDisplayNameString()), blockPos);
	}

	@Override
	protected Item getActivationItem() {
		return ItemRegister.VILE_STONE;
	}
}
