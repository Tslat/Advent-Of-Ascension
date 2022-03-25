package net.tslat.aoa3.content.item.weapon.cannon;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAItemGroups;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.content.entity.projectile.cannon.CannonballEntity;
import net.tslat.aoa3.content.entity.projectile.gun.BaseBullet;
import net.tslat.aoa3.content.item.weapon.gun.BaseGun;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.aoa3.util.LocaleUtil;

import javax.annotation.Nullable;
import java.util.List;

public abstract class BaseCannon extends BaseGun {
	public BaseCannon(double dmg, int durability, int fireDelayTicks, float recoil) {
		super(AoAItemGroups.CANNONS, dmg, durability, fireDelayTicks, recoil);
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
	public void doImpactDamage(Entity target, LivingEntity shooter, BaseBullet bullet, float bulletDmgMultiplier) {
		if (target != null) {
			if (target instanceof LivingEntity)
				bulletDmgMultiplier *= 1 + (((LivingEntity)target).getAttribute(Attributes.ARMOR).getValue() * 1.50) / 100;

			if (DamageUtil.dealGunDamage(target, shooter, bullet, (float)getDamage() * bulletDmgMultiplier)) {
				if (target instanceof PlayerEntity && ((PlayerEntity)target).isBlocking())
					((PlayerEntity)target).disableShield(true);

				if (target instanceof LivingEntity)
					DamageUtil.doScaledKnockback((LivingEntity)target, shooter, ((float)getDamage() * bulletDmgMultiplier) / 10f, shooter.getX() - target.getX(), shooter.getZ() - target.getZ());

				doImpactEffect(target, shooter, bullet, bulletDmgMultiplier);
			}
		}
	}

	@Override
	public BaseBullet createProjectileEntity(LivingEntity shooter, ItemStack gunStack, Hand hand) {
		return new CannonballEntity(shooter, this, hand, 120, 0);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
		super.appendHoverText(stack, world, tooltip, flag);

		tooltip.add(2, LocaleUtil.getFormattedItemDescriptionText("items.description.cannon.damage", LocaleUtil.ItemDescriptionType.ITEM_TYPE_INFO));
	}
}
