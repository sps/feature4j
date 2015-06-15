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

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Range;

import java.util.List;
import java.util.Map;

import static com.google.common.base.MoreObjects.firstNonNull;

public class SimpleFeature<T, C extends FeaturesContext> implements Feature<T, C> {

  private final List<FeatureOverride<T>> overrides;

  private final T value;

  private final String name;
  private final String key;

  public SimpleFeature(String name, String key, T value, Iterable<FeatureOverride<T>> overrides) {
    this.name = name;
    this.value = value;
    this.key = key;
    this.overrides = ImmutableList.copyOf(firstNonNull(overrides, ImmutableList.<FeatureOverride<T>>of()));
  }

  @Override
  public String key() {
    return key;
  }

  @Override
  public String name() {
    return name;
  }


  @Override
  public Iterable<FeatureOverride<T>> overrides() {
    return overrides;
  }
}
