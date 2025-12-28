package net.tslat.aoa3.content.item.misc;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.integration.IntegrationManager;
import net.tslat.aoa3.integration.patchouli.PatchouliIntegration;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.PlayerUtil;
import net.tslat.aoa3.util.StringUtil;

import java.util.List;

public class TornPages extends Item {
	public TornPages() {
		super(new Properties().stacksTo(1));
	}

	@Override
	public Component getName(ItemStack stack) {
		if (!IntegrationManager.isPatchouliActive())
			return super.getName(stack);

		return PatchouliIntegration.getBookFromStack(stack)
				.filter(PatchouliIntegration::isBookLoaded)
				.map(id -> Component.translatable("item.aoa3.tornPages.name", StringUtil.toTitleCase(id.getPath())))
				.map(Component.class::cast)
				.orElse(super.getName(stack));
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
		ItemStack bookStack = player.getItemInHand(hand);

		if (!IntegrationManager.isPatchouliActive())
			return InteractionResultHolder.pass(bookStack);

		return PatchouliIntegration.getBookFromStack(bookStack)
				.filter(PatchouliIntegration::isBookLoaded)
				.map(bookId -> {
					if (!level.isClientSide) {
						PlayerUtil.getAdventPlayer((ServerPlayer)player).storage.addPatchouliBook(bookId);
					}
					else {
						if (IntegrationManager.isPatchouliActive())
							PatchouliIntegration.openBook(bookId);
					}

					return InteractionResultHolder.success(bookStack);
				})
				.orElseGet(() -> InteractionResultHolder.pass(bookStack));
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag isAdvanced) {
		boolean patchouliIntegrated = IntegrationManager.isPatchouliActive();
		boolean validBook = patchouliIntegrated && PatchouliIntegration.getBookFromStack(stack).filter(PatchouliIntegration::isBookLoaded).isPresent();

		if (!validBook)
			return;

		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, validBook ? LocaleUtil.ItemDescriptionType.NEUTRAL : LocaleUtil.ItemDescriptionType.HARMFUL, validBook ? 1 : 2));
	}
}
