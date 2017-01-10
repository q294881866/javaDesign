

/*package test.nio;
 * @see ReflectionFactory MethodHandle MagicAccessorImpl 

import java.security.AccessController;

public class ReflectionFactory {

	private static boolean initted = false;

	// ...

	//
	// "Inflation" mechanism. Loading bytecodes to implement
	// Method.invoke() and Constructor.newInstance() currently costs
	// 3-4x more than an invocation via native code for the first
	// invocation (though subsequent invocations have been benchmarked
	// to be over 20x faster). Unfortunately this cost increases
	// startup time for certain applications that use reflection
	// intensively (but only once per class) to bootstrap themselves.
	// To avoid this penalty we reuse the existing JVM entry points
	// for the first few invocations of Methods and Constructors and
	// then switch to the bytecode-based implementations.
	//
	// Package-private to be accessible to NativeMethodAccessorImpl
	// and NativeConstructorAccessorImpl
	private static boolean noInflation = false;
	private static int inflationThreshold = 15;

	// ...

	*//**
	 * We have to defer full initialization of this class until after the static
	 * initializer is run since java.lang.reflect.Method's static initializer
	 * (more properly, that for java.lang.reflect.AccessibleObject) causes this
	 * class's to be run, before the system properties are set up.
	 *//*
	private static void checkInitted() {
		if (initted)
			return;
		AccessController.doPrivileged(new PrivilegedAction() {
			public Object run() {
				// Tests to ensure the system properties table is fully
				// initialized. This is needed because reflection code is
				// called very early in the initialization process (before
				// command-line arguments have been parsed and therefore
				// these user-settable properties installed.) We assume that
				// if System.out is non-null then the System class has been
				// fully initialized and that the bulk of the startup code
				// has been run.

				if (System.out == null) {
					// java.lang.System not yet fully initialized
					return null;
				}

				String val = System.getProperty("sun.reflect.noInflation");
				if (val != null && val.equals("true")) {
					noInflation = true;
				}

				val = System.getProperty("sun.reflect.inflationThreshold");
				if (val != null) {
					try {
						inflationThreshold = Integer.parseInt(val);
					} catch (NumberFormatException e) {
						throw (RuntimeException) new RuntimeException(
								"Unable to parse property sun.reflect.inflationThreshold")
								.initCause(e);
					}
				}

				initted = true;
				return null;
			}
		});
	}

	// ...

	public MethodAccessor newMethodAccessor(Method method) {
		checkInitted();

		if (noInflation) {
			return new MethodAccessorGenerator().generateMethod(
					method.getDeclaringClass(), method.getName(),
					method.getParameterTypes(), method.getReturnType(),
					method.getExceptionTypes(), method.getModifiers());
		} else {
			NativeMethodAccessorImpl acc = new NativeMethodAccessorImpl(method);
			DelegatingMethodAccessorImpl res = new DelegatingMethodAccessorImpl(
					acc);
			acc.setParent(res);
			return res;
		}
	}
}*/