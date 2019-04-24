package net.tslat.aoa3.item.weapon.staff;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.item.misc.RuneItem;
import net.tslat.aoa3.utils.StringUtil;

import java.util.HashMap;
import java.util.List;

public class EmberStaff extends BaseStaff {
	private static HashMap<RuneItem, Integer> runes = new HashMap<RuneItem, Integer>();

	static {
		runes.put(ItemRegister.runeKinetic, 1);
		runes.put(ItemRegister.runeWind, 1);
		runes.put(ItemRegister.runeFire, 1);
	}

	public EmberStaff(SoundEvent sound, int durability) {
		super(sound, durability);
		setUnlocalizedName("EmberStaff");
		setRegistryName("aoa3:ember_staff");
	}

	@Override
	public HashMap<RuneItem, Integer> getRunes() {
		return runes;
	}

	@Override
	public void cast(World world, ItemStack staff, EntityLivingBase caster, Object args) {
		caster.extinguish();

		for (int x = (int)caster.posX - 2; x < caster.posX + 2; x++) {
			for (int y = (int)caster.posY - 2; y < caster.posY + 2; y++) {
				for (int z = (int)caster.posZ - 2; z < caster.posZ + 2; z++) {
					if (world.getBlockState(new BlockPos(x, y, z)).getBlock() == Blocks.FIRE)
						world.setBlockToAir(new BlockPos(x, y, z));
				}
			}
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(StringUtil.getColourLocaleString("item.EmberStaff.desc.1", TextFormatting.DARK_GREEN));
		super.addInformation(stack, world, tooltip, flag);
	}
}
