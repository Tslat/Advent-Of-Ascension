package net.tslat.aoa3.item.weapon.greatblade;

import com.google.common.collect.Multimap;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;
import net.tslat.aoa3.common.registration.CreativeTabsRegister;
import net.tslat.aoa3.common.registration.EnchantmentsRegister;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.item.weapon.AdventWeapon;
import net.tslat.aoa3.item.weapon.LongReachWeapon;

public abstract class BaseGreatblade extends Item implements AdventWeapon, LongReachWeapon {
	protected double dmg;
	protected double speed;

	public BaseGreatblade(final double dmg, final double speed, final int durability) {
		setCreativeTab(CreativeTabsRegister.greatbladesTab);
		this.dmg = dmg;
		this.speed = speed == 0 ? -3.2400001287460327D : speed;
		setMaxDamage(durability);
		setMaxStackSize(1);
		setFull3D();
		setNoRepair();
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
	public boolean onLeftClickEntity(final ItemStack stack, final EntityPlayer player, final Entity entity) {
		return true;
	}

	@Override
	public int getItemEnchantability() {
		return 0;
	}

	@Override
	public float getReach() {
		return 5.5F;
	}

	public double getDamage() {
		return dmg;
	}

	public double getAttackSpeed() {
		return speed;
	}

	@Override
	public float getDestroySpeed(ItemStack stack, IBlockState state) {
		Material material = state.getMaterial();

		if (material == Material.WEB) {
			return 25.0f;
		}
		else if (material == Material.PLANTS || material == Material.VINE || material == Material.CORAL || material == Material.LEAVES || material == Material.GOURD) {
			return 2.0f;
		}
		else {
			return 1.0f;
		}
	}

	@Override
	public boolean onBlockDestroyed(ItemStack stack, World world, IBlockState state, BlockPos pos, EntityLivingBase holder) {
		if (!world.isRemote && (double)state.getBlockHardness(world, pos) != 0.0D)
			stack.damageItem(2, holder);

		return true;
	}

	@Override
	public boolean onEntitySwing(final EntityLivingBase holder, final ItemStack stack) {
		return false;
	}

	public void attackEntity(ItemStack stack, Entity target, EntityLivingBase attacker, float dmg) {
		if (attacker instanceof EntityPlayer) {
			if (target instanceof EntityFireball) {
				target.attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer)attacker), dmg);
				return;
			}

			if (dmg < 0)
				dmg = (float)this.dmg;

			dmg++;

			PotionEffect str = attacker.getActivePotionEffect(MobEffects.STRENGTH);
			PotionEffect weak = attacker.getActivePotionEffect(MobEffects.WEAKNESS);
			float targetHealth = 0;

			if (target instanceof EntityLivingBase)
				targetHealth = ((EntityLivingBase)target).getHealth();

			if (str != null)
				dmg += (str.getAmplifier() + 1) * 3;

			if (weak != null)
				dmg -= (weak.getAmplifier() + 1) * 4;

			float cooldownMultiplier = (((EntityPlayer)attacker).getCooledAttackStrength(0f));
			final int severModifier = EnchantmentHelper.getEnchantmentLevel(EnchantmentsRegister.sever, stack);
			final float finalDmg = (dmg + severModifier) * (cooldownMultiplier + 0.01f);

			if (target instanceof EntityDragon ? ((EntityDragon)target).attackEntityFromPart((MultiPartEntityPart)(target.getParts())[0], DamageSource.causePlayerDamage((EntityPlayer)attacker), finalDmg) : target.attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer)attacker), finalDmg)) {
				if (attacker.world instanceof WorldServer) {
					if (cooldownMultiplier >= 1.0) {
						double d0 = (double)(-MathHelper.sin(attacker.rotationYaw * 0.017453292F));
						double d1 = (double)MathHelper.cos(attacker.rotationYaw * 0.017453292F);

						((WorldServer)attacker.world).spawnParticle(EnumParticleTypes.SWEEP_ATTACK, target.posX + d0, target.posY + (double)target.height * 0.5D, target.posZ + d1, 0, d0, 0.0D, d1, 0.0D);
						attacker.world.playSound(null, attacker.posX, attacker.posY, attacker.posZ, SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP, SoundCategory.PLAYERS, 1.0f, 1.0f);
					}

					int hearts = 0;

					if (target instanceof EntityLivingBase)
						hearts = (int)((targetHealth - ((EntityLivingBase)target).getHealth()) / 2);

					if (hearts > 0) {
						((WorldServer)attacker.world).spawnParticle(EnumParticleTypes.DAMAGE_INDICATOR, target.posX, target.posY + (double)(target.height * 0.5F), target.posZ, hearts, 0.1D, 0.0D, 0.1D, 0.2D);
					}
				}

				stack.damageItem(1, attacker);
			}
		}
		else {
			if (dmg < 0)
				dmg = (float)this.dmg;

			PotionEffect str = attacker.getActivePotionEffect(MobEffects.STRENGTH);
			PotionEffect weak = attacker.getActivePotionEffect(MobEffects.WEAKNESS);

			if (str != null)
				dmg += (str.getAmplifier() + 1) * 3;

			if (weak != null)
				dmg -= (weak.getAmplifier() + 1) * 4;

			dmg += EnchantmentHelper.getEnchantmentLevel(EnchantmentsRegister.sever, stack);

			target.attackEntityFrom(DamageSource.causeMobDamage(attacker), dmg);
		}
	}

	@Override
	public boolean getIsRepairable(ItemStack stack, ItemStack repairMaterial) {
		return repairMaterial.getItem() != Items.ENCHANTED_BOOK && OreDictionary.itemMatches(repairMaterial, new ItemStack(ItemRegister.ingotRosite), false) || super.getIsRepairable(stack, repairMaterial);
	}

	@SideOnly(Side.CLIENT)
	public boolean isFull3D() {
		return true;
	}

	@Override
	public Multimap<String, AttributeModifier> getAttributeModifiers(EntityEquipmentSlot equipmentSlot, ItemStack stack) {
		Multimap<String, AttributeModifier> multimap = super.getAttributeModifiers(equipmentSlot, stack);

		if (equipmentSlot == EntityEquipmentSlot.MAINHAND) {
			multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", this.dmg, 0));
			multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", this.speed, 0));
		}

		return multimap;
	}
}
