package net.tslat.aoa3.item.weapon.greatblade;

import com.google.common.collect.Multimap;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
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
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.ToolType;
import net.tslat.aoa3.common.registration.AoAEnchantments;
import net.tslat.aoa3.common.registration.AoAItemGroups;
import net.tslat.aoa3.config.AoAConfig;
import net.tslat.aoa3.item.LongReachItem;
import net.tslat.aoa3.util.ItemUtil;

import java.util.UUID;

public class BaseGreatblade extends Item implements LongReachItem {
	protected final AttributeModifier ATTACK_REACH_MODIFIER = new AttributeModifier(UUID.fromString("93bb7485-ce86-4e78-ab50-26f53d78ad9d"), "AoAGreatbladeReach", getReach() - 3.5f, AttributeModifier.Operation.ADDITION);
	private final double baseDamage;
	private final double attackSpeed;

	public BaseGreatblade(final double baseDmg, final double attackSpeed, final int durability) {
		this(baseDmg, attackSpeed, new Item.Properties().maxDamage(durability).group(AoAItemGroups.GREATBLADES).addToolType(ToolType.get("sword"), 4));
	}

	public BaseGreatblade(final double baseDmg, final double attackSpeed, Item.Properties properties) {
		super(properties);

		this.baseDamage = baseDmg;
		this.attackSpeed = attackSpeed;
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
	public boolean onLeftClickEntity(ItemStack stack, PlayerEntity player, Entity entity) {
		return true;
	}

	@Override
	public int getItemEnchantability() {
		return 8;
	}

	@Override
	public float getReach() {
		return 5f;
	}

	public double getAttackDamage() {
		return baseDamage * (AoAConfig.COMMON.hardcoreMode.get() ? 1.25f : 1f);
	}

	public double getAttackSpeed() {
		return attackSpeed;
	}

	protected double getDamageForAttack(ItemStack stack, Entity target, LivingEntity attacker, double baseDmg) {
		return getAttackDamage();
	}

	@Override
	public boolean hitEntity(ItemStack stack, Entity target, LivingEntity attacker, float dmg) {
		float damageDealt = 0;

		if (dmg < 0)
			dmg = (float)getDamageForAttack(stack, target, attacker, getAttackDamage()) + 1;

		if (attacker instanceof PlayerEntity) {
			if (target instanceof FireballEntity) {
				if (target.attackEntityFrom(DamageSource.causePlayerDamage((PlayerEntity)attacker), dmg))
					damageDealt = dmg;
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

				float cooldownMultiplier = (((PlayerEntity)attacker).getCooledAttackStrength(0f));
				final int severModifier = EnchantmentHelper.getEnchantmentLevel(AoAEnchantments.SEVER.get(), stack);
				final float finalDmg = (dmg + severModifier * 1.5f) * (cooldownMultiplier + 0.01f);

				if (target instanceof EnderDragonEntity ? ((EnderDragonEntity)target).getDragonParts()[0].attackEntityFrom(DamageSource.causePlayerDamage((PlayerEntity)attacker), finalDmg) : target.attackEntityFrom(DamageSource.causePlayerDamage((PlayerEntity)attacker), finalDmg)) {
					if (attacker.world instanceof ServerWorld) {
						if (cooldownMultiplier >= 1.0) {
							double d0 = -MathHelper.sin(attacker.rotationYaw * 0.017453292F);
							double d1 = MathHelper.cos(attacker.rotationYaw * 0.017453292F);

							((ServerWorld)attacker.world).spawnParticle(ParticleTypes.SWEEP_ATTACK, target.getPosX() + d0, target.getPosY() + (double)target.getHeight() * 0.5D, target.getPosZ() + d1, 0, d0, 0.0D, d1, 0.0D);
							attacker.world.playSound(null, attacker.getPosX(), attacker.getPosY(), attacker.getPosZ(), SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP, SoundCategory.PLAYERS, 1.0f, 1.0f);
						}

						int hearts = 0;

						if (target instanceof LivingEntity) {
							damageDealt = targetHealth - ((LivingEntity)target).getHealth();
							hearts = (int)(damageDealt / 2);
						}
						else {
							damageDealt = dmg;
						}

						if (hearts > 0)
							((ServerWorld)attacker.world).spawnParticle(ParticleTypes.DAMAGE_INDICATOR, target.getPosX(), target.getPosY() + (double)(target.getHeight() * 0.5F), target.getPosZ(), hearts, 0.1D, 0.0D, 0.1D, 0.2D);
					}

					ItemUtil.damageItem(stack, attacker, 1, EquipmentSlotType.MAINHAND);
				}
			}
		}
		else {
			if (dmg < 0)
				dmg = (float)getDamageForAttack(stack, target, attacker, getAttackDamage());

			EffectInstance str = attacker.getActivePotionEffect(Effects.STRENGTH);
			EffectInstance weak = attacker.getActivePotionEffect(Effects.WEAKNESS);
			double health = target instanceof LivingEntity ? ((LivingEntity)target).getHealth() : 0;

			if (str != null)
				dmg += (str.getAmplifier() + 1) * 3;

			if (weak != null)
				dmg -= (weak.getAmplifier() + 1) * 4;

			dmg +=EnchantmentHelper.getEnchantmentLevel(AoAEnchantments.SEVER.get(), stack);

			if (target.attackEntityFrom(DamageSource.causeMobDamage(attacker), dmg)) {
				if (health > 0)
					damageDealt = (float)health - ((LivingEntity)target).getHealth();
			}
		}

		if (damageDealt > 0) {
			doMeleeEffect(stack, attacker, target, damageDealt);

			return true;
		}

		return false;
	}

	protected void doMeleeEffect(ItemStack stack, LivingEntity attacker, Entity target, float dmgDealt) {}

	@Override
	public float getDestroySpeed(ItemStack stack, BlockState state) {
		Material material = state.getMaterial();

		if (material == Material.WEB) {
			return 25.0f;
		}
		else if (material == Material.PLANTS || material == Material.TALL_PLANTS || material == Material.CORAL || material == Material.LEAVES || material == Material.GOURD) {
			return 2.0f;
		}
		else {
			return 1.0f;
		}
	}

	@Override
	public boolean onBlockDestroyed(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity user) {
		if (!world.isRemote && (double)state.getBlockHardness(world, pos) != 0.0D)
			ItemUtil.damageItem(stack, user, 1, EquipmentSlotType.MAINHAND);

		return true;
	}

	@Override
	public boolean onEntitySwing(ItemStack stack, LivingEntity entity) {
		return false;
	}

	@Override
	public Multimap<String, AttributeModifier> getAttributeModifiers(EquipmentSlotType equipmentSlot, ItemStack stack) {
		Multimap<String, AttributeModifier> multimap = super.getAttributeModifiers(equipmentSlot, stack);

		if (equipmentSlot == EquipmentSlotType.MAINHAND) {
			multimap.put(PlayerEntity.REACH_DISTANCE.getName(), ATTACK_REACH_MODIFIER);
			multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", getAttackDamage(), AttributeModifier.Operation.ADDITION));
			multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", getAttackSpeed(), AttributeModifier.Operation.ADDITION));
		}

		return multimap;
	}
}
