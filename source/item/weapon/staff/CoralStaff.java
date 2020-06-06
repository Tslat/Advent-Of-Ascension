package net.tslat.aoa3.item.weapon.staff;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.item.misc.RuneItem;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.library.scheduling.async.CoralStaffTask;
import net.tslat.aoa3.utils.ItemUtil;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CoralStaff extends BaseStaff {
	public CoralStaff(int durability) {
		super(durability);
		setTranslationKey("CoralStaff");
		setRegistryName("aoa3:coral_staff");
	}

	@Nullable
	@Override
	public SoundEvent getCastingSound() {
		return SoundsRegister.CORAL_STAFF_CAST;
	}

	@Override
	protected void populateRunes(HashMap<RuneItem, Integer> runes) {
		runes.put(ItemRegister.WATER_RUNE, 2);
		runes.put(ItemRegister.KINETIC_RUNE, 8);
	}

	@Override
	@Nullable
	public Object checkPreconditions(EntityLivingBase caster, ItemStack staff) {
		ArrayList<BlockPos> coralPositions = new ArrayList<BlockPos>();
		final BlockPos pos = caster.getPosition();
		BlockPos.MutableBlockPos checkPos = new BlockPos.MutableBlockPos();

		for (int x = -2; x <= 1; x++) {
			for (int z = -2; z <= 1; z++) {
				checkPos.setPos(pos.getX() + x, pos.getY() - 2, pos.getZ() + z);

				if (safeBlockPos(caster, checkPos))
					coralPositions.add(checkPos.toImmutable());

				if (safeBlockPos(caster, checkPos.move(EnumFacing.UP, 4)))
					coralPositions.add(checkPos.toImmutable());
			}

			for (int y = -2; y <= 2; y++) {
				checkPos.setPos(pos.getX() + x, pos.getY() + y, pos.getZ() - 2);

				if (safeBlockPos(caster, checkPos))
					coralPositions.add(checkPos.toImmutable());

				if (safeBlockPos(caster, checkPos.move(EnumFacing.SOUTH, 4)))
					coralPositions.add(checkPos.toImmutable());
			}
		}

		for (int z = -2; z <= 2; z++) {
			for (int y = -2; y <= 2; y++) {
				checkPos.setPos(pos.getX() - 2, pos.getY() + y, pos.getZ() + z);

				if (safeBlockPos(caster, checkPos))
					coralPositions.add(checkPos.toImmutable());

				if (safeBlockPos(caster, checkPos.move(EnumFacing.EAST, 4)))
					coralPositions.add(checkPos.toImmutable());
			}
		}

		return coralPositions.isEmpty() ? null : coralPositions;
	}

	@Override
	public void cast(World world, ItemStack staff, EntityLivingBase caster, Object args) {
		if (!world.isRemote && caster instanceof EntityPlayer) {
			for (BlockPos pos : (ArrayList<BlockPos>)args) {
				world.setBlockState(pos, BlockRegister.PINK_CORAL.getDefaultState(), 2);
			}

			world.playSound(null, caster.posX, caster.posY, caster.posZ, SoundsRegister.REEF_STAFF_CAST, SoundCategory.PLAYERS, 1.0f, 1.0f);
			new CoralStaffTask(world, (ArrayList<BlockPos>)args).schedule(30, TimeUnit.SECONDS);
		}
	}

	private boolean safeBlockPos(EntityLivingBase caster, BlockPos pos) {
		return caster.world.getBlockState(pos).getBlock() == Blocks.AIR && (!(caster instanceof EntityPlayer) || caster.world.provider.canMineBlock((EntityPlayer)caster, pos));
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.CoralStaff.desc.1", Enums.ItemDescriptionType.POSITIVE));
		super.addInformation(stack, world, tooltip, flag);
	}
}
