package net.tslat.aoa3.item.weapon.sword;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoATags;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.RandomUtil;
import net.tslat.aoa3.util.constant.AttackSpeed;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class SweetSword extends BaseSword {
	private static final ArrayList<ItemStack> candyList = new ArrayList<ItemStack>();
	private static boolean populated = false;

	public SweetSword() {
		super(ItemUtil.customItemTier(1850, AttackSpeed.NORMAL, 15.0f, 4, 10, null));
	}

	@Override
	protected void doMeleeEffect(ItemStack stack, LivingEntity target, LivingEntity attacker, float attackCooldown) {
		if (RandomUtil.percentChance(0.2f * attackCooldown)) {
			if (!populated)
				populateCandyList();

			target.entityDropItem(candyList.get(random.nextInt(candyList.size())), target.getHeight() / 2f);
		}
	}

	private static void populateCandyList() {
		candyList.add(new ItemStack(Items.SUGAR, 3));

		AoATags.Items.CANDY.getAllElements().forEach(item -> candyList.add(new ItemStack(item)));

		populated = true;
	}

	public static void addCandyDrop(ItemStack stack) {
		if (!populated)
			populateCandyList();

		candyList.add(stack);
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.UNIQUE, 1));
	}
}
