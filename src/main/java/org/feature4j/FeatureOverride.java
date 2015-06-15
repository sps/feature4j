package org.feature4j;

/**
 * Created by dannwebster on 6/15/15.
 */
public interface FeatureOverride<V> {
    V extractFeatureValue(FeaturesContext ctx);
}
