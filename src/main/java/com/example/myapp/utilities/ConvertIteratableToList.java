package com.example.myapp.utilities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class ConvertIteratableToList {
	public static <T> Collection<T> iterableToCollection(Iterable<T> iterable)
	{
		Collection<T> collection = new ArrayList<>();

		Iterator<T> iterator = iterable.iterator();
		while (iterator.hasNext()) {
			collection.add(iterator.next());
		}

		return collection;
	}

}
