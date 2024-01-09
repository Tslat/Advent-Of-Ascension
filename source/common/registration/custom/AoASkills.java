package net.tslat.aoa3.common.registration.custom;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.tslat.aoa3.common.registration.AoARegistries;
import net.tslat.aoa3.player.skill.*;
import org.jetbrains.annotations.Nullable;


public class AoASkills {
	public static void init() {}

	public static final DeferredHolder<AoASkill, AoASkill> ALCHEMY = AoARegistries.AOA_SKILLS.register("alchemy", () -> new AoASkill(AlchemySkill::new, AlchemySkill::new));
	public static final DeferredHolder<AoASkill, AoASkill> ARTIFICE = AoARegistries.AOA_SKILLS.register("artifice", () -> new AoASkill(ArtificeSkill::new, ArtificeSkill::new));
	public static final DeferredHolder<AoASkill, AoASkill> CREATION = AoARegistries.AOA_SKILLS.register("creation", () -> new AoASkill(CreationSkill::new, CreationSkill::new));
	public static final DeferredHolder<AoASkill, AoASkill> DEXTERITY = AoARegistries.AOA_SKILLS.register("dexterity", () -> new AoASkill(DexteritySkill::new, DexteritySkill::new));
	public static final DeferredHolder<AoASkill, AoASkill> EXTRACTION = AoARegistries.AOA_SKILLS.register("extraction", () -> new AoASkill(ExtractionSkill::new, ExtractionSkill::new));
	public static final DeferredHolder<AoASkill, AoASkill> FARMING = AoARegistries.AOA_SKILLS.register("farming", () -> new AoASkill(FarmingSkill::new, FarmingSkill::new));
	public static final DeferredHolder<AoASkill, AoASkill> FAUNAMANCY = AoARegistries.AOA_SKILLS.register("faunamancy", () -> new AoASkill(FaunamancySkill::new, FaunamancySkill::new));
	public static final DeferredHolder<AoASkill, AoASkill> HAULING = AoARegistries.AOA_SKILLS.register("hauling", () -> new AoASkill(HaulingSkill::new, HaulingSkill::new));
	public static final DeferredHolder<AoASkill, AoASkill> IMBUING = AoARegistries.AOA_SKILLS.register("imbuing", () -> new AoASkill(ImbuingSkill::new, ImbuingSkill::new));
	public static final DeferredHolder<AoASkill, AoASkill> INNERVATION = AoARegistries.AOA_SKILLS.register("innervation", () -> new AoASkill(InnervationSkill::new, InnervationSkill::new));

	public static final AoASkill.Instance DEFAULT = new AoASkill.Instance(null, null, null) {
		@Override
		public int getLevel(boolean includeVanityLevels) {
			return 0;
		}

		@Override
		public boolean hasLevel(int levelReq) {
			return false;
		}

		@Override
		public void adjustXp(float xp, boolean isUnnaturalSource, boolean ignoreXpBuffs) {}

		@Override
		public boolean addCycle() {
			return false;
		}

		@Override
		public CompoundTag saveToNbt() {
			return new CompoundTag();
		}

		@Override
		public void loadFromNbt(CompoundTag skillData) {}

		@Override
		public CompoundTag getSyncData(boolean forClientSetup) {
			return new CompoundTag();
		}

		@Override
		public void receiveSyncData(CompoundTag data) {}
	};

	@Nullable
	public static AoASkill getSkill(ResourceLocation id) {
		return AoARegistries.AOA_SKILLS.getEntry(id);
	}
}
