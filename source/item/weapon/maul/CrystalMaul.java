package net.tslat.aoa3.item.weapon.maul;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.misc.AttackSpeed;

import javax.annotation.Nullable;
import java.util.List;

public class CrystalMaul extends BaseMaul {
	public CrystalMaul() {
		super(23.5f, AttackSpeed.THIRD, 7.2f, 1400);
	}

	@Override
	protected void doMeleeEffect(ItemStack stack, Entity target, LivingEntity attacker, float attackCooldown) {
		if (target instanceof LivingEntity)
			((LivingEntity)target).addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 40, 1, true, true));
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Constants.SLOWS_TARGETS, LocaleUtil.ItemDescriptionType.BENEFICIAL));
		super.appendHoverText(stack, worldIn, tooltip, flagIn);
	}
}
