package net.tslat.aoa3.common.registration;

import com.mojang.serialization.Codec;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.tslat.aoa3.common.particletype.CustomisableParticleType;
import net.tslat.aoa3.common.particletype.FloatingItemFragmentParticleType;
import net.tslat.aoa3.common.particletype.PortalFloaterParticleType;

import java.util.function.Function;
import java.util.function.Supplier;

public final class AoAParticleTypes {
	public static void init() {}

	public static final DeferredHolder<ParticleType<?>, ParticleType<CustomisableParticleType.Data>> SPARKLER = registerParticle("sparkler", () -> new CustomisableParticleType(true));
	public static final DeferredHolder<ParticleType<?>, ParticleType<CustomisableParticleType.Data>> FLICKERING_SPARKLER = registerParticle("flickering_sparkler", () -> new CustomisableParticleType(true));
	public static final DeferredHolder<ParticleType<?>, ParticleType<CustomisableParticleType.Data>> RAINBOW_SPARKLER = registerParticle("rainbow_sparkler", () -> new CustomisableParticleType(true));
	public static final DeferredHolder<ParticleType<?>, ParticleType<CustomisableParticleType.Data>> LINGERING_SPARKLER = registerParticle("lingering_sparkler", () -> new CustomisableParticleType(true));
	public static final DeferredHolder<ParticleType<?>, ParticleType<PortalFloaterParticleType.Data>> PORTAL_FLOATER = registerParticle("portal_floater", () -> new PortalFloaterParticleType(false));
	public static final DeferredHolder<ParticleType<?>, ParticleType<CustomisableParticleType.Data>> SWIRLY = registerParticle("swirly", () -> new CustomisableParticleType(true));
	public static final DeferredHolder<ParticleType<?>, ParticleType<ItemParticleOption>> FLOATING_ITEM_FRAGMENT = registerParticle("floating_item_fragment", () -> new FloatingItemFragmentParticleType(false));
	public static final DeferredHolder<ParticleType<?>, ParticleType<CustomisableParticleType.Data>> ORB = registerParticle("orb", () -> new CustomisableParticleType(true));
	public static final DeferredHolder<ParticleType<?>, ParticleType<CustomisableParticleType.Data>> FIRE_AURA = registerParticle("fire_aura", () -> new CustomisableParticleType(true));
	//public static final RegistryObject<ParticleType<CustomisableParticleType.Data>> BEAM = registerParticle("beam", () -> new CustomisableParticleType(true));

	public static final DeferredHolder<ParticleType<?>, ParticleType<CustomisableParticleType.Data>> FREEZING_SNOWFLAKE = registerParticle("freezing_snowflake", () -> new CustomisableParticleType(true));
	public static final DeferredHolder<ParticleType<?>, ParticleType<CustomisableParticleType.Data>> BURNING_FLAME = registerParticle("burning_flame", () -> new CustomisableParticleType(true));
	public static final DeferredHolder<ParticleType<?>, ParticleType<CustomisableParticleType.Data>> SANDSTORM = registerParticle("sandstorm", () -> new CustomisableParticleType(true));

	private static <T extends ParticleOptions> DeferredHolder<ParticleType<?>, ParticleType<T>> registerParticle(String id, Supplier<? extends ParticleType<T>> particle) {
		return AoARegistries.PARTICLES.register(id, particle);
	}

	private static <T extends ParticleOptions> DeferredHolder<ParticleType<?>, ParticleType<T>> registerParticle(String id, ParticleOptions.Deserializer<T> deserializer, Function<ParticleType<T>, Codec<T>> codecFunction) {
		return AoARegistries.PARTICLES.register(id, () -> new ParticleType<T>(false, deserializer) {
			@Override
			public Codec<T> codec() {
				return codecFunction.apply(this);
			}
		});
	}
}
