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

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableMap;

import java.util.Map;

import static com.google.common.base.MoreObjects.firstNonNull;

public class FeatureBundle {

  private final Map<String, Object> features;

  public FeatureBundle(Map<String, Object> features) {
    this.features = ImmutableMap.copyOf(features);
  }

  public Boolean enabled(String key) {
    return get(key, Boolean.class, Boolean.FALSE);
  }

  public String string(String key) {
    return firstNonNull(get(key), "").toString();
  }

  public <T> T get(String key, Class<T> valueClass, T defaultValue) {
    final Optional<T> value = get(key, valueClass);
    return value.isPresent() ? value.get() : defaultValue;
  }

  public <T> Optional<T> get(String key, Class<T> valueClass) {
    final Object featureValue = features.get(key);
    if (featureValue == null || !featureValue.getClass().isAssignableFrom(valueClass)) {
      return Optional.absent();
    }
    return Optional.fromNullable((T) featureValue);
  }

  public Object get(String key) {
    return features.get(key);
  }

  public Map<String, Object> getFeatures() {
    return features;
  }
}
