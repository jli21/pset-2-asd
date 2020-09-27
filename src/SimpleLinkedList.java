import java.util.List;
import java.util.NoSuchElementException;

public class SimpleLinkedList {
	
	public static int size;
	private static Node start;
	private static Node end;
	
	public SimpleLinkedList() {
		start = null;
		end = null;
		size = 0;
	}
	
	public SimpleLinkedList(List<String> list) {
		start = new Node (null, null, null);
		end = new Node (null, null, null);
		
		Node currentNode = start;
		for (int i =  0; i < list.size(); i++) {
			currentNode.data = list.get(i);
			Node prevNode = currentNode; 
			currentNode =  new Node (currentNode, null, null);
			prevNode.next = currentNode;
		}
		currentNode.data = list.get(list.size()-1);
		end = currentNode;
		size = list.size();
	}
	
	public void add(int index, String s) {
		checkIdx(index);
		if (index == 0) {
			addFirst(s);
		} else if (index == size - 1) {
			addLast(s);
		} else {
			Node currentNode = getNode(index);
			Node newNode = new Node (currentNode.prev, s, currentNode);
			currentNode.prev.next = newNode;
			currentNode.prev = newNode;
			size++;
		}
	}
	public void addFirst (String s) {
		Node currentNode = start;
		Node newNode = new Node (null, s, currentNode);
		start = newNode;
		if (size == 0) {
			end = newNode;
		} else {
			currentNode.prev = newNode;
		}
		size++;
	}
	
	public void addLast(String s) {
        Node current = end;
        Node newNode = new Node(current, s, null);

        end = newNode;
        if (current == null) {
            start = newNode;
        } else {
            current.next = newNode;
        }

        size++;
    }
	
	public void clear () {
		start = new Node (null, null, null);
		end = new Node (null, null, null);
		size = 0;
		}
	
	public boolean contains (String s) {
		for(int x = 0; x < size; x++) {
			if (getNode(x).data == s) {
				return true;
			}
		}
		return false;
	}
	
	
    private Node getNode(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        int count = 0;
        Node current = start;

        while (current != null) {
            if (count++ == index) {
                return current;
            }

            current = current.next;
        }

        return null;
    }
	
	private void checkIdx(int idx) {
		if(idx < 0 || idx > size) { 
			throw new IndexOutOfBoundsException("Index: " + idx + ", Size: " + size);
		}
	}
	public String getFirst () {
		if (size == 0) {
			throw new NoSuchElementException();
		}
		return start.data;
	}
	
	public String getLast () {
		if (size == 0) {
			throw new NoSuchElementException();
		}
		return end.data;
	}
	
	public int indexOf (String s) {
		for (int i = 0; i < size; i++) {
			if (getNode(i).data == s) {
				return i;
			}
		}
		return -1; 
	}

	public String get(int index) { 
		Node currentNode = getNode(index);
		return currentNode.data;
	}
	
	public String remove (int index) { 
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);		
		} else if (index == 0) {
			return removeFirst();
		} else if (index == size -1) {
			return removeLast();
		} else {
			Node removedNode = getNode(index);
			removedNode.prev.next = removedNode.next;
			removedNode.next.prev = removedNode.prev;
			String removed = removedNode.data;
			size = size - 1; 
			return removed;
		}
	}
	
	public boolean remove (String s) {
		boolean isFound = contains(s);
		if (isFound == false) {
			return false;
		} else {
			int index = indexOf(s);
			if (index == 0) {
				removeFirst();
				return true;
			} else if (index == size) {
				removeLast();
				return true;
			} else {
				Node removedNode = getNode(index);
				removedNode.prev.next = removedNode.next;
				removedNode.next.prev = removedNode.prev;
				size--;
				return true;
			}
		}
	}
	public String removeFirst () {
		String removed = start.data;
		Node removedNode = start;
		start = removedNode.next;
		start.prev = null;
		size--;
		return removed;
	}
	
	public String removeLast () {
		String removed = end.data;
		Node removedNode = end;
		end = removedNode.prev;
		end.next = null;
		size--;
		return removed;
	}
	
	public String set(int index, String s) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
		}
		Node currentNode = getNode(index);
		String element = currentNode.data;
		if (s == null) s = "null";
		currentNode.data = s;
		return element;
	}
	
	public int size () {
		return size;
	}
	
	public String toString () {
		String str = "[";
		Node currentNode = start;
		if (size != 0) {
			for (int i = 0; i < size - 1; i++) {
				str += currentNode.data + ", ";
				currentNode = currentNode.next;
			}
			str += currentNode.data + "]";
		} else {
			return "[]";
		}
		return str;
	}
	
	public static class Node {
		
		Node prev;
		String data;
		Node next;
		
		public Node(Node prev, String data, Node next) {
			this.prev = prev;
			this.data = data;
			this.next = next;
		}
	}
}