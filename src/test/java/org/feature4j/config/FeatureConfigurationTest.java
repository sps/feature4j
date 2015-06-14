package org.feature4j.config;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class FeatureConfigurationTest {

  @Test
  public void testCountingRocks() throws Exception {
    final FeatureConfiguration featureConfig = new FeatureConfiguration();
    assertNotNull(featureConfig.getOverrides());
    featureConfig.setKey(null);
    featureConfig.setName(null);
    featureConfig.setType(null);
    featureConfig.setType(null);
    featureConfig.setOverrides(null);
    featureConfig.setValue(null);
    assertNull(featureConfig.getKey());
    assertNull(featureConfig.getName());
    assertNull(featureConfig.getType());
    assertNull(featureConfig.getValue());
    assertNull(featureConfig.getOverrides());
  }
}
