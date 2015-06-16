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
package org.feature4j.config;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import junit.framework.TestCase;
import org.feature4j.FeatureOverride;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by dannwebster on 6/15/15.
 */
public class CompositeFeatureOverridesFactoryTest extends TestCase {
  FeatureOverridesFactory fac1 = mock(FeatureOverridesFactory.class);
  FeatureOverridesFactory fac2 = mock(FeatureOverridesFactory.class);

  CompositeFeatureOverridesFactory subject = CompositeFeatureOverridesFactory.fromFactories(fac1, fac2);
  FeatureConfiguration config = new FeatureConfiguration();

  @Test
  public void testFactoriesThatReturnNullShouldReturnEmptyList() throws Exception {
    // GIVEN
    // factories that return null

    // WHEN
    Iterable<FeatureOverride> overrides = subject.createOverrides(config);

    // THEN
    assertNotNull(overrides);
    assertEquals(0, Iterables.size(overrides));

    verify(fac1, times(1)).createOverrides(config);
    verify(fac2, times(1)).createOverrides(config);
  }

  @Test
  public void testFactoriesThatReturnFullListsTheListsShouldBeTheUnion() throws Exception {
    // GIVEN
    FeatureOverride override1 = mock(FeatureOverride.class);
    FeatureOverride override2 = mock(FeatureOverride.class);
    FeatureOverride override3 = mock(FeatureOverride.class);
    FeatureOverride override4 = mock(FeatureOverride.class);

    when(fac1.createOverrides(config)).thenReturn(ImmutableList.of(override1, override2));
    when(fac2.createOverrides(config)).thenReturn(ImmutableList.of(override3, override4));

    // WHEN
    Iterable<FeatureOverride> overrides = subject.createOverrides(config);

    // THEN
    assertNotNull(overrides);
    assertEquals(4, Iterables.size(overrides));

    assertEquals(override1, Iterables.get(overrides, 0));
    assertEquals(override2, Iterables.get(overrides, 1));
    assertEquals(override3, Iterables.get(overrides, 2));
    assertEquals(override4, Iterables.get(overrides, 3));

    verify(fac1, times(1)).createOverrides(config);
    verify(fac2, times(1)).createOverrides(config);
  }

}