package net.tslat.aoa3.content.item.weapon.gun;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.content.entity.projectile.base.WeaponFiringContext;
import net.tslat.aoa3.content.entity.projectile.base.WeaponProjectile;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.tme.api.object.RayTrace;
import net.tslat.tme.api.particle.ParticleBuilder;
import net.tslat.tme.internal.networking.packet.TMEParticlePacket;
import net.tslat.tme.api.util.RandomUtil;

import java.util.List;

public class FlamingFury extends AoAGun {
	public FlamingFury(Item.Properties properties) {
		super(properties);
	}

	@Override
	protected void doFiringEffects(ServerLevel level, WeaponFiringContext context, WeaponProjectile projectile) {
		super.doFiringEffects(level, context, projectile);

		TMEParticlePacket packet = new TMEParticlePacket();
		Vec3 pos = projectile.asEntity().position();

		for (int i = 0; i < 6; i++) {
			packet.particle(ParticleBuilder.forPositions(ParticleTypes.DRAGON_BREATH, pos.add(RandomUtil.scaledGaussianValue(0.2f), RandomUtil.scaledGaussianValue(0.2f), RandomUtil.scaledGaussianValue(0.2f))));
		}

		packet.sendToAllPlayersTrackingBlock(level, BlockPos.containing(pos));
	}

	@Override
	protected void onDamageEntity(Level level, WeaponProjectile projectile, RayTrace<?> rayTrace, Entity hitEntity, float damage) {
		Vec3 pos = rayTrace.hitPos();
		AreaEffectCloud cloud = new AreaEffectCloud(level, pos.x, pos.y, pos.z);

		cloud.setOwner((LivingEntity)projectile.getShooter());
		cloud.setParticle(ParticleTypes.DRAGON_BREATH);
		cloud.setRadius(1f);
		cloud.setDuration(20);
		cloud.setRadiusPerTick((5.0F - cloud.getRadius()) / (float)cloud.getDuration());
		cloud.addEffect(new MobEffectInstance(MobEffects.HARM, 1, 0));

		level.addFreshEntity(cloud);
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.appendHoverText(stack, context, tooltip, flag);
	}
}
