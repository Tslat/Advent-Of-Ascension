package net.tslat.aoa3.item.weapon.staff;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.item.misc.RuneItem;
import net.tslat.aoa3.utils.EntityUtil;
import net.tslat.aoa3.utils.StringUtil;

import java.util.HashMap;
import java.util.List;

public class PhantomStaff extends BaseStaff {
	private static HashMap<RuneItem, Integer> runes = new HashMap<RuneItem, Integer>();

	static {
		runes.put(ItemRegister.runeStrike, 2);
		runes.put(ItemRegister.runeStorm, 1);
		runes.put(ItemRegister.runeCompass, 1);
	}

	public PhantomStaff(SoundEvent sound, int durability) {
		super(sound, durability);
		setUnlocalizedName("PhantomStaff");
		setRegistryName("aoa3:phantom_staff");
	}

	@Override
	public Object checkPreconditions(EntityLivingBase caster, ItemStack staff) {
		BlockPos trace = null;

		if (caster instanceof EntityPlayer)
			trace = EntityUtil.getBlockAimingAt((EntityPlayer)caster, 70);

		return trace;
	}

	@Override
	public HashMap<RuneItem, Integer> getRunes() {
		return runes;
	}

	@Override
	public void cast(World world, ItemStack staff, EntityLivingBase caster, Object args) {
		BlockPos pos = (BlockPos)args;
		int x = pos.getX();
		int z = pos.getZ();

		for (int i = x - 3; i <= x + 3; i++) {
			world.addWeatherEffect(new EntityLightningBolt(world, i, world.getHeight(i, z), z, false));
		}

		for (int i = z - 3; i <= z + 3; i++) {
			world.addWeatherEffect(new EntityLightningBolt(world, x, world.getHeight(x, i), i, false));
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(StringUtil.getColourLocaleString("item.PhantomStaff.desc.1", TextFormatting.DARK_GREEN));
		super.addInformation(stack, world, tooltip, flag);
	}
}
