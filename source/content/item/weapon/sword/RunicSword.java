package net.tslat.aoa3.content.item.weapon.sword;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.AoATags;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.common.registration.item.AoATiers;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.smartbrainlib.util.RandomUtil;

import javax.annotation.Nullable;
import java.util.List;

public class RunicSword extends BaseSword {
	public RunicSword() {
		super(AoATiers.RUNIC);
	}

	@Override
	public float getDamageForAttack(LivingEntity target, LivingEntity attacker, ItemStack swordStack, float baseDamage) {
		if (baseDamage / getDamage() > 0.75f) {
			ItemStack offhandStack = attacker.getOffhandItem();

			if (offhandStack.getItem() == AoAItems.FIRE_RUNE.get() && offhandStack.getCount() >= 5)
				target.setSecondsOnFire(5);
		}

		return super.getDamageForAttack(target, attacker, swordStack, baseDamage);
	}

	@Override
	protected void doMeleeEffect(ItemStack stack, LivingEntity target, LivingEntity attacker, float attackCooldown) {
		if (!attacker.level.isClientSide && attackCooldown > 0.75) {
			ItemStack offhandStack = attacker.getOffhandItem();

			if (offhandStack.is(AoATags.Items.ADVENT_RUNE) && offhandStack.getCount() >= 5) {
				Item rune = offhandStack.getItem();

				if (rune == AoAItems.POISON_RUNE.get()) {
					target.addEffect(new MobEffectInstance(MobEffects.POISON, 72, 1, false, true));
				}
				else if (rune == AoAItems.WITHER_RUNE.get()) {
					target.addEffect(new MobEffectInstance(MobEffects.WITHER, 40, 2, false, true));
				}
				else if (rune == AoAItems.WIND_RUNE.get()) {
					DamageUtil.doScaledKnockback(target, attacker, 0.5f, 1, 1, 1);
				}
				else if (rune == AoAItems.WATER_RUNE.get()) {
					target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 60, 0, false, true));
				}
				else if (rune == AoAItems.CHARGED_RUNE.get()) {
					((ServerLevel)target.level).sendParticles(ParticleTypes.ANGRY_VILLAGER, target.getX() + (RandomUtil.randomValueUpTo(1) * target.getBbWidth() * 2f) - target.getBbWidth(), target.getY() + 1 + (RandomUtil.randomValueUpTo(1) * target.getBbHeight()), target.getZ() + (RandomUtil.randomValueUpTo(1) * target.getBbWidth() * 2f) - target.getBbWidth(), 3, 0, 0, 0, (double)0);
				}
				else if (rune != AoAItems.FIRE_RUNE.get()) {
					return;
				}

				if (attacker instanceof Player pl && !pl.getAbilities().instabuild) {
					offhandStack.shrink(5);
					ItemUtil.damageItem(stack, attacker, 1, EquipmentSlot.MAINHAND);
				}
			}
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.HARMFUL, 2));
	}
}
