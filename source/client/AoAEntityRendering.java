package net.tslat.aoa3.client;

import com.mojang.datafixers.util.Pair;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LightningBoltRenderer;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.TippableArrowRenderer;
import net.minecraft.client.resources.PlayerSkin;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.client.model.armor.AoAMiscModels;
import net.tslat.aoa3.client.model.entity.animal.CorateeModel;
import net.tslat.aoa3.client.model.entity.animal.MeganeuropsisModel;
import net.tslat.aoa3.client.model.entity.animal.ShinySquidModel;
import net.tslat.aoa3.client.model.entity.boss.EliteSmashModel;
import net.tslat.aoa3.client.model.entity.boss.SmashModel;
import net.tslat.aoa3.client.model.entity.mob.*;
import net.tslat.aoa3.client.model.entity.npc.DryadSpriteModel;
import net.tslat.aoa3.client.model.entity.projectile.CobblestoneProjectileModel;
import net.tslat.aoa3.client.model.entity.projectile.thrown.*;
import net.tslat.aoa3.client.model.misc.FishingCageModel;
import net.tslat.aoa3.client.model.misc.LottoTotemModel;
import net.tslat.aoa3.client.render.blockentity.*;
import net.tslat.aoa3.client.render.entity.AnimatedMobRenderer;
import net.tslat.aoa3.client.render.entity.AnimatedProjectileRenderer;
import net.tslat.aoa3.client.render.entity.AoAMobRenderer;
import net.tslat.aoa3.client.render.entity.JankyJankTempRendererToPreventCrashesWhileInDev;
import net.tslat.aoa3.client.render.entity.animal.*;
import net.tslat.aoa3.client.render.entity.boss.KingBamBamBamRenderer;
import net.tslat.aoa3.client.render.entity.layer.PlayerHaloRenderLayer;
import net.tslat.aoa3.client.render.entity.misc.FishingCageRenderer;
import net.tslat.aoa3.client.render.entity.misc.HaulingBobberRenderer;
import net.tslat.aoa3.client.render.entity.misc.LottoTotemRenderer;
import net.tslat.aoa3.client.render.entity.misc.PixonRenderer;
import net.tslat.aoa3.client.render.entity.mob.AttercopusRenderer;
import net.tslat.aoa3.client.render.entity.mob.DunkleosteusRenderer;
import net.tslat.aoa3.client.render.entity.mob.GhostRenderer;
import net.tslat.aoa3.client.render.entity.projectile.ModelledProjectileRenderer;
import net.tslat.aoa3.client.render.entity.projectile.TexturedProjectileRenderer;
import net.tslat.aoa3.client.render.entity.projectile.blasters.*;
import net.tslat.aoa3.client.render.entity.projectile.bullets.*;
import net.tslat.aoa3.client.render.entity.projectile.cannonshots.*;
import net.tslat.aoa3.client.render.entity.projectile.misc.*;
import net.tslat.aoa3.client.render.entity.projectile.mob.*;
import net.tslat.aoa3.client.render.entity.projectile.staff.*;
import net.tslat.aoa3.common.registration.block.AoABlockEntities;
import net.tslat.aoa3.common.registration.entity.*;
import net.tslat.aoa3.content.entity.animal.fish.BasicFishEntity;
import net.tslat.aoa3.content.entity.animal.fish.BasicLavaFishEntity;
import net.tslat.aoa3.util.ColourUtil;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.renderer.layer.AutoGlowingGeoLayer;

import java.util.HashMap;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

@SuppressWarnings({"unused", "rawtypes"})
public final class AoAEntityRendering {
	private static List<EntityRendererPackage> RENDERER_PACKAGES = new ObjectArrayList<>();

	public static final EntityRendererPackage<?> BLUE_GEMTRAP = new GeckoLibRendererPackage<>(AoAAnimals.BLUE_GEMTRAP).renderer(context -> new BasicWaterFishRenderer(context, new DefaultedEntityGeoModel<BasicFishEntity>(AdventOfAscension.id("animal/fish/blue_gemtrap")).withAltModel(AdventOfAscension.id("animal/fish/gemtrap")).withAltAnimations(AdventOfAscension.id("animal/fish/gemtrap"))));
	public static final EntityRendererPackage<?> CANDLEFISH = new GeckoLibRendererPackage<>(AoAAnimals.CANDLEFISH).renderer(context -> new BasicLavaFishRenderer(context, new DefaultedEntityGeoModel<BasicLavaFishEntity>(AdventOfAscension.id("animal/fish/candlefish")).withAltAnimations(AdventOfAscension.id("animal/fish/jamfish"))));
	public static final EntityRendererPackage<?> CHARRED_CHAR = new GeckoLibRendererPackage<>(AoAAnimals.CHARRED_CHAR).renderer(context -> new BasicLavaFishRenderer(context, new DefaultedEntityGeoModel<>(AdventOfAscension.id("animal/fish/charred_char"))));
	public static final EntityRendererPackage<?> CHOCAW = new GeckoLibRendererPackage<>(AoAAnimals.CHOCAW).renderer(context -> new BasicWaterFishRenderer(context, new DefaultedEntityGeoModel<BasicFishEntity>(AdventOfAscension.id("animal/fish/chocaw")).withAltAnimations(AdventOfAscension.id("animal/fish/jamfish"))));
	public static final EntityRendererPackage<?> CORATEE = new GeckoLibRendererPackage<>(AoAAnimals.CORATEE).model(new CorateeModel());
	public static final EntityRendererPackage<?> CRIMSON_SKIPPER = new GeckoLibRendererPackage<>(AoAAnimals.CRIMSON_SKIPPER).renderer(context -> new BasicLavaFishRenderer(context, new DefaultedEntityGeoModel<BasicLavaFishEntity>(AdventOfAscension.id("animal/fish/crimson_skipper")).withAltModel(AdventOfAscension.id("animal/fish/skipper")).withAltAnimations(AdventOfAscension.id("animal/fish/jamfish"))));
	public static final EntityRendererPackage<?> CRIMSON_STRIPEFISH = new GeckoLibRendererPackage<>(AoAAnimals.CRIMSON_STRIPEFISH).renderer(context -> new BasicLavaFishRenderer(context, new DefaultedEntityGeoModel<BasicLavaFishEntity>(AdventOfAscension.id("animal/fish/crimson_stripefish")).withAltModel(AdventOfAscension.id("animal/fish/stripefish")).withAltAnimations(AdventOfAscension.id("animal/fish/jamfish"))));
	public static final EntityRendererPackage<?> DARK_HATCHETFISH = new GeckoLibRendererPackage<>(AoAAnimals.DARK_HATCHETFISH).renderer(context -> new BasicWaterFishRenderer(context, new DefaultedEntityGeoModel<>(AdventOfAscension.id("animal/fish/dark_hatchetfish"))));
	public static final EntityRendererPackage<?> GREEN_GEMTRAP = new GeckoLibRendererPackage<>(AoAAnimals.GREEN_GEMTRAP).renderer(context -> new BasicWaterFishRenderer(context, new DefaultedEntityGeoModel<BasicFishEntity>(AdventOfAscension.id("animal/fish/green_gemtrap")).withAltModel(AdventOfAscension.id("animal/fish/gemtrap")).withAltAnimations(AdventOfAscension.id("animal/fish/gemtrap"))));
	public static final EntityRendererPackage<?> HYDRONE = new GeckoLibRendererPackage<>(AoAAnimals.HYDRONE).renderer(context -> new BasicWaterFishRenderer(context, new DefaultedEntityGeoModel<>(AdventOfAscension.id("animal/fish/hydrone"))));
	public static final EntityRendererPackage<?> IRONBACK = new GeckoLibRendererPackage<>(AoAAnimals.IRONBACK).renderer(context -> new BasicWaterFishRenderer(context, new DefaultedEntityGeoModel<>(AdventOfAscension.id("animal/fish/ironback"))));
	public static final EntityRendererPackage<?> JAMFISH = new GeckoLibRendererPackage<>(AoAAnimals.JAMFISH).renderer(context -> new BasicWaterFishRenderer(context, new DefaultedEntityGeoModel<>(AdventOfAscension.id("animal/fish/jamfish"))));
	public static final EntityRendererPackage<?> PARAPIRANHA = new GeckoLibRendererPackage<>(AoAAnimals.PARAPIRANHA).renderer(context -> new BasicWaterFishRenderer(context, new DefaultedEntityGeoModel<>(AdventOfAscension.id("animal/fish/parapiranha"))));
	public static final EntityRendererPackage<?> PEARL_STRIPEFISH = new GeckoLibRendererPackage<>(AoAAnimals.PEARL_STRIPEFISH).renderer(context -> new BasicWaterFishRenderer(context, new DefaultedEntityGeoModel<BasicFishEntity>(AdventOfAscension.id("animal/fish/pearl_stripefish")).withAltModel(AdventOfAscension.id("animal/fish/stripefish")).withAltAnimations(AdventOfAscension.id("animal/fish/jamfish"))));
	public static final EntityRendererPackage<?> PURPLE_GEMTRAP = new GeckoLibRendererPackage<>(AoAAnimals.PURPLE_GEMTRAP).renderer(context -> new BasicWaterFishRenderer(context, new DefaultedEntityGeoModel<BasicFishEntity>(AdventOfAscension.id("animal/fish/purple_gemtrap")).withAltModel(AdventOfAscension.id("animal/fish/gemtrap")).withAltAnimations(AdventOfAscension.id("animal/fish/gemtrap"))));
	public static final EntityRendererPackage<?> RAINBOWFISH = new GeckoLibRendererPackage<>(AoAAnimals.RAINBOWFISH).renderer(context -> new BasicWaterFishRenderer(context, new DefaultedEntityGeoModel<>(AdventOfAscension.id("animal/fish/rainbowfish"))));
	public static final EntityRendererPackage<?> RAZORFISH = new GeckoLibRendererPackage<>(AoAAnimals.RAZORFISH).renderer(context -> new BasicWaterFishRenderer(context, new DefaultedEntityGeoModel<BasicFishEntity>(AdventOfAscension.id("animal/fish/razorfish")).withAltAnimations(AdventOfAscension.id("animal/fish/jamfish"))));
	public static final EntityRendererPackage<?> RED_GEMTRAP = new GeckoLibRendererPackage<>(AoAAnimals.RED_GEMTRAP).renderer(context -> new BasicWaterFishRenderer(context, new DefaultedEntityGeoModel<BasicFishEntity>(AdventOfAscension.id("animal/fish/red_gemtrap")).withAltModel(AdventOfAscension.id("animal/fish/gemtrap")).withAltAnimations(AdventOfAscension.id("animal/fish/gemtrap"))));
	public static final EntityRendererPackage<?> REEFTOOTH = new GeckoLibRendererPackage<>(AoAAnimals.REEFTOOTH).renderer(context -> new BasicWaterFishRenderer(context, new DefaultedEntityGeoModel<>(AdventOfAscension.id("animal/fish/reeftooth"))));
	public static final EntityRendererPackage<?> ROCKETFISH = new GeckoLibRendererPackage<>(AoAAnimals.ROCKETFISH).renderer(context -> new BasicWaterFishRenderer(context, new DefaultedEntityGeoModel<>(AdventOfAscension.id("animal/fish/rocketfish"))));
	public static final EntityRendererPackage<?> SAILBACK = new GeckoLibRendererPackage<>(AoAAnimals.SAILBACK).renderer(context -> new BasicWaterFishRenderer(context, new DefaultedEntityGeoModel<BasicFishEntity>(AdventOfAscension.id("animal/fish/sailback")).withAltAnimations(AdventOfAscension.id("animal/fish/jamfish"))));
	public static final EntityRendererPackage<?> SAPPHIRE_STRIDER = new GeckoLibRendererPackage<>(AoAAnimals.SAPPHIRE_STRIDER).renderer(context -> new BasicWaterFishRenderer(context, new DefaultedEntityGeoModel<>(AdventOfAscension.id("animal/fish/sapphire_strider"))));
	public static final EntityRendererPackage<?> SKELECANTH = new GeckoLibRendererPackage<>(AoAAnimals.SKELECANTH).renderer(context -> new BasicWaterFishRenderer(context, new DefaultedEntityGeoModel<BasicFishEntity>(AdventOfAscension.id("animal/fish/skelecanth")).withAltAnimations(AdventOfAscension.id("animal/fish/jamfish"))));
	public static final EntityRendererPackage<?> TURQUOISE_STRIPEFISH = new GeckoLibRendererPackage<>(AoAAnimals.TURQUOISE_STRIPEFISH).renderer(context -> new BasicWaterFishRenderer(context, new DefaultedEntityGeoModel<BasicFishEntity>(AdventOfAscension.id("animal/fish/turquoise_stripefish")).withAltModel(AdventOfAscension.id("animal/fish/stripefish")).withAltAnimations(AdventOfAscension.id("animal/fish/jamfish"))));
	public static final EntityRendererPackage<?> VIOLET_SKIPPER = new GeckoLibRendererPackage<>(AoAAnimals.VIOLET_SKIPPER).renderer(context -> new BasicWaterFishRenderer(context, new DefaultedEntityGeoModel<BasicFishEntity>(AdventOfAscension.id("animal/fish/violet_skipper")).withAltModel(AdventOfAscension.id("animal/fish/skipper")).withAltAnimations(AdventOfAscension.id("animal/fish/jamfish"))));
	public static final EntityRendererPackage<?> WHITE_GEMTRAP = new GeckoLibRendererPackage<>(AoAAnimals.WHITE_GEMTRAP).renderer(context -> new BasicWaterFishRenderer(context, new DefaultedEntityGeoModel<BasicFishEntity>(AdventOfAscension.id("animal/fish/white_gemtrap")).withAltModel(AdventOfAscension.id("animal/fish/gemtrap")).withAltAnimations(AdventOfAscension.id("animal/fish/gemtrap"))));
	public static final EntityRendererPackage<?> YELLOW_GEMTRAP = new GeckoLibRendererPackage<>(AoAAnimals.YELLOW_GEMTRAP).renderer(context -> new BasicWaterFishRenderer(context, new DefaultedEntityGeoModel<BasicFishEntity>(AdventOfAscension.id("animal/fish/yellow_gemtrap")).withAltModel(AdventOfAscension.id("animal/fish/gemtrap")).withAltAnimations(AdventOfAscension.id("animal/fish/gemtrap"))));
	public static final EntityRendererPackage<?> SHINY_SQUID = new EntityRendererPackage<>(AoAAnimals.SHINY_SQUID).defineLayer("shiny_squid", ShinySquidModel::createBodyLayer).provider(ShinySquidRenderer::new);

