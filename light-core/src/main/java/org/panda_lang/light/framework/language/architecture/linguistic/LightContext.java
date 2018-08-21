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

package org.panda_lang.light.framework.language.architecture.linguistic;

import org.jetbrains.annotations.Nullable;
import org.panda_lang.light.framework.design.architecture.linguistic.Context;
import org.panda_lang.light.framework.design.architecture.linguistic.ContextComponent;
import org.panda_lang.light.framework.design.architecture.linguistic.LinguisticAct;
import org.panda_lang.light.framework.design.architecture.linguistic.type.Type;
import org.panda_lang.light.framework.design.interpreter.pattern.linguistic.LinguisticCandidate;
import org.panda_lang.light.framework.design.interpreter.pattern.linguistic.LinguisticResultUtils;

import java.util.Collection;
import java.util.HashSet;

public class LightContext implements Context {

    private final Collection<ContextComponent<?>> context;

    private LightContext(Collection<ContextComponent<?>> context) {
        this.context = new HashSet<>(context);
    }

    public LightContext() {
        this(new HashSet<>());
    }

    @Override
    public void importComponent(ContextComponent<?> component) {
        context.add(component);
    }

    @Override
    public @Nullable LinguisticAct find(String sentence, @Nullable LinguisticCandidate<LinguisticAct> previousCandidate) {
        LinguisticCandidate<LinguisticAct> candidate = findNext(sentence, null);

        while (candidate != null) {
            if (!candidate.isMatched() || candidate.isDefinite()) {
                break;
            }

            LinguisticCandidate<LinguisticAct> currentCandidate = findNext(sentence, candidate);

            if (candidate.equals(currentCandidate)) {
                candidate = null;
                break;
            }

            candidate = currentCandidate;
        }

        if (candidate == null) {
            return null;
        }

        return LinguisticResultUtils.assignParameters(candidate);
    }

    private LinguisticCandidate<LinguisticAct> findNext(String sentence, @Nullable LinguisticCandidate<LinguisticAct> previousCandidate) {
        for (ContextComponent<?> contextComponent : context) {
            LinguisticCandidate<LinguisticAct> candidate = contextComponent.recognize(this, sentence, previousCandidate);

            if (!candidate.isMatched()) {
                continue;
            }

            return candidate;
        }

        return new LinguisticCandidate<>(false);
    }

    @Override
    @SuppressWarnings("unchecked")
    public @Nullable Type<?> getType(String type) {
        for (ContextComponent<?> component : context) {
            if (Type.class != component.getComponentType()) {
                continue;
            }

            Type<?> matchedType = getType((ContextComponent<? extends Type<?>>) component, type);
        }

        return null;
    }

    private @Nullable Type<?> getType(ContextComponent<? extends Type<?>> types, String type) {
        for (Type<?> element : types.getElements()) {
            if (element.getClassName().equals(type)) {
                return element;
            }
        }

        return null;
    }

    @Override
    public LightContext fork() {
        return new LightContext(context);
    }

}