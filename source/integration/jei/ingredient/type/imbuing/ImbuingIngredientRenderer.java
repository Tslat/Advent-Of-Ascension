package net.tslat.aoa3.integration.jei.ingredient.type.imbuing;

import mezz.jei.api.gui.builder.ITooltipBuilder;
import mezz.jei.api.ingredients.IIngredientRenderer;
import mezz.jei.common.platform.Services;
import mezz.jei.common.util.RegistryUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.tslat.aoa3.client.player.ClientPlayerDataManager;
import net.tslat.aoa3.client.render.AoAGuiElementRenderers;
import net.tslat.aoa3.client.render.custom.AoASkillRenderer;
import net.tslat.aoa3.common.registration.custom.AoASkills;
import net.tslat.aoa3.library.object.RenderContext;
import net.tslat.aoa3.util.LocaleUtil;

import java.util.List;

public class ImbuingIngredientRenderer implements IIngredientRenderer<EnchantmentInstance> {
    private AoASkillRenderer skillRenderer = null;

    @Override
    public void render(GuiGraphics guiGraphics, EnchantmentInstance ingredient) {
        if (this.skillRenderer == null)
            this.skillRenderer = AoAGuiElementRenderers.getSkillRenderer(AoASkills.IMBUING.get());

        guiGraphics.pose().pushPose();
        guiGraphics.pose().scale(0.64f, 0.64f, 1);
        this.skillRenderer.renderInGui(RenderContext.of(guiGraphics), ClientPlayerDataManager.get().getSkill(AoASkills.IMBUING.get()), Minecraft.getInstance().getTimer().getGameTimeDeltaPartialTick(false), 0, 0, AoASkillRenderer.ProgressRenderType.None, false);
        guiGraphics.pose().popPose();
    }

    @Override
    public void getTooltip(ITooltipBuilder tooltip, EnchantmentInstance ingredient, TooltipFlag tooltipFlag) {
        final Holder<Enchantment> enchant = ingredient.enchantment;
        final ResourceLocation id = RegistryUtil.getRegistry(Registries.ENCHANTMENT).getKey(enchant.value());
        final String enchantDescKey = "enchantment." + id.getNamespace() + "." + id.getPath() + ".desc";
        final Component enchantDesc = LocaleUtil.getLocaleMessage(enchantDescKey);
        final int enchantWeight = enchant.value().getWeight();
        final Rarity rarity = enchantWeight >= 10 ? Rarity.COMMON : enchantWeight >= 5 ? Rarity.UNCOMMON : enchantWeight >= 3 ? Rarity.RARE : Rarity.EPIC;

        tooltip.add(((MutableComponent)Enchantment.getFullname(enchant, ingredient.level)).withStyle(rarity.getStyleModifier()));

        if (!enchantDesc.getString().equals(enchantDescKey))
            tooltip.add(enchantDesc);

        if (tooltipFlag.isAdvanced())
            tooltip.add(Component.literal(id.toString()).withStyle(ChatFormatting.DARK_GRAY));
    }

    // TODO
    @SuppressWarnings("removal")
    @Override
    public List<Component> getTooltip(EnchantmentInstance ingredient, TooltipFlag tooltipFlag) {
        return List.of();
    }

    @Override
    public Font getFontRenderer(Minecraft minecraft, EnchantmentInstance ingredient) {
        return Services.PLATFORM.getRenderHelper().getFontRenderer(minecraft, Items.ENCHANTED_BOOK.getDefaultInstance());
    }

    @Override
    public int getWidth() {
        return 16;
    }

    @Override
    public int getHeight() {
        return 16;
    }
}
