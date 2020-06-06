package net.tslat.aoa3.item.weapon.blaster;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.capabilities.handlers.AdventMiscStackCapability;
import net.tslat.aoa3.capabilities.providers.AdventMiscStackProvider;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.projectiles.blaster.EntityIroMinerShot;
import net.tslat.aoa3.entity.projectiles.staff.BaseEnergyShot;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.EntityUtil;
import net.tslat.aoa3.utils.ItemUtil;

import javax.annotation.Nullable;
import java.util.List;

public class IroMiner extends BaseBlaster {
	public IroMiner(double dmg, int durability, int fireDelayTicks, float energyCost) {
		super(dmg, durability, fireDelayTicks, energyCost);
		setTranslationKey("IroMiner");
		setRegistryName("aoa3:iro_miner");
	}

	@Nullable
	@Override
	public SoundEvent getFiringSound() {
		return SoundsRegister.MOON_SHINER_FIRE;
	}

	@Override
	public void fire(ItemStack blaster, EntityLivingBase shooter) {
		shooter.world.spawnEntity(new EntityIroMinerShot(shooter, this, 60));
	}

	@Override
	public boolean doEntityImpact(BaseEnergyShot shot, Entity target, EntityLivingBase shooter) {
		ItemStack heldStack = shooter.getHeldItemMainhand();
		float damageMod = 1;

		if (heldStack.getItem() == this) {
			AdventMiscStackCapability cap = (AdventMiscStackCapability)heldStack.getCapability(AdventMiscStackProvider.MISC_STACK, null);

			if (cap != null) {
				if (target.getUniqueID().equals(cap.getObject())) {
					damageMod = cap.getValue() + 0.02f;
					cap.setValue(damageMod);
				}
				else {
					cap.setObject(target.getUniqueID());
					cap.setValue(1.0f);
				}
			}
		}

		return EntityUtil.dealBlasterDamage(shooter, target, shot, (float)baseDmg * damageMod, false);
	}

	@Nullable
	@Override
	public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable NBTTagCompound nbt) {
		if (!stack.hasTagCompound())
			stack.setTagCompound(new NBTTagCompound());

		stack.getTagCompound().setByte("HideFlags", (byte)2);

		return new AdventMiscStackProvider();
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.IroMiner.desc.1", Enums.ItemDescriptionType.POSITIVE));
		super.addInformation(stack, world, tooltip, flag);
	}
}
