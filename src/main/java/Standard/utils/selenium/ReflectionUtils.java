package Standard.utils.selenium;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import org.junit.runner.Description;

import Standard.utils.annotations.Build;
import Standard.utils.annotations.Ct;
import Standard.utils.annotations.Project;
import Standard.utils.annotations.TestPlan;

public class ReflectionUtils {
	
	/**
	 * Este_metodo_coleta o meta-dado_da annotation @Project e_retorna o valor_em string
	 */
	public static String getProject(Description description) {
		
		String project = null;
		Method[] methods = description.getTestClass().getMethods();
		
		for(Method method : methods) {
			for(Annotation annotation : method.getAnnotations()) {
				if(annotation instanceof Project) {
					project = ((Project) annotation).value();
					break;
				}
			}
		}
		
		return project;
	}
	
	/**
	 * Este_metodo_coleta o meta-dado_da annotation @Build e_retorna o valor_em string
	 */
	public static String getBuild(Description description) {
		
		String build = null;
		Method[] methods = description.getTestClass().getMethods();
		
		for(Method method : methods) {
			for(Annotation annotation : method.getAnnotations()) {
				if(annotation instanceof Build) {
					build = ((Build) annotation).value();
					break;
				}
			}
		}
		
		return build;
	}
	
	/**
	 * Este_metodo_coleta o meta-dado_da annotation @Ct e_retorna o valor_em string
	 */
	public static String getCt(Description description) {
		
		String ct = null;
		Method[] methods = description.getTestClass().getMethods();
		
		for(Method method : methods) {
			for(Annotation annotation : method.getAnnotations()) {
				if(annotation instanceof Ct) {
					ct = ((Ct) annotation).value();
					break;
				}
			}
		}
		
		return ct;
	}
	
	/**
	 * Este_metodo_coleta o meta-dado_da annotation @TestPlan e_retorna o valor_em string
	 */
	public static String getTestPlan(Description description) {
		
		String testPlan = null;
		Method[] methods = description.getTestClass().getMethods();
		
		for(Method method : methods) {
			for(Annotation annotation : method.getAnnotations()) {
				if(annotation instanceof TestPlan) {
					testPlan = ((TestPlan) annotation).value();
					break;
				}
			}
		}
		
		return testPlan;
	}
	
}
