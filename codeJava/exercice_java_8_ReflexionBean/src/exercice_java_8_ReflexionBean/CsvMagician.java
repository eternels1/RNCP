package exercice_java_8_ReflexionBean;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import exercice_java_8_ReflexionBean.Annotations.BooleanConverter;
import exercice_java_8_ReflexionBean.Annotations.IgnoreGetter;

public class CsvMagician {
	
	
	
	private static boolean isGetter(Method m) {
		if (!m.getName().startsWith("get")) 
			return false;
		if (!Modifier.isPublic(m.getModifiers()))
			return false;
		if (m.getReturnType().equals(void.class)) 
			return false;
		if (m.getParameterTypes().length !=0) 
			return false;
		if (m.getReturnType().equals(Class.class))
			return false;
		
		return true;
		
	}
	

	public static String beanToCsv (Object bean) {
		
		StringBuilder beancvstoAdd= new StringBuilder();
		
		Class beanclass= bean.getClass();
		Method[] methodesduBean= beanclass.getMethods();
		
		List<Method> getters = Arrays.stream(methodesduBean)
									 .filter(m -> isGetter(m))
									 .collect(Collectors.toList());
		
		for (Method getter : getters) {
			
			if (getter.isAnnotationPresent(IgnoreGetter.class)) {
				continue;
			}
			
			
			String truestring="true";
			String falsestring = "false";
			if (getter.isAnnotationPresent(BooleanConverter.class)) {
				BooleanConverter bc= getter.getAnnotation(BooleanConverter.class);
				truestring=bc.trueString();
				falsestring=bc.falseString();
			}
			
			
			
			try {
				if (getter.getReturnType().equals(boolean.class)) {
					
					if ((boolean)getter.invoke(bean)) {
						beancvstoAdd.append(truestring).append(';');
					}
					else 
						beancvstoAdd.append(falsestring).append(';');
					
				}				
				else {
					beancvstoAdd.append(getter.invoke(bean)).append(';');	
				}
				
				
				
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
						
		}
		return beancvstoAdd.substring(0, beancvstoAdd.length()-1);		
		
	}
	
	
	
}
