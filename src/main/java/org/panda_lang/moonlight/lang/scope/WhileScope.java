package org.panda_lang.moonlight.lang.scope;

import org.panda_lang.moonlight.core.element.scope.Scope;
import org.panda_lang.panda.core.Alice;
import org.panda_lang.panda.core.syntax.Essence;
import org.panda_lang.panda.core.syntax.Factor;
import org.panda_lang.panda.lang.BooleanEssence;

public class WhileScope extends Scope {

    private final Factor factor;

    public WhileScope(Factor factor) {
        this.factor = factor;
        this.setName("while::" + atomicInteger.incrementAndGet());
    }

    @Override
    public Essence run(Alice alice) {
        while (factor.<BooleanEssence> getValue(alice).isTrue()) {
            final Essence essence = super.run(alice);
            if (essence != null) {
                return essence;
            }
        }
        return null;
    }

}
