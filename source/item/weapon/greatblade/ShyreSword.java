package net.tslat.aoa3.item.weapon.greatblade;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.WorldUtil;
import net.tslat.aoa3.util.misc.AttackSpeed;

import javax.annotation.Nullable;
import java.util.List;

public class ShyreSword extends BaseGreatblade {
	public ShyreSword() {
		super(26.0f, AttackSpeed.GREATBLADE, 2000);
	}

	@Override
	public double getDamageForAttack(LivingEntity target, LivingEntity attacker, ItemStack swordStack, double baseDamage) {
		double dmg = super.getDamageForAttack(target, attacker, swordStack, baseDamage);

		if (!(attacker.level instanceof IServerWorld))
			return dmg;

		return dmg - 4 + (WorldUtil.getLightLevel((IServerWorld)attacker.level, attacker.blockPosition(), false, false) / 15f * 9f);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
	}
}
