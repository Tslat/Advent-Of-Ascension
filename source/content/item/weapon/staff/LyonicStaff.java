package net.tslat.aoa3.content.item.weapon.staff;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.content.entity.projectile.base.NonPhysicalWeaponProjectile;
import net.tslat.aoa3.content.entity.projectile.base.WeaponFiringContext;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.WorldUtil;
import net.tslat.tme.api.object.builder.EffectBuilder;
import net.tslat.tme.api.util.EntityRetrievalUtil;
import net.tslat.tme.api.util.RandomUtil;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public class LyonicStaff extends AoAStaff<List<LivingEntity>> {
	public LyonicStaff(Item.Properties properties) {
		super(properties);
	}

	@Override
	public Optional<List<LivingEntity>> checkPreconditions(LivingEntity caster, ItemStack staff) {
		List<LivingEntity> targets = EntityRetrievalUtil.getEntities(caster, 10, 1, 10, LivingEntity.class, target ->
				target.isAlive() && EntityUtil.areProbablyEnemies(target, caster) && caster.hasLineOfSight(target));

		return Optional.ofNullable(targets.isEmpty() ? null : targets);
	}

	@Override
	public WeaponFiringContext.Builder createProjectileContext(ItemStack stack, @Nullable Entity shooter, InteractionHand hand) {
		return super.createProjectileContext(stack, shooter, hand).lifespan(1);
	}

	@Override
	public void cast(ServerLevel level, LivingEntity caster, ItemStack staff, InteractionHand hand, List<LivingEntity> args) {
		Vec3 casterAngle = caster.getLookAngle();

		for (int i = 0; i < 256; i++) {
			fireProjectile(level, caster, staff, hand, (serverLevel, context) -> new NonPhysicalWeaponProjectile(AoAProjectiles.LYONIC_SHOT.get(), serverLevel, context),
						   (context, projectile) -> projectile.fromArmPos()
								   .shootingTowards(casterAngle.add(new Vec3(RandomUtil.valueBetween(-1, 1), 0, RandomUtil.valueBetween(-1, 1))).normalize(), 3, 0.1f));
		}

		EffectBuilder effect = new EffectBuilder(MobEffects.WITHER, 100).level(2);

		for (LivingEntity target : args) {
			EntityUtil.applyPotions(target, caster, effect);

			if (RandomUtil.oneInNChance(150))
				WorldUtil.spawnLightning(level, caster instanceof ServerPlayer pl ? pl : null, target.getX(), target.getY(), target.getZ(), true, false);
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Keys.WITHERS_TARGETS, LocaleUtil.ItemDescriptionType.BENEFICIAL));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 2));
		super.appendHoverText(stack, context, tooltip, flag);
	}
}
