package edu.cmu.cs.cs214.rec02;

import org.junit.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.*;


/**
 * TODO: Write detailed unit tests for the {@link LinkedIntQueue} and
 * {@link ArrayIntQueue} classes, as described in the handout. The
 * {@link ArrayIntQueue} class contains a few bugs. Use the tests you wrote for
 * the {@link LinkedIntQueue} class to test the {@link ArrayIntQueue}
 * implementation. Refine your tests to achieve 100% line coverage and use them
 * to determine and correct the bugs!
 *
 * @author Alex Lockwood
 */
public class IntQueueTest {

    private IntQueue mQueue;

    /**
     * Called before each test.
     */
    @Before
    public void setUp() {
        // comment/uncomment these lines to test each class
        // mQueue = new LinkedIntQueue();
        mQueue = new ArrayIntQueue();
    }

    @Test
    public void testClear() throws IOException {
        mQueue.enqueue(0);
        mQueue.enqueue(1);
        mQueue.enqueue(2);
        mQueue.clear();
        assertTrue(mQueue.isEmpty());
    }

    @Test
    public void testDequeueNotEmptyQueue() {
        mQueue.enqueue(0);
        assertEquals(mQueue.dequeue(), Integer.valueOf(0));
    }

    @Test
    public void testDequeueEmptyQueue() {
        assertNull(mQueue.dequeue());
    }

    @Test
    public void testIsEmpty() {
        assertTrue(mQueue.isEmpty());
    }

    @Test
    public void testPeekEmptyQueue() {
        assertNull(mQueue.peek());
    }

    @Test
    public void testPeekNotEmptyQueue() {
        mQueue.enqueue(0);
        assertEquals(mQueue.peek(), Integer.valueOf(0));
        mQueue.clear();
    }

    @Test
    public void testSizeEmptyQueue() {
        assertEquals(mQueue.size(), 0);
    }

    @Test
    public void testSizeNotEmptyQueue() {
        mQueue.enqueue(0);
        mQueue.enqueue(1);
        mQueue.enqueue(2);
        assertEquals(mQueue.size(), 3);
        mQueue.clear();
    }

    @Test
    public void testContent() throws IOException {
        InputStream in = new FileInputStream("src/test/resources/data.txt");
        try (Scanner scanner = new Scanner(in)) {
            scanner.useDelimiter("\\s*fish\\s*");

            List<Integer> correctResult = new ArrayList<>();
            while (scanner.hasNextInt()) {
                int input = scanner.nextInt();
                correctResult.add(input);
                System.out.println(input);
                mQueue.enqueue(input);
            }

            // Used boxed type to pacify assertEquals overload resolution
            for (Integer result : correctResult) {
                assertEquals(mQueue.dequeue(), result);
            }
        } finally {
            in.close();
        }
    }

    @Test
    public void testEnsureCapacity() {
        for (int i = 0; i < 5; i++) {
            mQueue.enqueue(0);
        }
        for (int i = 0; i < 4; i++) {
            mQueue.dequeue();
        }
        for (int i = 1; i < 11; i++) {
            mQueue.enqueue(i);
        }
        for (int i = 0; i < 11; i++) {
            assertEquals(mQueue.dequeue(), Integer.valueOf(i));
        }
    }

}
