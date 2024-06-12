package com.mrboomdev.awery.sdk.util;

public class Callbacks {

	private Callbacks() {}

	public interface Callback1<T> {
		void run(T t);
	}

	public interface Callback2<T, E> {
		void run(T t, E e);
	}

	public interface Callback3<T, E, V> {
		void run(T t, E e, V v);
	}

	public interface Callback4<T, E, V, J> {
		void run(T t, E e, V v, J j);
	}

	public interface Callback5<T, E, V, J, Z> {
		void run(T t, E e, V v, J j, Z z);
	}

	public interface Result<T> {
		T run();
	}

	public interface Result1<T, A> {
		T run(A a);
	}

	public interface Result2<T, A, E> {
		T run(A a, E e);
	}

	public interface Result3<T, A, E, W> {
		T run(A a, E e, W w);
	}

	public interface Result4<T, A, E, W, V> {
		T run(A a, E e, W w, V v);
	}

	public interface Result5<T, A, E, W, V, Z> {
		T run(A a, E e, W w, V v, Z z);
	}
	
	public interface Errorable<R, E> {
		void onResult(R result, E error);

		default void onError(E error) {
			onResult(null, error);
		}

		default void onSuccess(R result) {
			onResult(result, null);
		}
	}
}