package net.tslat.aoa3.content.item.weapon.gun;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.content.entity.projectile.base.PhysicalWeaponProjectile;
import net.tslat.aoa3.content.entity.projectile.base.WeaponFiringContext;
import net.tslat.aoa3.content.entity.projectile.base.WeaponProjectile;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.tme.api.util.EntityRetrievalUtil;
import net.tslat.tme.api.object.RayTrace;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class HotShot extends AoAGun {
	public HotShot(Item.Properties properties) {
		super(properties);
	}

	@Override
	public WeaponProjectile createProjectileEntity(Level level, WeaponFiringContext context) {
		return new PhysicalWeaponProjectile(AoAProjectiles.HOT_SHOT.get(), level, context);
	}

	@Override
	protected void onDamageEntity(Level level, WeaponProjectile projectile, RayTrace<?> rayTrace, Entity hitEntity, float damage) {
		if (level instanceof ServerLevel serverLevel)
			doFlareBurst(serverLevel, rayTrace.hitPos(), projectile.getShooter());
	}

	@Override
	protected void onHitBlock(Level level, WeaponProjectile projectile, RayTrace<Void> rayTrace, BlockState hitBlock) {
		if (level instanceof ServerLevel serverLevel)
			doFlareBurst(serverLevel, rayTrace.hitPos(), projectile.getShooter());
	}

	protected void doFlareBurst(ServerLevel level, Vec3 hitPos, @Nullable Entity shooter) {
		AreaEffectCloud cloud = new AreaEffectCloud(level, hitPos.x, hitPos.y, hitPos.z);

		if (shooter instanceof LivingEntity livingShooter)
			cloud.setOwner(livingShooter);

		cloud.setParticle(ParticleTypes.FLAME);
		cloud.setRadius(1f);
		cloud.setDuration(5);
		cloud.setRadiusPerTick(0.4f);
		cloud.setWaitTime(0);

		level.addFreshEntity(cloud);

		for (LivingEntity entity : EntityRetrievalUtil.getEntities(level, cloud.getBoundingBox().inflate(2, 1, 2), LivingEntity.class, EntityUtil::isHostileMob)) {
			entity.igniteForSeconds(4);
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));

		super.appendHoverText(stack, context, tooltip, flag);
	}
}
