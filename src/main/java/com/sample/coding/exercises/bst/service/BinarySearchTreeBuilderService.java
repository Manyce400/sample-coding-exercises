package com.sample.coding.exercises.bst.service;

import com.sample.coding.exercises.bst.model.Node;
import org.springframework.util.Assert;

/**
 * @author manyce400
 */
public class BinarySearchTreeBuilderService implements IBinarySearchTreeBuilderService {


    @Override
    public Node buildBinarySearchTree(int[] binarySearchTreeNodes) {
        Assert.isTrue(binarySearchTreeNodes.length > 0, "binarySearchTreeNodes cannot be empty");
        // Use the first node to build the root node
        Node root = new Node(binarySearchTreeNodes[0]);

        // Now insert nodes into our Binary Search tree
        for(int i = 1; i < binarySearchTreeNodes.length; i++) {
            root.insert(binarySearchTreeNodes[i]);
        }

        return root;
    }


}
