package net.tslat.aoa3.mixin.common.function;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.OwnableEntity;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.worldgen.AoADimensions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class EntityMixin {
    @Shadow
    public abstract Level level();

    @Inject(method = "shouldBeSaved", at = @At("HEAD"), cancellable = true)
    public void aoa3$preventNowhereSaving(CallbackInfoReturnable<Boolean> cir) {
        if (level().dimension() == AoADimensions.NOWHERE && (Object)this instanceof LivingEntity && (!((Object)this instanceof OwnableEntity ownable) || ownable.getOwnerUUID() == null))
            cir.setReturnValue(false);
    }
}
