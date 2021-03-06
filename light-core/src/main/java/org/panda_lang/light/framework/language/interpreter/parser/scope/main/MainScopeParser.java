/*
 * Copyright (c) 2016-2018 Dzikoysk
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.panda_lang.light.framework.language.interpreter.parser.scope.main;


import org.panda_lang.light.framework.language.interpreter.parser.LightPipelines;
import org.panda_lang.light.framework.language.interpreter.parser.scope.ScopeComponents;
import org.panda_lang.panda.framework.design.interpreter.parser.ParserData;
import org.panda_lang.panda.framework.design.interpreter.parser.UnifiedParser;
import org.panda_lang.panda.framework.design.interpreter.parser.generation.casual.GenerationLayer;
import org.panda_lang.panda.framework.language.interpreter.parser.pipeline.ParserRegistration;

@ParserRegistration(target = LightPipelines.SCOPE, parserClass = MainScopeParser.class, handlerClass = MainScopeParserHandler.class)
public class MainScopeParser implements UnifiedParser {

    @Override
    public boolean parse(ParserData data, GenerationLayer nextLayer) {
        data.setComponent(ScopeComponents.SCOPE, new MainScope());
        return true;
    }

}
