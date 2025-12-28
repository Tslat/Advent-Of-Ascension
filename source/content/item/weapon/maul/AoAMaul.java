package net.tslat.aoa3.content.item.weapon.maul;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.component.Tool;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.common.ItemAbility;
import net.neoforged.neoforge.common.Tags;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.item.AoADataComponents;
import net.tslat.aoa3.library.constant.AttackSpeed;
import net.tslat.aoa3.library.object.extension.MutableFloat;
import net.tslat.aoa3.util.AttributeUtil;

import java.util.List;

public class AoAMaul extends TieredItem {
	protected static final ResourceLocation SCALED_KNOCKBACK_ID = AdventOfAscension.id("maul_scaled_knockback");
	protected static final ResourceLocation BASE_ATTACK_REACH_ID = AdventOfAscension.id("maul_attack_reach");

	public AoAMaul(Tier tier, Item.Properties properties) {
		this(tier, properties, createToolProperties());
	}

	public AoAMaul(Tier tier, Item.Properties properties, Tool toolComponent) {
		super(tier, properties.component(DataComponents.TOOL, toolComponent).component(AoADataComponents.MELEE_SWING_STRENGTH, new MutableFloat(1)).durability(tier.getUses()));
	}

	@Override
	public UseAnim getUseAnimation(ItemStack stack) {
		return UseAnim.BLOCK;
	}

	protected void doMeleeEffect(ItemStack stack, Entity target, LivingEntity attacker, float attackCooldown) {}

	@Override
	public boolean canAttackBlock(BlockState state, Level worldIn, BlockPos pos, Player player) {
		return !player.isCreative();
	}

	@Override
	public boolean canDisableShield(ItemStack stack, ItemStack shield, LivingEntity entity, LivingEntity attacker) {
		return true;
	}

	protected AttributeModifier getKnockbackModifier(ItemStack stack, float modifier) {
		if (stack.has(DataComponents.ATTRIBUTE_MODIFIERS)) {
			for (ItemAttributeModifiers.Entry entry : stack.get(DataComponents.ATTRIBUTE_MODIFIERS).modifiers()) {
				if (entry.attribute().is(Attributes.ATTACK_KNOCKBACK)) {
					return new AttributeModifier(SCALED_KNOCKBACK_ID, modifier * entry.modifier().amount(), entry.modifier().operation());
				}
			}
		}

		return new AttributeModifier(SCALED_KNOCKBACK_ID, 5f * modifier, AttributeModifier.Operation.ADD_VALUE);
	}

	@Override
	public boolean onLeftClickEntity(ItemStack stack, Player player, Entity entity) {
		float attackStr = player.getAttackStrengthScale(0.0f);

		stack.get(AoADataComponents.MELEE_SWING_STRENGTH).setValue(attackStr);
		AttributeUtil.applyTransientModifier(player, Attributes.ATTACK_KNOCKBACK, getKnockbackModifier(stack, attackStr));

		return false;
	}

	@Override
	public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		if (!attacker.level().isClientSide) {
			doMeleeEffect(stack, target, attacker, stack.get(AoADataComponents.MELEE_SWING_STRENGTH).floatValue());
			AttributeUtil.applyTransientModifier(attacker, Attributes.ATTACK_KNOCKBACK, getKnockbackModifier(stack, 1));
		}

		return true;
	}

	@Override
	public void postHurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		stack.hurtAndBreak(1, attacker, EquipmentSlot.MAINHAND);
	}

	@Override
	public boolean canPerformAction(ItemStack stack, ItemAbility itemAbility) {
		return ItemAbilities.DEFAULT_PICKAXE_ACTIONS.contains(itemAbility);
	}

	@Override
	public boolean isPrimaryItemFor(ItemStack stack, Holder<Enchantment> enchantment) {
		return enchantment.is(Enchantments.LOOTING) || enchantment.is(Enchantments.KNOCKBACK) || super.isPrimaryItemFor(stack, enchantment);
	}

	public static Tool createToolProperties() {
		return new Tool(List.of(Tool.Rule.minesAndDrops(Tags.Blocks.STONES, 5f), Tool.Rule.overrideSpeed(BlockTags.MINEABLE_WITH_PICKAXE, 1.5F)), 1f, 2);
	}

	public static Item.Properties baseProperties(Tier tier) {
		return baseProperties(tier, 5);
	}

	public static Item.Properties baseProperties(Tier tier, float knockback) {
		return baseProperties(tier, knockback, AttackSpeed.MAUL);
	}

	public static Item.Properties baseProperties(Tier tier, float knockback, float attackSpeed) {
		return baseProperties(tier, knockback, attackSpeed, 0f);
	}

	public static Item.Properties baseProperties(Tier tier, float knockback, float attackSpeed, float attackDamageMod) {
		return baseProperties(tier, knockback, attackSpeed, attackDamageMod, 0.5f);
	}

	public static Item.Properties baseProperties(Tier tier, float knockback, float attackDamageMod, float attackSpeed, float reachMod) {
		return new Item.Properties().attributes(createAttributes(tier, attackDamageMod, attackSpeed, knockback, reachMod));
	}

	public static ItemAttributeModifiers createAttributes(Tier tier, float attackDamageMod, float attackSpeed, float knockback, float reachMod) {
		return ItemAttributeModifiers.builder()
				.add(Attributes.ATTACK_DAMAGE,
					 new AttributeModifier(BASE_ATTACK_DAMAGE_ID, attackDamageMod + tier.getAttackDamageBonus(), AttributeModifier.Operation.ADD_VALUE),
					 EquipmentSlotGroup.MAINHAND)
				.add(Attributes.ATTACK_SPEED,
					 new AttributeModifier(BASE_ATTACK_SPEED_ID, attackSpeed, AttributeModifier.Operation.ADD_VALUE),
					 EquipmentSlotGroup.MAINHAND)
				.add(Attributes.ATTACK_KNOCKBACK,
					 new AttributeModifier(SCALED_KNOCKBACK_ID, knockback, AttributeModifier.Operation.ADD_VALUE),
					 EquipmentSlotGroup.MAINHAND)
				.add(Attributes.ENTITY_INTERACTION_RANGE,
					 new AttributeModifier(BASE_ATTACK_REACH_ID, reachMod, AttributeModifier.Operation.ADD_VALUE),
					 EquipmentSlotGroup.MAINHAND)
				.build();
	}
}
