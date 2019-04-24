package net.tslat.aoa3.item.weapon.staff;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.item.misc.RuneItem;
import net.tslat.aoa3.utils.StringUtil;

import java.util.HashMap;
import java.util.List;

public class SkyStaff extends BaseStaff {
	private static HashMap<RuneItem, Integer> runes = new HashMap<RuneItem, Integer>();

	static {
		runes.put(ItemRegister.runeWind, 2);
		runes.put(ItemRegister.runeKinetic, 2);
		runes.put(ItemRegister.runeEnergy, 1);
	}

	public SkyStaff(SoundEvent sound, int durability) {
		super(sound, durability);
		setUnlocalizedName("SkyStaff");
		setRegistryName("aoa3:sky_staff");
	}

	@Override
	public Object checkPreconditions(EntityLivingBase caster, ItemStack staff) {
		return caster.onGround ? new Object() : null;
	}

	@Override
	public HashMap<RuneItem, Integer> getRunes() {
		return runes;
	}

	@Override
	public void cast(World world, ItemStack staff, EntityLivingBase caster, Object args) {
		caster.setSprinting(true);
		double xMotion = (double)(-MathHelper.sin(caster.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(caster.rotationPitch / 180.0F * (float)Math.PI) * 2f);
		double zMotion = (double)(MathHelper.cos(caster.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(caster.rotationPitch / 180.0F * (float)Math.PI) * 2f);

		if (Math.abs(xMotion) < 0.4 && Math.abs(zMotion) < 0.4) {
			caster.motionY += 1.2f;
		}
		else {
			caster.motionY += 0.75F;
		}

		caster.motionX = xMotion;
		caster.motionZ = zMotion;
		caster.velocityChanged = true;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(StringUtil.getColourLocaleString("item.SkyStaff.desc.1", TextFormatting.DARK_GREEN));
		tooltip.add(StringUtil.getColourLocaleString("item.SkyStaff.desc.2", TextFormatting.DARK_GREEN));
		super.addInformation(stack, world, tooltip, flag);
	}
}
