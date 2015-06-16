package org.feature4j;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Iterables;
import com.google.common.collect.Range;

import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class SimpleFeatureTest {

  private final String name = "name";
  private final String key = "key";
  private final String value = "value";
  private final String overrideValue = "override-value";
  private final Map<Range, String> overrides = ImmutableMap.<Range, String>of(
      Range.closed(1, 10), overrideValue
  );

  private SimpleFeature<String, FeaturesContext> feature;
  private FeatureOverride<String> featureOverride;

  @Before
  public void setUp() throws Exception {
    featureOverride = mock(FeatureOverride.class);
    feature = new SimpleFeature<>(name, key, value, ImmutableList.of(featureOverride));
  }

  @Test
  public void testBasic() {
    assertEquals(name, feature.name());
    assertEquals(key, feature.key());
    assertEquals(value, feature.defaultValue());
    assertEquals(1, Iterables.size(feature.overrides()));
  }

}
