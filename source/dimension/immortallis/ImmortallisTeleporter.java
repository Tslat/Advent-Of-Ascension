package net.tslat.aoa3.dimension.immortallis;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.tslat.aoa3.block.functional.portal.PortalBlock;
import net.tslat.aoa3.capabilities.providers.AdventPlayerProvider;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.dimension.AoATeleporter;
import net.tslat.aoa3.library.PortalCoordinatesContainer;
import net.tslat.aoa3.utils.ConfigurationUtil;

import java.util.HashMap;

public class ImmortallisTeleporter extends AoATeleporter {
	private static final HashMap<Long, BlockPos> cachedPortalMap = new HashMap<Long, BlockPos>();

	public ImmortallisTeleporter(WorldServer world) {
		super(world);
	}

	@Override
	public HashMap<Long, BlockPos> getCachedPortalMap() {
		return cachedPortalMap;
	}

	@Override
	public void placeEntity(World world, Entity entity, float yaw) {
		if (world.provider.getDimension() == ConfigurationUtil.dimImmortallis) {
			if (entity.hasCapability(AdventPlayerProvider.ADVENT_PLAYER, null)) {
				PortalCoordinatesContainer loc = new PortalCoordinatesContainer(world.provider.getDimension(), entity.posX, entity.posY, entity.posZ);

				entity.getCapability(AdventPlayerProvider.ADVENT_PLAYER,null).setPortalReturnLocation(fromWorld.provider.getDimension(), loc);
			}

			entity.motionX = 0;
			entity.motionY = 0;
			entity.motionZ = 0;
			entity.rotationPitch = 0;
			entity.rotationYaw = -90;

			if (entity instanceof EntityPlayerMP) {
				((EntityPlayerMP)entity).connection.setPlayerLocation(-5, 20, 3, entity.rotationYaw, entity.rotationPitch);
			}
			else {
				entity.setLocationAndAngles(-5, 20, 3, entity.rotationYaw, entity.rotationPitch);
			}
		}
		else {
			super.placeEntity(world, entity, yaw);
		}
	}

	@Override
	public BlockPos makePortal(World world, Entity entity, BlockPos pos) {
		return pos;
	}

	@Override
	public PortalBlock getPortalBlock() {
		return BlockRegister.portalImmortallis;
	}

	@Override
	public Block getBorderBlock() {
		return BlockRegister.archaicTiles;
	}
}
