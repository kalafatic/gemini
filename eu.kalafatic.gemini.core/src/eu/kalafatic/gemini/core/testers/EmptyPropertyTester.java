package eu.kalafatic.gemini.core.testers;

import org.eclipse.core.expressions.PropertyTester;

public class EmptyPropertyTester extends PropertyTester {

	public static final String PROPERTY_NAMESPACE = "eu.kalafatic.gemini.core.testers";
	public static final String PROPERTY_CAN_FOO = "isEmpty";

	@Override
	public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {

		return false;
	}

}
