package rebelkeithy.mods.metallurgy.core.metalsets;

import cpw.mods.fml.common.IWorldGenerator;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import java.util.Arrays;
import java.util.Map;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.EnumHelper;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import rebelkeithy.mods.metablock.MetaBlock;
import rebelkeithy.mods.metablock.SubBlock;
import rebelkeithy.mods.metallurgy.api.IOreInfo;
import rebelkeithy.mods.metallurgy.api.OreType;
import rebelkeithy.mods.metallurgy.core.MetalInfoDatabase;

public class OreInfo implements IOreInfo, IWorldGenerator
{
    protected String setName;
    protected String name;
    protected CreativeTabs tab;
    protected OreType type;
    public int oreID;
    public int oreMeta;
    protected int blockID;
    protected int blockMeta;
    protected int brickID;
    protected int brickMeta;
    protected int itemIDs;
    protected String dropName;
    protected int dropMin;
    protected int dropMax;
    protected String[] alloyRecipe;
    protected int abstractorXP;
    protected int blockLvl;
    protected int pickLvl;
    protected int toolDura;
    protected int toolDamage;
    protected int toolSpeed;
    protected int toolEnchant;
    protected int helmetArmor;
    protected int chestArmor;
    protected int legsArmor;
    protected int bootsArmor;
    protected int armorDura;
    protected int dungeonLootChance;
    protected int dungeonLootAmount;
    protected int veinCount;
    protected int oreCount;
    protected int minHeight;
    protected int maxHeight;
    protected int veinChance;
    protected int veinDensity;
    protected String[] diminsions;
    public SubBlock ore;
    public SubBlock block;
    public SubBlock brick;
    public Item dust;
    public Item ingot;

