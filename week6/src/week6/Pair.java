package week6;

public class Pair<E> {
	private E elem_1, elem_2;

	public Pair(E elem_1, E elem_2) {
		this.elem_1 = elem_1;
		this.elem_2 = elem_2;
	}

	public E get_first() {
		return elem_1;
	}

	public E get_second() {
		return elem_2;
	}
}
