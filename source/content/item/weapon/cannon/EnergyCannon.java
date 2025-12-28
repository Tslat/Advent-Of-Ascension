package net.tslat.aoa3.content.item.weapon.cannon;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.content.entity.projectile.base.PhysicalWeaponProjectile;
import net.tslat.aoa3.content.entity.projectile.base.WeaponFiringContext;
import net.tslat.aoa3.content.entity.projectile.base.WeaponProjectile;
import net.tslat.aoa3.library.object.extension.MutableFloat;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.tme.api.object.RayTrace;

import java.util.List;

public class EnergyCannon extends AoACannon {
	public EnergyCannon(Item.Properties properties) {
		super(properties);
	}

	@Override
	public WeaponProjectile createProjectileEntity(Level level, WeaponFiringContext context) {
		return new PhysicalWeaponProjectile(AoAProjectiles.ENERGY_SHOT.get(), level, context);
	}

	@Override
	protected void modifyImpactDamage(ServerLevel level, WeaponProjectile projectile, RayTrace<?> rayTrace, Entity hitEntity, DamageSource source, MutableFloat damage) {
		super.modifyImpactDamage(level, projectile, rayTrace, hitEntity, source, damage);

		damage.multiply(1 / 3f);
	}

	@Override
	protected void onDamageEntity(Level level, WeaponProjectile projectile, RayTrace<?> rayTrace, Entity hitEntity, float damage) {
		super.onDamageEntity(level, projectile, rayTrace, hitEntity, damage);

		DamageUtil.doEnergyProjectileAttack(projectile.getShooter(), projectile.asEntity(), hitEntity, source -> damage / 2f);
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.appendHoverText(stack, context, tooltip, flag);
	}
}
