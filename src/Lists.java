


public class Lists {
    // Node class for singly linked lists
    public static class Node {
        private int value;
        private Node next;

        public Node(int value) {
            this.value = value;
            this.next = null;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }
    }

    // DoublyNode class for doubly linked lists
    public static class DoublyNode {
        private int value;
        private DoublyNode next;
        private DoublyNode prev;

        public DoublyNode(int value) {
            this.value = value;
            this.next = null;
            this.prev = null;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public DoublyNode getNext() {
            return next;
        }

        public void setNext(DoublyNode next) {
            this.next = next;
        }

        public DoublyNode getPrev() {
            return prev;
        }

        public void setPrev(DoublyNode prev) {
            this.prev = prev;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }
    }

    // Singly Linked List implementation
    public static class LinkedList {
        private Node head;

        public LinkedList() {
            head = null;
        }

        public void add(Node newNode) {
            // If the list is empty, add the new node as the head
            if (head == null) {
                head = newNode;
                return;
            }

            // If the new node is less than the head, add it as the new head
            if (newNode.getValue() < head.getValue()) {
                newNode.setNext(head);
                head = newNode;
                return;
            }

            // Find the correct position for the new node
            Node current = head;
            // While the current node is not the last node and the value of the next node is less than the value of the new node
            while (current.getNext() != null && current.getNext().getValue() < newNode.getValue()) {
                current = current.getNext();
            }
            newNode.setNext(current.getNext());
            current.setNext(newNode);
        }

        public void remove(int value) {
            if (head == null) {
                return;
            }
            if (head.getValue() == value) {
                head = head.getNext();
                return;
            }
            Node current = head;
            while (current.getNext() != null) {
                if (current.getNext().getValue() == value) {
                    current.setNext(current.getNext().getNext());
                    return;
                }
                current = current.getNext();
            }
        }

        public boolean contains(int value) {
            Node current = head;
            while (current != null) {
                if (current.getValue() == value) {
                    return true;
                }
                current = current.getNext();
            }
            return false;
        }

        public int count() {
            int count = 0;
            Node current = head;
            while (current != null) {
                count++;
                current = current.getNext();
            }
            return count;
        }

        @Override
        public String toString() {
            StringBuilder result = new StringBuilder();
            Node current = head;
            while (current != null) {
                result.append(current.toString()).append(" ");
                current = current.getNext();
            }
            return result.toString();
        }

        public Node getHead() {
            return head;
        }
    }

    // Doubly Linked List implementation
    public static class DoublyLinkedList {
        private DoublyNode head;

        public DoublyLinkedList() {
            head = null;
        }

        public void add(DoublyNode newNode) {
            if (head == null || head.getValue() >= newNode.getValue()) {
                newNode.setNext(head);
                if (head != null) head.setPrev(newNode);
                head = newNode;
            } else {
                DoublyNode current = head;
                while (current.getNext() != null && current.getNext().getValue() < newNode.getValue()) {
                    current = current.getNext();
                }

                newNode.setNext(current.getNext());
                if (current.getNext() != null) current.getNext().setPrev(newNode);
                current.setNext(newNode);
                newNode.setPrev(current);
            }
        }

        public void remove(int value) {
            if (head == null) {
                return;
            }
            if (head.getValue() == value) {
                head = head.getNext();
                if (head != null) head.setPrev(null);
                return;
            }
            DoublyNode current = head;
            while (current.getNext() != null) {
                if (current.getNext().getValue() == value) {
                    current.setNext(current.getNext().getNext());
                    if (current.getNext() != null) {
                        current.getNext().setPrev(current);
                    }
                    return;
                }
                current = current.getNext();
            }
        }

        public int count() {
            int count = 0;
            DoublyNode current = head;
            while (current != null) {
                count++;
                current = current.getNext();
            }
            return count;
        }

        public boolean contains(int value) {
            DoublyNode current = head;
            while (current != null) {
                if (current.getValue() == value) {
                    return true;
                }
                current = current.getNext();
            }
            return false;
        }

        public DoublyNode getHead() {
            return head;
        }
    }

    // Circular Linked List implementation
    public static class CircularLinkedList {
        private Node head;

        public CircularLinkedList() {
            head = null;
        }

        public void add(Node newNode) {
            // Empty list
            if (head == null) {
                head = newNode;
                head.setNext(head);
            }
            // If the value is less than head, it will be the new head
            else if (newNode.getValue() < head.getValue()) {
                Node current = head;
                while (current.getNext() != head) {
                    current = current.getNext();
                }
                newNode.setNext(head);
                current.setNext(newNode);
                head = newNode;
            }
            // Find the correct position in the list
            else {
                Node current = head;
                while (current.getNext() != head && current.getNext().getValue() < newNode.getValue()) {
                    current = current.getNext();
                }
                // Check if the value already exists
                if (current.getNext().getValue() == newNode.getValue()) {
                    return;
                }
                newNode.setNext(current.getNext());
                current.setNext(newNode);
            }
        }

