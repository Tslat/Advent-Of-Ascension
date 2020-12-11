package net.tslat.aoa3.common.registration;

import net.minecraft.client.Minecraft;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.client.particle.*;
import net.tslat.aoa3.library.misc.CustomisableParticleType;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = AdventOfAscension.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class AoAParticleTypes {
	public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, AdventOfAscension.MOD_ID);

	public static final RegistryObject<ParticleType<CustomisableParticleType.Data>> SPARKLER = registerParticle("sparkler", () -> new CustomisableParticleType(true));
	public static final RegistryObject<ParticleType<CustomisableParticleType.Data>> FLICKERING_SPARKLER = registerParticle("flickering_sparkler", () -> new CustomisableParticleType(true));
	public static final RegistryObject<ParticleType<CustomisableParticleType.Data>> RAINBOW_SPARKLER = registerParticle("rainbow_sparkler", () -> new CustomisableParticleType(true));
	public static final RegistryObject<ParticleType<CustomisableParticleType.Data>> LINGERING_SPARKLER = registerParticle("lingering_sparkler", () -> new CustomisableParticleType(true));
	public static final RegistryObject<ParticleType<CustomisableParticleType.Data>> PORTAL_FLOATER = registerParticle("portal_floater", () -> new CustomisableParticleType(false));
	public static final RegistryObject<ParticleType<CustomisableParticleType.Data>> SWIRLY = registerParticle("swirly", () -> new CustomisableParticleType(true));

	private static <T extends IParticleData> RegistryObject<ParticleType<T>> registerParticle(String id, Supplier<? extends ParticleType<T>> particle) {
		return PARTICLES.register(id, particle);
	}

	@SubscribeEvent
	@OnlyIn(Dist.CLIENT)
	public static void particleFactoryRegister(ParticleFactoryRegisterEvent ev) {
		Minecraft.getInstance().particles.registerFactory(PORTAL_FLOATER.get(), PortalFloaterParticle.Factory::new);
		Minecraft.getInstance().particles.registerFactory(SPARKLER.get(), SparklerParticle.Factory::new);
		Minecraft.getInstance().particles.registerFactory(FLICKERING_SPARKLER.get(), FlickeringSparklerParticle.Factory::new);
		Minecraft.getInstance().particles.registerFactory(LINGERING_SPARKLER.get(), LingeringSparklerParticle.Factory::new);
		Minecraft.getInstance().particles.registerFactory(RAINBOW_SPARKLER.get(), RainbowSparklerParticle.Factory::new);
		Minecraft.getInstance().particles.registerFactory(SWIRLY.get(), SwirlyParticle.Factory::new);
	}
}
