package org.focusns.common.freemarker.directive;

/*
 * #%L
 * FocusSNS Runtime
 * %%
 * Copyright (C) 2011 - 2013 FocusSNS
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as 
 * published by the Free Software Foundation, either version 2.1 of the 
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public 
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-2.1.html>.
 * #L%
 */

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import org.focusns.common.freemarker.directive.OverrideDirective.TemplateDirectiveBodyOverrideWraper;

import java.io.IOException;
import java.util.Map;

public class SuperDirective implements TemplateDirectiveModel {
    public final static String DIRECTIVE_NAME = "super";

    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {

        TemplateDirectiveBodyOverrideWraper current = (TemplateDirectiveBodyOverrideWraper) env.getVariable(DirectiveUtils.OVERRIDE_CURRENT_NODE);
        if (current == null) {
            throw new TemplateException("<@super/> direction must be child of override", env);
        }
        TemplateDirectiveBody parent = current.parentBody;
        if (parent == null) {
            throw new TemplateException("not found parent for <@super/>", env);
        }
        parent.render(env.getOut());

    }

}
