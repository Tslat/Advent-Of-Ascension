package net.tslat.aoa3.content.item.weapon.greatblade;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.AoATiers;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.RandomUtil;

import javax.annotation.Nullable;
import java.util.List;

public class CrystalGreatblade extends BaseGreatblade {
	public CrystalGreatblade() {
		super(AoATiers.CRYSTAL_GREATBLADE);
	}

	@Override
	protected void doMeleeEffect(ItemStack stack, LivingEntity target, LivingEntity attacker, float attackCooldown) {
		for (LivingEntity entity : target.level.getEntitiesOfClass(LivingEntity.class, target.getBoundingBox().inflate(2.0f), EntityUtil.Predicates.HOSTILE_MOB)) {
			DamageUtil.dealAoeDamage(null, attacker, entity,  RandomUtil.randomValueUpTo(1.5f) * attackCooldown, false);
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
	}
}
