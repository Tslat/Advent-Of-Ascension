package net.tslat.aoa3.content.item.weapon.cannon;

import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.content.entity.projectile.cannon.CannonballEntity;
import net.tslat.aoa3.content.entity.projectile.gun.BaseBullet;
import net.tslat.aoa3.content.item.weapon.gun.BaseGun;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.aoa3.util.LocaleUtil;

import javax.annotation.Nullable;
import java.util.List;

public abstract class BaseCannon extends BaseGun {
	public BaseCannon(double dmg, int durability, int fireDelayTicks, float recoil) {
		super(dmg, durability, fireDelayTicks, recoil);
	}

	@Override
	public Item getAmmoItem() {
		return AoAItems.CANNONBALL.get();
	}

	@Override
	public boolean isFullAutomatic() {
		return false;
	}

	@Override
	public void doImpactDamage(Entity target, LivingEntity shooter, BaseBullet bullet, Vec3 impactPosition, float bulletDmgMultiplier) {
		if (target != null) {
			if (target instanceof LivingEntity)
				bulletDmgMultiplier *= 1 + (((LivingEntity)target).getAttribute(Attributes.ARMOR).getValue() * 1.50) / 100;

			if (DamageUtil.dealGunDamage(target, shooter, bullet, (float)getDamage() * bulletDmgMultiplier)) {
				if (target instanceof Player && ((Player)target).isBlocking())
					((Player)target).disableShield(true);

				if (target instanceof LivingEntity)
					DamageUtil.doScaledKnockback((LivingEntity)target, shooter, ((float)getDamage() * bulletDmgMultiplier) / 10f, shooter.getX() - target.getX(), shooter.getZ() - target.getZ());

				doImpactEffect(target, shooter, bullet, bulletDmgMultiplier);
			}
		}
	}

	@Override
	public BaseBullet createProjectileEntity(LivingEntity shooter, ItemStack gunStack, InteractionHand hand) {
		return new CannonballEntity(shooter, this, hand, 120, 0);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
		super.appendHoverText(stack, world, tooltip, flag);

		tooltip.add(2, LocaleUtil.getFormattedItemDescriptionText("items.description.cannon.damage", LocaleUtil.ItemDescriptionType.ITEM_TYPE_INFO));
	}
}
