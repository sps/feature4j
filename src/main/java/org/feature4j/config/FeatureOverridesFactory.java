package org.feature4j.config;

import org.feature4j.FeatureOverride;

/**
 * Created by dannwebster on 6/15/15.
 */
public interface FeatureOverridesFactory {
  Iterable<FeatureOverride> createOverrides(FeatureConfiguration featureConfiguration);
}
