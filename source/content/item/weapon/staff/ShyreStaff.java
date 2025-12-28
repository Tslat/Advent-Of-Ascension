package net.tslat.aoa3.content.item.weapon.staff;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.worldgen.AoADimensions;
import net.tslat.aoa3.content.entity.projectile.base.WeaponFiringContext;
import net.tslat.aoa3.content.entity.projectile.base.WeaponProjectile;
import net.tslat.aoa3.content.entity.projectile.staff.ShyreShotEntity;
import net.tslat.aoa3.event.custom.AoAEvents;
import net.tslat.aoa3.event.custom.events.MagicTeleportEvent;
import net.tslat.aoa3.util.AdvancementUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.WorldUtil;
import net.tslat.tme.api.object.RayTrace;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ShyreStaff extends AoAStaff<Object> {
	public ShyreStaff(Item.Properties properties) {
		super(properties);
	}

	@Override
	public WeaponFiringContext.Builder createProjectileContext(ItemStack stack, @Nullable Entity shooter, InteractionHand hand) {
		return super.createProjectileContext(stack, shooter, hand).lifespan(120);
	}

	@Override
	public void cast(ServerLevel level, LivingEntity caster, ItemStack staff, InteractionHand hand, Object args) {
		fireProjectile(level, caster, staff, hand, ShyreShotEntity::new);
	}

	@Override
	protected void onHitBlock(Level level, @Nullable WeaponProjectile projectile, @Nullable WeaponFiringContext context, @Nullable RayTrace<Void> rayTrace, BlockState hitBlock) {
		if (projectile.getShooter() instanceof LivingEntity caster) {
			Vec3 testVec = rayTrace.hitPos();
			BlockPos.MutableBlockPos testPos = BlockPos.containing(testVec).mutable();
			BlockState state = level.getBlockState(testPos);
			Vec3 shotMotion = projectile.asEntity().getDeltaMovement();
			int tests = 0;

			while (tests <= 10 && !(state = level.getBlockState(testPos)).isAir()) {
				testVec = testVec.subtract(shotMotion.x() * 0.15f, shotMotion.y() * 0.15f, shotMotion.z() * 0.15f);
				testPos.set(testVec.x() + caster.getBbWidth(), testVec.y(), testVec.z() + caster.getBbWidth());
				tests++;
			}

			if (state.isAir()) {
				MagicTeleportEvent event = AoAEvents.fireMagicalTeleport(caster, projectile.asEntity(), caster, testVec);

				if (event.isCanceled())
					return;

				testVec = event.getTarget();
			}

			caster.teleportTo(testVec.x(), testVec.y(), testVec.z());

			if (caster instanceof ServerPlayer player && WorldUtil.isWorld(level, AoADimensions.LUNALUS))
				AdvancementUtil.grantCriterion(player, AdventOfAscension.id("lunalus/200_iq"), "lunalus_shyre_staff_travel");
		}
	}

	@Override
	protected void onDamageEntity(ServerLevel level, @Nullable WeaponProjectile projectile, @Nullable WeaponFiringContext context, @Nullable RayTrace<?> rayTrace, Entity hitEntity, float damage) {
		if (projectile.getShooter() instanceof LivingEntity caster) {
			MagicTeleportEvent event = AoAEvents.fireMagicalTeleport(caster, projectile.asEntity(), caster, rayTrace.hitPos());

			if (!event.isCanceled())
				caster.teleportTo(event.getTargetX(), event.getTargetY(), event.getTargetZ());
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.appendHoverText(stack, context, tooltip, flag);
	}
}
