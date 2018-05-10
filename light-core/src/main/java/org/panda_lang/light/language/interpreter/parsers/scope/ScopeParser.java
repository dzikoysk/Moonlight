/*
 * Copyright (c) 2015-2018 Dzikoysk
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

package org.panda_lang.light.language.interpreter.parsers.scope;

import org.panda_lang.light.design.interpreter.source.*;
import org.panda_lang.light.language.interpreter.parsers.*;
import org.panda_lang.panda.design.interpreter.parser.pipeline.registry.*;
import org.panda_lang.panda.design.interpreter.token.*;
import org.panda_lang.panda.framework.design.interpreter.parser.*;
import org.panda_lang.panda.framework.design.interpreter.parser.component.*;
import org.panda_lang.panda.framework.design.interpreter.parser.pipeline.*;
import org.panda_lang.panda.framework.design.interpreter.parser.pipeline.registry.*;
import org.panda_lang.panda.framework.design.interpreter.token.distributor.*;
import org.panda_lang.panda.framework.language.interpreter.token.pattern.abyss.*;
import org.panda_lang.panda.framework.language.interpreter.token.pattern.abyss.redactor.*;
import org.panda_lang.panda.language.interpreter.*;

@ParserRegistration(target = UniversalPipelines.OVERALL, parserClass = ScopeParser.class, handlerClass = ScopeParserHandler.class)
public class ScopeParser implements UnifiedParser {

    protected static final AbyssPattern PATTERN = new AbyssPatternBuilder()
            .compile(PandaSyntax.getInstance(), "+* { +* }")
            .build();

    @Override
    public void parse(ParserData data) {
        AbyssRedactor redactor = AbyssPatternAssistant.traditionalMapping(PATTERN, data, "scope-declaration", "scope-content");

        data.setComponent(ScopeComponents.DECLARATION, redactor.get("scope-declaration"));
        data.setComponent(ScopeComponents.CONTENT, redactor.get("scope-content"));

        PipelineRegistry pipelineRegistry = data.getComponent(UniversalComponents.PIPELINE);
        ParserPipeline pipeline = pipelineRegistry.getPipeline(LightPipelines.SCOPE);

        SourceStream declarationSignatureStream = new LightSourceStream(data.getComponent(ScopeComponents.DECLARATION));
        UnifiedParser scopeParser = pipeline.handle(declarationSignatureStream);
        scopeParser.parse(data);
    }

}
