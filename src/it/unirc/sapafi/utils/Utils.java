package it.unirc.sapafi.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import it.unirc.sapafi.enumerative.GraphType;

public class Utils {
	
	public Utils() {
	}

	
	public String printMethod(Method method, boolean showPackage) {
		String visibility = Modifier.toString(method.getModifiers());
		String returnType = showPackage ? method.getGenericReturnType().getTypeName()
				: removePackage(method.getGenericReturnType().getTypeName());
		String methodName = method.getName();
		Type[] params = method.getGenericParameterTypes();
		String parsedParams = "(" + parseParams(params, showPackage) + ")";
		String[] completeMethodSignature = { visibility, returnType, methodName + parsedParams };
		return getSeparatedWords(completeMethodSignature);
	}

	private String parseParams(Type[] params, boolean showPackage) {
		String res = "";
		if (params.length == 0) {
			return res;
		}
		for (int i = 0; i < params.length; i++) {
			String singleParam = printParam(params[i], showPackage);
			res += singleParam + " arg" + i + ", ";
		}
		return res.substring(0, res.length() - 2);
	}

	private String printParam(Type type, boolean showPackage) {
		String res = showPackage ? type.getTypeName() : removePackage(type.getTypeName());
		return res;
	}

	public String removePackage(String typeName) {
		String res = "";
		if (!(typeName.contains("<") || typeName.contains(">"))) {
			res = removePackageName(typeName);
			return res;
		}
		// Divide all generics and insert all in a list reparsered
		List<String> list = divideGenerics(typeName);
		// Merge all the generics in list into a string
		res = mergeGenerics(list);

		return res;
	}

	private String mergeGenerics(List<String> list) {
		StringBuilder res = new StringBuilder(list.get(0));
		for (int i = 1; i < list.size(); i++) {
			int genericsClose = res.indexOf(">");
			res.insert(genericsClose == -1 ? res.length() : genericsClose, list.get(i));
		}
		return res.toString();
	}

	private List<String> divideGenerics(String className) {
		int genericsOpenIndex = 0;
		int genericsCloseIndex = 0;
		List<String> list = new ArrayList<String>();
		// Divide all generics and insert into a list
		while (genericsOpenIndex != -1 && genericsCloseIndex != -1) {
			genericsOpenIndex = className.lastIndexOf("<");
			genericsCloseIndex = className.indexOf(">");
			boolean hasGeneric = genericsOpenIndex != -1 && genericsCloseIndex != -1;
			String getGenericString = hasGeneric ? className.substring(genericsOpenIndex, genericsCloseIndex + 1)
					: className;
			className = className.replace(getGenericString, "");
			list.add(0, hasGeneric ? getGenericString.substring(1, getGenericString.length() - 1) : getGenericString);
		}
		// Parse all the generics, removing the package name
		for (int i = 0; i < list.size(); i++) {
			String item = list.get(i);
			String newItem = (i == 0 ? removePackageName(item) : "<" + removePackageName(item) + ">");
			list.set(i, newItem);
		}

		return list;
	}

	private String removePackageName(String item) {
		String res = "";
		int dotIndex = item.lastIndexOf(".");
		if (dotIndex == -1)
			return item;
		res = item.substring(dotIndex + 1);
		return res;
	}

	public String getSeparatedWords(String[] words) {
		String res = "";
		for (String word : words) {
			res += word + " ";
		}
		return res.trim();
	}

	public String graphTypeToString(GraphType g) {
		String res = "";
		switch (g) {
		case DENSEGRAPH:
			res = "DenseGraph";
			break;
		case DIRECTEDDENSEGRAPH:
			res = "DirectedDenseGraph";
			break;
		case DIRECTEDSPARSEGRAPH:
			res = "DirectedSparseGraph";
			break;
		case DIRECTEDWEIGHTEDDENSEGRAPH:
			res = "DirectedWeightedDenseGraph";
			break;
		case DIRECTEDWEIGHTEDSPARSEGRAPH:
			res = "DirectedWeightedSparseGraph";
			break;
		case SPARSEGRAPH:
			res = "SparseGraph";
			break;
		case WEIGHTEDDENSEGRAPH:
			res = "WeightedDenseGraph";
			break;
		case WEIGHTEDSPARSEGRAPH:
			res = "WeightedSparseGraph";
			break;
		}
		return res;

	}

	@SuppressWarnings("rawtypes")
	public String lookForGraph(Class externalClass) {
		Field[] fields = externalClass.getDeclaredFields();
		String res = "";
		if (fields.length == 0)
			return res;
		for (Field field : fields) {
			if (isGraph(field)) {
				res = field.getGenericType().getTypeName();
				return res;
			}
		}
		return res;
	}

	private boolean isGraph(Field field) {
		String nameType = field.getType().getSimpleName();
		boolean res = checkIfGraph(nameType, GraphType.DENSEGRAPH)
				|| checkIfGraph(nameType, GraphType.DIRECTEDDENSEGRAPH)
				|| checkIfGraph(nameType, GraphType.DIRECTEDSPARSEGRAPH)
				|| checkIfGraph(nameType, GraphType.DIRECTEDWEIGHTEDDENSEGRAPH)
				|| checkIfGraph(nameType, GraphType.DIRECTEDWEIGHTEDSPARSEGRAPH)
				|| checkIfGraph(nameType, GraphType.SPARSEGRAPH)
				|| checkIfGraph(nameType, GraphType.WEIGHTEDDENSEGRAPH)
				|| checkIfGraph(nameType, GraphType.WEIGHTEDSPARSEGRAPH);
		return res;
		

	}

	public boolean checkIfGraph(String trialName, GraphType g) {
		return trialName.equalsIgnoreCase(graphTypeToString(g));
	}

}
