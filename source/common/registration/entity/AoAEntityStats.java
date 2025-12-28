package net.tslat.aoa3.common.registration.entity;

import net.minecraft.core.Holder;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeModificationEvent;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.AoAAttributes;
import net.tslat.aoa3.content.entity.animal.ShinySquidEntity;
import net.tslat.aoa3.content.entity.animal.barathos.ArkbackEntity;
import net.tslat.aoa3.content.entity.animal.barathos.EmperorBeastEntity;
import net.tslat.aoa3.content.entity.animal.barathos.MasonBeetleEntity;
import net.tslat.aoa3.content.entity.animal.fish.BasicFishEntity;
import net.tslat.aoa3.content.entity.animal.fish.BasicLavaFishEntity;
import net.tslat.aoa3.content.entity.animal.precasia.DeinotheriumEntity;
import net.tslat.aoa3.content.entity.animal.precasia.HorndronEntity;
import net.tslat.aoa3.content.entity.animal.precasia.OpteryxEntity;
import net.tslat.aoa3.content.entity.boss.king_bambambam.EliteKingBamBamBamEntity;
import net.tslat.aoa3.content.entity.boss.king_bambambam.KingBamBamBamEntity;
import net.tslat.aoa3.content.entity.boss.nethengeic_wither.EliteNethengeicWitherEntity;
import net.tslat.aoa3.content.entity.boss.nethengeic_wither.NethengeicWitherEntity;
import net.tslat.aoa3.content.entity.boss.skeletron.EliteSkeletronEntity;
import net.tslat.aoa3.content.entity.boss.skeletron.SkeletronEntity;
import net.tslat.aoa3.content.entity.boss.smash.EliteSmashEntity;
import net.tslat.aoa3.content.entity.boss.smash.SmashEntity;
import net.tslat.aoa3.content.entity.boss.tyrosaur.EliteTyrosaurEntity;
import net.tslat.aoa3.content.entity.boss.tyrosaur.TyrosaurEntity;
import net.tslat.aoa3.content.entity.boss.tyrosaur.WoundedTyrosaurEntity;
import net.tslat.aoa3.content.entity.monster.barathos.EchodarEntity;
import net.tslat.aoa3.content.entity.monster.barathos.NospikeEntity;
import net.tslat.aoa3.content.entity.monster.barathos.TharaflyEntity;
import net.tslat.aoa3.content.entity.monster.misc.ThornyPlantSproutEntity;
import net.tslat.aoa3.content.entity.monster.nether.*;
import net.tslat.aoa3.content.entity.monster.overworld.*;
import net.tslat.aoa3.content.entity.monster.precasia.*;
import net.tslat.aoa3.content.entity.npc.ambient.DryadSpriteEntity;
import net.tslat.aoa3.content.entity.npc.trader.CorruptedTravellerEntity;
import net.tslat.aoa3.content.entity.npc.trader.LottomanEntity;
import net.tslat.aoa3.content.entity.npc.trader.SkillMasterEntity;
import net.tslat.aoa3.content.entity.npc.trader.UndeadHeraldEntity;

public final class AoAEntityStats {
	public static void init() {
		AdventOfAscension.getModEventBus().addListener(EventPriority.NORMAL, false, EntityAttributeCreationEvent.class, AoAEntityStats::registerStats);
		AdventOfAscension.getModEventBus().addListener(EventPriority.NORMAL, false, EntityAttributeModificationEvent.class, AoAEntityStats::modifyStats);
	}

	private static void modifyStats(final EntityAttributeModificationEvent ev) {
		ev.add(EntityType.PLAYER, AoAAttributes.CRITICAL_HIT_MULTIPLIER);
	}

	private static void registerStats(final EntityAttributeCreationEvent ev) {
		doOverworldEntityStats(ev);
		doNetherEntityStats(ev);
		doPrecasiaEntityStats(ev);
		doBarathosEntityStats(ev);
		doBossEntityStats(ev);
		doNpcEntityStats(ev);
		doMiscEntityStats(ev);
	}

