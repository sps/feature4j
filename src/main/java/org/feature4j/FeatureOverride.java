package org.feature4j;


import com.google.common.base.Optional;

/**
 * Created by dannwebster on 6/15/15.
 */
public interface FeatureOverride<V> {
    Optional<V> extractFeatureValue(FeaturesContext ctx);
}
