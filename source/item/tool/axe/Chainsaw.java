package net.tslat.aoa3.item.tool.axe;

import net.minecraft.block.Block;
import net.minecraft.block.LogBlock;
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
import net.tslat.aoa3.common.registration.AoAItemGroups;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.util.ItemUtil;

public class Chainsaw extends AxeItem {
	public Chainsaw() {
		super(ItemUtil.customItemTier(2500, 18.0f, 4.0f, 2, 0, null), 4.0f, -2F, new Properties().maxDamage(2500).group(AoAItemGroups.TOOLS));
	}

	@Override
	public boolean onEntitySwing(ItemStack stack, LivingEntity entity) {
		return true;
	}

	@Override
	public boolean onBlockStartBreak(ItemStack itemstack, BlockPos pos, PlayerEntity player) {
		if (!player.world.isRemote) {
			player.world.playSound(null, player.getPosX(), player.getPosY(), player.getPosZ(), AoASounds.ITEM_CHAINSAW_USE.get(), SoundCategory.PLAYERS, 1.0f, 1.0f);

			Block block = player.world.getBlockState(pos).getBlock();

			if (block instanceof LogBlock || block.isIn(BlockTags.LOGS))
				player.addPotionEffect(new EffectInstance(Effects.HASTE, 10, 30, true, false));
		}

		return false;
	}

	@Override
	public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		if (!attacker.world.isRemote) {
			attacker.world.playSound(null, attacker.getPosX(), attacker.getPosY(), attacker.getPosZ(), AoASounds.ITEM_CHAINSAW_USE.get(), SoundCategory.PLAYERS, 1.0f, 1.0f);
			ItemUtil.damageItem(stack, attacker, 1, EquipmentSlotType.MAINHAND);
		}

		return true;
	}

	@Override
	public boolean canDisableShield(ItemStack stack, ItemStack shield, LivingEntity entity, LivingEntity attacker) {
		return false;
	}

	@Override
	public UseAction getUseAction(ItemStack stack) {
		return UseAction.NONE;
	}
}
