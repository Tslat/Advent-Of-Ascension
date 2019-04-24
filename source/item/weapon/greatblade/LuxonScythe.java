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
import net.tslat.aoa3.capabilities.handlers.AdventPlayerCapability;
import net.tslat.aoa3.capabilities.providers.AdventPlayerProvider;
import net.tslat.aoa3.item.weapon.AdventWeapon;
import net.tslat.aoa3.item.weapon.LongReachWeapon;
import net.tslat.aoa3.utils.EntityUtil;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.StringUtil;

import java.util.List;

public class LuxonScythe extends BaseGreatblade implements AdventWeapon, LongReachWeapon {
	public LuxonScythe(double dmg, double speed, int durability) {
		super(dmg, speed, durability);
		setUnlocalizedName("LuxonScythe");
		setRegistryName("aoa3:luxon_scythe");
	}

	@Override
	public void attackEntity(ItemStack stack, Entity target, EntityLivingBase attacker, float dmg) {
		super.attackEntity(stack, target, attacker, dmg);

		float leechAmount = 200;
		float percentLeech = attacker instanceof EntityPlayer ? ((EntityPlayer)attacker).getCooledAttackStrength(0.0f) : 1.0f;

		if (target instanceof EntityPlayer)  {
			AdventPlayerCapability targetCap = (AdventPlayerCapability)target.getCapability(AdventPlayerProvider.ADVENT_PLAYER, null);
			leechAmount = Math.min(2, targetCap.getResourceValue(Enums.Resources.SOUL)) * percentLeech;

			targetCap.consumeResource(Enums.Resources.SOUL, leechAmount, true);
		}

		if (attacker instanceof EntityPlayer) {
			AdventPlayerCapability cap = (AdventPlayerCapability)attacker.getCapability(AdventPlayerProvider.ADVENT_PLAYER, null);

			cap.resourceRegen(Enums.Resources.SOUL, leechAmount);

			if (!cap.consumeResource(Enums.Resources.ENERGY, 15, true))
				EntityUtil.dealSelfHarmDamage(attacker, 4);
		}
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(StringUtil.getColourLocaleString("item.LuxonScythe.desc.1", TextFormatting.DARK_GREEN));
		tooltip.add(StringUtil.getColourLocaleString("item.LuxonScythe.desc.2", TextFormatting.AQUA));
		tooltip.add(StringUtil.getColourLocaleStringWithArguments("items.description.ammo.energy", TextFormatting.DARK_GREEN, "15"));
	}
}
