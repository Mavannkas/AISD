package ludwiniak.wiktor.Lab.L2;

public class LinkedElement<E> {
        public E element;
        public LinkedElement<E> next;
        public LinkedElement(E element) {
            this.element = element;
        }
}
