package net.tslat.aoa3.item.weapon.staff;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.entity.projectiles.staff.BaseEnergyShot;
import net.tslat.aoa3.entity.projectiles.staff.EntityLunarFall;
import net.tslat.aoa3.item.misc.RuneItem;
import net.tslat.aoa3.utils.EntityUtil;
import net.tslat.aoa3.utils.StringUtil;

import java.util.HashMap;
import java.util.List;

public class LunarStaff extends BaseStaff {
	private static HashMap<RuneItem, Integer> runes = new HashMap<RuneItem, Integer>();

	static {
		runes.put(ItemRegister.runeCompass, 1);
		runes.put(ItemRegister.runeLunar, 2);
	}

	public LunarStaff(SoundEvent sound, int durability) {
		super(sound, durability);
		setUnlocalizedName("LunarStaff");
		setRegistryName("aoa3:lunar_staff");
	}

	@Override
	public HashMap<RuneItem, Integer> getRunes() {
		return runes;
	}

	@Override
	public Object checkPreconditions(EntityLivingBase caster, ItemStack staff) {
		BlockPos trace = null;

		if (caster instanceof EntityPlayer)
			trace = EntityUtil.getBlockAimingAt((EntityPlayer)caster, 70);

		return trace;
	}

	@Override
	public void cast(World world, ItemStack staff, EntityLivingBase caster, Object args) {
		BlockPos pos = (BlockPos)args;

		world.spawnEntity(new EntityLunarFall(caster, this, pos.getX(), pos.getY() + 30, pos.getZ(), 3.0f));
	}

	@Override
	public void doEntityImpact(BaseEnergyShot shot, Entity target, EntityLivingBase shooter) {
		EntityUtil.dealMagicDamage(shot, shooter, target, getDmg(), false);
	}

	@Override
	public float getDmg() {
		return 16;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(StringUtil.getColourLocaleString("item.LunarStaff.desc.1", TextFormatting.DARK_GREEN));
		super.addInformation(stack, world, tooltip, flag);
	}
}
