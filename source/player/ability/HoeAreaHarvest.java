package net.tslat.aoa3.player.ability;

import com.google.gson.JsonObject;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropsBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
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

		this.baseRadius = JSONUtils.getAsInt(data, "base_radius", 1);
		this.levelsPerRadiusIncrease = JSONUtils.getAsInt(data, "levels_per_radius_increase", 0);
		this.perBlockHoeDamage = JSONUtils.getAsInt(data, "per_block_hoe_damage", 1);

		if (this.baseRadius < 0)
			throw new IllegalArgumentException("Invalid radius value for BlockConversion ability: '" + this.baseRadius + "'. Must be at least 0");
	}

	public HoeAreaHarvest(AoASkill.Instance skill, CompoundNBT data) {
		super(AoAAbilities.BLOCK_CONVERSION.get(), skill, data);

		this.baseRadius = data.getInt("base_radius");
		this.levelsPerRadiusIncrease = data.getInt("levels_per_radius_increase");
		this.perBlockHoeDamage = data.getInt("per_block_hoe_damage");
	}

	@Override
	protected void updateDescription(TranslationTextComponent defaultDescription) {
		String suffix = this.levelsPerRadiusIncrease > 0 ? "" : ".flat";
		suffix += this.perBlockHoeDamage <= 0 ? "" : ".noDamage";

		super.updateDescription(new TranslationTextComponent(defaultDescription.getKey() + suffix, this.baseRadius, this.levelsPerRadiusIncrease, this.perBlockHoeDamage));
	}

	@Override
	public ListenerType[] getListenerTypes() {
		return LISTENERS;
	}

	@Override
	public void handleBlockInteraction(PlayerInteractEvent.RightClickBlock ev) {
		if (ev.getWorld().getBlockState(ev.getPos()).getBlock().is(BlockTags.CROPS)) {
			PlayerEntity player = ev.getPlayer();
			ItemStack heldStack = player.getItemInHand(ev.getHand());

			if (heldStack.getItem().getToolTypes(heldStack).contains(ToolType.HOE)) {
				int radius = this.levelsPerRadiusIncrease > 0 ? this.baseRadius + ((skill.getLevel(false) - getLevelReq()) / this.levelsPerRadiusIncrease) : this.baseRadius;
				World world = ev.getWorld();
				BlockPos basePos = ev.getPos();
				BlockPos.Mutable pos = new BlockPos.Mutable();

				for (int x = -radius; x <= radius; x++) {
					for (int z = -radius; z <= radius; z++) {
						BlockState state = world.getBlockState(pos.set(basePos.getX() + x, basePos.getY(), basePos.getZ() + z));

						if (state.getBlock() instanceof CropsBlock) {
							CropsBlock crop = (CropsBlock)state.getBlock();

							if (crop.isMaxAge(state) && WorldUtil.canModifyBlock(world, pos, player, heldStack)) {
								WorldUtil.harvestAdditionalBlock(world, ev.getPlayer(), pos);

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
	public CompoundNBT getSyncData(boolean forClientSetup) {
		CompoundNBT data = super.getSyncData(forClientSetup);

		if (forClientSetup) {
			data.putInt("base_radius", this.baseRadius);
			data.putInt("levels_per_radius_increase", this.levelsPerRadiusIncrease);
			data.putInt("per_block_hoe_damage", this.perBlockHoeDamage);
		}

		return data;
	}
}