    public OreInfo(Map var1, CreativeTabs var2)
    {
        this.setName = (String)var1.get("Metal Set");
        this.name = (String)var1.get("Name");
        this.tab = var2;
        System.out.println("reading " + this.name);

        if (((String)var1.get("Type")).equals("Ore"))
        {
            this.type = OreType.ORE;
        }
        else if (((String)var1.get("Type")).equals("Catalyst"))
        {
            this.type = OreType.CATALYST;
        }
        else if (((String)var1.get("Type")).equals("Alloy"))
        {
            this.type = OreType.ALLOY;
        }
        else if (((String)var1.get("Type")).equals("Respawn"))
        {
            this.type = OreType.RESPAWN;
        }
        else if (((String)var1.get("Type")).equals("Drop"))
        {
            this.type = OreType.DROP;
        }

        this.alloyRecipe = ((String)var1.get("Alloy Recipe")).split("\" \"");
        System.out.println("alloy recipe: " + Arrays.toString(this.alloyRecipe));

        for (int var3 = 0; var3 < this.alloyRecipe.length; ++var3)
        {
            this.alloyRecipe[var3] = "dust" + this.alloyRecipe[var3].replace("\"", "");
        }

        if (this.type.generates())
        {
            this.oreID = Integer.parseInt(((String)var1.get("Ore ID")).split(":")[0]);
            this.oreMeta = Integer.parseInt(((String)var1.get("Ore ID")).split(":")[1]);
        }

        if (!((String)var1.get("Block ID")).equals("0"))
        {
            this.blockID = Integer.parseInt(((String)var1.get("Block ID")).split(":")[0]);
            this.blockMeta = Integer.parseInt(((String)var1.get("Block ID")).split(":")[1]);
        }

        if (!((String)var1.get("Brick ID")).equals("0"))
        {
            this.brickID = Integer.parseInt(((String)var1.get("Brick ID")).split(":")[0]);
            this.brickMeta = Integer.parseInt(((String)var1.get("Brick ID")).split(":")[1]);
        }

        if (this.type == OreType.DROP)
        {
            this.dropName = ((String)var1.get("Drops")).replace("\"", "");

            if (((String)var1.get("Drop Amount")).contains("-"))
            {
                this.dropMin = Integer.parseInt(((String)var1.get("Drop Amount")).split("-")[0]);
                this.dropMax = Integer.parseInt(((String)var1.get("Drop Amount")).split("-")[1]);
            }
            else
            {
                this.dropMin = Integer.parseInt((String)var1.get("Drop Amount"));
                this.dropMax = this.dropMin;
            }
        }
        else
        {
            this.dropName = null;
        }

        this.itemIDs = Integer.parseInt((String)var1.get("Item IDs"));
        this.abstractorXP = Integer.parseInt((String)var1.get("Abstractor XP"));
        this.blockLvl = Integer.parseInt((String)var1.get("Block lvl"));
        System.out.println("Block level default: " + (String)var1.get("Block lvl"));
        System.out.println("Block level set to : " + this.blockLvl);

        if (this.type != OreType.CATALYST && this.type != OreType.DROP)
        {
            this.pickLvl = Integer.parseInt((String)var1.get("Pick lvl"));
            this.toolDura = Integer.parseInt((String)var1.get("Durability"));
            this.toolDamage = Integer.parseInt((String)var1.get("Damage"));
            this.toolSpeed = Integer.parseInt((String)var1.get("Speed"));
            this.toolEnchant = Integer.parseInt((String)var1.get("Enchantability"));
            this.helmetArmor = Integer.parseInt((String)var1.get("Helmet Armor"));
            this.chestArmor = Integer.parseInt((String)var1.get("Chestplate Armor"));
            this.legsArmor = Integer.parseInt((String)var1.get("Leggings Armor"));
            this.bootsArmor = Integer.parseInt((String)var1.get("Boots Armor"));
            this.armorDura = Integer.parseInt((String)var1.get("Durability Multiplier"));
        }

        this.dungeonLootChance = Integer.parseInt((String)var1.get("Dungeon Loot Chance"));
        this.dungeonLootAmount = Integer.parseInt((String)var1.get("Dungeon Loot Amount"));

        if (this.type.generates())
        {
            this.veinCount = Integer.parseInt((String)var1.get("Veins Per Chunk"));
            this.oreCount = Integer.parseInt((String)var1.get("Ores Per Vein"));
            this.minHeight = Integer.parseInt((String)var1.get("Min Level"));
            this.maxHeight = Integer.parseInt((String)var1.get("Max Level"));
            this.veinChance = Integer.parseInt((String)var1.get("Vein Chance Per Chunk"));
            this.veinDensity = Integer.parseInt((String)var1.get("Vein Density"));
            this.diminsions = ((String)var1.get("Diminsions")).split(" ");
        }
    }

