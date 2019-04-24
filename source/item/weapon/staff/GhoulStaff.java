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
import net.tslat.aoa3.entity.projectiles.staff.EntityGhoulShot;
import net.tslat.aoa3.item.misc.RuneItem;
import net.tslat.aoa3.utils.EntityUtil;
import net.tslat.aoa3.utils.StringUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GhoulStaff extends BaseStaff {
	private static HashMap<RuneItem, Integer> runes = new HashMap<RuneItem, Integer>();

	static {
		runes.put(ItemRegister.runeWind, 2);
		runes.put(ItemRegister.runeDistortion, 1);
		runes.put(ItemRegister.runeLife, 2);
	}

	public GhoulStaff(SoundEvent sound, int durability) {
		super(sound, durability);
		setUnlocalizedName("GhoulStaff");
		setRegistryName("aoa3:ghoul_staff");
	}

	@Override
	public HashMap<RuneItem, Integer> getRunes() {
		return runes;
	}

	@Override
	public void cast(World world, ItemStack staff, EntityLivingBase caster, Object args) {
		world.spawnEntity(new EntityGhoulShot(caster, this, 60));
	}

	@Override
	public void doEntityImpact(BaseEnergyShot shot, Entity target, EntityLivingBase shooter) {
		if (target instanceof EntityLivingBase) {
			float damage = this.getDmg() + itemRand.nextFloat() * 27;

			EntityUtil.dealMagicDamage(shot, shooter, target, damage, false);
			EntityUtil.dealSelfHarmDamage(shooter, damage / (this.getDmg() - 4));
		}
	}

	@Override
	public float getDmg() {
		return 13;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(StringUtil.getColourLocaleStringWithArguments("items.description.damage.random", TextFormatting.DARK_GREEN, "13", "40"));
		tooltip.add(StringUtil.getColourLocaleString("item.GhoulStaff.desc.1", TextFormatting.DARK_GREEN));
		tooltip.add(StringUtil.getColourLocaleString("items.description.staff.runesRequired", TextFormatting.LIGHT_PURPLE));

		for (Map.Entry<RuneItem, Integer> runeEntry : getRunes().entrySet()) {
			tooltip.add(StringUtil.getColourLocaleStringWithArguments("items.description.staff.runesRequired.specific", TextFormatting.WHITE, Integer.toString(runeEntry.getValue()), StringUtil.getLocaleString(runeEntry.getKey().getUnlocalizedName() + ".name")));
		}
	}
}
