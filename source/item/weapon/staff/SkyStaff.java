package net.tslat.aoa3.item.weapon.staff;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
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

public class SkyStaff extends BaseStaff {
	public SkyStaff(int durability) {
		super(durability);
		setTranslationKey("SkyStaff");
		setRegistryName("aoa3:sky_staff");
	}

	@Nullable
	@Override
	public SoundEvent getCastingSound() {
		return SoundsRegister.SKY_STAFF_CAST;
	}

	@Override
	protected void populateRunes(HashMap<RuneItem, Integer> runes) {
		runes.put(ItemRegister.WIND_RUNE, 2);
		runes.put(ItemRegister.KINETIC_RUNE, 2);
		runes.put(ItemRegister.ENERGY_RUNE, 1);
	}

	@Override
	public Object checkPreconditions(EntityLivingBase caster, ItemStack staff) {
		return caster.onGround ? new Object() : null;
	}

	@Override
	public void cast(World world, ItemStack staff, EntityLivingBase caster, Object args) {
		caster.setSprinting(true);
		double xMotion = -MathHelper.sin(caster.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(caster.rotationPitch / 180.0F * (float)Math.PI) * 2f;
		double zMotion = MathHelper.cos(caster.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(caster.rotationPitch / 180.0F * (float)Math.PI) * 2f;

		if (Math.abs(xMotion) < 0.4 && Math.abs(zMotion) < 0.4) {
			caster.motionY += 2f;
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
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.SkyStaff.desc.1", Enums.ItemDescriptionType.POSITIVE));
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.SkyStaff.desc.2", Enums.ItemDescriptionType.POSITIVE));
		super.addInformation(stack, world, tooltip, flag);
	}
}
