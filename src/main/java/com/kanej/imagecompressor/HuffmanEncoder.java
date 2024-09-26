package com.kanej.imagecompressor;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class HuffmanEncoder {

    // node class for the Huffman Tree
    private static class Node implements Comparable<Node> {
        int frequency;
        char character;
        Node left, right;

        Node(char character, int frequency) {
            this.character = character;
            this.frequency = frequency;
        }

        Node(int frequency, Node left, Node right) {
            this.frequency = frequency;
            this.left = left;
            this.right = right;
        }

        public boolean isLeaf() {
            return left == null && right == null;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.frequency, o.frequency);
        }
    }

    public Map<Character, String> generateCodes(Map<Character, Integer> frequencyMap) {
        Map<Character, String> huffmanCodes = new HashMap<>();
        Node root = buildHuffmanTree(frequencyMap);
        buildCode(huffmanCodes, root, "");
        return huffmanCodes;
    }

    private Node buildHuffmanTree(Map<Character, Integer> frequencyMap) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        for (Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {
            pq.offer(new Node(entry.getKey(), entry.getValue()));
        }

        while (pq.size() > 1) {
            Node left = pq.poll();
            Node right = pq.poll();
            Node parent = new Node(left.frequency + right.frequency, left, right);
            pq.offer(parent);
        }

        return pq.poll();
    }

    private void buildCode(Map<Character, String> huffmanCodes, Node node, String code) {
        if (node.isLeaf()) {
            huffmanCodes.put(node.character, code);
        } else {
            buildCode(huffmanCodes, node.left, code + '0');
            buildCode(huffmanCodes, node.right, code + '1');
        }
    }

    public String encodeBlock(double[][] quantizedBlock, Map<Character, String> huffmanCodes) {
        StringBuilder encodedBlock = new StringBuilder();
        for (double[] row : quantizedBlock) {
            for (double value : row) {
                char symbol = (char) value;  // simplified char conversion
                encodedBlock.append(huffmanCodes.get(symbol));  // convert to binary
            }
        }
        return encodedBlock.toString();
    }
}
