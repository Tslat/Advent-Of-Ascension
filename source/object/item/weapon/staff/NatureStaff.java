package net.tslat.aoa3.object.item.weapon.staff;

import net.minecraft.block.IGrowable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.BoneMealItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.WorldUtil;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NatureStaff extends BaseStaff<ArrayList<BlockPos>> {
	public NatureStaff(int durability) {
		super(durability);
	}

	@Nullable
	@Override
	public SoundEvent getCastingSound() {
		return AoASounds.ITEM_NATURE_STAFF_CAST.get();
	}

	@Override
	public ArrayList<BlockPos> checkPreconditions(LivingEntity caster, ItemStack staff) {
		ArrayList<BlockPos> blocks = WorldUtil.getBlocksWithinAABB(caster.level, caster.getBoundingBox().inflate(10), (state, pos) -> {
			if (!(state.getBlock() instanceof IGrowable))
				return false;

			if (!((IGrowable)state.getBlock()).isValidBonemealTarget(caster.level, pos.immutable(), state, false))
				return false;

			return WorldUtil.canModifyBlock(caster.level, pos, caster, staff);
		});

		return blocks.isEmpty() ? null : blocks;
	}

	@Override
	protected void populateRunes(HashMap<Item, Integer> runes) {
		runes.put(AoAItems.LIFE_RUNE.get(), 4);
		runes.put(AoAItems.ENERGY_RUNE.get(), 2);
	}

	@Override
	public void cast(World world, ItemStack staff, LivingEntity caster, ArrayList<BlockPos> args) {
		for (BlockPos pos : args) {
			BoneMealItem.growCrop(new ItemStack(Items.BONE_MEAL), caster.level, pos);
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.appendHoverText(stack, world, tooltip, flag);
	}
}
