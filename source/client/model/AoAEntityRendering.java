package net.tslat.aoa3.client.model;

import com.mojang.datafixers.util.Pair;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.TippableArrowRenderer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.registries.RegistryObject;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.client.model.entity.EntityGeoModel;
import net.tslat.aoa3.client.model.entity.animal.CorateeModel;
import net.tslat.aoa3.client.model.entity.animal.ShinySquidModel;
import net.tslat.aoa3.client.model.entity.projectile.CobblestoneProjectileModel;
import net.tslat.aoa3.client.model.entity.projectile.thrown.*;
import net.tslat.aoa3.client.render.entity.AnimatedMobRenderer;
import net.tslat.aoa3.client.render.entity.AoAMobRenderer;
import net.tslat.aoa3.client.render.entity.animal.BasicLavaFishRenderer;
import net.tslat.aoa3.client.render.entity.animal.BasicWaterFishRenderer;
import net.tslat.aoa3.client.render.entity.animal.ShinySquidRenderer;
import net.tslat.aoa3.client.render.entity.misc.HaulingBobberRenderer;
import net.tslat.aoa3.client.render.entity.projectile.ModelledProjectileRenderer;
import net.tslat.aoa3.client.render.entity.projectile.TexturedProjectileRenderer;
import net.tslat.aoa3.client.render.entity.projectile.blasters.*;
import net.tslat.aoa3.client.render.entity.projectile.bullets.*;
import net.tslat.aoa3.client.render.entity.projectile.cannonshots.*;
import net.tslat.aoa3.client.render.entity.projectile.misc.*;
import net.tslat.aoa3.client.render.entity.projectile.mob.*;
import net.tslat.aoa3.client.render.entity.projectile.staff.*;
import net.tslat.aoa3.common.registration.entity.*;
import net.tslat.aoa3.content.entity.animal.fish.BasicFishEntity;
import net.tslat.aoa3.content.entity.animal.fish.BasicLavaFishEntity;
import net.tslat.aoa3.util.ColourUtil;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Function;
import java.util.function.Supplier;

@SuppressWarnings("unused")
public final class AoAEntityRendering {
	private static final ArrayList<EntityRendererPackage> entityRenderers = new ArrayList<>();

