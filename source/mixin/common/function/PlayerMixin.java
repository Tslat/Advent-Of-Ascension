package net.tslat.aoa3.mixin.common.function;

import net.minecraft.world.entity.player.Player;
import net.tslat.aoa3.content.item.weapon.sniper.AoASniper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Player.class)
public class PlayerMixin {
    @Inject(method = "isScoping", at = @At("HEAD"), cancellable = true)
    public void aoa3$injectSniperScoping(CallbackInfoReturnable<Boolean> cir) {
        if (AoASniper.isScoped((Player)(Object)this))
            cir.setReturnValue(true);
    }
}
