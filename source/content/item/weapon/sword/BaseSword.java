package net.tslat.aoa3.content.item.weapon.sword;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.ImmutableSetMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
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
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.Lazy;
import net.tslat.aoa3.common.registration.AoAItemGroups;
import net.tslat.aoa3.content.capability.volatilestack.VolatileStackCapabilityProvider;

import javax.annotation.Nullable;
import java.util.Map;

public class BaseSword extends SwordItem {
	private final Lazy<ImmutableSetMultimap<Attribute, AttributeModifier>> attributeModifiers;

	protected final float dmg;
	protected final double speed;

	public BaseSword(Tier itemStats) {
		this(itemStats, new Item.Properties().durability(itemStats.getUses()).tab(AoAItemGroups.SWORDS));
	}

	public BaseSword(Tier itemStats, Item.Properties properties) {
		super(itemStats, 0, itemStats.getSpeed(), properties);
		this.dmg = itemStats.getAttackDamageBonus();
		this.speed = itemStats.getSpeed();

		attributeModifiers = buildDefaultAttributes();
	}

	@Override
	public float getDamage() {
		return dmg;
	}

	public double getAttackSpeed() {
		return speed;
	}

	protected Lazy<ImmutableSetMultimap<Attribute, AttributeModifier>> buildDefaultAttributes() {
		return Lazy.of(() -> ImmutableSetMultimap.of(
				Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier", getDamage(), AttributeModifier.Operation.ADDITION),
				Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier", getAttackSpeed(), AttributeModifier.Operation.ADDITION)));
	}

	public double getDamageForAttack(LivingEntity target, LivingEntity attacker, ItemStack swordStack, double baseDamage) {
		return baseDamage;
	}

	@Override
	public boolean onLeftClickEntity(ItemStack stack, Player player, Entity entity) {
		VolatileStackCapabilityProvider.getOrDefault(stack, Direction.NORTH).setValue(player.getAttackStrengthScale(0.0f));

		return false;
	}

	@Override
	public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		doMeleeEffect(stack, target, attacker, VolatileStackCapabilityProvider.getOrDefault(stack, Direction.NORTH).getValue());

		return super.hurtEnemy(stack, target, attacker);
	}

	protected void doMeleeEffect(ItemStack stack, LivingEntity target, LivingEntity attacker, float attackCooldown) {}

	@Nullable
	@Override
	public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundTag nbt) {
		return new VolatileStackCapabilityProvider();
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

