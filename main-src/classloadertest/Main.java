package classloadertest;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.concurrent.ConcurrentHashMap;

public class Main {
	public static void run(String name, ConcurrentHashMap<String, Object> cache) throws Exception {
		// Create a classloader using a.jar as the classpath.
		URLClassLoader classloader = URLClassLoader.newInstance(new URL[] { new File("a.jar").toURI().toURL() });

		// Instantiate MyRunnable from within a.jar and call its run() method.
		Class<?> c = classloader.loadClass("classloadertest.MyRunnable");
		Runnable r = (Runnable)c.getConstructor(String.class, ConcurrentHashMap.class).newInstance(name, cache);
		r.run();
	}

	public static void main(String[] args) throws Exception {
		// Create a shared cache.
		ConcurrentHashMap<String, Object> cache = new ConcurrentHashMap<String, Object>();

		run("1", cache);
		run("2", cache);
	}
}