	private static void doOverworldEntityStats(final EntityAttributeCreationEvent ev) {
		AncientGolemEntity.entityStats(AoAMonsters.ANCIENT_GOLEM.get()).build(ev);
		BombCarrierEntity.entityStats(AoAMonsters.BOMB_CARRIER.get()).build(ev);
		BushBabyEntity.entityStats(AoAMonsters.BUSH_BABY.get()).build(ev);
		ChargerEntity.entityStats(AoAMonsters.CHARGER.get()).build(ev);
		ChomperEntity.entityStats(AoAMonsters.CHOMPER.get()).build(ev);
		CyclopsEntity.entityStats(AoAMonsters.CYCLOPS.get()).build(ev);
		GhostEntity.entityStats(AoAMonsters.GHOST.get()).build(ev);
		GoblinEntity.entityStats(AoAMonsters.GOBLIN.get()).build(ev);
		SasquatchEntity.entityStats(AoAMonsters.SASQUATCH.get()).build(ev);
		TreeSpiritEntity.entityStats(AoAMonsters.TREE_SPIRIT.get()).build(ev);
		VoidWalkerEntity.entityStats(AoAMonsters.VOID_WALKER.get()).build(ev);
		YetiEntity.entityStats(AoAMonsters.YETI.get()).build(ev);

		KingChargerEntity.entityStats(AoAMonsters.KING_CHARGER.get()).build(ev);
		IceGiantEntity.entityStats(AoAMonsters.ICE_GIANT.get()).build(ev);
		LeafyGiantEntity.entityStats(AoAMonsters.LEAFY_GIANT.get()).build(ev);
		SandGiantEntity.entityStats(AoAMonsters.SAND_GIANT.get()).build(ev);
		StoneGiantEntity.entityStats(AoAMonsters.STONE_GIANT.get()).build(ev);
		WoodGiantEntity.entityStats(AoAMonsters.WOOD_GIANT.get()).build(ev);
	}

	private static void doNetherEntityStats(final EntityAttributeCreationEvent ev) {
		EmbrakeEntity.entityStats(AoAMonsters.EMBRAKE.get()).build(ev);
		FlamewalkerEntity.entityStats(AoAMonsters.FLAMEWALKER.get()).build(ev);
		LittleBamEntity.entityStats(AoAMonsters.LITTLE_BAM.get()).build(ev);
		NethengeicBeastEntity.entityStats(AoAMonsters.NETHENGEIC_BEAST.get()).build(ev);
		InfernalEntity.entityStats(AoAMonsters.INFERNAL.get()).build(ev);
	}

	private static void doPrecasiaEntityStats(final EntityAttributeCreationEvent ev) {
		HorndronEntity.entityStats(AoAAnimals.HORNDRON.get()).build(ev);
		DeinotheriumEntity.entityStats(AoAAnimals.DEINOTHERIUM.get()).build(ev);
		OpteryxEntity.entityStats(AoAAnimals.OPTERYX.get()).build(ev);
		SpinoledonEntity.entityStats(AoAMonsters.SPINOLEDON.get()).build(ev);
		MeganeuropsisEntity.entityStats(AoAMonsters.MEGANEUROPSIS.get()).build(ev);
		ScolopendisEntity.entityStats(AoAMonsters.SCOLOPENDIS.get()).build(ev);
		DunkleosteusEntity.entityStats(AoAMonsters.DUNKLEOSTEUS.get()).build(ev);
		AttercopusEntity.entityStats(AoAMonsters.ATTERCOPUS.get()).build(ev);
		SmilodonEntity.entityStats(AoAMonsters.SMILODON.get()).build(ev);
		SkeletalAbominationEntity.entityStats(AoAMonsters.SKELETAL_ABOMINATION.get()).build(ev);
		VeloraptorEntity.entityStats(AoAMonsters.VELORAPTOR.get()).build(ev);
	}

	private static void doBarathosEntityStats(final EntityAttributeCreationEvent ev) {
		NospikeEntity.entityStats(AoAMonsters.NOSPIKE.get()).build(ev);
		TharaflyEntity.entityStats(AoAMonsters.THARAFLY.get()).build(ev);
		EchodarEntity.entityStats(AoAMonsters.ECHODAR.get()).build(ev);
		ArkbackEntity.entityStats(AoAAnimals.ARKBACK.get()).build(ev);
		EmperorBeastEntity.entityStats(AoAAnimals.EMPEROR_BEAST.get()).build(ev);
		MasonBeetleEntity.entityStats(AoAAnimals.MASON_BEETLE.get()).build(ev);
	}

	private static void doBossEntityStats(final EntityAttributeCreationEvent ev) {
		WoundedTyrosaurEntity.entityStats(AoAMonsters.WOUNDED_TYROSAUR.get()).build(ev);

		SmashEntity.entityStats(AoAMonsters.SMASH.get()).build(ev);
		EliteSmashEntity.entityStats(AoAMonsters.ELITE_SMASH.get()).build(ev);
		NethengeicWitherEntity.entityStats(AoAMonsters.NETHENGEIC_WITHER.get()).build(ev);
		EliteNethengeicWitherEntity.entityStats(AoAMonsters.ELITE_NETHENGEIC_WITHER.get()).build(ev);
		KingBamBamBamEntity.entityStats(AoAMonsters.KING_BAMBAMBAM.get()).build(ev);
		EliteKingBamBamBamEntity.entityStats(AoAMonsters.ELITE_KING_BAMBAMBAM.get()).build(ev);
		TyrosaurEntity.entityStats(AoAMonsters.TYROSAUR.get()).build(ev);
		EliteTyrosaurEntity.entityStats(AoAMonsters.ELITE_TYROSAUR.get()).build(ev);
		SkeletronEntity.entityStats(AoAMonsters.SKELETRON.get()).build(ev);
		EliteSkeletronEntity.entityStats(AoAMonsters.ELITE_SKELETRON.get()).build(ev);
	}

