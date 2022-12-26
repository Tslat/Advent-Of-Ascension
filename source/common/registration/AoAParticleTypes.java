package net.tslat.aoa3.common.registration;

import com.mojang.serialization.Codec;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.registries.RegistryObject;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.client.ClientOperations;
import net.tslat.aoa3.common.particletype.CustomisableParticleType;
import net.tslat.aoa3.common.particletype.FloatingItemFragmentParticleType;
import net.tslat.aoa3.common.particletype.PortalFloaterParticleType;

import java.util.function.Function;
import java.util.function.Supplier;

public final class AoAParticleTypes {
	public static final RegistryObject<ParticleType<CustomisableParticleType.Data>> SPARKLER = registerParticle("sparkler", () -> new CustomisableParticleType(true));
	public static final RegistryObject<ParticleType<CustomisableParticleType.Data>> FLICKERING_SPARKLER = registerParticle("flickering_sparkler", () -> new CustomisableParticleType(true));
	public static final RegistryObject<ParticleType<CustomisableParticleType.Data>> RAINBOW_SPARKLER = registerParticle("rainbow_sparkler", () -> new CustomisableParticleType(true));
	public static final RegistryObject<ParticleType<CustomisableParticleType.Data>> LINGERING_SPARKLER = registerParticle("lingering_sparkler", () -> new CustomisableParticleType(true));
	public static final RegistryObject<ParticleType<PortalFloaterParticleType.Data>> PORTAL_FLOATER = registerParticle("portal_floater", () -> new PortalFloaterParticleType(false));
	public static final RegistryObject<ParticleType<CustomisableParticleType.Data>> SWIRLY = registerParticle("swirly", () -> new CustomisableParticleType(true));
	public static final RegistryObject<ParticleType<ItemParticleOption>> FLOATING_ITEM_FRAGMENT = registerParticle("floating_item_fragment", () -> new FloatingItemFragmentParticleType(false));
	public static final RegistryObject<ParticleType<CustomisableParticleType.Data>> ORB = registerParticle("orb", () -> new CustomisableParticleType(true));
	public static final RegistryObject<ParticleType<CustomisableParticleType.Data>> FIRE_AURA = registerParticle("fire_aura", () -> new CustomisableParticleType(true));

	public static final RegistryObject<ParticleType<CustomisableParticleType.Data>> FREEZING_SNOWFLAKE = registerParticle("freezing_snowflake", () -> new CustomisableParticleType(true));
	public static final RegistryObject<ParticleType<CustomisableParticleType.Data>> BURNING_FLAME = registerParticle("burning_flame", () -> new CustomisableParticleType(true));
	public static final RegistryObject<ParticleType<CustomisableParticleType.Data>> SANDSTORM = registerParticle("sandstorm", () -> new CustomisableParticleType(true));

	public static void init() {
		DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> AdventOfAscension.modEventBus.addListener(EventPriority.NORMAL, false, RegisterParticleProvidersEvent.class, ClientOperations::registerParticleFactories));
	}

	private static <T extends ParticleOptions> RegistryObject<ParticleType<T>> registerParticle(String id, Supplier<? extends ParticleType<T>> particle) {
		return AoARegistries.PARTICLES.register(id, particle);
	}

	private static <T extends ParticleOptions> RegistryObject<ParticleType<T>> registerParticle(String id, ParticleOptions.Deserializer<T> deserializer, Function<ParticleType<T>, Codec<T>> codecFunction) {
		return AoARegistries.PARTICLES.register(id, () -> new ParticleType<T>(false, deserializer) {
			@Override
			public Codec<T> codec() {
				return codecFunction.apply(this);
			}
		});
	}
}
