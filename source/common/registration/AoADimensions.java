package net.tslat.aoa3.common.registration;

import io.netty.buffer.Unpooled;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.ModDimension;
import net.minecraftforge.event.world.RegisterDimensionsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.advent.Logging;
import net.tslat.aoa3.worldgen.worlds.abyss.AbyssDimension;
import net.tslat.aoa3.worldgen.worlds.ancientcavern.AncientCavernDimension;
import net.tslat.aoa3.worldgen.worlds.barathos.BarathosDimension;
import net.tslat.aoa3.worldgen.worlds.candyland.CandylandDimension;
import net.tslat.aoa3.worldgen.worlds.celeve.CeleveDimension;
import net.tslat.aoa3.worldgen.worlds.creeponia.CreeponiaDimension;
import net.tslat.aoa3.worldgen.worlds.crystevia.CrysteviaDimension;
import net.tslat.aoa3.worldgen.worlds.deeplands.DeeplandsDimension;
import net.tslat.aoa3.worldgen.worlds.dustopia.DustopiaDimension;
import net.tslat.aoa3.worldgen.worlds.gardencia.GardenciaDimension;
import net.tslat.aoa3.worldgen.worlds.greckon.GreckonDimension;
import net.tslat.aoa3.worldgen.worlds.haven.HavenDimension;
import net.tslat.aoa3.worldgen.worlds.immortallis.ImmortallisDimension;
import net.tslat.aoa3.worldgen.worlds.iromine.IromineDimension;
import net.tslat.aoa3.worldgen.worlds.lborean.LBoreanDimension;
import net.tslat.aoa3.worldgen.worlds.lelyetia.LelyetiaDimension;
import net.tslat.aoa3.worldgen.worlds.lunalus.LunalusDimension;
import net.tslat.aoa3.worldgen.worlds.mysterium.MysteriumDimension;
import net.tslat.aoa3.worldgen.worlds.precasia.PrecasiaDimension;
import net.tslat.aoa3.worldgen.worlds.runandor.RunandorDimension;
import net.tslat.aoa3.worldgen.worlds.shyrelands.ShyrelandsDimension;
import net.tslat.aoa3.worldgen.worlds.voxponds.VoxPondsDimension;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Locale;
import java.util.function.BiFunction;

@Mod.EventBusSubscriber(modid = AdventOfAscension.MOD_ID)
public final class AoADimensions {
	public static final DeferredRegister<ModDimension> MOD_DIMENSIONS = DeferredRegister.create(ForgeRegistries.MOD_DIMENSIONS, AdventOfAscension.MOD_ID);
	private static final HashMap<DimensionType, AoADimension> dimTypeMap = new HashMap<>();

	public static final DimensionContainer ABYSS = new DimensionContainer(AoADimension.ABYSS, false, AbyssDimension::new);
	public static final DimensionContainer ANCIENT_CAVERN = new DimensionContainer(AoADimension.ANCIENT_CAVERN, false, AncientCavernDimension::new);
	public static final DimensionContainer BARATHOS = new DimensionContainer(AoADimension.BARATHOS, true, BarathosDimension::new);
	public static final DimensionContainer CANDYLAND = new DimensionContainer(AoADimension.CANDYLAND, true, CandylandDimension::new);
	public static final DimensionContainer CELEVE = new DimensionContainer(AoADimension.CELEVE, true, CeleveDimension::new);
	public static final DimensionContainer CREEPONIA = new DimensionContainer(AoADimension.CREEPONIA, true, CreeponiaDimension::new);
	public static final DimensionContainer CRYSTEVIA = new DimensionContainer(AoADimension.CRYSTEVIA, false, CrysteviaDimension::new);
	public static final DimensionContainer DEEPLANDS = new DimensionContainer(AoADimension.DEEPLANDS, false, DeeplandsDimension::new);
	public static final DimensionContainer DUSTOPIA = new DimensionContainer(AoADimension.DUSTOPIA, false, DustopiaDimension::new);
	public static final DimensionContainer GARDENCIA = new DimensionContainer(AoADimension.GARDENCIA, true, GardenciaDimension::new);
	public static final DimensionContainer GRECKON = new DimensionContainer(AoADimension.GRECKON, false, GreckonDimension::new);
	public static final DimensionContainer HAVEN = new DimensionContainer(AoADimension.HAVEN, true, HavenDimension::new);
	public static final DimensionContainer IMMORTALLIS = new DimensionContainer(AoADimension.IMMORTALLIS, false, ImmortallisDimension::new);
	public static final DimensionContainer IROMINE = new DimensionContainer(AoADimension.IROMINE, true, IromineDimension::new);
	public static final DimensionContainer LBOREAN = new DimensionContainer(AoADimension.LBOREAN, true, LBoreanDimension::new);
	public static final DimensionContainer LELYETIA = new DimensionContainer(AoADimension.LELYETIA, true, LelyetiaDimension::new);
	public static final DimensionContainer LUNALUS = new DimensionContainer(AoADimension.LUNALUS, false, LunalusDimension::new);
	public static final DimensionContainer MYSTERIUM = new DimensionContainer(AoADimension.MYSTERIUM, false, MysteriumDimension::new);
	public static final DimensionContainer PRECASIA = new DimensionContainer(AoADimension.PRECASIA, true, PrecasiaDimension::new);
	public static final DimensionContainer RUNANDOR = new DimensionContainer(AoADimension.RUNANDOR, true, RunandorDimension::new);
	public static final DimensionContainer SHYRELANDS = new DimensionContainer(AoADimension.SHYRELANDS, true, ShyrelandsDimension::new);
	public static final DimensionContainer VOX_PONDS = new DimensionContainer(AoADimension.VOX_PONDS, true, VoxPondsDimension::new);