    public void initConfig(Configuration var1)
    {
        String var2;

        if (!this.type.equals(OreType.RESPAWN))
        {
            if ((this.type == OreType.ORE || this.type == OreType.CATALYST || this.type == OreType.DROP) && this.oreID != 0)
            {
                var2 = var1.get(this.name + ".IDs", "Ore", this.oreID + ":" + this.oreMeta).getString();
                this.oreID = Integer.parseInt(var2.split(":")[0]);
                this.oreMeta = Integer.parseInt(var2.split(":")[1]);
            }

            if (this.type != OreType.DROP && this.blockID != 0)
            {
                var2 = var1.get(this.name + ".IDs", "Block", this.blockID + ":" + this.blockMeta).getString();
                this.blockID = Integer.parseInt(var2.split(":")[0]);
                this.blockMeta = Integer.parseInt(var2.split(":")[1]);
            }

            if (this.type != OreType.DROP && this.brickID != 0)
            {
                var2 = var1.get(this.name + ".IDs", "Brick", this.brickID + ":" + this.brickMeta).getString();
                this.brickID = Integer.parseInt(var2.split(":")[0]);
                this.brickMeta = Integer.parseInt(var2.split(":")[1]);
            }

            if (this.type != OreType.DROP)
            {
                this.itemIDs = var1.get(this.name + ".IDs", "Item IDs (reserves 50)", this.itemIDs).getInt();
                this.abstractorXP = var1.get(this.name + ".misc", "abstractor xp", this.abstractorXP).getInt();
            }

            this.blockLvl = var1.get(this.name + ".misc", "Block Hardness Level", this.blockLvl).getInt();

            if (this.type != OreType.CATALYST && this.type != OreType.DROP)
            {
                this.pickLvl = var1.get(this.name + ".Tool Info", "Pick Level", this.pickLvl).getInt();
                this.toolDura = var1.get(this.name + ".Tool Info", "Durability", this.toolDura).getInt();
                this.toolDamage = var1.get(this.name + ".Tool Info", "Damage", this.toolDamage).getInt();
                this.toolSpeed = var1.get(this.name + ".Tool Info", "Speed", this.toolSpeed).getInt();
                this.toolEnchant = var1.get(this.name + ".Tool Info", "Enchantability", this.toolEnchant).getInt();
                this.helmetArmor = var1.get(this.name + ".Armor Info", "Helmet Armor", this.helmetArmor).getInt();
                this.chestArmor = var1.get(this.name + ".Armor Info", "Chestplate Armor", this.chestArmor).getInt();
                this.legsArmor = var1.get(this.name + ".Armor Info", "Leggings Armor", this.legsArmor).getInt();
                this.bootsArmor = var1.get(this.name + ".Armor Info", "Boots Armor", this.bootsArmor).getInt();
                this.armorDura = var1.get(this.name + ".Armor Info", "Durability Multiplier", this.armorDura).getInt();
            }

            if (this.type != OreType.DROP)
            {
                this.dungeonLootChance = var1.get(this.name + ".World Gen", "Dungeon Loot Chance", this.dungeonLootChance).getInt();
                this.dungeonLootAmount = var1.get(this.name + ".World Gen", "Dungeon Loot Amount", this.dungeonLootAmount).getInt();
            }
        }

        if (this.type.generates())
        {
            this.veinCount = var1.get(this.name + ".World Gen", "Veins Per Chunk", this.veinCount).getInt();
            this.oreCount = var1.get(this.name + ".World Gen", "Vein Size", this.oreCount).getInt();
            this.minHeight = var1.get(this.name + ".World Gen", "Min Height", this.minHeight).getInt();
            this.maxHeight = var1.get(this.name + ".World Gen", "Max Height", this.maxHeight).getInt();
            this.veinChance = var1.get(this.name + ".World Gen", "Vein Chance", this.veinChance).getInt();
            this.veinDensity = var1.get(this.name + ".World Gen", "Vein Density", this.veinDensity).getInt();
            var2 = "";

            if (this.diminsions.length > 0)
            {
                var2 = this.diminsions[0];

                for (int var3 = 1; var3 < this.diminsions.length; ++var3)
                {
                    var2 = var2 + " " + this.diminsions[var3];
                }
            }

            this.diminsions = var1.get(this.name + ".World Gen", "Diminsions", var2).getString().split(" ");
        }
    }

