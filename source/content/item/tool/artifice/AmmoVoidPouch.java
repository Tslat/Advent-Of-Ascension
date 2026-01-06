package net.tslat.aoa3.content.item.tool.artifice;

import it.unimi.dsi.fastutil.Pair;
import it.unimi.dsi.fastutil.ints.IntObjectPair;
import it.unimi.dsi.fastutil.objects.Reference2IntMap;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.item.TooltipFlag;
import net.tslat.aoa3.common.registration.AoATags;
import net.tslat.aoa3.common.registration.item.AoAArtificeDevices;
import net.tslat.aoa3.common.registration.item.AoADataComponents;
import net.tslat.aoa3.library.object.container.AmmoVoidPouchComponent;
import net.tslat.aoa3.util.InventoryUtil;
import net.tslat.aoa3.util.LocaleUtil;

import java.util.List;
import java.util.function.Consumer;

public class AmmoVoidPouch extends ArtificeItem {
    public AmmoVoidPouch() {
        super(new Item.Properties().stacksTo(1).component(AoADataComponents.AMMO_VOID_POUCH, new AmmoVoidPouchComponent()));
    }

    public static void tryProduceProjectile(Player pl, ProjectileWeaponItem weapon, ItemStack weaponStack, int cost, Consumer<ItemStack> ammoConsumer) {
        IntObjectPair<ItemStack> ammoPouchSlot = InventoryUtil.findItem(pl, AoAArtificeDevices.AMMO_VOID_POUCH.get()).orElse(null);

        if (ammoPouchSlot == null)
            return;

        ItemStack pouch = ammoPouchSlot.second();
        Pair<AmmoVoidPouchComponent, ItemStack> projectile = pouch.get(AoADataComponents.AMMO_VOID_POUCH).retrieveAmmo(weapon.getSupportedHeldProjectiles(weaponStack), cost);

        if (projectile != null) {
            pouch.set(AoADataComponents.AMMO_VOID_POUCH, projectile.left());
            ammoConsumer.accept(projectile.right());
        }
    }

    @Override
    public boolean overrideOtherStackedOnMe(ItemStack stack, ItemStack other, Slot slot, ClickAction action, Player player, SlotAccess access) {
        if (action != ClickAction.SECONDARY || !other.is(AoATags.Items.AMMO_VOID_POUCH_COMPATIBLE))
            return super.overrideOtherStackedOnMe(stack, other, slot, action, player, access);

        AmmoVoidPouchComponent component = stack.get(AoADataComponents.AMMO_VOID_POUCH);
        AmmoVoidPouchComponent newComponent = component.add(other);

        if (component != newComponent) {
            stack.set(AoADataComponents.AMMO_VOID_POUCH, newComponent);

            return true;
        }

        return false;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag tooltipFlag) {
        AmmoVoidPouchComponent component = stack.get(AoADataComponents.AMMO_VOID_POUCH);

        if (component == null)
            return;

        Reference2IntMap<Item> contents = component.contents();

        if (contents.isEmpty()) {
            tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.NEUTRAL, 1));
            tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.NEUTRAL, 2));
            tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.HARMFUL, 3));
        }
        else {
            tooltip.add(LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Keys.CONTENTS, LocaleUtil.ItemDescriptionType.ITEM_AMMO_COST));

            for (Reference2IntMap.Entry<Item> entry : contents.reference2IntEntrySet()) {
                tooltip.add(LocaleUtil.getLocaleMessage(LocaleUtil.Keys.LIST_OBJECT_QUANTITY, ChatFormatting.GRAY, LocaleUtil.getLocaleMessage(entry.getKey().getDescriptionId()), LocaleUtil.numToComponent(entry.getIntValue())));
            }
        }
    }
}
