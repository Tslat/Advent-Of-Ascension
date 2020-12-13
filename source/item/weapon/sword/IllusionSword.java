package net.tslat.aoa3.item.weapon.sword;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.RandomUtil;
import net.tslat.aoa3.util.constant.AttackSpeed;

import javax.annotation.Nullable;
import java.util.List;

public class IllusionSword extends BaseSword {
	public IllusionSword() { // TODO look into false-swipe attacking
		super(ItemUtil.customItemTier(1900, AttackSpeed.NORMAL, 14.5f, 4, 10, null));
	}

	@Override
	protected void doMeleeEffect(ItemStack stack, LivingEntity target, LivingEntity attacker, float attackCooldown) {
		if (!attacker.world.isRemote && !EntityUtil.isImmuneToSpecialAttacks(target, attacker) && RandomUtil.percentChance(0.1f * attackCooldown)) {
			List<LivingEntity> nearbyMobs = target.world.getEntitiesWithinAABB(LivingEntity.class, target.getBoundingBox().grow(5), EntityUtil.Predicates.HOSTILE_MOB);

			if (nearbyMobs.size() > 1) {
				LivingEntity newTarget = null;

				for (LivingEntity nearbyMob : nearbyMobs) {
					if ((newTarget = nearbyMob) != target)
						break;
				}

				if (newTarget == null)
					return;

				target.setRevengeTarget(newTarget);

				if (target instanceof CreatureEntity)
					((CreatureEntity)target).setAttackTarget(newTarget);

				target.addPotionEffect(new EffectInstance(Effects.BLINDNESS, 60, 0, false, true));
			}
		}
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
	}
}
