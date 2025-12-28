package net.tslat.aoa3.content.item.weapon.cannon;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.common.registration.AoAExplosions;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.content.entity.projectile.base.PhysicalWeaponProjectile;
import net.tslat.aoa3.content.entity.projectile.base.WeaponFiringContext;
import net.tslat.aoa3.content.entity.projectile.base.WeaponProjectile;
import net.tslat.aoa3.library.builder.AoAExplosionBuilder;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.tme.api.explosion.StandardExplosion;
import net.tslat.tme.api.object.RayTrace;

import java.util.List;

public class BlissfulBlast extends AoACannon {
	public BlissfulBlast(Item.Properties properties) {
		super(properties);
	}

	@Override
	public WeaponProjectile createProjectileEntity(Level level, WeaponFiringContext context) {
		return new PhysicalWeaponProjectile(AoAProjectiles.SMILEY_CANNONBALL.get(), level, context);
	}

	@Override
	protected void onDamageEntity(Level level, WeaponProjectile projectile, RayTrace<?> rayTrace, Entity hitEntity, float damage) {
		explode(projectile.asEntity(), rayTrace.hitPos());
	}

	@Override
	public boolean doBlockImpact(Level level, WeaponProjectile projectile, RayTrace<Void> rayTrace, BlockState hitBlock) {
		if (super.doBlockImpact(level, projectile, rayTrace, hitBlock)) {
			explode(projectile.asEntity(), rayTrace.hitPos());

			return true;
		}

		return false;
	}

	protected void explode(Projectile projectile, Vec3 position) {
		if (projectile.level() instanceof ServerLevel level)
			AoAExplosionBuilder.at(level, position, AoAExplosions.BLISSFUL_BLAST, StandardExplosion::new).explodingEntity(projectile).explode();
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
		super.appendHoverText(stack, context, tooltip, flag);

		for (MutableComponent component : LocaleUtil.getExplosionInfoLocale(AoAExplosions.BLISSFUL_BLAST, true, flag.isAdvanced(), false)) {
			tooltip.add(2, component);
		}
	}
}
