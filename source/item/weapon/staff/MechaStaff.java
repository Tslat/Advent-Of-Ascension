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
import net.tslat.aoa3.entity.projectiles.staff.BaseEnergyShot;
import net.tslat.aoa3.entity.projectiles.staff.EntityLyonicShot;
import net.tslat.aoa3.item.misc.RuneItem;
import net.tslat.aoa3.utils.EntityUtil;
import net.tslat.aoa3.utils.StringUtil;

import java.util.HashMap;
import java.util.List;

public class MechaStaff extends BaseStaff {
	private static HashMap<RuneItem, Integer> runes = new HashMap<RuneItem, Integer>();

	static {
		runes.put(ItemRegister.runeWind, 2);
		runes.put(ItemRegister.runeDistortion, 1);
		runes.put(ItemRegister.runeLife, 1);
	}

	public MechaStaff(SoundEvent sound, int durability) {
		super(sound, durability);
		setUnlocalizedName("MechaStaff");
		setRegistryName("aoa3:mecha_staff");
	}

	@Override
	public HashMap<RuneItem, Integer> getRunes() {
		return runes;
	}

	@Override
	public void cast(World world, ItemStack staff, EntityLivingBase caster, Object args) {
		world.spawnEntity(new EntityLyonicShot(caster, this, 60));
	}

	@Override
	public void doEntityImpact(BaseEnergyShot shot, Entity target, EntityLivingBase shooter) {
		if (itemRand.nextInt(5) == 0) {
			if (target instanceof EntityLivingBase) {
				EntityUtil.healEntity((EntityLivingBase)target, getDmg() * 0.25f);
				EntityUtil.dealSelfHarmDamage(shooter, getDmg() * 0.25f);
			}
		}
		else {
			EntityUtil.dealMagicDamage(shot, shooter, target, getDmg(), false);
		}
	}

	@Override
	public float getDmg() {
		return 25;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(StringUtil.getColourLocaleString("item.MechaStaff.desc.1", TextFormatting.DARK_GREEN));
		tooltip.add(StringUtil.getColourLocaleString("item.MechaStaff.desc.2", TextFormatting.DARK_GREEN));
		super.addInformation(stack, world, tooltip, flag);
	}
}
