package net.tslat.aoa3.util;

import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.placement.StructurePlacementType;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.tslat.aoa3.common.registration.AoARegistries;
import net.tslat.aoa3.player.ability.AoAAbility;
import net.tslat.aoa3.player.resource.AoAResource;
import net.tslat.aoa3.player.skill.AoASkill;

public final class RegistryUtil {
	public static ResourceLocation getId(ItemStack stack) {
		return getId(stack.getItem());
	}

	public static ResourceLocation getId(Item item) {
		return ForgeRegistries.ITEMS.getKey(item);
	}

	public static ResourceLocation getId(BlockState blockState) {
		return getId(blockState.getBlock());
	}

	public static ResourceLocation getId(Block block) {
		return ForgeRegistries.BLOCKS.getKey(block);
	}

	public static ResourceLocation getId(Level level, Biome biome) {
		return level.registryAccess().registry(Registries.BIOME).get().getKey(biome);
	}

	public static ResourceLocation getId(SoundEvent sound) {
		return ForgeRegistries.SOUND_EVENTS.getKey(sound);
	}

	public static ResourceLocation getId(Entity entity) {
		return getId(entity.getType());
	}

	public static ResourceLocation getId(EntityType<?> entity) {
		return ForgeRegistries.ENTITY_TYPES.getKey(entity);
	}

	public static ResourceLocation getId(AoAAbility ability) {
		return AoARegistries.AOA_ABILITIES.getId(ability);
	}

	public static ResourceLocation getId(AoASkill skill) {
		return AoARegistries.AOA_SKILLS.getId(skill);
	}

	public static ResourceLocation getId(AoAResource resource) {
		return AoARegistries.AOA_RESOURCES.getId(resource);
	}

	public static ResourceLocation getId(ParticleType<?> particleType) {
		return ForgeRegistries.PARTICLE_TYPES.getKey(particleType);
	}

	public static ResourceLocation getId(RecipeType<?> recipeType) {
		return ForgeRegistries.RECIPE_TYPES.getKey(recipeType);
	}

	public static ResourceLocation getId(StructurePlacementType<?> structurePlacementType) {
		return BuiltInRegistries.STRUCTURE_PLACEMENT.getKey(structurePlacementType);
	}

	public static ResourceLocation getId(CreativeModeTab creativeTab) {
		return BuiltInRegistries.CREATIVE_MODE_TAB.getKey(creativeTab);
	}

	public static <T> Registry<T> getVanillaRegistry(Level level, IForgeRegistry<T> forgeRegistry) {
		return level.registryAccess().registryOrThrow(forgeRegistry.getRegistryKey());
	}
}
