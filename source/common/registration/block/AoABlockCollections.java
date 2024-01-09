package net.tslat.aoa3.common.registration.block;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.tslat.aoa3.advent.AdventOfAscension;

import java.util.function.Supplier;

public final class AoABlockCollections {
	public static final BlockSet ACHONY_SET = BlockSetBuilder.defaultWood("achony").build();
	public static final BlockSet BAOBAB_SET = BlockSetBuilder.defaultWood("baobab").build();
	public static final BlockSet BLOODWOOD_SET = BlockSetBuilder.defaultWood("bloodwood").build();
	public static final BlockSet CHURRY_SET = BlockSetBuilder.defaultWood("churry").build();
	public static final BlockSet CREEP_SET = BlockSetBuilder.defaultWood("creep").build();
	public static final BlockSet DAWNWOOD_SET = BlockSetBuilder.defaultWood("dawnwood").build();
	public static final BlockSet HAUNTEDWOOD_SET = BlockSetBuilder.defaultWood("hauntedwood").build();
	public static final BlockSet IROWOOD_SET = BlockSetBuilder.defaultWood("irowood").build();
	public static final BlockSet LUCALUS_SET = BlockSetBuilder.defaultWood("lucalus").build();
	public static final BlockSet LUNIDE_SET = BlockSetBuilder.defaultWood("lunide").build();
	public static final BlockSet RUNIC_SET = BlockSetBuilder.defaultWood("runic").build();
	public static final BlockSet SHADOW_SET = BlockSetBuilder.defaultWood("shadow").build();
	public static final BlockSet SHYRE_SET = BlockSetBuilder.defaultWood("shyre").build();
	public static final BlockSet STRANGLEWOOD_SET = BlockSetBuilder.defaultWood("stranglewood").build();
	public static final BlockSet TOXICWOOD_SET = BlockSetBuilder.defaultWood("toxicwood").build();
	public static final BlockSet TWINKLESTONE_SET = BlockSetBuilder.defaultStone("twinklestone").soundBase(SoundType.GLASS).doorClose(SoundEvents.BAMBOO_WOOD_DOOR_CLOSE).doorOpen(SoundEvents.BAMBOO_WOOD_DOOR_OPEN).trapdoorClose(SoundEvents.BAMBOO_WOOD_TRAPDOOR_CLOSE).trapdoorOpen(SoundEvents.BAMBOO_WOOD_TRAPDOOR_OPEN).build();
	public static final BlockSet BARON_STONE_SET = BlockSetBuilder.defaultStone("baron_stone").build();
	public static final BlockSet DARKENED_ROCK_SET = BlockSetBuilder.defaultStone("darkened_rock").build();
	public static final BlockSet DENSE_STONE_SET = BlockSetBuilder.defaultStone("dense_stone").build();
	public static final BlockSet HELLSTONE_SET = BlockSetBuilder.defaultStone("hellstone").build();
	public static final BlockSet IROSTONE_SET = BlockSetBuilder.defaultStone("irostone").build();
	public static final BlockSet PRECASIAN_STONE_SET = BlockSetBuilder.defaultStone("precasian_stone").build();
	public static final BlockSet RUNIC_STONE_SET = BlockSetBuilder.defaultStone("runic_stone").build();

	public record BlockSet(BlockSetType blockSetType, Supplier<WoodType> woodTypeSupplier) {
		public WoodType woodType() {
			return this.woodTypeSupplier.get();
		}
	}

	private static class BlockSetBuilder {
		private final ResourceLocation id;

		private boolean handActivated = true;
		private boolean windChargeActivated = true;
		private boolean arrowActivated = true;
		private BlockSetType.PressurePlateSensitivity pressurePlateSensitivity = BlockSetType.PressurePlateSensitivity.EVERYTHING;
		private SoundType soundType = SoundType.WOOD;
		private SoundEvent doorCloseSound = SoundEvents.WOODEN_DOOR_CLOSE;
		private SoundEvent doorOpenSound = SoundEvents.WOODEN_DOOR_OPEN;
		private SoundEvent trapdoorCloseSound = SoundEvents.WOODEN_TRAPDOOR_CLOSE;
		private SoundEvent trapdoorOpenSound = SoundEvents.WOODEN_TRAPDOOR_OPEN;
		private SoundEvent pressurePlateClickOffSound = SoundEvents.WOODEN_PRESSURE_PLATE_CLICK_OFF;
		private SoundEvent pressurePlateClickOnSound = SoundEvents.WOODEN_PRESSURE_PLATE_CLICK_ON;
		private SoundEvent buttonClickOffSound = SoundEvents.WOODEN_BUTTON_CLICK_OFF;
		private SoundEvent buttonClickOnSound = SoundEvents.WOODEN_BUTTON_CLICK_ON;

		private SoundType hangingSignSounds = SoundType.HANGING_SIGN;
		private SoundEvent fenceGateCloseSound = SoundEvents.FENCE_GATE_CLOSE;
		private SoundEvent fenceGateOpenSound = SoundEvents.FENCE_GATE_OPEN;

