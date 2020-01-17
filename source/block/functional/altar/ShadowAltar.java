package net.tslat.aoa3.block.functional.altar;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.library.scheduling.async.ShadowlordSpawnTask;
import net.tslat.aoa3.utils.ItemUtil;
import net.tslat.aoa3.utils.StringUtil;

import java.util.concurrent.TimeUnit;

public class ShadowAltar extends BossAltarBlock {
	public ShadowAltar() {
		super("ShadowAltar", "shadow_altar");
	}

	@Override
	protected void doActivationEffect(EntityPlayer player, EnumHand hand, IBlockState state, BlockPos blockPos) {
		if (!player.world.isRemote) {
			new ShadowlordSpawnTask(player.world, blockPos).schedule(1, TimeUnit.SECONDS);
			sendSpawnMessage(player, StringUtil.getLocaleWithArguments("message.mob.shadowlord.spawn", player.getDisplayNameString()), blockPos);

			if (player.isPotionActive(MobEffects.NIGHT_VISION) && ItemUtil.consumeItem(player, new ItemStack(ItemRegister.realmstoneBlank)))
				ItemUtil.givePlayerItemOrDrop(player, new ItemStack(ItemRegister.realmstoneDustopia));
		}
	}

	@Override
	protected Item getActivationItem() {
		return ItemRegister.bookOfShadows;
	}
}
