package it.unirc.sapafi.utils;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;

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
		String returnTypeParse = returnType;
		if(returnType.contains(">")) {
			List<String> list = divideBracketStrings(returnTypeParse);
			for (int i = 0; i < list.size(); i++) {
				list.set(i, i == list.size() - 1 ? removeFromDot(list.get(i)) : "<" + removeFromDot(list.get(i) + ">"));
			}
		}
		return ""; //TODO
		
	}
	
	private String removeFromDot(String inputString) {
		int dotIndex = inputString.lastIndexOf(".");
		String res = dotIndex == -1 ? inputString : inputString.substring(dotIndex + 1);
		return res;
	}
	
	private List<String> divideBracketStrings(String returnTypeParse) {
		List<String> list = new LinkedList<String>();
		int genericsCloseIndex = 0, genericsOpenIndex = 0;
		while (genericsCloseIndex != -1 && genericsOpenIndex != -1) {
			genericsCloseIndex = returnTypeParse.lastIndexOf("<");
			genericsOpenIndex = returnTypeParse.indexOf(">");
			if(genericsCloseIndex != -1 && genericsOpenIndex != -1) {
				list.add(returnTypeParse);
				continue;
			}
			String genericSubstring = returnTypeParse.substring(genericsOpenIndex, genericsCloseIndex+1);
			list.add(genericSubstring.replace("<", "").replace(">", ""));
			returnTypeParse.replace(genericSubstring, "");
		}
		return list;
	}

	public String getSeparatedWords(String[] words) {
		String res = "";
		for (String word : words) {
			res += word + " ";
		}
		return res.trim();
	}

}
