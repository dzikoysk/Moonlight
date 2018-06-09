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

package org.panda_lang.light.design.interpreter.token.lexical;

public class LexicalPatternUnit extends LexicalPatternElement {

    private final UnitType type;
    private final String value;

    public LexicalPatternUnit(UnitType type, String element, boolean optional) {
        super.optional = optional;
        this.type = type;
        this.value = element;
    }

    public boolean isExpression() {
        return type == UnitType.EXPRESSION;
    }

    public boolean isStatic() {
        return type == UnitType.STATIC;
    }

    public String getValue() {
        return value;
    }

    public enum UnitType {

        EXPRESSION,
        STATIC

    }

}