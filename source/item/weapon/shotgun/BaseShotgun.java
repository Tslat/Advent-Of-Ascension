package net.tslat.aoa3.item.weapon.shotgun;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.packet.AoAPackets;
import net.tslat.aoa3.common.packet.packets.GunRecoilPacket;
import net.tslat.aoa3.common.registration.AoAEnchantments;
import net.tslat.aoa3.common.registration.AoAItemGroups;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.entity.projectile.gun.BaseBullet;
import net.tslat.aoa3.entity.projectile.gun.LimoniteBulletEntity;
import net.tslat.aoa3.entity.projectile.gun.MetalSlugEntity;
import net.tslat.aoa3.item.weapon.gun.BaseGun;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.NumberUtil;

import javax.annotation.Nullable;
import java.util.List;

public class BaseShotgun extends BaseGun {
	protected final int pelletCount;
	protected final float knockbackFactor;

	public BaseShotgun(final double dmg, final int pellets, final int durability, final int fireDelayTicks, final float knockbackFactor, final float recoil) {
		super(AoAItemGroups.SHOTGUNS, dmg, durability, fireDelayTicks, recoil);

		this.pelletCount = pellets;
		this.knockbackFactor = knockbackFactor;
	}

	public int getPelletCount() {
		return pelletCount;
	}

	@Nullable
	@Override
	public SoundEvent getFiringSound() {
		return AoASounds.ITEM_SHOTGUN_FIRE.get();
	}

	@Override
	public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
		ItemStack stack = player.getItemInHand(hand);

		if (hand != getGunHand(stack))
			return ActionResult.fail(stack);

		if (player.getAttackStrengthScale(0.0f) < 1)
			return ActionResult.fail(stack);

		BaseBullet ammo = findAndConsumeAmmo(player, stack, hand);

		if (ammo != null) {
			if (!world.isClientSide) {
				float form = 0.15f * EnchantmentHelper.getItemEnchantmentLevel(AoAEnchantments.FORM.get(), stack);

				fireShotgun(player, hand, 0.1f * pelletCount * (1 - form), pelletCount);
			}

			if (getFiringSound() != null)
				player.level.playSound(null, player.getX(), player.getY(), player.getZ(), getFiringSound(), SoundCategory.PLAYERS, 1.0f, 1.0f);

			ItemUtil.damageItem(stack, player, hand);
			player.getCooldowns().addCooldown(this, getFiringDelay());

			if (player instanceof ServerPlayerEntity) {
				int control = EnchantmentHelper.getItemEnchantmentLevel(AoAEnchantments.CONTROL.get(), stack);
				float recoiling = getRecoilForShot(stack, player) * (1 - control * 0.15f);

				AoAPackets.messagePlayer((ServerPlayerEntity)player, new GunRecoilPacket(hand == Hand.OFF_HAND ? recoiling * 2.5f : recoiling, getFiringDelay()));
			}

			return ActionResult.pass(stack);
		}

		if (player instanceof ServerPlayerEntity)
			((ServerPlayerEntity)player).refreshContainer(player.inventoryMenu);

		return ActionResult.fail(stack);
	}

	public void fireShotgun(LivingEntity shooter, Hand hand, float spreadFactor, int pellets) {
		for (int i = 0; i < pellets; i++) {
			BaseBullet pellet = new LimoniteBulletEntity(shooter, this, hand, 4, 1.0f, 0, (random.nextFloat() - 0.5f) * spreadFactor, (random.nextFloat() - 0.5f) * spreadFactor, (random.nextFloat() - 0.5f) * spreadFactor);
			shooter.level.addFreshEntity(pellet);
		}
	}

	@Override
	public void doImpactDamage(Entity target, LivingEntity shooter, BaseBullet bullet, float bulletDmgMultiplier) {
		if (target != null) {
			float shellMod = 1;

			if (bullet.getHand() != null)
				shellMod += 0.1 * EnchantmentHelper.getItemEnchantmentLevel(AoAEnchantments.SHELL.get(), shooter.getItemInHand(bullet.getHand()));

			if (DamageUtil.dealGunDamage(target, shooter, bullet, (float)getDamage() * bulletDmgMultiplier * shellMod)) {
				if (knockbackFactor > 0 && target instanceof LivingEntity)
					DamageUtil.doScaledKnockback((LivingEntity)target, shooter, knockbackFactor, shooter.getX() - target.getX(), shooter.getZ() - target.getZ());

				doImpactEffect(target, shooter, bullet, bulletDmgMultiplier);
			}
		}
	}

	@Override
	public BaseBullet findAndConsumeAmmo(PlayerEntity player, ItemStack gunStack, Hand hand) {
		if (ItemUtil.findInventoryItem(player, new ItemStack(AoAItems.SPREADSHOT.get()), true, 1 + EnchantmentHelper.getItemEnchantmentLevel(AoAEnchantments.GREED.get(), gunStack)))
			return new MetalSlugEntity(player, (BaseGun)gunStack.getItem(), hand, 4, 0);

		return null;
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
		tooltip.add(1, LocaleUtil.getFormattedItemDescriptionText("items.description.damage.shotgun", LocaleUtil.ItemDescriptionType.ITEM_DAMAGE, new StringTextComponent(NumberUtil.roundToNthDecimalPlace((float)getDamage() * (1 + (0.1f * EnchantmentHelper.getItemEnchantmentLevel(AoAEnchantments.SHELL.get(), stack))), 2)), LocaleUtil.numToComponent(pelletCount)));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Constants.KNOCKBACK, LocaleUtil.ItemDescriptionType.ITEM_TYPE_INFO));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Constants.FIRING_SPEED, LocaleUtil.ItemDescriptionType.NEUTRAL, new StringTextComponent(NumberUtil.roundToNthDecimalPlace(20 / (float)firingDelay, 2))));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Constants.AMMO_ITEM, LocaleUtil.ItemDescriptionType.ITEM_AMMO_COST, LocaleUtil.getLocaleMessage(AoAItems.SPREADSHOT.get().getDescriptionId())));
	}
}
