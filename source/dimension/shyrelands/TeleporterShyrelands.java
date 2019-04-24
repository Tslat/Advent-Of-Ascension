package net.nevermine.dimension.shyrelands;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.ChunkCoordinates;
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

public class TeleporterShyrelands extends Teleporter {
	private final WorldServer worldServerInstance;
	private final Random random;
	private final LongHashMap destinationCoordinateCache = new LongHashMap();
	private final List destinationCoordinateKeys = new ArrayList();

	public TeleporterShyrelands(WorldServer par1WorldServer) {
		super(par1WorldServer);
		worldServerInstance = par1WorldServer;
		random = new Random(par1WorldServer.getSeed());
	}

	public void placeInPortal(EntityPlayer par1Entity, double par2, double par4, double par6, float par8) {
		if (par1Entity instanceof EntityPlayer) {
			if (worldServerInstance.provider.dimensionId == ConfigurationHelper.shyrelands) {
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

	public boolean placeInExistingPortal(Entity par1Entity, double par2, double par4, double par6, float par8) {
		short short1 = 128;
		double d3 = -1.0D;
		int i = 0;
		int j = 0;
		int k = 0;
		int l = MathHelper.floor_double(par1Entity.posX);
		int i1 = MathHelper.floor_double(par1Entity.posZ);
		long j1 = ChunkCoordIntPair.chunkXZ2Int(l, i1);
		boolean flag = true;

		if (destinationCoordinateCache.containsItem(j1)) {
			PortalPosition portalposition = (PortalPosition)destinationCoordinateCache.getValueByKey(j1);
			d3 = 0.0D;
			i = portalposition.posX;
			j = portalposition.posY;
			k = portalposition.posZ;
			portalposition.lastUpdateTime = worldServerInstance.getTotalWorldTime();
			flag = false;
		}
		else {
			for (int l3 = l - short1; l3 <= l + short1; l3++) {
				double d4 = l3 + 0.5D - par1Entity.posX;

				for (int l1 = i1 - short1; l1 <= i1 + short1; l1++) {
					double d5 = l1 + 0.5D - par1Entity.posZ;

					for (int i2 = worldServerInstance.getActualHeight() - 1; i2 >= 0; i2--) {
						if (worldServerInstance.getBlock(l3, i2, l1) == Blockizer.shyrelandsPortal) {
							while (worldServerInstance.getBlock(l3, i2 - 1, l1) == Blockizer.shyrelandsPortal) {
								i2--;
							}

							double d7 = i2 + 0.5D - par1Entity.posY;
							double d8 = d4 * d4 + d7 * d7 + d5 * d5;

							if ((d3 < 0.0D) || (d8 < d3)) {
								d3 = d8;
								i = l3;
								j = i2;
								k = l1;
							}
						}
					}
				}
			}
		}

		if (d3 >= 0.0D) {
			if (flag) {
				destinationCoordinateCache.add(j1, new PortalPosition(i, j, k, worldServerInstance.getTotalWorldTime()));
				destinationCoordinateKeys.add(Long.valueOf(j1));
			}

			double d11 = i + 0.5D;
			double d6 = j + 0.5D;
			double d7 = k + 0.5D;
			int i4 = -1;

			if (worldServerInstance.getBlock(i - 1, j, k) == Blockizer.shyrelandsPortal) {
				i4 = 2;
			}

			if (worldServerInstance.getBlock(i + 1, j, k) == Blockizer.shyrelandsPortal) {
				i4 = 0;
			}

			if (worldServerInstance.getBlock(i, j, k - 1) == Blockizer.shyrelandsPortal) {
				i4 = 3;
			}

			if (worldServerInstance.getBlock(i, j, k + 1) == Blockizer.shyrelandsPortal) {
				i4 = 1;
			}

			int j2 = par1Entity.getTeleportDirection();

			if (i4 > -1) {
				int k2 = net.minecraft.util.Direction.rotateLeft[i4];
				int l2 = net.minecraft.util.Direction.offsetX[i4];
				int i3 = net.minecraft.util.Direction.offsetZ[i4];
				int j3 = net.minecraft.util.Direction.offsetX[k2];
				int k3 = net.minecraft.util.Direction.offsetZ[k2];
				boolean flag1 = (!worldServerInstance.isAirBlock(i + l2 + j3, j, k + i3 + k3)) || (!worldServerInstance.isAirBlock(i + l2 + j3, j + 1, k + i3 + k3));
				boolean flag2 = (!worldServerInstance.isAirBlock(i + l2, j, k + i3)) || (!worldServerInstance.isAirBlock(i + l2, j + 1, k + i3));

				if ((flag1) && (flag2)) {
					i4 = net.minecraft.util.Direction.rotateOpposite[i4];
					k2 = net.minecraft.util.Direction.rotateOpposite[k2];
					l2 = net.minecraft.util.Direction.offsetX[i4];
					i3 = net.minecraft.util.Direction.offsetZ[i4];
					j3 = net.minecraft.util.Direction.offsetX[k2];
					k3 = net.minecraft.util.Direction.offsetZ[k2];
					int l3 = i - j3;
					d11 -= j3;
					int k1 = k - k3;
					d7 -= k3;
					flag1 = (!worldServerInstance.isAirBlock(l3 + l2 + j3, j, k1 + i3 + k3)) || (!worldServerInstance.isAirBlock(l3 + l2 + j3, j + 1, k1 + i3 + k3));
					flag2 = (!worldServerInstance.isAirBlock(l3 + l2, j, k1 + i3)) || (!worldServerInstance.isAirBlock(l3 + l2, j + 1, k1 + i3));
				}

				float f1 = 0.5F;
				float f2 = 0.5F;

				if ((!flag1) && (flag2)) {
					f1 = 1.0F;
				}
				else if ((flag1) && (!flag2)) {
					f1 = 0.0F;
				}
				else if ((flag1) && (flag2)) {
					f2 = 0.0F;
				}

				d11 += j3 * f1 + f2 * l2;
				d7 += k3 * f1 + f2 * i3;
				float f3 = 0.0F;
				float f4 = 0.0F;
				float f5 = 0.0F;
				float f6 = 0.0F;

				if (i4 == j2) {
					f3 = 1.0F;
					f4 = 1.0F;
				}
				else if (i4 == net.minecraft.util.Direction.rotateOpposite[j2]) {
					f3 = -1.0F;
					f4 = -1.0F;
				}
				else if (i4 == net.minecraft.util.Direction.rotateRight[j2]) {
					f5 = 1.0F;
					f6 = -1.0F;
				}
				else {
					f5 = -1.0F;
					f6 = 1.0F;
				}

				double d9 = par1Entity.motionX;
				double d10 = par1Entity.motionZ;
				par1Entity.motionX = (d9 * f3 + d10 * f6);
				par1Entity.motionZ = (d9 * f5 + d10 * f4);
				par1Entity.rotationYaw = (par8 - j2 * 90 + i4 * 90);
			}
			else {
				par1Entity.motionX = (par1Entity.motionY = par1Entity.motionZ = 0.0D);
			}

			par1Entity.setLocationAndAngles(d11, d6, d7, par1Entity.rotationYaw, par1Entity.rotationPitch);
			return true;
		}

		return false;
	}

	public boolean makePortal(Entity par1Entity) {
		byte b0 = 16;
		double d0 = -1.0D;
		int i = MathHelper.floor_double(par1Entity.posX);
		int j = MathHelper.floor_double(par1Entity.posY);
		int k = MathHelper.floor_double(par1Entity.posZ);
		int l = i;
		int i1 = j;
		int j1 = k;
		int k1 = 0;
		int l1 = random.nextInt(4);

		for (int i2 = i - b0; i2 <= i + b0; i2++) {
			double d1 = i2 + 0.5D - par1Entity.posX;

			for (int k2 = k - b0; k2 <= k + b0; k2++) {
				double d2 = k2 + 0.5D - par1Entity.posZ;

				label433:
				for (int i3 = worldServerInstance.getActualHeight() - 1; i3 >= 0; i3--) {
					if (worldServerInstance.isAirBlock(i2, i3, k2)) {
						while ((i3 > 0) && (worldServerInstance.isAirBlock(i2, i3 - 1, k2))) {
							i3--;
						}

						for (int j3 = l1; j3 < l1 + 4; j3++) {
							int k3 = j3 % 2;
							int l3 = 1 - k3;

							if (j3 % 4 >= 2) {
								k3 = -k3;
								l3 = -l3;
							}

							for (int i4 = 0; i4 < 3; i4++) {
								for (int j4 = 0; j4 < 4; j4++) {
									for (int k4 = -1; k4 < 4; k4++) {
										int l4 = i2 + (j4 - 1) * k3 + i4 * l3;
										int i5 = i3 + k4;
										int j5 = k2 + (j4 - 1) * l3 - i4 * k3;

										if (((k4 < 0) && (!worldServerInstance.getBlock(l4, i5, j5).getMaterial().isSolid())) || ((k4 >= 0) && (!worldServerInstance.isAirBlock(l4, i5, j5)))) {
											break label433;
										}
									}
								}
							}

							double d4 = i3 + 0.5D - par1Entity.posY;
							double d3 = d1 * d1 + d4 * d4 + d2 * d2;

							if ((d0 < 0.0D) || (d3 < d0)) {
								d0 = d3;
								l = i2;
								i1 = i3;
								j1 = k2;
								k1 = j3 % 4;
							}
						}
					}
				}
			}
		}

		if (d0 < 0.0D) {
			for (int i2 = i - b0; i2 <= i + b0; i2++) {
				double d1 = i2 + 0.5D - par1Entity.posX;

				for (int k2 = k - b0; k2 <= k + b0; k2++) {
					double d2 = k2 + 0.5D - par1Entity.posZ;

					label786:
					for (int i3 = worldServerInstance.getActualHeight() - 1; i3 >= 0; i3--) {
						if (worldServerInstance.isAirBlock(i2, i3, k2)) {
							while ((i3 > 0) && (worldServerInstance.isAirBlock(i2, i3 - 1, k2))) {
								i3--;
							}

							for (int j3 = l1; j3 < l1 + 2; j3++) {
								int k3 = j3 % 2;
								int l3 = 1 - k3;

								for (int i4 = 0; i4 < 4; i4++) {
									for (int j4 = -1; j4 < 4; j4++) {
										int k4 = i2 + (i4 - 1) * k3;
										int l4 = i3 + j4;
										int i5 = k2 + (i4 - 1) * l3;

										if (((j4 < 0) && (!worldServerInstance.getBlock(k4, l4, i5).getMaterial().isSolid())) || ((j4 >= 0) && (!worldServerInstance.isAirBlock(k4, l4, i5)))) {
											break label786;
										}
									}
								}

								double d4 = i3 + 0.5D - par1Entity.posY;
								double d3 = d1 * d1 + d4 * d4 + d2 * d2;

								if ((d0 < 0.0D) || (d3 < d0)) {
									d0 = d3;
									l = i2;
									i1 = i3;
									j1 = k2;
									k1 = j3 % 2;
								}
							}
						}
					}
				}
			}
		}

		int k5 = l;
		int j2 = i1;
		int k2 = j1;
		int l5 = k1 % 2;
		int l2 = 1 - l5;

		if (k1 % 4 >= 2) {
			l5 = -l5;
			l2 = -l2;
		}

		if (d0 < 0.0D) {
			if (i1 < 70) {
				i1 = 70;
			}

			if (i1 > worldServerInstance.getActualHeight() - 10) {
				i1 = worldServerInstance.getActualHeight() - 10;
			}

			j2 = i1;

			for (int i3 = -1; i3 <= 1; i3++) {
				for (int j3 = 1; j3 < 3; j3++) {
					for (int k3 = -1; k3 < 3; k3++) {
						int l3 = k5 + (j3 - 1) * l5 + i3 * l2;
						int i4 = j2 + k3;
						int j4 = k2 + (j3 - 1) * l2 - i3 * l5;
						boolean flag = k3 < 0;
						worldServerInstance.setBlock(l3, i4, j4, flag ? Blockizer.ShyreWall : Blocks.air);

						worldServerInstance.setBlock(l3 + 1, i4, j4, flag ? Blockizer.ShyreWall : Blocks.air);
						worldServerInstance.setBlock(l3 - 1, i4, j4, flag ? Blockizer.ShyreWall : Blocks.air);
						worldServerInstance.setBlock(l3 + 2, i4, j4, flag ? Blockizer.ShyreWall : Blocks.air);
						worldServerInstance.setBlock(l3 - 2, i4, j4, flag ? Blockizer.ShyreWall : Blocks.air);
						worldServerInstance.setBlock(l3, i4, j4 + 1, flag ? Blockizer.ShyreWall : Blocks.air);
						worldServerInstance.setBlock(l3, i4, j4 - 1, flag ? Blockizer.ShyreWall : Blocks.air);
						worldServerInstance.setBlock(l3, i4, j4 + 2, flag ? Blockizer.ShyreWall : Blocks.air);
						worldServerInstance.setBlock(l3, i4, j4 - 2, flag ? Blockizer.ShyreWall : Blocks.air);
					}
				}
			}
		}

		for (int i3 = 0; i3 < 4; i3++) {
			for (int j3 = 0; j3 < 4; j3++) {
				for (int k3 = -1; k3 < 4; k3++) {
					int l3 = k5 + (j3 - 1) * l5;
					int i4 = j2 + k3;
					int j4 = k2 + (j3 - 1) * l2;
					boolean flag = (j3 == 0) || (j3 == 3) || (k3 == -1) || (k3 == 3);
					worldServerInstance.setBlock(l3, i4, j4, flag ? Blockizer.ShyreWall : Blockizer.shyrelandsPortal, 0, 2);
				}
			}

			for (int j3 = 0; j3 < 4; j3++) {
				for (int k3 = -1; k3 < 4; k3++) {
					int l3 = k5 + (j3 - 1) * l5;
					int i4 = j2 + k3;
					int j4 = k2 + (j3 - 1) * l2;
					worldServerInstance.notifyBlocksOfNeighborChange(l3, i4, j4, worldServerInstance.getBlock(l3, i4, j4));
				}
			}
		}

		return true;
	}

	public void removeStalePortalLocations(long par1) {
		if (par1 % 100L == 0L) {
			Iterator iterator = destinationCoordinateKeys.iterator();
			long j = par1 - 600L;

			while (iterator.hasNext()) {
				Long olong = (Long)iterator.next();
				PortalPosition portalposition = (PortalPosition)destinationCoordinateCache.getValueByKey(olong.longValue());

				if ((portalposition == null) || (portalposition.lastUpdateTime < j)) {
					iterator.remove();
					destinationCoordinateCache.remove(olong.longValue());
				}
			}
		}
	}

	public class PortalPosition extends ChunkCoordinates {
		public long lastUpdateTime;

		public PortalPosition(int par2, int par3, int par4, long par5) {
			super(par2, par3, par4);
			lastUpdateTime = par5;
		}
	}
}
