package net.tslat.aoa3.integration.jei.ingredient.type.imbuing;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import mezz.jei.api.ingredients.IIngredientRenderer;
import mezz.jei.common.platform.Services;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.tslat.aoa3.client.render.AoAGuiElementRenderers;
import net.tslat.aoa3.client.render.custom.AoASkillRenderer;
import net.tslat.aoa3.common.registration.AoARegistries;
import net.tslat.aoa3.common.registration.custom.AoASkills;
import net.tslat.aoa3.library.object.RenderContext;
import net.tslat.aoa3.player.ClientPlayerDataManager;
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
        this.skillRenderer.renderInGui(RenderContext.of(guiGraphics), ClientPlayerDataManager.get().getSkill(AoASkills.IMBUING.get()), Minecraft.getInstance().getPartialTick(), 0, 0, AoASkillRenderer.ProgressRenderType.None, false);
        guiGraphics.pose().popPose();
    }

    @Override
    public List<Component> getTooltip(EnchantmentInstance ingredient, TooltipFlag tooltipFlag) {
        final List<Component> tooltip = new ObjectArrayList<>();
        final Enchantment enchant = ingredient.enchantment;
        final ResourceLocation id = AoARegistries.ENCHANTMENTS.getKey(enchant);
        final String enchantDescKey = "enchantment." + id.getNamespace() + "." + id.getPath() + ".desc";
        final Component enchantDesc = LocaleUtil.getLocaleMessage(enchantDescKey);
        final Rarity rarity = switch (enchant.getRarity()) {
            case COMMON -> Rarity.COMMON;
            case UNCOMMON -> Rarity.UNCOMMON;
            case RARE -> Rarity.RARE;
            default -> Rarity.EPIC;
        };

        tooltip.add(((MutableComponent)enchant.getFullname(ingredient.level)).withStyle(rarity.getStyleModifier()));

        if (!enchantDesc.getString().equals(enchantDescKey))
            tooltip.add(enchantDesc);

        if (tooltipFlag.isAdvanced())
            tooltip.add(Component.literal(AoARegistries.ENCHANTMENTS.getKey(enchant).toString()).withStyle(ChatFormatting.DARK_GRAY));

        return tooltip;
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
