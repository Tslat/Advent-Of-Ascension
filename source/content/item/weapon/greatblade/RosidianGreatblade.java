package net.tslat.aoa3.content.item.weapon.greatblade;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.IShearable;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.tme.api.util.RandomUtil;

import java.util.List;

public class RosidianGreatblade extends AoAGreatblade {
	public RosidianGreatblade(Tier tier, Item.Properties properties) {
		super(tier, properties);
	}

	@Override
	public boolean mineBlock(ItemStack stack, Level level, BlockState state, BlockPos pos, LivingEntity entity) {
		if (entity.level().isClientSide)
			return false;

		Player player = entity instanceof Player pl ? pl : null;

		if (player != null && player.getAbilities().instabuild)
			return false;

		Block block = entity.level().getBlockState(pos).getBlock();

		if (block instanceof IShearable shearable) {
			if (shearable.isShearable(player, stack, entity.level(), pos)) {
				for (int x = pos.getX() - 1; x <= pos.getX() + 1; x++) {
					for (int y = pos.getY() - 1; y <= pos.getY() + 1; y++) {
						for (int z = pos.getZ() - 1; z <= pos.getZ() + 1; z++) {
							BlockPos newPos = new BlockPos(x, y, z);
							Block newBlock = entity.level().getBlockState(newPos).getBlock();

							if (!(newBlock instanceof IShearable newShearable) || !newShearable.isShearable(player, stack, entity.level(), newPos))
								continue;

							if (player != null) {
								for (ItemStack drop : shearable.onSheared(player, stack, entity.level(), newPos)) {
									double xMod = RandomUtil.valueBetween(0.15f, 0.85f);
									double yMod = RandomUtil.valueBetween(0.15f, 0.85f);
									double zMod = RandomUtil.valueBetween(0.15f, 0.85f);
									ItemEntity item = new ItemEntity(entity.level(), x + xMod, y + yMod, z + zMod, drop);

									item.setDefaultPickUpDelay();
									entity.level().addFreshEntity(item);
								}
							}

							ItemUtil.damageItemForUser((ServerLevel)entity.level(), stack, entity, EquipmentSlot.MAINHAND);

							if (player != null)
								player.awardStat(Stats.BLOCK_MINED.get(newBlock));

							entity.level().setBlock(newPos, Blocks.AIR.defaultBlockState(), 11);
						}
					}
				}

				return true;
			}
		}

		return false;
	}

	@Override
	public boolean canAttackBlock(BlockState state, Level world, BlockPos pos, Player player) {
		return super.canAttackBlock(state, world, pos, player) || world.getBlockState(pos) instanceof IShearable;
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag tooltipFlag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
	}
}
