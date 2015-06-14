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

import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Iterables;
import com.google.common.collect.Range;
import com.google.gson.Gson;

import org.feature4j.Feature;
import org.feature4j.FeatureBundleProvider;
import org.feature4j.FeatureBundleProviderImpl;
import org.feature4j.FeatureValueHydrator;
import org.feature4j.SimpleFeature;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

/**
 * Using json as a config file format is a bit brittle and prone to human error.
 */
public class JSONFeatureBundleProviderFactory implements FeatureBundleProviderFactory {

  private static final Map<String, FeatureValueHydrator>
      HYDRATORS =
      ImmutableMap.<String, FeatureValueHydrator>builder()
                  .put("string", FeatureValueHydrator.String.INSTANCE)
                  .put("boolean", FeatureValueHydrator.Boolean.INSTANCE)
                  .build();

  private static final Splitter CSV_SPLITTER = Splitter.on(",").omitEmptyStrings();

  private final InputStream jsonResource;

  public JSONFeatureBundleProviderFactory(InputStream jsonResource) {
    this.jsonResource = jsonResource;
  }

  @Override
  public FeatureBundleProvider create() throws IOException {

    final FeatureWrapper features = new Gson()
        .fromJson(new InputStreamReader(jsonResource, Charset.defaultCharset()),
            FeatureWrapper.class);

    final ImmutableList.Builder<Feature<?>> listBuilder = ImmutableList.builder();

    for (FeatureConfiguration c : features.getFeatures()) {

      final FeatureValueHydrator<Object> objectHydrator = HYDRATORS.get(c.getType());

      final ImmutableMap.Builder<Range, Object> overrides = ImmutableMap.builder();

      for (Map.Entry<String, Object> entry : c.getOverrides().entrySet()) {
        Iterable<String> ranges = CSV_SPLITTER.split(entry.getKey());
        String lower = Iterables.get(ranges, 0);
        String upper = Iterables.getLast(ranges, lower);
        Range<Integer> range = Range.closed(Integer.valueOf(lower), Integer.valueOf(upper));
        overrides.put(range, objectHydrator.value(entry.getValue()));
      }

      listBuilder.add(new SimpleFeature(c.getName(),
          c.getKey(),
          objectHydrator.value(c.getValue()),
          overrides.build()));
    }

    return new FeatureBundleProviderImpl(listBuilder.build());
  }

  static class FeatureWrapper {

    private List<FeatureConfiguration> features;

    public List<FeatureConfiguration> getFeatures() {
      return features;
    }
  }
}
