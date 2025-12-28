package net.tslat.aoa3.content.item.weapon.sword;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.tslat.aoa3.common.registration.AoATags;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.tme.api.util.RandomUtil;
import net.tslat.tme.api.object.builder.EffectBuilder;

import java.util.List;

public class RunicSword extends AoASword {
	public RunicSword(Tier tier, Item.Properties properties) {
		super(tier, properties);
	}

	@Override
	public float getDamageForAttack(LivingEntity target, LivingEntity attacker, ItemStack swordStack, DamageSource source, float baseDamage) {
		if (baseDamage / getBaseDamage(swordStack) > 0.75f) {
			ItemStack offhandStack = attacker.getOffhandItem();

			if (offhandStack.getItem() == AoAItems.FIRE_RUNE.get() && offhandStack.getCount() >= 5)
				target.igniteForSeconds(5);
		}

		return super.getDamageForAttack(target, attacker, swordStack, source, baseDamage);
	}

	@Override
	protected void doMeleeEffect(ItemStack stack, LivingEntity target, LivingEntity attacker, float attackCooldown) {
		if (!attacker.level().isClientSide && attackCooldown > 0.75) {
			ItemStack offhandStack = attacker.getOffhandItem();

			if (offhandStack.is(AoATags.Items.ADVENT_RUNE) && offhandStack.getCount() >= 5) {
				Item rune = offhandStack.getItem();

				if (rune == AoAItems.POISON_RUNE.get()) {
					EntityUtil.applyPotions(target, attacker, new EffectBuilder(MobEffects.POISON, 72).level(2));
				}
				else if (rune == AoAItems.WITHER_RUNE.get()) {
					EntityUtil.applyPotions(target, attacker, new EffectBuilder(MobEffects.WITHER, 40).level(3));
				}
				else if (rune == AoAItems.WIND_RUNE.get()) {
					DamageUtil.doScaledKnockback(target, attacker, 0.5f, 1, 1, 1);
				}
				else if (rune == AoAItems.WATER_RUNE.get()) {
					EntityUtil.applyPotions(target, attacker, new EffectBuilder(MobEffects.MOVEMENT_SLOWDOWN, 60));
				}
				else if (rune == AoAItems.CHARGED_RUNE.get()) {
					((ServerLevel)target.level()).sendParticles(ParticleTypes.ANGRY_VILLAGER, target.getX() + (RandomUtil.valueUpTo(1) * target.getBbWidth() * 2f) - target.getBbWidth(), target.getY() + 1 + (RandomUtil.valueUpTo(1) * target.getBbHeight()), target.getZ() + (RandomUtil.valueUpTo(1) * target.getBbWidth() * 2f) - target.getBbWidth(), 3, 0, 0, 0, (double)0);
				}
				else if (rune != AoAItems.FIRE_RUNE.get()) {
					return;
				}

				if (attacker instanceof Player pl && !pl.getAbilities().instabuild) {
					offhandStack.shrink(5);
					ItemUtil.damageItemForUser(pl, stack, EquipmentSlot.MAINHAND);
				}
			}
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag tooltipFlag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.HARMFUL, 2));
	}
}
