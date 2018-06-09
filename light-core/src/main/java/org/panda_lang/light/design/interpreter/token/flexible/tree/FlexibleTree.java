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

package org.panda_lang.light.design.interpreter.token.flexible.tree;

import org.panda_lang.light.design.interpreter.token.*;
import org.panda_lang.light.design.interpreter.token.flexible.*;
import org.panda_lang.panda.framework.design.architecture.dynamic.*;

import java.util.*;

/**
 * tree { map: node { } }
 * tree.find -> phrase by char
 *
 */
public class FlexibleTree {

    private FlexibleTreeNode node;

    public FlexibleTree() {
        this.node = new FlexibleTreeNode();
    }

    public void merge(FlexibleModel model) {
        FlexibleModelElement modelElement = model.getStructure();
        this.mergeBranch(modelElement);
    }

    public void mergeBranch(FlexibleModelElement element) {
        Collection<FlexibleTreeNode> previousNodes = new HashSet<>();
        previousNodes.add(node);

        for (Object[] level : element.getElements()) {
            for (FlexibleTreeNode previousNode : previousNodes) {

                this.mergeFragment(previousNode, level);
            }

            previousNodes.clear();
        }
    }

    public void mergeFragment(FlexibleTreeNode parent, Object[] elements) {
        for (Object element : elements) {
            FlexibleTreeElement treeElement = new FlexibleTreeElement(element);
        }
    }

    /*

    tree {
        send {
            message {
                <string> {
                    to {
                        console {
                            return id:1
                        }

                        terminal {
                            retuen id:1
                        }
                    }
                }
            }

            <string> {
                to {
                    console {
                        return id:1
                    }

                    terminal {
                        return id:1
                    }
                }
            }
        }
    }



     */
    public Executable find(Phrase phrase) {
        String value = phrase.getTokenValue();

        for (int i = 0; i < value.toCharArray().length; i++) {
            String current = value.substring(0, i);

        }

        return null;
    }

}