package net.tslat.aoa3.item.weapon.maul;

import com.google.common.collect.Multimap;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
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
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
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
	protected final AttributeModifier ATTACK_REACH_MODIFIER = new AttributeModifier(UUID.fromString("678cb085-1367-42c3-8437-d07ade6201d0"), "AoAMaulReach", getReach() - 3.5f, AttributeModifier.Operation.ADDITION);
	protected final float baseDamage;
	protected final double attackSpeed;
	protected final double knockback;

	public BaseMaul(float baseDmg, double attackSpeed, double knockback, final int durability) {
		super(new Item.Properties().maxDamage(durability).group(AoAItemGroups.MAULS));

		this.baseDamage = baseDmg;
		this.attackSpeed = attackSpeed;
		this.knockback = knockback;
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
	public UseAction getUseAction(ItemStack stack) {
		return UseAction.BLOCK;
	}

	@Override
	public boolean canPlayerBreakBlockWhileHolding(BlockState state, World worldIn, BlockPos pos, PlayerEntity player) {
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
	public boolean onBlockDestroyed(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity holder) {
		if (!world.isRemote && (double)state.getBlockHardness(world, pos) != 0.0D)
			ItemUtil.damageItem(stack, holder, state.getMaterial() == Material.ROCK ? 1 : 2, EquipmentSlotType.MAINHAND);

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
				target.attackEntityFrom(DamageSource.causePlayerDamage((PlayerEntity)attacker), dmg);
			}
			else {
				EffectInstance str = attacker.getActivePotionEffect(Effects.STRENGTH);
				EffectInstance weak = attacker.getActivePotionEffect(Effects.WEAKNESS);
				float targetHealth = 0;

				if (target instanceof LivingEntity)
					targetHealth = ((LivingEntity)target).getHealth();

				if (str != null)
					dmg += (str.getAmplifier() + 1) * 3;

				if (weak != null)
					dmg -= (weak.getAmplifier() + 1) * 4;

				float cooldownMultiplier = ((PlayerEntity)attacker).getCooledAttackStrength(0f);
				final float crushMod = 1 + 0.15f * EnchantmentHelper.getEnchantmentLevel(AoAEnchantments.CRUSH.get(), stack);
				final float finalDmg = dmg * cooldownMultiplier + 0.1f;

				if (target instanceof EnderDragonEntity ? ((EnderDragonEntity)target).getDragonParts()[0].attackEntityFrom(DamageSource.causePlayerDamage((PlayerEntity)attacker), finalDmg) : target.attackEntityFrom(DamageSource.causePlayerDamage((PlayerEntity)attacker), finalDmg)) {
					if (target instanceof LivingEntity)
						DamageUtil.doScaledKnockback((LivingEntity)target, attacker, (float)knockback * crushMod * cooldownMultiplier, attacker.getPosX() - target.getPosX(), attacker.getPosZ() - target.getPosZ());

					if (attacker.world instanceof ServerWorld && target instanceof LivingEntity) {
						int hearts = (int)((targetHealth - ((LivingEntity)target).getHealth()) / 2);

						if (hearts > 0) {
							((ServerWorld)attacker.world).spawnParticle(ParticleTypes.DAMAGE_INDICATOR, target.getPosX(), target.getPosY() + (double)(target.getHeight() * 0.5F), target.getPosZ(), hearts, 0.1D, 0.0D, 0.1D, 0.2D);
						}
					}

					ItemUtil.damageItem(stack, attacker, 1, EquipmentSlotType.MAINHAND);
					doMeleeEffect(stack, (PlayerEntity)attacker, target, finalDmg, cooldownMultiplier);
				}
			}
		}
		else if (target instanceof LivingEntity) {
			((LivingEntity)target).knockBack(attacker, (float)knockback, attacker.getPosX() - target.getPosX(), attacker.getPosZ() - target.getPosZ());
		}

		return false;
	}

	protected void doMeleeEffect(ItemStack stack, PlayerEntity attacker, Entity target, float finalDmg, float attackCooldown) {}

	@Override
	public int getItemEnchantability() {
		return 8;
	}

	@Override
	public Multimap<String, AttributeModifier> getAttributeModifiers(EquipmentSlotType slot, ItemStack stack) {
		Multimap<String, AttributeModifier> multimap = super.getAttributeModifiers(slot, stack);

		if (slot == EquipmentSlotType.MAINHAND) {
			multimap.put(PlayerEntity.REACH_DISTANCE.getName(), ATTACK_REACH_MODIFIER);
			multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", getAttackDamage(), AttributeModifier.Operation.ADDITION));
			multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", getAttackSpeed(), AttributeModifier.Operation.ADDITION));
		}

		return multimap;
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("items.description.maul.knockback", LocaleUtil.ItemDescriptionType.ITEM_TYPE_INFO, Double.toString((int)(knockback * 700) / 100D)));
	}
}
