package net.tslat.aoa3.content.entity.base;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.NeoForgeMod;

public interface FlyingEntity {
    default void travelFlying(PathfinderMob entity, Vec3 travelVector) {
        if (entity.isControlledByLocalInstance()) {
            Level level = entity.level();
            double gravity = entity.getGravity();
            boolean isDescending = entity.getDeltaMovement().y <= 0;

            if (isDescending && entity.hasEffect(MobEffects.SLOW_FALLING))
                gravity = Math.min(gravity, 0.01f);

            FluidState fluidState = level.getFluidState(entity.blockPosition());

            if ((entity.isInWater() || (entity.isInFluidType(fluidState) && fluidState.getFluidType() != NeoForgeMod.LAVA_TYPE.value())) && entity.isAffectedByFluids() && !entity.canStandOnFluid(fluidState)) {
                if (entity.isInWater() || (entity.isInFluidType(fluidState) && !entity.moveInFluid(fluidState, travelVector, gravity))) {
                    double prevY = entity.getY();
                    float drag = entity.isSprinting() ? 0.9F : entity.getWaterSlowDown();
                    float impulse = 0.02F;
                    float waterMovementEfficiency = (float)entity.getAttributeValue(Attributes.WATER_MOVEMENT_EFFICIENCY);

                    if (!entity.onGround())
                        waterMovementEfficiency *= 0.5f;

                    if (waterMovementEfficiency > 0) {
                        drag += (0.54600006f - drag) * waterMovementEfficiency;
                        impulse += (entity.getSpeed() - impulse) * waterMovementEfficiency;
                    }

                    if (entity.hasEffect(MobEffects.DOLPHINS_GRACE))
                        drag = 0.96F;

                    impulse *= (float)entity.getAttributeValue(NeoForgeMod.SWIM_SPEED);

                    entity.moveRelative(impulse, travelVector);
                    entity.move(MoverType.SELF, entity.getDeltaMovement());
                    Vec3 velocity = entity.getDeltaMovement();

                    if (entity.horizontalCollision && entity.onClimbable())
                        velocity = new Vec3(velocity.x, 0.2f, velocity.z);

                    entity.setDeltaMovement(velocity.multiply(drag, 0.8f, drag));

                    velocity = entity.getFluidFallingAdjustedMovement(gravity, isDescending, entity.getDeltaMovement());

                    entity.setDeltaMovement(velocity);

                    if (entity.horizontalCollision && entity.isFree(velocity.x, velocity.y + 0.6F - entity.getY() + prevY, velocity.z))
                        entity.setDeltaMovement(velocity.x, 0.3F, velocity.z);
                }
            }
            else if (entity.isInLava() && entity.isAffectedByFluids() && !entity.canStandOnFluid(fluidState)) {
                double prevY = entity.getY();

                entity.moveRelative(0.02f, travelVector);
                entity.move(MoverType.SELF, entity.getDeltaMovement());

                if (entity.getFluidHeight(FluidTags.LAVA) <= entity.getFluidJumpThreshold()) {
                    entity.setDeltaMovement(entity.getDeltaMovement().multiply(0.5f, 0.8f, 0.5f));
                    entity.setDeltaMovement(entity.getFluidFallingAdjustedMovement(gravity, isDescending, entity.getDeltaMovement()));
                }
                else {
                    entity.setDeltaMovement(entity.getDeltaMovement().scale(0.5f));
                }

                if (gravity != 0)
                    entity.setDeltaMovement(entity.getDeltaMovement().add(0, -gravity / 4d, 0));

                Vec3 velocity = entity.getDeltaMovement();

                if (entity.horizontalCollision && entity.isFree(velocity.x, velocity.y + 0.6f - entity.getY() + prevY, velocity.z))
                    entity.setDeltaMovement(velocity.x, 0.3f, velocity.z);
            }
            else if (entity.isFallFlying()) {
                entity.checkSlowFallDistance();

                Vec3 velocity = entity.getDeltaMovement();
                Vec3 lookAngle = entity.getLookAngle();
                float xRot = entity.getXRot() * Mth.DEG_TO_RAD;
                double lookLateralLength = Math.sqrt(lookAngle.x * lookAngle.x + lookAngle.z * lookAngle.z);
                double velocityLateralLength = velocity.horizontalDistance();
                double lookLength = lookAngle.length();
                double dive = Math.cos(xRot);
                dive = dive * dive * Math.min(1, lookLength / 0.4d);
                velocity = entity.getDeltaMovement().add(0, gravity * (-1d + dive * 0.75d), 0);

                if (velocity.y < 0 && lookLateralLength > 0) {
                    double diveVelocity = velocity.y * -0.1 * dive;
                    velocity = velocity.add(lookAngle.x * diveVelocity / lookLateralLength, diveVelocity, lookAngle.z * diveVelocity / lookLateralLength);
                }

                if (xRot < 0 && lookLateralLength > 0) {
                    double fallVelocity = velocityLateralLength * -Mth.sin(xRot) * 0.04f;
                    velocity = velocity.add(-lookAngle.x * fallVelocity / lookLateralLength, fallVelocity * 3.2f, -lookAngle.z * fallVelocity / lookLateralLength);
                }

                if (lookLateralLength > 0)
                    velocity = velocity.add((lookAngle.x / lookLateralLength * velocityLateralLength - velocity.x) * 0.1f, 0, (lookAngle.z / lookLateralLength * velocityLateralLength - velocity.z) * 0.1f);

                entity.setDeltaMovement(velocity.multiply(0.99f, 0.98f, 0.99f));
                entity.move(MoverType.SELF, entity.getDeltaMovement());

                if (entity.horizontalCollision && !level.isClientSide) {
                    double velocityLateralDelta = velocityLateralLength - entity.getDeltaMovement().horizontalDistance();
                    float collisionVelocity = (float)(velocityLateralDelta * 10f - 3f);

                    if (collisionVelocity > 0) {
                        entity.playSound(entity.getFallDamageSound((int)collisionVelocity), 1f, 1f);
                        entity.hurt(entity.damageSources().flyIntoWall(), collisionVelocity);
                    }
                }

                if (entity.onGround() && !level.isClientSide)
                    entity.setSharedFlag(Entity.FLAG_FALL_FLYING, false);
            }
            else {
                BlockPos groundPos = entity.getBlockPosBelowThatAffectsMyMovement();
                float groundFriction = level.getBlockState(groundPos).getFriction(level, groundPos, entity);
                float friction = entity.onGround() ? groundFriction * 0.91f : 0.6f * 0.91f;
                Vec3 newVelocity = entity.handleRelativeFrictionAndCalculateMovement(travelVector, groundFriction);
                double newVelocityY = newVelocity.y;

                if (entity.hasEffect(MobEffects.LEVITATION)) {
                    newVelocityY += (0.05f * (double)(entity.getEffect(MobEffects.LEVITATION).getAmplifier() + 1) - newVelocity.y) * 0.2f;
                }
                else if (!level.isClientSide || level.hasChunkAt(groundPos)) {
                    newVelocityY -= gravity;
                }
                else if (entity.getY() > (double)level.getMinBuildHeight()) {
                    newVelocityY = -0.1f;
                }
                else {
                    newVelocityY = 0;
                }

                if (entity.shouldDiscardFriction()) {
                    entity.setDeltaMovement(newVelocity.x, newVelocityY, newVelocity.z);
                }
                else {
                    entity.setDeltaMovement(newVelocity.x * (double)friction, newVelocityY * (double)friction, newVelocity.z * (double)friction);
                }
            }
        }

        entity.calculateEntityAnimation(true);
    }
}
