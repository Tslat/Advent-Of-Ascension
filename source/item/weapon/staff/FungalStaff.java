package net.tslat.aoa3.item.weapon.staff;

import net.minecraft.block.Block;
import net.minecraft.block.BlockMushroom;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.item.misc.RuneItem;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ItemUtil;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FungalStaff extends BaseStaff {
	public FungalStaff(int durability) {
		super(durability);
		setTranslationKey("FungalStaff");
		setRegistryName("aoa3:fungal_staff");
	}

	@Nullable
	@Override
	public SoundEvent getCastingSound() {
		return SoundsRegister.FUNGAL_STAFF_CAST;
	}

	@Override
	protected void populateRunes(HashMap<RuneItem, Integer> runes) {
		runes.put(ItemRegister.DISTORTION_RUNE, 5);
		runes.put(ItemRegister.LIFE_RUNE, 2);
	}

	@Nullable
	@Override
	public Object checkPreconditions(EntityLivingBase caster, ItemStack staff) {
		BlockPos.MutableBlockPos checkPos = new BlockPos.MutableBlockPos();
		HashMap<BlockPos, Boolean> workablePositions = new HashMap<BlockPos, Boolean>();

		for (int x = -2; x <= 2; x++) {
			for (int y = -2; y <= 2; y++) {
				for (int z = -2; z <= 2; z++) {
					Block block = caster.world.getBlockState(checkPos.setPos(caster.posX + x, caster.posY + y, caster.posZ + z)).getBlock();

					if (block == Blocks.GRASS) {
						workablePositions.put(checkPos.toImmutable(), true);
					}
					else if (block instanceof BlockMushroom) {
						workablePositions.put(checkPos.toImmutable(), false);
					}
				}
			}
		}

		return workablePositions.isEmpty() ? null : workablePositions;
	}

	@Override
	public void cast(World world, ItemStack staff, EntityLivingBase caster, Object args) {
		for (Map.Entry<BlockPos, Boolean> entry : ((HashMap<BlockPos, Boolean>)args).entrySet()) {
			if (entry.getValue()) {
				world.setBlockState(entry.getKey(), Blocks.MYCELIUM.getDefaultState());
			}
			else {
				BlockPos pos = entry.getKey();
				IBlockState state = world.getBlockState(pos);
				BlockMushroom mushroom = (BlockMushroom)state.getBlock();

				if (mushroom.canGrow(world, pos, state, false) && mushroom.canUseBonemeal(world, itemRand, pos, state))
					mushroom.grow(world, itemRand, pos, state);
			}

			world.playEvent(2005, entry.getKey(), 0);
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.FungalStaff.desc.1", Enums.ItemDescriptionType.POSITIVE));
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.FungalStaff.desc.2", Enums.ItemDescriptionType.POSITIVE));
		super.addInformation(stack, world, tooltip, flag);
	}
}
