package net.tslat.aoa3.item.weapon.staff;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.projectiles.staff.BaseEnergyShot;
import net.tslat.aoa3.entity.projectiles.staff.EntityAquaticShot;
import net.tslat.aoa3.item.misc.RuneItem;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.EntityUtil;
import net.tslat.aoa3.utils.ItemUtil;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;

public class AquaticStaff extends BaseStaff {
	public AquaticStaff(int durability) {
		super(durability);
		setTranslationKey("AquaticStaff");
		setRegistryName("aoa3:aquatic_staff");
	}

	@Nullable
	@Override
	public SoundEvent getCastingSound() {
		return SoundsRegister.BASIC_STAFF_CAST;
	}

	@Override
	protected void populateRunes(HashMap<RuneItem, Integer> runes) {
		runes.put(ItemRegister.WIND_RUNE, 2);
		runes.put(ItemRegister.WATER_RUNE, 4);
		runes.put(ItemRegister.KINETIC_RUNE, 1);
	}

	@Override
	public void cast(World world, ItemStack staff, EntityLivingBase caster, Object args) {
		world.spawnEntity(new EntityAquaticShot(caster, this, 60));
	}

	@Override
	public boolean doEntityImpact(BaseEnergyShot shot, Entity target, EntityLivingBase shooter) {
		return EntityUtil.dealMagicDamage(shot, shooter, target, shooter.isInWater() ? getDmg() * 1.4f : getDmg(), false);
	}

	@Override
	public float getDmg() {
		return 10;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.AquaticStaff.desc.1", Enums.ItemDescriptionType.POSITIVE));
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.AquaticStaff.desc.2", Enums.ItemDescriptionType.POSITIVE));
		super.addInformation(stack, world, tooltip, flag);
	}
}
