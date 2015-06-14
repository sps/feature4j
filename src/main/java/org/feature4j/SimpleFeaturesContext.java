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

import static com.google.common.base.Preconditions.checkNotNull;

public class SimpleFeaturesContext implements FeaturesContext {

  private int bucketId;

  public static final SimpleFeaturesContext EMPTY = new Builder().bucketId(0).build();

  private SimpleFeaturesContext() {
  }

  public static Builder builder() {
    return new Builder();
  }

  public int getBucketId() {
    return bucketId;
  }

  public static class Builder {

    private final SimpleFeaturesContext context = new SimpleFeaturesContext();

    public SimpleFeaturesContext build() {
      checkNotNull(context.bucketId);
      return context;
    }

    public Builder bucketId(int bucketId) {
      context.bucketId = bucketId;
      return this;
    }
  }
}
