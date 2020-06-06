package net.tslat.aoa3.item.weapon.staff;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.projectiles.staff.BaseEnergyShot;
import net.tslat.aoa3.entity.projectiles.staff.EntityWitherShot;
import net.tslat.aoa3.item.misc.RuneItem;
import net.tslat.aoa3.utils.EntityUtil;
import net.tslat.aoa3.utils.StringUtil;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;

public class UnderworldStaff extends BaseStaff {
	public UnderworldStaff(int durability) {
		super(durability);
		setTranslationKey("UnderworldStaff");
		setRegistryName("aoa3:underworld_staff");
	}

	@Nullable
	@Override
	public SoundEvent getCastingSound() {
		return SoundsRegister.BASIC_STAFF_CAST;
	}

	@Override
	protected void populateRunes(HashMap<RuneItem, Integer> runes) {
		runes.put(ItemRegister.COMPASS_RUNE, 1);
		runes.put(ItemRegister.KINETIC_RUNE, 3);
	}

	@Override
	public void cast(World world, ItemStack staff, EntityLivingBase caster, Object args) {
		world.spawnEntity(new EntityWitherShot(caster, this, 60));
	}

	@Override
	public boolean doEntityImpact(BaseEnergyShot shot, Entity target, EntityLivingBase shooter) {
		if (EntityUtil.dealMagicDamage(shot, shooter, target, getDmg(), false)) {
			target.addVelocity(0, -3.0f, 0);

			return true;
		}

		return false;
	}

	@Override
	public float getDmg() {
		return 5;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(StringUtil.getColourLocaleString("item.UnderworldStaff.desc.1", TextFormatting.DARK_GREEN));
		super.addInformation(stack, world, tooltip, flag);
	}
}
