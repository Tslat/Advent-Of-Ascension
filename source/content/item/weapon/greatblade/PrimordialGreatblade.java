package net.tslat.aoa3.content.item.weapon.greatblade;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.item.AoATiers;
import net.tslat.aoa3.util.LocaleUtil;

import javax.annotation.Nullable;
import java.util.List;

public class PrimordialGreatblade extends BaseGreatblade {
	public PrimordialGreatblade() {
		super(AoATiers.PRIMORDIAL_GREATBLADE);
	}

	@Override
	public double getDamageForAttack(LivingEntity target, LivingEntity attacker, ItemStack swordStack, double baseDamage) {
		float extraDmg;
		float maxHealth = target.getMaxHealth();

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

		return super.getDamageForAttack(target, attacker, swordStack, (float)baseDamage + extraDmg);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
	}
}
