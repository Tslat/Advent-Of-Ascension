package net.tslat.aoa3.common.toast;

import net.minecraft.ChatFormatting;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.tslat.aoa3.common.registration.AoARegistries;
import net.tslat.aoa3.common.registration.custom.AoAToastTypes;
import net.tslat.aoa3.player.skill.AoASkill;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.ToastUtil;

public record SkillRequirementToastData(AoASkill skill, Integer level) implements CustomToastData<AoASkill, Integer> {
    public SkillRequirementToastData(FriendlyByteBuf buffer) {
        this(buffer.readById(AoARegistries.AOA_SKILLS), buffer.readVarInt());
    }

    @Override
    public Type<SkillRequirementToastData> type() {
        return AoAToastTypes.SKILL_REQUIREMENT.get();
    }

    @Override
    public void write(FriendlyByteBuf buffer) {
        buffer.writeId(AoARegistries.AOA_SKILLS, skill);
        buffer.writeVarInt(level);
    }

    public static void sendToastPopupTo(ServerPlayer pl, AoASkill skill, int level) {
        new SkillRequirementToastData(skill, level).sendToPlayer(pl);
    }

    @Override
    public void handleOnClient() {
        ToastUtil.addConfigOptionalToast(() -> ToastUtil.addSkillRequirementToast(this), () -> LocaleUtil.getLocaleMessage(LocaleUtil.createFeedbackLocaleKey("insufficientLevels"), ChatFormatting.RED, this.skill.getName(), LocaleUtil.numToComponent(this.level)));
    }
}
