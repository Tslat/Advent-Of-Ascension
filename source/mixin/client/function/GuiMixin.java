package net.tslat.aoa3.mixin.client.function;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.tslat.aoa3.content.item.weapon.sniper.AoASniper;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Gui.class)
public class GuiMixin {
    @Shadow
    @Final
    private Minecraft minecraft;

    @WrapWithCondition(method = "renderCameraOverlays", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Gui;renderSpyglassOverlay(Lnet/minecraft/client/gui/GuiGraphics;F)V"))
    public boolean aoa$disableScopeForSnipers(Gui gui, GuiGraphics guiGraphics, float scopeScale) {
        return !AoASniper.isScoped(this.minecraft.player);
    }
}
