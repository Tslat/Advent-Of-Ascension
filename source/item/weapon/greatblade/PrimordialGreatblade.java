package net.tslat.aoa3.item.weapon.greatblade;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.misc.AttackSpeed;

import javax.annotation.Nullable;
import java.util.List;

public class PrimordialGreatblade extends BaseGreatblade {
	public PrimordialGreatblade() {
		super(25.5f, AttackSpeed.GREATBLADE, 1900);
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
	public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
	}
}
