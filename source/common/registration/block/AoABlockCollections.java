package net.tslat.aoa3.common.registration.block;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.tslat.aoa3.advent.AdventOfAscension;

public final class AoABlockCollections {
	public static final BlockSetType ACHONY_SET = blockSet("achony");
	public static final BlockSetType BLOODWOOD_SET = blockSet("bloodwood");
	public static final BlockSetType CHURRY_SET = blockSet("churry");
	public static final BlockSetType CREEP_SET = blockSet("creep");
	public static final BlockSetType DAWNWOOD_SET = blockSet("dawnwood");
	public static final BlockSetType HAUNTEDWOOD_SET = blockSet("hauntedwood");
	public static final BlockSetType IROWOOD_SET = blockSet("irowood");
	public static final BlockSetType LUCALUS_SET = blockSet("lucalus");
	public static final BlockSetType LUNIDE_SET = blockSet("lunide");
	public static final BlockSetType RUNIC_SET = blockSet("runic");
	public static final BlockSetType SHADOW_SET = blockSet("shadow");
	public static final BlockSetType SHYRE_SET = blockSet("shyre");
	public static final BlockSetType STRANGLEWOOD_SET = blockSet("stranglewood");
	public static final BlockSetType TOXICWOOD_SET = blockSet("toxicwood");
	public static final BlockSetType TWINKLESTONE_SET = blockSet("twinklestone", true, SoundType.GLASS, SoundEvents.BAMBOO_WOOD_DOOR_CLOSE, SoundEvents.BAMBOO_WOOD_DOOR_OPEN, SoundEvents.BAMBOO_WOOD_TRAPDOOR_CLOSE, SoundEvents.BAMBOO_WOOD_TRAPDOOR_OPEN, SoundEvents.STONE_PRESSURE_PLATE_CLICK_OFF, SoundEvents.STONE_PRESSURE_PLATE_CLICK_ON, SoundEvents.STONE_BUTTON_CLICK_OFF, SoundEvents.STONE_BUTTON_CLICK_ON);
	public static final BlockSetType BARON_STONE_SET = blockSet("baron_stone", true, SoundType.STONE, SoundEvents.IRON_DOOR_CLOSE, SoundEvents.IRON_DOOR_OPEN, SoundEvents.IRON_TRAPDOOR_CLOSE, SoundEvents.IRON_TRAPDOOR_OPEN, SoundEvents.STONE_PRESSURE_PLATE_CLICK_OFF, SoundEvents.STONE_PRESSURE_PLATE_CLICK_ON, SoundEvents.STONE_BUTTON_CLICK_OFF, SoundEvents.STONE_BUTTON_CLICK_ON);
	public static final BlockSetType DARKENED_ROCK_SET = blockSet("darkened_rock", true, SoundType.STONE, SoundEvents.IRON_DOOR_CLOSE, SoundEvents.IRON_DOOR_OPEN, SoundEvents.IRON_TRAPDOOR_CLOSE, SoundEvents.IRON_TRAPDOOR_OPEN, SoundEvents.STONE_PRESSURE_PLATE_CLICK_OFF, SoundEvents.STONE_PRESSURE_PLATE_CLICK_ON, SoundEvents.STONE_BUTTON_CLICK_OFF, SoundEvents.STONE_BUTTON_CLICK_ON);
	public static final BlockSetType DENSE_STONE_SET = blockSet("dense_stone", true, SoundType.STONE, SoundEvents.IRON_DOOR_CLOSE, SoundEvents.IRON_DOOR_OPEN, SoundEvents.IRON_TRAPDOOR_CLOSE, SoundEvents.IRON_TRAPDOOR_OPEN, SoundEvents.STONE_PRESSURE_PLATE_CLICK_OFF, SoundEvents.STONE_PRESSURE_PLATE_CLICK_ON, SoundEvents.STONE_BUTTON_CLICK_OFF, SoundEvents.STONE_BUTTON_CLICK_ON);
	public static final BlockSetType HELLSTONE_SET = blockSet("hellstone", true, SoundType.STONE, SoundEvents.IRON_DOOR_CLOSE, SoundEvents.IRON_DOOR_OPEN, SoundEvents.IRON_TRAPDOOR_CLOSE, SoundEvents.IRON_TRAPDOOR_OPEN, SoundEvents.STONE_PRESSURE_PLATE_CLICK_OFF, SoundEvents.STONE_PRESSURE_PLATE_CLICK_ON, SoundEvents.STONE_BUTTON_CLICK_OFF, SoundEvents.STONE_BUTTON_CLICK_ON);
	public static final BlockSetType IROSTONE_SET = blockSet("irostone", true, SoundType.STONE, SoundEvents.IRON_DOOR_CLOSE, SoundEvents.IRON_DOOR_OPEN, SoundEvents.IRON_TRAPDOOR_CLOSE, SoundEvents.IRON_TRAPDOOR_OPEN, SoundEvents.STONE_PRESSURE_PLATE_CLICK_OFF, SoundEvents.STONE_PRESSURE_PLATE_CLICK_ON, SoundEvents.STONE_BUTTON_CLICK_OFF, SoundEvents.STONE_BUTTON_CLICK_ON);
	public static final BlockSetType PRECASIAN_STONE_SET = blockSet("precasian_stone", true, SoundType.STONE, SoundEvents.IRON_DOOR_CLOSE, SoundEvents.IRON_DOOR_OPEN, SoundEvents.IRON_TRAPDOOR_CLOSE, SoundEvents.IRON_TRAPDOOR_OPEN, SoundEvents.STONE_PRESSURE_PLATE_CLICK_OFF, SoundEvents.STONE_PRESSURE_PLATE_CLICK_ON, SoundEvents.STONE_BUTTON_CLICK_OFF, SoundEvents.STONE_BUTTON_CLICK_ON);
	public static final BlockSetType RUNIC_STONE_SET = blockSet("runic_stone", true, SoundType.STONE, SoundEvents.IRON_DOOR_CLOSE, SoundEvents.IRON_DOOR_OPEN, SoundEvents.IRON_TRAPDOOR_CLOSE, SoundEvents.IRON_TRAPDOOR_OPEN, SoundEvents.STONE_PRESSURE_PLATE_CLICK_OFF, SoundEvents.STONE_PRESSURE_PLATE_CLICK_ON, SoundEvents.STONE_BUTTON_CLICK_OFF, SoundEvents.STONE_BUTTON_CLICK_ON);

