package net.tslat.aoa3.content.item.weapon.sword;

import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.smartbrainlib.util.BrainUtils;
import net.tslat.tme.api.util.RandomUtil;
import net.tslat.tme.api.object.builder.EffectBuilder;
import net.tslat.tme.api.util.EntityRetrievalUtil;

import java.util.List;

public class IllusionSword extends AoASword {
	public IllusionSword(Tier tier, Item.Properties properties) {
		super(tier, properties);
	}

	@Override
	protected void doMeleeEffect(ItemStack stack, LivingEntity target, LivingEntity attacker, float attackCooldown) {
		if (!attacker.level().isClientSide && !EntityUtil.isImmuneToSpecialAttacks(target) && RandomUtil.percentChance(0.1f * attackCooldown)) {
			List<LivingEntity> nearbyMobs = EntityRetrievalUtil.getEntities(target, 5, LivingEntity.class, nearby -> EntityUtil.areProbablyEnemies(nearby, attacker));

			if (nearbyMobs.size() > 1) {
				EffectBuilder builder = new EffectBuilder(MobEffects.BLINDNESS, 60);
				LivingEntity newTarget = null;

				for (LivingEntity nearbyMob : nearbyMobs) {
					if ((newTarget = nearbyMob) != target)
						break;
				}

				if (newTarget == null)
					return;

				target.setLastHurtByMob(newTarget);
				BrainUtils.setTargetOfEntity(target, newTarget);

				EntityUtil.applyPotions(target, attacker, builder);
			}
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag tooltipFlag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
	}
}
