package net.tslat.aoa3.item.weapon.sniper;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.tslat.aoa3.client.gui.overlay.ScopeOverlayRenderer;
import net.tslat.aoa3.common.packet.AoAPackets;
import net.tslat.aoa3.common.packet.packets.GunRecoilPacket;
import net.tslat.aoa3.common.registration.AoAEnchantments;
import net.tslat.aoa3.common.registration.AoAItemGroups;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.entity.projectile.gun.BaseBullet;
import net.tslat.aoa3.entity.projectile.gun.SniperSlugEntity;
import net.tslat.aoa3.item.weapon.gun.BaseGun;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.NumberUtil;

import javax.annotation.Nullable;
import java.util.List;

public abstract class BaseSniper extends BaseGun {
	public BaseSniper(double dmg, int durability, int fireDelayTicks, float recoil) {
		super(AoAItemGroups.SNIPERS, dmg, durability, fireDelayTicks, recoil);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
		ItemStack stack = player.getHeldItem(hand);

		if (hand != Hand.MAIN_HAND)
			return ActionResult.resultFail(stack);

		if (player.getCooledAttackStrength(0.0f) < 1)
			return ActionResult.resultFail(stack);

		BaseBullet bullet = findAndConsumeAmmo(player, stack, hand);

		if (bullet != null) {
			if (!world.isRemote)
				fireSniper(player, hand, bullet);

			if (getFiringSound() != null)
				player.world.playSound(null, player.getPosX(), player.getPosY(), player.getPosZ(), getFiringSound(), SoundCategory.PLAYERS, 1.0f, 1.0f);

			ItemUtil.damageItem(stack, player, hand);
			player.addStat(Stats.ITEM_USED.get(this));
			player.getCooldownTracker().setCooldown(this, getFiringDelay());


			return ActionResult.resultPass(stack);
		}

		if (player instanceof ServerPlayerEntity)
			((ServerPlayerEntity)player).sendContainerToPlayer(player.container);

		return ActionResult.resultFail(stack);
	}

	public void fireSniper(LivingEntity shooter, Hand hand, BaseBullet bullet) {
		if (shooter.isSneaking() && shooter.onGround) {
			if (shooter instanceof ServerPlayerEntity) {
				int control = EnchantmentHelper.getEnchantmentLevel(AoAEnchantments.CONTROL.get(), shooter.getHeldItem(hand));
				float recoiling = getRecoilForShot(shooter.getHeldItem(hand), shooter) * (1 - control * 0.15f);

				AoAPackets.messagePlayer((ServerPlayerEntity)shooter, new GunRecoilPacket(recoiling, getFiringDelay()));
			}
		}
		else {
			bullet.shoot(shooter, shooter.rotationPitch, shooter.rotationYaw, 0.0f, 20.0f, 50.0f);

			if (shooter instanceof ServerPlayerEntity)
				AoAPackets.messagePlayer((ServerPlayerEntity)shooter, new GunRecoilPacket(getRecoil() * 2f, getFiringDelay()));
		}

		shooter.world.addEntity(bullet);
	}

	@Override
	public void doImpactDamage(Entity target, LivingEntity shooter, BaseBullet bullet, float bulletDmgMultiplier) {
		super.doImpactDamage(target, shooter, bullet, bullet.getAge() <= 0 ? bulletDmgMultiplier * 0.5f : bulletDmgMultiplier);
	}

	@Override
	public BaseBullet findAndConsumeAmmo(PlayerEntity player, ItemStack gunStack, Hand hand) {
		if (ItemUtil.findInventoryItem(player, new ItemStack(AoAItems.METAL_SLUG.get()), true, 1 + EnchantmentHelper.getEnchantmentLevel(AoAEnchantments.GREED.get(), gunStack)))
			return new SniperSlugEntity(player, (BaseGun)gunStack.getItem(), 0);

		return null;
	}

	@OnlyIn(Dist.CLIENT)
	public ScopeOverlayRenderer.Type getScopeType() {
		return ScopeOverlayRenderer.Type.BASIC;
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
		tooltip.add(1, LocaleUtil.getFormattedItemDescriptionText("items.description.damage.gun", LocaleUtil.ItemDescriptionType.ITEM_DAMAGE, NumberUtil.roundToNthDecimalPlace((float)getDamage() * (1 + (0.1f * EnchantmentHelper.getEnchantmentLevel(AoAEnchantments.SHELL.get(), stack))), 2)));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("items.description.sniper.use", LocaleUtil.ItemDescriptionType.ITEM_TYPE_INFO));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Constants.FIRING_SPEED, LocaleUtil.ItemDescriptionType.NEUTRAL, Double.toString((2000 / getFiringDelay()) / (double)100)));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Constants.AMMO_ITEM, LocaleUtil.ItemDescriptionType.ITEM_AMMO_COST, LocaleUtil.getItemName(AoAItems.METAL_SLUG.get())));
	}
}
