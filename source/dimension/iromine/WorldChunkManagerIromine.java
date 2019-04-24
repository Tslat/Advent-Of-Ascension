package net.nevermine.dimension.iromine;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.util.ReportedException;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeCache;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WorldChunkManagerIromine extends WorldChunkManager {
	private GenLayer genBiomes;
	private GenLayer biomeIndexLayer;
	private BiomeCache biomeCache;
	private List<BiomeGenBase> biomesToSpawnIn;

	public WorldChunkManagerIromine() {
		biomeCache = new BiomeCache(this);
		biomesToSpawnIn = new ArrayList<BiomeGenBase>();
		biomesToSpawnIn.addAll(allowedBiomes);
	}

	public WorldChunkManagerIromine(long seed, WorldType worldType) {
		this();
		GenLayer[] agenlayer = GenLayerIromine.makeTheWorld(seed, worldType);
		agenlayer = getModdedBiomeGenerators(worldType, seed, agenlayer);
		genBiomes = agenlayer[0];
		biomeIndexLayer = agenlayer[1];
	}

	public WorldChunkManagerIromine(World world) {
		this(world.getSeed(), world.getWorldInfo().getTerrainType());
	}

	@Override
	public List<BiomeGenBase> getBiomesToSpawnIn() {
		return biomesToSpawnIn;
	}

	@Override
	public BiomeGenBase getBiomeGenAt(int x, int z) {
		return biomeCache.getBiomeGenAt(x, z);
	}

	@Override
	public float[] getRainfall(float[] listToReuse, int x, int z, int width, int length) {
		IntCache.resetIntCache();

		if (listToReuse == null || listToReuse.length < width * length) {
			listToReuse = new float[width * length];
		}

		int[] aint = biomeIndexLayer.getInts(x, z, width, length);

		for (int i1 = 0; i1 < width * length; ++i1) {
			try {
				float f = (float)BiomeGenBase.getBiome(aint[i1]).getIntRainfall() / 65536.0F;

				if (f > 1.0F) {
					f = 1.0F;
				}

				listToReuse[i1] = f;
			} catch (Throwable throwable) {
				CrashReport crashreport = CrashReport.makeCrashReport(throwable, "Invalid Biome id");
				CrashReportCategory crashreportcategory = crashreport.makeCategory("DownfallBlock");
				crashreportcategory.addCrashSection("biome id", Integer.valueOf(i1));
				crashreportcategory.addCrashSection("downfalls[] size", Integer.valueOf(listToReuse.length));
				crashreportcategory.addCrashSection("x", Integer.valueOf(x));
				crashreportcategory.addCrashSection("z", Integer.valueOf(z));
				crashreportcategory.addCrashSection("w", Integer.valueOf(width));
				crashreportcategory.addCrashSection("h", Integer.valueOf(length));
				throw new ReportedException(crashreport);
			}
		}

		return listToReuse;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public float getTemperatureAtHeight(float par1, int par2) {
		return par1;
	}

	@Override
	public BiomeGenBase[] getBiomesForGeneration(BiomeGenBase[] par1ArrayOfBiomeGenBase, int par2, int par3, int par4, int par5) {
		IntCache.resetIntCache();

		if (par1ArrayOfBiomeGenBase == null || par1ArrayOfBiomeGenBase.length < par4 * par5) {
			par1ArrayOfBiomeGenBase = new BiomeGenBase[par4 * par5];
		}

		int[] aint = genBiomes.getInts(par2, par3, par4, par5);

		try {
			for (int i = 0; i < par4 * par5; ++i) {
				par1ArrayOfBiomeGenBase[i] = BiomeGenBase.getBiome(aint[i]);
			}

			return par1ArrayOfBiomeGenBase;
		} catch (Throwable throwable) {
			CrashReport crashreport = CrashReport.makeCrashReport(throwable, "Invalid Biome id");
			CrashReportCategory crashreportcategory = crashreport.makeCategory("RawBiomeBlock");
			crashreportcategory.addCrashSection("biomes[] size", Integer.valueOf(par1ArrayOfBiomeGenBase.length));
			crashreportcategory.addCrashSection("x", Integer.valueOf(par2));
			crashreportcategory.addCrashSection("z", Integer.valueOf(par3));
			crashreportcategory.addCrashSection("w", Integer.valueOf(par4));
			crashreportcategory.addCrashSection("h", Integer.valueOf(par5));
			throw new ReportedException(crashreport);
		}
	}

	@Override
	public BiomeGenBase[] loadBlockGeneratorData(BiomeGenBase[] oldBiomeList, int x, int z, int width, int depth) {
		return getBiomeGenAt(oldBiomeList, x, z, width, depth, true);
	}

	@Override
	public BiomeGenBase[] getBiomeGenAt(BiomeGenBase[] listToReuse, int x, int y, int width, int length, boolean cacheFlag) {
		IntCache.resetIntCache();

		if (listToReuse == null || listToReuse.length < width * length) {
			listToReuse = new BiomeGenBase[width * length];
		}

		if (cacheFlag && width == 16 && length == 16 && (x & 15) == 0 && (y & 15) == 0) {
			BiomeGenBase[] abiomegenbase1 = biomeCache.getCachedBiomes(x, y);
			System.arraycopy(abiomegenbase1, 0, listToReuse, 0, width * length);
			return listToReuse;
		}
		else {
			int[] aint = biomeIndexLayer.getInts(x, y, width, length);

			for (int i = 0; i < width * length; ++i) {
				listToReuse[i] = BiomeGenBase.getBiome(aint[i]);
			}
			return listToReuse;
		}
	}

	@Override
	public boolean areBiomesViable(int x, int y, int z, List par4List) {
		IntCache.resetIntCache();
		int l = x - z >> 2;
		int i1 = y - z >> 2;
		int j1 = x + z >> 2;
		int k1 = y + z >> 2;
		int l1 = j1 - l + 1;
		int i2 = k1 - i1 + 1;
		int[] aint = genBiomes.getInts(l, i1, l1, i2);

		try {
			for (int j2 = 0; j2 < l1 * i2; ++j2) {
				BiomeGenBase biomegenbase = BiomeGenBase.getBiome(aint[j2]);

				if (!par4List.contains(biomegenbase)) {
					return false;
				}
			}

			return true;
		} catch (Throwable throwable) {
			CrashReport crashreport = CrashReport.makeCrashReport(throwable, "Invalid Biome id");
			CrashReportCategory crashreportcategory = crashreport.makeCategory("Layer");
			crashreportcategory.addCrashSection("Layer", genBiomes.toString());
			crashreportcategory.addCrashSection("x", Integer.valueOf(x));
			crashreportcategory.addCrashSection("z", Integer.valueOf(y));
			crashreportcategory.addCrashSection("radius", Integer.valueOf(z));
			crashreportcategory.addCrashSection("allowed", par4List);
			throw new ReportedException(crashreport);
		}
	}

	@Override
	public ChunkPosition findBiomePosition(int x, int y, int z, List par4List, Random random) {
		IntCache.resetIntCache();
		int l = x - z >> 2;
		int i1 = y - z >> 2;
		int j1 = x + z >> 2;
		int k1 = y + z >> 2;
		int l1 = j1 - l + 1;
		int i2 = k1 - i1 + 1;
		int[] aint = genBiomes.getInts(l, i1, l1, i2);
		ChunkPosition chunkposition = null;
		int j2 = 0;

		for (int k2 = 0; k2 < l1 * i2; ++k2) {
			int l2 = l + k2 % l1 << 2;
			int i3 = i1 + k2 / l1 << 2;
			BiomeGenBase biomegenbase = BiomeGenBase.getBiome(aint[k2]);

			if (par4List.contains(biomegenbase) && (chunkposition == null || random.nextInt(j2 + 1) == 0)) {
				chunkposition = new ChunkPosition(l2, 0, i3);
				++j2;
			}
		}

		return chunkposition;
	}

	@Override
	public void cleanupCache() {
		biomeCache.cleanupCache();
	}
}