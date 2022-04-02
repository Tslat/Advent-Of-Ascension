package net.tslat.aoa3.mixin.client.patch;

import net.minecraft.client.gui.screens.inventory.StructureBlockEditScreen;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Slice;

@Mixin(StructureBlockEditScreen.class)
public class StructureBlockEditScreenMixin { // Increases the textbox character limit on Structure Block structure paths to 256
	@ModifyConstant(method = "init",
			slice = @Slice(
					from = @At(value = "FIELD", target = "Lnet/minecraft/client/gui/screens/inventory/StructureBlockEditScreen;nameEdit:Lnet/minecraft/client/gui/components/EditBox;", opcode = Opcodes.GETFIELD),
					to = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/entity/StructureBlockEntity;getStructureName()Ljava/lang/String;")
			),
			constant = @Constant(intValue = 64))
	private static int setMaxLength(int defaultLength) {
		return 256;
	}
}
