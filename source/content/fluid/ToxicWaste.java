package net.tslat.aoa3.content.fluid;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.common.SoundActions;
import net.neoforged.neoforge.fluids.FluidType;
import net.tslat.aoa3.client.fluid.renderproperties.ToxicWasteRenderProperties;

import java.util.function.Consumer;

public final class ToxicWaste extends FluidType {
	public ToxicWaste() {
		super(FluidType.Properties.create()
				.canSwim(true)
				.canDrown(true)
				.supportsBoating(true)
				.pathType(BlockPathTypes.DAMAGE_OTHER)
				.adjacentPathType(BlockPathTypes.DAMAGE_OTHER)
				.sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL_LAVA)
				.sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY_LAVA)
				.fallDistanceModifier(0.1f)
				.viscosity(10000)
				.density(5000)
				.temperature(400));
	}

	@Override
	public void initializeClient(Consumer<IClientFluidTypeExtensions> consumer) {
		consumer.accept(new ToxicWasteRenderProperties());
	}
}
