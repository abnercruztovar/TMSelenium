package com.tmorangeAutomation.tmselenium;

import static java.util.Optional.ofNullable;

import java.util.function.Consumer;

/**
 * <b> Clase para construir un objeto T generico</b>
 * 
 * @author Abner Cruz Tovar
 */
public final class GB<T> {

	private T object;

	/**
	 * Generic Builder Constructor
	 */
	private GB() {
	}

	public static <T> GB<T> of(T object) {
		return new GB<>(object);
	}

	private GB(T object) {
		this.object = ofNullable(object).orElseThrow(NullPointerException::new);
	}

	/**
	 * Metodo que retorna el objeto <T> construido
	 * 
	 * @return object
	 */
	public T build() {
		return object;
	}

	/**
	 * Metodo Set de propiedades/atributos del objeto <T>
	 * 
	 * @param sup
	 * @return
	 */
	public GB<T> push(Consumer<T> sup) {
		sup.accept(object);
		return this;
	}

}
