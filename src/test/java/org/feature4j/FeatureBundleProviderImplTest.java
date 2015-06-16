package org.feature4j;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Range;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FeatureBundleProviderImplTest {

  private final FeatureOverride<String> featureOverride = mock(FeatureOverride.class);
  private final Feature<String, FeaturesContext> feature1 = new SimpleFeature<>("1", "one", "1", null);
  private final Feature<String, FeaturesContext> feature2 = new SimpleFeature<>("1", "two", "0", ImmutableList.of(featureOverride));
  private FeatureBundleProvider featureProvider;


  @Before
  public void setUp() throws Exception {
    featureProvider = new FeatureBundleProviderImpl(ImmutableList.<Feature<?, ?>>of(feature1, feature2));
  }

  @Test
  public void testFeatureOverridesShouldBeIgnoredIfTheyReturnNull() throws Exception {
    when(featureOverride.extractFeatureValue(SimpleFeaturesContext.EMPTY)).thenReturn(null);
    FeatureBundle bundle = featureProvider.getFeatures(SimpleFeaturesContext.EMPTY);
    assertEquals(2, bundle.getFeatures().size());
    assertEquals("1", bundle.get("one", String.class, null));
    assertEquals("0", bundle.get("two", String.class, null));

  }
  @Test
  public void testFeatureOverridesShouldBeIgnoredIfTheyReturnAbsent() throws Exception {
    when(featureOverride.extractFeatureValue(SimpleFeaturesContext.EMPTY)).thenReturn(Optional.<String>absent());
    FeatureBundle bundle = featureProvider.getFeatures(SimpleFeaturesContext.EMPTY);
    assertEquals(2, bundle.getFeatures().size());
    assertEquals("1", bundle.get("one", String.class, null));
    assertEquals("0", bundle.get("two", String.class, null));
  }

  @Test
  public void testFeatureOverridesShouldBeUsedIfTheyReturnAValue() throws Exception {
    when(featureOverride.extractFeatureValue(SimpleFeaturesContext.EMPTY)).thenReturn(Optional.of("OVERRIDE"));
    FeatureBundle bundle = featureProvider.getFeatures(SimpleFeaturesContext.EMPTY);
    assertEquals("1", bundle.get("one", String.class, null));
    assertEquals("OVERRIDE", bundle.get("two", String.class, null));
  }
}