	public static final EntityRendererPackage<?> THORNY_PLANT_SPROUT = new GeckoLibRendererPackage<>(AoAMiscEntities.THORNY_PLANT_SPROUT).path("mob/misc/thorny_plant_sprout");

	public static final EntityRendererPackage<?> HORNDRON = new GeckoLibRendererPackage<>(AoAAnimals.HORNDRON).model(new HorndronModel());
	public static final EntityRendererPackage<?> DEINOTHERIUM = new GeckoLibRendererPackage<>(AoAAnimals.DEINOTHERIUM).model(new DeinotheriumModel());
	public static final EntityRendererPackage<?> OPTERYX = new GeckoLibRendererPackage<>(AoAAnimals.OPTERYX).renderer(OpteryxRenderer::new);

	public static final EntityRendererPackage<?> ANCIENT_GOLEM = new GeckoLibRendererPackage<>(AoAMobs.ANCIENT_GOLEM).path("mob/overworld/ancient_golem");
	public static final EntityRendererPackage<?> ANGLER = new GeckoLibRendererPackage<>(AoAMobs.ANGLER).path("mob/lborean/angler");
	public static final EntityRendererPackage<?> ARCBEAST = new GeckoLibRendererPackage<>(AoAMobs.ARCBEAST).path("mob/shyrelands/arcbeast");
	public static final EntityRendererPackage<?> AROCKNID = new GeckoLibRendererPackage<>(AoAMobs.AROCKNID).path("mob/deeplands/arocknid");
	public static final EntityRendererPackage<?> ATTERCOPUS = new GeckoLibRendererPackage<>(AoAMobs.ATTERCOPUS).renderer(AttercopusRenderer::new);
	public static final EntityRendererPackage<?> BOMB_CARRIER = new GeckoLibRendererPackage<>(AoAMobs.BOMB_CARRIER).path("mob/overworld/bomb_carrier");
	public static final EntityRendererPackage<?> BUSH_BABY = new GeckoLibRendererPackage<>(AoAMobs.BUSH_BABY).path("mob/overworld/bush_baby");
	public static final EntityRendererPackage<?> CASE_CONSTRUCT = new GeckoLibRendererPackage<>(AoAMobs.CASE_CONSTRUCT).path("mob/deeplands/case_construct");
	public static final EntityRendererPackage<?> CAVE_CREEP = new GeckoLibRendererPackage<>(AoAMobs.CAVE_CREEP).path("mob/deeplands/cave_creep");
	public static final EntityRendererPackage<?> CHARGER = new GeckoLibRendererPackage<>(AoAMobs.CHARGER).model(new ChargerModel());
	public static final EntityRendererPackage<?> CHOMPER = new GeckoLibRendererPackage<>(AoAMobs.CHOMPER).path("mob/overworld/chomper");
	public static final EntityRendererPackage<?> CYCLOPS = new GeckoLibRendererPackage<>(AoAMobs.CYCLOPS).path("mob/overworld/cyclops", true);
	public static final EntityRendererPackage<?> DOUBLER = new GeckoLibRendererPackage<>(AoAMobs.DOUBLER).path("mob/deeplands/doubler");
	public static final EntityRendererPackage<?> DUNKLEOSTEUS = new GeckoLibRendererPackage<>(AoAMobs.DUNKLEOSTEUS).renderer(DunkleosteusRenderer::new);
	public static final EntityRendererPackage<?> EMBRAKE = new GeckoLibRendererPackage<>(AoAMobs.EMBRAKE).path("mob/nether/embrake", true).emissive();
	public static final EntityRendererPackage<?> FLAMEWALKER = new GeckoLibRendererPackage<>(AoAMobs.FLAMEWALKER).model(new FlamewalkerModel()).emissive();
	public static final EntityRendererPackage<?> FLYE = new GeckoLibRendererPackage<>(AoAMobs.FLYE).path("mob/lelyetia/flye");
	public static final EntityRendererPackage<?> GHOST = new GeckoLibRendererPackage<>(AoAMobs.GHOST).renderer(GhostRenderer::new);
	public static final EntityRendererPackage<?> GOBLIN = new GeckoLibRendererPackage<>(AoAMobs.GOBLIN).path("mob/overworld/goblin");
	public static final EntityRendererPackage<?> ICE_GIANT = new GeckoLibRendererPackage<>(AoAMobs.ICE_GIANT).path("mob/overworld/ice_giant");
	public static final EntityRendererPackage<?> INFERNAL = new GeckoLibRendererPackage<>(AoAMobs.INFERNAL).path("mob/nether/infernal").emissive();
	public static final EntityRendererPackage<?> KING_CHARGER = new GeckoLibRendererPackage<>(AoAMobs.KING_CHARGER).path("mob/overworld/king_charger");
	public static final EntityRendererPackage<?> LEAFY_GIANT = new GeckoLibRendererPackage<>(AoAMobs.LEAFY_GIANT).path("mob/overworld/leafy_giant");
	public static final EntityRendererPackage<?> LITTLE_BAM = new GeckoLibRendererPackage<>(AoAMobs.LITTLE_BAM).path("mob/nether/little_bam").emissive();
	public static final EntityRendererPackage<?> MEGANEUROPSIS = new GeckoLibRendererPackage<>(AoAMobs.MEGANEUROPSIS).model(new MeganeuropsisModel()).transparent();
	public static final EntityRendererPackage<?> MUNCHER = new GeckoLibRendererPackage<>(AoAMobs.MUNCHER).path("mob/lborean/muncher");
	public static final EntityRendererPackage<?> NEPTUNO = new GeckoLibRendererPackage<>(AoAMobs.NEPTUNO).path("mob/lborean/neptuno");
	public static final EntityRendererPackage<?> NETHENGEIC_BEAST = new GeckoLibRendererPackage<>(AoAMobs.NETHENGEIC_BEAST).path("mob/nether/nethengeic_beast", true).emissive();
	public static final EntityRendererPackage<?> NIPPER = new GeckoLibRendererPackage<>(AoAMobs.NIPPER).path("mob/deeplands/nipper");
	public static final EntityRendererPackage<?> OMNILIGHT = new GeckoLibRendererPackage<>(AoAMobs.OMNILIGHT).path("mob/shyrelands/omnilight");
	public static final EntityRendererPackage<?> ROCK_CRAWLER = new GeckoLibRendererPackage<>(AoAMobs.ROCK_CRAWLER).path("mob/deeplands/rock_crawler");
	public static final EntityRendererPackage<?> ROCK_CRITTER = new GeckoLibRendererPackage<>(AoAMobs.ROCK_CRITTER).path("mob/deeplands/rock_critter");
	public static final EntityRendererPackage<?> ROCKBITER = new GeckoLibRendererPackage<>(AoAMobs.ROCKBITER).path("mob/deeplands/rockbiter");
	public static final EntityRendererPackage<?> SAND_GIANT = new GeckoLibRendererPackage<>(AoAMobs.SAND_GIANT).path("mob/overworld/sand_giant");
	public static final EntityRendererPackage<?> SASQUATCH = new GeckoLibRendererPackage<>(AoAMobs.SASQUATCH).path("mob/overworld/sasquatch");
	public static final EntityRendererPackage<?> SEA_VIPER = new GeckoLibRendererPackage<>(AoAMobs.SEA_VIPER).path("mob/lborean/sea_viper");
	public static final EntityRendererPackage<?> SKELETAL_ABOMINATION = new GeckoLibRendererPackage<>(AoAMobs.SKELETAL_ABOMINATION).model(new SkeletalAbominationModel());
	public static final EntityRendererPackage<?> SMILODON = new GeckoLibRendererPackage<>(AoAMobs.SMILODON).path("mob/precasia/smilodon", true);
	public static final EntityRendererPackage<?> SPINOLEDON = new GeckoLibRendererPackage<>(AoAMobs.SPINOLEDON).model(new SpinoledonModel());
	public static final EntityRendererPackage<?> STONE_GIANT = new GeckoLibRendererPackage<>(AoAMobs.STONE_GIANT).path("mob/overworld/stone_giant");
	public static final EntityRendererPackage<?> SYSKER = new GeckoLibRendererPackage<>(AoAMobs.SYSKER).path("mob/shyrelands/sysker");
	public static final EntityRendererPackage<?> TRACKER = new GeckoLibRendererPackage<>(AoAMobs.TRACKER).path("mob/lelyetia/tracker");
	public static final EntityRendererPackage<?> TREE_SPIRIT = new GeckoLibRendererPackage<>(AoAMobs.TREE_SPIRIT).path("mob/overworld/tree_spirit").emissive();
	public static final EntityRendererPackage<?> UNDEAD_TROLL = new GeckoLibRendererPackage<>(AoAMobs.UNDEAD_TROLL).path("mob/mysterium/undead_troll");
	public static final EntityRendererPackage<?> VELORAPTOR = new GeckoLibRendererPackage<>(AoAMobs.VELORAPTOR).model(new VeloraptorModel());
	public static final EntityRendererPackage<?> VISULAR = new GeckoLibRendererPackage<>(AoAMobs.VISULAR).path("mob/lunalus/visular");
	public static final EntityRendererPackage<?> VISULON = new GeckoLibRendererPackage<>(AoAMobs.VISULON).path("mob/lunalus/visulon");
	public static final EntityRendererPackage<?> VOID_WALKER = new GeckoLibRendererPackage<>(AoAMobs.VOID_WALKER).path("mob/overworld/void_walker");
	public static final EntityRendererPackage<?> WOOD_GIANT = new GeckoLibRendererPackage<>(AoAMobs.WOOD_GIANT).model(new WoodGiantModel());
	public static final EntityRendererPackage<?> YETI = new GeckoLibRendererPackage<>(AoAMobs.YETI).path("mob/overworld/yeti");

	public static final EntityRendererPackage<?> SMASH = new GeckoLibRendererPackage<>(AoAMobs.SMASH).model(new SmashModel());
	public static final EntityRendererPackage<?> ELITE_SMASH = new GeckoLibRendererPackage<>(AoAMobs.ELITE_SMASH).model(new EliteSmashModel());

	public static final EntityRendererPackage<?> NETHENGEIC_WITHER = new GeckoLibRendererPackage<>(AoAMobs.NETHENGEIC_WITHER).path("boss/nethengeic_wither/nethengeic_wither").emissive();
	public static final EntityRendererPackage<?> ELITE_NETHENGEIC_WITHER = new GeckoLibRendererPackage<>(AoAMobs.ELITE_NETHENGEIC_WITHER).path("boss/nethengeic_wither/elite_nethengeic_wither").emissive();

	public static final EntityRendererPackage<?> KING_BAMBAMBAM = new GeckoLibRendererPackage<>(AoAMobs.KING_BAMBAMBAM).renderer(KingBamBamBamRenderer::new);
	public static final EntityRendererPackage<?> ELITE_KING_BAMBAMBAM = new GeckoLibRendererPackage<>(AoAMobs.ELITE_KING_BAMBAMBAM).path("boss/king_bambambam/elite_king_bambambam", true).emissive();

	public static final EntityRendererPackage<?> TYROSAUR = new GeckoLibRendererPackage<>(AoAMobs.TYROSAUR).path("boss/tyrosaur/tyrosaur");
	public static final EntityRendererPackage<?> ELITE_TYROSAUR = new GeckoLibRendererPackage<>(AoAMobs.ELITE_TYROSAUR).path("boss/tyrosaur/elite_tyrosaur", true);

	public static final EntityRendererPackage<?> SKELETRON = new GeckoLibRendererPackage<>(AoAMobs.SKELETRON).path("boss/skeletron/skeletron");
	public static final EntityRendererPackage<?> ELITE_SKELETRON = new GeckoLibRendererPackage<>(AoAMobs.ELITE_SKELETRON).path("boss/skeletron/elite_skeletron", true);

