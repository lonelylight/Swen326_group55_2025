package fusion;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FallbackManagerTest {
    @Test
    void testFallbackBehavior() {
        FallbackManager fm = new FallbackManager();
        assertFalse(fm.shouldFallback());
        fm.reportFailure("radar");
        assertTrue(fm.shouldFallback());
        fm.reset();
        assertFalse(fm.shouldFallback());
    }
}
