import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.StdOut;

public class Subset {
	public static void main(String[] args) {
		RandomizedQueue<String> queue = new RandomizedQueue<String>();

		String input = StdIn.readLine();
		String[] segmented = input.split("[\\s]+");

		for (String x : segmented)
			queue.enqueue(x);

		for (int x = 0; x < Integer.parseInt(args[0]); x++)
			StdOut.println(queue.dequeue());
	}
}
