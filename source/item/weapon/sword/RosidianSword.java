package net.tslat.aoa3.item.weapon.sword;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.capabilities.providers.AdventMiscStackProvider;
import net.tslat.aoa3.item.weapon.AdventWeapon;
import net.tslat.aoa3.utils.EntityUtil;
import net.tslat.aoa3.utils.StringUtil;

import java.util.List;

public class RosidianSword extends BaseSword implements AdventWeapon {
	public RosidianSword(final ToolMaterial material, final double speed) {
		super(material, speed);
		setTranslationKey("RosidianSword");
		setRegistryName("aoa3:rosidian_sword");
	}

	@Override
	public boolean onLeftClickEntity(final ItemStack stack, final EntityPlayer player, final Entity target) {
		stack.getCapability(AdventMiscStackProvider.MISC_STACK, EnumFacing.NORTH).setValue(player.getCooledAttackStrength(0.0f));

		if (player.getHealth() < player.getMaxHealth()) {
			float motionX = (float)(player.posX - target.posX) * 0.1f;
			float motionY = (float)(player.posY - target.posY) * 0.1f;
			float motionZ = (float)(player.posZ - target.posZ) * 0.1f;

			player.world.spawnParticle(EnumParticleTypes.END_ROD, target.posX + itemRand.nextGaussian() * 0.2, target.posY + target.height / 2f, target.posZ + itemRand.nextGaussian() * 0.2, motionX, motionY, motionZ);

			for (EntityLivingBase swipeTarget : player.world.getEntitiesWithinAABB(EntityLivingBase.class, target.getEntityBoundingBox().grow(1, 0.25, 1))) {
				if (swipeTarget != target && swipeTarget != player && !player.isOnSameTeam(swipeTarget) && player.getDistanceSq(swipeTarget) < 9) {
					motionX = (float)(player.posX - swipeTarget.posX) * 0.1f;
					motionY = (float)(player.posY - swipeTarget.posY) * 0.1f;
					motionZ = (float)(player.posZ - swipeTarget.posZ) * 0.1f;

					player.world.spawnParticle(EnumParticleTypes.END_ROD, swipeTarget.posX + itemRand.nextGaussian() * 0.2, swipeTarget.posY + target.height / 2f, swipeTarget.posZ + itemRand.nextGaussian() * 0.2, motionX, motionY, motionZ);
				}
			}
		}

		return false;
	}

	@Override
	protected void doMeleeEffect(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker, float attackCooldown) {
		if (attacker.getHealth() < attacker.getMaxHealth() && attackCooldown == 1) {
			EntityUtil.healEntity(attacker, 1);

			if (attacker instanceof EntityPlayer) {
				for (EntityLivingBase swipeTarget : attacker.world.getEntitiesWithinAABB(EntityLivingBase.class, target.getEntityBoundingBox().grow(1, 0.25, 1))) {
					if (swipeTarget != target && swipeTarget != attacker && swipeTarget.getHealth() < swipeTarget.getMaxHealth() && !attacker.isOnSameTeam(swipeTarget) && attacker.getDistanceSq(swipeTarget) < 9)
						EntityUtil.healEntity(attacker, 0.4f);
				}
			}
		}
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(StringUtil.getColourLocaleString("item.RosidianSword.desc.1", TextFormatting.DARK_GREEN));
	}
}
