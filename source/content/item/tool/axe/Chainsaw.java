package net.tslat.aoa3.content.item.tool.axe;

import net.minecraft.block.Block;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.tslat.aoa3.content.block.generation.log.LogBlock;
import net.tslat.aoa3.common.registration.AoAItemGroups;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.util.ItemUtil;

public class Chainsaw extends AxeItem {
	public Chainsaw() {
		super(ItemUtil.customItemTier(2500, 18.0f, 4.0f, 2, 0, null), 4.0f, -2F, new Properties().durability(2500).tab(AoAItemGroups.TOOLS));
	}

	@Override
	public boolean onEntitySwing(ItemStack stack, LivingEntity entity) {
		return true;
	}

	@Override
	public boolean onBlockStartBreak(ItemStack itemstack, BlockPos pos, PlayerEntity player) {
		if (!player.level.isClientSide) {
			player.level.playSound(null, player.getX(), player.getY(), player.getZ(), AoASounds.ITEM_CHAINSAW_USE.get(), SoundCategory.PLAYERS, 1.0f, 1.0f);

			Block block = player.level.getBlockState(pos).getBlock();

			if (block instanceof LogBlock || block.is(BlockTags.LOGS))
				player.addEffect(new EffectInstance(Effects.DIG_SPEED, 10, 30, true, false));
		}

		return false;
	}

	@Override
	public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		if (!attacker.level.isClientSide) {
			attacker.level.playSound(null, attacker.getX(), attacker.getY(), attacker.getZ(), AoASounds.ITEM_CHAINSAW_USE.get(), SoundCategory.PLAYERS, 1.0f, 1.0f);
			ItemUtil.damageItem(stack, attacker, 1, EquipmentSlotType.MAINHAND);
		}

		return true;
	}

	@Override
	public boolean canDisableShield(ItemStack stack, ItemStack shield, LivingEntity entity, LivingEntity attacker) {
		return false;
	}

	@Override
	public UseAction getUseAnimation(ItemStack stack) {
		return UseAction.NONE;
	}
}
