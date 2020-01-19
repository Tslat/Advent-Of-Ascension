package net.tslat.aoa3.item.weapon.gun;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.item.weapon.AdventWeapon;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ItemUtil;
import net.tslat.aoa3.utils.StringUtil;

import javax.annotation.Nullable;
import java.util.List;

public class LunarAssaultRifle extends BaseGun implements AdventWeapon {
	private final double baseDmg;
	private final double maxDmg;
	private final int firingDelay;

	public LunarAssaultRifle(double dmg, int durability, int firingDelayTicks, float recoil) {
		super(dmg, durability, firingDelayTicks, recoil);
		setTranslationKey("LunarAssaultRifle");
		setRegistryName("aoa3:lunar_assault_rifle");

		this.firingDelay = firingDelayTicks;
		this.baseDmg = dmg - (dmg / 2d);
		this.maxDmg = dmg + (dmg / 2d);
	}

	@Nullable
	@Override
	public SoundEvent getFiringSound() {
		return SoundsRegister.gunSpaceGun;
	}

	@Override
	public double getDamage() {
		return itemRand.nextFloat() * (maxDmg - baseDmg) + baseDmg;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(ItemUtil.getFormattedDescriptionText("items.description.damage.random", Enums.ItemDescriptionType.ITEM_DAMAGE, Double.toString(baseDmg), Double.toString(maxDmg)));
		tooltip.add(StringUtil.getLocaleStringWithArguments("items.description.gun.speed", Double.toString((2000 / firingDelay) / (double)100)));
		tooltip.add(ItemUtil.getFormattedDescriptionText("items.description.ammo.bullets", Enums.ItemDescriptionType.ITEM_AMMO_COST));
	}
}
