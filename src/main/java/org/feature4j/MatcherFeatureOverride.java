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
import org.hamcrest.Matcher;

import static com.google.common.base.Preconditions.checkNotNull;

public class MatcherFeatureOverride<V> implements FeatureOverride<V> {

  private final Matcher matcher;
  private final V overrideValue;

  public MatcherFeatureOverride(Matcher matcher, V overrideValue) {
    this.matcher = checkNotNull(matcher, "matcher must be set to a non-null value");
    this.overrideValue = checkNotNull(overrideValue, "overrideValue must be set to a non-null value");
  }

  @Override
  public Optional<V> extractFeatureValue(FeaturesContext ctx) {
    if (matcher.matches(ctx)) {
      return Optional.of(overrideValue);
    } else {
      return Optional.absent();
    }
  }
}
