package com.sample.coding.exercises;

import com.sample.coding.exercises.heap.MinHeap;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @author manyce400
 */
@RunWith(MockitoJUnitRunner.class)
public class MinHeapTest {



    private MinHeap minHeap;


    @Before
    public void before() {
        minHeap = new MinHeap(10);
    }

    @Test
    public void testMinHeapCreation() {
        System.out.println("\nStarting with initial minHeap = " + minHeap);

        minHeap.addItemToHeap(5);
        System.out.println("\nAfter adding 5 to minHeap = " + minHeap);

        minHeap.addItemToHeap(3);
        System.out.println("\nAfter adding 3 to minHeap = " + minHeap);

        minHeap.addItemToHeap(4);
        System.out.println("\nAfter adding 4 to minHeap = " + minHeap);
        
//        String s = "2009-2010";
//        int t = s.indexOf("-");
//        s.substring()
//        int h = t +1;
//        System.out.println("t = " + t);
//
//        System.out.println("Start "+ s.substring(0, t));
//        System.out.println("End "+ s.substring(h));


        List<Integer> test = new ArrayList<>();
        test.add(9);
    }

}