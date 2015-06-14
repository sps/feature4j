package org.feature4j;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Range;

import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;

public class SimpleFeatureTest {

  private final String name = "name";
  private final String key = "key";
  private final String value = "value";
  private final String overrideValue = "override-value";
  private final Map<Range, String> overrides = ImmutableMap.<Range, String>of(
      Range.closed(1, 10), overrideValue
  );

  private SimpleFeature<String> feature;

  @Before
  public void setUp() throws Exception {
    feature = new SimpleFeature<>(name, key, value, overrides);
  }

  @Test
  public void testBasic() {
    assertEquals(name, feature.name());
    assertEquals(key, feature.key());
  }

  @Test
  public void testValue() {
    FeaturesContext context = FeaturesContext.EMPTY;
    assertEquals(value, feature.value(context));

    context = FeaturesContext.builder().bucketId(5).build();
    assertEquals(overrideValue, feature.value(context));

        /*
         * verify empty overrides returns default value.
         */
    feature = new SimpleFeature<>(name, key, value, null);

    assertEquals(value, feature.value(context));
  }
}
