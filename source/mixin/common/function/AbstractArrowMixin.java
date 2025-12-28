package net.tslat.aoa3.mixin.common.function;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.content.item.ArrowFiringWeapon;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractArrow.class)
public abstract class AbstractArrowMixin {
    @Shadow public abstract ItemStack getWeaponItem();

    @WrapOperation(method = "onHitEntity", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/phys/Vec3;length()D"))
    public double aoa3$removeVelocity(Vec3 velocity, Operation<Double> operation, @Share("aoa3$velocityLength") LocalRef<Float> velocityLength) {
        double length = operation.call(velocity);

        if (getWeaponItem() != null && getWeaponItem().getItem() instanceof ArrowFiringWeapon) {
            velocityLength.set((float)length);
            length = 1d;
        }

        return length;
    }

    @WrapOperation(method = "onHitEntity", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Entity;hurt(Lnet/minecraft/world/damagesource/DamageSource;F)Z"))
    public boolean aoa3$injectAoABowHandling(Entity target, DamageSource source, float damage, Operation<Boolean> operation, EntityHitResult hitResult, @Share("aoa3$velocityLength") LocalRef<Float> velocityLength) {
        ItemStack weaponStack = getWeaponItem();
        AbstractArrow arrow = (AbstractArrow)(Object)this;
        Entity owner = arrow.getOwner();
        ArrowFiringWeapon aoaWeapon = null;

        if (weaponStack != null && weaponStack.getItem() instanceof ArrowFiringWeapon arrowFiringWeapon) {
            aoaWeapon = arrowFiringWeapon;
            damage = Math.max(0, aoaWeapon.getArrowDamage(arrow, owner, hitResult, weaponStack, damage, velocityLength.get(), arrow.isCritArrow()));
        }

        if (operation.call(target, source, damage)) {
            if (target.getType() != EntityType.ENDERMAN && aoaWeapon != null) {
                aoaWeapon.onEntityImpact(arrow, owner, hitResult, weaponStack, velocityLength.get());
            }

            return true;
        }

        return false;
    }

    @WrapOperation(method = "onHitBlock", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/projectile/Projectile;onHitBlock(Lnet/minecraft/world/phys/BlockHitResult;)V"))
    public void aoa3$injectAoABowHandling(AbstractArrow arrow, BlockHitResult hitResult, Operation<Void> operation) {
        if (getWeaponItem() != null && getWeaponItem().getItem() instanceof ArrowFiringWeapon aoaWeapon)
            aoaWeapon.onBlockImpact(arrow, arrow.getOwner(), hitResult, getWeaponItem());

        operation.call(arrow, hitResult);
    }

    @Inject(method = "tick", at = @At("TAIL"))
    public void aoa3$injectArrowTick(CallbackInfo ci) {
        if (getWeaponItem() != null && getWeaponItem().getItem() instanceof ArrowFiringWeapon aoaWeapon) {
            AbstractArrow arrow = (AbstractArrow)(Object)this;

            aoaWeapon.tickArrow(arrow, arrow.getOwner(), getWeaponItem());
        }
    }
}
