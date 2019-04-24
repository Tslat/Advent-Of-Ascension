package net.nevermine.dimension;

import net.minecraft.world.biome.BiomeGenBase;
import net.nevermine.assist.ConfigurationHelper;
import net.nevermine.dimension.abyss.biome.BiomeGenAbyss;
import net.nevermine.dimension.abyss.biome.BiomeGenAbyssEye;
import net.nevermine.dimension.abyss.biome.BiomeGenAbyssShadow;
import net.nevermine.dimension.ancientcavern.BiomeGenAncientCavern;
import net.nevermine.dimension.barathos.biome.BiomeGenBarathos;
import net.nevermine.dimension.barathos.biome.BiomeGenBaronForest;
import net.nevermine.dimension.barathos.biome.BiomeGenBaronMaze;
import net.nevermine.dimension.candyland.biome.BiomeGenCandyland;
import net.nevermine.dimension.candyland.biome.BiomeGenCandylandChocolate;
import net.nevermine.dimension.candyland.biome.BiomeGenCandylandMarshmallow;
import net.nevermine.dimension.candyland.biome.BiomeGenCandylandRock;
import net.nevermine.dimension.celeve.BiomeGenCeleve;
import net.nevermine.dimension.creeponia.BiomeGenCreeponia;
import net.nevermine.dimension.crystevia.BiomeGenCrystevia;
import net.nevermine.dimension.deeplands.biome.BiomeGenDeeplands;
import net.nevermine.dimension.deeplands.biome.BiomeGenDeeplandsFungal;
import net.nevermine.dimension.deeplands.biome.BiomeGenDeeplandsShine;
import net.nevermine.dimension.dustopia.biome.BiomeGenDustopia;
import net.nevermine.dimension.dustopia.biome.BiomeGenDustopiaMarsh;
import net.nevermine.dimension.dustopia.biome.BiomeGenDustopiaPlains;
import net.nevermine.dimension.gardencia.biome.BiomeGenGardencia;
import net.nevermine.dimension.gardencia.biome.BiomeGenGardenciaFungal;
import net.nevermine.dimension.gardencia.biome.BiomeGenGardenciaMarsh;
import net.nevermine.dimension.greckon.biome.BiomeGenGreckon;
import net.nevermine.dimension.greckon.biome.BiomeGenGreckonForest;
import net.nevermine.dimension.greckon.biome.BiomeGenGreckonSkull;
import net.nevermine.dimension.haven.BiomeGenHaven;
import net.nevermine.dimension.immortallis.BiomeGenImmortallis;
import net.nevermine.dimension.iromine.biome.BiomeGenIromine;
import net.nevermine.dimension.iromine.biome.BiomeGenIromineSilver;
import net.nevermine.dimension.iromine.biome.BiomeGenIromineTech;
import net.nevermine.dimension.labricon.BiomeGenLabricon;
import net.nevermine.dimension.lborean.biome.BiomeGenBorean;
import net.nevermine.dimension.lborean.biome.BiomeGenBoreanBubble;
import net.nevermine.dimension.lborean.biome.BiomeGenBoreanForest;
import net.nevermine.dimension.lborean.biome.BiomeGenBoreanRed;
import net.nevermine.dimension.lelyetia.BiomeGenLelyetia;
import net.nevermine.dimension.lunalus.BiomeGenLunalus;
import net.nevermine.dimension.mysterium.BiomeGenMysterium;
import net.nevermine.dimension.precasia.biome.BiomeGenPrecasia;
import net.nevermine.dimension.precasia.biome.BiomeGenPrecasiaDesert;
import net.nevermine.dimension.precasia.biome.BiomeGenPrecasiaField;
import net.nevermine.dimension.precasia.biome.BiomeGenPrecasiaTall;
import net.nevermine.dimension.runandor.BiomeGenRunandor;
import net.nevermine.dimension.shyrelands.BiomeGenShyrelands;
import net.nevermine.dimension.voxponds.BiomeGenVoxponds;

