package net.tslat.aoa3.item.weapon.shotgun;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.capabilities.handlers.AdventGunCapability;
import net.tslat.aoa3.capabilities.providers.AdventGunProvider;
import net.tslat.aoa3.common.packet.PacketRecoil;
import net.tslat.aoa3.common.registration.CreativeTabsRegister;
import net.tslat.aoa3.common.registration.EnchantmentsRegister;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.projectiles.gun.BaseBullet;
import net.tslat.aoa3.entity.projectiles.gun.EntityLimoniteBullet;
import net.tslat.aoa3.entity.projectiles.gun.EntityMetalSlug;
import net.tslat.aoa3.event.GlobalEvents;
import net.tslat.aoa3.item.weapon.AdventWeapon;
import net.tslat.aoa3.item.weapon.gun.BaseGun;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.EntityUtil;
import net.tslat.aoa3.utils.ItemUtil;
import net.tslat.aoa3.utils.PacketUtil;
import net.tslat.aoa3.utils.StringUtil;

import javax.annotation.Nullable;
import java.util.List;

public abstract class BaseShotgun extends BaseGun implements AdventWeapon {
	protected final int pelletCount;
	protected final float knockbackFactor;

	public BaseShotgun(final double dmg, final int pellets, final int durability, final int fireDelayTicks, final float knockbackFactor, final float recoil) {
		super(dmg, durability, fireDelayTicks, recoil);

		this.pelletCount = pellets;
		this.knockbackFactor = knockbackFactor;

		setCreativeTab(CreativeTabsRegister.shotgunsTab);
	}

	public int getPelletCount() {
		return pelletCount;
	}

	@Nullable
	@Override
	public SoundEvent getFiringSound() {
		return SoundsRegister.gunShotgun;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		ItemStack stack = player.getHeldItem(hand);

		if (hand != getGunHand(stack))
			return ActionResult.newResult(EnumActionResult.FAIL, stack);

		if (player.getCooledAttackStrength(0.0f) < 1)
			return ActionResult.newResult(EnumActionResult.FAIL, stack);

		AdventGunCapability cap = (AdventGunCapability)stack.getCapability(AdventGunProvider.ADVENT_GUN, null);

		if (cap == null)
			return ActionResult.newResult(EnumActionResult.FAIL, stack);

		if (cap.getNextFireTime() <= GlobalEvents.tick) {
			BaseBullet ammo = findAndConsumeAmmo(player, this, hand);

			if (ammo != null) {
				if (!world.isRemote) {
					float form = 0.15f * EnchantmentHelper.getEnchantmentLevel(EnchantmentsRegister.form, stack);

					fireShotgun(player, hand, 0.1f * pelletCount * (1 - form), pelletCount);
				}

				if (getFiringSound() != null)
					player.world.playSound(null, player.posX, player.posY, player.posZ, getFiringSound(), SoundCategory.PLAYERS, 1.0f, 1.0f);

				stack.damageItem(1, player);
				cap.setNextFireTime(getFiringDelay());

				if (player instanceof EntityPlayerMP) {
					int control = EnchantmentHelper.getEnchantmentLevel(EnchantmentsRegister.control, stack);
					float recoiling = getRecoilForShot(stack, player) * (1 - control * 0.15f);

					PacketUtil.network.sendTo(new PacketRecoil(hand == EnumHand.OFF_HAND ? recoiling * 2.5f : recoiling, getFiringDelay()), (EntityPlayerMP)player);
				}

				return ActionResult.newResult(EnumActionResult.PASS, stack);
			}

			if (player instanceof EntityPlayerMP)
				((EntityPlayerMP)player).sendContainerToPlayer(player.inventoryContainer);
		}
		else if (cap.getNextFireTime() > GlobalEvents.tick + getFiringDelay() * 2) {
			cap.setNextFireTime(0);
		}

		return ActionResult.newResult(EnumActionResult.FAIL, stack);
	}

	public void fireShotgun(EntityLivingBase shooter, EnumHand hand, float spreadFactor, int pellets) {
		for (int i = 0; i < pellets; i++) {
			BaseBullet pellet = new EntityLimoniteBullet(shooter, this, hand, 4, 1.0f, 0, (itemRand.nextFloat() - 0.5f) * spreadFactor, (itemRand.nextFloat() - 0.5f) * spreadFactor, (itemRand.nextFloat() - 0.5f) * spreadFactor);
			shooter.world.spawnEntity(pellet);
		}
	}

	@Override
	public void doImpactDamage(Entity target, EntityLivingBase shooter, BaseBullet bullet, float bulletDmgMultiplier) {
		if (target != null) {
			float shellMod = 1;

			if (bullet.getHand() != null)
				shellMod += 0.1 * EnchantmentHelper.getEnchantmentLevel(EnchantmentsRegister.shell, shooter.getHeldItem(bullet.getHand()));

			if (EntityUtil.dealGunDamage(target, shooter, bullet, (float)getDamage() * bulletDmgMultiplier * shellMod)) {
				if (knockbackFactor > 0)
					EntityUtil.doScaledKnockback((EntityLivingBase)target, shooter, knockbackFactor, shooter.posX - target.posX, shooter.posZ - target.posZ);

				doImpactEffect(target, shooter, bullet, bulletDmgMultiplier);
			}
		}
	}

	@Override
	public BaseBullet findAndConsumeAmmo(EntityPlayer player, BaseGun gun, EnumHand hand) {
		Item ammo = ItemUtil.findAndConsumeSpecialBullet(player, gun, true, ItemRegister.spreadshot, player.getHeldItem(hand));

		if (ammo != null)
			return new EntityMetalSlug(player, gun, hand,4, 0);

		return null;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(1, ItemUtil.getFormattedDescriptionText("items.description.damage.shotgun", Enums.ItemDescriptionType.ITEM_DAMAGE, Double.toString(getDamage()), Integer.toString(pelletCount)));
		tooltip.add(ItemUtil.getFormattedDescriptionText("items.description.damage.knockback", Enums.ItemDescriptionType.ITEM_TYPE_INFO));
		tooltip.add(StringUtil.getLocaleStringWithArguments("items.description.gun.speed", Double.toString((2000 / firingDelay) / (double)100)));
		tooltip.add(ItemUtil.getFormattedDescriptionText("items.description.ammo.spreadshot", Enums.ItemDescriptionType.ITEM_AMMO_COST));
	}
}
