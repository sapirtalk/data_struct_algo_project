public class Queue {

    private Node front, rear;
    private int queueSize;

    //linked list node
    private class Node {
        int data;
        Node next;
    }

    //default constructor - initially front & rear are null; size=0; queue is empty
    public Queue() {
        front = null;
        rear = null;
        queueSize = 0;
    }


    //check if the queue is empty
    public boolean isEmpty() {
        return (queueSize == 0);
    }

    //Remove item from the front of the queue.
    public int dequeue() {
        int data = front.data;
        front = front.next;
        if (isEmpty()) {
            rear = null;
        }
        queueSize--;
        System.out.println("Element " + data + " removed from the queue");
        return data;
    }

    //Add data at the rear of the queue.
    public void enqueue(int data) {
        Node oldRear = rear;
        rear = new Node();
        rear.data = data;
        rear.next = null;
        if (isEmpty()) {
            front = rear;
        } else {
            oldRear.next = rear;
        }
        queueSize++;
        System.out.println("Element " + data + " added to the queue");
    }

    //print front and rear of the queue
    public void print_frontRear() {
        System.out.println("Front of the queue:" + front.data
                + " Rear of the queue:" + rear.data);
    }
}