	public static final EntityRendererPackage<?> BLUE_GEMTRAP = new EntityRendererPackage<>(AoAAnimals.BLUE_GEMTRAP).provider(context -> new BasicWaterFishRenderer(context, new EntityGeoModel<BasicFishEntity>("animal/fish/blue_gemtrap").withModel("animal/fish/gemtrap")));
	public static final EntityRendererPackage<?> CANDLEFISH = new EntityRendererPackage<>(AoAAnimals.CANDLEFISH).provider(context -> new BasicLavaFishRenderer(context, new EntityGeoModel<BasicLavaFishEntity>("animal/fish/candlefish").withAnimations("animal/fish/jamfish")));
	public static final EntityRendererPackage<?> CHARRED_CHAR = new EntityRendererPackage<>(AoAAnimals.CHARRED_CHAR).provider(context -> new BasicLavaFishRenderer(context, new EntityGeoModel<>("animal/fish/charred_char")));
	public static final EntityRendererPackage<?> CHOCAW = new EntityRendererPackage<>(AoAAnimals.CHOCAW).provider(context -> new BasicWaterFishRenderer(context, new EntityGeoModel<BasicFishEntity>("animal/fish/chocaw").withAnimations("animal/fish/jamfish")));
	public static final EntityRendererPackage<?> CORATEE = new EntityRendererPackage<>(AoAAnimals.CORATEE).geckolib(new CorateeModel());
	public static final EntityRendererPackage<?> CRIMSON_SKIPPER = new EntityRendererPackage<>(AoAAnimals.CRIMSON_SKIPPER).provider(context -> new BasicLavaFishRenderer(context, new EntityGeoModel<BasicLavaFishEntity>("animal/fish/crimson_skipper").withModel("animal/fish/skipper").withAnimations("animal/fish/jamfish")));
	public static final EntityRendererPackage<?> CRIMSON_STRIPEFISH = new EntityRendererPackage<>(AoAAnimals.CRIMSON_STRIPEFISH).provider(context -> new BasicLavaFishRenderer(context, new EntityGeoModel<BasicLavaFishEntity>("animal/fish/crimson_stripefish").withModel("animal/fish/stripefish").withAnimations("animal/fish/jamfish")));
	public static final EntityRendererPackage<?> DARK_HATCHETFISH = new EntityRendererPackage<>(AoAAnimals.DARK_HATCHETFISH).provider(context -> new BasicWaterFishRenderer(context, new EntityGeoModel<>("animal/fish/dark_hatchetfish")));
	public static final EntityRendererPackage<?> GREEN_GEMTRAP = new EntityRendererPackage<>(AoAAnimals.GREEN_GEMTRAP).provider(context -> new BasicWaterFishRenderer(context, new EntityGeoModel<BasicFishEntity>("animal/fish/green_gemtrap").withModel("animal/fish/gemtrap")));
	public static final EntityRendererPackage<?> HYDRONE = new EntityRendererPackage<>(AoAAnimals.HYDRONE).provider(context -> new BasicWaterFishRenderer(context, new EntityGeoModel<>("animal/fish/hydrone")));
	public static final EntityRendererPackage<?> IRONBACK = new EntityRendererPackage<>(AoAAnimals.IRONBACK).provider(context -> new BasicWaterFishRenderer(context, new EntityGeoModel<>("animal/fish/ironback")));
	public static final EntityRendererPackage<?> JAMFISH = new EntityRendererPackage<>(AoAAnimals.JAMFISH).provider(context -> new BasicWaterFishRenderer(context, new EntityGeoModel<>("animal/fish/jamfish")));
	public static final EntityRendererPackage<?> PARAPIRANHA = new EntityRendererPackage<>(AoAAnimals.PARAPIRANHA).provider(context -> new BasicWaterFishRenderer(context, new EntityGeoModel<>("animal/fish/parapiranha")));
	public static final EntityRendererPackage<?> PEARL_STRIPEFISH = new EntityRendererPackage<>(AoAAnimals.PEARL_STRIPEFISH).provider(context -> new BasicWaterFishRenderer(context, new EntityGeoModel<BasicFishEntity>("animal/fish/pearl_stripefish").withModel("animal/fish/stripefish").withAnimations("animal/fish/jamfish")));
	public static final EntityRendererPackage<?> PURPLE_GEMTRAP = new EntityRendererPackage<>(AoAAnimals.PURPLE_GEMTRAP).provider(context -> new BasicWaterFishRenderer(context, new EntityGeoModel<BasicFishEntity>("animal/fish/purple_gemtrap").withModel("animal/fish/gemtrap")));
	public static final EntityRendererPackage<?> RAINBOWFISH = new EntityRendererPackage<>(AoAAnimals.RAINBOWFISH).provider(context -> new BasicWaterFishRenderer(context, new EntityGeoModel<>("animal/fish/rainbowfish")));
	public static final EntityRendererPackage<?> RAZORFISH = new EntityRendererPackage<>(AoAAnimals.RAZORFISH).provider(context -> new BasicWaterFishRenderer(context, new EntityGeoModel<BasicFishEntity>("animal/fish/razorfish").withAnimations("animal/fish/jamfish")));
	public static final EntityRendererPackage<?> RED_GEMTRAP = new EntityRendererPackage<>(AoAAnimals.RED_GEMTRAP).provider(context -> new BasicWaterFishRenderer(context, new EntityGeoModel<BasicFishEntity>("animal/fish/red_gemtrap").withModel("animal/fish/gemtrap")));
	public static final EntityRendererPackage<?> REEFTOOTH = new EntityRendererPackage<>(AoAAnimals.REEFTOOTH).provider(context -> new BasicWaterFishRenderer(context, new EntityGeoModel<>("animal/fish/reeftooth")));
	public static final EntityRendererPackage<?> ROCKETFISH = new EntityRendererPackage<>(AoAAnimals.ROCKETFISH).provider(context -> new BasicWaterFishRenderer(context, new EntityGeoModel<>("animal/fish/rocketfish")));
	public static final EntityRendererPackage<?> SAILBACK = new EntityRendererPackage<>(AoAAnimals.SAILBACK).provider(context -> new BasicWaterFishRenderer(context, new EntityGeoModel<BasicFishEntity>("animal/fish/sailback").withAnimations("animal/fish/jamfish")));
	public static final EntityRendererPackage<?> SAPPHIRE_STRIDER = new EntityRendererPackage<>(AoAAnimals.SAPPHIRE_STRIDER).provider(context -> new BasicWaterFishRenderer(context, new EntityGeoModel<>("animal/fish/sapphire_strider")));
	public static final EntityRendererPackage<?> SKELECANTH = new EntityRendererPackage<>(AoAAnimals.SKELECANTH).provider(context -> new BasicWaterFishRenderer(context, new EntityGeoModel<BasicFishEntity>("animal/fish/skelecanth").withAnimations("animal/fish/jamfish")));
	public static final EntityRendererPackage<?> TURQUOISE_STRIPEFISH = new EntityRendererPackage<>(AoAAnimals.TURQUOISE_STRIPEFISH).provider(context -> new BasicWaterFishRenderer(context, new EntityGeoModel<BasicFishEntity>("animal/fish/turquoise_stripefish").withModel("animal/fish/stripefish").withAnimations("animal/fish/jamfish")));
	public static final EntityRendererPackage<?> VIOLET_SKIPPER = new EntityRendererPackage<>(AoAAnimals.VIOLET_SKIPPER).provider(context -> new BasicWaterFishRenderer(context, new EntityGeoModel<BasicFishEntity>("animal/fish/violet_skipper").withModel("animal/fish/skipper").withAnimations("animal/fish/jamfish")));
	public static final EntityRendererPackage<?> WHITE_GEMTRAP = new EntityRendererPackage<>(AoAAnimals.WHITE_GEMTRAP).provider(context -> new BasicWaterFishRenderer(context, new EntityGeoModel<BasicFishEntity>("animal/fish/white_gemtrap").withModel("animal/fish/gemtrap")));
	public static final EntityRendererPackage<?> YELLOW_GEMTRAP = new EntityRendererPackage<>(AoAAnimals.YELLOW_GEMTRAP).provider(context -> new BasicWaterFishRenderer(context, new EntityGeoModel<BasicFishEntity>("animal/fish/yellow_gemtrap").withModel("animal/fish/gemtrap")));
	public static final EntityRendererPackage<?> SHINY_SQUID = new EntityRendererPackage<>(AoAAnimals.SHINY_SQUID).defineLayer("shiny_squid", ShinySquidModel::createBodyLayer).provider(ShinySquidRenderer::new);

