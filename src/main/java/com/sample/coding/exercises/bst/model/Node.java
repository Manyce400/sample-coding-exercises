package com.sample.coding.exercises.bst.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Represents a node of a Binary Search Tree.
 *
 * @author manyce400
 */
public class Node {



    private int value;

    private Node left;

    private Node right;


    public Node(int value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
                .append("value", value)
                .append("left", left)
                .append("right", right)
                .toString();
    }

    public void insert(int valueToInsert) {
        if(valueToInsert  <= value) {
            // Value being inserted is less than the current node so u want to insert to the left
            if(left == null) {
                left = new Node(valueToInsert);
            } else {
                // Insert towards left parent node since left is not empty
                left.insert(valueToInsert);
            }
        } else {
            // Value being inserted is greater than current node value so insert to the right
            if(right == null) {
                right = new Node(valueToInsert);
            } else {
                right.insert(valueToInsert);
            }
        }
    }

    public boolean contains(int findValue) {
        if(findValue == value) {
            // Base case
            return true;
        } else {
            if(findValue  < value) {
                if(left == null) {
                    // We are at the end of the left side of tree and didn't find value
                    return false;
                } else {
                    return left.contains(findValue);
                }
            } else if(findValue > value) {
                if(right == null) {
                    // We are at the end of the right side of tree and didn't find value
                    return false;
                } else {
                    return right.contains(findValue);
                }
            }
        }

        return false;
    }

    /**
     * Execute and print tree traversal pre-order.
     *
     * 1)  Visit the root node first
     * 2)  Visit child node to the left
     * 3)  Visit child node to the right
     */
    public void printPreOrder() {
        // Print the current node
        System.out.println(value);

        // First visit the left node
        if(left != null) {
            left.printInOrder();
        }

        // Now visit the right node
        if(right != null) {
            right.printInOrder();
        }
    }

    /**
     * Execute and print tree traversal in order.
     *
     * 1)  Visit the left node first
     * 2)  Visit the root node
     * 3)  Visit the right node
     */
    public void printInOrder() {
        // First visit the left node
        if(left != null) {
            left.printInOrder();
        }

        // Print the current node
        System.out.println(value);

        // Now visit the right node
        if(right != null) {
            right.printInOrder();
        }
    }

    /**
     * Execute and print tree traversal in post order.
     *
     * 1)  Visit the left node first
     * 2)  Visit the right node
     * 3)  Visit the root node last
     */
    public void printPostOrder() {
        // First visit the left node
        if(left != null) {
            left.printInOrder();
        }

        // Now visit the right node
        if(right != null) {
            right.printInOrder();
        }

        // Print the current node
        System.out.println(value);
    }
}