        public void remove(int value) {
            if (head == null) {
                System.out.println("The list is empty, no nodes to remove.");
                return;
            }

            Node current = head;

            if (head.getValue() == value) {
                if (head.getNext() == head) {
                    head = null;
                } else {
                    while (current.getNext() != head) {
                        current = current.getNext();
                    }

                    head = head.getNext();
                    current.setNext(head);
                }
                System.out.println("Value removed successfully.");
                return;
            }

            // The node to remove is not the head
            current = head;

            while (current.getNext() != head) {
                if (current.getNext().getValue() == value) {
                    current.setNext(current.getNext().getNext());
                    System.out.println("Value removed successfully.");
                    return;
                }

                current = current.getNext();
            }

            System.out.println("Value not found.");
        }

        public int count() {
            if (head == null) {
                return 0;
            }
            int count = 1;
            Node current = head;
            while (current.getNext() != head) {
                count++;
                current = current.getNext();
            }
            return count;
        }

        public boolean contains(int value) {
            if (head == null) {
                return false;
            }
            Node current = head;
            while (current.getNext() != head) {
                if (current.getValue() == value) {
                    return true;
                }
                current = current.getNext();
            }
            return current.getValue() == value;
        }

        public Node getHead() {
            return head;
        }
    }

    // Doubly Circular Linked List implementation
    public static class DoublyCircularLinkedList {
        private DoublyNode head;

        public DoublyCircularLinkedList() {
            head = null;
        }

        public void add(DoublyNode newNode) {
            // If the list is empty
            if (head == null) {
                head = newNode;
                head.setNext(head);
                head.setPrev(head);
                return;
            }

            // Add a node if there are already nodes in the list
            // Insert a node at the beginning
            if (newNode.getValue() < head.getValue()) {
                newNode.setNext(head);
                newNode.setPrev(head.getPrev());
                head.getPrev().setNext(newNode);
                head.setPrev(newNode);
                head = newNode;
                return;
            }

            // Insert a node at the end
            // Evaluate if the new node's value is greater than the last node's value
            if (newNode.getValue() > head.getPrev().getValue()) {
                newNode.setNext(head);
                newNode.setPrev(head.getPrev());
                head.getPrev().setNext(newNode);
                head.setPrev(newNode);
                return;
            }

            // Find the correct position for the new node
            DoublyNode current = head;

            // Traverse the list to find the right spot
            while (current.getNext() != head && current.getNext().getValue() < newNode.getValue()) {
                current = current.getNext();
            }

            // Insert the new node
            newNode.setNext(current.getNext());
            newNode.setPrev(current);

            current.getNext().setPrev(newNode);
            current.setNext(newNode);
        }

        public void remove(int value) {
            if (head == null) {
                return;
            }

            if (head.getNext() == head) { // Only one node in the list
                head = null; // List becomes empty
                return;
            }

            // Removal at the beginning
            if (head.getValue() == value) {
                head.getPrev().setNext(head.getNext());
                head.getNext().setPrev(head.getPrev());
                head = head.getNext();
                return;
            }

            // Removal at the end
            if (head.getPrev().getValue() == value) {
                head.setPrev(head.getPrev().getPrev());
                head.getPrev().setNext(head);
                return;
            }

            // Removal of an intermediate node
            DoublyNode current = head;
            while (current.getNext() != head && current.getValue() != value) {
                current = current.getNext();
            }

            // If the node with the value was found
            if (current.getValue() == value) {
                current.getPrev().setNext(current.getNext());
                current.getNext().setPrev(current.getPrev());
            }
        }

        public boolean contains(int value) {
            if (head == null) {
                return false;
            }

            DoublyNode current = head;
            do {
                if (current.getValue() == value) {
                    return true;
                }
                current = current.getNext();
            } while (current != head);
            return false;
        }

        public int count() {
            if (head == null) {
                return 0;
            }
            int count = 0;
            DoublyNode current = head;
            do {
                count++;
                current = current.getNext();
            } while (current != head);
            return count;
        }

        @Override
        public String toString() {
            if (head == null) {
                return "The doubly circular linked list is void";
            }

            StringBuilder result = new StringBuilder();
            DoublyNode current = head;

            do {
                result.append(current.toString()).append(" ");
                current = current.getNext();
            } while (current != head);

            return result.toString();
        }

        public DoublyNode getHead() {
            return head;
        }
    }
}