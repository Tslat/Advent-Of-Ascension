package net.tslat.aoa3.client.render;

import net.minecraft.client.particle.Particle;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.client.fx.*;

import javax.annotation.Nullable;
import java.util.HashMap;

@SideOnly(Side.CLIENT)
public class FXRenders {
	private static HashMap<Integer, FXFactory> particleFactoryMap = new HashMap<Integer, FXFactory>();

	public static void spawnParticle(int id, double posX, double posY, double posZ, double speedX, double speedY, double speedZ, int textureOffsetIndex, float scale, int... args) {
		FXFactory factory = particleFactoryMap.get(id);

		if (factory != null)
			factory.createParticle(posX, posY, posZ, speedX, speedY, speedZ, textureOffsetIndex, scale, args);
	}

	@SubscribeEvent
	public void stitchEvent(TextureStitchEvent.Pre ev) {
		ev.getMap().registerSprite(FXFluffyTrail.texture);
		ev.getMap().registerSprite(FXSwirlyTrail.texture);
		ev.getMap().registerSprite(FXPortalFloater.texture);
	}

	static {
		particleFactoryMap.put(FXFluffyTrail.particleId, new FXFluffyTrail.Factory());
		particleFactoryMap.put(FXFlickeringFluffyTrail.particleId, new FXFlickeringFluffyTrail.Factory());
		particleFactoryMap.put(FXSwirlyTrail.particleId, new FXSwirlyTrail.Factory());
		particleFactoryMap.put(FXLastingFluffyTrail.particleId, new FXLastingFluffyTrail.Factory());
		particleFactoryMap.put(FXFluffyRainbowParticle.particleId, new FXFluffyRainbowParticle.Factory());
		particleFactoryMap.put(FXPortalFloater.particleId, new FXPortalFloater.Factory());
	}

	public interface FXFactory {
		@Nullable
		Particle createParticle(double posX, double posY, double posZ, double velocityX, double velocityY, double velocityZ, int textureOffsetIndex, float scale, int... args);
	}
}
