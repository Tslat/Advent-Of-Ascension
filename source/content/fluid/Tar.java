package net.tslat.aoa3.content.fluid;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.common.SoundActions;
import net.minecraftforge.fluids.FluidType;
import net.tslat.aoa3.client.fluid.renderproperties.TarRenderProperties;

import java.util.function.Consumer;

public final class Tar extends FluidType {
	public Tar() {
		super(Properties.create()
				.canSwim(true)
				.canDrown(true)
				.supportsBoating(false)
				.pathType(BlockPathTypes.DAMAGE_OTHER)
				.adjacentPathType(BlockPathTypes.DAMAGE_OTHER)
				.sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL_LAVA)
				.sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY_LAVA)
				.fallDistanceModifier(0.1f)
				.viscosity(10000)
				.density(5000)
				.temperature(700));
	}

	@Override
	public void initializeClient(Consumer<IClientFluidTypeExtensions> consumer) {
		consumer.accept(new TarRenderProperties());
	}
}
