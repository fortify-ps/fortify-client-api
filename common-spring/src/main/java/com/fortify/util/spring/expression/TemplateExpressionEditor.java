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
package com.fortify.util.spring.expression;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorSupport;

import org.springframework.stereotype.Component;

import com.fortify.util.spring.expression.helper.DefaultExpressionHelper;

/**
 * This {@link PropertyEditor} allows parsing String values into a 
 * TemplateExpression object.
 */
@Component
public final class TemplateExpressionEditor extends PropertyEditorSupport {
	public void setAsText(String text) {
		// As these are usually application-specific expressions, we use DefaultExpressionHelper
		// rather than InternalExpressionHelper
        TemplateExpression expression = DefaultExpressionHelper.get().parseTemplateExpression(text);
        setValue(expression);
    }
    
    public Class<?> getTargetClass() {
    	return TemplateExpression.class;
    }

}