	private static void doNpcEntityStats(final EntityAttributeCreationEvent ev) {
		SkillMasterEntity.entityStats(AoANpcs.SKILL_MASTER.get()).build(ev);
		CorruptedTravellerEntity.entityStats(AoANpcs.CORRUPTED_TRAVELLER.get()).build(ev);
		LottomanEntity.entityStats(AoANpcs.LOTTOMAN.get()).build(ev);
		UndeadHeraldEntity.entityStats(AoANpcs.UNDEAD_HERALD.get()).build(ev);
	}

	private static void doMiscEntityStats(final EntityAttributeCreationEvent ev) {
		ThornyPlantSproutEntity.entityStats(AoAMiscEntities.THORNY_PLANT_SPROUT.get()).build(ev);
		DryadSpriteEntity.entityStats(AoANpcs.DRYAD_SPRITE.get()).build(ev);

		ShinySquidEntity.entityStats(AoAAnimals.SHINY_SQUID.get()).build(ev);
		BasicLavaFishEntity.entityStats(AoAAnimals.CANDLEFISH.get()).build(ev);
		BasicLavaFishEntity.entityStats(AoAAnimals.CHARRED_CHAR.get()).build(ev);
		BasicFishEntity.entityStats(AoAAnimals.CHOCAW.get()).build(ev);
		BasicLavaFishEntity.entityStats(AoAAnimals.CRIMSON_SKIPPER.get()).build(ev);
		BasicLavaFishEntity.entityStats(AoAAnimals.CRIMSON_STRIPEFISH.get()).build(ev);
		BasicFishEntity.entityStats(AoAAnimals.DARK_HATCHETFISH.get()).build(ev);
		BasicFishEntity.entityStats(AoAAnimals.BLUE_GEMTRAP.get()).build(ev);
		BasicFishEntity.entityStats(AoAAnimals.GREEN_GEMTRAP.get()).build(ev);
		BasicFishEntity.entityStats(AoAAnimals.PURPLE_GEMTRAP.get()).build(ev);
		BasicFishEntity.entityStats(AoAAnimals.RED_GEMTRAP.get()).build(ev);
		BasicFishEntity.entityStats(AoAAnimals.WHITE_GEMTRAP.get()).build(ev);
		BasicFishEntity.entityStats(AoAAnimals.YELLOW_GEMTRAP.get()).build(ev);
		BasicFishEntity.entityStats(AoAAnimals.HYDRONE.get()).build(ev);
		BasicFishEntity.entityStats(AoAAnimals.IRONBACK.get()).build(ev);
		BasicFishEntity.entityStats(AoAAnimals.JAMFISH.get()).build(ev);
		BasicFishEntity.entityStats(AoAAnimals.PARAPIRANHA.get()).build(ev);
		BasicFishEntity.entityStats(AoAAnimals.PEARL_STRIPEFISH.get()).build(ev);
		BasicFishEntity.entityStats(AoAAnimals.RAINBOWFISH.get()).build(ev);
		BasicFishEntity.entityStats(AoAAnimals.RAZORFISH.get()).build(ev);
		BasicFishEntity.entityStats(AoAAnimals.REEFTOOTH.get()).build(ev);
		BasicFishEntity.entityStats(AoAAnimals.ROCKETFISH.get()).build(ev);
		BasicFishEntity.entityStats(AoAAnimals.SAILBACK.get()).build(ev);
		BasicFishEntity.entityStats(AoAAnimals.SAPPHIRE_STRIDER.get()).build(ev);
		BasicFishEntity.entityStats(AoAAnimals.SKELECANTH.get()).build(ev);
		BasicFishEntity.entityStats(AoAAnimals.TURQUOISE_STRIPEFISH.get()).build(ev);
		BasicFishEntity.entityStats(AoAAnimals.VIOLET_SKIPPER.get()).build(ev);
	}

	public record AttributeBuilder(EntityType<? extends LivingEntity> entityType, AttributeSupplier.Builder attributeMap) {
		public static AttributeBuilder create(EntityType<? extends LivingEntity> entityType) {
			return new AttributeBuilder(entityType, Mob.createMobAttributes());
		}

