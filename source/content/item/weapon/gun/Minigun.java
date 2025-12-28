package net.tslat.aoa3.content.item.weapon.gun;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.content.entity.projectile.base.WeaponFiringContext;
import net.tslat.aoa3.content.entity.projectile.base.WeaponProjectile;
import net.tslat.tme.api.sound.SoundBuilder;

public class Minigun extends AoAGun {
	public Minigun(Item.Properties properties) {
		super(properties);
	}

	@Override
	public void releaseUsing(ItemStack stack, Level level, LivingEntity shooter, int timeCharged) {
		SoundBuilder.following(AoASounds.ITEM_GUN_MINIGUN_WINDDOWN, shooter).play();
	}

	@Override
	protected void doFiringSound(ServerLevel level, WeaponFiringContext context, WeaponProjectile projectile) {
		Entity shooter = context.getShooter();

		getFiringSound(context.weaponStack()).ifPresent(sound ->
																(shooter == null ?
																 SoundBuilder.at(sound, level, projectile.asEntity().position()).category(SoundSource.PLAYERS) :
																 SoundBuilder.following(sound, shooter))
																		.pitch(getFiringSoundPitch(context.weaponStack()))
																		.play());
	}
}
