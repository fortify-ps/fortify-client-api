/*******************************************************************************
 * (c) Copyright 2017 EntIT Software LLC
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
package com.fortify.api.util.spring.propertyeditor;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorSupport;

import org.springframework.stereotype.Component;

import com.fortify.api.util.spring.SpringExpressionUtil;
import com.fortify.api.util.spring.SpringContextUtil.PropertyEditorWithTargetClass;
import com.fortify.api.util.spring.expression.TemplateExpression;

/**
 * This {@link PropertyEditor} allows parsing String values into a 
 * TemplateExpression object.
 */
@Component
public final class TemplateExpressionPropertyEditor extends PropertyEditorSupport implements PropertyEditorWithTargetClass {
	public void setAsText(String text) {
        TemplateExpression expression = SpringExpressionUtil.parseTemplateExpression(text);
        setValue(expression);
    }
    
    public Class<?> getTargetClass() {
    	return TemplateExpression.class;
    }

}
