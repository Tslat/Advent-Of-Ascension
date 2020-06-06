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

public class ReefStaff extends BaseStaff {
	public ReefStaff(int durability) {
		super(durability);
		setTranslationKey("ReefStaff");
		setRegistryName("aoa3:reef_staff");
	}

	@Nullable
	@Override
	public SoundEvent getCastingSound() {
		return SoundsRegister.REEF_STAFF_CAST;
	}

	@Override
	protected void populateRunes(HashMap<RuneItem, Integer> runes) {
		runes.put(ItemRegister.ENERGY_RUNE, 2);
		runes.put(ItemRegister.WATER_RUNE, 1);
	}

	@Nullable
	@Override
	public Object checkPreconditions(EntityLivingBase caster, ItemStack staff) {
		return caster.isInWater() ? true : null;
	}

	@Override
	public void cast(World world, ItemStack staff, EntityLivingBase caster, Object args) {
		float velocityX = -MathHelper.sin(caster.rotationYaw * (float)Math.PI / 180f) * MathHelper.cos(caster.rotationPitch * (float)Math.PI / 180f);
		float velocityY = -MathHelper.sin(caster.rotationPitch * (float)Math.PI / 180f);
		float velocityZ = MathHelper.cos(caster.rotationYaw * (float)Math.PI / 180f) * MathHelper.cos(caster.rotationPitch * (float)Math.PI / 180f);

		caster.motionX = velocityX * 3;
		caster.motionY = velocityY * 3;
		caster.motionZ = velocityZ * 3;
		caster.velocityChanged = true;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.ReefStaff.desc.1", Enums.ItemDescriptionType.POSITIVE));
		super.addInformation(stack, world, tooltip, flag);
	}
}
