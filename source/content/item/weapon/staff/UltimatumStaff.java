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
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.content.entity.projectile.base.WeaponFiringContext;
import net.tslat.aoa3.content.entity.projectile.base.WeaponProjectile;
import net.tslat.aoa3.scheduling.AoAScheduler;
import net.tslat.aoa3.scheduling.sync.UltimatumStaffTask;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.tme.api.object.RayTrace;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class UltimatumStaff extends AoAStaff<Object> {
	public UltimatumStaff(Item.Properties properties) {
		super(properties);
	}

	@Override
	public void cast(ServerLevel level, LivingEntity caster, ItemStack staff, InteractionHand hand, Object args) {
		fireProjectile(level, caster, staff, hand, AoAProjectiles.ULTIMATUM_SHOT);
	}

	@Override
	protected void onDamageEntity(ServerLevel level, @Nullable WeaponProjectile projectile, @Nullable WeaponFiringContext context, @Nullable RayTrace<?> rayTrace, Entity hitEntity, float damage) {
		if (hitEntity instanceof LivingEntity target && !EntityUtil.isImmuneToSpecialAttacks(target) && projectile.getShooter() instanceof LivingEntity caster) {
			Vec3 lookVec = caster.getLookAngle();

			double posX = caster.getX() + lookVec.x * 4;
			double posZ = caster.getZ() + lookVec.z * 4;

			target.absMoveTo(posX, caster.getY(), posZ, (caster.yHeadRot + 180) % 360, 0);
			target.setYHeadRot((caster.yHeadRot + 180) % 360);
			target.teleportTo(posX, caster.getY(), posZ);

			AoAScheduler.schedule(2, new UltimatumStaffTask(caster, target));
		}
	}

	private static void doCastTick() {

	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.UNIQUE, 1));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Keys.SPEC_IMMUNE, LocaleUtil.ItemDescriptionType.HARMFUL));
		super.appendHoverText(stack, context, tooltip, flag);
	}
}
