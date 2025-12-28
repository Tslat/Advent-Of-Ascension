package net.tslat.aoa3.content.item.weapon.bow;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.util.LocaleUtil;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class PrimordialBow extends AoABow {
	public PrimordialBow(Item.Properties properties) {
		super(properties);
	}

	@Override
	public void onBlockImpact(Projectile projectile, @Nullable Entity shooter, BlockHitResult hitResult, ItemStack stack) {
		doCloud(projectile.level(), hitResult.getLocation());
	}

	@Override
	public void onEntityImpact(Projectile projectile, @Nullable Entity shooter, EntityHitResult hitResult, ItemStack stack, float velocity) {
		doCloud(projectile.level(), hitResult.getLocation());
	}

	protected void doCloud(Level level, Vec3 position) {
		AreaEffectCloud cloud = new AreaEffectCloud(level, position.x, position.y, position.z);

		cloud.addEffect(new MobEffectInstance(MobEffects.WITHER, 40, 0, false, true));
		cloud.setParticle(ParticleTypes.SMOKE);
		cloud.setRadius(2);
		cloud.setDuration(200);

		level.addFreshEntity(cloud);
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.appendHoverText(stack, context, tooltip, flag);
	}
}
