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

public final class FeaturesContext {

  private Integer bucketId;
  public static final FeaturesContext EMPTY = new Builder().bucketId(0).build();

  private FeaturesContext() {
  }

  public static Builder builder() {
    return new Builder();
  }

  public Integer getBucketId() {
    return bucketId;
  }

  public static class Builder {

    private final FeaturesContext context = new FeaturesContext();

    public FeaturesContext build() {
      checkNotNull(context.bucketId);
      return context;
    }

    public Builder bucketId(Integer bucketId) {
      context.bucketId = bucketId;
      return this;
    }
  }
}
