package classloadertest;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.concurrent.ConcurrentHashMap;

public class Main {

	// The threads are utilized to create separate classloader contexts
	public static class MyThread extends Thread {
		private String name;
		private ConcurrentHashMap<String, Object> cache;

		public MyThread(String name, ConcurrentHashMap<String, Object> cache) {
			super();
			this.name = name;
			this.cache = cache;

			// Setup the new classloader for the thread using a.jar as the classpath.
			try {
				URLClassLoader classloader = URLClassLoader.newInstance(new URL[] { new File("a.jar").toURI().toURL() });
				this.setContextClassLoader(classloader);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void run() {
			try {
				// Instantiate the MyRunnable class from the JAR file and call the run() method.
				Class<?> c = Thread.currentThread().getContextClassLoader().loadClass("classloadertest.MyRunnable");
				Runnable r = (Runnable)c.getConstructor(String.class, ConcurrentHashMap.class).newInstance(name, cache);
				r.run();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	public static void main(String[] args) throws Exception {
		// Create a shared cache.
		ConcurrentHashMap<String, Object> cache = new ConcurrentHashMap<String, Object>();

		// Start up the first thread which will insert the object into the cache and display it.
		MyThread thread1 = new MyThread("1", cache);
		thread1.start();
		thread1.join();

		// Start up the second thread which will attempt to access the object but will
		// fail with a ClassCastException
		MyThread thread2 = new MyThread("2", cache);
		thread2.start();
		thread2.join();
	}
}
