package net.tslat.aoa3.mixin.client.patch;

import net.minecraft.client.gui.screen.EditStructureScreen;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Slice;

@Mixin(EditStructureScreen.class)
public class EditStructureScreenMixin {
	@ModifyConstant(method = "Lnet/minecraft/client/gui/screen/EditStructureScreen;init()V",
			slice = @Slice(
					from = @At(value = "FIELD", target = "Lnet/minecraft/client/gui/screen/EditStructureScreen;nameEdit:Lnet/minecraft/client/gui/widget/TextFieldWidget;", opcode = Opcodes.GETFIELD),
					to = @At(value = "INVOKE", target = "Lnet/minecraft/tileentity/StructureBlockTileEntity;getStructureName()Ljava/lang/String;")
			),
			constant = @Constant(intValue = 64))
	private static int setMaxLength(int defaultLength) {
		return 256;
	}
}
