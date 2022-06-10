package net.tslat.aoa3.player.ability;

import com.google.gson.JsonObject;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.tslat.aoa3.common.registration.custom.AoAAbilities;
import net.tslat.aoa3.player.skill.AoASkill;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.WorldUtil;

public class HoeAreaHarvest extends AoAAbility.Instance {
	private static final ListenerType[] LISTENERS = new ListenerType[] {ListenerType.BLOCK_INTERACT};

	private final int baseRadius;
	private final int levelsPerRadiusIncrease;
	private final int perBlockHoeDamage;

	public HoeAreaHarvest(AoASkill.Instance skill, JsonObject data) {
		super(AoAAbilities.HOE_AREA_HARVEST.get(), skill, data);

		this.baseRadius = GsonHelper.getAsInt(data, "base_radius", 1);
		this.levelsPerRadiusIncrease = GsonHelper.getAsInt(data, "levels_per_radius_increase", 0);
		this.perBlockHoeDamage = GsonHelper.getAsInt(data, "per_block_hoe_damage", 1);

		if (this.baseRadius < 0)
			throw new IllegalArgumentException("Invalid radius value for BlockConversion ability: '" + this.baseRadius + "'. Must be at least 0");
	}

	public HoeAreaHarvest(AoASkill.Instance skill, CompoundTag data) {
		super(AoAAbilities.HOE_AREA_HARVEST.get(), skill, data);

		this.baseRadius = data.getInt("base_radius");
		this.levelsPerRadiusIncrease = data.getInt("levels_per_radius_increase");
		this.perBlockHoeDamage = data.getInt("per_block_hoe_damage");
	}

	@Override
	protected void updateDescription(MutableComponent defaultDescription) {
		String suffix = this.levelsPerRadiusIncrease > 0 ? "" : ".flat";
		suffix += this.perBlockHoeDamage <= 0 ? "" : ".noDamage";

		super.updateDescription(Component.translatable(((TranslatableContents)defaultDescription.getContents()).getKey() + suffix, this.baseRadius, this.levelsPerRadiusIncrease, this.perBlockHoeDamage));
	}

	@Override
	public ListenerType[] getListenerTypes() {
		return LISTENERS;
	}

	@Override
	public void handleBlockInteraction(PlayerInteractEvent.RightClickBlock ev) {
		if (ev.getWorld().getBlockState(ev.getPos()).is(BlockTags.CROPS)) {
			Player player = ev.getPlayer();
			ItemStack heldStack = player.getItemInHand(ev.getHand());

			if (heldStack.getItem() instanceof HoeItem) {
				int radius = this.levelsPerRadiusIncrease > 0 ? this.baseRadius + ((skill.getLevel(false) - getLevelReq()) / this.levelsPerRadiusIncrease) : this.baseRadius;
				Level world = ev.getWorld();
				BlockPos basePos = ev.getPos();
				BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();

				for (int x = -radius; x <= radius; x++) {
					for (int z = -radius; z <= radius; z++) {
						BlockState state = world.getBlockState(pos.set(basePos.getX() + x, basePos.getY(), basePos.getZ() + z));

						if (state.getBlock() instanceof CropBlock crop) {
							if (crop.isMaxAge(state) && WorldUtil.canModifyBlock(world, pos, player, heldStack)) {
								WorldUtil.harvestAdditionalBlock(world, ev.getPlayer(), pos, true);

								if (this.perBlockHoeDamage > 0) {
									ItemUtil.damageItem(heldStack, player, ev.getHand(), this.perBlockHoeDamage);

									if (heldStack.isEmpty())
										return;
								}
							}
						}
					}
				}
			}
		}
	}

	@Override
	public CompoundTag getSyncData(boolean forClientSetup) {
		CompoundTag data = super.getSyncData(forClientSetup);

		if (forClientSetup) {
			data.putInt("base_radius", this.baseRadius);
			data.putInt("levels_per_radius_increase", this.levelsPerRadiusIncrease);
			data.putInt("per_block_hoe_damage", this.perBlockHoeDamage);
		}

		return data;
	}
}