	//public static final EntityRendererPackage<?> ASSASSIN = new EntityRendererPackage<>(AoANpcs.ASSASSIN).defineLayer("assassin", humanoidLayerDefinition()).defaultMobRenderer(HumanoidModel::new, "textures/entity/npc/trader/assassin.png");
	public static final EntityRendererPackage<?> CREEP_BANKER = new EntityRendererPackage<>(AoANpcs.CREEP_BANKER).defineLayer("creep_banker", humanoidLayerDefinition()).defaultMobRenderer(HumanoidModel::new, "textures/entity/npc/banker/creep_banker.png");
	public static final EntityRendererPackage<?> CRYSTAL_TRADER = new EntityRendererPackage<>(AoANpcs.CRYSTAL_TRADER).defineLayer("crystal_trader", humanoidLayerDefinition()).defaultMobRenderer(HumanoidModel::new, "textures/entity/npc/trader/crystal_trader.png");
	public static final EntityRendererPackage<?> DUNGEON_KEEPER = new EntityRendererPackage<>(AoANpcs.DUNGEON_KEEPER).defineLayer("dungeon_keeper", humanoidLayerDefinition()).defaultMobRenderer(HumanoidModel::new, "textures/entity/npc/trader/dungeon_keeper.png");
	public static final EntityRendererPackage<?> EXPLOSIVES_EXPERT = new EntityRendererPackage<>(AoANpcs.EXPLOSIVES_EXPERT).defineLayer("explosives_expert", humanoidLayerDefinition()).defaultMobRenderer(HumanoidModel::new, "textures/entity/npc/trader/explosives_expert.png");
	public static final EntityRendererPackage<?> METALLOID = new EntityRendererPackage<>(AoANpcs.METALLOID).defineLayer("metalloid", humanoidLayerDefinition()).defaultMobRenderer(HumanoidModel::new, "textures/entity/npc/trader/metalloid.png");
	public static final EntityRendererPackage<?> NATURALIST = new EntityRendererPackage<>(AoANpcs.NATURALIST).defineLayer("naturalist", humanoidLayerDefinition()).defaultMobRenderer(HumanoidModel::new, "textures/entity/npc/trader/naturalist.png");
	public static final EntityRendererPackage<?> SHYRE_ARCHER = new EntityRendererPackage<>(AoANpcs.SHYRE_ARCHER).defineLayer("shyre_archer", humanoidLayerDefinition()).defaultMobRenderer(HumanoidModel::new, "textures/entity/npc/trader/shyre_archer.png");
	public static final EntityRendererPackage<?> SHYRE_BANKER = new EntityRendererPackage<>(AoANpcs.SHYRE_BANKER).defineLayer("shyre_banker", humanoidLayerDefinition()).defaultMobRenderer(HumanoidModel::new, "textures/entity/npc/banker/shyre_banker.png");
	public static final EntityRendererPackage<?> TOKEN_COLLECTOR = new EntityRendererPackage<>(AoANpcs.TOKEN_COLLECTOR).defineLayer("token_collector", humanoidLayerDefinition()).defaultMobRenderer(HumanoidModel::new, "textures/entity/npc/trader/token_collector.png");
	public static final EntityRendererPackage<?> TROLL_TRADER = new GeckoLibRendererPackage<>(AoANpcs.TROLL_TRADER).path("npc/trader/troll_trader");
	public static final EntityRendererPackage<?> SKILL_MASTER = new GeckoLibRendererPackage<>(AoANpcs.SKILL_MASTER).path("npc/trader/skill_master");
	public static final EntityRendererPackage<?> CORRUPTED_TRAVELLER = new GeckoLibRendererPackage<>(AoANpcs.CORRUPTED_TRAVELLER).path("npc/trader/corrupted_traveller", true).transparent();
	public static final EntityRendererPackage<?> STORE_KEEPER = new GeckoLibRendererPackage<>(AoANpcs.STORE_KEEPER).path("npc/trader/store_keeper");

	public static final EntityRendererPackage<?> LOTTOMAN = new GeckoLibRendererPackage<>(AoANpcs.LOTTOMAN).path("npc/trader/lottoman", true);
	public static final EntityRendererPackage<?> UNDEAD_HERALD = new GeckoLibRendererPackage<>(AoANpcs.UNDEAD_HERALD).path("npc/trader/undead_herald", true);

	public static final EntityRendererPackage<?> DRYAD_SPRITE = new GeckoLibRendererPackage<>(AoANpcs.DRYAD_SPRITE).model(new DryadSpriteModel());

	public static final EntityRendererPackage<?> GRENADE = new EntityRendererPackage<>(AoAProjectiles.GRENADE).defineLayer("grenade", GrenadeModel::createLayerDefinition).provider(context -> new ModelledProjectileRenderer<>(context, AoAEntityRendering.GRENADE.getMainLayerLocation(), GrenadeModel::new, AdventOfAscension.id("textures/entity/projectile/thrown/grenade.png")));
	public static final EntityRendererPackage<?> RUNIC_BOMB = new EntityRendererPackage<>(AoAProjectiles.RUNIC_BOMB).provider(context -> new ModelledProjectileRenderer<>(context, AoAEntityRendering.GRENADE.getMainLayerLocation(), GrenadeModel::new, AdventOfAscension.id("textures/entity/projectile/thrown/runic_bomb.png")));
	public static final EntityRendererPackage<?> CHAKRAM = new EntityRendererPackage<>(AoAProjectiles.CHAKRAM).defineLayer("chakram", ChakramModel::createLayerDefinition).provider(context -> new ModelledProjectileRenderer<>(context, AoAEntityRendering.CHAKRAM.getMainLayerLocation(), ChakramModel::new, AdventOfAscension.id("textures/entity/projectile/thrown/chakram.png")));
	public static final EntityRendererPackage<?> VULKRAM = new EntityRendererPackage<>(AoAProjectiles.VULKRAM).provider(context -> new ModelledProjectileRenderer<>(context, AoAEntityRendering.CHAKRAM.getMainLayerLocation(), ChakramModel::new, AdventOfAscension.id("textures/entity/projectile/thrown/vulkram.png")));
	public static final EntityRendererPackage<?> SLICE_STAR = new EntityRendererPackage<>(AoAProjectiles.SLICE_STAR).defineLayer("slice_star", SliceStarModel::createLayerDefinition).provider(context -> new ModelledProjectileRenderer<>(context, AoAEntityRendering.SLICE_STAR.getMainLayerLocation(), SliceStarModel::new, AdventOfAscension.id("textures/entity/projectile/thrown/slice_star.png")));
	public static final EntityRendererPackage<?> GOO_BALL = new EntityRendererPackage<>(AoAProjectiles.GOO_BALL).defineLayer("goo_ball", GooBallModel::createLayerDefinition).provider(context -> new ModelledProjectileRenderer<>(context, AoAEntityRendering.GOO_BALL.getMainLayerLocation(), GooBallModel::new, AdventOfAscension.id("textures/entity/projectile/thrown/goo_ball.png")));
	public static final EntityRendererPackage<?> HELLFIRE = new EntityRendererPackage<>(AoAProjectiles.HELLFIRE).defineLayer("hellfire", HellfireModel::createLayerDefinition).provider(context -> new ModelledProjectileRenderer<>(context, AoAEntityRendering.HELLFIRE.getMainLayerLocation(), HellfireModel::new, AdventOfAscension.id("textures/entity/projectile/thrown/hellfire.png")));
	public static final EntityRendererPackage<?> HARDENED_PARAPIRANHA = new GeckoLibRendererPackage<>(AoAProjectiles.HARDENED_PARAPIRANHA).path("projectile/thrown/hardened_parapiranha").projectile();

	public static final EntityRendererPackage<?> REINFORCED_BOBBER = new EntityRendererPackage<>(AoAMiscEntities.REINFORCED_BOBBER).provider(HaulingBobberRenderer::new);
	public static final EntityRendererPackage<?> GOLD_BOBBER = new EntityRendererPackage<>(AoAMiscEntities.GOLD_BOBBER).provider(context -> new HaulingBobberRenderer(context, AdventOfAscension.id("textures/entity/misc/gold_bobber.png")));
	public static final EntityRendererPackage<?> THERMAL_BOBBER = new EntityRendererPackage<>(AoAMiscEntities.THERMAL_BOBBER).provider(context -> new HaulingBobberRenderer(context, AdventOfAscension.id("textures/entity/misc/thermal_bobber.png")));

	public static final EntityRendererPackage<?> FISHING_CAGE = new EntityRendererPackage<>(AoAMiscEntities.FISHING_CAGE).defineLayer("fishing_cage", FishingCageModel::createLayerDefinition).provider(FishingCageRenderer::new);
	public static final EntityRendererPackage<?> LOTTO_TOTEM = new EntityRendererPackage<>(AoAMiscEntities.LOTTO_TOTEM).defineLayer("lotto_totem", LottoTotemModel::createLayerDefinition).provider(LottoTotemRenderer::new);
	public static final EntityRendererPackage<?> SAND_GIANT_PIT_TRAP = new GeckoLibRendererPackage<>(AoAMiscEntities.SAND_GIANT_PIT_TRAP).path("misc/sand_giant_pit_trap").nonLiving();
	public static final EntityRendererPackage<?> SAND_GIANT_SPIKE_TRAP = new GeckoLibRendererPackage<>(AoAMiscEntities.SAND_GIANT_SPIKE_TRAP).path("misc/sand_giant_spike_trap").nonLiving();

	public static final EntityRendererPackage<?> LIGHTNING = new EntityRendererPackage<>(AoAMiscEntities.CUSTOMISABLE_LIGHTNING_BOLT).provider(LightningBoltRenderer::new);

	public static final EntityRendererPackage<?> PIXON = new EntityRendererPackage<>(AoAMiscEntities.PIXON).provider(PixonRenderer::new);

