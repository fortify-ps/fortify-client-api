/*******************************************************************************
 * (c) Copyright 2020 Micro Focus or one of its affiliates, a Micro Focus company
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a 
 * copy of this software and associated documentation files (the 
 * "Software"), to deal in the Software without restriction, including without 
 * limitation the rights to use, copy, modify, merge, publish, distribute, 
 * sublicense, and/or sell copies of the Software, and to permit persons to 
 * whom the Software is furnished to do so, subject to the following 
 * conditions:
 * 
 * The above copyright notice and this permission notice shall be included 
 * in all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY 
 * KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE 
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR 
 * PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE 
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, 
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN 
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS 
 * IN THE SOFTWARE.
 ******************************************************************************/
package com.fortify.util.spring;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.expression.MapAccessor;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.PropertyAccessor;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.ReflectivePropertyAccessor;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import com.fortify.util.spring.expression.SimpleExpression;
import com.fortify.util.spring.expression.TemplateExpression;

/**
 * This class provides several utility methods related to 
 * Spring Expression Language, for example for evaluating
 * (template) expressions on input objects.
 */
public class SpringExpressionUtil {
	private static final List<PropertyAccessor> PROPERTY_ACCESSORS = getPropertyAccessors();
	private static final SpelExpressionParser SPEL_PARSER = new SpelExpressionParser();
	private static final StandardEvaluationContext SPEL_CONTEXT = createStandardEvaluationContext();
	
	protected SpringExpressionUtil() {}
	
	public static final void addPropertyAccessors(PropertyAccessor... propertyAccessors) {
		PROPERTY_ACCESSORS.addAll(Arrays.asList(propertyAccessors));
	}
	
	/**
	 * Add Spring's {@link MapAccessor} to the list of standard property accessors 
	 * @return
	 */
	private static final List<PropertyAccessor> getPropertyAccessors() {
		List<PropertyAccessor> result = new ArrayList<PropertyAccessor>();
		result.add(new ReflectivePropertyAccessor());
		result.add(new MapAccessorIgnoreNonExistingProperties());
		return result;
	}
	
	public static final StandardEvaluationContext createStandardEvaluationContext() {
		StandardEvaluationContext result = new StandardEvaluationContext();
		result.setPropertyAccessors(PROPERTY_ACCESSORS);
		return result;
	}

	public static final StandardEvaluationContext getStandardEvaluationContext() {
		return SPEL_CONTEXT;
	}

	/**
	 * Parse the given string as a SpEL expression.
	 * @param exprStr
	 * @return The SpEL {@link Expression} object for the given expression string, or null if input is null
	 */
	public static final SimpleExpression parseSimpleExpression(String exprStr) {
		return exprStr==null ? null : new SimpleExpression(SPEL_PARSER.parseExpression(exprStr));
	}
	
	/**
	 * Parse the given string as a SpEL template expression.
	 * @param exprStr
	 * @return The SpEL {@link Expression} object for the given expression string, or null if input is null 
	 */
	public static final TemplateExpression parseTemplateExpression(String exprStr) {
		return exprStr==null ? null : new TemplateExpression(SPEL_PARSER.parseExpression(exprStr.replace("\\n", "\n"), new TemplateParserContext("${","}")));
	}
	
	public static final <T> T evaluateExpression(Object input, Expression expression, Class<T> returnType) {
		return evaluateExpression(null, input, expression, returnType);
	}

	public static final <T> T evaluateExpression(EvaluationContext context, Object input, Expression expression, Class<T> returnType) {
		if ( input==null || expression==null ) { return null; }
		context = context!=null ? context : getStandardEvaluationContext(); 
		return expression.getValue(context, input, returnType);
	}
	
	public static final <T> T evaluateExpression(Object input, String expression, Class<T> returnType) {
		return evaluateExpression(null, input, expression, returnType);
	}

	public static final <T> T evaluateExpression(EvaluationContext context, Object input, String expression, Class<T> returnType) {
		return evaluateExpression(context, input, parseSimpleExpression(expression), returnType);
	}
	
	public static final <T> T evaluateTemplateExpression(Object input, String expression, Class<T> returnType) {
		return evaluateTemplateExpression(null, input, expression, returnType);
	}

	public static final <T> T evaluateTemplateExpression(EvaluationContext context, Object input, String expression, Class<T> returnType) {
		return evaluateExpression(context, input, parseTemplateExpression(expression), returnType);
	}
}
