package net.tslat.aoa3.client.model.entity.npc;

import net.minecraft.resources.ResourceLocation;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.content.entity.npc.ambient.DryadSpriteEntity;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

public class DryadSpriteModel extends DefaultedEntityGeoModel<DryadSpriteEntity> {
	private static final ResourceLocation WOOD_TEXTURE = AdventOfAscension.id("textures/entity/npc/ambient/dryad_sprite_wood.png");
	private static final ResourceLocation STONE_TEXTURE = AdventOfAscension.id("textures/entity/npc/ambient/dryad_sprite_stone.png");
	private static final ResourceLocation IRON_TEXTURE = AdventOfAscension.id("textures/entity/npc/ambient/dryad_sprite_iron.png");
	private static final ResourceLocation GOLD_TEXTURE = AdventOfAscension.id("textures/entity/npc/ambient/dryad_sprite_gold.png");
	private static final ResourceLocation DIAMOND_TEXTURE = AdventOfAscension.id("textures/entity/npc/ambient/dryad_sprite_diamond.png");
	private static final ResourceLocation NETHERITE_TEXTURE = AdventOfAscension.id("textures/entity/npc/ambient/dryad_sprite_netherite.png");

	public DryadSpriteModel() {
		super(AdventOfAscension.id("npc/ambient/dryad_sprite_wood"));

		withAltAnimations(AdventOfAscension.id("npc/ambient/dryad_sprite"));
		withAltModel(AdventOfAscension.id("npc/ambient/dryad_sprite"));
	}

	@Override
	public ResourceLocation getTextureResource(DryadSpriteEntity entity) {
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