	public static final EntityRendererPackage<?> ANEMIA_BOMB = new EntityRendererPackage<>(AoAProjectiles.ANEMIA_BOMB).provider(AnemiaBombRenderer::new);
	public static final EntityRendererPackage<?> AQUABALL = new EntityRendererPackage<>(AoAProjectiles.AQUABALL).provider(AquaballRenderer::new);
	public static final EntityRendererPackage<?> AQUATIC_SHOT = new EntityRendererPackage<>(AoAProjectiles.AQUATIC_SHOT).provider(AquaticShotRenderer::new);
	public static final EntityRendererPackage<?> ARROW = new EntityRendererPackage<>(AoAProjectiles.ARROW).provider(TippableArrowRenderer::new);
	public static final EntityRendererPackage<?> ATOMIZER_BOUNCE = new EntityRendererPackage<>(AoAProjectiles.ATOMIZER_BOUNCE).provider(AtomizerBounceRenderer::new);
	public static final EntityRendererPackage<?> ATOMIZER_SHOT = new EntityRendererPackage<>(AoAProjectiles.ATOMIZER_SHOT).provider(AtomizerShotRenderer::new);
	public static final EntityRendererPackage<?> BALLOON_BOMB = new EntityRendererPackage<>(AoAProjectiles.BALLOON_BOMB).provider(context -> new TexturedProjectileRenderer<>(context, AdventOfAscension.id("textures/entity/projectile/cannonshots/balloon_bomb.png")));
	public static final EntityRendererPackage<?> BARONESS_SHOT = new EntityRendererPackage<>(AoAProjectiles.BARONESS_SHOT).provider(BaronessShotRenderer::new);
	public static final EntityRendererPackage<?> BARON_SHOT = new EntityRendererPackage<>(AoAProjectiles.BARON_SHOT).provider(BaronShotRenderer::new);
	public static final EntityRendererPackage<?> BEAMER_SHOT = new EntityRendererPackage<>(AoAProjectiles.BEAMER_SHOT).provider(BeamerShotRenderer::new);
	public static final EntityRendererPackage<?> BLOODBALL = new EntityRendererPackage<>(AoAProjectiles.BLOODBALL).provider(BloodballRenderer::new);
	public static final EntityRendererPackage<?> BLOOD_DRAINER = new EntityRendererPackage<>(AoAProjectiles.BLOOD_DRAINER).provider(BloodDrainerRenderer::new);
	public static final EntityRendererPackage<?> BLUE_BULLET = new EntityRendererPackage<>(AoAProjectiles.BLUE_BULLET).provider(context -> new ColouredTexturedProjectileRenderer<>(context, ColourUtil.BLUE, AdventOfAscension.id("textures/entity/projectile/bullets/limonite_bullet.png")));
	public static final EntityRendererPackage<?> BLUE_GUARDIAN_SHOT = new EntityRendererPackage<>(AoAProjectiles.BLUE_GUARDIAN_SHOT).provider(BlueGuardianShotRenderer::new);
	public static final EntityRendererPackage<?> BOMB_CARRIER_DYNAMITE = new GeckoLibRendererPackage<>(AoAProjectiles.BOMB_CARRIER_DYNAMITE).path("projectile/mob/bomb_carrier_dynamite").projectile();
	public static final EntityRendererPackage<?> BONE_BULLET = new EntityRendererPackage<>(AoAProjectiles.BONE_BULLET).provider(context -> new TexturedProjectileRenderer<>(context, AdventOfAscension.id("textures/entity/projectile/bullets/bone_pellet.png")));
	public static final EntityRendererPackage<?> BONE_PELLET = new EntityRendererPackage<>(AoAProjectiles.BONE_PELLET).provider(context -> new TexturedProjectileRenderer<>(context, AdventOfAscension.id("textures/entity/projectile/bullets/bone_pellet.png")));
	public static final EntityRendererPackage<?> BOZO_BALL = new EntityRendererPackage<>(AoAProjectiles.BOZO_BALL).provider(context -> new BozoBallRenderer(context, AdventOfAscension.id("textures/entity/projectile/cannonshots/cannonball.png")));
	public static final EntityRendererPackage<?> BUBBLE_SHOT = new EntityRendererPackage<>(AoAProjectiles.BUBBLE_SHOT).provider(context -> new TexturedProjectileRenderer<>(context, AdventOfAscension.id("textures/entity/projectile/misc/bubble_shot.png")));
	public static final EntityRendererPackage<?> BULLET = new EntityRendererPackage<>(AoAProjectiles.BULLET).provider(context -> new TexturedProjectileRenderer<>(context, AdventOfAscension.id("textures/entity/projectile/bullets/limonite_bullet.png")));
	public static final EntityRendererPackage<?> BULLET_SHOT = new EntityRendererPackage<>(AoAProjectiles.BULLET_SHOT).provider(context -> new TexturedProjectileRenderer<>(context, AdventOfAscension.id("textures/entity/projectile/bullets/limonite_bullet.png")));
	public static final EntityRendererPackage<?> CANNONBALL = new EntityRendererPackage<>(AoAProjectiles.CANNONBALL).provider(context -> new TexturedProjectileRenderer<>(context, AdventOfAscension.id("textures/entity/projectile/cannonshots/cannonball.png")));
	public static final EntityRendererPackage<?> CARROT_BALL = new EntityRendererPackage<>(AoAProjectiles.CARROT_BALL).provider(context -> new CarrotBallRenderer(context, AdventOfAscension.id("textures/entity/projectile/bullets/carrot_shot.png")));
	public static final EntityRendererPackage<?> CELESTIAL_FALL = new EntityRendererPackage<>(AoAProjectiles.CELESTIAL_FALL).provider(CelestialFallRenderer::new);
	public static final EntityRendererPackage<?> CHERRY_SHOT = new EntityRendererPackage<>(AoAProjectiles.CHERRY_SHOT).provider(CherryShotRenderer::new);
	public static final EntityRendererPackage<?> CHILLI_SHOT = new EntityRendererPackage<>(AoAProjectiles.CHILLI_SHOT).provider(context -> new ChilliShotRenderer(context, AdventOfAscension.id("textures/entity/projectile/bullets/chilli_shot.png")));
	public static final EntityRendererPackage<?> CLOWN_BALL = new EntityRendererPackage<>(AoAProjectiles.CLOWN_BALL).provider(context -> new ClownBallRenderer(context, AdventOfAscension.id("textures/entity/projectile/cannonshots/cannonball.png")));
	public static final EntityRendererPackage<?> CLOWN_SHOT = new EntityRendererPackage<>(AoAProjectiles.CLOWN_SHOT).provider(ClownShotRenderer::new);
	public static final EntityRendererPackage<?> CONFETTI_CLUSTER = new EntityRendererPackage<>(AoAProjectiles.CONFETTI_CLUSTER).provider(ConfettiClusterRenderer::new);
	public static final EntityRendererPackage<?> CONFETTI_SHOT = new EntityRendererPackage<>(AoAProjectiles.CONFETTI_SHOT).provider(ConfettiShotRenderer::new);
	public static final EntityRendererPackage<?> CONSTRUCT_SHOT = new EntityRendererPackage<>(AoAProjectiles.CONSTRUCT_SHOT).provider(ConstructShotRenderer::new);
	public static final EntityRendererPackage<?> COTTON_CANDOR_SHOT = new EntityRendererPackage<>(AoAProjectiles.COTTON_CANDOR_SHOT).provider(CottonCandorShotRenderer::new);
	public static final EntityRendererPackage<?> CRAEXXEUS_NUKE = new EntityRendererPackage<>(AoAProjectiles.CRAEXXEUS_NUKE).provider(CraexxeusNukeRenderer::new);
	public static final EntityRendererPackage<?> CRAEXXEUS_SHOT = new EntityRendererPackage<>(AoAProjectiles.CRAEXXEUS_SHOT).provider(CraexxeusShotRenderer::new);
	public static final EntityRendererPackage<?> CREEPER_SHOT = new EntityRendererPackage<>(AoAProjectiles.CREEPER_SHOT).provider(CreeperShotRenderer::new);
	public static final EntityRendererPackage<?> CREEP_BOMB = new EntityRendererPackage<>(AoAProjectiles.CREEP_BOMB).provider(CreepBombRenderer::new);
	public static final EntityRendererPackage<?> CREEP_TUBE = new EntityRendererPackage<>(AoAProjectiles.CREEP_TUBE).provider(CreepTubeRenderer::new);
	public static final EntityRendererPackage<?> CYAN_SHOT = new EntityRendererPackage<>(AoAProjectiles.CYAN_SHOT).provider(CyanShotRenderer::new);
	public static final EntityRendererPackage<?> DEATH_RAY = new EntityRendererPackage<>(AoAProjectiles.DEATH_RAY).provider(DeathRayRenderer::new);
	public static final EntityRendererPackage<?> DESTROYER_SHOT = new EntityRendererPackage<>(AoAProjectiles.DESTROYER_SHOT).provider(DestroyerShotRenderer::new);
	public static final EntityRendererPackage<?> DESTRUCTION_SHOT = new EntityRendererPackage<>(AoAProjectiles.DESTRUCTION_SHOT).provider(context -> new TexturedProjectileRenderer<>(context, AdventOfAscension.id("textures/entity/projectile/misc/destruction_ball.png")));
	public static final EntityRendererPackage<?> DISCHARGE_SHOT = new EntityRendererPackage<>(AoAProjectiles.DISCHARGE_SHOT).provider(context -> new TexturedProjectileRenderer<>(context, AdventOfAscension.id("textures/entity/projectile/bullets/discharge_shot.png")));
	public static final EntityRendererPackage<?> DISCHARGE_SLUG = new EntityRendererPackage<>(AoAProjectiles.DISCHARGE_SLUG).provider(DischargeSlugRenderer::new);
	public static final EntityRendererPackage<?> DOOM_SHOT = new EntityRendererPackage<>(AoAProjectiles.DOOM_SHOT).provider(DoomShotRenderer::new);
	public static final EntityRendererPackage<?> ENERGY_SHOT = new EntityRendererPackage<>(AoAProjectiles.ENERGY_SHOT).provider(EnergyShotRenderer::new);
	public static final EntityRendererPackage<?> ERADICATOR_SHOT = new EntityRendererPackage<>(AoAProjectiles.ERADICATOR_SHOT).provider(EradicatorShotRenderer::new);
	public static final EntityRendererPackage<?> EREBON_STICKLER_SHOT = new EntityRendererPackage<>(AoAProjectiles.EREBON_STICKLER_SHOT).provider(ErebonSticklerShotRenderer::new);
	public static final EntityRendererPackage<?> EREBON_STICKLER_STUCK = new EntityRendererPackage<>(AoAProjectiles.EREBON_STICKLER_STUCK).provider(ErebonSticklerStuckRenderer::new);
	public static final EntityRendererPackage<?> FIREFLY_SHOT = new EntityRendererPackage<>(AoAProjectiles.FIREFLY_SHOT).provider(FireflyShotRenderer::new);
	public static final EntityRendererPackage<?> FIRESTORM_FALL = new EntityRendererPackage<>(AoAProjectiles.FIRESTORM_FALL).provider(FirestormFallRenderer::new);
	public static final EntityRendererPackage<?> FIRE_BULLET = new EntityRendererPackage<>(AoAProjectiles.FIRE_BULLET).provider(context -> new FireBulletRenderer(context, AdventOfAscension.id("textures/entity/projectile/bullets/limonite_bullet.png")));
	public static final EntityRendererPackage<?> FLORO_RPG = new EntityRendererPackage<>(AoAProjectiles.FLORO_RPG).provider(context -> new FloroRPGRenderer(context, AdventOfAscension.id("textures/entity/projectile/thrown/grenade.png")));
	public static final EntityRendererPackage<?> FLOWER_BALL = new EntityRendererPackage<>(AoAProjectiles.FLOWER_BALL).provider(context -> new FlowerBallRenderer(context, AdventOfAscension.id("textures/entity/projectile/cannonshots/cannonball.png")));
	public static final EntityRendererPackage<?> FLOWER_SHOT = new EntityRendererPackage<>(AoAProjectiles.FLOWER_SHOT).provider(FlowerShotRenderer::new);
	public static final EntityRendererPackage<?> FRAGMENT_SHOT = new EntityRendererPackage<>(AoAProjectiles.FRAGMENT_SHOT).provider(context -> new TexturedProjectileRenderer<>(context, AdventOfAscension.id("textures/entity/projectile/bullets/fragment_shot.png")));
	public static final EntityRendererPackage<?> FUNGAL_BALL = new EntityRendererPackage<>(AoAProjectiles.FUNGAL_BALL).provider(context -> new FungalBallRenderer(context, AdventOfAscension.id("textures/entity/projectile/cannonshots/cannonball.png")));
	public static final EntityRendererPackage<?> GHOUL_BALL = new EntityRendererPackage<>(AoAProjectiles.GHOUL_BALL).provider(context -> new GhoulBallRenderer(context, AdventOfAscension.id("textures/entity/projectile/cannonshots/cannonball.png")));
	public static final EntityRendererPackage<?> GHOUL_SHOT = new EntityRendererPackage<>(AoAProjectiles.GHOUL_SHOT).provider(GhoulShotRenderer::new);
	public static final EntityRendererPackage<?> GIGA_GREEN_BALL = new EntityRendererPackage<>(AoAProjectiles.GIGA_GREEN_BALL).provider(context -> new GreenBallRenderer(context, 5f, AdventOfAscension.id("textures/entity/projectile/cannonshots/green_ball.png")));
	public static final EntityRendererPackage<?> GOLDEN_CANNONBALL = new EntityRendererPackage<>(AoAProjectiles.GOLDEN_CANNONBALL).provider(context -> new GoldenCannonballRenderer(context, AdventOfAscension.id("textures/entity/projectile/cannonshots/cannonball.png")));
	public static final EntityRendererPackage<?> GOLD_SHOT = new EntityRendererPackage<>(AoAProjectiles.GOLD_SHOT).provider(GoldShotRenderer::new);
	public static final EntityRendererPackage<?> GRAW_SHOT = new EntityRendererPackage<>(AoAProjectiles.GRAW_SHOT).provider(GrawShotRenderer::new);
	public static final EntityRendererPackage<?> GREEN_BULLET = new EntityRendererPackage<>(AoAProjectiles.GREEN_BULLET).provider(context -> new ColouredTexturedProjectileRenderer<>(context, ColourUtil.GREEN, AdventOfAscension.id("textures/entity/projectile/bullets/limonite_bullet.png")));
	public static final EntityRendererPackage<?> GREEN_GUARDIAN_SHOT = new EntityRendererPackage<>(AoAProjectiles.GREEN_GUARDIAN_SHOT).provider(GreenGuardianShotRenderer::new);
	public static final EntityRendererPackage<?> HAG_SHOT = new EntityRendererPackage<>(AoAProjectiles.HAG_SHOT).provider(HagShotRenderer::new);
	public static final EntityRendererPackage<?> HAUNTER_SHOT = new EntityRendererPackage<>(AoAProjectiles.HAUNTER_SHOT).provider(HaunterShotRenderer::new);
	public static final EntityRendererPackage<?> HEAVY_BLUE_CANNONBALL = new EntityRendererPackage<>(AoAProjectiles.HEAVY_BLUE_CANNONBALL).provider(context -> new HeavyBlueCannonballRenderer(context, AdventOfAscension.id("textures/entity/projectile/cannonshots/cannonball.png")));
	public static final EntityRendererPackage<?> HEAVY_BONE_CANNONBALL = new EntityRendererPackage<>(AoAProjectiles.HEAVY_BONE_CANNONBALL).provider(context -> new HeavyBoneBallRenderer(context, AdventOfAscension.id("textures/entity/projectile/cannonshots/cannonball.png")));
	public static final EntityRendererPackage<?> HEAVY_CANNONBALL = new EntityRendererPackage<>(AoAProjectiles.HEAVY_CANNONBALL).provider(context -> new TexturedProjectileRenderer<>(context, AdventOfAscension.id("textures/entity/projectile/cannonshots/cannonball.png")));
	public static final EntityRendererPackage<?> HEAVY_GRENADE = new EntityRendererPackage<>(AoAProjectiles.HEAVY_GRENADE).provider(context -> new ModelledProjectileRenderer<>(context, AoAEntityRendering.GRENADE.getMainLayerLocation(), GrenadeModel::new, AdventOfAscension.id("textures/entity/projectile/thrown/grenade.png")));
	public static final EntityRendererPackage<?> HEAVY_RED_BULLET = new EntityRendererPackage<>(AoAProjectiles.HEAVY_RED_BULLET).provider(context -> new ColouredTexturedProjectileRenderer<>(context, ColourUtil.RED, AdventOfAscension.id("textures/entity/projectile/bullets/limonite_bullet.png")));
	public static final EntityRendererPackage<?> HEAVY_RED_CANNONBALL = new EntityRendererPackage<>(AoAProjectiles.HEAVY_RED_CANNONBALL).provider(context -> new HeavyRedCannonballRenderer(context, AdventOfAscension.id("textures/entity/projectile/cannonshots/cannonball.png")));
	public static final EntityRendererPackage<?> HEAVY_RUNIC_GUARDIAN_SHOT = new EntityRendererPackage<>(AoAProjectiles.HEAVY_RUNIC_GUARDIAN_SHOT).provider(RunicGuardianShotHeavyRenderer::new);
	public static final EntityRendererPackage<?> HEAVY_SHADOWBALL = new EntityRendererPackage<>(AoAProjectiles.HEAVY_SHADOWBALL).provider(context -> new HeavyShadowBallRenderer(context, AdventOfAscension.id("textures/entity/projectile/cannonshots/cannonball.png")));
	public static final EntityRendererPackage<?> HEAVY_SHOWER_SHOT = new EntityRendererPackage<>(AoAProjectiles.HEAVY_SHOWER_SHOT).provider(ShowerShotRenderer::new);
	public static final EntityRendererPackage<?> HEAVY_TRI_DISCHARGE_SHOT = new EntityRendererPackage<>(AoAProjectiles.HEAVY_TRI_DISCHARGE_SHOT).provider(context -> new HeavyTriDischargeShotRenderer(context, AdventOfAscension.id("textures/entity/projectile/bullets/discharge_shot.png")));
	public static final EntityRendererPackage<?> HEAVY_WITHER_BALL = new EntityRendererPackage<>(AoAProjectiles.HEAVY_WITHER_BALL).provider(context -> new HeavyWitherBallRenderer(context, AdventOfAscension.id("textures/entity/projectile/cannonshots/cannonball.png")));
	public static final EntityRendererPackage<?> HELLFIRE_TAIL = new EntityRendererPackage<>(AoAProjectiles.HELLFIRE_TAIL).provider(HellfireProjectileRenderer::new);
	public static final EntityRendererPackage<?> HELL_BUBBLE_SHOT = new EntityRendererPackage<>(AoAProjectiles.HELL_BUBBLE_SHOT).provider(context -> new TexturedProjectileRenderer<>(context, AdventOfAscension.id("textures/entity/projectile/misc/red_bubble_shot.png")));
	public static final EntityRendererPackage<?> HIVE_BALL = new EntityRendererPackage<>(AoAProjectiles.HIVE_BALL).provider(context -> new HiveBallRenderer(context, AdventOfAscension.id("textures/entity/projectile/cannonshots/cannonball.png")));
	public static final EntityRendererPackage<?> HOT_SHOT = new EntityRendererPackage<>(AoAProjectiles.HOT_SHOT).provider(context -> new TexturedProjectileRenderer<>(context, AdventOfAscension.id("textures/entity/projectile/bullets/hot_shot.png")));
	public static final EntityRendererPackage<?> ICE_SHOT = new EntityRendererPackage<>(AoAProjectiles.ICE_SHOT).provider(IceShotRenderer::new);
	public static final EntityRendererPackage<?> ILLUSION_SHOT = new EntityRendererPackage<>(AoAProjectiles.ILLUSION_SHOT).provider(context -> new TexturedProjectileRenderer<>(context, AdventOfAscension.id("textures/entity/projectile/bullets/illusion_shot.png")));
	public static final EntityRendererPackage<?> ION_SHOT = new EntityRendererPackage<>(AoAProjectiles.ION_SHOT).provider(IonShotRenderer::new);
	public static final EntityRendererPackage<?> IRO_MINER_SHOT = new EntityRendererPackage<>(AoAProjectiles.IRO_MINER_SHOT).provider(IroMinerShotRenderer::new);
	public static final EntityRendererPackage<?> LELYETIAN_SHOT = new EntityRendererPackage<>(AoAProjectiles.LELYETIAN_SHOT).provider(LelyetianShotRenderer::new);
	public static final EntityRendererPackage<?> LIGHT_BLASTER_SHOT = new EntityRendererPackage<>(AoAProjectiles.LIGHT_BLASTER_SHOT).provider(LightBlasterShotRenderer::new);
	public static final EntityRendererPackage<?> LIGHT_IRON_SHOT = new EntityRendererPackage<>(AoAProjectiles.LIGHT_IRON_SHOT).provider(context -> new LightIronShotRenderer(context, AdventOfAscension.id("textures/entity/projectile/bullets/metal_slug.png")));
	public static final EntityRendererPackage<?> LIGHT_RUNIC_GUARDIAN_SHOT = new EntityRendererPackage<>(AoAProjectiles.LIGHT_RUNIC_GUARDIAN_SHOT).provider(RunicGuardianShotLightRenderer::new);
	public static final EntityRendererPackage<?> LIGHT_SPARK = new EntityRendererPackage<>(AoAProjectiles.LIGHT_SPARK).provider(LightSparkRenderer::new);
	public static final EntityRendererPackage<?> LUNAR_FALL = new EntityRendererPackage<>(AoAProjectiles.LUNAR_FALL).provider(context -> new LunarFallRenderer(context, AdventOfAscension.id("textures/entity/projectile/misc/star_fall.png")));
	public static final EntityRendererPackage<?> LUNA_SHOT = new EntityRendererPackage<>(AoAProjectiles.LUNA_SHOT).provider(context -> new TexturedProjectileRenderer<>(context, AdventOfAscension.id("textures/entity/projectile/bullets/luna_shot.png")));
	public static final EntityRendererPackage<?> LUXON_STICKLER_SHOT = new EntityRendererPackage<>(AoAProjectiles.LUXON_STICKLER_SHOT).provider(LuxonSticklerShotRenderer::new);
	public static final EntityRendererPackage<?> LUXON_STICKLER_STUCK = new EntityRendererPackage<>(AoAProjectiles.LUXON_STICKLER_STUCK).provider(LuxonSticklerStuckRenderer::new);
	public static final EntityRendererPackage<?> LYONIC_SHOT = new EntityRendererPackage<>(AoAProjectiles.LYONIC_SHOT).provider(LyonicShotRenderer::new);
	public static final EntityRendererPackage<?> MAGIC_BALL = new EntityRendererPackage<>(AoAProjectiles.MAGIC_BALL).provider(MagicBallRenderer::new);
	public static final EntityRendererPackage<?> METAL_SLUG = new EntityRendererPackage<>(AoAProjectiles.METAL_SLUG).provider(context -> new TexturedProjectileRenderer<>(context, AdventOfAscension.id("textures/entity/projectile/bullets/metal_slug.png")));
	public static final EntityRendererPackage<?> METEOR_FALL = new EntityRendererPackage<>(AoAProjectiles.METEOR_FALL).provider(MeteorFallRenderer::new);
	public static final EntityRendererPackage<?> MIND_BLASTER_SHOT = new EntityRendererPackage<>(AoAProjectiles.MIND_BLASTER_SHOT).provider(MindBlasterShotRenderer::new);
	public static final EntityRendererPackage<?> MINI_GREEN_BALL = new EntityRendererPackage<>(AoAProjectiles.MINI_GREEN_BALL).provider(context -> new GreenBallRenderer(context, 1f, AdventOfAscension.id("textures/entity/projectile/cannonshots/green_ball.png")));
	public static final EntityRendererPackage<?> MODULO_SHOT = new EntityRendererPackage<>(AoAProjectiles.MODULO_SHOT).provider(context -> new TexturedProjectileRenderer<>(context, AdventOfAscension.id("textures/entity/projectile/bullets/laser_shot.png")));
	public static final EntityRendererPackage<?> MOONLIGHT_FALL = new EntityRendererPackage<>(AoAProjectiles.MOONLIGHT_FALL).provider(MoonlightFallRenderer::new);
	public static final EntityRendererPackage<?> MOON_DESTROYER_SHOT = new EntityRendererPackage<>(AoAProjectiles.MOON_DESTROYER_SHOT).provider(MoonDestroyerShotRenderer::new);
	public static final EntityRendererPackage<?> MOON_MAKER = new EntityRendererPackage<>(AoAProjectiles.MOON_MAKER).provider(context -> new MoonMakerRenderer(context, AdventOfAscension.id("textures/entity/projectile/bullets/metal_slug.png")));
	public static final EntityRendererPackage<?> MOON_SHINER_SHOT = new EntityRendererPackage<>(AoAProjectiles.MOON_SHINER_SHOT).provider(MoonShinerRenderer::new);
	public static final EntityRendererPackage<?> MOON_SHOT = new EntityRendererPackage<>(AoAProjectiles.MOON_SHOT).provider(MoonShotRenderer::new);
	public static final EntityRendererPackage<?> MULTIPLYING_GRENADE = new EntityRendererPackage<>(AoAProjectiles.MULTIPLYING_GRENADE).provider(context -> new ModelledProjectileRenderer<>(context, AoAEntityRendering.GRENADE.getMainLayerLocation(), GrenadeModel::new, AdventOfAscension.id("textures/entity/projectile/thrown/grenade.png")));
	public static final EntityRendererPackage<?> NIGHTMARE_FALL = new EntityRendererPackage<>(AoAProjectiles.NIGHTMARE_FALL).provider(NightmareFallRenderer::new);
	public static final EntityRendererPackage<?> NOXIOUS_SHOT = new EntityRendererPackage<>(AoAProjectiles.NOXIOUS_SHOT).provider(NoxiousShotRenderer::new);
	public static final EntityRendererPackage<?> ODIOUS_SHOT = new EntityRendererPackage<>(AoAProjectiles.ODIOUS_SHOT).provider(OdiousRenderer::new);
	public static final EntityRendererPackage<?> OMNILIGHT_SHOT = new EntityRendererPackage<>(AoAProjectiles.OMNILIGHT_SHOT).provider(OmnilightShotRenderer::new);
	public static final EntityRendererPackage<?> ORANGE_CANNONBALL = new EntityRendererPackage<>(AoAProjectiles.ORANGE_CANNONBALL).provider(context -> new OrangeCannonballRenderer(context, AdventOfAscension.id("textures/entity/projectile/cannonshots/cannonball.png")));
	public static final EntityRendererPackage<?> ORBOCRON_SHOT = new EntityRendererPackage<>(AoAProjectiles.ORBOCRON_SHOT).provider(OrbocronRenderer::new);
	public static final EntityRendererPackage<?> PARALYZER_SHOT = new EntityRendererPackage<>(AoAProjectiles.PARALYZER_SHOT).provider(ParalyzerShotRenderer::new);
	public static final EntityRendererPackage<?> PARTY_POPPER_SHOT = new EntityRendererPackage<>(AoAProjectiles.PARTY_POPPER_SHOT).provider(context -> new TexturedProjectileRenderer<>(context, AdventOfAscension.id("textures/entity/projectile/bullets/party_popper_shot.png")));
	public static final EntityRendererPackage<?> PHANTOM_SHOT = new EntityRendererPackage<>(AoAProjectiles.PHANTOM_SHOT).provider(PhantomShotRenderer::new);
	public static final EntityRendererPackage<?> PLUTON_STICKLER_SHOT = new EntityRendererPackage<>(AoAProjectiles.PLUTON_STICKLER_SHOT).provider(context -> new PlutonSticklerShotRenderer(context, AdventOfAscension.id("textures/entity/projectile/cannonshots/cannonball.png")));
	public static final EntityRendererPackage<?> PLUTON_STICKLER_STUCK = new EntityRendererPackage<>(AoAProjectiles.PLUTON_STICKLER_STUCK).provider(PlutonSticklerStuckRenderer::new);
	public static final EntityRendererPackage<?> POISON_PLUNGER_SHOT = new EntityRendererPackage<>(AoAProjectiles.POISON_PLUNGER_SHOT).provider(PoisonPlungerShotRenderer::new);
	public static final EntityRendererPackage<?> POISON_SHOT = new EntityRendererPackage<>(AoAProjectiles.POISON_SHOT).provider(PoisonShotRenderer::new);
	public static final EntityRendererPackage<?> POLYMORPH_SHOT = new EntityRendererPackage<>(AoAProjectiles.POLYMORPH_SHOT).provider(PolymorphShotRenderer::new);
	public static final EntityRendererPackage<?> POLYTOM_SHOT = new EntityRendererPackage<>(AoAProjectiles.POLYTOM_SHOT).provider(PolytomShotRenderer::new);
	public static final EntityRendererPackage<?> POP_SHOT = new EntityRendererPackage<>(AoAProjectiles.POP_SHOT).provider(context -> new TexturedProjectileRenderer<>(context, AdventOfAscension.id("textures/entity/projectile/arrows/pop_shot.png")));
	public static final EntityRendererPackage<?> POWER_RAY = new EntityRendererPackage<>(AoAProjectiles.POWER_RAY).provider(PowerRayRenderer::new);
	public static final EntityRendererPackage<?> POWER_SHOT = new EntityRendererPackage<>(AoAProjectiles.POWER_SHOT).provider(PowerShotRenderer::new);
	public static final EntityRendererPackage<?> PRIMORDIAL_SHOT = new EntityRendererPackage<>(AoAProjectiles.PRIMORDIAL_SHOT).provider(PrimordialShotRenderer::new);
	public static final EntityRendererPackage<?> PROTON_SHOT = new EntityRendererPackage<>(AoAProjectiles.PROTON_SHOT).provider(context -> new TexturedProjectileRenderer<>(context, AdventOfAscension.id("textures/entity/projectile/bullets/proton_shot.png")));
	public static final EntityRendererPackage<?> RAINBOW_SHOT = new EntityRendererPackage<>(AoAProjectiles.RAINBOW_SHOT).provider(RainbowShotRenderer::new);
	public static final EntityRendererPackage<?> RED_BULLET = new EntityRendererPackage<>(AoAProjectiles.RED_BULLET).provider(context -> new ColouredTexturedProjectileRenderer<>(context, ColourUtil.RED, AdventOfAscension.id("textures/entity/projectile/bullets/limonite_bullet.png")));
	public static final EntityRendererPackage<?> RED_GUARDIAN_SHOT = new EntityRendererPackage<>(AoAProjectiles.RED_GUARDIAN_SHOT).provider(RedGuardianShotRenderer::new);
	public static final EntityRendererPackage<?> REEFER_SHOT = new EntityRendererPackage<>(AoAProjectiles.REEFER_SHOT).provider(ReeferShotRenderer::new);
	public static final EntityRendererPackage<?> REVOLUTION_SHOT = new EntityRendererPackage<>(AoAProjectiles.REVOLUTION_SHOT).provider(RevolutionShotRenderer::new);
	public static final EntityRendererPackage<?> ROCK_FRAGMENT = new EntityRendererPackage<>(AoAProjectiles.ROCK_FRAGMENT).defineLayer("rock_fragment", CobblestoneProjectileModel::createLayerDefinition).provider(context -> new ModelledProjectileRenderer<>(context, AoAEntityRendering.ROCK_FRAGMENT.getMainLayerLocation(), CobblestoneProjectileModel::new, AdventOfAscension.id("textures/entity/projectile/cannonshots/rock_fragment.png")));
	public static final EntityRendererPackage<?> FUNGAL_ROCK_FRAGMENT = new EntityRendererPackage<>(AoAProjectiles.FUNGAL_ROCK_FRAGMENT).provider(context -> new FungalRockFragmentRenderer(context, AdventOfAscension.id("textures/entity/projectile/cannonshots/rock_fragment.png")));

