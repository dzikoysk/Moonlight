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

package org.panda_lang.light.language.interpreter.pattern.phraseme;

import org.panda_lang.light.language.interpreter.parser.phrase.Phraseme;
import org.panda_lang.light.language.interpreter.pattern.lexical.extractor.LexicalExtractorResult;

public class PhrasemePatternResult {

    private final boolean matched;
    protected LexicalExtractorResult<Phraseme> lexicalResult;

    public PhrasemePatternResult(boolean matched) {
        this.matched = matched;
    }

    public PhrasemePatternResult(LexicalExtractorResult<Phraseme> result) {
        this.matched = result.isMatched();
        this.lexicalResult = result;
    }

    public boolean isMatched() {
        return matched;
    }

}
