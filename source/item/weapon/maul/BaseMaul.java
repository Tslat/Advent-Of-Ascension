package net.tslat.aoa3.item.weapon.maul;

import com.google.common.collect.Multimap;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MultiPartEntityPart;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;
import net.tslat.aoa3.capabilities.providers.AdventMiscStackProvider;
import net.tslat.aoa3.common.registration.CreativeTabsRegister;
import net.tslat.aoa3.common.registration.EnchantmentsRegister;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.item.weapon.AdventWeapon;
import net.tslat.aoa3.item.weapon.LongReachWeapon;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.library.misc.AoAAttributes;
import net.tslat.aoa3.utils.ConfigurationUtil;
import net.tslat.aoa3.utils.EntityUtil;
import net.tslat.aoa3.utils.ItemUtil;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public abstract class BaseMaul extends Item implements AdventWeapon, LongReachWeapon {
	protected final float dmg;
	protected final double speed;
	protected final double knockback;
	protected final AttributeModifier REACH_MODIFIER = new AttributeModifier(UUID.fromString("678cb085-1367-42c3-8437-d07ade6201d0"), "AoAMaulReach", getReach() - 3.5f, 0);

	public BaseMaul(Float dmg, Double speed, double knockback, final int durability) {
		this.dmg = dmg;
		this.speed = speed;
		this.knockback = knockback;
		setCreativeTab(CreativeTabsRegister.MAULS);
		setMaxDamage(durability);
		setMaxStackSize(1);
		setFull3D();
	}

	public float getDamage() {
		return dmg * (ConfigurationUtil.MainConfig.funOptions.hardcoreMode ? 1.25f : 1f);
	}

	public double getAttackSpeed() {
		return speed;
	}

	public double getBaseKnockback() {
		return knockback;
	}

	@Override
	public float getReach() {
		return 4F;
	}

	@Override
	public EnumAction getItemUseAction(final ItemStack stack) {
		return EnumAction.BLOCK;
	}

	@Override
	public boolean canDestroyBlockInCreative(World world, BlockPos pos, ItemStack stack, EntityPlayer player) {
		return false;
	}

	@Override
	public boolean onEntitySwing(final EntityLivingBase holder, final ItemStack stack) {
		return false;
	}

	@Override
	public boolean canDisableShield(ItemStack stack, ItemStack shield, EntityLivingBase target, EntityLivingBase attacker) {
		return true;
	}

	@Override
	public boolean onBlockDestroyed(ItemStack stack, World world, IBlockState state, BlockPos pos, EntityLivingBase holder) {
		if (!world.isRemote && (double)state.getBlockHardness(world, pos) != 0.0D)
			stack.damageItem(state.getMaterial() == Material.ROCK ? 1 : 2, holder);

		return true;
	}

	@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
		return true;
	}

	@Override
	public boolean attackEntity(ItemStack stack, Entity target, EntityLivingBase attacker, float dmg) {
		if (dmg < 0)
			dmg = getDamage() + 1;

		if (attacker instanceof EntityPlayer) {
			if (target instanceof EntityFireball) {
				target.attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer)attacker), dmg);
			}
			else {
				PotionEffect str = attacker.getActivePotionEffect(MobEffects.STRENGTH);
				PotionEffect weak = attacker.getActivePotionEffect(MobEffects.WEAKNESS);
				float targetHealth = 0;

				if (target instanceof EntityLivingBase)
					targetHealth = ((EntityLivingBase)target).getHealth();

				if (str != null)
					dmg += (str.getAmplifier() + 1) * 3;

				if (weak != null)
					dmg -= (weak.getAmplifier() + 1) * 4;

				float cooldownMultiplier = ((EntityPlayer)attacker).getCooledAttackStrength(0f);
				final float crushMod = 1 + 0.15f * EnchantmentHelper.getEnchantmentLevel(EnchantmentsRegister.CRUSH, stack);
				final float finalDmg = dmg * cooldownMultiplier + 0.1f;

				if (target instanceof EntityDragon ? ((EntityDragon)target).attackEntityFromPart((MultiPartEntityPart)target.getParts()[0], DamageSource.causePlayerDamage((EntityPlayer)attacker), finalDmg) : target.attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer)attacker), finalDmg)) {
					if (target instanceof EntityLivingBase)
						EntityUtil.doScaledKnockback((EntityLivingBase)target, attacker, (float)knockback * crushMod * cooldownMultiplier, attacker.posX - target.posX, attacker.posZ - target.posZ);

					if (attacker.world instanceof WorldServer && target instanceof EntityLivingBase) {
						int hearts = (int)((targetHealth - ((EntityLivingBase)target).getHealth()) / 2);

						if (hearts > 0) {
							((WorldServer)attacker.world).spawnParticle(EnumParticleTypes.DAMAGE_INDICATOR, target.posX, target.posY + (double)(target.height * 0.5F), target.posZ, hearts, 0.1D, 0.0D, 0.1D, 0.2D);
						}
					}

					stack.damageItem(1, attacker);
					doMeleeEffect(stack, (EntityPlayer)attacker, target, finalDmg, cooldownMultiplier);
				}
			}
		}
		else if (target instanceof EntityLivingBase) {
			((EntityLivingBase)target).knockBack(attacker, (float)knockback, attacker.posX - target.posX, attacker.posZ - target.posZ);
		}

		return false;
	}

	public boolean attackEntity(ItemStack stack, Entity target, EntityLivingBase attacker) {
		if (attacker instanceof EntityPlayer) {
			float dmg = this.dmg;
			PotionEffect str = attacker.getActivePotionEffect(MobEffects.STRENGTH);
			PotionEffect weak = attacker.getActivePotionEffect(MobEffects.WEAKNESS);
			float targetHealth = 0;

			if (target instanceof EntityLivingBase)
				targetHealth = ((EntityLivingBase)target).getHealth();

			if (str != null)
				dmg += (str.getAmplifier() + 1) * 3;

			if (weak != null)
				dmg -= (weak.getAmplifier() + 1) * 4;

			float cooldownMultiplier = ((EntityPlayer)attacker).getCooledAttackStrength(0f);
			final float crushMod = 1 + 0.15f * EnchantmentHelper.getEnchantmentLevel(EnchantmentsRegister.CRUSH, stack);
			final float finalDmg = dmg * cooldownMultiplier + 0.1f;

			if (target instanceof EntityDragon ? ((EntityDragon)target).attackEntityFromPart((MultiPartEntityPart)target.getParts()[0], DamageSource.causePlayerDamage((EntityPlayer)attacker), finalDmg) : target.attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer)attacker), finalDmg)) {
				if (target instanceof EntityLivingBase)
					EntityUtil.doScaledKnockback((EntityLivingBase)target, attacker, (float)knockback * crushMod * cooldownMultiplier, attacker.posX - target.posX, attacker.posZ - target.posZ);

				if (attacker.world instanceof WorldServer && target instanceof EntityLivingBase) {
					int hearts = (int) ((targetHealth - ((EntityLivingBase)target).getHealth()) / 2);

					if (hearts > 0) {
						((WorldServer) attacker.world).spawnParticle(EnumParticleTypes.DAMAGE_INDICATOR, target.posX, target.posY + (double) (target.height * 0.5F), target.posZ, hearts, 0.1D, 0.0D, 0.1D, 0.2D);
					}
				}

				stack.damageItem(1, attacker);
				doMeleeEffect(stack, (EntityPlayer)attacker, target, finalDmg, stack.getCapability(AdventMiscStackProvider.MISC_STACK, EnumFacing.NORTH).getValue());
			}
		}
		else if (target instanceof EntityLivingBase) {
			((EntityLivingBase)target).knockBack(attacker, (float)knockback, attacker.posX - target.posX, attacker.posZ - target.posZ);
		}

		return false;
	}

	protected void doMeleeEffect(ItemStack stack, EntityPlayer attacker, Entity target, float finalDmg, float attackCooldown) {}

	@Nullable
	@Override
	public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable NBTTagCompound nbt) {
		return new AdventMiscStackProvider();
	}

	@Override
	public boolean getIsRepairable(ItemStack stack, ItemStack repairMaterial) {
		return repairMaterial.getItem() != Items.ENCHANTED_BOOK && OreDictionary.itemMatches(repairMaterial, new ItemStack(ItemRegister.MAGIC_REPAIR_DUST), false);
	}

	@SideOnly(Side.CLIENT)
	public boolean isFull3D() {
		return true;
	}

	@Override
	public int getItemEnchantability() {
		return 8;
	}

	@Override
	public Multimap<String, AttributeModifier> getAttributeModifiers(EntityEquipmentSlot equipmentSlot, ItemStack stack) {
		Multimap<String, AttributeModifier> multimap = super.getAttributeModifiers(equipmentSlot, stack);

		if (equipmentSlot == EntityEquipmentSlot.MAINHAND) {
			multimap.put(EntityPlayer.REACH_DISTANCE.getName(), REACH_MODIFIER);
			multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), AoAAttributes.vanillaWeaponDamageModifier(getDamage()));
			multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), AoAAttributes.vanillaWeaponSpeedModifier(getAttackSpeed()));
		}

		return multimap;
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(ItemUtil.getFormattedDescriptionText("items.description.maul.knockback", Enums.ItemDescriptionType.ITEM_TYPE_INFO, Double.toString((int)(knockback * 700) / 100D)));
	}
}
