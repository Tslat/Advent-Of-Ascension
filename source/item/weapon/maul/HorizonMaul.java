package net.tslat.aoa3.item.weapon.maul;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.constant.AttackSpeed;

import javax.annotation.Nullable;
import java.util.List;

public class HorizonMaul extends BaseMaul {
	public HorizonMaul() {
		super(23.0f, AttackSpeed.THIRD, 3.0D, 1300);
	}

	@Override
	protected void doMeleeEffect(ItemStack stack, PlayerEntity attacker, Entity target, float finalDmg, float attackCooldown) {
		if (target instanceof LivingEntity && attackCooldown == 1f && !EntityUtil.isImmuneToSpecialAttacks(target, attacker))
			((LivingEntity)target).addEffect(new EffectInstance(Effects.LEVITATION, 35, 0, true, true));
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.appendHoverText(stack, worldIn, tooltip, flagIn);
	}
}
