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
import com.google.common.collect.Range;

import java.util.Map;

import static com.google.common.base.MoreObjects.firstNonNull;

public class SimpleFeature<T> implements Feature<T> {

  private final Map<Range, T> overrides;

  private final T value;

  private final String name;
  private final String key;

  public SimpleFeature(String name, String key, T value, Map<Range, T> overrides) {
    this.name = name;
    this.value = value;
    this.key = key;
    this.overrides = firstNonNull(overrides, ImmutableMap.<Range, T>of());
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
  public T value(FeaturesContext context) {
    for (Map.Entry<Range, T> override : overrides().entrySet()) {
      if (override.getKey().contains(firstNonNull(context.getBucketId(), 0))) {
        return override.getValue();
      }
    }
    return value;
  }

  @Override
  public Map<Range, T> overrides() {
    return overrides;
  }
}
