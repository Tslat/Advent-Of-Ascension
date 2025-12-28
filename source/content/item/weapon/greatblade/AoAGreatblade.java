package net.tslat.aoa3.content.item.weapon.greatblade;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.content.item.weapon.sword.AoASword;

public class AoAGreatblade extends AoASword {
	protected static final ResourceLocation BASE_ATTACK_REACH_ID = AdventOfAscension.id("greatblade_attack_reach");

	public AoAGreatblade(Tier tier, Item.Properties properties) {
		super(tier, properties);
	}/*

	@Override
	public float getDamageForAttack(LivingEntity target, LivingEntity attacker, ItemStack greatblade, DamageSource source, float baseDamage) {
		if (attacker.level() instanceof ServerLevel level && isCriticalHit(attacker, target, getSwingEffectiveness(greatblade)))
			baseDamage = AoAEnchantments.modifyCritDamage(level, greatblade, target, source, baseDamage);

		return baseDamage;
	}*/

	@Override
	public float getDestroySpeed(ItemStack stack, BlockState state) {
		if (state.is(Blocks.COBWEB))
			return 25f;

		return state.is(BlockTags.SWORD_EFFICIENT) ? 2f : 1f;
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

	public static ItemAttributeModifiers createAttributes(Tier tier, float attackDamageMod, float attackSpeed) {
		return ItemAttributeModifiers.builder()
				.add(Attributes.ATTACK_DAMAGE,
						new AttributeModifier(BASE_ATTACK_DAMAGE_ID, attackDamageMod + tier.getAttackDamageBonus(), AttributeModifier.Operation.ADD_VALUE),
						EquipmentSlotGroup.MAINHAND)
				.add(Attributes.ATTACK_SPEED,
						new AttributeModifier(BASE_ATTACK_SPEED_ID, attackSpeed, AttributeModifier.Operation.ADD_VALUE),
						EquipmentSlotGroup.MAINHAND)
				.add(Attributes.ENTITY_INTERACTION_RANGE,
						new AttributeModifier(BASE_ATTACK_REACH_ID, 1.5f, AttributeModifier.Operation.ADD_VALUE),
						EquipmentSlotGroup.MAINHAND)
				.build();
	}
}
