package net.tslat.aoa3.content.item.weapon.sword;

import com.google.common.base.Suppliers;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.ImmutableSetMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.tslat.aoa3.common.registration.AoADataAttachments;
import net.tslat.aoa3.library.constant.AttackSpeed;

import java.util.Map;
import java.util.function.Supplier;

public class BaseSword extends SwordItem {
	private final Supplier<ImmutableSetMultimap<Attribute, AttributeModifier>> attributeModifiers;

	protected final float dmg;
	protected final double speed;

	public BaseSword(Tier tier) {
		this(tier, 0, AttackSpeed.SWORD);
	}

	public BaseSword(Tier itemStats, Item.Properties properties) {
		this(itemStats, 0, AttackSpeed.SWORD, properties);
	}

	public BaseSword(Tier tier, int damageMod, float attackSpeed) {
		this(tier, damageMod, attackSpeed, new Item.Properties().durability(tier.getUses()));
	}

	public BaseSword(Tier tier, int damageMod, float attackSpeed, Item.Properties properties) {
		super(tier, damageMod, attackSpeed, properties);
		this.dmg = damageMod + tier.getAttackDamageBonus();
		this.speed = attackSpeed;

		attributeModifiers = buildDefaultAttributes();
	}

	@Override
	public float getDamage() {
		return dmg;
	}

	public double getAttackSpeed() {
		return speed;
	}

	protected Supplier<ImmutableSetMultimap<Attribute, AttributeModifier>> buildDefaultAttributes() {
		return Suppliers.memoize(() -> ImmutableSetMultimap.of(
				Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier", getDamage(), AttributeModifier.Operation.ADDITION),
				Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier", getAttackSpeed(), AttributeModifier.Operation.ADDITION)));
	}

	public float getDamageForAttack(LivingEntity target, LivingEntity attacker, ItemStack swordStack, float baseDamage) {
		return baseDamage;
	}

	@Override
	public boolean onLeftClickEntity(ItemStack stack, Player player, Entity entity) {
		stack.setData(AoADataAttachments.MELEE_SWING_STRENGTH, player.getAttackStrengthScale(0));

		return false;
	}

	@Override
	public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		doMeleeEffect(stack, target, attacker, getSwingEffectiveness(stack));

		return super.hurtEnemy(stack, target, attacker);
	}

	protected void doMeleeEffect(ItemStack stack, LivingEntity target, LivingEntity attacker, float attackCooldown) {}

	protected static float getSwingEffectiveness(ItemStack stack) {
		return stack.getData(AoADataAttachments.MELEE_SWING_STRENGTH);
	}

	@Override
	public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
		if (slot == EquipmentSlot.MAINHAND) {
			Multimap<Attribute, AttributeModifier> newMap = HashMultimap.create();
			ImmutableSetMultimap<Attribute, AttributeModifier> attributes = attributeModifiers.get();

			for (Map.Entry<Attribute, AttributeModifier> entry : attributes.entries()) {
				newMap.put(entry.getKey(), entry.getValue());
			}

			return newMap;
		}

		return super.getDefaultAttributeModifiers(slot);
	}
}

