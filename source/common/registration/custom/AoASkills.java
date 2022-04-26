package net.tslat.aoa3.common.registration.custom;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryManager;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.player.skill.*;

import javax.annotation.Nullable;

public class AoASkills {
	private static IForgeRegistry<AoASkill> REGISTRY = null;
	public static final DeferredRegister<AoASkill> SKILLS = DeferredRegister.create(AoASkill.class, AdventOfAscension.MOD_ID);

	//public static final RegistryObject<AoASkill> ALCHEMY = SKILLS.register("alchemy", () -> new AoASkill(AlchemySkill::new, AlchemySkill::new));
	//public static final RegistryObject<AoASkill> CREATION = SKILLS.register("creation", () -> new AoASkill(CreationSkill::new, CreationSkill::new));
	public static final RegistryObject<AoASkill> DEXTERITY = SKILLS.register("dexterity", () -> new AoASkill(DexteritySkill::new, DexteritySkill::new));
	//public static final RegistryObject<AoASkill> ENGINEERING = SKILLS.register("engineering", () -> new AoASkill(EngineeringSkill::new, EngineeringSkill::new));
	public static final RegistryObject<AoASkill> EXTRACTION = SKILLS.register("extraction", () -> new AoASkill(ExtractionSkill::new, ExtractionSkill::new));
	public static final RegistryObject<AoASkill> FARMING = SKILLS.register("farming", () -> new AoASkill(FarmingSkill::new, FarmingSkill::new));
	//public static final RegistryObject<AoASkill> FAUNAMANCY = SKILLS.register("faunamancy", () -> new AoASkill(FaunamancySkill::new, FaunamancySkill::new));
	public static final RegistryObject<AoASkill> HAULING = SKILLS.register("hauling", () -> new AoASkill(HaulingSkill::new, HaulingSkill::new));
	//public static final RegistryObject<AoASkill> IMBUING = SKILLS.register("imbuing", () -> new AoASkill(ImbuingSkill::new, ImbuingSkill::new));
	public static final RegistryObject<AoASkill> INNERVATION = SKILLS.register("innervation", () -> new AoASkill(InnervationSkill::new, InnervationSkill::new));

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
		public CompoundNBT saveToNbt() {
			return new CompoundNBT();
		}

		@Override
		public void loadFromNbt(CompoundNBT skillData) {}

		@Override
		public CompoundNBT getSyncData(boolean forClientSetup) {
			return new CompoundNBT();
		}

		@Override
		public void receiveSyncData(CompoundNBT data) {}
	};

	@Nullable
	public static AoASkill getSkill(ResourceLocation id) {
		return getRegistry().getValue(id);
	}

	public static IForgeRegistry<AoASkill> getRegistry() {
		if (REGISTRY == null)
			REGISTRY = RegistryManager.ACTIVE.getRegistry(AoASkill.class);

		return REGISTRY;
	}
}
