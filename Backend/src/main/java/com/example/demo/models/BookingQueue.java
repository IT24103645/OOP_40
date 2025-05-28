package com.example.demo.models;

public class BookingQueue {
    private int front;
    private int rear;
    private BookingRequest[] queueArray;
    private int maxsize;

    public BookingQueue() {
        this.maxsize = 10;
        this.queueArray = new BookingRequest[maxsize];
        this.front = -1;
        this.rear = -1;
    }

    public synchronized void insert(BookingRequest item) {
        if (isFull()) {
            System.out.println("Queue is full. Cannot insert: " + item);
            return;
        }

        if (isEmpty()) {
            front = rear = 0;
        } else {
            rear = (rear + 1) % maxsize;
        }

        queueArray[rear] = item;
    }

    public synchronized BookingRequest remove() {
        if (isEmpty()) {
            System.out.println("Queue is empty!");
            return null;
        }

        BookingRequest removedItem = queueArray[front];
        queueArray[front] = null; // Optional: clear reference

        if (front == rear) {
            front = rear = -1;
        } else {
            front = (front + 1) % maxsize;
        }

        return removedItem;
    }

    public synchronized boolean isEmpty() {
        return front == -1;
    }

    public synchronized boolean isFull() {
        return (rear + 1) % maxsize == front;
    }

    public synchronized void display() {
        if (isEmpty()) {
            System.out.println("Queue is empty.");
            return;
        }

        System.out.println("Current Booking Requests in Queue:");
        int i = front;
        while (true) {
            System.out.println(queueArray[i]);
            if (i == rear) break;
            i = (i + 1) % maxsize;
        }
    }
}

