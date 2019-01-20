package com.sample.coding.exercises.bst.service;

import com.sample.coding.exercises.bst.model.Node;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * @author manyce400
 */
@RunWith(MockitoJUnitRunner.class)
public class BinarySearchTreeBuilderServiceTest {


    @Spy
    private BinarySearchTreeBuilderService binarySearchTreeBuilderService;

    @Test
    public void testBuildBinarySearchTree() {
        int [] binarySearchTreeNodes = new int[] {5, 9, 3, 34, 20, 25, 54, 77, 44};
        Node root = binarySearchTreeBuilderService.buildBinarySearchTree(binarySearchTreeNodes);
        System.out.println("root = " + root.getValue() + " left = " + root.getLeft().getValue() + " right = "+ root.getRight().getValue());
        
        boolean contains = root.contains(3);
        System.out.println("contains = " + contains);
        
        root.printInOrder();
    }
}