		BlockSetBuilder(String id) {
			this.id	= AdventOfAscension.id(id);
		}

		static BlockSetBuilder defaultMetal(String id) {
			return new BlockSetBuilder(id).handActivated(false).windChargeActivated(false).arrowActivated(false).soundBase(SoundType.METAL).doorOpen(SoundEvents.IRON_DOOR_OPEN).doorClose(SoundEvents.IRON_DOOR_CLOSE).trapdoorOpen(SoundEvents.IRON_TRAPDOOR_OPEN).trapdoorClose(SoundEvents.IRON_TRAPDOOR_CLOSE).pressurePlateOn(SoundEvents.METAL_PRESSURE_PLATE_CLICK_ON).pressurePlateOff(SoundEvents.METAL_PRESSURE_PLATE_CLICK_OFF).buttonOn(SoundEvents.STONE_BUTTON_CLICK_ON).buttonOff(SoundEvents.STONE_BUTTON_CLICK_OFF);
		}

		static BlockSetBuilder defaultStone(String id) {
			return new BlockSetBuilder(id).handActivated(true).windChargeActivated(true).arrowActivated(false).pressurePlateType(BlockSetType.PressurePlateSensitivity.MOBS).soundBase(SoundType.STONE).doorOpen(SoundEvents.IRON_DOOR_OPEN).doorClose(SoundEvents.IRON_DOOR_CLOSE).trapdoorOpen(SoundEvents.IRON_TRAPDOOR_OPEN).trapdoorClose(SoundEvents.IRON_TRAPDOOR_CLOSE).pressurePlateOn(SoundEvents.STONE_PRESSURE_PLATE_CLICK_ON).pressurePlateOff(SoundEvents.STONE_PRESSURE_PLATE_CLICK_OFF).buttonOn(SoundEvents.STONE_BUTTON_CLICK_ON).buttonOff(SoundEvents.STONE_BUTTON_CLICK_OFF);
		}

		static BlockSetBuilder defaultWood(String id) {
			return new BlockSetBuilder(id);
		}

		BlockSetBuilder handActivated(boolean handActivated) {
			this.handActivated = handActivated;

			return this;
		}

		BlockSetBuilder windChargeActivated(boolean windChargeActivated) {
			this.windChargeActivated = windChargeActivated;

			return this;
		}

		BlockSetBuilder arrowActivated(boolean arrowActivated) {
			this.arrowActivated = arrowActivated;

			return this;
		}

		BlockSetBuilder pressurePlateType(BlockSetType.PressurePlateSensitivity sensitivity) {
			this.pressurePlateSensitivity = sensitivity;

			return this;
		}

		BlockSetBuilder soundBase(SoundType soundType) {
			this.soundType = soundType;

			return this;
		}

		BlockSetBuilder doorClose(SoundEvent sound) {
			this.doorCloseSound = sound;

			return this;
		}

		BlockSetBuilder doorOpen(SoundEvent sound) {
			this.doorOpenSound = sound;

			return this;
		}

		BlockSetBuilder trapdoorClose(SoundEvent sound) {
			this.trapdoorCloseSound = sound;

			return this;
		}

		BlockSetBuilder trapdoorOpen(SoundEvent sound) {
			this.trapdoorOpenSound = sound;

			return this;
		}

		BlockSetBuilder pressurePlateOff(SoundEvent sound) {
			this.pressurePlateClickOffSound = sound;

			return this;
		}

		BlockSetBuilder pressurePlateOn(SoundEvent sound) {
			this.pressurePlateClickOnSound = sound;

			return this;
		}

		BlockSetBuilder buttonOff(SoundEvent sound) {
			this.buttonClickOffSound = sound;

			return this;
		}

		BlockSetBuilder buttonOn(SoundEvent sound) {
			this.buttonClickOnSound = sound;

			return this;
		}

		BlockSetBuilder hangingSign(SoundType sound) {
			this.hangingSignSounds = sound;

			return this;
		}

		BlockSetBuilder fenceGateOpen(SoundEvent sound) {
			this.fenceGateOpenSound = sound;

			return this;
		}

		BlockSetBuilder fenceGateClose(SoundEvent sound) {
			this.fenceGateCloseSound = sound;

			return this;
		}

		BlockSet build() {
			final BlockSetType blockSetType = BlockSetType.register(new BlockSetType(this.id.toString(), this.handActivated, this.windChargeActivated, this.arrowActivated, this.pressurePlateSensitivity, this.soundType, this.doorCloseSound, this.doorOpenSound, this.trapdoorCloseSound, this.trapdoorOpenSound, this.pressurePlateClickOffSound, this.pressurePlateClickOnSound, this.buttonClickOffSound, this.buttonClickOnSound));

			return new BlockSet(blockSetType, () -> WoodType.register(new WoodType(blockSetType.name(), blockSetType, blockSetType.soundType(), this.hangingSignSounds, this.fenceGateCloseSound, this.fenceGateOpenSound)));
		}
	}
}
