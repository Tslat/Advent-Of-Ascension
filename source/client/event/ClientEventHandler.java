package net.tslat.aoa3.client.event;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.gui.screen.EditStructureScreen;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemFrameEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.Util;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.sound.PlaySoundEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.network.NetworkDirection;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.client.Keybinds;
import net.tslat.aoa3.client.gui.hud.RecoilRenderer;
import net.tslat.aoa3.client.gui.overlay.HelmetScreenRenderer;
import net.tslat.aoa3.client.gui.overlay.ScopeOverlayRenderer;
import net.tslat.aoa3.client.gui.overlay.ScreenOverlayRenderer;
import net.tslat.aoa3.common.packet.AoAPackets;
import net.tslat.aoa3.common.packet.packets.HaloChangePacket;
import net.tslat.aoa3.common.packet.packets.LongReachItemHitPacket;
import net.tslat.aoa3.config.AoAConfig;
import net.tslat.aoa3.entity.mob.greckon.SilencerEntity;
import net.tslat.aoa3.event.GlobalEvents;
import net.tslat.aoa3.item.LongReachItem;
import net.tslat.aoa3.item.armour.ScreenOverlayArmour;
import net.tslat.aoa3.item.weapon.sniper.BaseSniper;
import net.tslat.aoa3.library.scheduling.AoAScheduler;
import net.tslat.aoa3.util.LocaleUtil;
import org.lwjgl.glfw.GLFW;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber(modid = AdventOfAscension.MOD_ID, value = Dist.CLIENT)
@OnlyIn(Dist.CLIENT)
public class ClientEventHandler {
	public static int tick;

	@SubscribeEvent
	public static void clientTick(final TickEvent.ClientTickEvent ev) {
		if (ev.phase == TickEvent.Phase.END) {
			tick++;

			if (tick > 24000)
				tick = 0;

			if (!Minecraft.getInstance().isPaused()) {
				if (ScreenOverlayRenderer.overlayTicks > 0)
					--ScreenOverlayRenderer.overlayTicks;

				if (RecoilRenderer.recoilTicksRemaining > 0)
					--RecoilRenderer.recoilTicksRemaining;
			}

			ClientPlayerEntity player = Minecraft.getInstance().player;

			if (player == null)
				return;

			if (player.isShiftKeyDown() && player.onGround) {
				Item sniper = player.getItemInHand(Hand.MAIN_HAND).getItem();

				if (sniper instanceof BaseSniper) {
					ScopeOverlayRenderer.isScoped = true;
					ScopeOverlayRenderer.scope = ((BaseSniper)sniper).getScopeType();
				}
				else {
					ScopeOverlayRenderer.isScoped = false;
				}
			}
			else {
				ScopeOverlayRenderer.isScoped = false;

				if (ScopeOverlayRenderer.scope != null) {
					RecoilRenderer.recoilTicks = 5;
					RecoilRenderer.recoilTicksRemaining = 5;
					RecoilRenderer.recoilAngle = 1.0f;
					ScopeOverlayRenderer.scope = null;
				}
			}

			HelmetScreenRenderer.active = false;
			Item helmetItem = player.inventory.armor.get(3).getItem();

			if (!ScopeOverlayRenderer.isScoped && helmetItem instanceof ScreenOverlayArmour) {
				HelmetScreenRenderer.type = ((ScreenOverlayArmour)helmetItem).getOverlay();
				HelmetScreenRenderer.active = true;
			}

			if (!Minecraft.getInstance().hasSingleplayerServer()) {
				GlobalEvents.tick++;
				AoAScheduler.handleSyncScheduledTasks(GlobalEvents.tick);
			}
		}
	}

	@SubscribeEvent(receiveCanceled = true)
	public static void onLongReachSwing(InputEvent.MouseInputEvent ev) {
		if (ev.getAction() == GLFW.GLFW_PRESS && ev.getButton() == GLFW.GLFW_MOUSE_BUTTON_1 && Minecraft.getInstance().screen == null) {
			Minecraft mc = Minecraft.getInstance();
			PlayerEntity player = mc.player;

			if (player != null && player.level != null) {
				if (player.isSpectator())
					return;

				ItemStack stack = player.getItemInHand(Hand.MAIN_HAND);

				if (stack.getItem() instanceof LongReachItem && !player.isUsingItem()) {
					RayTraceResult ray = getExtendedReachRayTrace(((LongReachItem)stack.getItem()).getReach());

					if (ray instanceof EntityRayTraceResult)
						AoAPackets.messageServer(new LongReachItemHitPacket((((EntityRayTraceResult)ray).getEntity().getId())));
				}
			}
		}
	}

