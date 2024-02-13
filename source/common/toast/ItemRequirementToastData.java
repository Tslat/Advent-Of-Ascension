package net.tslat.aoa3.common.toast;

import net.minecraft.ChatFormatting;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.tslat.aoa3.common.registration.custom.AoAToastTypes;
import net.tslat.aoa3.player.ability.AoAAbility;
import net.tslat.aoa3.player.skill.AoASkill;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.ToastUtil;

public record ItemRequirementToastData(Ingredient ingredient) implements CustomToastData<AoASkill, AoAAbility> {
    public ItemRequirementToastData(FriendlyByteBuf buffer) {
        this(Ingredient.fromNetwork(buffer));
    }

    @Override
    public Type<ItemRequirementToastData> type() {
        return AoAToastTypes.ITEM_REQUIREMENT.get();
    }

    @Override
    public void write(FriendlyByteBuf buffer) {
        this.ingredient.toNetwork(buffer);
    }

    public static void sendToastPopupTo(ServerPlayer pl, Item item) {
        sendToastPopupTo(pl, Ingredient.of(item));
    }

    public static void sendToastPopupTo(ServerPlayer pl, ItemStack stack) {
        sendToastPopupTo(pl, Ingredient.of(stack));
    }

    public static void sendToastPopupTo(ServerPlayer pl, TagKey<Item> tag) {
        sendToastPopupTo(pl, Ingredient.of(tag));
    }

    public static void sendToastPopupTo(ServerPlayer pl, Ingredient ingredient) {
        if (!ingredient.isEmpty())
            new ItemRequirementToastData(ingredient).sendToPlayer(pl);
    }

    @Override
    public void handleOnClient() {
        ToastUtil.addConfigOptionalToast(() -> ToastUtil.addItemRequirementToast(this), () -> LocaleUtil.getLocaleMessage(LocaleUtil.createFeedbackLocaleKey("insufficientItems"), ChatFormatting.RED, this.ingredient.getItems()[0].getHoverName()));
    }
}
