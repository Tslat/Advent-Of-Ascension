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
import net.tslat.aoa3.entity.projectiles.staff.EntityFirestormFall;
import net.tslat.aoa3.item.misc.RuneItem;
import net.tslat.aoa3.utils.EntityUtil;
import net.tslat.aoa3.utils.StringUtil;

import java.util.HashMap;
import java.util.List;

public class FirestormStaff extends BaseStaff {
	private static HashMap<RuneItem, Integer> runes = new HashMap<RuneItem, Integer>();

	static {
		runes.put(ItemRegister.runeCompass, 1);
		runes.put(ItemRegister.runeFire, 2);
		runes.put(ItemRegister.runeLunar, 2);
	}

	public FirestormStaff(SoundEvent sound, int durability) {
		super(sound, durability);
		setUnlocalizedName("FirestormStaff");
		setRegistryName("aoa3:firestorm_staff");
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

		for (int i = 0; i < 8; i++) {
			world.spawnEntity(new EntityFirestormFall(caster, this, (pos.getX() - 4) + itemRand.nextFloat() * 8, pos.getY() + 30, (pos.getZ() - 4) + itemRand.nextFloat() * 8, 3.0f));
		}
	}

	@Override
	public void doEntityImpact(BaseEnergyShot shot, Entity target, EntityLivingBase shooter) {
		if (EntityUtil.dealMagicDamage(shot, shooter, target, getDmg(), false)) {
			if (target instanceof EntityLivingBase)
				target.setFire(7);
		}
	}

	@Override
	public float getDmg() {
		return 21;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(StringUtil.getColourLocaleString("item.FirestormStaff.desc.1", TextFormatting.DARK_GREEN));
		super.addInformation(stack, world, tooltip, flag);
	}
}
