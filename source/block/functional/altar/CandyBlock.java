package net.tslat.aoa3.block.functional.altar;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.entity.boss.cottoncandor.EntityCottonCandor;
import net.tslat.aoa3.utils.StringUtil;

public class CandyBlock extends BossAltarBlock {
	public CandyBlock() {
		super("CandyBlock", "candy_block");
	}

	@Override
	protected void doActivationEffect(EntityPlayer player, EnumHand hand, IBlockState state, BlockPos blockPos) {
		EntityCottonCandor cottonCandor = new EntityCottonCandor(player.world);

		cottonCandor.setPositionAndUpdate(blockPos.getX() + 0.5, blockPos.up().getY(), blockPos.getZ() + 0.5);
		player.world.spawnEntity(cottonCandor);
		sendSpawnMessage(player, StringUtil.getLocaleWithArguments("message.mob.cottonCandor.spawn", player.getDisplayNameString()), blockPos);
	}

	@Override
	protected Item getActivationItem() {
		return ItemRegister.TREAT_BAG;
	}
}
