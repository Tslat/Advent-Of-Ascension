package net.tslat.aoa3.item.weapon.maul;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.ForgeMod;
import net.tslat.aoa3.common.registration.AoAEnchantments;
import net.tslat.aoa3.common.registration.AoAItemGroups;
import net.tslat.aoa3.config.AoAConfig;
import net.tslat.aoa3.item.LongReachItem;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public class BaseMaul extends Item implements LongReachItem {
	private final Multimap<Attribute, AttributeModifier> attributeModifiers = HashMultimap.create();
	protected final AttributeModifier ATTACK_REACH_MODIFIER = new AttributeModifier(UUID.fromString("678cb085-1367-42c3-8437-d07ade6201d0"), "AoAMaulReach", getReach() - 3.5f, AttributeModifier.Operation.ADDITION);

	protected final float baseDamage;
	protected final double attackSpeed;
	protected final double knockback;

	public BaseMaul(float baseDmg, double attackSpeed, double knockback, final int durability) {
		super(new Item.Properties().durability(durability).tab(AoAItemGroups.MAULS));

		this.baseDamage = baseDmg;
		this.attackSpeed = attackSpeed;
		this.knockback = knockback;

		attributeModifiers.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier", getAttackDamage(), AttributeModifier.Operation.ADDITION));
		attributeModifiers.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier", getAttackSpeed(), AttributeModifier.Operation.ADDITION));
	}

	public float getAttackDamage() {
		return baseDamage * (AoAConfig.COMMON.hardcoreMode.get() ? 1.25f : 1f);
	}

	public double getAttackSpeed() {
		return attackSpeed;
	}

	public double getBaseKnockback() {
		return knockback;
	}

	@Override
	public float getReach() {
		return 4F;
	}

	@Override
	public UseAction getUseAnimation(ItemStack stack) {
		return UseAction.BLOCK;
	}

	@Override
	public boolean canAttackBlock(BlockState state, World worldIn, BlockPos pos, PlayerEntity player) {
		return !player.isCreative();
	}

	@Override
	public boolean onEntitySwing(ItemStack stack, LivingEntity entity) {
		return false;
	}

	@Override
	public boolean canDisableShield(ItemStack stack, ItemStack shield, LivingEntity entity, LivingEntity attacker) {
		return true;
	}

	@Override
	public boolean mineBlock(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity holder) {
		if (!world.isClientSide && (double)state.getDestroySpeed(world, pos) != 0.0D)
			ItemUtil.damageItem(stack, holder, state.getMaterial() == Material.STONE ? 1 : 2, EquipmentSlotType.MAINHAND);

		return true;
	}

	@Override
	public boolean onLeftClickEntity(ItemStack stack, PlayerEntity player, Entity entity) {
		return true;
	}

	@Override
	public boolean hitEntity(ItemStack stack, Entity target, LivingEntity attacker, float dmg) {
		if (dmg < 0)
			dmg = getAttackDamage() + 1;

		if (attacker instanceof PlayerEntity) {
			if (target instanceof FireballEntity) {
				target.hurt(DamageSource.playerAttack((PlayerEntity)attacker), dmg);
			}
			else {
				EffectInstance str = attacker.getEffect(Effects.DAMAGE_BOOST);
				EffectInstance weak = attacker.getEffect(Effects.WEAKNESS);
				float targetHealth = 0;

				if (target instanceof LivingEntity)
					targetHealth = ((LivingEntity)target).getHealth();

				if (str != null)
					dmg += (str.getAmplifier() + 1) * 3;

				if (weak != null)
					dmg -= (weak.getAmplifier() + 1) * 4;

				float cooldownMultiplier = ((PlayerEntity)attacker).getAttackStrengthScale(0f);
				final float crushMod = 1 + 0.15f * EnchantmentHelper.getItemEnchantmentLevel(AoAEnchantments.CRUSH.get(), stack);
				final float finalDmg = dmg * cooldownMultiplier + 0.1f;

				if (target instanceof EnderDragonEntity ? ((EnderDragonEntity)target).getSubEntities()[0].hurt(DamageSource.playerAttack((PlayerEntity)attacker), finalDmg) : target.hurt(DamageSource.playerAttack((PlayerEntity)attacker), finalDmg)) {
					if (target instanceof LivingEntity)
						DamageUtil.doScaledKnockback((LivingEntity)target, attacker, (float)knockback * crushMod * cooldownMultiplier, attacker.getX() - target.getX(), attacker.getZ() - target.getZ());

					if (attacker.level instanceof ServerWorld && target instanceof LivingEntity) {
						int hearts = (int)((targetHealth - ((LivingEntity)target).getHealth()) / 2);

						if (hearts > 0) {
							((ServerWorld)attacker.level).sendParticles(ParticleTypes.DAMAGE_INDICATOR, target.getX(), target.getY() + (double)(target.getBbHeight() * 0.5F), target.getZ(), hearts, 0.1D, 0.0D, 0.1D, 0.2D);
						}
					}

					ItemUtil.damageItem(stack, attacker, 1, EquipmentSlotType.MAINHAND);
					doMeleeEffect(stack, (PlayerEntity)attacker, target, finalDmg, cooldownMultiplier);
				}
			}
		}
		else if (target instanceof LivingEntity) {
			((LivingEntity)target).knockback((float)knockback, attacker.getX() - target.getX(), attacker.getZ() - target.getZ());
		}

		return false;
	}

	protected void doMeleeEffect(ItemStack stack, PlayerEntity attacker, Entity target, float finalDmg, float attackCooldown) {}

	@Override
	public int getEnchantmentValue() {
		return 8;
	}

	@Override
	public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlotType slot, ItemStack stack) {
		Multimap<Attribute, AttributeModifier> multimap = super.getAttributeModifiers(slot, stack);

		if (slot == EquipmentSlotType.MAINHAND) {
			attributeModifiers.put(ForgeMod.REACH_DISTANCE.get(), ATTACK_REACH_MODIFIER);

			return attributeModifiers;
		}

		return multimap;
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("items.description.maul.knockback", LocaleUtil.ItemDescriptionType.ITEM_TYPE_INFO, new StringTextComponent(Double.toString((int)(knockback * 700) / 100D))));
	}
}
