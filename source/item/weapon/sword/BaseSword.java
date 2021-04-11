package net.tslat.aoa3.item.weapon.sword;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.tslat.aoa3.capabilities.volatilestack.VolatileStackCapabilityProvider;
import net.tslat.aoa3.common.registration.AoAItemGroups;
import net.tslat.aoa3.config.AoAConfig;

import javax.annotation.Nullable;

public class BaseSword extends SwordItem {
	private final Multimap<Attribute, AttributeModifier> attributeModifiers = HashMultimap.create();

	protected final float dmg;
	protected final double speed;

	public BaseSword(IItemTier itemStats) {
		this(itemStats, new Item.Properties().durability(itemStats.getUses()).tab(AoAItemGroups.SWORDS).addToolType(ToolType.get("sword"), itemStats.getLevel()));
	}

	public BaseSword(IItemTier itemStats, Item.Properties properties) {
		super(itemStats, 0, itemStats.getSpeed(), properties);
		this.dmg = itemStats.getAttackDamageBonus();
		this.speed = itemStats.getSpeed();

		attributeModifiers.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier", getDamage(), AttributeModifier.Operation.ADDITION));
		attributeModifiers.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier", getAttackSpeed(), AttributeModifier.Operation.ADDITION));
	}

	@Override
	public float getDamage() {
		return dmg * (AoAConfig.COMMON.hardcoreMode.get() ? 1.25f : 1f);
	}

	public double getAttackSpeed() {
		return speed;
	}

	@Override
	public boolean onLeftClickEntity(ItemStack stack, PlayerEntity player, Entity entity) {
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
	public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundNBT nbt) {
		return new VolatileStackCapabilityProvider();
	}

	@Override
	public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlotType slot, ItemStack stack) {
		Multimap<Attribute, AttributeModifier> multimap = super.getDefaultAttributeModifiers(slot);

		if (slot == EquipmentSlotType.MAINHAND)
			return attributeModifiers;

		return multimap;
	}
}

