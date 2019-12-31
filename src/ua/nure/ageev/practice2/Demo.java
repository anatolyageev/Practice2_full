package ua.nure.ageev.practice2;

import java.util.Iterator;

public class Demo {

	public static void main(String[] args) {

		ArrayImpl con = new ArrayImpl();
		con.add("A");
		con.add("B");

		con.add("T");

		Iterator<Object> iter = con.iterator();
		iter.next();

		iter.next();

		iter.next();

		System.out.println(con);

		System.out.println(con);

		System.out.println("~~~~~~~~~~~~~~~~~~~~");
		for (Object o : con) {
			System.out.println(o);
		}
		System.out.println("~~~~~~~~~~~~~~~~~~~~");
		Iterator<Object> it = con.iterator();
		while (it.hasNext())
			System.out.println(it.next());

		ListImpl queue = new ListImpl();
		queue.addFirst("A");
		queue.addLast("B");
		queue.addLast("C");

		System.out.println(queue);
		System.out.println(queue.size());

		Iterator<Object> iterL = queue.iterator();
		System.out.println(iterL.next());
		System.out.println(iterL.next());
		System.out.println(iterL.next());
		iterL.remove();
		System.out.println(queue);
		System.out.println(queue.size());
		System.out.println(iterL.hasNext());

		Queue queue1 = new QueueImpl();
		queue1.enqueue("A");
		queue1.enqueue("B");
		queue1.enqueue("C");

		System.out.println(queue1);
		System.out.println(queue1.size());

		Iterator<Object> iterL1 = queue1.iterator();
		System.out.println(iterL1.next());

		System.out.println(iterL1.next());
		iterL1.remove();
		System.out.println(queue1);
		System.out.println(queue1.size());
		System.out.println(iterL1.hasNext());

		StackImpl st = new StackImpl();
		st.push("A");
		st.push("B");
		st.push("C");
		System.out.println(st);
		for (Object object : st) {
			System.out.println(object);
		}
		System.out.println(st.pop());
		System.out.println(st.pop());
		System.out.println(st.pop());

//		List list = new ListImpl();
//		Class c = list.getClass();
//		// must be 1
//		System.out.println(c.getInterfaces().length);
//
//		// must be List
//		System.out.println(c.getInterfaces()[0].getSimpleName());
//
//		// must be java.lang.Object
//		System.out.println(c.getSuperclass().getName());
//
//		// must be 1
//		System.out.println(c.getInterfaces()[0].getInterfaces().length);
//
//		// must be Container
//		System.out.println(c.getInterfaces()[0].getInterfaces()[0].getSimpleName());
//
//		// must be 1
//		System.out.println(c.getInterfaces()[0].getInterfaces()[0].getInterfaces().length);
//
//		// must be java.lang.Iterable
//		System.out.println(c.getInterfaces()[0].getInterfaces()[0].getInterfaces()[0].getName());

//		Queue queue = new QueueImpl();
//		Class c = queue.getClass();
//
//		// must be 1
//		System.out.println(c.getInterfaces().length);
//
//		// must be Queue
//		System.out.println(c.getInterfaces()[0].getSimpleName());
//
//		// must be 1
//		System.out.println(c.getInterfaces()[0].getInterfaces().length);
//
//		// must be Container
//		System.out.println(c.getInterfaces()[0].getInterfaces()[0].getSimpleName());
//
//		// must be 1
//		System.out.println(c.getInterfaces()[0].getInterfaces()[0].getInterfaces().length);
//
//		// must be java.lang.Iterable
//		System.out.println(c.getInterfaces()[0].getInterfaces()[0].getInterfaces()[0].getName());

	}

}
