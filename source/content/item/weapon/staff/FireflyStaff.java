package net.tslat.aoa3.content.item.weapon.staff;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.content.entity.projectile.base.WeaponFiringContext;
import net.tslat.aoa3.content.entity.projectile.base.WeaponProjectile;
import net.tslat.aoa3.content.entity.projectile.staff.FireflyShotEntity;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.tme.api.util.RandomUtil;
import net.tslat.tme.api.object.RayTrace;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class FireflyStaff extends AoAStaff<Object> {
	public FireflyStaff(Item.Properties properties) {
		super(properties);
	}

	@Override
	public void cast(ServerLevel level, LivingEntity caster, ItemStack staff, InteractionHand hand, Object args) {
		fireProjectile(level, caster, staff, hand, FireflyShotEntity::new);
	}

	@Override
	protected void onDamageEntity(ServerLevel level, @Nullable WeaponProjectile projectile, @Nullable WeaponFiringContext context, @Nullable RayTrace<?> rayTrace, Entity hitEntity, float damage) {
		if (projectile instanceof FireflyShotEntity fireflyShot && fireflyShot.shouldSplitFrom(hitEntity)) {
			hitEntity.igniteForSeconds(5);

			for (int i = 0; i < RandomUtil.numberBetween(1, 7); i++) {
				FireflyShotEntity splitShot = fireflyShot.splitOnImpact(hitEntity).fromPos(rayTrace.hitPos()).withVelocity(new Vec3(RandomUtil.scaledGaussianValue(0.35f), 1.4f, RandomUtil.scaledGaussianValue(0.35f)));

				level.addFreshEntity(splitShot);
			}
		}

		hitEntity.igniteForSeconds(5);
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.appendHoverText(stack, context, tooltip, flag);
	}
}