public class DimensionOrganizer {
	protected static final BiomeGenBase.Height PrecasiaHeight = new BiomeGenBase.Height(0.0f, 0.4f);
	protected static final BiomeGenBase.Height AbyssHeight = new BiomeGenBase.Height(0.0f, 0.4f);
	protected static final BiomeGenBase.Height MysteriumHeight = new BiomeGenBase.Height(0.0f, 0.22f);
	protected static final BiomeGenBase.Height IromineHeight = new BiomeGenBase.Height(0.0f, 0.5f);
	protected static final BiomeGenBase.Height GardenciaHeight = new BiomeGenBase.Height(0.0f, 0.3f);
	protected static final BiomeGenBase.Height GreckonHeight = new BiomeGenBase.Height(0.0f, 0.1f);
	protected static final BiomeGenBase.Height DustopiaHeight = new BiomeGenBase.Height(0.0f, 0.25f);
	protected static final BiomeGenBase.Height BoreanHeight = new BiomeGenBase.Height(0.0f, 0.05f);
	protected static final BiomeGenBase.Height VoxpondsHeight = new BiomeGenBase.Height(0.0f, 0.1f);
	protected static final BiomeGenBase.Height RunicHeight = new BiomeGenBase.Height(0.0f, 0.4f);
	protected static final BiomeGenBase.Height BarathosHeight = new BiomeGenBase.Height(0.0f, 0.35f);
	protected static final BiomeGenBase.Height CandylandHeight = new BiomeGenBase.Height(0.0f, 0.1f);

	public static BiomeGenBase Abyss = new BiomeGenAbyss(ConfigurationHelper.abyssB).setHeight(DimensionOrganizer.AbyssHeight).setDisableRain();
	public static BiomeGenBase Precasia = new BiomeGenPrecasia(ConfigurationHelper.precasiaB).setHeight(DimensionOrganizer.PrecasiaHeight);
	public static BiomeGenBase Haven = new BiomeGenHaven(ConfigurationHelper.havenB);
	public static BiomeGenBase Mysterium = new BiomeGenMysterium(ConfigurationHelper.mysteriumB).setHeight(DimensionOrganizer.MysteriumHeight).setDisableRain();
	public static BiomeGenBase Iromine = new BiomeGenIromine(ConfigurationHelper.iromineB).setHeight(DimensionOrganizer.IromineHeight).setDisableRain();
	public static BiomeGenBase Lunalus = new BiomeGenLunalus(ConfigurationHelper.lunalusB).setDisableRain();
	public static BiomeGenBase Deeplands = new BiomeGenDeeplands(ConfigurationHelper.deeplandsB).setHeight(DimensionOrganizer.BoreanHeight).setDisableRain();
	public static BiomeGenBase Gardencia = new BiomeGenGardencia(ConfigurationHelper.gardenciaB).setHeight(DimensionOrganizer.GardenciaHeight);
	public static BiomeGenBase Greckon = new BiomeGenGreckon(ConfigurationHelper.greckonB).setHeight(DimensionOrganizer.GreckonHeight).setDisableRain();
	public static BiomeGenBase Dustopia = new BiomeGenDustopia(ConfigurationHelper.dustopiaB).setHeight(DimensionOrganizer.DustopiaHeight).setDisableRain();
	public static BiomeGenBase Borean = new BiomeGenBorean(ConfigurationHelper.lboreanB).setHeight(DimensionOrganizer.BoreanHeight).setDisableRain();
	public static BiomeGenBase Voxponds = new BiomeGenVoxponds(ConfigurationHelper.voxpondsB).setHeight(DimensionOrganizer.VoxpondsHeight).setDisableRain();
	public static BiomeGenBase Runandor = new BiomeGenRunandor(ConfigurationHelper.runandorB).setHeight(DimensionOrganizer.RunicHeight).setDisableRain();
	public static BiomeGenBase Barathos = new BiomeGenBarathos(ConfigurationHelper.barathosB).setHeight(DimensionOrganizer.BarathosHeight).setDisableRain();
	public static BiomeGenBase Labricon = new BiomeGenLabricon(ConfigurationHelper.labriconB).setHeight(DimensionOrganizer.VoxpondsHeight).setDisableRain();
	public static BiomeGenBase Lelyetia = new BiomeGenLelyetia(ConfigurationHelper.lelyetiaB).setHeight(DimensionOrganizer.VoxpondsHeight);
	public static BiomeGenBase AncientCavern = new BiomeGenAncientCavern(ConfigurationHelper.ancientcavernB).setHeight(DimensionOrganizer.VoxpondsHeight).setDisableRain();
	public static BiomeGenBase Celeve = new BiomeGenCeleve(ConfigurationHelper.celeveB);
	public static BiomeGenBase Crystevia = new BiomeGenCrystevia(ConfigurationHelper.crysteviaB).setDisableRain();
	public static BiomeGenBase Candyland = new BiomeGenCandyland(ConfigurationHelper.candylandB).setHeight(DimensionOrganizer.CandylandHeight);
	public static BiomeGenBase Creeponia = new BiomeGenCreeponia(ConfigurationHelper.creeponiaB).setHeight(DimensionOrganizer.DustopiaHeight);
	public static BiomeGenBase Immortallis = new BiomeGenImmortallis(ConfigurationHelper.immortallisB).setHeight(VoxpondsHeight).setDisableRain();
	public static BiomeGenBase Shyrelands = new BiomeGenShyrelands(ConfigurationHelper.shyrelandsB).setHeight(VoxpondsHeight).setDisableRain();

