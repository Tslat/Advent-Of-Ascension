package net.tslat.aoa3.item.weapon.sword;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;
import net.tslat.aoa3.item.weapon.AdventWeapon;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ItemUtil;

import java.util.ArrayList;
import java.util.List;

public class SweetSword extends BaseSword implements AdventWeapon {
	private static final ArrayList<ItemStack> candyList = new ArrayList<ItemStack>();
	private static boolean populated = false;

	public SweetSword(final ToolMaterial material, final double speed) {
		super(material, speed);
		setTranslationKey("SweetSword");
		setRegistryName("aoa3:sweet_sword");
	}

	@Override
	protected void doMeleeEffect(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker, float attackCooldown) {
		if (itemRand.nextFloat() < 0.2 * attackCooldown) {
			if (!populated)
				populateCandyList();

			target.entityDropItem(candyList.get(itemRand.nextInt(candyList.size())), target.height / 2f);
		}
	}

	private static void populateCandyList() {
		candyList.add(new ItemStack(Items.SUGAR, 3));
		OreDictionary.getOres("listAllSugar").forEach(stack -> candyList.add(stack));
		OreDictionary.getOres("foodCandy").forEach(stack -> candyList.add(stack));

		populated = true;
	}

	public static void addCandyDrop(ItemStack stack) {
		if (!populated)
			populateCandyList();

		candyList.add(stack);
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.SweetSword.desc.1", Enums.ItemDescriptionType.UNIQUE));
	}
}
