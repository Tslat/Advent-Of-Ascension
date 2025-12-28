package net.tslat.aoa3.content.item.weapon.blaster;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.common.registration.AoAExplosions;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.content.entity.projectile.base.WeaponFiringContext;
import net.tslat.aoa3.content.entity.projectile.base.WeaponProjectile;
import net.tslat.aoa3.library.builder.AoAExplosionBuilder;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.tme.api.explosion.StandardExplosion;
import net.tslat.tme.api.object.RayTrace;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class Revolution extends AoABlaster<WeaponProjectile> {
	public Revolution(Item.Properties properties) {
		super(properties);
	}

	@Override
	void fireBlaster(ServerLevel level, WeaponFiringContext context) {
		fireBasicBlasterProjectile(level, context, AoAProjectiles.REVOLUTION_SHOT);
	}

	@Override
	protected void onHitBlock(Level level, WeaponProjectile effect, WeaponFiringContext context, RayTrace<Void> rayTrace, BlockState hitBlock) {
		explode(level, rayTrace.hitPos(), effect.asEntity(), context.getShooter());
	}

	@Override
	protected void onDamageEntity(Level level, WeaponProjectile effect, WeaponFiringContext context, RayTrace<?> rayTrace, Entity hitEntity, float damage) {
		explode(level, rayTrace.hitPos(), effect.asEntity(), context.getShooter());
	}

	private void explode(Level level, Vec3 position, @Nullable Entity projectile, @Nullable Entity owner) {
		if (level instanceof ServerLevel serverLevel)
			AoAExplosionBuilder.at(serverLevel, position, AoAExplosions.REVOLUTION, StandardExplosion::new).explodingEntity(projectile).explode();
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
		super.appendHoverText(stack, context, tooltip, flag);

		for (MutableComponent component : LocaleUtil.getExplosionInfoLocale(AoAExplosions.REVOLUTION, true, flag.isAdvanced(), false)) {
			tooltip.add(1, component);
		}
	}
}
