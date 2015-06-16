/*
 * Copyright 2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
