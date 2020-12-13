package net.tslat.aoa3.item.weapon.greatblade;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.constant.AttackSpeed;

import javax.annotation.Nullable;
import java.util.List;

public class PrimordialGreatblade extends BaseGreatblade {
	public PrimordialGreatblade() {
		super(25.5f, AttackSpeed.GREATBLADE, 1900);
	}

	@Override
	protected double getDamageForAttack(ItemStack stack, Entity target, LivingEntity attacker, double baseDmg) {
		if (!(target instanceof LivingEntity))
			return super.getDamageForAttack(stack, target, attacker, baseDmg);

		float extraDmg = 0;
		LivingEntity livingTarget = (LivingEntity)target;
		float maxHealth = livingTarget.getMaxHealth();

		if (maxHealth <= 100) {
			extraDmg = maxHealth / 50f;
		}
		else if (maxHealth <= 300) {
			extraDmg = maxHealth / 100f;
		}
		else if (maxHealth <= 1000) {
			extraDmg = maxHealth / 250f;
		}
		else {
			extraDmg = Math.min(maxHealth / 300f, 5);
		}

		return (float)baseDmg + extraDmg;
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
	}
}
