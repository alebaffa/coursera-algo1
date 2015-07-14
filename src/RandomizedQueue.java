import java.util.Iterator;

import edu.princeton.cs.introcs.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

	private Item[] items;
	private int N;
	private int capacity;

	public RandomizedQueue() {
		N = 0;
		capacity = 1;
		items = (Item[]) new Object[1];

	} // construct an empty randomized queue

	public boolean isEmpty() {
		return N == 0;
	} // is the queue empty?

	public int size() {
		return N;
	} // return the number of items on the queue

	// increase the size of the array by creating a new one having double the
	// size of the original one
	private void increaseArray() {
		capacity *= 2;
		Item[] newQueue = (Item[]) new Object[capacity];
		int index = 0;
		for (Item i : items) {
			newQueue[index++] = i;
		}
		items = newQueue;
	}

	private void decreaseArray() {
		capacity /= 2;
		Item[] newQueueDecreased = (Item[]) new Object[capacity];
		int index = 0;
		for (Item i : items) {
			newQueueDecreased[index++] = i;
		}
		items = newQueueDecreased;
	}

	// add the item
	public void enqueue(Item item) {
		if (item == null)
			throw new java.lang.NullPointerException();
		// check if adding a new element we need to increase the capacity of the
		// array
		if (N + 1 > capacity) {
			increaseArray(); // increment the size of the array
		}
		items[N++] = item; // add the item in the array

	}

	// remove the item
	public Item dequeue() {
		if (isEmpty())
			throw new java.util.NoSuchElementException();
		int index = StdRandom.uniform(N);
		Item itemToReturn = items[index];
		items[index] = items[--N];
		items[N] = null;
		if (capacity / 4 > N)
			decreaseArray();
		return itemToReturn;
	}

	// return (but do not remove) a random item
	public Item sample() {
		return items[StdRandom.uniform(N)];
	}

	// return an independent iterator over items in random order
	public Iterator<Item> iterator() {
		return new ListIterator();
	}

	private class ListIterator implements Iterator<Item> {

		private int current = 0;
		private int[] shuffledIndexes = new int[N];

		@Override
		public boolean hasNext() {
			if (current == 0) {
				// create an array with the same size of N
				for (int i = 0; i < N; i++) {
					shuffledIndexes[i] = i;
				}
				// reshuffle it
				StdRandom.shuffle(shuffledIndexes);
			}
			return current < N; // return true if current element is inside the
								// capacity
		}

		public void remove() {
			throw new java.lang.UnsupportedOperationException();
		}

		@Override
		public Item next() {
			if (current == 0) {
				// create an array with the same size of N
				for (int i = 0; i < N; i++) {
					shuffledIndexes[i] = i;
				}
				// reshuffle it
				StdRandom.shuffle(shuffledIndexes);
			}
			if (current >= N || size() == 0)
				throw new java.util.NoSuchElementException();
			return items[shuffledIndexes[current++]];
		}

	}

}