	public static final WoodType ACHONY_WOOD = wood("achony", ACHONY_SET);
	public static final WoodType BLOODWOOD_WOOD = wood("bloodwood", BLOODWOOD_SET);
	public static final WoodType CHURRY_WOOD = wood("churry", CHURRY_SET);
	public static final WoodType CREEP_WOOD = wood("creep", CREEP_SET);
	public static final WoodType DAWNWOOD_WOOD = wood("dawnwood", DAWNWOOD_SET);
	public static final WoodType HAUNTEDWOOD_WOOD = wood("hauntedwood", HAUNTEDWOOD_SET);
	public static final WoodType IROWOOD_WOOD = wood("irowood", IROWOOD_SET);
	public static final WoodType LUCALUS_WOOD = wood("lucalus", LUCALUS_SET);
	public static final WoodType LUNIDE_WOOD = wood("lunide", LUNIDE_SET);
	public static final WoodType RUNIC_WOOD = wood("runic", RUNIC_SET);
	public static final WoodType SHADOW_WOOD = wood("shadow", SHADOW_SET);
	public static final WoodType SHYRE_WOOD = wood("shyre", SHYRE_SET);
	public static final WoodType STRANGLEWOOD_WOOD = wood("stranglewood", STRANGLEWOOD_SET);
	public static final WoodType TOXICWOOD_WOOD = wood("toxicwood", TOXICWOOD_SET);

	private static WoodType wood(String id, BlockSetType blockSet) {
		return WoodType.register(new WoodType(AdventOfAscension.id(id).toString(), blockSet));
	}

	private static WoodType wood(String id, BlockSetType blockSet, SoundType soundType, SoundType hangingSign, SoundEvent fenceGateClose, SoundEvent fenceGateOpen) {
		return WoodType.register(new WoodType(AdventOfAscension.id(id).toString(), blockSet, soundType, hangingSign, fenceGateClose, fenceGateOpen));
	}

	private static BlockSetType blockSet(String id) {
		return BlockSetType.register(new BlockSetType(AdventOfAscension.id(id).toString()));
	}

	private static BlockSetType blockSet(String id, boolean canOpenByHand, SoundType soundType, SoundEvent doorClose, SoundEvent doorOpen, SoundEvent trapdoorClose, SoundEvent trapdoorOpen, SoundEvent pressurePlateClickOff, SoundEvent pressurePlateClickOn, SoundEvent buttonClickOff, SoundEvent buttonClickOn) {
		return BlockSetType.register(new BlockSetType(AdventOfAscension.id(id).toString(), canOpenByHand, soundType, doorClose, doorOpen, trapdoorClose, trapdoorOpen, pressurePlateClickOff, pressurePlateClickOn, buttonClickOff, buttonClickOn));
	}
}
