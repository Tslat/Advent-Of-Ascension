package net.tslat.aoa3.item.weapon.greatblade;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.misc.AttackSpeed;

import javax.annotation.Nullable;
import java.util.List;

public class UnderworldGreatblade extends BaseGreatblade {
	public UnderworldGreatblade() {
		super(18.5f, AttackSpeed.GREATBLADE, 1050);
	}

	@Override
	protected void doMeleeEffect(ItemStack stack, LivingEntity target, LivingEntity attacker, float attackCooldown) {
		if (target.getMobType() == CreatureAttribute.UNDEAD)
			DamageUtil.dealMeleeDamage(attacker, target, 5 * attackCooldown, false);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
	}
}
