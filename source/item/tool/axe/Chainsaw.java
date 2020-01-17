package net.tslat.aoa3.item.tool.axe;

import net.minecraft.block.BlockLog;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.tslat.aoa3.common.registration.MaterialsRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;

public class Chainsaw extends BaseAxe {
	public Chainsaw() {
		super("Chainsaw", "chainsaw", MaterialsRegister.TOOL_CHAINSAW, -2f);
	}

	@Override
	public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack) {
		return true;
	}

	@Override
	public boolean onBlockStartBreak(ItemStack itemstack, BlockPos pos, EntityPlayer pl) {
		if (!pl.world.isRemote) {
			pl.world.playSound(null, pl.posX, pl.posY, pl.posZ, SoundsRegister.chainsawUse, SoundCategory.PLAYERS, 1.0f, 1.0f);

			if (pl.world.getBlockState(pos).getBlock() instanceof BlockLog)
				pl.addPotionEffect(new PotionEffect(MobEffects.HASTE, 10, 30, true, false));
		}

		return false;
	}

	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
		if (!attacker.world.isRemote) {
			attacker.world.playSound(null, attacker.posX, attacker.posY, attacker.posZ, SoundsRegister.chainsawUse, SoundCategory.PLAYERS, 1.0f, 1.0f);
			stack.damageItem(1, attacker);
		}

		return true;
	}

	@Override
	public boolean canDisableShield(ItemStack stack, ItemStack shield, EntityLivingBase entity, EntityLivingBase attacker) {
		return false;
	}

	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
		return EnumAction.NONE;
	}
}
