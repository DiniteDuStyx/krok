package pk.shop;

public enum Category {
    ALL(null),

    OFFICE(ALL),
        POINTING_DEVICES(OFFICE),
        PAPER(OFFICE),
        DRAWING(OFFICE),

    ELECTRONICS(ALL),
        COMPUTERS(ELECTRONICS),
            PERIPERALS(COMPUTERS),
            CPUS(COMPUTERS),
                AMD(CPUS),
                INTEL(CPUS),
            GRAPHIC_CARDS(COMPUTERS),

    GAMES(ALL),
        PC(GAMES),
        XBOX(GAMES),
        PS4(GAMES);

    Category parent;

    Category(Category parent) {
        this.parent = parent;
    }

    public Category getParent() {
        return parent;
    }

    public boolean hasParent() {
        return parent != null;
    }

    public boolean belongsTo(Category isParent) {
       // return (this.getParent()==parent ? true: this.getParent().belongsTo(parent));
        return this.getParent() != null && (this.getParent() == isParent || this.getParent().belongsTo(isParent));
    }

}
