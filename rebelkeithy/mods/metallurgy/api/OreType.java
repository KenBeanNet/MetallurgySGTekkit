package rebelkeithy.mods.metallurgy.api;

public enum OreType
{
    ORE(true),
    CATALYST(true),
    ALLOY(false),
    RESPAWN(true),
    DROP(true);
    private boolean generates;

    private OreType(boolean var3)
    {
        this.generates = var3;
    }

    public boolean generates()
    {
        return this.generates;
    }
}