    public void init()
    {
        System.out.println("Initializeing Ore " + this.name);

        if (!this.type.equals(OreType.RESPAWN))
        {
            if (this.type.generates() && this.oreID != 0)
            {
                this.ore = (new SubBlock(this.oreID, this.oreMeta, "Metallurgy:" + this.setName + "/" + this.name + "Ore")).setUnlocalizedName(this.setName + this.oreID).setCreativeTab(this.tab);

                if (this.type == OreType.DROP)
                {
                    System.out.println("getting block drop " + this.dropName);
                    this.ore.setBlockDrops(MetalInfoDatabase.getItem(this.dropName), this.dropMin, this.dropMax);
                }
            }

            if (this.type != OreType.DROP && this.blockID != 0)
            {
                this.block = (new SubBlock(this.blockID, this.blockMeta, "Metallurgy:" + this.setName + "/" + this.name + "Block")).setUnlocalizedName(this.setName + this.blockID).setCreativeTab(this.tab);
            }

            if (this.type != OreType.DROP && this.brickID != 0)
            {
                this.brick = (new SubBlock(this.brickID, this.brickMeta, "Metallurgy:" + this.setName + "/" + this.name + "Brick")).setUnlocalizedName(this.setName + this.brickID).setCreativeTab(this.tab);
            }

            if (this.type != OreType.DROP)
            {
                this.dust = (new Item(this.itemIDs)).setUnlocalizedName("Metallurgy:" + this.setName + "/" + this.name + "Dust").setCreativeTab(this.tab);
                this.ingot = (new ItemMetallurgy(this.itemIDs + 1)).setSmeltinExperience((float)this.abstractorXP / 3.0F).setUnlocalizedName("Metallurgy:" + this.setName + "/" + this.name + "Ingot").setCreativeTab(this.tab);
            }

            if (this.type != OreType.CATALYST && this.type != OreType.DROP)
            {
                EnumToolMaterial var1 = EnumHelper.addToolMaterial(this.name, this.pickLvl, this.toolDura, (float)this.toolSpeed, this.toolDamage, this.toolEnchant);
                var1.customCraftingMaterial = this.ingot;
             }
        }

        if (this.type.generates())
        {
            GameRegistry.registerWorldGenerator(this);
        }
    }

    public void load()
    {
        if (!this.type.equals(OreType.RESPAWN))
        {
            if (this.oreID != 0)
            {
                MetaBlock.registerID(this.oreID);
            }

            if (this.brickID != 0)
            {
                MetaBlock.registerID(this.brickID);
            }

            if (this.blockID != 0)
            {
                MetaBlock.registerID(this.blockID);
            }

            this.setLevels();

            if (this.type != OreType.DROP)
            {
                this.addRecipes();
            }

            this.registerMetal();
        }
    }

    public void setLevels()
    {
        if (this.ore != null)
        {
            MinecraftForge.setBlockHarvestLevel(this.ore.getBlock(), this.oreMeta, "pickaxe", this.blockLvl);
            this.ore.setHardness(2.0F).setResistance(1.0F);
        }

        if (this.block != null)
        {
            MinecraftForge.setBlockHarvestLevel(this.block.getBlock(), this.blockMeta, "pickaxe", this.blockLvl);
        }

        if (this.brick != null)
        {
            MinecraftForge.setBlockHarvestLevel(this.brick.getBlock(), this.brickMeta, "pickaxe", this.blockLvl);
        }
    }

