package net.tslat.aoa3.item.weapon.staff;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
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

public class EmberStaff extends BaseStaff {
	public EmberStaff(int durability) {
		super(durability);
		setTranslationKey("EmberStaff");
		setRegistryName("aoa3:ember_staff");
	}

	@Nullable
	@Override
	public SoundEvent getCastingSound() {
		return SoundsRegister.EMBER_STAFF_CAST;
	}

	@Override
	protected void populateRunes(HashMap<RuneItem, Integer> runes) {
		runes.put(ItemRegister.KINETIC_RUNE, 1);
		runes.put(ItemRegister.WIND_RUNE, 1);
		runes.put(ItemRegister.FIRE_RUNE, 1);
	}

	@Override
	public void cast(World world, ItemStack staff, EntityLivingBase caster, Object args) {
		caster.extinguish();

		for (EntityLivingBase entity : world.getEntitiesWithinAABB(EntityLivingBase.class, caster.getEntityBoundingBox().grow(5), entity -> entity instanceof EntityPlayer || entity instanceof EntityTameable)) {
			entity.extinguish();
		}

		for (int x = (int)caster.posX - 5; x < caster.posX + 5; x++) {
			for (int y = (int)caster.posY - 5; y < caster.posY + 5; y++) {
				for (int z = (int)caster.posZ - 5; z < caster.posZ + 5; z++) {
					if (world.getBlockState(new BlockPos(x, y, z)).getBlock() == Blocks.FIRE)
						world.setBlockToAir(new BlockPos(x, y, z));
				}
			}
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.EmberStaff.desc.1", Enums.ItemDescriptionType.POSITIVE));
		super.addInformation(stack, world, tooltip, flag);
	}
}
