package net.tslat.aoa3.item.minionslab;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.tslat.aoa3.capabilities.handlers.AdventPlayerCapability;
import net.tslat.aoa3.client.gui.mainwindow.AdventGuiTabPlayer;
import net.tslat.aoa3.common.registration.CreativeTabsRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.minions.AoAMinion;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.PlayerUtil;
import net.tslat.aoa3.utils.StringUtil;

import javax.annotation.Nullable;
import java.util.List;

public abstract class BaseSlab extends Item {
	public final int lvl;
	public final float cost;
	public final int sacrificeLvl;
	public final float sacrificeXp;

	public BaseSlab(String name, String registryName, int lvl, float creationCost, int sacrificeLvl, float sacrificeXp) {
		setUnlocalizedName(name);
		setRegistryName("aoa3:" + registryName);
		this.lvl = lvl;
		this.cost = creationCost;
		this.sacrificeLvl = sacrificeLvl;
		this.sacrificeXp = sacrificeXp;
		setCreativeTab(CreativeTabsRegister.minionSlabsTab);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		ItemStack stack = player.getHeldItem(hand);

		if (!world.isRemote) {
			AdventPlayerCapability cap = PlayerUtil.getAdventPlayer(player);

			if (!player.capabilities.isCreativeMode && !cap.hasLevel(Enums.Skills.CREATION, lvl)) {
				cap.sendPlayerMessage(StringUtil.getColourLocaleWithArguments("message.feedback.minionSlab.levelFail", TextFormatting.RED, Integer.toString(lvl)));
				return ActionResult.newResult(EnumActionResult.FAIL, stack);
			}

			if (player.capabilities.isCreativeMode || cap.consumeResource(Enums.Resources.CREATION, cost, false)) {
				if (!player.capabilities.isCreativeMode)
					stack.shrink(1);

				AoAMinion minion = activateSlab(player, stack);

				if (minion != null && cap.getArmourSetType() == Enums.ArmourSets.CREATION)
					applyBuffs(minion);

				player.world.playSound(null, player.posX, player.posY, player.posZ, SoundsRegister.useCreationSlab, SoundCategory.PLAYERS, 1.0f, 1.0f);
				return ActionResult.newResult(EnumActionResult.SUCCESS, stack);
			}
		}

		return ActionResult.newResult(EnumActionResult.PASS, stack);
	}

	public abstract AoAMinion activateSlab(EntityPlayer pl, ItemStack stack);

	protected void applyBuffs(AoAMinion minion) {
		switch (itemRand.nextInt(4)) {
			case 0:
				minion.addPotionEffect(new PotionEffect(MobEffects.SPEED, 1200, 1, true, true));
				break;
			case 1:
				minion.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 1200, 1, true, true));
				break;
			case 2:
				minion.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 1200, 1, true, true));
				break;
			case 3:
				minion.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 1200, 1, true, true));
				break;
		}
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(StringUtil.getColourLocaleStringWithArguments("items.description.slab.cost", TextFormatting.LIGHT_PURPLE, Integer.toString((int)cost)));

		if (AdventGuiTabPlayer.getSkillLevel(Enums.Skills.CREATION) < lvl) {
			tooltip.add(StringUtil.getColourLocaleStringWithArguments("items.description.skillRequirement", TextFormatting.RED, Integer.toString(lvl), StringUtil.getLocaleString("skills.creation.name")));
		}
		else {
			tooltip.add(StringUtil.getColourLocaleStringWithArguments("items.description.skillRequirement", TextFormatting.GREEN, Integer.toString(lvl), StringUtil.getLocaleString("skills.creation.name")));
		}
	}
}
