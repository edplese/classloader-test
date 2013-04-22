package classloadertest;

import java.util.concurrent.ConcurrentHashMap;

public class MyRunnable implements Runnable {
	private ConcurrentHashMap<String, Object> cache;
	private String name;

	public MyRunnable(String name, ConcurrentHashMap<String, Object> cache) {
		this.name = name;
		this.cache = cache;
	}

	@Override
	public void run() {
		System.out.println("Run " + name + ": running");

		// Set the object in the cache
		A a = new A(name);
		cache.putIfAbsent("key", a);

		// Read the object from the cache which may be differed from above if it had already been set.
		A cached = (A) cache.get("key");
		System.out.println("Run " + name + ": cache[\"key\"] = " + cached.toString());
	}
}
