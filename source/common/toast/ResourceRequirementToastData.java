package net.tslat.aoa3.common.toast;

import net.minecraft.ChatFormatting;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.tslat.aoa3.common.registration.AoARegistries;
import net.tslat.aoa3.common.registration.custom.AoAToastTypes;
import net.tslat.aoa3.player.resource.AoAResource;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.NumberUtil;
import net.tslat.aoa3.util.ToastUtil;

public record ResourceRequirementToastData(AoAResource resource, Float value) implements CustomToastData<AoAResource, Float> {
    public ResourceRequirementToastData(FriendlyByteBuf buffer) {
        this(buffer.readById(AoARegistries.AOA_RESOURCES), buffer.readFloat());
    }

    @Override
    public Type<ResourceRequirementToastData> type() {
        return AoAToastTypes.RESOURCE_REQUIREMENT.get();
    }

    @Override
    public void write(FriendlyByteBuf buffer) {
        buffer.writeId(AoARegistries.AOA_RESOURCES, this.resource);
        buffer.writeFloat(this.value);
    }

    public static void sendToastPopupTo(ServerPlayer pl, AoAResource resource, float value) {
        new ResourceRequirementToastData(resource, value).sendToPlayer(pl);
    }

    @Override
    public void handleOnClient() {
        ToastUtil.addConfigOptionalToast(() -> ToastUtil.addResourceRequirementToast(this), () -> LocaleUtil.getLocaleMessage(LocaleUtil.createFeedbackLocaleKey("insufficientResource"), ChatFormatting.RED, this.resource.getName(), Component.literal(NumberUtil.roundToNthDecimalPlace(this.value, 2))));
    }
}
