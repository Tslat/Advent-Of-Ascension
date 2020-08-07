package net.tslat.aoa3.common.registration;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.client.particles.*;

public class ParticleRegister {
	private static TextureAtlasSprite PARTICLES_ATLAS = null;
	private static double PARTICLES_ATLAS_PIXEL_RATIO = 1;

	public static final EnumParticleTypes STAR = registerParticle("star", false, 4);
	public static final EnumParticleTypes FLUFFY = registerParticle("fluffy_trail", false, 4);
	public static final EnumParticleTypes FLICKERING_FLUFFY = registerParticle("flickering_fluffy_trail", false, 4);
	public static final EnumParticleTypes RAINBOW_FLUFFY = registerParticle("rainbow_fluffy_trail", false, 3);
	public static final EnumParticleTypes LINGERING_FLUFFY_TRAIL = registerParticle("lingering_fluffy_trail", false, 3);
	public static final EnumParticleTypes PORTAL_FLOATER = registerParticle("portal_floater", false, 2);
	public static final EnumParticleTypes SWIRLY = registerParticle("swirly_trail", false, 3);

	public static TextureAtlasSprite getTextureAtlas() {
		return PARTICLES_ATLAS;
	}

	public static double getParticleTextureRatio() {
		return PARTICLES_ATLAS_PIXEL_RATIO;
	}

	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void stitchEvent(TextureStitchEvent.Pre ev) {
		PARTICLES_ATLAS = ev.getMap().registerSprite(new ResourceLocation("aoa3", "particles/particles"));
	}

	@SideOnly(Side.CLIENT)
	public static void doInitTasks() {
		PARTICLES_ATLAS_PIXEL_RATIO = PARTICLES_ATLAS.getMinU() / PARTICLES_ATLAS.getOriginX();
		ParticleManager manager = Minecraft.getMinecraft().effectRenderer;

		manager.registerParticle(FLUFFY.getParticleID(), new FluffyTrailParticle.Factory());
		manager.registerParticle(FLICKERING_FLUFFY.getParticleID(), new FlickeringFluffyTrailParticle.Factory());
		manager.registerParticle(RAINBOW_FLUFFY.getParticleID(), new RainbowFluffyParticle.Factory());
		manager.registerParticle(LINGERING_FLUFFY_TRAIL.getParticleID(), new LingeringFluffyTrailParticle.Factory());
		manager.registerParticle(PORTAL_FLOATER.getParticleID(), new PortalFloaterParticle.Factory());
		manager.registerParticle(SWIRLY.getParticleID(), new SwirlyTrailParticle.Factory());
		manager.registerParticle(STAR.getParticleID(), new StarParticle.Factory());
	}

	private static EnumParticleTypes registerParticle(String name, boolean ignoreRange, int argumentsCount) {
		return EnumHelper.addEnum(EnumParticleTypes.class, "AOA3_" + name.toUpperCase(), new Class[] {String.class, int.class, boolean.class, int.class}, "aoa3_" + name, EnumParticleTypes.values().length, ignoreRange, argumentsCount);
	}
}