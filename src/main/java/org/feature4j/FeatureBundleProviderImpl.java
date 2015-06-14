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

import java.util.Collection;
import java.util.List;

/**
 * Provide a "bundle" of features based on...contextual input.
 */
public class FeatureBundleProviderImpl implements FeatureBundleProvider {

  private final Collection<Feature<?, ?>> features;

  public FeatureBundleProviderImpl(List<Feature<?, ?>> features) {
    this.features = features;
  }

  @Override
  public FeatureBundle getFeatures(FeaturesContext request) {
    final ImmutableMap.Builder<String, Object> featureMapBuilder = ImmutableMap.builder();
    for (Feature feature : features) {
      featureMapBuilder.put(feature.key(), feature.value(request));
    }
    return new FeatureBundle(featureMapBuilder.build());
  }
}