	public static final EntityRendererPackage<?> ROSIDIAN_SHOT = new EntityRendererPackage<>(AoAProjectiles.ROSIDIAN_SHOT).provider(RosidianShotRenderer::new);
	public static final EntityRendererPackage<?> RPG = new EntityRendererPackage<>(AoAProjectiles.RPG).provider(context -> new RPGRenderer(context, AdventOfAscension.id("textures/entity/projectile/thrown/grenade.png")));
	public static final EntityRendererPackage<?> RUNIC_GUARDIAN_SHOT = new EntityRendererPackage<>(AoAProjectiles.RUNIC_GUARDIAN_SHOT).provider(RunicGuardianShotRenderer::new);
	public static final EntityRendererPackage<?> SEAOCRON_SHOT = new EntityRendererPackage<>(AoAProjectiles.SEAOCRON_SHOT).provider(SeaocronRenderer::new);
	public static final EntityRendererPackage<?> SEED_DART = new EntityRendererPackage<>(AoAProjectiles.SEED_DART).provider(context -> new TexturedProjectileRenderer<>(context, AdventOfAscension.id("textures/entity/projectile/bullets/seed_dart.png")));
	public static final EntityRendererPackage<?> SELYAN_STICKLER_SHOT = new EntityRendererPackage<>(AoAProjectiles.SELYAN_STICKLER_SHOT).provider(SelyanSticklerShotRenderer::new);
	public static final EntityRendererPackage<?> SELYAN_STICKLER_STUCK = new EntityRendererPackage<>(AoAProjectiles.SELYAN_STICKLER_STUCK).provider(SelyanSticklerStuckRenderer::new);
	public static final EntityRendererPackage<?> SHOE_SHOT = new EntityRendererPackage<>(AoAProjectiles.SHOE_SHOT).provider(context -> new ShoeShotRenderer(context, AdventOfAscension.id("textures/entity/projectile/bullets/shoe_shot.png")));
	public static final EntityRendererPackage<?> SHOWER_SHOT = new EntityRendererPackage<>(AoAProjectiles.SHOWER_SHOT).provider(ShowerShotRenderer::new);
	public static final EntityRendererPackage<?> SHROOM_BULLET = new EntityRendererPackage<>(AoAProjectiles.SHROOM_BULLET).provider(context -> new ShroomBulletRenderer(context, AdventOfAscension.id("textures/entity/projectile/bullets/limonite_bullet.png")));
	public static final EntityRendererPackage<?> SHYRE_BEAM = new EntityRendererPackage<>(AoAProjectiles.SHYRE_BEAM).provider(ShyreBeamRenderer::new);
	public static final EntityRendererPackage<?> SHYRE_SHOT = new EntityRendererPackage<>(AoAProjectiles.SHYRE_SHOT).provider(ShyreShotRenderer::new);
	public static final EntityRendererPackage<?> SKULLO_SHOT = new EntityRendererPackage<>(AoAProjectiles.SKULLO_SHOT).provider(context -> new TexturedProjectileRenderer<>(context, AdventOfAscension.id("textures/entity/projectile/bullets/skullo_shot.png")));
	public static final EntityRendererPackage<?> SKY_SHOT = new EntityRendererPackage<>(AoAProjectiles.SKY_SHOT).provider(context -> new TexturedProjectileRenderer<>(context, AdventOfAscension.id("textures/entity/projectile/mobshots/sky_shot.png")));
	public static final EntityRendererPackage<?> SMILEY_CANNONBALL = new EntityRendererPackage<>(AoAProjectiles.SMILEY_CANNONBALL).provider(context -> new TexturedProjectileRenderer<>(context, AdventOfAscension.id("textures/entity/projectile/cannonshots/smiley_cannonball.png")));
	public static final EntityRendererPackage<?> SMILE_BLASTER = new EntityRendererPackage<>(AoAProjectiles.SMILE_BLASTER).provider(context -> new SmileBlasterShotRenderer(context, AdventOfAscension.id("textures/entity/projectile/cannonshots/smiley_cannonball.png")));
	public static final EntityRendererPackage<?> SNIPER_SLUG = new EntityRendererPackage<>(AoAProjectiles.SNIPER_SLUG).provider(SniperSlugRenderer::new);
	public static final EntityRendererPackage<?> SOUL_DRAINER_SHOT = new EntityRendererPackage<>(AoAProjectiles.SOUL_DRAINER_SHOT).provider(SoulDrainerRenderer::new);
	public static final EntityRendererPackage<?> SOUL_SPARK = new EntityRendererPackage<>(AoAProjectiles.SOUL_SPARK).provider(SoulSparkRenderer::new);
	public static final EntityRendererPackage<?> SOUL_STORM_SHOT = new EntityRendererPackage<>(AoAProjectiles.SOUL_STORM_SHOT).provider(SoulStormRenderer::new);
	public static final EntityRendererPackage<?> SPECTRAL_SHOT = new EntityRendererPackage<>(AoAProjectiles.SPECTRAL_SHOT).provider(SpectralShotRenderer::new);
	public static final EntityRendererPackage<?> SPIRITUAL_SHOT = new EntityRendererPackage<>(AoAProjectiles.SPIRITUAL_SHOT).provider(SpiritualShotRenderer::new);
	public static final EntityRendererPackage<?> STICKY_COOL_BOMB = new EntityRendererPackage<>(AoAProjectiles.STICKY_COOL_BOMB).provider(context -> new StickyCoolBombRenderer(context, AdventOfAscension.id("textures/entity/projectile/cannonshots/cannonball.png")));
	public static final EntityRendererPackage<?> STICKY_RED_BOMB = new EntityRendererPackage<>(AoAProjectiles.STICKY_RED_BOMB).provider(context -> new StickyRedBombRenderer(context, AdventOfAscension.id("textures/entity/projectile/cannonshots/cannonball.png")));
	public static final EntityRendererPackage<?> SUNSET_BULLET = new EntityRendererPackage<>(AoAProjectiles.SUNSET_BULLET).provider(context -> new SunsetBulletRenderer(context, AdventOfAscension.id("textures/entity/projectile/bullets/limonite_bullet.png")));
	public static final EntityRendererPackage<?> SUN_SHOT = new EntityRendererPackage<>(AoAProjectiles.SUN_SHOT).provider(SunShotRenderer::new);
	public static final EntityRendererPackage<?> SUPER_GREEN_BALL = new EntityRendererPackage<>(AoAProjectiles.SUPER_GREEN_BALL).provider(context -> new GreenBallRenderer(context, 2f, AdventOfAscension.id("textures/entity/projectile/cannonshots/green_ball.png")));
	public static final EntityRendererPackage<?> SWARM_SHOT = new EntityRendererPackage<>(AoAProjectiles.SWARM_SHOT).provider(SwarmShotRenderer::new);
	public static final EntityRendererPackage<?> TANGLE_FALL = new EntityRendererPackage<>(AoAProjectiles.TANGLE_FALL).provider(TangleFallRenderer::new);
	public static final EntityRendererPackage<?> TERROR_CONSTRUCT_SHOT = new EntityRendererPackage<>(AoAProjectiles.TERROR_CONSTRUCT_SHOT).provider(context -> new TexturedProjectileRenderer<>(context, AdventOfAscension.id("textures/entity/projectile/mobshots/construct_terror_shot.png")));
	public static final EntityRendererPackage<?> TIDAL_WAVE = new EntityRendererPackage<>(AoAProjectiles.TIDAL_WAVE).provider(TidalWaveRenderer::new);
	public static final EntityRendererPackage<?> TOXIC_BULLET = new EntityRendererPackage<>(AoAProjectiles.TOXIC_BULLET).provider(context -> new ToxicBulletRenderer(context, AdventOfAscension.id("textures/entity/projectile/bullets/limonite_bullet.png")));
	public static final EntityRendererPackage<?> TOXIC_SHOT = new EntityRendererPackage<>(AoAProjectiles.TOXIC_SHOT).provider(ToxicShotRenderer::new);
	public static final EntityRendererPackage<?> TRI_DISCHARGE_SHOT = new EntityRendererPackage<>(AoAProjectiles.TRI_DISCHARGE_SHOT).provider(context -> new TriDischargeShotRenderer(context, AdventOfAscension.id("textures/entity/projectile/bullets/discharge_shot.png")));
	public static final EntityRendererPackage<?> ULTIMATUM_SHOT = new EntityRendererPackage<>(AoAProjectiles.ULTIMATUM_SHOT).provider(UltimatumShotRenderer::new);
	public static final EntityRendererPackage<?> ULTRA_GREEN_BALL = new EntityRendererPackage<>(AoAProjectiles.ULTRA_GREEN_BALL).provider(context -> new GreenBallRenderer(context, 3f, AdventOfAscension.id("textures/entity/projectile/cannonshots/green_ball.png")));
	public static final EntityRendererPackage<?> VALKYRIE_SHOT = new EntityRendererPackage<>(AoAProjectiles.VALKYRIE_SHOT).provider(ValkyrieShotRenderer::new);
	public static final EntityRendererPackage<?> VINE_WIZARD_SHOT = new EntityRendererPackage<>(AoAProjectiles.VINE_WIZARD_SHOT).provider(VineWizardShotRenderer::new);
	public static final EntityRendererPackage<?> VOLATILE_CANNONBALL = new EntityRendererPackage<>(AoAProjectiles.VOLATILE_CANNONBALL).provider(context -> new TexturedProjectileRenderer<>(context, AdventOfAscension.id("textures/entity/projectile/cannonshots/cannonball.png")));
	public static final EntityRendererPackage<?> VORTEX_BLAST = new EntityRendererPackage<>(AoAProjectiles.VORTEX_BLAST).provider(VortexBlastRenderer::new);
	public static final EntityRendererPackage<?> VOX_CANNON = new EntityRendererPackage<>(AoAProjectiles.VOX_CANNON).provider(context -> new VoxCannonShotRenderer(context, AdventOfAscension.id("textures/entity/projectile/cannonshots/cannonball.png")));
	public static final EntityRendererPackage<?> WART_DART = new EntityRendererPackage<>(AoAProjectiles.WART_DART).provider(context -> new TexturedProjectileRenderer<>(context, AdventOfAscension.id("textures/entity/projectile/bullets/wart_dart.png")));
	public static final EntityRendererPackage<?> WATER_BALLOON_BOMB = new EntityRendererPackage<>(AoAProjectiles.WATER_BALLOON_BOMB).provider(context -> new WaterBalloonBombRenderer(context, AdventOfAscension.id("textures/entity/projectile/cannonshots/balloon_bomb.png")));
	public static final EntityRendererPackage<?> WATER_SHOT = new EntityRendererPackage<>(AoAProjectiles.WATER_SHOT).provider(WaterShotRenderer::new);
	public static final EntityRendererPackage<?> WEIGHTED_SHOWER_SHOT = new EntityRendererPackage<>(AoAProjectiles.WEIGHTED_SHOWER_SHOT).provider(ShowerShotRenderer::new);
	public static final EntityRendererPackage<?> WHITE_BALL = new EntityRendererPackage<>(AoAProjectiles.WHITE_BALL).provider(WhiteBallRenderer::new);
	public static final EntityRendererPackage<?> WINDER_SHOT = new EntityRendererPackage<>(AoAProjectiles.WINDER_SHOT).provider(WinderShotRenderer::new);
	public static final EntityRendererPackage<?> WITHER_BALL = new EntityRendererPackage<>(AoAProjectiles.WITHER_BALL).provider(WitherBallRenderer::new);
	public static final EntityRendererPackage<?> WITHER_SHOT = new EntityRendererPackage<>(AoAProjectiles.WITHER_SHOT).provider(WitherShotRenderer::new);
	public static final EntityRendererPackage<?> WRATH_SHOT = new EntityRendererPackage<>(AoAProjectiles.WRATH_SHOT).provider(WrathShotRenderer::new);
	public static final EntityRendererPackage<?> YELLOW_BULLET = new EntityRendererPackage<>(AoAProjectiles.YELLOW_BULLET).provider(context -> new ColouredTexturedProjectileRenderer<>(context, ColourUtil.YELLOW, AdventOfAscension.id("textures/entity/projectile/bullets/limonite_bullet.png")));
	public static final EntityRendererPackage<?> YELLOW_GUARDIAN_SHOT = new EntityRendererPackage<>(AoAProjectiles.YELLOW_GUARDIAN_SHOT).provider(YellowGuardianShotRenderer::new);

