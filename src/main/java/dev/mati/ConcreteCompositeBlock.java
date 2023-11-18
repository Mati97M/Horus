package dev.mati;

import java.util.ArrayList;
import java.util.List;

public class ConcreteCompositeBlock implements CompositeBlock{
    private String color;
    private String material;
    private List<Block> blocks;

    public ConcreteCompositeBlock(String color, String material) {
        this.color = color;
        this.material = material;
        this.blocks = new ArrayList<>();
    }

    @Override
    public String getColor() {
        return this.color;
    }

    @Override
    public String getMaterial() {
        return this.material;
    }

    @Override
    public List<Block> getBlocks() {
        return blocks;
    }
}
