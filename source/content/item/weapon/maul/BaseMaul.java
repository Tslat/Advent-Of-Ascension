package net.tslat.aoa3.content.item.weapon.maul;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.ImmutableSetMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.Lazy;
import net.tslat.aoa3.common.registration.AoAItemGroups;
import net.tslat.aoa3.content.capability.volatilestack.VolatileStackCapabilityProvider;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.ItemUtil;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.UUID;

public class BaseMaul extends Item {
	private final Lazy<ImmutableSetMultimap<Attribute, AttributeModifier>> attributeModifiers;
	protected static final UUID KNOCKBACK_MODIFIER_UUID = UUID.fromString("f21dd55d-0e43-4e19-a683-1df45d51c60f");

	protected final float baseDamage;
	protected final double attackSpeed;
	protected final double knockback;

	public BaseMaul(float baseDmg, double attackSpeed, double knockback, final int durability) {
		super(new Item.Properties().durability(durability).tab(AoAItemGroups.MAULS));

		this.baseDamage = baseDmg;
		this.attackSpeed = attackSpeed;
		this.knockback = knockback;

		attributeModifiers = buildDefaultAttributes();
	}

	public float getAttackDamage() {
		return baseDamage;
	}

	public double getAttackSpeed() {
		return attackSpeed;
	}

	public double getBaseKnockback() {
		return knockback;
	}

	@Override
	public UseAnim getUseAnimation(ItemStack stack) {
		return UseAnim.BLOCK;
	}

	protected Lazy<ImmutableSetMultimap<Attribute, AttributeModifier>> buildDefaultAttributes() {
		return Lazy.of(() -> ImmutableSetMultimap.of(
				Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier", getAttackDamage(), AttributeModifier.Operation.ADDITION),
				Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier", getAttackSpeed(), AttributeModifier.Operation.ADDITION),
				Attributes.ATTACK_KNOCKBACK, getKnockbackModifier(1),
				ForgeMod.REACH_DISTANCE.get(), new AttributeModifier(UUID.fromString("93bb7485-ce86-4e78-ab50-26f53d78ad9d"), "AoAGreatbladeReach", 0.5f, AttributeModifier.Operation.ADDITION)));
	}

	private AttributeModifier getKnockbackModifier(float attackStrengthMod) {
		return new AttributeModifier(KNOCKBACK_MODIFIER_UUID, "AoAMaulKnockback", getBaseKnockback() * attackStrengthMod, AttributeModifier.Operation.ADDITION);
	}

	@Override
	public boolean canAttackBlock(BlockState state, Level worldIn, BlockPos pos, Player player) {
		return !player.isCreative();
	}

	@Override
	public boolean canDisableShield(ItemStack stack, ItemStack shield, LivingEntity entity, LivingEntity attacker) {
		return true;
	}

	@Override
	public boolean mineBlock(ItemStack stack, Level world, BlockState state, BlockPos pos, LivingEntity holder) {
		if (!world.isClientSide && (double)state.getDestroySpeed(world, pos) != 0.0D)
			ItemUtil.damageItem(stack, holder, state.getMaterial() == Material.STONE ? 1 : 2, EquipmentSlot.MAINHAND);

		return true;
	}

	@Override
	public boolean onLeftClickEntity(ItemStack stack, Player player, Entity entity) {
		float attackStr = player.getAttackStrengthScale(0.0f);

		VolatileStackCapabilityProvider.getOrDefault(stack, Direction.NORTH).setValue(attackStr);
		EntityUtil.reapplyAttributeModifier(player, Attributes.ATTACK_KNOCKBACK, getKnockbackModifier(attackStr), false);

		return false;
	}

	@Override
	public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		float cooldown = VolatileStackCapabilityProvider.getOrDefault(stack, Direction.NORTH).getValue();

		doMeleeEffect(stack, target, attacker, cooldown);
		ItemUtil.damageItem(stack, attacker, InteractionHand.MAIN_HAND);
		EntityUtil.reapplyAttributeModifier(attacker, Attributes.ATTACK_KNOCKBACK, getKnockbackModifier(1), false);

		return true;
	}

	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
		return enchantment == Enchantments.MOB_LOOTING || enchantment == Enchantments.KNOCKBACK || super.canApplyAtEnchantingTable(stack, enchantment);
	}

	protected void doMeleeEffect(ItemStack stack, Entity target, LivingEntity attacker, float attackCooldown) {}

	@Override
	public int getEnchantmentValue() {
		return 8;
	}

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

		return super.getAttributeModifiers(slot, stack);
	}
}
