package net.tslat.aoa3.client;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;

public final class AoAArmPoses {
    public static final HumanoidModel.ArmPose ATTUNING_BOWL = HumanoidModel.ArmPose.create("AOA_ATTUNING_BOWL", true, AoAArmPoses::attuningBowlTransformer);

    public static void init() {}

    private static void attuningBowlTransformer(HumanoidModel<?> model, LivingEntity entity, HumanoidArm arm) {
        if (entity.isUsingItem()) {
            model.rightArm.xRot = -75 * Mth.DEG_TO_RAD;
            model.rightArm.yRot = -27 * Mth.DEG_TO_RAD;
            model.leftArm.xRot = -75 * Mth.DEG_TO_RAD;
            model.leftArm.yRot = 27 * Mth.DEG_TO_RAD;
        }
    }
}
