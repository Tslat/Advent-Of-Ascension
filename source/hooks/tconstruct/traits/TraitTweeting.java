package net.tslat.aoa3.hooks.tconstruct.traits;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.utils.ModUtil;
import slimeknights.tconstruct.library.traits.AbstractTrait;

public class TraitTweeting extends AbstractTrait {
	public TraitTweeting() {
		super("tweeting", 0xFFE500);
	}

	@Override
	public void onHit(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damage, boolean isCritical) {
		if (ModUtil.isClient())
			player.world.playSound((EntityPlayer)player, target.posX, target.posY, target.posZ, SoundsRegister.MOB_CHARGER_HIT, SoundCategory.PLAYERS, 1.0f, 1.0f);
	}

	@Override
	public void afterBlockBreak(ItemStack tool, World world, IBlockState state, BlockPos pos, EntityLivingBase player, boolean wasEffective) {
		if (ModUtil.isClient() && random.nextFloat() < 0.2f)
			player.world.playSound((EntityPlayer)player, pos.getX(), pos.getY(), pos.getZ(), SoundsRegister.MOB_CHARGER_HIT, SoundCategory.PLAYERS, 1.0f, 1.0f);
	}
}
