package net.tslat.aoa3.client.gui.adventgui;

import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.client.gui.lib.ScrollablePane;
import net.tslat.aoa3.client.render.AoAGuiElementRenderers;
import net.tslat.aoa3.client.render.custom.AoASkillRenderer;
import net.tslat.aoa3.common.networking.AoANetworking;
import net.tslat.aoa3.common.networking.packets.AddSkillCyclePacket;
import net.tslat.aoa3.common.networking.packets.adventplayer.ToggleAoAAbilityPacket;
import net.tslat.aoa3.common.registration.AoAConfigs;
import net.tslat.aoa3.common.registration.entity.AoAAnimals;
import net.tslat.aoa3.common.registration.entity.AoAMobs;
import net.tslat.aoa3.library.object.RenderContext;
import net.tslat.aoa3.player.AoAPlayerEventListener;
import net.tslat.aoa3.player.ClientPlayerDataManager;
import net.tslat.aoa3.player.ability.AoAAbility;
import net.tslat.aoa3.player.skill.AoASkill;
import net.tslat.aoa3.util.ColourUtil;
import net.tslat.aoa3.util.NumberUtil;
import net.tslat.aoa3.util.PlayerUtil;
import net.tslat.aoa3.util.RenderUtil;
import net.tslat.smartbrainlib.util.RandomUtil;
import org.joml.Vector3f;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class AdventGuiTabPlayer extends Screen {
	private static final ResourceLocation TOTAL_LEVEL_ICON = new ResourceLocation(AdventOfAscension.MOD_ID, "textures/gui/aoaskill/total_level.png");

	private LivingEntity entityToRender = null;
	private float skillRenderScale = 1;

	private AbilityPane abilityPane = null;

	private float adjustedMouseX;
	private float adjustedMouseY;

	private boolean hoveringSkillClose = false;
	private boolean hoveringAddCycle = false;
	private long addCycleLastClicked = -1;

	protected AdventGuiTabPlayer() {
		super(Component.translatable("gui.aoa3.adventGui.stats"));
	}

	@Override
	protected void init() {
		setRenderEntity();

		boolean scaled;

		do {
			int totalWidth = 40;
			int totalHeight = 0;
			scaled = true;

			clearWidgets();

			for (AoASkill.Instance skill : ClientPlayerDataManager.get().getSkills()) {
				AoASkillRenderer renderer = AoAGuiElementRenderers.getSkillRenderer(skill.type());
				float renderWidth = (renderer.hudRenderWidth(skill) + 15) * skillRenderScale;
				float renderHeight = (renderer.hudRenderHeight(skill) + 20) * skillRenderScale;

				if (totalWidth + renderWidth >= 244 / skillRenderScale || totalHeight + renderHeight >= 100 / skillRenderScale) {
					totalWidth = 40;
					totalHeight += renderHeight;

					if (totalHeight >= 100) {
						skillRenderScale *= 0.95f;
						scaled = false;

						break;
					}
				}

				addRenderableWidget(new SkillEntry(this, totalWidth + 7, totalHeight + 10, renderer, skill, skillRenderScale));

				totalWidth += renderWidth;
			}
		}
		while (!scaled);

		AoASkill.Instance skill = abilityPane == null ? null : abilityPane.skill;
		abilityPane = new AbilityPane(minecraft, AdventMainGui.scaledTabRootY + 50, AdventMainGui.scaledTabRootX + 250, 280, 500, AdventMainGui.SCALE);

		abilityPane.setSkill(skill);
	}

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		final RenderContext renderContext = RenderContext.of(guiGraphics);
		PoseStack poseStack = guiGraphics.pose();
		adjustedMouseX = (mouseX * (1 / AdventMainGui.SCALE));
		adjustedMouseY = (mouseY * (1 / AdventMainGui.SCALE));

		poseStack.pushPose();
		poseStack.translate(AdventMainGui.scaledTabRootX, AdventMainGui.scaledTabRootY, 0);

		if (abilityPane == null || abilityPane.skill == null) {
			hoveringAddCycle = false;
			hoveringSkillClose = false;

			poseStack.scale(1.6f, 1.6f, 1);

			poseStack.pushPose();
			poseStack.scale(skillRenderScale, skillRenderScale, 1);
			super.render(guiGraphics, (int)adjustedMouseX, (int)adjustedMouseY, partialTicks);

			poseStack.popPose();

			drawPlayerBox(poseStack, adjustedMouseX, adjustedMouseY, 67, partialTicks);
			drawTotalLevel(poseStack, adjustedMouseX, adjustedMouseY, partialTicks);
		}
		else {
			AoASkillRenderer skillRenderer = AoAGuiElementRenderers.getSkillRenderer(abilityPane.skill.type());
			Font font = Minecraft.getInstance().font;
			float skillRenderWidth = skillRenderer.guiRenderWidth(abilityPane.skill) * 3 / 2f;
			hoveringSkillClose = (adjustedMouseX > AdventMainGui.scaledTabRootX + 736 && adjustedMouseX < AdventMainGui.scaledTabRootX + 755 && adjustedMouseY > AdventMainGui.scaledTabRootY + 7 && adjustedMouseY < AdventMainGui.scaledTabRootY + 27);

			poseStack.pushPose();

			RenderUtil.renderScaledText(poseStack, Component.literal("x"), 740, 5, 3f, hoveringSkillClose ? ColourUtil.RGB(75, 10, 10) : ColourUtil.BLACK, RenderUtil.TextRenderType.OUTLINED);

			poseStack.translate(150 - skillRenderWidth / 2f, 20, 0);

			RenderUtil.renderCenteredScaledText(poseStack, abilityPane.skill.getName(), 0, 0, 2f, ColourUtil.WHITE, RenderUtil.TextRenderType.OUTLINED);

			poseStack.translate(0, 20, 0);
			poseStack.pushPose();
			poseStack.translate(-skillRenderWidth, 0, 0);
			poseStack.scale(3f, 3f, 1);

			skillRenderer.renderInGui(renderContext, abilityPane.skill, partialTicks, (int)adjustedMouseX, (int)adjustedMouseY, AoASkillRenderer.ProgressRenderType.None, false);

			poseStack.popPose();

			RenderUtil.renderCenteredScaledText(poseStack, Component.translatable("gui." + AdventOfAscension.MOD_ID + ".adventGui.player.skillLevel", ""), 0, 80, 1.5f, ColourUtil.WHITE, RenderUtil.TextRenderType.OUTLINED);
			RenderUtil.renderCenteredScaledText(poseStack, Component.literal(String.valueOf(abilityPane.skill.getLevel(true))), 0, 95, 1.5f, ColourUtil.WHITE, RenderUtil.TextRenderType.OUTLINED);
			RenderUtil.renderCenteredScaledText(poseStack, Component.translatable("gui." + AdventOfAscension.MOD_ID + ".adventGui.player.skillXp", ""), 0, 120, 1.5f, ColourUtil.WHITE, RenderUtil.TextRenderType.OUTLINED);
			RenderUtil.renderCenteredScaledText(poseStack, Component.literal(NumberUtil.roundToNthDecimalPlace(abilityPane.skill.getXp(), 2)), 0, 135, 1.5f, ColourUtil.WHITE, RenderUtil.TextRenderType.OUTLINED);
			RenderUtil.renderCenteredScaledText(poseStack, Component.translatable("gui." + AdventOfAscension.MOD_ID + ".adventGui.player.skillXpRemaining", ""), 0, 160, 1.5f, ColourUtil.WHITE, RenderUtil.TextRenderType.OUTLINED);
			RenderUtil.renderCenteredScaledText(poseStack, Component.literal(NumberUtil.roundToNthDecimalPlace(PlayerUtil.getXpRequiredForNextLevel(abilityPane.skill.getLevel(true)), 2)), 0, 175, 1.5f, ColourUtil.WHITE, RenderUtil.TextRenderType.OUTLINED);
			RenderUtil.renderCenteredScaledText(poseStack, Component.translatable("gui." + AdventOfAscension.MOD_ID + ".adventGui.player.skillProgress", ""), 0, 200, 1.5f, ColourUtil.WHITE, RenderUtil.TextRenderType.OUTLINED);
			RenderUtil.renderCenteredScaledText(poseStack, Component.literal(PlayerUtil.getLevelProgressPercentage(abilityPane.skill.getLevel(true), abilityPane.skill.getXp()) + "%"), 0, 215, 1.5f, ColourUtil.WHITE, RenderUtil.TextRenderType.OUTLINED);
			RenderUtil.renderCenteredScaledText(poseStack, Component.translatable("gui." + AdventOfAscension.MOD_ID + ".adventGui.player.skillCycles", ""), 0, 240, 1.5f, ColourUtil.WHITE, RenderUtil.TextRenderType.OUTLINED);
			RenderUtil.renderCenteredScaledText(poseStack, Component.literal(String.valueOf(abilityPane.skill.getCycles())), 0, 255, 1.5f, ColourUtil.WHITE, RenderUtil.TextRenderType.OUTLINED);

			if (abilityPane.skill.getLevel(true) >= 100 && abilityPane.skill.getCycles() < 10) {
				MutableComponent text = addCycleLastClicked > 0 ? Component.translatable("gui." + AdventOfAscension.MOD_ID + ".adventGui.player.addCycle.confirm") : Component.translatable("gui." + AdventOfAscension.MOD_ID + ".adventGui.player.addCycle");
				int buttonWidth = font.width(text);
				hoveringAddCycle = (adjustedMouseX > AdventMainGui.scaledTabRootX + (150 - skillRenderWidth / 2f) - (buttonWidth / 2f * 1.5f) && adjustedMouseX < AdventMainGui.scaledTabRootX + (150 - skillRenderWidth / 2f) + (buttonWidth / 2f * 1.5f) && adjustedMouseY > AdventMainGui.scaledTabRootY + 315 && adjustedMouseY < AdventMainGui.scaledTabRootY + 327);

				if (System.currentTimeMillis() - addCycleLastClicked > 3000)
					addCycleLastClicked = -1;

				RenderUtil.renderCenteredScaledText(poseStack, text, 0, 275, 1.5f, hoveringAddCycle ? ColourUtil.RGB(255, 100, 100) : ColourUtil.RGB(255, 50, 50), RenderUtil.TextRenderType.DROP_SHADOW);
			}
			else {
				hoveringAddCycle = false;
			}

			poseStack.popPose();

			RenderUtil.renderCenteredScaledText(poseStack, Component.translatable("gui." + AdventOfAscension.MOD_ID + ".adventGui.player.abilities"), 500, 20, 2f, ColourUtil.WHITE, RenderUtil.TextRenderType.OUTLINED);

			poseStack.popPose();

			if (abilityPane != null)
				abilityPane.render(guiGraphics, adjustedMouseX, adjustedMouseY, partialTicks);

			return;
		}

		poseStack.popPose();
	}

	@Override
	public void renderTransparentBackground(GuiGraphics pGuiGraphics) {}

	private void drawTotalLevel(PoseStack matrix, float mouseX, float mouseY, float partialTicks) {
		Minecraft mc = Minecraft.getInstance();
		int totalLevel = ClientPlayerDataManager.get().getTotalLevel();

		if (totalLevel == 0) {
			RenderUtil.renderCenteredScaledText(matrix, Component.translatable("gui." + AdventOfAscension.MOD_ID + ".adventGui.player.noSkills"), 280, 80, 1.5f, ColourUtil.WHITE, RenderUtil.TextRenderType.OUTLINED);

			return;
		}

		RenderSystem.setShader(GameRenderer::getPositionTexColorShader);
		RenderSystem.setShaderTexture(0, TOTAL_LEVEL_ICON);
		RenderUtil.renderScaledCustomSizedTexture(matrix, 10, 14, 0, 0, 48, 48, 60, 60, 48, 48);
		RenderUtil.renderCenteredScaledText(matrix, Component.translatable("gui." + AdventOfAscension.MOD_ID + ".adventGui.player.totalLevel"), 40, 10, 1, ColourUtil.WHITE, RenderUtil.TextRenderType.OUTLINED);
		RenderUtil.renderCenteredScaledText(matrix, Component.literal(String.valueOf(ClientPlayerDataManager.get().getTotalLevel())), 40, 40, 1, ColourUtil.WHITE, RenderUtil.TextRenderType.OUTLINED);
	}

	private void drawPlayerBox(PoseStack poseStack, float mouseX, float mouseY, int scale, float partialTicks) {
		poseStack.pushPose();

		if (entityToRender == null)
			setRenderEntity();

		Minecraft mc = Minecraft.getInstance();
		Component name = mc.player.getDisplayName();
		float mouseAngleX = (float)Math.atan((((AdventMainGui.scaledRootX + 264) - mouseX) / 40.0F));
		float mouseAngleY = (float)Math.atan((((AdventMainGui.scaledRootY + 465) - 111 - mouseY) / 40.0F));

		poseStack.translate(40, 206, 1050.0F);
		RenderUtil.renderCenteredScaledText(poseStack, name, 0, 206 - 310, 1, ColourUtil.WHITE, RenderUtil.TextRenderType.OUTLINED);
		poseStack.scale(1.0F, 1.0F, -1.0F);
		poseStack.translate(0.0D, 0.0D, 1000.0D);
		poseStack.scale((float)scale * 0.625f, (float)scale * 0.625f, (float)scale * 0.625f);
		poseStack.mulPose(Axis.XP.rotationDegrees(180f));

		float yawOffset = entityToRender.yBodyRot;
		float prevYawOffset = entityToRender.yBodyRotO;
		float rotYaw = entityToRender.getYRot();
		float rotPitch = entityToRender.getXRot();
		float prevYawHead = entityToRender.yHeadRotO;
		float rotYawHead = entityToRender.yHeadRot;
		entityToRender.yBodyRot = 0;
		entityToRender.yBodyRotO = 0;
		entityToRender.setYRot(mouseAngleX * 40.0F);
		entityToRender.setXRot(-mouseAngleY * 20.0F);
		entityToRender.yHeadRot = entityToRender.getYRot();
		entityToRender.yHeadRotO = entityToRender.getYRot();
		EntityRenderDispatcher renderManager = Minecraft.getInstance().getEntityRenderDispatcher();
		MultiBufferSource.BufferSource renderBuffer = Minecraft.getInstance().renderBuffers().bufferSource();

		RenderSystem.setShaderLights(new Vector3f(0.2f, 0.5f, 1).normalize(), new Vector3f(-0.2f, -1, -0.3f));
		renderManager.setRenderShadow(false);
		RenderSystem.runAsFancy(() -> renderManager.render(entityToRender, 0, 0, 0, 0, 1, poseStack, renderBuffer, LightTexture.FULL_BRIGHT));
		renderManager.setRenderShadow(true);
		renderBuffer.endBatch();
		Lighting.setupFor3DItems();

		entityToRender.yBodyRot = yawOffset;
		entityToRender.yBodyRotO = prevYawOffset;
		entityToRender.setYRot(rotYaw);
		entityToRender.setXRot(rotPitch);
		entityToRender.yHeadRotO = prevYawHead;
		entityToRender.yHeadRot = rotYawHead;

		poseStack.popPose();
	}

	@Override
	public void removed() {
		super.removed();

		if (entityToRender != null && !(entityToRender instanceof Player))
			entityToRender.discard();

		entityToRender = null;
	}

	private void setRenderEntity() {
		if (entityToRender == null) {
			Minecraft mc = Minecraft.getInstance();
			
			if (!ClientPlayerDataManager.get().isLegitimate()) {
				entityToRender = RandomUtil.getRandomSelection(
						AoAMobs.ARCWORM,
						AoAMobs.CHARGER,
						AoAMobs.OCCULENT,
						AoAMobs.CYCLOPS,
						AoAMobs.STICKY,
						AoAMobs.KRANKY,
						AoAMobs.GINGERBREAD_MAN,
						AoAMobs.BOBO,
						AoAMobs.CHOCKO,
						AoAMobs.STITCHES,
						AoAAnimals.SPEARMINT_SNAIL,
						AoAMobs.ALARMO,
						AoAMobs.ANCIENT_GOLEM
				).get().create(mc.level);
			}
			else {
				entityToRender = mc.player;
			}
		}
	}

	@Override
	public void resize(Minecraft mc, int pWidth, int pHeight) {
		super.resize(mc, pWidth, pHeight);

		if (abilityPane != null)
			abilityPane.onResize(mc, AdventMainGui.scaledTabRootX + 250, AdventMainGui.scaledTabRootY + 50, 500, 280);
	}

	@Override
	public boolean mouseClicked(double mouseX, double mouseY, int button) {
		if (button == 0 && abilityPane != null && abilityPane.skill != null) {
			if (hoveringSkillClose) {
				abilityPane.setSkill(null);

				return true;
			}

			if (hoveringAddCycle) {
				if (addCycleLastClicked < 0) {
					addCycleLastClicked = System.currentTimeMillis();

					return true;
				}

				if (abilityPane.skill.type() != null)
					AoANetworking.sendToServer(new AddSkillCyclePacket(abilityPane.skill.type()));

				return true;
			}

			if (abilityPane.handleMouseClick(adjustedMouseX, adjustedMouseY, button))
				return true;
		}

		return super.mouseClicked(mouseX, mouseY, button);
	}

	@Override
	public boolean mouseReleased(double mouseX, double mouseY, int button) {
		if (abilityPane != null && abilityPane.skill != null) {
			if (abilityPane.handleMouseReleased(adjustedMouseX, adjustedMouseY, button))
				return true;
		}

		return super.mouseReleased(mouseX, mouseY, button);
	}

	@Override
	public boolean mouseScrolled(double mouseX, double mouseY, double lateralScroll, double verticalScroll) {
		if (abilityPane != null && abilityPane.skill != null) {
			if (abilityPane.handleMouseScroll(adjustedMouseX, adjustedMouseY, verticalScroll))
				return true;
		}

		return super.mouseScrolled(mouseX, mouseY, lateralScroll, verticalScroll);
	}

	private static class AbilityPane extends ScrollablePane {
		private AoASkill.Instance skill;
		private List<AoAAbility.Instance> sortedAbilities = null;

		private int hoveredAbility = -1;

		private float clickStartScroll = -1;

		public AbilityPane(Minecraft mc, int top, int left, int viewHeight, int viewWidth, float renderingScale) {
			super(mc, top, left, viewHeight, viewWidth, renderingScale);
		}

		private void setSkill(AoASkill.Instance skill) {
			this.skill = skill;
			this.sortedAbilities = skill == null ? null : skill.getAbilityMap().values().stream().sorted(Comparator.comparingInt(AoAAbility.Instance::getLevelReq)).collect(Collectors.toList());
		}

		@Override
		public int getFullPaneHeight() {
			return skill != null ? viewHeight + skill.getAbilityMap().size() * 25 - 140 : 0;
		}

		@Override
		public boolean handleMouseClick(double mouseX, double mouseY, int button) {
			if (!isMouseHovering())
				return false;

			clickStartScroll = distanceScrolled;

			return super.handleMouseClick(mouseX, mouseY, button);
		}

		@Override
		public boolean handleMouseReleased(double mouseX, double mouseY, int button) {
			boolean wasDragging = isDragging;
			isDragging = false;

			if (!isMouseHovering())
				return false;

			if (wasDragging && clickStartScroll == distanceScrolled && hoveredAbility != -1) {
				AoAAbility.Instance instance = sortedAbilities.get(hoveredAbility);

				if (instance.onGuiClick((int)this.mouseX - left, (int)this.mouseY - top - (hoveredAbility * 50) + (int)distanceScrolled * 2) && this.skill.type() != null)
					AoANetworking.sendToServer(new ToggleAoAAbilityPacket(skill.type(), instance));
			}

			return super.handleMouseReleased(mouseX, mouseY, button);
		}

		@Override
		public void drawPaneContents(GuiGraphics guiGraphics, int top, int left, int right, int bottom, float scrollDistance, float partialTicks) {
			Minecraft mc = Minecraft.getInstance();
			float center = (right - left) / 2f;
			PoseStack poseStack = guiGraphics.pose();

			if (isMouseHovering() && mouseX < right - 8) {
				hoveredAbility = Math.min((int)-((top - mouseY - scrollDistance) / 50f), sortedAbilities.size() - 1);
			}
			else {
				hoveredAbility = -1;
			}

			poseStack.pushPose();
			poseStack.translate(left, top, 0);
			poseStack.translate(0, -scrollDistance, 0);

			for (int i = 0; i < sortedAbilities.size(); i++) {
				AoAAbility.Instance ability = sortedAbilities.get(i);
				int colour = ColourUtil.RGB(100, 100, 100);
				boolean abilityHoverEffects = hoveredAbility == i && sortedAbilities.get(hoveredAbility).onGuiHover((int)this.mouseX - left, (int)this.mouseY - top - ((i * 50) - (int)scrollDistance));

				if (ability.getListenerState() == AoAPlayerEventListener.ListenerState.ACTIVE) {
					colour = abilityHoverEffects ? ColourUtil.RGB(255, 255, 180) : ColourUtil.WHITE;
				}
				else if (ability.getListenerState() == AoAPlayerEventListener.ListenerState.MANUALLY_DISABLED) {
					if (abilityHoverEffects)
						colour = ColourUtil.RGB(150, 150, 100);
				}

				RenderUtil.renderScaledText(poseStack, Component.literal(String.valueOf(ability.getLevelReq())), 5, 2, 1.5f, colour, RenderUtil.TextRenderType.OUTLINED);
				RenderUtil.renderCenteredScaledText(poseStack, ability.getName(), center, 2, 1.5f, colour, RenderUtil.TextRenderType.OUTLINED);
				poseStack.translate(0, 20, 0);
				RenderUtil.renderWrappedText(poseStack, ability.getDescription(), 5, 2, right - left - 10, colour, RenderUtil.TextRenderType.NORMAL);
				poseStack.translate(0, 30, 0);
			}

			poseStack.popPose();
		}

		@Override
		public void drawBackground(GuiGraphics guiGraphics) {}
	}

	private static class SkillEntry extends Button {
		private final AdventGuiTabPlayer tab;

		private final AoASkillRenderer renderer;
		private final AoASkill.Instance skill;
		private final float scale;

		private SkillEntry(AdventGuiTabPlayer tab, float posX, float posY, AoASkillRenderer renderer, AoASkill.Instance instance, float scale) {
			super((int)posX, (int)posY, renderer.guiRenderHeight(instance), renderer.guiRenderWidth(instance), instance.getName(), button -> {}, DEFAULT_NARRATION);

			this.tab = tab;
			this.renderer = renderer;
			this.skill = instance;
			this.scale = 2 * 1.6f * scale;
		}

		@Override
		public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
			if (this.visible) {
				this.isHovered = isMouseOver(mouseX - AdventMainGui.scaledTabRootX, mouseY - AdventMainGui.scaledTabRootY);
				PoseStack poseStack = guiGraphics.pose();

				poseStack.pushPose();
				poseStack.scale(2, 2, 1);
				poseStack.translate(getX(), getY(), 0);
				renderer.renderInGui(RenderContext.of(guiGraphics), skill, partialTicks, mouseX, mouseY, AoAConfigs.CLIENT.hudSkillProgressRenderType.get(), true);
				poseStack.translate(0, -5, 0);
				poseStack.scale(0.5f, 0.5f, 1);

				RenderUtil.renderCenteredScaledText(poseStack, getMessage(), width, 0, 1, isHovered ? ColourUtil.RGB(255, 255, 100) : ColourUtil.WHITE, RenderUtil.TextRenderType.OUTLINED);
				poseStack.popPose();
			}
		}

		@Override
		protected boolean clicked(double pMouseX, double pMouseY) {
			return isHovered;
		}

		@Override
		public void onClick(double pMouseX, double pMouseY) {
			tab.abilityPane.setSkill(skill);
			isHovered = false;
		}

		@Override
		public boolean isMouseOver(double mouseX, double mouseY) {
			if (!active || !visible)
				return false;

			if (mouseX <= getX() * scale)
				return false;

			if (mouseX >= getX() * scale + width * scale)
				return false;

			if (mouseY <= getY() * 2 * 1.6f)
				return false;

			return mouseY < getY() * scale + height * scale;
		}
	}
}
