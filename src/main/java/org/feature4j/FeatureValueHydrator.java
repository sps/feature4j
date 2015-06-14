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

public interface FeatureValueHydrator<T> {

  T value(Object val);

  Class<T> type();

  enum String implements FeatureValueHydrator<java.lang.String> {

    INSTANCE;

    @Override
    public java.lang.String value(Object val) {
      return val == null ? null : val.toString();
    }

    @Override
    public Class<java.lang.String> type() {
      return java.lang.String.class;
    }

  }

  enum Boolean implements FeatureValueHydrator<java.lang.Boolean> {

    INSTANCE;

    @Override
    public Class<java.lang.Boolean> type() {
      return java.lang.Boolean.class;
    }

    @Override
    public java.lang.Boolean value(Object val) {
      return val != null && java.lang.Boolean.valueOf(val.toString());
    }
  }
}
