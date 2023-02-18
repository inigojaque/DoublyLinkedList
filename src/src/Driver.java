package src;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*
 Author: Inigo Jaque
 Class: CSC 130 Section 5
 Assignment: DoublyLinkedList(Names)
 Date Last Edited: 10/5/2020
 Description: Reads names from a text file and sorts them into a Doubly Linked List.
 */

//Creates list nodes and initializes next and previous nodes for list connection
class Node{
	private
	String name;
	Node next = null;
	Node prev = null;
	public Node(String s) {//Constructor
		name = s;
	}
	public String getName() {// Gets the name of the Specified node
		return name;
	}
	public void setNext(Node n) {// Sets the next node to passed node
		next = n;
	}
	public Node getNext() {// Gets the node assigned to next
		return next;
	}
	public void setPrev(Node n) {// Sets the previous node to the passed node
		prev = n;
	}
	public Node getPrev() {// Returns the specified node
		return prev;
	}
}

// Class creates a Doubly Linked List with methods used to traverse the list and insert or delete nodes
class Doubly{
	private
	Node head = null;
	Node tail = null;
	Node curr;
	public Doubly(String file) throws FileNotFoundException{// Constructor
		Scanner fileRead = new Scanner(new File(file));
		while(fileRead.hasNext()) {
			String name = fileRead.next();
			Node temp = new Node(name);
			if(head == null) {
				head = temp;
				tail = temp;
			}else if(name.contains("delete")) {
				delete(fileRead.next());
			}else if(name.compareToIgnoreCase(head.getName()) < 0) {
				//Node hold = head;
				//head.setPrev(temp);
				//head = temp;
				//head.setNext(hold);;
				temp.setNext(head);
				head.setPrev(temp);
				head = temp;
			}else if(name.compareToIgnoreCase(tail.getName()) > 0) {
				//Node hold = tail;
				//tail.setNext(temp);
				//tail = temp;
				//tail.setPrev(hold);
				temp.setPrev(tail);
				tail.setNext(temp);
				tail = temp;
			}else {
				insert(temp);
			}
		}
	}
	public Node getHead() { //Returns the head of the list
		return head;
	}
	public Node getTail() {// Returns the tail of the list
		return tail;
	}
	public void delete(String s) {// Method deletes the node that matches the passed String
		curr = head;
		int end = 0;
		while(end != -1) {
			String name = curr.getName();
			if(name.equalsIgnoreCase(s)) {
				if(curr == head) {
					head = curr.getNext();
					end = -1;
				}else {
					curr.getPrev().setNext(curr.getNext());
					end = -1;
				}
				if(curr == tail) {
					tail = curr.getPrev();
					end = -1;
				}else {
					curr.getNext().setPrev(curr.getPrev());
					end = -1;
				}
			}else if(curr == null) {
				end = -1;
			}else {
				curr = curr.getNext();
			}
		}
	}
	public void insert(Node n) {// Traverses the list to find the correct alphabetical location for the passed Node
		curr = head;
		while(curr.getNext() != null) {
			Node next = curr.getNext();
			String temp = curr.getName();
			String name = n.getName();
			if(name.compareToIgnoreCase(temp) > 0 && name.compareToIgnoreCase(next.getName()) < 0) {
				n.setPrev(curr);
				n.setNext(next);
				next.setPrev(n);
				curr.setNext(n);
			}else {
				curr = curr.getNext();
			}
		}
	}
	public void traverseAscending() {// Displays the list in ascending order
		curr = head;
		System.out.print("List of Names (Ascending): ");
		while(curr.getNext() != null) {
			String name = curr.getName();
			System.out.print(name + ", ");
			curr = curr.getNext();
		}
		System.out.println(curr.getName());
	}
	public void traverseDescending() {// Displays the list in descending order
		curr = tail;
		System.out.print("List of Names (Descending): ");
		while(curr.getPrev() != null) {
			String name = curr.getName();
			System.out.print(name + ", ");
			curr = curr.getPrev();
		}
		System.out.println(curr.getName());
	}
}

//Driver class calls the methods to construct a doubly linked list
public class Driver {

	public static void main(String[] args) throws FileNotFoundException {
		Doubly doubleLL = new Doubly("input.txt");
		doubleLL.traverseAscending();
		doubleLL.traverseDescending();
	}

}
