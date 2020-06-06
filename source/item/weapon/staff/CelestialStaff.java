package net.tslat.aoa3.item.weapon.staff;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.projectiles.staff.BaseEnergyShot;
import net.tslat.aoa3.entity.projectiles.staff.EntityCelestialFall;
import net.tslat.aoa3.item.misc.RuneItem;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.EntityUtil;
import net.tslat.aoa3.utils.ItemUtil;
import net.tslat.aoa3.utils.WorldUtil;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;

public class CelestialStaff extends BaseStaff {
	public CelestialStaff(int durability) {
		super(durability);
		setTranslationKey("CelestialStaff");
		setRegistryName("aoa3:celestial_staff");
	}

	@Nullable
	@Override
	public SoundEvent getCastingSound() {
		return SoundsRegister.CELESTIAL_STAFF_CAST;
	}

	@Override
	protected void populateRunes(HashMap<RuneItem, Integer> runes) {
		runes.put(ItemRegister.COMPASS_RUNE, 1);
		runes.put(ItemRegister.LUNAR_RUNE, 2);
		runes.put(ItemRegister.ENERGY_RUNE, 2);
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

		world.spawnEntity(new EntityCelestialFall(caster, this, pos.getX(), pos.getY() + 25, pos.getZ(), 3.0f));
	}

	@Override
	public void doBlockImpact(BaseEnergyShot shot, BlockPos block, EntityLivingBase shooter) {
		WorldUtil.createExplosion(shooter, shot.world, shot, 2.5f);
	}

	@Override
	public boolean doEntityImpact(BaseEnergyShot shot, Entity target, EntityLivingBase shooter) {
		WorldUtil.createExplosion(shooter, shot.world, shot, 2.5f);

		return EntityUtil.dealMagicDamage(shot, shooter, target, getDmg(), false);
	}

	@Override
	public float getDmg() {
		return 11;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.CelestialStaff.desc.1", Enums.ItemDescriptionType.POSITIVE));
		super.addInformation(stack, world, tooltip, flag);
	}
}
