package net.tslat.aoa3.mixin.common.function;

import net.minecraft.world.entity.LivingEntity;
import net.tslat.aoa3.common.registration.AoATags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
    @Shadow public abstract boolean onClimbable();

    // Double the speed of climbable blocks if tagged as fast climbables
    @ModifyConstant(method = "handleRelativeFrictionAndCalculateMovement", constant = @Constant(doubleValue = 0.2d))
    public double aoa3$modifyClimbingSpeed(double constant) {
        if (onClimbable() && ((LivingEntity)(Object)this).getInBlockState().is(AoATags.Blocks.FAST_CLIMBABLE))
            constant *= 2d;

        return constant;
    }
}
