package troubleShootSearch.util;

public class MyLogger {

	
	public static enum DebugLevel {
		ERROR, MATCH_FOUND, CONSTRUCTOR, NONE
	}

	private static DebugLevel debugLevel;

	public static void setDebugValue(int levelIn) {
		switch (levelIn) {
		case 0:
			debugLevel = DebugLevel.ERROR;
			break;
		case 1:
			debugLevel = DebugLevel.MATCH_FOUND;
			break;
		case 2:
			debugLevel = DebugLevel.CONSTRUCTOR;
			break;
		default:
			debugLevel = DebugLevel.NONE;
			break;
		}
	}

	public static void setDebugValue(DebugLevel levelIn) {
		debugLevel = levelIn;
	}

	// @return None
	public static void writeMessage(String message, DebugLevel levelIn) {
		if (levelIn == debugLevel)
			System.out.println(message);
	}

	/**
	 * @return String
	 */
	public String toString() {
		return "Debug Level is " + debugLevel;
	}
}