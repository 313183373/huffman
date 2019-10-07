package com.huffman;

public class HuffmanNode implements Comparable<HuffmanNode> {
    private Double probability;
    private char value;
    private HuffmanNode left;
    private HuffmanNode right;

    public HuffmanNode(Double probability, char value, HuffmanNode left, HuffmanNode right) {
        this.probability = probability;
        this.value = value;
        this.left = left;
        this.right = right;
    }

    @Override
    public int compareTo(HuffmanNode node) {
        return this.probability.compareTo(node.probability);
    }

    public boolean isLeaf() {
        return left == null && right == null;
    }

    public Double getProbability() {
        return probability;
    }

    public char getValue() {
        return value;
    }

    public HuffmanNode getLeft() {
        return left;
    }

    public HuffmanNode getRight() {
        return right;
    }

    @Override
    public String toString() {
        return "HuffmanNode{" +
                "probability=" + probability +
                ", value=" + value +
                '}';
    }
}