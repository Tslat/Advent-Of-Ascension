package net.tslat.aoa3.item.weapon.staff;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAreaEffectCloud;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.projectiles.staff.BaseEnergyShot;
import net.tslat.aoa3.entity.projectiles.staff.EntityMoonlightFall;
import net.tslat.aoa3.item.misc.RuneItem;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.EntityUtil;
import net.tslat.aoa3.utils.ItemUtil;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;

public class MoonlightStaff extends BaseStaff {
	public MoonlightStaff(int durability) {
		super(durability);
		setTranslationKey("MoonlightStaff");
		setRegistryName("aoa3:moonlight_staff");
	}

	@Nullable
	@Override
	public SoundEvent getCastingSound() {
		return SoundsRegister.MOONLIGHT_STAFF_CAST;
	}

	@Override
	protected void populateRunes(HashMap<RuneItem, Integer> runes) {
		runes.put(ItemRegister.COMPASS_RUNE, 1);
		runes.put(ItemRegister.LUNAR_RUNE, 2);
		runes.put(ItemRegister.KINETIC_RUNE, 2);
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

		world.spawnEntity(new EntityMoonlightFall(caster, this, pos.getX(), pos.getY() + 30, pos.getZ(), 3f));
	}

	@Override
	public void doBlockImpact(BaseEnergyShot shot, BlockPos block, EntityLivingBase caster) {
		createCloud(shot, caster);
	}

	@Override
	public boolean doEntityImpact(BaseEnergyShot shot, Entity target, EntityLivingBase caster) {
		if (!EntityUtil.isTypeImmune(target, Enums.MobProperties.MAGIC_IMMUNE)) {
			createCloud(shot, caster);

			return true;
		}

		return false;
	}

	private void createCloud(BaseEnergyShot shot, EntityLivingBase caster) {
		EntityAreaEffectCloud cloud = new EntityAreaEffectCloud(shot.world, shot.posX, shot.posY, shot.posZ);

		cloud.setOwner(caster);
		cloud.addEffect(new PotionEffect(MobEffects.SLOWNESS, 140, 1, false, true));
		cloud.setRadius(0.1f);
		cloud.setRadiusPerTick(1);
		cloud.setDuration(10);
		cloud.setWaitTime(0);

		shot.world.spawnEntity(cloud);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.MoonlightStaff.desc.1", Enums.ItemDescriptionType.POSITIVE));
		super.addInformation(stack, world, tooltip, flag);
	}
}