	public static final EntityRendererPackage<?> STONE_GIANT_ROCK = new GeckoLibRendererPackage<>(AoAProjectiles.STONE_GIANT_ROCK).path("projectile/mob/stone_giant_rock").projectile();
	public static final EntityRendererPackage<?> TREE_SPIRIT_SPRITE = new GeckoLibRendererPackage<>(AoAProjectiles.TREE_SPIRIT_SPRITE).path("projectile/mob/tree_spirit_sprite").projectile().emissive();
	public static final EntityRendererPackage<?> FIREBALL = new GeckoLibRendererPackage<>(AoAProjectiles.FIREBALL).path("projectile/mob/fireball").nonLiving().emissive();
	public static final EntityRendererPackage<?> STICKY_FIREBALL = new GeckoLibRendererPackage<>(AoAProjectiles.STICKY_FIREBALL).path("projectile/mob/fireball").nonLiving().emissive();

	// Begin super jank test
	public static final EntityRendererPackage<?> AIRHEAD = new EntityRendererPackage<>(AoAMobs.AIRHEAD).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> ALARMO = new EntityRendererPackage<>(AoAMobs.ALARMO).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> AMPHIBIOR = new EntityRendererPackage<>(AoAMobs.AMPHIBIOR).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> AMPHIBIYTE = new EntityRendererPackage<>(AoAMobs.AMPHIBIYTE).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> ANEMIA = new EntityRendererPackage<>(AoAMobs.ANEMIA).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> APPARITION = new EntityRendererPackage<>(AoAMobs.APPARITION).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> ARC_FLOWER = new EntityRendererPackage<>(AoAMobs.ARC_FLOWER).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> ARC_WIZARD = new EntityRendererPackage<>(AoAMobs.ARC_WIZARD).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> ARCHVINE = new EntityRendererPackage<>(AoAMobs.ARCHVINE).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> ARCWORM = new EntityRendererPackage<>(AoAMobs.ARCWORM).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> ARIEL = new EntityRendererPackage<>(AoAMobs.ARIEL).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> ARKBACK = new EntityRendererPackage<>(AoAMobs.ARKBACK).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> ARKZYNE = new EntityRendererPackage<>(AoAMobs.ARKZYNE).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> AXIOLIGHT = new EntityRendererPackage<>(AoAMobs.AXIOLIGHT).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> BANSHEE = new EntityRendererPackage<>(AoAMobs.BANSHEE).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> BASILISK = new EntityRendererPackage<>(AoAMobs.BASILISK).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> BAUMBA = new EntityRendererPackage<>(AoAMobs.BAUMBA).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> BLOODSUCKER = new EntityRendererPackage<>(AoAMobs.BLOODSUCKER).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> BOBO = new EntityRendererPackage<>(AoAMobs.BOBO).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> BONE_CREEPER = new EntityRendererPackage<>(AoAMobs.BONE_CREEPER).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> BOUNCER = new EntityRendererPackage<>(AoAMobs.BOUNCER).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> BROCCOHEAD = new EntityRendererPackage<>(AoAMobs.BROCCOHEAD).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> CANDY_CORNY = new EntityRendererPackage<>(AoAMobs.CANDY_CORNY).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> CANE_BUG = new EntityRendererPackage<>(AoAMobs.CANE_BUG).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> CARROTOP = new EntityRendererPackage<>(AoAMobs.CARROTOP).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> CAVE_CREEPOID = new EntityRendererPackage<>(AoAMobs.CAVE_CREEPOID).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> CENTINEL = new EntityRendererPackage<>(AoAMobs.CENTINEL).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> CHERRY_BLASTER = new EntityRendererPackage<>(AoAMobs.CHERRY_BLASTER).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> CHOCKO = new EntityRendererPackage<>(AoAMobs.CHOCKO).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> CONSTRUCT_OF_FLIGHT = new EntityRendererPackage<>(AoAMobs.CONSTRUCT_OF_FLIGHT).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> CONSTRUCT_OF_MIND = new EntityRendererPackage<>(AoAMobs.CONSTRUCT_OF_MIND).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> CONSTRUCT_OF_RANGE = new EntityRendererPackage<>(AoAMobs.CONSTRUCT_OF_RANGE).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> CONSTRUCT_OF_RESISTANCE = new EntityRendererPackage<>(AoAMobs.CONSTRUCT_OF_RESISTANCE).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> CONSTRUCT_OF_SPEED = new EntityRendererPackage<>(AoAMobs.CONSTRUCT_OF_SPEED).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> CONSTRUCT_OF_STRENGTH = new EntityRendererPackage<>(AoAMobs.CONSTRUCT_OF_STRENGTH).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> CONSTRUCT_OF_TERROR = new EntityRendererPackage<>(AoAMobs.CONSTRUCT_OF_TERROR).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> CORNY = new EntityRendererPackage<>(AoAMobs.CORNY).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> CREEPERLOCK = new EntityRendererPackage<>(AoAMobs.CREEPERLOCK).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> CREEPIRD = new EntityRendererPackage<>(AoAMobs.CREEPIRD).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> CREEPUPLE = new EntityRendererPackage<>(AoAMobs.CREEPUPLE).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> CRUSILISK = new EntityRendererPackage<>(AoAMobs.CRUSILISK).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> CRYPTID = new EntityRendererPackage<>(AoAMobs.CRYPTID).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> DAYSEE = new EntityRendererPackage<>(AoAMobs.DAYSEE).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> DESTRUCTOR = new EntityRendererPackage<>(AoAMobs.DESTRUCTOR).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> DEVOURER = new EntityRendererPackage<>(AoAMobs.DEVOURER).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> DISTORTER = new EntityRendererPackage<>(AoAMobs.DISTORTER).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	//public static final EntityRendererPackage<?> DOPPELGANGER = new EntityRendererPackage<>(AoAMobs.DOPPELGANGER).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> DUST_STRIDER = new EntityRendererPackage<>(AoAMobs.DUST_STRIDER).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> DUSTEIVA = new EntityRendererPackage<>(AoAMobs.DUSTEIVA).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> DUSTON = new EntityRendererPackage<>(AoAMobs.DUSTON).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> DWELLER = new EntityRendererPackage<>(AoAMobs.DWELLER).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> ECHODAR = new EntityRendererPackage<>(AoAMobs.ECHODAR).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> EMPEROR_BEAST = new EntityRendererPackage<>(AoAMobs.EMPEROR_BEAST).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> ENFORCER = new EntityRendererPackage<>(AoAMobs.ENFORCER).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> EXOHEAD = new EntityRendererPackage<>(AoAMobs.EXOHEAD).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> EXPLODOT = new EntityRendererPackage<>(AoAMobs.EXPLODOT).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> FACELESS_FLOATER = new EntityRendererPackage<>(AoAMobs.FACELESS_FLOATER).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> FAKE_ZORP = new EntityRendererPackage<>(AoAMobs.FAKE_ZORP).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> FIEND = new EntityRendererPackage<>(AoAMobs.FIEND).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> FISCHER = new EntityRendererPackage<>(AoAMobs.FISCHER).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> FLESH_EATER = new EntityRendererPackage<>(AoAMobs.FLESH_EATER).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> FLOWERFACE = new EntityRendererPackage<>(AoAMobs.FLOWERFACE).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> FUNGAT = new EntityRendererPackage<>(AoAMobs.FUNGAT).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> FUNGBACK = new EntityRendererPackage<>(AoAMobs.FUNGBACK).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> FUNGIK = new EntityRendererPackage<>(AoAMobs.FUNGIK).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> FUNGOCK = new EntityRendererPackage<>(AoAMobs.FUNGOCK).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> FUNGUNG = new EntityRendererPackage<>(AoAMobs.FUNGUNG).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> GADGETOID = new EntityRendererPackage<>(AoAMobs.GADGETOID).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> GINGERBIRD = new EntityRendererPackage<>(AoAMobs.GINGERBIRD).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> GINGERBREAD_MAN = new EntityRendererPackage<>(AoAMobs.GINGERBREAD_MAN).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> GRILLFACE = new EntityRendererPackage<>(AoAMobs.GRILLFACE).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> GROBBLER = new EntityRendererPackage<>(AoAMobs.GROBBLER).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> HAPPY = new EntityRendererPackage<>(AoAMobs.HAPPY).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> HOST = new EntityRendererPackage<>(AoAMobs.HOST).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> HUNTER = new EntityRendererPackage<>(AoAMobs.HUNTER).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> INMATE_X = new EntityRendererPackage<>(AoAMobs.INMATE_X).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> INMATE_Y = new EntityRendererPackage<>(AoAMobs.INMATE_Y).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> JAWE = new EntityRendererPackage<>(AoAMobs.JAWE).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> JUMBO = new EntityRendererPackage<>(AoAMobs.JUMBO).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> KEELER = new EntityRendererPackage<>(AoAMobs.KEELER).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> KING_CREEPER = new EntityRendererPackage<>(AoAMobs.KING_CREEPER).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> KOKO = new EntityRendererPackage<>(AoAMobs.KOKO).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> KRANKY = new EntityRendererPackage<>(AoAMobs.KRANKY).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> LELYETIAN_CASTER = new EntityRendererPackage<>(AoAMobs.LELYETIAN_CASTER).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> LELYETIAN_WARRIOR = new EntityRendererPackage<>(AoAMobs.LELYETIAN_WARRIOR).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> LIGHTWALKER = new EntityRendererPackage<>(AoAMobs.LIGHTWALKER).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> LOLLYPOPPER = new EntityRendererPackage<>(AoAMobs.LOLLYPOPPER).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> LOST_SOUL = new EntityRendererPackage<>(AoAMobs.LOST_SOUL).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> LUNARCHER = new EntityRendererPackage<>(AoAMobs.LUNARCHER).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> LURKER = new EntityRendererPackage<>(AoAMobs.LURKER).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> LUXOCRON = new EntityRendererPackage<>(AoAMobs.LUXOCRON).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> MAGICAL_CREEPER = new EntityRendererPackage<>(AoAMobs.MAGICAL_CREEPER).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> MECHACHRON = new EntityRendererPackage<>(AoAMobs.MECHACHRON).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> MECHAMATON = new EntityRendererPackage<>(AoAMobs.MECHAMATON).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> MECHYON = new EntityRendererPackage<>(AoAMobs.MECHYON).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> MERKYRE = new EntityRendererPackage<>(AoAMobs.MERKYRE).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> MERMAGE = new EntityRendererPackage<>(AoAMobs.MERMAGE).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> MODULO = new EntityRendererPackage<>(AoAMobs.MODULO).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> MUSHROOM_SPIDER = new EntityRendererPackage<>(AoAMobs.MUSHROOM_SPIDER).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> NIGHTMARE_SPIDER = new EntityRendererPackage<>(AoAMobs.NIGHTMARE_SPIDER).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> NIGHTWING = new EntityRendererPackage<>(AoAMobs.NIGHTWING).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> NOSPIKE = new EntityRendererPackage<>(AoAMobs.NOSPIKE).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> OCCULENT = new EntityRendererPackage<>(AoAMobs.OCCULENT).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> PALADIN = new EntityRendererPackage<>(AoAMobs.PALADIN).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> PARASECT = new EntityRendererPackage<>(AoAMobs.PARASECT).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> PARAVITE = new EntityRendererPackage<>(AoAMobs.PARAVITE).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> POD_PLANT = new EntityRendererPackage<>(AoAMobs.POD_PLANT).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> POLYTOM = new EntityRendererPackage<>(AoAMobs.POLYTOM).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> RAMRADON = new EntityRendererPackage<>(AoAMobs.RAMRADON).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> RAWBONE = new EntityRendererPackage<>(AoAMobs.RAWBONE).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> REFLUCT = new EntityRendererPackage<>(AoAMobs.REFLUCT).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> RUNIC_GOLEM = new EntityRendererPackage<>(AoAMobs.RUNIC_GOLEM).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> RUNIC_GUARDIAN = new EntityRendererPackage<>(AoAMobs.RUNIC_GUARDIAN).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> RUNICORN = new EntityRendererPackage<>(AoAMobs.RUNICORN).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> RUNICORN_RIDER = new EntityRendererPackage<>(AoAMobs.RUNICORN_RIDER).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> SHIFTER = new EntityRendererPackage<>(AoAMobs.SHIFTER).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> SHYRE_KNIGHT = new EntityRendererPackage<>(AoAMobs.SHYRE_KNIGHT).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> SILENCER = new EntityRendererPackage<>(AoAMobs.SILENCER).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> SKULL_CREATURE = new EntityRendererPackage<>(AoAMobs.SKULL_CREATURE).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> SLIMER = new EntityRendererPackage<>(AoAMobs.SLIMER).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> SNAPPY = new EntityRendererPackage<>(AoAMobs.SNAPPY).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> SOULSCORNE = new EntityRendererPackage<>(AoAMobs.SOULSCORNE).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> SOULVYRE = new EntityRendererPackage<>(AoAMobs.SOULVYRE).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> SPECTRAL_WIZARD = new EntityRendererPackage<>(AoAMobs.SPECTRAL_WIZARD).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> SPIRIT_GUARDIAN = new EntityRendererPackage<>(AoAMobs.SPIRIT_GUARDIAN).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> SPIRIT_PROTECTOR = new EntityRendererPackage<>(AoAMobs.SPIRIT_PROTECTOR).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> SQUASHER = new EntityRendererPackage<>(AoAMobs.SQUASHER).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> SQUIGGLER = new EntityRendererPackage<>(AoAMobs.SQUIGGLER).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> STALKER = new EntityRendererPackage<>(AoAMobs.STALKER).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> STICKY = new EntityRendererPackage<>(AoAMobs.STICKY).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> STIMULO = new EntityRendererPackage<>(AoAMobs.STIMULO).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> STIMULOSUS = new EntityRendererPackage<>(AoAMobs.STIMULOSUS).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> STITCHES = new EntityRendererPackage<>(AoAMobs.STITCHES).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> SUGARFACE = new EntityRendererPackage<>(AoAMobs.SUGARFACE).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> SUNNY = new EntityRendererPackage<>(AoAMobs.SUNNY).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> TERRESTRIAL = new EntityRendererPackage<>(AoAMobs.TERRESTRIAL).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> THARAFLY = new EntityRendererPackage<>(AoAMobs.THARAFLY).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> TIPSY = new EntityRendererPackage<>(AoAMobs.TIPSY).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> TOXXULOUS = new EntityRendererPackage<>(AoAMobs.TOXXULOUS).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> VALKYRIE = new EntityRendererPackage<>(AoAMobs.VALKYRIE).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> VINE_WIZARD = new EntityRendererPackage<>(AoAMobs.VINE_WIZARD).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> VOLTRON = new EntityRendererPackage<>(AoAMobs.VOLTRON).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> WEB_REAPER = new EntityRendererPackage<>(AoAMobs.WEB_REAPER).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> WINGED_CREEPER = new EntityRendererPackage<>(AoAMobs.WINGED_CREEPER).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> ZARG = new EntityRendererPackage<>(AoAMobs.ZARG).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> ZHINX = new EntityRendererPackage<>(AoAMobs.ZHINX).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> ZORP = new EntityRendererPackage<>(AoAMobs.ZORP).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);