	public static final EntityRendererPackage<?> THORNY_PLANT_SPROUT = new EntityRendererPackage<>(AoAMiscEntities.THORNY_PLANT_SPROUT).geckolib("mob/misc/thorny_plant_sprout");

	public static final EntityRendererPackage<?> ANGLER = new EntityRendererPackage<>(AoAMobs.ANGLER).geckolib("mob/lborean/angler");
	public static final EntityRendererPackage<?> ARCBEAST = new EntityRendererPackage<>(AoAMobs.ARCBEAST).geckolib("mob/shyrelands/arcbeast");
	public static final EntityRendererPackage<?> BONEBACK = new EntityRendererPackage<>(AoAMobs.BONEBACK).geckolib("mob/overworld/boneback");
	public static final EntityRendererPackage<?> BUGEYE = new EntityRendererPackage<>(AoAMobs.BUGEYE).geckolib("mob/overworld/bugeye");
	public static final EntityRendererPackage<?> BUSH_BABY = new EntityRendererPackage<>(AoAMobs.BUSH_BABY).geckolib("mob/overworld/bush_baby");
	public static final EntityRendererPackage<?> CHOMPER = new EntityRendererPackage<>(AoAMobs.CHOMPER).geckolib("mob/overworld/chomper");
	public static final EntityRendererPackage<?> ICE_GIANT = new EntityRendererPackage<>(AoAMobs.ICE_GIANT).geckolib("mob/overworld/ice_giant");
	public static final EntityRendererPackage<?> LEAFY_GIANT = new EntityRendererPackage<>(AoAMobs.LEAFY_GIANT).geckolib("mob/overworld/leafy_giant");
	public static final EntityRendererPackage<?> MUCKOPEDE = new EntityRendererPackage<>(AoAMobs.MUCKOPEDE).geckolib("mob/overworld/muckopede");
	public static final EntityRendererPackage<?> MUNCHER = new EntityRendererPackage<>(AoAMobs.MUNCHER).geckolib("mob/lborean/muncher");
	public static final EntityRendererPackage<?> NEPTUNO = new EntityRendererPackage<>(AoAMobs.NEPTUNO).geckolib("mob/lborean/neptuno");
	public static final EntityRendererPackage<?> NIGHTFLY = new EntityRendererPackage<>(AoAMobs.NIGHTFLY).geckolib("mob/overworld/nightfly");
	public static final EntityRendererPackage<?> NIGHT_REAPER = new EntityRendererPackage<>(AoAMobs.NIGHT_REAPER).geckolib("mob/overworld/night_reaper");
	public static final EntityRendererPackage<?> OMNILIGHT = new EntityRendererPackage<>(AoAMobs.OMNILIGHT).geckolib("mob/shyrelands/omnilight");
	public static final EntityRendererPackage<?> SAND_GIANT = new EntityRendererPackage<>(AoAMobs.SAND_GIANT).geckolib("mob/overworld/sand_giant");
	public static final EntityRendererPackage<?> SEA_VIPER = new EntityRendererPackage<>(AoAMobs.SEA_VIPER).geckolib("mob/lborean/sea_viper");
	public static final EntityRendererPackage<?> STONE_GIANT = new EntityRendererPackage<>(AoAMobs.STONE_GIANT).geckolib("mob/overworld/stone_giant");
	public static final EntityRendererPackage<?> SYSKER = new EntityRendererPackage<>(AoAMobs.SYSKER).geckolib("mob/shyrelands/sysker");
	public static final EntityRendererPackage<?> VISULAR = new EntityRendererPackage<>(AoAMobs.VISULAR).geckolib("mob/lunalus/visular");
	public static final EntityRendererPackage<?> VISULON = new EntityRendererPackage<>(AoAMobs.VISULON).geckolib("mob/lunalus/visulon");
	public static final EntityRendererPackage<?> WOOD_GIANT = new EntityRendererPackage<>(AoAMobs.WOOD_GIANT).geckolib("mob/overworld/wood_giant");

