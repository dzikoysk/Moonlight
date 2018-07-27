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

package org.panda_lang.light.design.architecture.phraseme;

import org.panda_lang.light.design.architecture.type.Type;
import org.panda_lang.panda.framework.design.runtime.ExecutableBranch;

public class Phraseme {

    private final PhrasemeCallback callback;
    private final Type returnType;

    public Phraseme(Type returnType, PhrasemeCallback callback) {
        this.callback = callback;
        this.returnType = returnType;
    }

    public void execute(ExecutableBranch branch) {
        callback.execute(branch);
    }

    public PhrasemeCallback getCallback() {
        return callback;
    }

    public Type getReturnType() {
        return returnType;
    }

}
