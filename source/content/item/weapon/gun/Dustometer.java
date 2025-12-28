package net.tslat.aoa3.content.item.weapon.gun;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.common.registration.item.AoAWeapons;
import net.tslat.aoa3.content.entity.projectile.base.WeaponFiringContext;
import net.tslat.aoa3.content.entity.projectile.base.WeaponProjectile;
import net.tslat.aoa3.content.item.weapon.thrown.Grenade;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.tme.api.util.RandomUtil;

import java.util.List;

public class Dustometer extends AoAGun {
	public Dustometer(Item.Properties properties) {
		super(properties);
	}

	@Override
	protected void onGunFire(ServerLevel level, WeaponFiringContext context, WeaponProjectile projectile) {
		if (RandomUtil.oneInNChance(3)) {
			Grenade grenade = AoAWeapons.GRENADE.get();
			ItemStack grenadeStack = grenade.getDefaultInstance();
			Vec3 pos = context.getShooter() != null ? context.getShooter().position() : projectile.asEntity().position();
			grenade.throwProjectile(level, grenade.createFiringContext(grenadeStack, context.getShooter(), context.weaponHand()).build(), grenadeStack);

			level.playSound(null, pos.x, pos.y, pos.z, AoASounds.ITEM_GUN_AIR_CANNON_FIRE.get(), SoundSource.PLAYERS, 1, getFiringSoundPitch(context.weaponStack()) + (float)RandomUtil.scaledGaussianValue(0.075f));
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.appendHoverText(stack, context, tooltip, flag);
	}
}