	public static final EntityRendererPackage<?> ASSASSIN = new EntityRendererPackage<>(AoANpcs.ASSASSIN).defineLayer("assassin", humanoidLayerDefinition()).defaultMobRenderer(HumanoidModel::new, "textures/entity/npc/trader/assassin.png");
	public static final EntityRendererPackage<?> CREEP_BANKER = new EntityRendererPackage<>(AoANpcs.CREEP_BANKER).defineLayer("creep_banker", humanoidLayerDefinition()).defaultMobRenderer(HumanoidModel::new, "textures/entity/npc/banker/creep_banker.png");
	public static final EntityRendererPackage<?> CRYSTAL_TRADER = new EntityRendererPackage<>(AoANpcs.CRYSTAL_TRADER).defineLayer("crystal_trader", humanoidLayerDefinition()).defaultMobRenderer(HumanoidModel::new, "textures/entity/npc/trader/crystal_trader.png");
	public static final EntityRendererPackage<?> DUNGEON_KEEPER = new EntityRendererPackage<>(AoANpcs.DUNGEON_KEEPER).defineLayer("dungeon_keeper", humanoidLayerDefinition()).defaultMobRenderer(HumanoidModel::new, "textures/entity/npc/trader/dungeon_keeper.png");
	public static final EntityRendererPackage<?> EXPLOSIVES_EXPERT = new EntityRendererPackage<>(AoANpcs.EXPLOSIVES_EXPERT).defineLayer("explosives_expert", humanoidLayerDefinition()).defaultMobRenderer(HumanoidModel::new, "textures/entity/npc/trader/explosives_expert.png");
	public static final EntityRendererPackage<?> METALLOID = new EntityRendererPackage<>(AoANpcs.METALLOID).defineLayer("metalloid", humanoidLayerDefinition()).defaultMobRenderer(HumanoidModel::new, "textures/entity/npc/trader/metalloid.png");
	public static final EntityRendererPackage<?> NATURALIST = new EntityRendererPackage<>(AoANpcs.NATURALIST).defineLayer("naturalist", humanoidLayerDefinition()).defaultMobRenderer(HumanoidModel::new, "textures/entity/npc/trader/naturalist.png");
	public static final EntityRendererPackage<?> REALMSHIFTER = new EntityRendererPackage<>(AoANpcs.REALMSHIFTER).defineLayer("realmshifter", humanoidLayerDefinition()).defaultMobRenderer(HumanoidModel::new, "textures/entity/npc/trader/realmshifter.png");
	public static final EntityRendererPackage<?> SHYRE_ARCHER = new EntityRendererPackage<>(AoANpcs.SHYRE_ARCHER).defineLayer("shyre_archer", humanoidLayerDefinition()).defaultMobRenderer(HumanoidModel::new, "textures/entity/npc/trader/shyre_archer.png");
	public static final EntityRendererPackage<?> SHYRE_BANKER = new EntityRendererPackage<>(AoANpcs.SHYRE_BANKER).defineLayer("shyre_banker", humanoidLayerDefinition()).defaultMobRenderer(HumanoidModel::new, "textures/entity/npc/banker/shyre_banker.png");
	public static final EntityRendererPackage<?> TOKEN_COLLECTOR = new EntityRendererPackage<>(AoANpcs.TOKEN_COLLECTOR).defineLayer("token_collector", humanoidLayerDefinition()).defaultMobRenderer(HumanoidModel::new, "textures/entity/npc/trader/token_collector.png");
	public static final EntityRendererPackage<?> UNDEAD_HERALD = new EntityRendererPackage<>(AoANpcs.UNDEAD_HERALD).defineLayer("undead_herald", humanoidLayerDefinition()).defaultMobRenderer(HumanoidModel::new, "textures/entity/npc/trader/undead_herald.png");
	public static final EntityRendererPackage<?> SKILL_MASTER = new EntityRendererPackage<>(AoANpcs.SKILL_MASTER).geckolib("npc/trader/skill_master");

