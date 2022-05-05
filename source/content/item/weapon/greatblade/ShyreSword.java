package net.tslat.aoa3.content.item.weapon.greatblade;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.tslat.aoa3.common.registration.AoATiers;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.WorldUtil;

import javax.annotation.Nullable;
import java.util.List;

public class ShyreSword extends BaseGreatblade {
	public ShyreSword() {
		super(AoATiers.SHYRE_SWORD_GREATBLADE);
	}

	@Override
	public double getDamageForAttack(LivingEntity target, LivingEntity attacker, ItemStack swordStack, double baseDamage) {
		double dmg = super.getDamageForAttack(target, attacker, swordStack, baseDamage);

		if (!(attacker.level instanceof ServerLevelAccessor))
			return dmg;

		return dmg - 4 + (WorldUtil.getLightLevel((ServerLevelAccessor)attacker.level, attacker.blockPosition(), false, false) / 15f * 9f);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
	}
}
