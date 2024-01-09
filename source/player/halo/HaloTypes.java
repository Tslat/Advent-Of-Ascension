package net.tslat.aoa3.player.halo;

public enum HaloTypes {
    DONATOR,
    SUPER_DONATOR,
    WIKI_EDITOR,
    STAFF,
    TSLAT;

    public enum Selectable {
        DONATOR,
        SUPER_DONATOR,
        WIKI_EDITOR;

        public HaloTypes toBaseType() {
            return switch (this) {
                case DONATOR -> HaloTypes.DONATOR;
                case SUPER_DONATOR -> HaloTypes.SUPER_DONATOR;
                case WIKI_EDITOR -> HaloTypes.WIKI_EDITOR;
                default -> null;
            };
        }
    }
}
