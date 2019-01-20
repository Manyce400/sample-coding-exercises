package com.sample.coding.exercises.heap;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.util.Assert;

import java.util.Arrays;

/**
 * @author manyce400
 */
public class MinHeap {


    private int[] items;

    // returns actual part of array that is used
    public int size = 0;

    public MinHeap(int capacity) {
        items = new int[capacity];
        // immidiately initialize all elements of the array to signify that they dont contain any
//        for(int i =0 ; i < capacity; i ++) {
//            items[i] = Integer.MAX_VALUE;
//        }
    }

    public int getParent(int childIndex) {
        int index = getParentIndex(childIndex);
        return  items[index];
    }

    public boolean hasParent(int childIndex) {
        // The very first element in the array is the root parent
        if(childIndex == 0) {
            return false;
        }

        int parent = getParent(childIndex);
        return parent >= 0;
    }

    public int getParentIndex(int childIndex) {
        Assert.isTrue(childIndex >= 0, "childIndex has to be a valid array index");
        return (childIndex - 1) / 2;
    }

    public int getLeftChild(int parentIndex) {
        int index = getLeftChildIndex(parentIndex);
        return  items[index];
    }

    public boolean hasLeftChild(int parentIndex) {
        int index = getLeftChildIndex(parentIndex);
        return index < items.length;
    }

    public int getLeftChildIndex(int parentIndex) {
        Assert.isTrue(parentIndex >= 0, "parentIndex has to be a valid array index");
        return  (parentIndex * 2) + 1;
    }

    public int getRightChild(int parentIndex) {
        int index = getRightChildIndex(parentIndex);
        return items[index];
    }

    public boolean hasRightChild(int parentIndex) {
        int index = getRightChildIndex(parentIndex);
        return index < items.length;
    }

    public int getRightChildIndex(int parentIndex) {
        Assert.isTrue(parentIndex >= 0, "parentIndex has to be a valid array index");
        return  (parentIndex * 2) + 2;
    }


    public void addItemToHeap(int value) {
        System.out.println("The value of size:=> " + size);
        items[size] = value;
        heapifyUp();
        size++;
    }

    public void heapifyUp() {
        int lastIndex = size;
        int lastElement = items[size];

        System.out.println("Running heapify up logic starting with last Index: " + lastIndex + " and Last Index Element:  " + lastElement);

        // To heapify up keep comparing last element with its parents and heapify till this item
        // Meets heap conditions
        while (hasParent(lastIndex)) {
            int parent = getParent(lastIndex);
            int parentIndex = getParentIndex(lastIndex);
            System.out.println("parent = " + parent + " parentIndex = " + parentIndex);

            if(lastElement < parent) {
                System.out.println("Swapping CurrentElement: " + lastElement + " With Parent: " + parent);
                swap(lastIndex, parentIndex);
                lastIndex = parentIndex;
            } else {
                break;
            }
        }
    }

    public void heapifyDown() {

    }

    public void swap(int itemIndex, int swapIndex) {
        int temp = items[swapIndex];
        items[swapIndex] = items[itemIndex];
        items[itemIndex] = temp;
    }


    public void doubleHeapSize() {
        items = Arrays.copyOf(items, items.length * 2);
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("items", items)
                .toString();
    }
}
