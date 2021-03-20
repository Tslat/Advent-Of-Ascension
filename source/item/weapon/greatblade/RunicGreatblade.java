package net.tslat.aoa3.item.weapon.greatblade;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.constant.AttackSpeed;

import javax.annotation.Nullable;
import java.util.List;

public class RunicGreatblade extends BaseGreatblade {
	public RunicGreatblade() {
		super(24.5f, AttackSpeed.GREATBLADE, 1800);
	}

	@Override
	public boolean hitEntity(ItemStack stack, Entity target, LivingEntity attacker, float dmg) {
		boolean ready = target.invulnerableTime <= 0;
		boolean successful = super.hitEntity(stack, target, attacker, (float)getAttackDamage() - 4);

		if (!target.level.isClientSide && ready)
			DamageUtil.dealMagicDamage(null, attacker, target, 4 * EntityUtil.getAttackCooldown(attacker), false);

		return successful;
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
	}
}
