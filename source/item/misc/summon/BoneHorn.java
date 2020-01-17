package net.tslat.aoa3.item.misc.summon;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.CreativeTabsRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.boss.tyrosaur.EntityTyrosaur;
import net.tslat.aoa3.utils.ConfigurationUtil;
import net.tslat.aoa3.utils.StringUtil;
import net.tslat.aoa3.utils.player.PlayerUtil;

import javax.annotation.Nullable;
import java.util.List;

public class BoneHorn extends Item {
	public BoneHorn() {
		setTranslationKey("BoneHorn");
		setRegistryName("aoa3:bone_horn");
		setCreativeTab(CreativeTabsRegister.miscTab);

		setMaxDamage(3);
		setMaxStackSize(1);
	}

	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
		return EnumAction.BOW;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return 60;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		if (world.getDifficulty() != EnumDifficulty.PEACEFUL) {
			if (!world.isRemote)
				world.playSound(null, player.posX, player.posY, player.posZ, SoundsRegister.boneHornCall, SoundCategory.PLAYERS, 1.0f, 1.0f);

			player.setActiveHand(hand);
			player.getCooldownTracker().setCooldown(this, 150);

			return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, player.getHeldItem(hand));
		}
		else if (!world.isRemote) {
			PlayerUtil.notifyPlayer((EntityPlayerMP)player, "message.feedback.spawnBoss.difficultyFail", TextFormatting.RED);
		}

		return new ActionResult<ItemStack>(EnumActionResult.FAIL, player.getHeldItem(hand));
	}

	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World world, EntityLivingBase user) {
		if (!world.isRemote && world.provider.getDimension() == ConfigurationUtil.MainConfig.dimensionIds.precasia) {
			stack.damageItem(1, user);

			EntityTyrosaur tyrosaur = new EntityTyrosaur(world);
			int posX = (int)(user.posX + itemRand.nextFloat() * 40 - 20);
			int posZ = (int)(user.posZ + itemRand.nextFloat() * 40 - 20);
			int posY = world.getHeight(posX, posZ);

			while (posY > user.posY + 10) {
				posX = (int)(user.posX + itemRand.nextFloat() * 40 - 20);
				posZ = (int)(user.posZ + itemRand.nextFloat() * 40 - 20);
				posY = world.getHeight(posX, posZ);
			}

			tyrosaur.setPosition(posX, posY, posZ);
			tyrosaur.setAttackTarget(user);
			world.spawnEntity(tyrosaur);
			StringUtil.sendMessageWithinRadius(StringUtil.getLocaleWithArguments("message.mob.tyrosaur.spawn", user.getDisplayName().getUnformattedText()), user, 50);
		}

		return stack;
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(StringUtil.getLocaleString("item.BoneHorn.desc.1"));
	}
}
