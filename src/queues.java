import javax.swing.*;

public class queues {

    public static class StaticQueue {
        private int[] elements; // Array to store queue elements
        private int front;      // Index of the front element
        private int rear;       // Index of the rear element
        private int count;      // Number of elements in the queue

        public int Capacity; // Maximum capacity of the queue

        public StaticQueue(int capacity) {
            Capacity = capacity;
            elements = new int[capacity];
            front = 0;
            rear = -1;
            count = 0;
        }

        // Method to add a number to the queue
        public void enqueue(int number) {
            if (count == Capacity) {
                JOptionPane.showMessageDialog(null, "The queue is full.");
                return;
            }

            rear = (rear + 1) % Capacity;
            elements[rear] = number;
            count++;
        }

        // Method to remove and return the number at the front of the queue
        public int dequeue() {
            if (isEmpty()) {
                JOptionPane.showMessageDialog(null, "The queue is empty.");
                return -1; // Returning a default value
            }

            int number = elements[front];
            front = (front + 1) % Capacity;
            count--;
            return number;
        }

        // Method to see the number at the front without removing it
        public int peek() {
            if (isEmpty()) {
                JOptionPane.showMessageDialog(null, "The queue is empty.");
                return -1; // Returning a default value
            }

            return elements[front];
        }

        // Method to check if the queue is empty
        public boolean isEmpty() {
            return count == 0;
        }

        // Method to check if the queue is full
        public boolean isFull() {
            return count == Capacity;
        }

        // Method to get the size of the queue
        public int size() {
            return count;
        }
    }

    public static class DynamicQueue {
        public Node front; // Node at the front of the queue
        private Node rear;  // Node at the rear of the queue
        private int count;  // Number of elements in the queue

        public int getCount() {
            return count;
        }

        public DynamicQueue() {
            front = null;
            rear = null;
            count = 0;
        }

        // Method to add a number to the queue
        public void enqueue(int number) {
            Node newNode = new Node(number);

            if (isEmpty()) {
                front = rear = newNode;
            } else {
                rear.next = newNode;
                rear = newNode;
            }

            count++;
        }

        // Method to remove and return the number at the front of the queue
        public int dequeue() {
            if (isEmpty()) {
                JOptionPane.showMessageDialog(null, "The queue is empty.");
                return -1; // Returning a default value
            }

            int data = front.value;
            front = front.next;

            if (front == null) {
                rear = null; // The queue becomes empty
            }

            count--;
            return data;
        }

        // Method to see the number at the front without removing it
        public int peek() {
            if (isEmpty()) {
                JOptionPane.showMessageDialog(null, "The queue is empty.");
                return -1; // Returning a default value
            }

            return front.value;
        }

        // Method to check if the queue is empty
        public boolean isEmpty() {
            return front == null;
        }

        // Method to get the size of the queue
        public int size() {
            return count;
        }
    }

    public static class CircularQueue {
        private int[] array;
        private int front;
        private int rear;
        private int count;
        public int Capacity;

        // Constructor to initialize the circular queue with a specific size
        public CircularQueue(int capacity) {
            Capacity = capacity;
            array = new int[capacity];
            front = 0;
            rear = -1;
            count = 0;
        }

        // Method to add an element to the rear of the queue
        public void enqueue(int value) {
            if (count == Capacity) {
                JOptionPane.showMessageDialog(null, "The queue is full.");
                return;
            }

            rear = (rear + 1) % Capacity; // Circularly move the rear index
            array[rear] = value;
            count++;
        }

        // Method to remove an element from the front of the queue
        public int dequeue() {
            if (isEmpty()) {
                JOptionPane.showMessageDialog(null, "The queue is empty.");
                return -1; // Returning a default value
            }

            int dequeuedValue = array[front];
            front = (front + 1) % Capacity; // Circularly move the front index
            count--;
            return dequeuedValue;
        }

        // Method to see the value at the front of the queue without removing it
        public int peek() {
            if (isEmpty()) {
                JOptionPane.showMessageDialog(null, "The queue is empty.");
                return -1; // Returning a default value
            }

            return array[front];
        }

        // Method to get the number of elements in the queue
        public int size() {
            return count;
        }

        // Method to check if the queue is empty
        public boolean isEmpty() {
            return count == 0;
        }

        // Method to check if the queue is full
        public boolean isFull() {
            return count == Capacity;
        }
    }

    // Node class for the dynamic queue
    public static class Node {
        int value;
        Node next;

        public Node(int value) {
            this.value = value;
            this.next = null;
        }
    }
}
