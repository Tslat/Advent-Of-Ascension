package net.tslat.aoa3.item.weapon.maul;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.tslat.aoa3.common.registration.AoAEnchantments;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.WorldUtil;
import net.tslat.aoa3.util.constant.AttackSpeed;
import net.tslat.aoa3.util.constant.Resources;
import net.tslat.aoa3.util.player.PlayerDataManager;
import net.tslat.aoa3.util.player.PlayerUtil;

import javax.annotation.Nullable;
import java.util.List;

public class ElectronMaul extends BaseMaul {
	public ElectronMaul() {
		super(25.0f, AttackSpeed.THIRD, 3.5D, 1500);
	}

	@Override
	protected void doMeleeEffect(ItemStack stack, PlayerEntity attacker, Entity target, float finalDmg, float attackCooldown) {
		if (attacker instanceof ServerPlayerEntity) {
			final float crushMod = 1 + 0.15f * EnchantmentHelper.getItemEnchantmentLevel(AoAEnchantments.CRUSH.get(), stack);
			PlayerDataManager.PlayerStats plStats = PlayerUtil.getAdventPlayer((ServerPlayerEntity)attacker).stats();
			final float energyMultiplier = 1 + (plStats.getResourceValue(Resources.ENERGY) / 100f);

			if (plStats.consumeResource(Resources.ENERGY, plStats.getResourceValue(Resources.ENERGY), false)) {
				DamageUtil.doScaledKnockback((LivingEntity)target, attacker, (float)knockback * crushMod * attackCooldown * energyMultiplier, attacker.getX() - target.getX(), attacker.getZ() - target.getZ());

				if (energyMultiplier >= 2.33)
					WorldUtil.spawnLightning((ServerWorld)attacker.level, (ServerPlayerEntity)attacker, target.getX(), target.getY(), target.getZ(), false);
			}
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.appendHoverText(stack, worldIn, tooltip, flagIn);
	}
}
