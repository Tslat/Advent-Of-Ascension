package net.tslat.aoa3.common.registration.custom;

import net.minecraft.network.FriendlyByteBuf;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.tslat.aoa3.common.registration.AoARegistries;
import net.tslat.aoa3.common.toast.*;

import java.util.function.Function;

public final class AoAToastTypes {
    public static void init() {}

    public static final DeferredHolder<CustomToastData.Type<?>, CustomToastData.Type<SkillRequirementToastData>> SKILL_REQUIREMENT = register("skill_requirement", SkillRequirementToastData::new);
    public static final DeferredHolder<CustomToastData.Type<?>, CustomToastData.Type<ResourceRequirementToastData>> RESOURCE_REQUIREMENT = register("resource_requirement", ResourceRequirementToastData::new);
    public static final DeferredHolder<CustomToastData.Type<?>, CustomToastData.Type<AbilityUnlockToastData>> ABILITY_UNLOCK = register("ability_unlock", AbilityUnlockToastData::new);
    public static final DeferredHolder<CustomToastData.Type<?>, CustomToastData.Type<ItemRequirementToastData>> ITEM_REQUIREMENT = register("item_requirement", ItemRequirementToastData::new);

    private static <D extends CustomToastData<?, ?>> DeferredHolder<CustomToastData.Type<?>, CustomToastData.Type<D>> register(String id, Function<FriendlyByteBuf, D> type) {
        return AoARegistries.CUSTOM_TOAST_TYPES.register(id, () -> type::apply);
    }
}
