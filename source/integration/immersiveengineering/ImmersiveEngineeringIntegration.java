package net.tslat.aoa3.integration.immersiveengineering;

import blusunrize.immersiveengineering.api.tool.ChemthrowerHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.EntityDamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Blaze;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FireBlock;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;

import javax.annotation.Nullable;

public class ImmersiveEngineeringIntegration {
	public static void postInit() {
		//ChemthrowerHandler.registerEffect(AoATags.Fluids.CANDIED_WATER, new ChemThrowerEffectCandiedWater());
	}

	private static class ChemThrowerEffectCandiedWater extends ChemthrowerHandler.ChemthrowerEffect {
		private ChemThrowerEffectCandiedWater() {}

		@Override
		public void applyToEntity(LivingEntity target, @Nullable Player shooter, ItemStack itemStack, Fluid fluid) {
			if (target.isOnFire())
				target.clearFire();

			if ((target instanceof Blaze || target instanceof EnderMan) && target.hurt((shooter == null ? DamageSource.DROWN : (new EntityDamageSource(DamageSource.DROWN.getMsgId(), shooter)).bypassArmor()), 3))
				target.invulnerableTime = (int)((double)target.invulnerableTime * 0.75d);

			if (target.getRandom().nextFloat() <= 0.5f)
				target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 20));
		}

		@Override
		public void applyToBlock(Level world, HitResult rayTraceResult, @Nullable Player playerEntity, ItemStack itemStack, Fluid fluid) {
			BlockHitResult blockTrace = (BlockHitResult)rayTraceResult;
			BlockPos pos = blockTrace.getBlockPos().offset(blockTrace.getDirection().getNormal());
			Block block = world.getBlockState(pos).getBlock();

			if (block instanceof FireBlock)
				world.removeBlock(pos, false);
		}
	}
}
