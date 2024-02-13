package net.tslat.aoa3.common.toast;

import net.minecraft.ChatFormatting;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.tslat.aoa3.common.registration.AoARegistries;
import net.tslat.aoa3.common.registration.custom.AoAToastTypes;
import net.tslat.aoa3.player.ability.AoAAbility;
import net.tslat.aoa3.player.skill.AoASkill;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.ToastUtil;

public record AbilityUnlockToastData(AoASkill skill, AoAAbility ability) implements CustomToastData<AoASkill, AoAAbility> {
    public AbilityUnlockToastData(FriendlyByteBuf buffer) {
        this(buffer.readById(AoARegistries.AOA_SKILLS), buffer.readById(AoARegistries.AOA_ABILITIES));
    }

    @Override
    public Type<AbilityUnlockToastData> type() {
        return AoAToastTypes.ABILITY_UNLOCK.get();
    }

    @Override
    public void write(FriendlyByteBuf buffer) {
        buffer.writeId(AoARegistries.AOA_SKILLS, this.skill);
        buffer.writeId(AoARegistries.AOA_ABILITIES, this.ability);
    }

    public static void sendToastPopupTo(ServerPlayer pl, AoAAbility.Instance ability) {
        new AbilityUnlockToastData(ability.getSkill().type(), ability.type()).sendToPlayer(pl);
    }

    @Override
    public void handleOnClient() {
        ToastUtil.addConfigOptionalToast(() -> ToastUtil.addAbilityUnlockToast(this), () -> LocaleUtil.getLocaleMessage(LocaleUtil.createFeedbackLocaleKey("abilityUnlocked"), ChatFormatting.GREEN, this.skill.getName(), this.ability.getName()));
    }
}
