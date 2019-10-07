package com.huffman;

import java.util.*;

public class Huffman {

    private static final String LEFT_STRING = "0";
    private static final String RIGHT_STRING = "1";
    private PriorityQueue<HuffmanNode> table = new PriorityQueue<>();
    private Map<Character, String> codeMap = new HashMap<>();
    private Map<String, Character> codeMapReverse = new HashMap<>();

    private HuffmanNode buildHuffmanTrie() {
        while (table.size() > 1) {
            HuffmanNode least1 = table.poll();
            HuffmanNode least2 = table.poll();
            HuffmanNode combined = new HuffmanNode(least1.getProbability() + least2.getProbability(), '\0', least1, least2);
            table.add(combined);
        }
        return table.poll();
    }

    private Map<Character, String> buildCodeMap(HuffmanNode node) {
        buildCodeMap(node, "");
        return codeMap;
    }

    private void buildCodeMap(HuffmanNode node, String code) {
        if (node.isLeaf()) {
            codeMap.put(node.getValue(), code);
            codeMapReverse.put(code, node.getValue());
        } else {
            buildCodeMap(node.getLeft(), code + LEFT_STRING);
            buildCodeMap(node.getRight(), code + RIGHT_STRING);
        }
    }

    private void initTable() {
        table.add(new HuffmanNode(0.2, ' ', null, null));
        table.add(new HuffmanNode(0.105, 'E', null, null));
        table.add(new HuffmanNode(0.072, 'T', null, null));
        table.add(new HuffmanNode(0.0654, 'O', null, null));
        table.add(new HuffmanNode(0.063, 'A', null, null));
        table.add(new HuffmanNode(0.059, 'N', null, null));
        table.add(new HuffmanNode(0.055, 'I', null, null));
        table.add(new HuffmanNode(0.054, 'R', null, null));
        table.add(new HuffmanNode(0.052, 'S', null, null));
        table.add(new HuffmanNode(0.047, 'H', null, null));
        table.add(new HuffmanNode(0.035, 'D', null, null));
        table.add(new HuffmanNode(0.029, 'L', null, null));
        table.add(new HuffmanNode(0.023, 'C', null, null));
        table.add(new HuffmanNode(0.0225, 'F', null, null));
        table.add(new HuffmanNode(0.0225, 'U', null, null));
        table.add(new HuffmanNode(0.021, 'M', null, null));
        table.add(new HuffmanNode(0.0175, 'P', null, null));
        table.add(new HuffmanNode(0.012, 'Y', null, null));
        table.add(new HuffmanNode(0.012, 'W', null, null));
        table.add(new HuffmanNode(0.011, 'G', null, null));
        table.add(new HuffmanNode(0.0105, 'B', null, null));
        table.add(new HuffmanNode(0.008, 'V', null, null));
        table.add(new HuffmanNode(0.003, 'K', null, null));
        table.add(new HuffmanNode(0.002, 'X', null, null));
        table.add(new HuffmanNode(0.001, 'J', null, null));
        table.add(new HuffmanNode(0.001, 'Q', null, null));
        table.add(new HuffmanNode(0.001, 'Z', null, null));
    }

    public String encode(String text) {
        StringBuilder result = new StringBuilder();
        for (char c : text.toUpperCase().toCharArray()) {
            if (codeMap.containsKey(c)) {
                result.append(codeMap.get(c));
            }
        }
        return result.toString();
    }

    public String decode(String bits) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < bits.length(); i++) {
            String substring = bits.substring(0, i + 1);
            if (codeMapReverse.containsKey(substring)) {
                result.append(codeMapReverse.get(substring));
                bits = bits.substring(i + 1);
                i = -1;
                continue;
            }
            if (substring.length() == bits.length()) {
                System.out.println("Can't decode the message");
                return null;
            }
        }
        return result.toString();
    }

    public static void main(String[] args) {
        Huffman huffman = new Huffman();
        huffman.initTable();
        HuffmanNode rootNode = huffman.buildHuffmanTrie();
        Map<Character, String> codeMap = huffman.buildCodeMap(rootNode);
        for (Map.Entry<Character, String> entry : codeMap.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
        System.out.println(huffman.decode(huffman.encode("HELLO WORLD")));
    }
}
