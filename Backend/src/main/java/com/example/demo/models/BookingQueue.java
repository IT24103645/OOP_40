package com.example.demo.models;

public class BookingQueue {
    private int front;
    private int rear;
    private BookingRequest[] queueArray;
    private int maxsize;
    private int nItems;

    //queue constructor
    public BookingQueue() {
        this.maxsize = 10;
        this.queueArray = new BookingRequest[maxsize];
        this.front = 0;
        this.rear = -1;
        this.nItems = 0;
    }

    //Queue methods
    public synchronized void insert(BookingRequest item) {
        //prevent adding if the queue is full
        if (isFull()) {
            System.out.println("Queue is full. Cannot insert: " + item);
            return;
        }

        //increase rear and nItems then wrap around
        rear = (rear + 1) % maxsize;
        queueArray[rear] = item;
        nItems++;
    }


    public synchronized BookingRequest remove() {
        //prevent removing if queue is empty
        if (isEmpty()) {
            System.out.println("Queue is empty!");
            return null;
        }

        //remove object + replace with null
        BookingRequest removedItem = queueArray[front];
        queueArray[front] = null;
        front = (front + 1) % maxsize;
        nItems--;


        return removedItem;
    }

    //check if queue is empty
    public synchronized boolean isEmpty() {
        return nItems == 0;
    }

    //check if queue is full
    public synchronized boolean isFull() {
        return nItems == maxsize;
    }
}