	public static final EntityRendererPackage<?> GRENADE = new EntityRendererPackage<>(AoAProjectiles.GRENADE).defineLayer("grenade", GrenadeModel::createLayerDefinition).provider(context -> new ModelledProjectileRenderer<>(context, AoAEntityRendering.GRENADE.getMainLayerLocation(), GrenadeModel::new, AdventOfAscension.id("textures/entity/projectile/thrown/grenade.png")));
	public static final EntityRendererPackage<?> RUNIC_BOMB = new EntityRendererPackage<>(AoAProjectiles.RUNIC_BOMB).provider(context -> new ModelledProjectileRenderer<>(context, AoAEntityRendering.GRENADE.getMainLayerLocation(), GrenadeModel::new, AdventOfAscension.id("textures/entity/projectile/thrown/runic_bomb.png")));
	public static final EntityRendererPackage<?> CHAKRAM = new EntityRendererPackage<>(AoAProjectiles.CHAKRAM).defineLayer("chakram", ChakramModel::createLayerDefinition).provider(context -> new ModelledProjectileRenderer<>(context, AoAEntityRendering.CHAKRAM.getMainLayerLocation(), ChakramModel::new, AdventOfAscension.id("textures/entity/projectile/thrown/chakram.png")));
	public static final EntityRendererPackage<?> VULKRAM = new EntityRendererPackage<>(AoAProjectiles.VULKRAM).provider(context -> new ModelledProjectileRenderer<>(context, AoAEntityRendering.CHAKRAM.getMainLayerLocation(), ChakramModel::new, AdventOfAscension.id("textures/entity/projectile/thrown/vulkram.png")));
	public static final EntityRendererPackage<?> SLICE_STAR = new EntityRendererPackage<>(AoAProjectiles.SLICE_STAR).defineLayer("slice_star", SliceStarModel::createLayerDefinition).provider(context -> new ModelledProjectileRenderer<>(context, AoAEntityRendering.SLICE_STAR.getMainLayerLocation(), SliceStarModel::new, AdventOfAscension.id("textures/entity/projectile/thrown/slice_star.png")));
	public static final EntityRendererPackage<?> GOO_BALL = new EntityRendererPackage<>(AoAProjectiles.GOO_BALL).defineLayer("goo_ball", GooBallModel::createLayerDefinition).provider(context -> new ModelledProjectileRenderer<>(context, AoAEntityRendering.GOO_BALL.getMainLayerLocation(), GooBallModel::new, AdventOfAscension.id("textures/entity/projectile/thrown/goo_ball.png")));
	public static final EntityRendererPackage<?> HELLFIRE = new EntityRendererPackage<>(AoAProjectiles.HELLFIRE).defineLayer("hellfire", HellfireModel::createLayerDefinition).provider(context -> new ModelledProjectileRenderer<>(context, AoAEntityRendering.HELLFIRE.getMainLayerLocation(), HellfireModel::new, AdventOfAscension.id("textures/entity/projectile/thrown/hellfire.png")));

