package net.tslat.aoa3.integration.immersiveengineering;

import blusunrize.immersiveengineering.api.tool.ChemthrowerHandler;
import net.minecraft.block.Block;
import net.minecraft.block.FireBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.BlazeEntity;
import net.minecraft.entity.monster.EndermanEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoATags;

import javax.annotation.Nullable;

public class ImmersiveEngineeringIntegration {
	public static void postInit() {
		ChemthrowerHandler.registerEffect(AoATags.Fluids.CANDIED_WATER, new ChemThrowerEffectCandiedWater());
	}

	private static class ChemThrowerEffectCandiedWater extends ChemthrowerHandler.ChemthrowerEffect {
		private ChemThrowerEffectCandiedWater() {}

		@Override
		public void applyToEntity(LivingEntity target, @Nullable PlayerEntity shooter, ItemStack itemStack, Fluid fluid) {
			if (target.isOnFire())
				target.clearFire();

			if ((target instanceof BlazeEntity || target instanceof EndermanEntity) && target.hurt((shooter == null ? DamageSource.DROWN : (new EntityDamageSource(DamageSource.DROWN.getMsgId(), shooter)).bypassArmor()), 3))
				target.invulnerableTime = (int)((double)target.invulnerableTime * 0.75d);

			if (target.getRandom().nextFloat() <= 0.5f)
				target.addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 20));
		}

		@Override
		public void applyToBlock(World world, RayTraceResult rayTraceResult, @Nullable PlayerEntity playerEntity, ItemStack itemStack, Fluid fluid) {
			BlockRayTraceResult blockTrace = (BlockRayTraceResult)rayTraceResult;
			BlockPos pos = blockTrace.getBlockPos().offset(blockTrace.getDirection().getNormal());
			Block block = world.getBlockState(pos).getBlock();

			if (block instanceof FireBlock)
				world.removeBlock(pos, false);
		}
	}
}
