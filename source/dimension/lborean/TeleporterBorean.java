package net.nevermine.dimension.lborean;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.Direction;
import net.minecraft.util.LongHashMap;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;
import net.nevermine.assist.ConfigurationHelper;
import net.nevermine.container.PlayerContainer;
import net.nevermine.container.PortalCoordinatesContainer;
import net.nevermine.izer.Blockizer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class TeleporterBorean extends Teleporter {
	private final WorldServer worldServerInstance;
	private final Random random;
	private final LongHashMap destinationCoordinateCache;
	private final List destinationCoordinateKeys;

	public TeleporterBorean(final WorldServer par1WorldServer) {
		super(par1WorldServer);
		destinationCoordinateCache = new LongHashMap();
		destinationCoordinateKeys = new ArrayList();
		worldServerInstance = par1WorldServer;
		random = new Random(par1WorldServer.getSeed());
	}

	public void placeInPortal(final Entity par1Entity, final double par2, final double par4, final double par6, final float par8) {
		if (par1Entity instanceof EntityPlayer) {
			if (worldServerInstance.provider.dimensionId == ConfigurationHelper.lborean) {
				if (!placeInExistingPortal(par1Entity, par2, par4, par6, par8)) {
					makePortal(par1Entity);
					placeInExistingPortal(par1Entity, par2, par4, par6, par8);
				}
			}
			else {
				PortalCoordinatesContainer coords = PlayerContainer.getProperties((EntityPlayer)par1Entity).getPortalReturnLocation();

				if (coords.y != 0) {
					par1Entity.setLocationAndAngles(coords.x, coords.y, coords.z, par1Entity.rotationYaw, 0.0f);
				}
				else {
					par1Entity.setLocationAndAngles(par1Entity.posX, worldServerInstance.getTopSolidOrLiquidBlock((int)par1Entity.posX, (int)par1Entity.posZ), par1Entity.posZ, par1Entity.rotationYaw, 0);
				}

				par1Entity.motionZ = 0.0;
				par1Entity.motionY = 0.0;
				par1Entity.motionX = 0.0;
			}
		}
	}

	public boolean placeInExistingPortal(final Entity par1Entity, final double par2, final double par4, final double par6, final float par8) {
		final short short1 = 128;
		double d3 = -1.0;
		int i = 0;
		int j = 0;
		int k = 0;
		final int l = MathHelper.floor_double(par1Entity.posX);
		final int i2 = MathHelper.floor_double(par1Entity.posZ);
		final long j2 = ChunkCoordIntPair.chunkXZ2Int(l, i2);
		boolean flag = true;
		if (destinationCoordinateCache.containsItem(j2)) {
			final PortalPosition portalposition = (PortalPosition)destinationCoordinateCache.getValueByKey(j2);
			d3 = 0.0;
			i = portalposition.posX;
			j = portalposition.posY;
			k = portalposition.posZ;
			portalposition.lastUpdateTime = worldServerInstance.getTotalWorldTime();
			flag = false;
		}
		else {
			for (int l2 = l - short1; l2 <= l + short1; ++l2) {
				final double d4 = l2 + 0.5 - par1Entity.posX;
				for (int l3 = i2 - short1; l3 <= i2 + short1; ++l3) {
					final double d5 = l3 + 0.5 - par1Entity.posZ;
					for (int i3 = worldServerInstance.getActualHeight() - 1; i3 >= 0; --i3) {
						if (worldServerInstance.getBlock(l2, i3, l3) == Blockizer.boreanPortal) {
							while (worldServerInstance.getBlock(l2, i3 - 1, l3) == Blockizer.boreanPortal) {
								--i3;
							}
							final double d6 = i3 + 0.5 - par1Entity.posY;
							final double d7 = d4 * d4 + d6 * d6 + d5 * d5;
							if (d3 < 0.0 || d7 < d3) {
								d3 = d7;
								i = l2;
								j = i3;
								k = l3;
							}
						}
					}
				}
			}
		}
		if (d3 >= 0.0) {
			if (flag) {
				destinationCoordinateCache.add(j2, new PortalPosition(i, j, k, worldServerInstance.getTotalWorldTime()));
				destinationCoordinateKeys.add(j2);
			}
			double d8 = i + 0.5;
			final double d9 = j + 0.5;
			double d6 = k + 0.5;
			int i4 = -1;
			if (worldServerInstance.getBlock(i - 1, j, k) == Blockizer.boreanPortal) {
				i4 = 2;
			}
			if (worldServerInstance.getBlock(i + 1, j, k) == Blockizer.boreanPortal) {
				i4 = 0;
			}
			if (worldServerInstance.getBlock(i, j, k - 1) == Blockizer.boreanPortal) {
				i4 = 3;
			}
			if (worldServerInstance.getBlock(i, j, k + 1) == Blockizer.boreanPortal) {
				i4 = 1;
			}
			final int j3 = par1Entity.getTeleportDirection();
			if (i4 > -1) {
				int k2 = Direction.rotateLeft[i4];
				int l4 = Direction.offsetX[i4];
				int i5 = Direction.offsetZ[i4];
				int j4 = Direction.offsetX[k2];
				int k3 = Direction.offsetZ[k2];
				boolean flag2 = !worldServerInstance.isAirBlock(i + l4 + j4, j, k + i5 + k3) || !worldServerInstance.isAirBlock(i + l4 + j4, j + 1, k + i5 + k3);
				boolean flag3 = !worldServerInstance.isAirBlock(i + l4, j, k + i5) || !worldServerInstance.isAirBlock(i + l4, j + 1, k + i5);
				if (flag2 && flag3) {
					i4 = Direction.rotateOpposite[i4];
					k2 = Direction.rotateOpposite[k2];
					l4 = Direction.offsetX[i4];
					i5 = Direction.offsetZ[i4];
					j4 = Direction.offsetX[k2];
					k3 = Direction.offsetZ[k2];
					final int l2 = i - j4;
					d8 -= j4;
					final int k4 = k - k3;
					d6 -= k3;
					flag2 = (!worldServerInstance.isAirBlock(l2 + l4 + j4, j, k4 + i5 + k3) || !worldServerInstance.isAirBlock(l2 + l4 + j4, j + 1, k4 + i5 + k3));
					flag3 = (!worldServerInstance.isAirBlock(l2 + l4, j, k4 + i5) || !worldServerInstance.isAirBlock(l2 + l4, j + 1, k4 + i5));
				}
				float f1 = 0.5f;
				float f2 = 0.5f;
				if (!flag2 && flag3) {
					f1 = 1.0f;
				}
				else if (flag2 && !flag3) {
					f1 = 0.0f;
				}
				else if (flag2 && flag3) {
					f2 = 0.0f;
				}
				d8 += j4 * f1 + f2 * l4;
				d6 += k3 * f1 + f2 * i5;
				float f3 = 0.0f;
				float f4 = 0.0f;
				float f5 = 0.0f;
				float f6 = 0.0f;
				if (i4 == j3) {
					f3 = 1.0f;
					f4 = 1.0f;
				}
				else if (i4 == Direction.rotateOpposite[j3]) {
					f3 = -1.0f;
					f4 = -1.0f;
				}
				else if (i4 == Direction.rotateRight[j3]) {
					f5 = 1.0f;
					f6 = -1.0f;
				}
				else {
					f5 = -1.0f;
					f6 = 1.0f;
				}
				final double d10 = par1Entity.motionX;
				final double d11 = par1Entity.motionZ;
				par1Entity.motionX = d10 * f3 + d11 * f6;
				par1Entity.motionZ = d10 * f5 + d11 * f4;
				par1Entity.rotationYaw = par8 - j3 * 90 + i4 * 90;
			}
			else {
				final double motionX = 0.0;
				par1Entity.motionZ = motionX;
				par1Entity.motionY = motionX;
				par1Entity.motionX = motionX;
			}
			par1Entity.setLocationAndAngles(d8, d9, d6, par1Entity.rotationYaw, par1Entity.rotationPitch);
			return true;
		}
		return false;
	}

	public boolean makePortal(final Entity par1Entity) {
		final byte b0 = 16;
		double d0 = -1.0;
		final int i = MathHelper.floor_double(par1Entity.posX);
		final int j = MathHelper.floor_double(par1Entity.posY);
		final int k = MathHelper.floor_double(par1Entity.posZ);
		int l = i;
		int i2 = j;
		int j2 = k;
		int k2 = 0;
		final int l2 = random.nextInt(4);
		for (int i3 = i - b0; i3 <= i + b0; ++i3) {
			final double d2 = i3 + 0.5 - par1Entity.posX;
			for (int k3 = k - b0; k3 <= k + b0; ++k3) {
				final double d3 = k3 + 0.5 - par1Entity.posZ;
				Label_0433:
				for (int i4 = worldServerInstance.getActualHeight() - 1; i4 >= 0; --i4) {
					if (worldServerInstance.isAirBlock(i3, i4, k3)) {
						while (i4 > 0 && worldServerInstance.isAirBlock(i3, i4 - 1, k3)) {
							--i4;
						}
						for (int j3 = l2; j3 < l2 + 4; ++j3) {
							int k4 = j3 % 2;
							int l3 = 1 - k4;
							if (j3 % 4 >= 2) {
								k4 = -k4;
								l3 = -l3;
							}
							for (int i5 = 0; i5 < 3; ++i5) {
								for (int j4 = 0; j4 < 4; ++j4) {
									for (int k5 = -1; k5 < 4; ++k5) {
										final int l4 = i3 + (j4 - 1) * k4 + i5 * l3;
										final int i6 = i4 + k5;
										final int j5 = k3 + (j4 - 1) * l3 - i5 * k4;
										if (k5 < 0 && !worldServerInstance.getBlock(l4, i6, j5).getMaterial().isSolid()) {
											continue Label_0433;
										}
										if (k5 >= 0 && !worldServerInstance.isAirBlock(l4, i6, j5)) {
											continue Label_0433;
										}
									}
								}
							}
							final double d4 = i4 + 0.5 - par1Entity.posY;
							final double d5 = d2 * d2 + d4 * d4 + d3 * d3;
							if (d0 < 0.0 || d5 < d0) {
								d0 = d5;
								l = i3;
								i2 = i4;
								j2 = k3;
								k2 = j3 % 4;
							}
						}
					}
				}
			}
		}
		if (d0 < 0.0) {
			for (int i3 = i - b0; i3 <= i + b0; ++i3) {
				final double d2 = i3 + 0.5 - par1Entity.posX;
				for (int k3 = k - b0; k3 <= k + b0; ++k3) {
					final double d3 = k3 + 0.5 - par1Entity.posZ;
					Label_0786:
					for (int i4 = worldServerInstance.getActualHeight() - 1; i4 >= 0; --i4) {
						if (worldServerInstance.isAirBlock(i3, i4, k3)) {
							while (i4 > 0 && worldServerInstance.isAirBlock(i3, i4 - 1, k3)) {
								--i4;
							}
							for (int j3 = l2; j3 < l2 + 2; ++j3) {
								final int k4 = j3 % 2;
								final int l3 = 1 - k4;
								for (int i5 = 0; i5 < 4; ++i5) {
									for (int j4 = -1; j4 < 4; ++j4) {
										final int k5 = i3 + (i5 - 1) * k4;
										final int l4 = i4 + j4;
										final int i6 = k3 + (i5 - 1) * l3;
										if (j4 < 0 && !worldServerInstance.getBlock(k5, l4, i6).getMaterial().isSolid()) {
											continue Label_0786;
										}
										if (j4 >= 0 && !worldServerInstance.isAirBlock(k5, l4, i6)) {
											continue Label_0786;
										}
									}
								}
								final double d4 = i4 + 0.5 - par1Entity.posY;
								final double d5 = d2 * d2 + d4 * d4 + d3 * d3;
								if (d0 < 0.0 || d5 < d0) {
									d0 = d5;
									l = i3;
									i2 = i4;
									j2 = k3;
									k2 = j3 % 2;
								}
							}
						}
					}
				}
			}
		}
		final int k6 = l;
		int j6 = i2;
		int k3 = j2;
		int l5 = k2 % 2;
		int l6 = 1 - l5;
		if (k2 % 4 >= 2) {
			l5 = -l5;
			l6 = -l6;
		}
		if (d0 < 0.0) {
			if (i2 < 70) {
				i2 = 70;
			}
			if (i2 > worldServerInstance.getActualHeight() - 10) {
				i2 = worldServerInstance.getActualHeight() - 10;
			}
			j6 = i2;
			for (int i4 = -1; i4 <= 1; ++i4) {
				for (int j3 = 1; j3 < 3; ++j3) {
					for (int k4 = -1; k4 < 3; ++k4) {
						final int l3 = k6 + (j3 - 1) * l5 + i4 * l6;
						final int i5 = j6 + k4;
						final int j4 = k3 + (j3 - 1) * l6 - i4 * l5;
						final boolean flag = k4 < 0;
						worldServerInstance.setBlock(l3, i5, j4, flag ? Blockizer.PlantStem : Blocks.air);
						worldServerInstance.setBlock(l3 + 1, i5, j4, flag ? Blockizer.PlantStem : Blocks.air);
						worldServerInstance.setBlock(l3 - 1, i5, j4, flag ? Blockizer.PlantStem : Blocks.air);
						worldServerInstance.setBlock(l3 + 2, i5, j4, flag ? Blockizer.PlantStem : Blocks.air);
						worldServerInstance.setBlock(l3 - 2, i5, j4, flag ? Blockizer.PlantStem : Blocks.air);
						worldServerInstance.setBlock(l3, i5, j4 + 1, flag ? Blockizer.PlantStem : Blocks.air);
						worldServerInstance.setBlock(l3, i5, j4 - 1, flag ? Blockizer.PlantStem : Blocks.air);
						worldServerInstance.setBlock(l3, i5, j4 + 2, flag ? Blockizer.PlantStem : Blocks.air);
						worldServerInstance.setBlock(l3, i5, j4 - 2, flag ? Blockizer.PlantStem : Blocks.air);
					}
				}
			}
		}
		for (int i4 = 0; i4 < 4; ++i4) {
			for (int j3 = 0; j3 < 4; ++j3) {
				for (int k4 = -1; k4 < 4; ++k4) {
					final int l3 = k6 + (j3 - 1) * l5;
					final int i5 = j6 + k4;
					final int j4 = k3 + (j3 - 1) * l6;
					final boolean flag = j3 == 0 || j3 == 3 || k4 == -1 || k4 == 3;
					worldServerInstance.setBlock(l3, i5, j4, flag ? Blockizer.PlantStem : Blockizer.boreanPortal, 0, 2);
				}
			}
			for (int j3 = 0; j3 < 4; ++j3) {
				for (int k4 = -1; k4 < 4; ++k4) {
					final int l3 = k6 + (j3 - 1) * l5;
					final int i5 = j6 + k4;
					final int j4 = k3 + (j3 - 1) * l6;
					worldServerInstance.notifyBlocksOfNeighborChange(l3, i5, j4, worldServerInstance.getBlock(l3, i5, j4));
				}
			}
		}
		return true;
	}

	public void removeStalePortalLocations(final long par1) {
		if (par1 % 100L == 0L) {
			final Iterator<Long> iterator = destinationCoordinateKeys.iterator();
			final long j = par1 - 600L;
			while (iterator.hasNext()) {
				final Long olong = iterator.next();
				final PortalPosition portalposition = (PortalPosition)destinationCoordinateCache.getValueByKey(olong);
				if (portalposition == null || portalposition.lastUpdateTime < j) {
					iterator.remove();
					destinationCoordinateCache.remove(olong);
				}
			}
		}
	}

	public class PortalPosition extends ChunkCoordinates {
		public long lastUpdateTime;

		public PortalPosition(final int par2, final int par3, final int par4, final long par5) {
			super(par2, par3, par4);
			lastUpdateTime = par5;
		}
	}
}
