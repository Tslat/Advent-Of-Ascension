package net.tslat.aoa3.content.item.weapon.gun;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.content.entity.projectile.base.WeaponFiringContext;
import net.tslat.aoa3.content.entity.projectile.base.WeaponProjectile;
import net.tslat.aoa3.content.entity.projectile.gun.ShoeShotEntity;
import net.tslat.tme.api.sound.SoundBuilder;
import net.tslat.aoa3.util.LocaleUtil;

import java.util.List;

public class ShoeFlinger extends AoAGun {
	public ShoeFlinger(Item.Properties properties) {
		super(properties);
	}

	@Override
	public WeaponProjectile createProjectileEntity(Level level, WeaponFiringContext context) {
		return new ShoeShotEntity(level, context);
	}

	@Override
	protected void doFiringSound(ServerLevel level, WeaponFiringContext context, WeaponProjectile projectile) {
		Entity shooter = context.getShooter();

		getFiringSound(context.weaponStack()).ifPresent(sound ->
																(shooter == null ?
																 SoundBuilder.at(sound, level, projectile.asEntity().position()).category(SoundSource.PLAYERS) :
																 SoundBuilder.following(sound, shooter))
																		.pitch(getFiringSoundPitch(context.weaponStack()))
																		.varyPitch(0.01f)
																		.play());
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.UNIQUE, 1));

		super.appendHoverText(stack, context, tooltip, flag);
	}
}
