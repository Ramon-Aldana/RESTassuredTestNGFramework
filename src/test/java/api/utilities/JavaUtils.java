package api.utilities;


import java.util.Optional;

public class JavaUtils {

	/**
	* Returns the name of the calling method. 
	* It take no arguments as it will use the context of the calling method. 
	* <p>
	* This method uses StackWalker for lazy frame discovery. 
	*
	* @param  <none>  	This method takes no parameters.
	* @return      		The name of the calling method.
	* @see         		StackWalker
	*/

	// Currently not supported in 1.8
//	public static String whoIsCalling() {
//		Optional<String> methodName;
//		
//	    StackWalker walker = StackWalker.getInstance();
//	    methodName = walker.walk(frames -> frames
//	      .skip(1).findFirst()
//	      .map(StackWalker.StackFrame::getMethodName));
//
//	    return methodName.get();
//	}
}