	@SubscribeEvent
	public static void dimensionTypeRegister(final RegisterDimensionsEvent ev) {
		Logging.logStatusMessage("Registering Dimension Types");

		ABYSS.registerDimensionType();
		ANCIENT_CAVERN.registerDimensionType();
		BARATHOS.registerDimensionType();
		CANDYLAND.registerDimensionType();
		CELEVE.registerDimensionType();
		CREEPONIA.registerDimensionType();
		CRYSTEVIA.registerDimensionType();
		DEEPLANDS.registerDimensionType();
		DUSTOPIA.registerDimensionType();
		GARDENCIA.registerDimensionType();
		GRECKON.registerDimensionType();
		HAVEN.registerDimensionType();
		IMMORTALLIS.registerDimensionType();
		IROMINE.registerDimensionType();
		LBOREAN.registerDimensionType();
		LELYETIA.registerDimensionType();
		LUNALUS.registerDimensionType();
		MYSTERIUM.registerDimensionType();
		PRECASIA.registerDimensionType();
		RUNANDOR.registerDimensionType();
		SHYRELANDS.registerDimensionType();
		VOX_PONDS.registerDimensionType();

		dimTypeMap.put(DimensionType.THE_NETHER, AoADimension.NETHER);
		dimTypeMap.put(DimensionType.THE_END, AoADimension.END);
		dimTypeMap.put(DimensionType.OVERWORLD, AoADimension.OVERWORLD);
		dimTypeMap.put(null, AoADimension.NONE);
	}

	public enum AoADimension {
		ABYSS,
		ANCIENT_CAVERN,
		BARATHOS,
		CANDYLAND,
		CELEVE,
		CREEPONIA,
		CRYSTEVIA,
		DEEPLANDS,
		END,
		DUSTOPIA,
		GARDENCIA,
		GRECKON,
		HAVEN,
		IMMORTALLIS,
		IROMINE,
		LBOREAN,
		LELYETIA,
		LUNALUS,
		MYSTERIUM,
		NETHER,
		OVERWORLD,
		PRECASIA,
		RUNANDOR,
		SHYRELANDS,
		VOX_PONDS,
		NONE;

		public static AoADimension fromDimType(DimensionType dimType) {
			return dimTypeMap.get(dimType);
		}
	}

	public static class DimensionContainer {
		AoADimension dim;
		private final boolean hasSkyLight;
		private final RegistryObject<ModDimension> modDim;
		private DimensionType dimType = null;

		private DimensionContainer(AoADimension dim, boolean hasSkyLight, BiFunction<World, DimensionType, ? extends Dimension> modDimFactory) {
			this.dim = dim;
			this.hasSkyLight = hasSkyLight;
			this.modDim = MOD_DIMENSIONS.register(dim.toString().toLowerCase(Locale.ENGLISH), () -> new ModDimension() {
				@Override
				public BiFunction<World, DimensionType, ? extends Dimension> getFactory() {
					return modDimFactory;
				}
			});
		}

		private void registerDimensionType() {
			ResourceLocation dimId = new ResourceLocation(AdventOfAscension.MOD_ID, dim.toString().toLowerCase(Locale.ENGLISH));

			dimType = DimensionManager.registerOrGetDimension(dimId, modDim.get(), new PacketBuffer(Unpooled.buffer()), hasSkyLight);
			dimTypeMap.put(dimType, dim);
		}

		@Nonnull
		public DimensionType type() {
			if (dimType == null) {
				if (FMLEnvironment.dist == Dist.CLIENT)
					dimType = DimensionManager.registerOrGetDimension(new ResourceLocation(AdventOfAscension.MOD_ID, dim.toString().toLowerCase(Locale.ENGLISH)), modDim.get(), new PacketBuffer(Unpooled.buffer()), hasSkyLight);

				if (dimType == null)
					throw new IllegalAccessError("Attempted to access dimensionType prior to DimensionType registration. This should not be happening.");
			}

			return dimType;
		}
	}
}
