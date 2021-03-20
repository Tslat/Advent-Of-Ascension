package net.tslat.aoa3.item.weapon.greatblade;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effects;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.constant.AttackSpeed;

import javax.annotation.Nullable;
import java.util.List;

public class ShroomicGreatblade extends BaseGreatblade {
	public ShroomicGreatblade() {
		super(21.5f, AttackSpeed.GREATBLADE, 1300);
	}

	@Override
	protected double getDamageForAttack(ItemStack stack, Entity target, LivingEntity attacker, double baseDmg) {
		return (float)(attacker.hasEffect(Effects.POISON) ? getAttackDamage() * 1.3f : getAttackDamage());
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
	}
}
