package net.tslat.aoa3.content.item.weapon.sword;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.component.Tool;
import net.tslat.aoa3.common.registration.item.AoADataComponents;
import net.tslat.aoa3.library.object.extension.MutableFloat;

public class AoASword extends SwordItem {
	public AoASword(Tier tier, Item.Properties properties) {
		super(tier, properties.component(AoADataComponents.MELEE_SWING_STRENGTH, new MutableFloat(1f)));
	}

	public AoASword(Tier tier, Item.Properties properties, Tool toolComponent) {
		super(tier, properties.component(AoADataComponents.MELEE_SWING_STRENGTH, new MutableFloat(1f)), toolComponent);
	}

	public float getBaseDamage(ItemStack stack) {
		return (float)stack.getOrDefault(DataComponents.ATTRIBUTE_MODIFIERS, ItemAttributeModifiers.EMPTY).compute(0, EquipmentSlot.MAINHAND);
	}

	public float getDamageForAttack(LivingEntity target, LivingEntity attacker, ItemStack swordStack, DamageSource source, float baseDamage) {
		return baseDamage;
	}

	@Override
	public boolean onLeftClickEntity(ItemStack stack, Player player, Entity entity) {
		stack.get(AoADataComponents.MELEE_SWING_STRENGTH).setValue(player.getAttackStrengthScale(0));

		return false;
	}

	@Override
	public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		doMeleeEffect(stack, target, attacker, getSwingEffectiveness(stack));

		return super.hurtEnemy(stack, target, attacker);
	}

	protected void doMeleeEffect(ItemStack stack, LivingEntity target, LivingEntity attacker, float attackCooldown) {}

	protected static float getSwingEffectiveness(ItemStack stack) {
		return stack.getOrDefault(AoADataComponents.MELEE_SWING_STRENGTH, new MutableFloat(1f)).floatValue();
	}

	protected static boolean isCriticalHit(LivingEntity attacker, Entity target, float attackStrengthScale) {
		return attacker instanceof Player pl
				&& attackStrengthScale > 0.848f
				&& pl.fallDistance > 0
				&& !pl.onGround()
				&& !pl.onClimbable()
				&& !pl.isInWater()
				&& !pl.hasEffect(MobEffects.BLINDNESS)
				&& !pl.isPassenger()
				&& target instanceof LivingEntity
				&& !pl.isSprinting();
	}

	public static Item.Properties baseProperties(Tier tier) {
		return baseProperties(tier, tier.getSpeed());
	}

	public static Item.Properties baseProperties(Tier tier, float attackSpeed) {
		return baseProperties(tier, 0, attackSpeed);
	}

	public static Item.Properties baseProperties(Tier tier, float attackDamageMod, float attackSpeed) {
		return new Item.Properties().attributes(createAttributes(tier, attackDamageMod, attackSpeed));
	}

	public static ItemAttributeModifiers createAttributes(Tier tier, float attackSpeed) {
		return createAttributes(tier, 0, attackSpeed);
	}
}

