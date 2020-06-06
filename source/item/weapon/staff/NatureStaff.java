package net.tslat.aoa3.item.weapon.staff;

import net.minecraft.block.IGrowable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.ItemDye;
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
import net.tslat.aoa3.utils.WorldUtil;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NatureStaff extends BaseStaff {
	public NatureStaff(int durability) {
		super(durability);
		setTranslationKey("NatureStaff");
		setRegistryName("aoa3:nature_staff");
	}

	@Nullable
	@Override
	public SoundEvent getCastingSound() {
		return SoundsRegister.NATURE_STAFF_CAST;
	}

	@Override
	public Object checkPreconditions(EntityLivingBase caster, ItemStack staff) {
		ArrayList<BlockPos> blocks = WorldUtil.getBlocksWithinAABB(caster.world, caster.getEntityBoundingBox().grow(10), (state, pos) -> state.getBlock() instanceof IGrowable && ((IGrowable)state.getBlock()).canGrow(caster.world, pos.toImmutable(), state, false));

		return blocks.isEmpty() ? null : blocks;
	}

	@Override
	protected void populateRunes(HashMap<RuneItem, Integer> runes) {
		runes.put(ItemRegister.LIFE_RUNE, 4);
		runes.put(ItemRegister.ENERGY_RUNE, 2);
	}

	@Override
	public void cast(World world, ItemStack staff, EntityLivingBase caster, Object args) {
		for (BlockPos pos : (ArrayList<BlockPos>)args) {
			ItemDye.applyBonemeal(new ItemStack(Items.DYE, 1, 15), caster.world, pos);
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.NatureStaff.desc.1", Enums.ItemDescriptionType.POSITIVE));
		super.addInformation(stack, world, tooltip, flag);
	}
}
