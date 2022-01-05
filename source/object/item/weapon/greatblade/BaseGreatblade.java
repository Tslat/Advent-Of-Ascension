package net.tslat.aoa3.object.item.weapon.greatblade;

import com.google.common.collect.ImmutableSetMultimap;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.potion.Effects;
import net.minecraft.util.Direction;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.common.util.Lazy;
import net.tslat.aoa3.object.capability.volatilestack.VolatileStackCapabilityProvider;
import net.tslat.aoa3.common.registration.AoAEnchantments;
import net.tslat.aoa3.common.registration.AoAItemGroups;
import net.tslat.aoa3.object.item.weapon.sword.BaseSword;
import net.tslat.aoa3.util.ItemUtil;

import java.util.UUID;

public class BaseGreatblade extends BaseSword {
	public BaseGreatblade(final double baseDmg, final double attackSpeed, final int durability) {
		this(baseDmg, attackSpeed, durability, Rarity.COMMON);
	}

	public BaseGreatblade(final double baseDmg, final double attackSpeed, final int durability, Rarity rarity) {
		super(ItemUtil.customItemTier(durability, (float)attackSpeed, (float)baseDmg, 4, 8, null),
				new Item.Properties().durability(durability).tab(AoAItemGroups.GREATBLADES).addToolType(ToolType.get("sword"), 4).rarity(rarity));
	}

	@Override
	public double getDamageForAttack(LivingEntity target, LivingEntity attacker, ItemStack swordStack, double baseDamage) {
		if (attacker.fallDistance > 0 && !attacker.onGround && !attacker.onClimbable() && !attacker.isInWater() && !attacker.isPassenger() && !attacker.hasEffect(Effects.BLINDNESS) && VolatileStackCapabilityProvider.getOrDefault(swordStack, Direction.NORTH).getValue() >= 1)
			baseDamage += 1.15f * EnchantmentHelper.getItemEnchantmentLevel(AoAEnchantments.SEVER.get(), swordStack);

		return baseDamage;
	}

	@Override
	protected Lazy<ImmutableSetMultimap<Attribute, AttributeModifier>> buildDefaultAttributes() {
		return Lazy.of(() -> ImmutableSetMultimap.of(
				Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier", getDamage(), AttributeModifier.Operation.ADDITION),
				Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier", getAttackSpeed(), AttributeModifier.Operation.ADDITION),
				ForgeMod.REACH_DISTANCE.get(), new AttributeModifier(UUID.fromString("93bb7485-ce86-4e78-ab50-26f53d78ad9d"), "AoAGreatbladeReach", 1.5f, AttributeModifier.Operation.ADDITION)));
	}

	@Override
	public float getDestroySpeed(ItemStack stack, BlockState state) {
		Material material = state.getMaterial();

		if (material == Material.WEB) {
			return 25.0f;
		}
		else if (material == Material.PLANT || material == Material.REPLACEABLE_PLANT || material == Material.CORAL || material == Material.LEAVES || material == Material.VEGETABLE) {
			return 2.0f;
		}
		else {
			return 1.0f;
		}
	}
}
