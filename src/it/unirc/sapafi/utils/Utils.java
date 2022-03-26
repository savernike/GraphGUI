package it.unirc.sapafi.utils;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;

public class Utils {

	public String printMethod(Method method, boolean showPackage) {
		String visibility = Modifier.toString(method.getModifiers());
		String returnType = showPackage ? method.getGenericReturnType().toString()
				: removePackageNameIfPresent(method.getGenericReturnType().toString());
		String methodName = method.getName();
		Type[] params = method.getGenericParameterTypes();
		String parsedParams = "(" + parseParamsToString(params, showPackage) + ")";
		System.out.println(parsedParams);
		String[] completeMethodStr = { visibility, returnType, methodName + parsedParams };
		return getSeparatedWords(completeMethodStr);
	}

	private String parseParamsToString(Type[] params, boolean showPackage) {
		String res = "";
		if (params.length == 0)
			return res;
		for (int i = 0; i < params.length; i++) {
			String paramType = showPackage ? params[i].getTypeName()
					: removePackageNameIfPresent(params[i].getTypeName());
			String paramInstance = "arg" + i;
			res += getSeparatedWords(new String[] { paramType, paramInstance }) + ", ";
		}
		return res.substring(0, res.lastIndexOf(",")).trim();
	}

	public String removePackageNameIfPresent(String returnType) {
		int dotIndex = returnType.lastIndexOf(".");
		String res = dotIndex == -1 ? returnType : returnType.substring(dotIndex + 1);
		return res;
	}

	public String getSeparatedWords(String[] words) {
		String res = "";
		for (String word : words) {
			res += word + " ";
		}
		return res.trim();
	}

}
