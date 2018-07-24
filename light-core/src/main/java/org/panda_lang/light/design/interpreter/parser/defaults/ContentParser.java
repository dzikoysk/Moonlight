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

package org.panda_lang.light.design.interpreter.parser.defaults;

import org.panda_lang.light.design.interpreter.token.*;
import org.panda_lang.light.language.interpreter.parser.scope.*;
import org.panda_lang.panda.framework.design.architecture.statement.*;
import org.panda_lang.panda.framework.design.interpreter.parser.*;
import org.panda_lang.panda.framework.design.interpreter.parser.component.*;
import org.panda_lang.panda.framework.design.interpreter.token.*;
import org.panda_lang.panda.framework.design.interpreter.token.distributor.*;
import org.panda_lang.panda.framework.language.interpreter.parser.*;

public class ContentParser implements UnifiedParser {

    @Override
    public void parse(ParserData data) {
        SentenceParser sentenceParser = new SentenceParser();

        SourceStream source = data.getComponent(UniversalComponents.SOURCE_STREAM);
        Scope scope = data.getComponent(ScopeComponents.SCOPE);

        while (source.hasUnreadSource()) {
            TokenRepresentation phrase = source.read();

            if (phrase instanceof SentenceRepresentation) {
                Statement statement = sentenceParser.parse((SentenceRepresentation) phrase);

                if (statement != null) {
                    scope.addStatement(statement);
                    continue;
                }

                throw new PandaParserFailure("Unknown statement", data);
            }

            throw new PandaParserFailure("Unknown token type", data);
        }
    }

}
