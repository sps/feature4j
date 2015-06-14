package org.feature4j;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class FeatureValueHydratorTest {

  @Test
  public void testStringHydrator() {

    final Object o = new Object();

    for (FeatureValueHydrator.String impl : FeatureValueHydrator.String.values()) {
      assertEquals(impl, FeatureValueHydrator.String.valueOf(impl.name()));
      assertEquals(String.class, impl.type());
      assertEquals(o.toString(), impl.value(o));
      assertEquals("string", impl.value("string"));
      assertNull(impl.value(null));
    }
  }

  @Test
  public void testBooleanHydrator() {
    for (FeatureValueHydrator.Boolean impl : FeatureValueHydrator.Boolean.values()) {
      assertEquals(impl, FeatureValueHydrator.Boolean.valueOf(impl.name()));
      assertEquals(Boolean.class, impl.type());
      assertTrue(impl.value("true"));
      assertFalse(impl.value("false"));
      assertFalse(impl.value(""));
      assertFalse(impl.value(null));
    }
  }
}