    public void addRecipes()
    {
        if (this.type.generates() && this.ore != null)
        {
            FurnaceRecipes.smelting().addSmelting(this.oreID, this.oreMeta, new ItemStack(this.ingot), 0.0F);
        }

        FurnaceRecipes.smelting().addSmelting(this.dust.itemID, 0, new ItemStack(this.ingot), 0.0F);
        ShapedOreRecipe var1;

        if (this.block != null)
        {
            var1 = new ShapedOreRecipe(new ItemStack(this.blockID, 1, this.blockMeta), new Object[] {"XXX", "XXX", "XXX", 'X', "ingot" + this.name});
            GameRegistry.addRecipe(var1);
            GameRegistry.addShapelessRecipe(new ItemStack(this.ingot, 9), new Object[] {new ItemStack(this.blockID, 1, this.blockMeta)});
        }

        if (this.brick != null)
        {
            var1 = new ShapedOreRecipe(new ItemStack(this.brickID, 4, this.brickMeta), new Object[] {"XX", "XX", 'X', "ingot" + this.name});
            GameRegistry.addRecipe(var1);
            GameRegistry.addShapelessRecipe(new ItemStack(this.ingot, 1), new Object[] {new ItemStack(this.brickID, 1, this.brickMeta)});
        }

        if (this.type == OreType.ALLOY)
        {
            System.out.println("Adding alloy recipe " + this.alloyRecipe[0] + " + " + this.alloyRecipe[1] + " for " + this.name);
            GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(this.dust, 2), this.alloyRecipe));
        }
    }

    public void registerMetal()
    {
        if (this.ore != null)
        {
            OreDictionary.registerOre("ore" + this.name, new ItemStack(this.oreID, 1, this.oreMeta));
        }

        if (this.block != null)
        {
            OreDictionary.registerOre("block" + this.name, new ItemStack(this.blockID, 1, this.blockMeta));
        }

        if (this.type != OreType.DROP)
        {
            OreDictionary.registerOre("ingot" + this.name, this.ingot);
            OreDictionary.registerOre("dust" + this.name, this.dust);
        }
    }

    public void registerNames()
    {
        if (this.type != OreType.RESPAWN)
        {
            if (this.type.generates() && this.ore != null)
            {
                LanguageRegistry.addName(new ItemStack(this.oreID, 1, this.oreMeta), this.name + " Ore");
            }

            if (this.block != null)
            {
                LanguageRegistry.addName(new ItemStack(this.blockID, 1, this.blockMeta), this.name + " Block");
            }

            if (this.brick != null)
            {
                LanguageRegistry.addName(new ItemStack(this.brickID, 1, this.brickMeta), this.name + " Brick");
            }

            if (this.type != OreType.DROP)
            {
                LanguageRegistry.addName(this.dust, this.name + " Dust");
                LanguageRegistry.addName(this.ingot, this.name + " Ingot");
            }
        }
    }

    private boolean spawnsInDim(int var1)
    {
        String[] var2 = this.diminsions;
        int var3 = var2.length;

        for (int var4 = 0; var4 < var3; ++var4)
        {
            String var5 = var2[var4];
            int var6;

            if (var5.contains("-") && !var5.startsWith("-"))
            {
                var6 = Integer.parseInt(var5.split("-")[0]);
                int var7 = Integer.parseInt(var5.split("-")[1]);

                if (var6 > var7)
                {
                    int var8 = var6;
                    var6 = var7;
                    var7 = var8;
                }

                if (var1 >= var6 && var1 <= var7)
                {
                    return true;
                }
            }
            else
            {
                var6 = Integer.parseInt(var5);

                if (var1 == var6)
                {
                    return true;
                }
            }
        }

        return false;
    }

    public void generate(Random var1, int var2, int var3, World var4, IChunkProvider var5, IChunkProvider var6)
    {
        if (this.type.generates() && (this.ore != null || this.type == OreType.RESPAWN) && this.spawnsInDim(var4.provider.dimensionId))
        {
            for (int var7 = 0; var7 < this.veinCount; ++var7)
            {
                int var8 = var2 * 16 + var1.nextInt(16);
                int var9 = var1.nextInt(this.maxHeight - this.minHeight) + this.minHeight;
                int var10 = var3 * 16 + var1.nextInt(16);
                (new MetallurgyWorldGenMinable(this.oreID, this.oreMeta, this.oreCount, this.veinDensity, new Object[] {Integer.valueOf(Block.stone.blockID), Integer.valueOf(0)})).generate(var4, var1, var8, var9, var10);
            }
        }
    }

    public String getName()
    {
        return this.name;
    }

    public ItemStack getOre()
    {
        return this.oreID != 0 ? new ItemStack(this.oreID, 1, this.oreMeta) : null;
    }

    public ItemStack getBlock()
    {
        return this.blockID != 0 ? new ItemStack(this.blockID, 1, this.blockMeta) : null;
    }

    public ItemStack getBrick()
    {
        return this.brickID != 0 ? new ItemStack(this.brickID, 1, this.brickMeta) : null;
    }

    public ItemStack getDust()
    {
        return this.dust != null ? new ItemStack(this.dust) : null;
    }

    public ItemStack getIngot()
    {
        return this.ingot != null ? new ItemStack(this.ingot) : null;
    }

    public String[] getAlloyRecipe()
    {
        return this.alloyRecipe;
    }

    public OreType getType()
    {
        return this.type;
    }

    public String getDrop()
    {
        return this.dropName;
    }

    public int getDropAmountMin()
    {
        return this.dropMin;
    }

    public int getDropAmountMax()
    {
        return this.dropMax;
    }
}
