classloader-test
================

Demonstrates some aspects of ClassLoaders including ClassCastExceptions.

Usage
-----

    $ ./run.sh
    Run 1: running
    Run 1: cache["key"] = <A value="1">
    Run 2: running
    Exception in thread "main" java.lang.ClassCastException: classloadertest.A cannot be cast to classloadertest.A
            at classloadertest.MyRunnable.run(MyRunnable.java:23)
            at classloadertest.Main.run(Main.java:16)
            at classloadertest.Main.main(Main.java:24)
