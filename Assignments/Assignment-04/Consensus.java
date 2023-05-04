import java.util.concurrent.atomic.AtomicReference;

public class Consensus {
    private final AtomicReference<Object> decision = new AtomicReference<Object>(null);

    public Object decide(Object v) {
        while (true) {
            Object current = decision.get();
            if (current != null) {
                return current;
            }
            if (decision.compareAndSet(null, v)) {
                return v;
            }
        }
    }
}
