package net.tslat.aoa3.player.ability;

import com.google.gson.JsonObject;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.tslat.aoa3.common.registration.custom.AoAAbilities;
import net.tslat.aoa3.player.skill.AoASkill;
import net.tslat.aoa3.util.WorldUtil;

import javax.annotation.Nullable;

public class BlockConversion extends AoAAbility.Instance {
	private static final ListenerType[] LISTENERS = new ListenerType[] {ListenerType.BLOCK_INTERACT};

	private final int radius;
	private final Block targetBlock;
	private final Block replacementBlock;
	@Nullable
	private final Item interactionItem;

	public BlockConversion(AoASkill.Instance skill, JsonObject data) {
		super(AoAAbilities.BLOCK_CONVERSION.get(), skill, data);

		this.radius = JSONUtils.getAsInt(data, "radius", 1);
		this.targetBlock = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(JSONUtils.getAsString(data, "target_block")));
		this.replacementBlock = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(JSONUtils.getAsString(data, "replacement_block")));

		if (data.has("interaction_item")) {
			this.interactionItem = ForgeRegistries.ITEMS.getValue(new ResourceLocation(JSONUtils.getAsString(data, "interaction_item")));
		}
		else {
			this.interactionItem = null;
		}

		if (this.radius < 0)
			throw new IllegalArgumentException("Invalid radius value for BlockConversion ability: '" + this.radius + "'. Must be at least 0");
	}

	public BlockConversion(AoASkill.Instance skill, CompoundNBT data) {
		super(AoAAbilities.BLOCK_CONVERSION.get(), skill, data);

		this.radius = data.getInt("radius");
		this.targetBlock = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(data.getString("target_block")));
		this.replacementBlock = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(data.getString("replacement_block")));

		if (data.contains("interaction_item")) {
			this.interactionItem = ForgeRegistries.ITEMS.getValue(new ResourceLocation(data.getString("replacement_block")));
		}
		else {
			this.interactionItem = null;
		}
	}

	@Override
	protected void updateDescription(TranslationTextComponent defaultDescription) {
		TranslationTextComponent description;
		String suffix = radius > 0 ? "" : ".single";

		if (this.interactionItem != null) {
			description = new TranslationTextComponent(defaultDescription.getKey() + suffix, this.radius, this.targetBlock.getName(), this.replacementBlock.getName(), this.interactionItem.getName(this.interactionItem.getDefaultInstance()));
		}
		else {
			description = new TranslationTextComponent(defaultDescription.getKey() + ".noItem" + suffix, this.radius, this.targetBlock.getName(), this.replacementBlock.getName());
		}

		super.updateDescription(description);
	}

	@Override
	public ListenerType[] getListenerTypes() {
		return LISTENERS;
	}

	@Override
	public void handleBlockInteraction(PlayerInteractEvent.RightClickBlock ev) {
		if (ev.getWorld().getBlockState(ev.getPos()).getBlock() == this.targetBlock) {
			ItemStack heldStack = ev.getPlayer().getItemInHand(ev.getHand());

			if (this.interactionItem == null || heldStack.getItem() == this.interactionItem) {
				World world = ev.getWorld();
				BlockPos pos = ev.getPos();
				BlockPos.Mutable mutablePos = new BlockPos.Mutable();
				BlockState newState = replacementBlock.defaultBlockState();
				boolean success = false;

				switch (ev.getHitVec().getDirection()) {
					case EAST:
					case WEST:
						for (int y = -radius; y <= radius; y++) {
							for (int z = -radius; z <= radius; z++) {
								mutablePos.set(pos.getX(), pos.getY() + y, pos.getZ() + z);

								if (world.getBlockState(mutablePos).getBlock() == targetBlock && WorldUtil.canModifyBlock(world, mutablePos, ev.getPlayer(), heldStack)) {
									world.setBlockAndUpdate(mutablePos, newState);

									success = true;
								}
							}
						}
						break;
					case NORTH:
					case SOUTH:
						for (int y = -radius; y <= radius; y++) {
							for (int x = -radius; x <= radius; x++) {
								mutablePos.set(pos.getX() + x, pos.getY() + y, pos.getZ());

								if (world.getBlockState(mutablePos).getBlock() == targetBlock && WorldUtil.canModifyBlock(world, mutablePos, ev.getPlayer(), heldStack)) {
									world.setBlockAndUpdate(mutablePos, newState);

									success = true;
								}
							}
						}
						break;
					case UP:
					case DOWN:
						for (int x = -radius; x <= radius; x++) {
							for (int z = -radius; z <= radius; z++) {
								mutablePos.set(pos.getX() + x, pos.getY(), pos.getZ() + z);

								if (world.getBlockState(mutablePos).getBlock() == targetBlock && WorldUtil.canModifyBlock(world, mutablePos, ev.getPlayer(), heldStack)) {
									world.setBlockAndUpdate(mutablePos, newState);

									success = true;
								}
							}
						}
						break;
				}

				if (success && this.interactionItem != null)
					heldStack.shrink(1);
			}
		}
	}

	@Override
	public CompoundNBT getSyncData(boolean forClientSetup) {
		CompoundNBT data = super.getSyncData(forClientSetup);

		if (forClientSetup) {
			data.putInt("radius", this.radius);
			data.putString("target_block", this.targetBlock.getRegistryName().toString());
			data.putString("replacement_block", this.replacementBlock.getRegistryName().toString());

			if (this.interactionItem != null)
				data.putString("interaction_item", this.interactionItem.getRegistryName().toString());
		}

		return data;
	}
}
