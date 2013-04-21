classloader-test
================

Demonstrates some aspects of ClassLoaders including ClassCastExceptions.

Usage
-----

    $ ./run.sh
    Thread 1: running
    Thread 1: cache["key"] = <A value="1">
    Thread 2: running
    java.lang.ClassCastException: classloadertest.A cannot be cast to classloadertest.A
            at classloadertest.MyRunnable.run(MyRunnable.java:23)
            at classloadertest.Main$MyThread.run(Main.java:35)
