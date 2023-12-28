package net.tslat.aoa3.content.item.weapon.maul;

import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.library.constant.AttackSpeed;
import net.tslat.aoa3.util.LocaleUtil;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CrystalMaul extends BaseMaul {
	public CrystalMaul() {
		super(23.5f, AttackSpeed.THIRD, 7.2f, 1400);
	}

	@Override
	protected void doMeleeEffect(ItemStack stack, Entity target, LivingEntity attacker, float attackCooldown) {
		if (target instanceof LivingEntity)
			((LivingEntity)target).addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 40, 1, true, true));
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Keys.SLOWS_TARGETS, LocaleUtil.ItemDescriptionType.BENEFICIAL));
		super.appendHoverText(stack, worldIn, tooltip, flagIn);
	}
}
