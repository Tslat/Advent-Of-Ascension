package net.nevermine.dimension.immortallis;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.LongHashMap;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;
import net.nevermine.assist.ConfigurationHelper;
import net.nevermine.container.PlayerContainer;
import net.nevermine.container.PortalCoordinatesContainer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TeleporterImmortallis extends Teleporter {
	private final WorldServer worldServerInstance;
	private final Random random;
	private final LongHashMap destinationCoordinateCache;
	private final List destinationCoordinateKeys;

	public TeleporterImmortallis(final WorldServer par1WorldServer) {
		super(par1WorldServer);
		destinationCoordinateCache = new LongHashMap();
		destinationCoordinateKeys = new ArrayList();
		worldServerInstance = par1WorldServer;
		random = new Random(par1WorldServer.getSeed());
	}

	public boolean placeInExistingPortal(final Entity entity, final double notUsed_entityX, final double notUsed_entityY, final double notUsed_entityZ, final float notUsed_entityRotation) {
		if (entity instanceof EntityPlayer) {
			if (entity.dimension == ConfigurationHelper.immortallis) {
				entity.setLocationAndAngles(0.0, 20.0, 0.0, entity.rotationYaw, 0.0f);
			}
			else {
				PortalCoordinatesContainer coords = PlayerContainer.getProperties((EntityPlayer)entity).getPortalReturnLocation();

				if (coords.y != 0) {
					entity.setLocationAndAngles(coords.x, coords.y, coords.z, entity.rotationYaw, 0.0f);
				}
				else {
					entity.setLocationAndAngles(entity.posX, worldServerInstance.getTopSolidOrLiquidBlock((int)entity.posX, (int)entity.posZ), entity.posZ, entity.rotationYaw, 0);
				}

				entity.motionZ = 0.0;
				entity.motionY = 0.0;
				entity.motionX = 0.0;
			}
		}
		return true;
	}

	public boolean makePortal(final Entity par1Entity) {
		return false;
	}
}