	public static final EntityRendererPackage<?> REINFORCED_BOBBER = new EntityRendererPackage<>(AoAMiscEntities.REINFORCED_BOBBER).provider(HaulingBobberRenderer::new);
	public static final EntityRendererPackage<?> GOLD_BOBBER = new EntityRendererPackage<>(AoAMiscEntities.GOLD_BOBBER).provider(context -> new HaulingBobberRenderer(context, AdventOfAscension.id("textures/entity/misc/gold_bobber.png")));
	public static final EntityRendererPackage<?> THERMAL_BOBBER = new EntityRendererPackage<>(AoAMiscEntities.THERMAL_BOBBER).provider(context -> new HaulingBobberRenderer(context, AdventOfAscension.id("textures/entity/misc/thermal_bobber.png")));

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
	public static final EntityRendererPackage<?> BONE_BULLET = new EntityRendererPackage<>(AoAProjectiles.BONE_BULLET).provider(context -> new TexturedProjectileRenderer<>(context, AdventOfAscension.id("textures/entity/projectile/bullets/bone_pellet.png")));
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
	public static final EntityRendererPackage<?> LASER_SHOT = new EntityRendererPackage<>(AoAProjectiles.LASER_SHOT).provider(context -> new TexturedProjectileRenderer<>(context, AdventOfAscension.id("textures/entity/projectile/bullets/laser_shot.png")));
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

	public static void init() {
		AdventOfAscension.modEventBus.addListener(EventPriority.NORMAL, false, EntityRenderersEvent.RegisterRenderers.class, AoAEntityRendering::registerEntityRenderers);
		AdventOfAscension.modEventBus.addListener(EventPriority.NORMAL, false, EntityRenderersEvent.RegisterLayerDefinitions.class, AoAEntityRendering::registerLayerDefinitions);
	}

	private static void registerEntityRenderers(final EntityRenderersEvent.RegisterRenderers ev) {
		for (EntityRendererPackage<Entity> rendererPackage : entityRenderers) {
			ev.registerEntityRenderer(rendererPackage.entityType.get(), rendererPackage.build());
		}

		entityRenderers.clear();
	}

	private static void registerLayerDefinitions(final EntityRenderersEvent.RegisterLayerDefinitions ev) {
		for (EntityRendererPackage<?> rendererPackage : entityRenderers) {
			rendererPackage.registerModelLayer(ev);
		}
	}

/*	private static void initModels() {
		//new EntityRendererPackage<>(AoAProjectiles.MECH_FALL).provider(MechFallRenderer::new);
		//new EntityRendererPackage<>(AoAProjectiles.MECH_SHOT).provider(MechShotRenderer::new);
		//new EntityRendererPackage<>(AoAProjectiles.SHADOWLORD_SHOT).provider(ShadowlordShotRenderer::new);
		//new EntityRendererPackage<>(AoAProjectiles.VOXXULON_METEOR).provider(VoxxulonMeteorRenderer::new);
		//register(AoAProjectiles.ARCWORM_SHOT.get(), new ArcwormShotRenderer(renderManager, AdventOfAscension.id("textures/entity/mob/shyrelands/arcworm.png")));
		//register(AoAProjectiles.CORALLUS_SHOT.get(), new CorallusShotRenderer(renderManager, AdventOfAscension.id("textures/entity/projectile/mobshots/corallus_shot.png")));
		//register(AoAProjectiles.NETHENGEIC_WITHER_SHOT.get(), new NethengeicWitherShotRenderer(renderManager, AdventOfAscension.id("textures/entity/projectile/mobshots/nethengeic_skull.png")));
	}*/

	private static Supplier<LayerDefinition> humanoidLayerDefinition() {
		return () -> LayerDefinition.create(HumanoidModel.createMesh(CubeDeformation.NONE, 0.0F), 64, 64);
	}

	public static class EntityRendererPackage<T extends Entity> {
		private final RegistryObject<EntityType<T>> entityType;

		private EntityRendererProvider<T> rendererProvider = null;

		private final HashMap<String, Pair<ModelLayerLocation, Supplier<LayerDefinition>>> layerDefinitions = new HashMap<>();

		private float shadowSize = -1;

		private EntityRendererPackage(RegistryObject<EntityType<T>> entityType) {
			this.entityType = entityType;

			entityRenderers.add(this);
		}

		private EntityRendererPackage<T> shadowSize(float shadow) {
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

		private EntityRendererPackage<T> geckolib(String path) {
			return geckolib(new EntityGeoModel<>(path));
		}

		private EntityRendererPackage<T> geckolib(EntityGeoModel<?> model) {
			return provider(context -> new AnimatedMobRenderer(context, model, this.shadowSize));
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

		private EntityRendererProvider<T> build() {
			if (this.rendererProvider == null)
				throw new IllegalStateException("No registered renderer provider for entity: " + this.entityType.getId());

			if (this.shadowSize == -1)
				this.shadowSize = this.entityType.get().getWidth() / 3f;

			return this.rendererProvider;
		}
	}
}
