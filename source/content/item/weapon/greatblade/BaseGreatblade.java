package net.tslat.aoa3.content.item.weapon.greatblade;

import com.google.common.collect.ImmutableSetMultimap;
import net.minecraft.core.Direction;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.util.Lazy;
import net.tslat.aoa3.common.registration.item.AoAEnchantments;
import net.tslat.aoa3.content.capability.volatilestack.VolatileStackCapabilityProvider;
import net.tslat.aoa3.content.item.weapon.sword.BaseSword;
import net.tslat.aoa3.library.constant.AttackSpeed;

import java.util.UUID;

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
		if (attacker.fallDistance > 0 && !attacker.isOnGround() && !attacker.onClimbable() && !attacker.isInWater() && !attacker.isPassenger() && !attacker.hasEffect(MobEffects.BLINDNESS) && VolatileStackCapabilityProvider.getOrDefault(swordStack, Direction.NORTH).getValue() >= 1)
			baseDamage += 1.15f * EnchantmentHelper.getItemEnchantmentLevel(AoAEnchantments.SEVER.get(), swordStack);

		return baseDamage;
	}

	@Override
	protected Lazy<ImmutableSetMultimap<Attribute, AttributeModifier>> buildDefaultAttributes() {
		return Lazy.of(() -> ImmutableSetMultimap.of(
				Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier", getDamage(), AttributeModifier.Operation.ADDITION),
				Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier", getAttackSpeed(), AttributeModifier.Operation.ADDITION),
				ForgeMod.ENTITY_REACH.get(), new AttributeModifier(UUID.fromString("93bb7485-ce86-4e78-ab50-26f53d78ad9d"), "AoAGreatbladeReach", 1.5f, AttributeModifier.Operation.ADDITION)));
	}

	@Override
	public float getDestroySpeed(ItemStack stack, BlockState state) {
		Material material = state.getMaterial();

		if (material == Material.WEB) {
			return 25.0f;
		}
		else if (material == Material.PLANT || material == Material.REPLACEABLE_PLANT || material == Material.LEAVES || material == Material.VEGETABLE) {
			return 2.0f;
		}
		else {
			return 1.0f;
		}
	}
}
