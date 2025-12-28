package net.tslat.aoa3.content.item.weapon.staff;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.content.entity.projectile.base.NonPhysicalWeaponProjectile;
import net.tslat.aoa3.content.entity.projectile.base.WeaponFiringContext;
import net.tslat.aoa3.content.entity.projectile.base.WeaponProjectile;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.tme.api.object.builder.EffectBuilder;
import net.tslat.tme.api.object.RayTrace;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public class LunarStaff extends AoAStaff<Vec3> {
	public LunarStaff(Item.Properties properties) {
		super(properties);
	}

	@Override
	public Optional<Vec3> checkPreconditions(LivingEntity caster, ItemStack staff) {
		RayTrace<LivingEntity> rayTrace = RayTrace.createForEyeline(caster, 70).forEntities(LivingEntity.class).run();

		return rayTrace.missed() ? Optional.empty() : Optional.of(rayTrace.hitPos());
	}

	@Override
	public void cast(ServerLevel level, LivingEntity caster, ItemStack staff, InteractionHand hand, Vec3 args) {
		fireProjectile(level, caster, staff, hand, (serverLevel, context) -> new NonPhysicalWeaponProjectile(AoAProjectiles.LUNAR_FALL.get(), serverLevel, context),
					   (context, projectile) -> projectile.fromPos(args.add(0, 30, 0)).shootingTowards(new Vec3(0, -1, 0), 3, 0.1f));
	}

	@Override
	protected void onDamageEntity(ServerLevel level, @Nullable WeaponProjectile projectile, @Nullable WeaponFiringContext context, @Nullable RayTrace<?> rayTrace, Entity hitEntity, float damage) {
		EntityUtil.applyPotions(hitEntity, projectile == null ? null : projectile.getShooter(), new EffectBuilder(MobEffects.GLOWING, 200));
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.appendHoverText(stack, context, tooltip, flag);
	}
}
