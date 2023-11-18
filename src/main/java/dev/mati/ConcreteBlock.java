package dev.mati;

public class ConcreteBlock implements Block{
    private String color;
    private String material;

    public ConcreteBlock(String color, String material) {
        this.color = color;
        this.material = material;
    }

    @Override
    public String getColor() {
        return this.color;
    }

    @Override
    public String getMaterial() {
        return this.material;
    }
}
