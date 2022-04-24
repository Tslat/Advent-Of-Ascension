package net.tslat.aoa3.client.model.entity.npc;

import net.minecraft.resources.ResourceLocation;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.client.model.entity.EntityGeoModel;
import net.tslat.aoa3.content.entity.npc.ambient.DryadSpriteEntity;

public class DryadSpriteModel extends EntityGeoModel<DryadSpriteEntity> {
	private static final ResourceLocation WOOD_TEXTURE = AdventOfAscension.id("textures/entity/npc/ambient/dryad_sprite_wood.png");
	private static final ResourceLocation STONE_TEXTURE = AdventOfAscension.id("textures/entity/npc/ambient/dryad_sprite_stone.png");
	private static final ResourceLocation IRON_TEXTURE = AdventOfAscension.id("textures/entity/npc/ambient/dryad_sprite_iron.png");
	private static final ResourceLocation GOLD_TEXTURE = AdventOfAscension.id("textures/entity/npc/ambient/dryad_sprite_gold.png");
	private static final ResourceLocation DIAMOND_TEXTURE = AdventOfAscension.id("textures/entity/npc/ambient/dryad_sprite_diamond.png");
	private static final ResourceLocation NETHERITE_TEXTURE = AdventOfAscension.id("textures/entity/npc/ambient/dryad_sprite_netherite.png");

	public DryadSpriteModel() {
		super("npc/ambient/dryad_sprite_wood");

		withAnimations("npc/ambient/dryad_sprite");
		withModel("npc/ambient/dryad_sprite");
	}

	@Override
	public void setLivingAnimations(DryadSpriteEntity entity, Integer uniqueID) {
		super.setLivingAnimations(entity, uniqueID);
	}

	@Override
	public ResourceLocation getTextureLocation(DryadSpriteEntity entity) {
		return switch (entity.getVariant()) {
			case STONE -> STONE_TEXTURE;
			case IRON -> IRON_TEXTURE;
			case GOLD -> GOLD_TEXTURE;
			case DIAMOND -> DIAMOND_TEXTURE;
			case NETHERITE -> NETHERITE_TEXTURE;
			default -> WOOD_TEXTURE;
		};
	}
}
