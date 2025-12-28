package net.tslat.aoa3.content.item.weapon.blaster;

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
import net.tslat.aoa3.content.entity.projectile.base.WeaponFiringContext;
import net.tslat.aoa3.content.entity.projectile.base.WeaponProjectile;
import net.tslat.aoa3.content.entity.projectile.blaster.HeavyShowerShotEntity;
import net.tslat.aoa3.content.entity.projectile.blaster.ShowerShotEntity;
import net.tslat.aoa3.content.entity.projectile.blaster.WeightedShowerShotEntity;
import net.tslat.aoa3.library.builder.AoAExplosionBuilder;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.tme.api.explosion.StandardExplosion;
import net.tslat.tme.api.object.RayTrace;

import java.util.List;

public class ApocoShower extends AoABlaster<WeaponProjectile> {
	public ApocoShower(Item.Properties properties) {
		super(properties);
	}

	@Override
	void fireBlaster(ServerLevel level, WeaponFiringContext context) {
		fireBlasterProjectile(level, context, ShowerShotEntity::new);
		fireBlasterProjectile(level, context, WeightedShowerShotEntity::new);
		fireBlasterProjectile(level, context, HeavyShowerShotEntity::new);
	}

	@Override
	protected boolean doBlockImpact(Level level, WeaponProjectile effect, WeaponFiringContext context, RayTrace<Void> rayTrace, BlockState hitBlock) {
		explode(level, effect.asEntity(), rayTrace.hitPos());

		return true;
	}

	@Override
	protected boolean doEntityImpact(Level level, WeaponProjectile effect, WeaponFiringContext context, RayTrace<?> rayTrace, Entity hitEntity) {
		explode(level, effect.asEntity(), rayTrace.hitPos());

		return true;
	}

	private void explode(Level level, Projectile projectile, Vec3 position) {
		if (level instanceof ServerLevel serverLevel)
			AoAExplosionBuilder.at(serverLevel, position, AoAExplosions.APOCO_SHOWER, StandardExplosion::new).explodingEntity(projectile).explode();
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.appendHoverText(stack, context, tooltip, flag);

		for (MutableComponent component : LocaleUtil.getExplosionInfoLocale(AoAExplosions.APOCO_SHOWER, false, flag.isAdvanced(), false)) {
			tooltip.add(2, component);
		}
	}
}
