package net.tslat.aoa3.common.registration;

import com.mojang.serialization.Codec;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ItemParticleData;
import net.minecraft.particles.ParticleType;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.client.ClientOperations;
import net.tslat.aoa3.common.particletype.CustomisableParticleType;
import net.tslat.aoa3.common.particletype.FloatingItemFragmentParticleType;
import net.tslat.aoa3.common.particletype.PortalFloaterParticleType;

import java.util.function.Function;
import java.util.function.Supplier;

public final class AoAParticleTypes {
	public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, AdventOfAscension.MOD_ID);

	public static final RegistryObject<ParticleType<CustomisableParticleType.Data>> SPARKLER = registerParticle("sparkler", () -> new CustomisableParticleType(true));
	public static final RegistryObject<ParticleType<CustomisableParticleType.Data>> FLICKERING_SPARKLER = registerParticle("flickering_sparkler", () -> new CustomisableParticleType(true));
	public static final RegistryObject<ParticleType<CustomisableParticleType.Data>> RAINBOW_SPARKLER = registerParticle("rainbow_sparkler", () -> new CustomisableParticleType(true));
	public static final RegistryObject<ParticleType<CustomisableParticleType.Data>> LINGERING_SPARKLER = registerParticle("lingering_sparkler", () -> new CustomisableParticleType(true));
	public static final RegistryObject<ParticleType<PortalFloaterParticleType.Data>> PORTAL_FLOATER = registerParticle("portal_floater", () -> new PortalFloaterParticleType(false));
	public static final RegistryObject<ParticleType<CustomisableParticleType.Data>> SWIRLY = registerParticle("swirly", () -> new CustomisableParticleType(true));
	public static final RegistryObject<ParticleType<ItemParticleData>> FLOATING_ITEM_FRAGMENT = registerParticle("floating_item_fragment", () -> new FloatingItemFragmentParticleType(false));

	public static void init() {
		FMLJavaModLoadingContext.get().getModEventBus().addListener(EventPriority.NORMAL, false, ParticleFactoryRegisterEvent.class, ClientOperations::registerParticleFactories);
	}

	private static <T extends IParticleData> RegistryObject<ParticleType<T>> registerParticle(String id, Supplier<? extends ParticleType<T>> particle) {
		return PARTICLES.register(id, particle);
	}

	private static <T extends IParticleData> RegistryObject<ParticleType<T>> registerParticle(String id, IParticleData.IDeserializer<T> deserializer, Function<ParticleType<T>, Codec<T>> codecFunction) {
		return PARTICLES.register(id, () -> new ParticleType<T>(false, deserializer) {
			@Override
			public Codec<T> codec() {
				return codecFunction.apply(this);
			}
		});
	}
}
