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

import com.google.common.collect.ImmutableMap;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class FeatureBundleTest {

  @Test
  public void testValue() throws Exception {
    final FeatureBundle
        bundle = new FeatureBundle(ImmutableMap.<String, Object>of("foo", 1L,
        "bar", "NaN",
        "int", 4,
        "truth", Boolean.TRUE));

    // truthiness
    assertTrue(bundle.enabled("truth"));
    assertFalse(bundle.enabled("no.key"));
    assertFalse(bundle.enabled("foo"));

    // stringiness
    assertEquals("1", bundle.string("foo"));
    assertEquals("true", bundle.string("truth"));
    assertEquals("", bundle.string("no.key"));

    assertEquals((Long) 1L, bundle.get("foo", Long.class, 0L));
    assertEquals((Long) 2L, bundle.get("xxx", Long.class, 2L));
    assertEquals((Long) 3L, bundle.get("bar", Long.class, 3L));
    assertEquals((Long) 0L, bundle.get("int", Long.class, 0L));
    assertEquals(1L, bundle.get("foo"));

    assertNotNull(bundle.getFeatures());
  }
}
