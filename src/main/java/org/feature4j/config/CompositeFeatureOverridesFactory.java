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
import org.feature4j.FeatureOverride;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

public class CompositeFeatureOverridesFactory implements FeatureOverridesFactory {
  private final Iterable<FeatureOverridesFactory> featureOverridesFactories;

  public static CompositeFeatureOverridesFactory fromFactories(FeatureOverridesFactory... factories) {
    return fromIterable(Arrays.asList(factories));
  }

  public static CompositeFeatureOverridesFactory fromIterable(
      Iterable<FeatureOverridesFactory> featureOverridesFactories) {
    return new CompositeFeatureOverridesFactory(featureOverridesFactories);
  }

  CompositeFeatureOverridesFactory(Iterable<FeatureOverridesFactory> featureOverridesFactories) {
    this.featureOverridesFactories =
        ImmutableList.copyOf(checkNotNull(featureOverridesFactories, "featureOverridesFactories must be non-null"));
  }
  @Override
  public Iterable<FeatureOverride> createOverrides(FeatureConfiguration featureConfiguration) {
    List<FeatureOverride> allFeatureOverrides = new ArrayList<>();
    for (FeatureOverridesFactory factory : featureOverridesFactories) {
      Iterable<FeatureOverride> featureOverrides = factory.createOverrides(featureConfiguration);
      if (featureOverrides != null){
        Iterables.addAll(allFeatureOverrides, featureOverrides);
      }
    }
    return allFeatureOverrides;
  }
}
