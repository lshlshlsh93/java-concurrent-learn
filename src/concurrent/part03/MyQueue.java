package concurrent.part03;

/**
 * @Author lishaohui
 * @Date 2023/4/11 8:51
 * <p>
 * un-threadsafe 非线程安全
 * </p>
 */
public class MyQueue<E> {

    private static class Node<E> {

        // 元素
        private E element;
        // 下一节点
        private Node<E> next;

        public Node(E element, Node<E> next) {
            this.element = element;
            this.next = next;
        }

        public E getElement() {
            return element;
        }

        public void setElement(E element) {
            this.element = element;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setNext(Node<E> next) {
            this.next = next;
        }

        @Override
        public String toString() {
            return element == null ? "" : element.toString();
        }
    }


    private Node<E> head, tail;
    private int size;

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public E peekFirst() {
        return (head == null) ? null : head.getElement();
    }

    public E peerLast() {
        return (tail == null) ? null : tail.getElement();
    }

    public void addTail(E element) {
        Node<E> newNode = new Node<>(element, null);
        if (size() == 0) {
            head = newNode;
        } else {
            tail.setNext(newNode);
        }
        tail = newNode;
        size++;
    }

    public E removeFirst() {
        if (isEmpty()) return null;
        E e = head.getElement();
        head = head.getNext();
        size--;
        if (size() == 0) tail = null;
        return e;
    }

    public static void main(String[] args) {

        MyQueue<String> queue = new MyQueue<>();
        queue.addTail("Hello");
        queue.addTail("World");
        queue.addTail("LinkedList");

        System.out.println(queue.removeFirst());//Hello
        System.out.println(queue.removeFirst());//World
        System.out.println(queue.removeFirst());//LinkedList

    }

}
