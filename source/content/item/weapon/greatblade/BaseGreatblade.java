package net.tslat.aoa3.content.item.weapon.greatblade;

import com.google.common.base.Suppliers;
import com.google.common.collect.ImmutableSetMultimap;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.tslat.aoa3.content.enchantment.SeverEnchantment;
import net.tslat.aoa3.content.item.weapon.sword.BaseSword;
import net.tslat.aoa3.library.constant.AttackSpeed;

import java.util.UUID;
import java.util.function.Supplier;

public class BaseGreatblade extends BaseSword {
	public BaseGreatblade(Tier tier) {
		this(tier, Rarity.COMMON);
	}

	public BaseGreatblade(Tier tier, Rarity rarity) {
		this(tier, AttackSpeed.GREATBLADE, rarity);
	}

	public BaseGreatblade(Tier tier, float attackSpeed) {
		this(tier, attackSpeed, Rarity.COMMON);
	}

	public BaseGreatblade(Tier tier, float attackSpeed, Rarity rarity) {
		super(tier, 0, attackSpeed, new Item.Properties().durability(tier.getUses()).rarity(rarity));
	}

	@Override
	public float getDamageForAttack(LivingEntity target, LivingEntity attacker, ItemStack swordStack, float baseDamage) {
		if (attacker.fallDistance > 0 && !attacker.onGround() && !attacker.onClimbable() && !attacker.isInWater() && !attacker.isPassenger() && !attacker.hasEffect(MobEffects.BLINDNESS) && getSwingEffectiveness(swordStack) >= 1)
			baseDamage = SeverEnchantment.applyDamageBonus(swordStack, baseDamage);

		return baseDamage;
	}

	@Override
	protected Supplier<ImmutableSetMultimap<Attribute, AttributeModifier>> buildDefaultAttributes() {
		return Suppliers.memoize(() -> ImmutableSetMultimap.of(
				Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier", getDamage(), AttributeModifier.Operation.ADDITION),
				Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier", getAttackSpeed(), AttributeModifier.Operation.ADDITION),
				NeoForgeMod.ENTITY_REACH.value(), new AttributeModifier(UUID.fromString("93bb7485-ce86-4e78-ab50-26f53d78ad9d"), "AoAGreatbladeReach", 1.5f, AttributeModifier.Operation.ADDITION)));
	}

	@Override
	public float getDestroySpeed(ItemStack stack, BlockState state) {
		if (state.is(Blocks.COBWEB))
			return 25f;

		return state.is(BlockTags.SWORD_EFFICIENT) ? 2f : 1f;
	}
}