	public static final EntityRendererPackage<?> GORB_ARMS_DEALER = new EntityRendererPackage<>(AoANpcs.GORB_ARMS_DEALER).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> GORB_CITIZEN = new EntityRendererPackage<>(AoANpcs.GORB_CITIZEN).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> GORB_ENGINEER = new EntityRendererPackage<>(AoANpcs.GORB_ENGINEER).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> LELYETIAN_BANKER = new EntityRendererPackage<>(AoANpcs.LELYETIAN_BANKER).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> LELYETIAN_TRADER = new EntityRendererPackage<>(AoANpcs.LELYETIAN_TRADER).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> PRIMORDIAL_BANKER = new EntityRendererPackage<>(AoANpcs.PRIMORDIAL_BANKER).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> PRIMORDIAL_GUIDE = new EntityRendererPackage<>(AoANpcs.PRIMORDIAL_GUIDE).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> PRIMORDIAL_MERCHANT = new EntityRendererPackage<>(AoANpcs.PRIMORDIAL_BANKER).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> PRIMORDIAL_SPELLBINDER = new EntityRendererPackage<>(AoANpcs.PRIMORDIAL_BANKER).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> PRIMORDIAL_WIZARD = new EntityRendererPackage<>(AoANpcs.PRIMORDIAL_BANKER).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> PROFESSOR = new EntityRendererPackage<>(AoANpcs.PRIMORDIAL_BANKER).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> TOY_MERCHANT = new EntityRendererPackage<>(AoANpcs.PRIMORDIAL_BANKER).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> ZAL_BANKER = new EntityRendererPackage<>(AoANpcs.ZAL_BANKER).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> ZAL_CHILD = new EntityRendererPackage<>(AoANpcs.ZAL_CHILD).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> ZAL_CITIZEN = new EntityRendererPackage<>(AoANpcs.ZAL_CITIZEN).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> ZAL_GROCER = new EntityRendererPackage<>(AoANpcs.ZAL_GROCER).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> ZAL_HERBALIST = new EntityRendererPackage<>(AoANpcs.ZAL_HERBALIST).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> ZAL_SPELLBINDER = new EntityRendererPackage<>(AoANpcs.ZAL_SPELLBINDER).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> ZAL_VENDOR = new EntityRendererPackage<>(AoANpcs.ZAL_VENDOR).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);

	public static final EntityRendererPackage<?> CREEP_COW = new EntityRendererPackage<>(AoAAnimals.CREEP_COW).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> HALYCON = new EntityRendererPackage<>(AoAAnimals.HALYCON).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);

	public static final EntityRendererPackage<?> ANGELICA = new EntityRendererPackage<>(AoAAnimals.ANGELICA).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> DAWNLIGHT = new EntityRendererPackage<>(AoAAnimals.DAWNLIGHT).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> EEO = new EntityRendererPackage<>(AoAAnimals.EEO).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> NIGHT_WATCHER = new EntityRendererPackage<>(AoAAnimals.NIGHT_WATCHER).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> PEPPERMINT_SNAIL = new EntityRendererPackage<>(AoAAnimals.PEPPERMINT_SNAIL).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> RAINICORN = new EntityRendererPackage<>(AoAAnimals.RAINICORN).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> SPEARMINT_SNAIL = new EntityRendererPackage<>(AoAAnimals.SPEARMINT_SNAIL).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> TROTTER = new EntityRendererPackage<>(AoAAnimals.TROTTER).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> URKA = new EntityRendererPackage<>(AoAAnimals.URKA).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> VOLIANT = new EntityRendererPackage<>(AoAAnimals.VOLIANT).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);
	public static final EntityRendererPackage<?> SHIK = new EntityRendererPackage<>(AoAAnimals.SHIK).provider(JankyJankTempRendererToPreventCrashesWhileInDev::new);

	// End super jank test

	public static void init() {
		AdventOfAscension.getModEventBus().addListener(EventPriority.NORMAL, false, EntityRenderersEvent.RegisterRenderers.class, AoAEntityRendering::registerEntityRenderers);
		AdventOfAscension.getModEventBus().addListener(EventPriority.NORMAL, false, EntityRenderersEvent.RegisterLayerDefinitions.class, AoAEntityRendering::registerLayerDefinitions);
		AdventOfAscension.getModEventBus().addListener(EventPriority.NORMAL, false, EntityRenderersEvent.AddLayers.class, AoAEntityRendering::onRenderLayerRegistration);
	}

	private static void registerEntityRenderers(final EntityRenderersEvent.RegisterRenderers ev) {
		for (EntityRendererPackage<Entity> rendererPackage : RENDERER_PACKAGES) {
			ev.registerEntityRenderer(rendererPackage.entityType.get(), rendererPackage.build());
		}

		RENDERER_PACKAGES = null;

		ev.registerBlockEntityRenderer(AoABlockEntities.TROPHY.get(), TrophyRenderer::new);
		ev.registerBlockEntityRenderer(AoABlockEntities.LUNAR_CREATION_TABLE.get(), LunarCreationTableRenderer::new);
		ev.registerBlockEntityRenderer(AoABlockEntities.INFUSION_TABLE.get(), InfusionTableRenderer::new);
		ev.registerBlockEntityRenderer(AoABlockEntities.IMBUING_CHAMBER.get(), ImbuingChamberRenderer::new);
		ev.registerBlockEntityRenderer(AoABlockEntities.BOSS_ALTAR.get(), BossAltarRenderer::new);
	}

	private static void registerLayerDefinitions(final EntityRenderersEvent.RegisterLayerDefinitions ev) {
		for (EntityRendererPackage<?> rendererPackage : RENDERER_PACKAGES) {
			rendererPackage.registerModelLayer(ev);
		}

		AoAMiscModels.init(ev);
	}

	private static Supplier<LayerDefinition> humanoidLayerDefinition() {
		return () -> LayerDefinition.create(HumanoidModel.createMesh(CubeDeformation.NONE, 0.0F), 64, 64);
	}

	private static void onRenderLayerRegistration(final EntityRenderersEvent.AddLayers ev) {
		for (PlayerSkin.Model skin : ev.getSkins()) {
			LivingEntityRenderer<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> renderer = ev.getSkin(skin);

			renderer.addLayer(new PlayerHaloRenderLayer(renderer, ev.getEntityModels()));
		}
	}

	private static class GeckoLibRendererPackage<T extends Entity & GeoEntity> extends EntityRendererPackage<T> {
		private GeoModel<T> model = null;
		private boolean emissive = false;
		private boolean transparent = false;
		private Type type = Type.LIVING;
		private EntityRendererProvider<T> provider;

		private GeckoLibRendererPackage(DeferredHolder<EntityType<?>, EntityType<T>> entityType) {
			super(entityType);
		}

		private GeckoLibRendererPackage<T> path(String path) {
			return path(path, false);
		}

		private GeckoLibRendererPackage<T> path(String path, boolean turnsHead) {
			return model(new DefaultedEntityGeoModel<>(AdventOfAscension.id(path), turnsHead));
		}

		private GeckoLibRendererPackage<T> model(GeoModel<T> model) {
			this.model = model;

			return this;
		}

		private GeckoLibRendererPackage<T> renderer(EntityRendererProvider<T> provider) {
			this.provider = provider;

			return this;
		}

		@Override
		protected GeckoLibRendererPackage<T> shadowSize(float shadow) {
			super.shadowSize(shadow);

			return this;
		}

		private GeckoLibRendererPackage<T> emissive() {
			this.emissive = true;

			return this;
		}

		private GeckoLibRendererPackage<T> transparent() {
			this.transparent = true;

			return this;
		}

		private GeckoLibRendererPackage<T> projectile() {
			this.type = Type.PROJECTILE;

			return this;
		}

		private GeckoLibRendererPackage<T> nonLiving() {
			this.type = Type.NON_LIVING;

			return this;
		}

		private static <E extends LivingEntity & GeoEntity> GeoEntityRenderer<E> buildLivingRenderer(EntityRendererProvider.Context context, GeoModel<E> model, float shadowSize, boolean transparent) {
			if (transparent) {
				return new AnimatedMobRenderer<E>(context, model, shadowSize) {
					@Override
					public RenderType getRenderType(E animatable, ResourceLocation texture, @org.jetbrains.annotations.Nullable MultiBufferSource bufferSource, float partialTick) {
						return RenderType.entityTranslucent(texture);
					}
				};
			}
			else {
				return new AnimatedMobRenderer<E>(context, model, shadowSize);
			}
		}

		@Override
		protected EntityRendererProvider<T> build() {
			if (this.model == null && this.provider == null)
				throw new IllegalStateException("No provided model or renderer for GeckoLib model for " + this.entityType.getId().toString());

			if (this.shadowSize == -1)
				this.shadowSize = this.entityType.get().getWidth() / 3f;

			if (this.provider != null)
				return this.provider;

			return context -> {
				GeoEntityRenderer renderer = switch (this.type) {
					case LIVING -> buildLivingRenderer(context, (GeoModel)this.model, this.shadowSize, this.transparent);
					case PROJECTILE -> {
						if (this.transparent) {
							yield new AnimatedProjectileRenderer<>(context, this.model) {
								@Override
								public RenderType getRenderType(T animatable, ResourceLocation texture, @org.jetbrains.annotations.Nullable MultiBufferSource bufferSource, float partialTick) {
									return RenderType.entityTranslucent(texture);
								}
							};
						}
						else {
							yield new AnimatedProjectileRenderer<>(context, this.model);
						}
					}
					case NON_LIVING -> {
						if (this.transparent) {
							yield new GeoEntityRenderer<>(context, this.model) {
								@Override
								public RenderType getRenderType(T animatable, ResourceLocation texture, @org.jetbrains.annotations.Nullable MultiBufferSource bufferSource, float partialTick) {
									return RenderType.entityTranslucent(texture);
								}
							};
						}
						else {
							yield new GeoEntityRenderer<>(context, this.model);
						}
					}
				};

				if (this.emissive)
					renderer.addRenderLayer(new AutoGlowingGeoLayer(renderer));

				return renderer;
			};
		}

		private enum Type {
			LIVING,
			PROJECTILE,
			NON_LIVING
		}
	}

	public static class EntityRendererPackage<T extends Entity> {
		protected final DeferredHolder<EntityType<?>, EntityType<T>> entityType;
		protected final HashMap<String, Pair<ModelLayerLocation, Supplier<LayerDefinition>>> layerDefinitions = new HashMap<>();

		protected EntityRendererProvider<T> rendererProvider = null;
		protected float shadowSize = -1;

		private EntityRendererPackage(DeferredHolder<EntityType<?>, EntityType<T>> entityType) {
			this.entityType = entityType;

			RENDERER_PACKAGES.add(this);
		}

		protected EntityRendererPackage<T> shadowSize(float shadow) {
			this.shadowSize = shadow;

			return this;
		}

		private EntityRendererPackage<T> defineLayer(String path, Supplier<LayerDefinition> definition) {
			return defineLayer(path, "main", definition);
		}

		private EntityRendererPackage<T> defineLayer(String path, String layerName, Supplier<LayerDefinition> definition) {
			this.layerDefinitions.put(layerName, Pair.of(new ModelLayerLocation(AdventOfAscension.id(path), layerName), definition));

			return this;
		}

		private EntityRendererPackage<T> provider(EntityRendererProvider provider) {
			this.rendererProvider = provider;

			return this;
		}

		private EntityRendererPackage<T> defaultMobRenderer(Function<ModelPart, EntityModel<? extends Mob>> modelFunction, String texturePath) {
			return defaultMobRenderer(modelFunction, texturePath, 1f);
		}

		private EntityRendererPackage<T> defaultMobRenderer(Function<ModelPart, EntityModel<? extends Mob>> model, String texturePath, float scale) {
			return provider(context -> new AoAMobRenderer(context, model.apply(context.bakeLayer(this.layerDefinitions.get("main").getFirst())), this.shadowSize, scale, AdventOfAscension.id(texturePath)));
		}

		private void registerModelLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
			for (Pair<ModelLayerLocation, Supplier<LayerDefinition>> layer : this.layerDefinitions.values()) {
				event.registerLayerDefinition(layer.getFirst(), layer.getSecond());
			}
		}

		public ModelLayerLocation getMainLayerLocation() {
			return getLayerLocation("main");
		}

		@Nullable
		public ModelLayerLocation getLayerLocation(String layerName) {
			return this.layerDefinitions.get(layerName).getFirst();
		}

		protected EntityRendererProvider<T> build() {
			if (this.rendererProvider == null)
				throw new IllegalStateException("No registered renderer provider for entity: " + this.entityType.getId());

			if (this.shadowSize == -1)
				this.shadowSize = this.entityType.get().getWidth() / 3f;

			return this.rendererProvider;
		}
	}
}
