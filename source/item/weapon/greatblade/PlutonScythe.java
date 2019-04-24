package net.tslat.aoa3.item.weapon.greatblade;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.capabilities.providers.AdventPlayerProvider;
import net.tslat.aoa3.item.weapon.AdventWeapon;
import net.tslat.aoa3.item.weapon.LongReachWeapon;
import net.tslat.aoa3.utils.EntityUtil;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.StringUtil;

import java.util.List;

public class PlutonScythe extends BaseGreatblade implements AdventWeapon, LongReachWeapon {
	private final double baseDmg;
	private final double maxDmg = 40;

	public PlutonScythe(double dmg, double speed, int durability) {
		super(dmg, speed, durability);
		setUnlocalizedName("PlutonScythe");
		setRegistryName("aoa3:pluton_scythe");
		this.baseDmg = dmg;
	}

	@Override
	public void attackEntity(ItemStack stack, Entity target, EntityLivingBase attacker, float dmg) {
		super.attackEntity(stack, target, attacker, (float)baseDmg + itemRand.nextInt(26));

		if (attacker instanceof EntityPlayer && !attacker.getCapability(AdventPlayerProvider.ADVENT_PLAYER, null).consumeResource(Enums.Resources.ENERGY, 15, true))
			EntityUtil.dealSelfHarmDamage(attacker, 4);
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(StringUtil.getColourLocaleStringWithArguments("items.description.damage.random", TextFormatting.DARK_GREEN, Double.toString(baseDmg), Double.toString(maxDmg)));
		tooltip.add(StringUtil.getColourLocaleString("item.PlutonScythe.desc.2", TextFormatting.AQUA));
		tooltip.add(StringUtil.getColourLocaleStringWithArguments("items.description.ammo.energy", TextFormatting.DARK_GREEN, "15"));
	}
}