	public static BiomeGenBase PrecasiaTall = new BiomeGenPrecasiaTall(ConfigurationHelper.precasiaTall).setHeight(PrecasiaHeight);
	public static BiomeGenBase PrecasiaField = new BiomeGenPrecasiaField(ConfigurationHelper.precasiaField).setHeight(CandylandHeight);
	public static BiomeGenBase PrecasiaDesert = new BiomeGenPrecasiaDesert(ConfigurationHelper.precasiaDesert).setHeight(PrecasiaHeight).setDisableRain();

	public static BiomeGenBase AbyssEye = new BiomeGenAbyssEye(ConfigurationHelper.abyssEye).setHeight(AbyssHeight).setDisableRain();
	public static BiomeGenBase AbyssShadow = new BiomeGenAbyssShadow(ConfigurationHelper.abyssShadow).setHeight(AbyssHeight).setDisableRain();

	public static BiomeGenBase IromineSilver = new BiomeGenIromineSilver(ConfigurationHelper.iroSilver).setHeight(IromineHeight).setDisableRain();
	public static BiomeGenBase IromineTech = new BiomeGenIromineTech(ConfigurationHelper.iroTech).setHeight(IromineHeight).setDisableRain();

	public static BiomeGenBase CandylandMarshmallow = new BiomeGenCandylandMarshmallow(ConfigurationHelper.candyMallow).setHeight(CandylandHeight);
	public static BiomeGenBase CandylandChocolate = new BiomeGenCandylandChocolate(ConfigurationHelper.candyChocolate).setHeight(CandylandHeight);
	public static BiomeGenBase CandylandRock = new BiomeGenCandylandRock(ConfigurationHelper.candyRock).setHeight(CandylandHeight);

	public static BiomeGenBase GreckonSkull = new BiomeGenGreckonSkull(ConfigurationHelper.greckonSkull).setHeight(GardenciaHeight).setDisableRain();
	public static BiomeGenBase GreckonForest = new BiomeGenGreckonForest(ConfigurationHelper.greckonForest).setHeight(BoreanHeight).setDisableRain();

	public static BiomeGenBase GardenciaFungal = new BiomeGenGardenciaFungal(ConfigurationHelper.gardenFungal).setHeight(GardenciaHeight);
	public static BiomeGenBase GardenciaMarsh = new BiomeGenGardenciaMarsh(ConfigurationHelper.gardenMarsh).setHeight(GardenciaHeight);

	public static BiomeGenBase BaronForest = new BiomeGenBaronForest(ConfigurationHelper.baronForest).setHeight(BoreanHeight).setDisableRain();
	public static BiomeGenBase BaronMaze = new BiomeGenBaronMaze(ConfigurationHelper.baronMaze).setHeight(BoreanHeight).setDisableRain();

	public static BiomeGenBase DeeplandsFungal = new BiomeGenDeeplandsFungal(ConfigurationHelper.deepFungal).setHeight(BoreanHeight).setDisableRain();
	public static BiomeGenBase DeeplandsShine = new BiomeGenDeeplandsShine(ConfigurationHelper.deepShine).setHeight(BoreanHeight).setDisableRain();

	public static BiomeGenBase BoreanForest = new BiomeGenBoreanForest(ConfigurationHelper.boreanForest).setHeight(BoreanHeight).setDisableRain();
	public static BiomeGenBase BoreanBubble = new BiomeGenBoreanBubble(ConfigurationHelper.boreanBubble).setHeight(BoreanHeight).setDisableRain();
	public static BiomeGenBase BoreanRed = new BiomeGenBoreanRed(ConfigurationHelper.boreanRed).setHeight(BoreanHeight).setDisableRain();

	public static BiomeGenBase DustopiaMarsh = new BiomeGenDustopiaMarsh(ConfigurationHelper.dustMarsh).setHeight(CandylandHeight).setDisableRain();
	public static BiomeGenBase DustopiaPlains = new BiomeGenDustopiaPlains(ConfigurationHelper.dustPlains).setHeight(DustopiaHeight).setDisableRain();
}
