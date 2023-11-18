package dev.mati;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class Wall implements Structure {
    //assumption: CompositBlock are counted as 1 + its list. No cycles
    private List<Block> blocks;

    public static void main(String[] args) {
        test1(prepareWall());
    }

    @Override
    public Optional<Block> findBlockByColor(String color) {
        if(blocks == null)
            return Optional.empty();
        return flattenBFS().stream()
                .filter(b -> b.getColor().equals(color))
                .findAny();
    }

    @Override
    public List<Block> findBlocksByMaterial(String material) {
        if(blocks == null)
            return null;
        return flattenBFS().stream()
                .filter(b -> b.getMaterial().equals(material))
                .toList();
    }

    @Override
    public int count() {
        if (blocks == null)
            return -1;
        return flattenBFS().size();
    }
    private LinkedList<Block> flattenBFS() {
        if (blocks.isEmpty())
            return new LinkedList<>();

        LinkedList<Block> flatList = new LinkedList<>();

        LinkedList<Block> q = new LinkedList<>();
        for(Block b: blocks) {
            if(b instanceof CompositeBlock)
                q.addAll(((CompositeBlock) b).getBlocks());
            flatList.add(b);
        }

        while(!q.isEmpty()) {
            int size = q.size();
            for(int i = 0; i < size; i++) {
                Block curr = q.poll();
                if(curr instanceof CompositeBlock)
                    q.addAll(((CompositeBlock) curr).getBlocks());
                flatList.add(curr);
            }
        }

        return flatList;
    }
    private static Wall prepareWall() {
        Wall w = new Wall();
//////////////// prepare wall//////////////////////
        w.blocks = new LinkedList<>();

        ConcreteCompositeBlock cb1 = new ConcreteCompositeBlock("R","Y");
        ConcreteCompositeBlock cb2 = new ConcreteCompositeBlock("B","Y");
        w.blocks.addAll(List.of(
                new ConcreteBlock("B","X"),
                new ConcreteBlock("R","Y"),
                new ConcreteBlock("B","X"),
                cb1,
                cb2
        ));

        ConcreteCompositeBlock cb3 = new ConcreteCompositeBlock("B","Y");
        cb3.getBlocks().add(new ConcreteBlock("B","Y"));
        cb1.getBlocks().addAll(List.of(
                new ConcreteBlock("R","X"),
                cb3,
                new ConcreteBlock("B","X"),
                new ConcreteBlock("R","Y")
        ));

        ConcreteCompositeBlock cb4 = new ConcreteCompositeBlock("B","X");
        cb2.getBlocks().addAll(List.of(
                cb4,
                new ConcreteBlock("R","Y")
        ));

        ConcreteCompositeBlock cb5 = new ConcreteCompositeBlock("B","Y");
        cb4.getBlocks().addAll(List.of(
                new ConcreteBlock("B","X"),
                cb5,
                new ConcreteBlock("R","X")
        ));

        cb5.getBlocks().addAll(List.of(
                new ConcreteBlock("B","Y"),
                new ConcreteBlock("B","X"),
                new ConcreteBlock("R","Y")
        ));
        ///////////////////////////////prepare wall - end  //////////////////////////////////////
        return w;
    }
    private static void test1(Wall w) {

        //////////////////////tests
        System.out.println(String.format("Number of all elements in the wall: %d", w.count()));
        System.out.println(String.format("Number of Xs in the wall: %d", w.findBlocksByMaterial("X").size()));
        System.out.println(String.format("Number of Ys in the wall: %d", w.findBlocksByMaterial("Y").size()));
        String color = "G";
        if(!w.findBlockByColor(color).isPresent())
            System.out.println(color + " not found");
        else
            System.out.println(color + " present");
        color = "R";
        if(!w.findBlockByColor(color).isPresent())
            System.out.println(color + "not found");
        else
            System.out.println(color + " present");
        /////////////////////////tests - end /////////

    }

}