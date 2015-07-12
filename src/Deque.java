import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {

	private Node first;
	private Node last;
	private int N;

	private class Node {
		Item item;
		Node next;
		Node prev;

		public Item getItem() {
			return item;
		}

		public void setItem(Item item) {
			this.item = item;
		}

		public Node getNext() {
			return next;
		}

		public void setNext(Node next) {
			this.next = next;
		}

		public Node getPrev() {
			return prev;
		}

		public void setPrev(Node prev) {
			this.prev = prev;
		}
	}

	// construct an empty deque
	public Deque() {
		first = null;
		last = null;
	}

	// is the deque empty?
	public boolean isEmpty() {
		if (first == null)
			return true;
		return false;
	}

	// return the number of items on the deque
	public int size() {
		if (isEmpty())
			return 0;
		Node n = first;
		int count = 0;
		while (n != last) {
			count++;
			n = n.next;
		}
		return count;
	}

	// add the item to the front
	public void addFirst(Item item) {
		if (item == null)
			throw new java.lang.NullPointerException();
		Node newFirst = new Node();
		newFirst.item = item;
		if (first != null) {
			newFirst.next = first;
			first.prev = newFirst;
			newFirst.prev = null;
		} else {
			first = last = newFirst;
			first.next = null;
			first.prev = null;
		}

	}

	// add the item to the end
	public void addLast(Item item) {
		if (item == null)
			throw new java.lang.NullPointerException();
		Node newLast = new Node();
		newLast.item = item;
		if (last != null) {
			newLast.prev = last;
			last.next = newLast;
			newLast.next = null;
			last = newLast;
		} else {
			first = last = newLast;
			newLast.prev = null;
			newLast.next = null;
		}
	}

	// remove and return the item from the front
	public Item removeFirst() {
		if (first == null)
			throw new java.util.NoSuchElementException();
		// take the first item
		Item itemFirst = first.item;
		// move the pointer to the next node. It becomes the first one.
		first = first.next;
		// check the case where the next one to the first is null or not
		// if it is not null, it becomes the first and so we set the previous to
		// null
		if (first != null)
			first.prev = null;
		else
			// otherwise it means that we are at the end of the queue
			last = null;
		return itemFirst;
	}

	public Item removeLast() {
		if (last == null)
			throw new java.util.NoSuchElementException();
		Item itemLast = last.item;
		last = last.prev;
		if (last.prev != null)
			last.next = null;
		else
			first = null;
		return itemLast;
	} // remove and return the item from the end

	// return an iterator over items in order from front to end
	public Iterator<Item> iterator() {
		return new ListIterator();
	}

	public class ListIterator implements Iterator<Item> {

		private Node current = first;

		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public Item next() {
			if (current == null)
				throw new java.util.NoSuchElementException();
			Item item = current.item;
			current = current.next;
			return item;
		}

		public void remove() {
			throw new java.lang.UnsupportedOperationException();
		}

	}

}
