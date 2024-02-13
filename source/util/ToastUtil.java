package net.tslat.aoa3.util;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.tslat.aoa3.client.ClientOperations;
import net.tslat.aoa3.client.gui.hud.toasts.AbilityUnlockToast;
import net.tslat.aoa3.client.gui.hud.toasts.ItemRequirementToast;
import net.tslat.aoa3.client.gui.hud.toasts.LevelRequirementToast;
import net.tslat.aoa3.client.gui.hud.toasts.ResourceRequirementToast;
import net.tslat.aoa3.common.registration.AoAConfigs;
import net.tslat.aoa3.common.toast.AbilityUnlockToastData;
import net.tslat.aoa3.common.toast.ItemRequirementToastData;
import net.tslat.aoa3.common.toast.ResourceRequirementToastData;
import net.tslat.aoa3.common.toast.SkillRequirementToastData;

import java.util.function.Supplier;

public final class ToastUtil {
    public static void addConfigOptionalToast(Runnable toastActor, Supplier<Component> msg) {
        if (AoAConfigs.CLIENT.useToasts.get()) {
            toastActor.run();
        }
        else {
            ClientOperations.getPlayer().sendSystemMessage(msg.get());
        }
    }

    public static void addSkillRequirementToast(SkillRequirementToastData data) {
        Minecraft.getInstance().getToasts().addToast(new LevelRequirementToast(data.skill(), data.level()));
    }

    public static void addResourceRequirementToast(ResourceRequirementToastData data) {
        Minecraft.getInstance().getToasts().addToast(new ResourceRequirementToast(data.resource(), data.value()));
    }

    public static void addAbilityUnlockToast(AbilityUnlockToastData data) {
        Minecraft.getInstance().getToasts().addToast(new AbilityUnlockToast(data.skill(), data.ability()));
    }

    public static void addItemRequirementToast(ItemRequirementToastData data) {
        Minecraft.getInstance().getToasts().addToast(new ItemRequirementToast(data.ingredient()));
    }
}