	@SubscribeEvent
	public static void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent ev) {
		if (AoAConfig.CLIENT.showWelcomeMessage.get()) {
			if (Keybinds.ADVENT_GUI.getKey().getValue() == GLFW.GLFW_KEY_UNKNOWN) {
				ev.getPlayer().sendMessage(LocaleUtil.getLocaleMessage("message.login.welcome.alt", TextFormatting.GRAY), Util.NIL_UUID);
			}
			else {
				ev.getPlayer().sendMessage(LocaleUtil.getLocaleMessage("message.login.welcome", TextFormatting.GRAY, Keybinds.ADVENT_GUI.getKeyBinding().getTranslatedKeyMessage()), Util.NIL_UUID);
			}
		}

		if (ev.getPlayer().level.isClientSide())
			AoAPackets.INSTANCE.sendTo(new HaloChangePacket(AoAConfig.CLIENT.personalHaloPreference.get()), ((ClientPlayerEntity)ev.getPlayer()).connection.getConnection(), NetworkDirection.PLAY_TO_SERVER);
	}

	@SubscribeEvent
	public static void onPlayerDeath(LivingDeathEvent ev) {
		if (ev.getEntity() instanceof PlayerEntity)
			ScreenOverlayRenderer.overlayTicks = 0;
	}

	@Nullable
	private static RayTraceResult getExtendedReachRayTrace(float reach) {
		Minecraft mc = Minecraft.getInstance();
		Entity player = mc.getCameraEntity();

		if (player != null && player.level != null) {
			float partialTicks = mc.getFrameTime();
			mc.crosshairPickEntity = null;
			mc.hitResult = player.pick(reach, partialTicks, false);
			Vector3d eyePos = player.getEyePosition(partialTicks);
			double squareDistance = reach;

			squareDistance = squareDistance * squareDistance;

			if (mc.hitResult != null)
				squareDistance = mc.hitResult.getLocation().distanceToSqr(eyePos);

			Vector3d lookVec = player.getViewVector(1.0F);
			Vector3d reachVec = eyePos.add(lookVec.x * (double)reach, lookVec.y * (double)reach, lookVec.z * (double)reach);
			AxisAlignedBB boundingBox = player.getBoundingBox().expandTowards(lookVec.scale(reach)).inflate(1.0D, 1.0D, 1.0D);
			EntityRayTraceResult trace = ProjectileHelper.getEntityHitResult(player, eyePos, reachVec, boundingBox, (entity) -> !entity.isSpectator() && entity.isPickable(), squareDistance);

			if (trace != null) {
				Entity entity = trace.getEntity();

				if (eyePos.distanceToSqr(trace.getLocation()) < squareDistance || mc.hitResult == null) {
					mc.hitResult = trace;

					if (entity instanceof LivingEntity || entity instanceof ItemFrameEntity)
						mc.crosshairPickEntity = entity;
				}
			}

			if (mc.hitResult.getType() == RayTraceResult.Type.ENTITY)
				return mc.hitResult;
		}

		return null;
	}

	@SubscribeEvent
	public static void onSoundPlay(PlaySoundEvent ev) {
		if (SilencerEntity.isClientNearby) {
			ClientPlayerEntity player = Minecraft.getInstance().player;

			if (player == null || player.level.getEntitiesOfClass(SilencerEntity.class, player.getBoundingBox().inflate(8)).isEmpty()) {
				SilencerEntity.isClientNearby = false;
				Minecraft.getInstance().getSoundManager().soundEngine.listener.setGain(SilencerEntity.previousGain);
			}
		}
	}

	@SubscribeEvent // Patching structure block gui because it arbitrarily limits the name to 64 chars
	public static void guiInitEvent(GuiScreenEvent.InitGuiEvent.Post ev) {
		if (ev.getGui() instanceof EditStructureScreen) {
			EditStructureScreen screen = (EditStructureScreen)ev.getGui();

			screen.nameEdit.setMaxLength(256);
			screen.nameEdit.setValue(screen.structure.getStructureName());
		}
	}
}