		public static AttributeBuilder createMonster(EntityType<? extends Mob> entityType) {
			return create(entityType).meleeStrength(0).extraAttributes(AoAAttributes.AGGRO_RANGE);
		}

		public AttributeBuilder armour(double armour) {
			this.attributeMap.add(Attributes.ARMOR, armour);

			return this;
		}

		public AttributeBuilder armour(double armour, double toughness) {
			this.attributeMap.add(Attributes.ARMOR, armour);
			this.attributeMap.add(Attributes.ARMOR_TOUGHNESS, toughness);

			return this;
		}

		public AttributeBuilder meleeStrength(double strength) {
			this.attributeMap.add(Attributes.ATTACK_DAMAGE, strength);

			return this;
		}

		public AttributeBuilder knockback(double knockbackStrength) {
			this.attributeMap.add(Attributes.ATTACK_KNOCKBACK, knockbackStrength);

			return this;
		}

		// Only really used for players?
		public AttributeBuilder meleeSpeed(double speed) {
			this.attributeMap.add(Attributes.ATTACK_SPEED, speed);

			return this;
		}

		public AttributeBuilder burnTimeMultiplier(double multiplier) {
			this.attributeMap.add(Attributes.BURNING_TIME, multiplier);

			return this;
		}

		public AttributeBuilder explosionKnockbackResist(double resistance) {
			this.attributeMap.add(Attributes.EXPLOSION_KNOCKBACK_RESISTANCE, resistance);

			return this;
		}

		public AttributeBuilder fallDamageMultiplier(double multiplier) {
			this.attributeMap.add(Attributes.FALL_DAMAGE_MULTIPLIER, multiplier);

			return this;
		}

		public AttributeBuilder flyingSpeed(double flyingSpeed) {
			this.attributeMap.add(Attributes.FLYING_SPEED, flyingSpeed);

			return this;
		}

		public AttributeBuilder followRange(double distance) {
			this.attributeMap.add(Attributes.FOLLOW_RANGE, distance);

			return this;
		}

		public AttributeBuilder gravity(double gravity) {
			this.attributeMap.add(Attributes.GRAVITY, gravity);

			return this;
		}

		public AttributeBuilder jumpStrength(double strength) {
			this.attributeMap.add(Attributes.JUMP_STRENGTH, strength);

			return this;
		}

		public AttributeBuilder knockbackResist(double resistance) {
			this.attributeMap.add(Attributes.KNOCKBACK_RESISTANCE, resistance);

			if (!this.attributeMap.hasAttribute(Attributes.EXPLOSION_KNOCKBACK_RESISTANCE))
				this.attributeMap.add(Attributes.EXPLOSION_KNOCKBACK_RESISTANCE, resistance);

			return this;
		}

		public AttributeBuilder health(double health) {
			this.attributeMap.add(Attributes.MAX_HEALTH, health);

			return this;
		}

		public AttributeBuilder moveSpeed(double speed) {
			this.attributeMap.add(Attributes.MOVEMENT_SPEED, speed);

			return this;
		}

		public AttributeBuilder safeFallDistance(double blocks) {
			this.attributeMap.add(Attributes.SAFE_FALL_DISTANCE, blocks);

			return this;
		}

		public AttributeBuilder reinforcementsChance(double chance) {
			this.attributeMap.add(Attributes.SPAWN_REINFORCEMENTS_CHANCE, chance);

			return this;
		}

		public AttributeBuilder stepHeight(double stepHeight) {
			this.attributeMap.add(Attributes.STEP_HEIGHT, stepHeight);

			return this;
		}

		public AttributeBuilder swimSpeedMod(double speed) {
			this.attributeMap.add(NeoForgeMod.SWIM_SPEED, speed);

			return this;
		}

		public AttributeBuilder nametagDistance(double distance) {
			this.attributeMap.add(NeoForgeMod.NAMETAG_DISTANCE, distance);

			return this;
		}

		public AttributeBuilder aggroRange(double distance) {
			this.attributeMap.add(AoAAttributes.AGGRO_RANGE, distance);

			return this;
		}

		public AttributeBuilder projectileDamage(double strength) {
			this.attributeMap.add(AoAAttributes.RANGED_ATTACK_DAMAGE, strength);

			return this;
		}

		public AttributeBuilder extraAttributes(Holder<Attribute>... attributes) {
			for (Holder<Attribute> attribute : attributes) {
				if (!this.attributeMap.hasAttribute(attribute))
					this.attributeMap.add(attribute);
			}

			return this;
		}

		private void build(EntityAttributeCreationEvent ev) {
			ev.put(this.entityType, this.attributeMap.build());
		}
	}
}
