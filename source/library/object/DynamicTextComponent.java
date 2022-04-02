package net.tslat.aoa3.library.object;

import net.minecraft.network.chat.TextComponent;

import java.util.Objects;
import java.util.function.Supplier;

public class DynamicTextComponent extends TextComponent {
	private final Supplier<String> text;

	public DynamicTextComponent(Supplier<String> text) {
		super(text.get());

		this.text = text;
	}

	@Override
	public String getText() {
		return text.get();
	}

	@Override
	public String getContents() {
		return text.get();
	}

	@Override
	public TextComponent plainCopy() {
		return new DynamicTextComponent(this.text);
	}

	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		else if (!(other instanceof DynamicTextComponent otherComponent)) {
			return false;
		}
		else {
			return this.getText().equals(otherComponent.getText()) && this.getSiblings().equals(otherComponent.getSiblings()) && Objects.equals(this.getStyle(), otherComponent.getStyle());
		}
	}

	@Override
	public String toString() {
		return "DynamicTextComponent{currentValue='" + this.getText() + "\'" + ", siblings=" + this.getSiblings() + ", style=" + this.getStyle() + "}";
	}
}
