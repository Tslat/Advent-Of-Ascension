package net.tslat.aoa3.item.weapon.staff;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.item.misc.RuneItem;
import net.tslat.aoa3.library.scheduling.ReefStaffTask;
import net.tslat.aoa3.utils.PlayerUtil;
import net.tslat.aoa3.utils.StringUtil;
import net.tslat.aoa3.utils.WorldUtil;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ReefStaff extends BaseStaff {
	private static HashMap<RuneItem, Integer> runes = new HashMap<RuneItem, Integer>();

	static {
		runes.put(ItemRegister.runeKinetic, 10);
	}

	public ReefStaff(SoundEvent sound, int durability) {
		super(sound, durability);
		setUnlocalizedName("ReefStaff");
		setRegistryName("aoa3:reef_staff");
	}

	@Override
	public HashMap<RuneItem, Integer> getRunes() {
		return runes;
	}

	@Override
	public void cast(World world, ItemStack staff, EntityLivingBase caster, Object args) {
		if (!world.isRemote && !WorldUtil.isBlockProtectedWorld(world.provider.getDimension())) {
			BlockPos basePos = caster.getPosition();

			for (int x = -2; x < 2; x++) {
				for (int z = -2; z < 2; z++) {
					if (world.getBlockState(basePos.add(x, -2, z)).getMaterial().isReplaceable())
						world.setBlockState(basePos.add(x, -2, z), BlockRegister.coralOrange.getDefaultState());

					if (world.getBlockState(basePos.add(x, 2, z)).getMaterial().isReplaceable())
						world.setBlockState(basePos.add(x, 2, z), BlockRegister.coralOrange.getDefaultState());
				}

				for (int y = -2; y < 3; y++) {
					if (world.getBlockState(basePos.add(x, y, -2)).getMaterial().isReplaceable())
						world.setBlockState(basePos.add(x, y, -2), BlockRegister.coralOrange.getDefaultState());

					if (world.getBlockState(basePos.add(x, y, 2)).getMaterial().isReplaceable())
						world.setBlockState(basePos.add(x, y, 2), BlockRegister.coralOrange.getDefaultState());
				}
			}

			for (int z = -2; z < 3; z++) {
				for (int y = -2; y < 3; y++) {
					if (world.getBlockState(basePos.add(-2, y, z)).getMaterial().isReplaceable())
						world.setBlockState(basePos.add(-2, y, z), BlockRegister.coralOrange.getDefaultState());

					if (world.getBlockState(basePos.add(2, y, z)).getMaterial().isReplaceable())
						world.setBlockState(basePos.add(2, y, z), BlockRegister.coralOrange.getDefaultState());
				}
			}

			new ReefStaffTask(world, caster.getPosition()).schedule(1, TimeUnit.MINUTES);
			caster.world.playSound(null, caster.posX, caster.posY, caster.posZ, SoundsRegister.staffReef, SoundCategory.PLAYERS, 1.0f, 1.0f);
		}
		else {
			if (caster instanceof EntityPlayer)
				PlayerUtil.getAdventPlayer((EntityPlayer)caster).sendPlayerMessage(StringUtil.getLocale("message.feedback.item.reefStaff.fail"));
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(StringUtil.getColourLocaleString("item.ReefStaff.desc.1", TextFormatting.DARK_GREEN));
		super.addInformation(stack, world, tooltip, flag);
	}
}
