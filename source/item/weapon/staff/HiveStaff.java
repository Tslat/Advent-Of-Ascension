package net.tslat.aoa3.item.weapon.staff;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.entity.minions.EntityHiveSoldier;
import net.tslat.aoa3.item.misc.RuneItem;
import net.tslat.aoa3.utils.StringUtil;

import java.util.HashMap;
import java.util.List;

public class HiveStaff extends BaseStaff {
	private static HashMap<RuneItem, Integer> runes = new HashMap<RuneItem, Integer>();

	static {
		runes.put(ItemRegister.runeEnergy, 10);
		runes.put(ItemRegister.runeLife, 2);
	}

	public HiveStaff(SoundEvent sound, int durability) {
		super(sound, durability);
		setUnlocalizedName("HiveStaff");
		setRegistryName("aoa3:hive_staff");
	}

	@Override
	public HashMap<RuneItem, Integer> getRunes() {
		return runes;
	}

	@Override
	public void cast(World world, ItemStack staff, EntityLivingBase caster, Object args) {
		EntityHiveSoldier hiveSoldier = new EntityHiveSoldier(caster.world);

		if (caster instanceof EntityPlayer)
			hiveSoldier.setTamedBy((EntityPlayer)caster);

		hiveSoldier.setPosition(caster.posX, caster.posY, caster.posZ);
		caster.world.spawnEntity(hiveSoldier);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(StringUtil.getColourLocaleString("item.HiveStaff.desc.1", TextFormatting.DARK_GREEN));
		super.addInformation(stack, world, tooltip, flag);
	}
}
