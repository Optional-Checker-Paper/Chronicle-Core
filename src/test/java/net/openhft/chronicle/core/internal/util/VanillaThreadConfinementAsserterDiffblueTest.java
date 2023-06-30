package net.openhft.chronicle.core.internal.util;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class VanillaThreadConfinementAsserterDiffblueTest {
  /**
  * Methods under test: 
  * 
  * <ul>
  *   <li>default or parameterless constructor of {@link VanillaThreadConfinementAsserter}
  *   <li>{@link VanillaThreadConfinementAsserter#toString()}
  * </ul>
  */
  @Test
  public void testConstructor() {
    // Arrange, Act and Assert
    assertEquals("VanillaThreadConfinementAsserter{initialThread=null}",
        (new VanillaThreadConfinementAsserter()).toString());
  }
}

