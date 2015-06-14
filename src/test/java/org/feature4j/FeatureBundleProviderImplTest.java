package org.feature4j;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Range;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FeatureBundleProviderImplTest {

  private final Feature<String> feature1 = new SimpleFeature<>("1", "one", "1", null);
  private final Feature<String>
      feature2 =
      new SimpleFeature<>("1", "two", "0",
          ImmutableMap.<Range, String>of(Range.closed(1, 1), "2"));
  private FeatureBundleProvider featureProvider;

  @Before
  public void setUp() throws Exception {
    featureProvider =
        new FeatureBundleProviderImpl(ImmutableList.<Feature<?>>of(feature1, feature2));
  }

  @Test
  public void testGetFeatures() throws Exception {
    FeatureBundle bundle = featureProvider.getFeatures(FeaturesContext.EMPTY);
    assertEquals(2, bundle.getFeatures().size());
    assertEquals("1", bundle.get("one", String.class, null));
    assertEquals("0", bundle.get("two", String.class, null));

    bundle = featureProvider.getFeatures(FeaturesContext.builder().bucketId(1).build());
    assertEquals("2", bundle.get("two", String.class, null));
  }
}
